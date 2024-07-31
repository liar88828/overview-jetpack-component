package com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.converter

import androidx.room.TypeConverter
import java.util.Date

open class DateConverter {
	@TypeConverter
	fun toDate(date: Long?): Date? {
		return date?.let { Date(it) }
	}

	@TypeConverter
	fun fromDate(date: Date?): Long? {
		return date?.time
	}
}