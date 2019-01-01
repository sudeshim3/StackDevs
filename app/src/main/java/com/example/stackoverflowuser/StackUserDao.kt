package com.example.stackoverflowuser

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.example.stackoverflowuser.Models.UserObject

@Dao
public interface StackUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(userObject: UserObject):Long

    @Query("select * from stackusers")
    fun getAllUsers():List<UserObject>

    @Query("SELECT * FROM stackusers")
    abstract fun getAllUsersPaged(): DataSource.Factory<Int, UserObject>

    @Transaction
    open fun insertUser(userObject: UserObject) {
        upsert(userObject)
    }
}