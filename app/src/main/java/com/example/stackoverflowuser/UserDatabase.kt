package com.example.stackoverflowuser

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.stackoverflowuser.Models.UserObject

@Database(entities = [UserObject::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDatabaseDao(): StackUserDao

    companion object {
        @Volatile private var instance: UserDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "data")
                    .fallbackToDestructiveMigration()
                    .build()
    }
}