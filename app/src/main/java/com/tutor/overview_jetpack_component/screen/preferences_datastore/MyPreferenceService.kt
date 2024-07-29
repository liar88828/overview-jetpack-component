package com.tutor.overview_jetpack_component.screen.preferences_datastore

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

enum class Priority(val color: Color) {
	High(color = Color.Red),
	Medium(color = Color.Yellow),
	Low(color = Color.Blue),

}

data class TaskStatus(
	val isComplete: Boolean,
	val priority: Priority
)

/**
 * Provides a DataStore for storing preferences in the application context.

 * The DataStore is named "setting" and is used to persist key-value pairs.

 */
val Context.myPreferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")

/**
 * A class responsible for managing preferences related to a task, leveraging DataStore.
 *
 * It provides a [taskStatusFlow] to observe the current task status and methods to update the task's
 * completion status ([updateIsComplete]) and priority ([updateIsPriority]).
 */
@Singleton
class MyPreferenceDataStore
@Inject constructor(
	@ApplicationContext context: Context
) {
	private val myPreferenceDataStore = context.myPreferenceDataStore

	/**
	 * This object holds the keys for preferences used in the application.
	 */
	private object PreferenceKeys {
		val IS_COMPLETE_KEY = booleanPreferencesKey("is_complete")
		val PRIORITY_KEY = stringPreferencesKey("priority")
	}

	/**
	 * Flow that emits the current task status from the `myPreferenceDataStore`.
	 *
	 * [catch] It handles IOExceptions by emitting empty preferences, allowing the UI to handle the absence of data gracefully.
	 * Other exceptions are rethrown to be handled upstream.
	 *
	 * Each emission represents the latest task status, including its completion status and priority.
	 *
	 * @return a Flow of [TaskStatus] representing the current task status.
	 */
	val taskStatusFlow = myPreferenceDataStore.data
		.catch { exception ->
			if (exception is IOException) {
				emit(emptyPreferences())
			} else {
				throw exception
			}
		}
		.map { preferences ->
			val isComplete = preferences[PreferenceKeys.IS_COMPLETE_KEY] ?: false
			val priority = Priority.valueOf(
				preferences[PreferenceKeys.PRIORITY_KEY] ?: Priority.Low.name
			)
			TaskStatus(isComplete, priority)
		}

	/**
	 * Updates the 'isComplete' flag in the preference data store.
	 *
	 * @param isComplete The new value for the 'isComplete' flag.
	 */
	suspend fun updateIsComplete(isComplete: Boolean) {
		myPreferenceDataStore.edit { preferences ->
			preferences[PreferenceKeys.IS_COMPLETE_KEY] = isComplete
		}
	}

	/**
	 * Updates the stored priority value in the preference data store.
	 *
	 * @param priority The new priority to be saved.
	 */
	suspend fun updateIsPriority(priority: Priority) {
		myPreferenceDataStore.edit { preferences ->
			preferences[PreferenceKeys.PRIORITY_KEY] = priority.name
		}
	}

}