package com.example.stackoverflowuser

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.stackoverflowuser.Models.UserObject

@Dao
public interface StackUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(userObject: UserObject):Long

    @Query("select * from stackusers")
    fun getAllUsers():List<UserObject>
}