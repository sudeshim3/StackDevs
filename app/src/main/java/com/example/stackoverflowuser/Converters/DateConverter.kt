package com.example.stackoverflowuser.Converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    companion object {
        fun toDate(timeStamp: Long?): Date? {

            if (timeStamp == null)
                return null
            return Date(timeStamp)
        }

        fun toTimeStamp(date: Date?): Long? {
            return date?.time
        }
    }


}