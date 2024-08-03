package com.tutor.overview_jetpack_component.core.di

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
class TestAppModule {

	@Provides
	@Singleton
	fun provideMyDatabase(application: Application): NoteDatabase {
		return Room.inMemoryDatabaseBuilder(
			application,
			NoteDatabase::class.java,
//			"note_db.db"
		)
			.build()

	}
}