package com.tutor.overview_jetpack_component.core.data.local

import androidx.test.filters.SmallTest
import com.tutor.overview_jetpack_component.MVI.notes.core.di.NoteModule
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule

@HiltAndroidApp
@SmallTest
@UninstallModules(NoteModule::class)
class NoteDaoTest {
	@get:Rule
	var hiltRule = HiltAndroidRule(this)

	@Before
	fun setup() {
		hiltRule.inject()

	}
}