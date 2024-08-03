package com.tutor.overview_jetpack_component.MVI.notes.core.di

import android.app.Application
import androidx.room.Room
import com.tutor.overview_jetpack_component.MVI.notes.core.data.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {
	@Provides
	@Singleton
	fun providerNoteDb(application: Application): NoteDatabase {
		return Room.databaseBuilder(
			application,
			NoteDatabase::class.java,
			"note_db"
		)
			.build()
	}
}