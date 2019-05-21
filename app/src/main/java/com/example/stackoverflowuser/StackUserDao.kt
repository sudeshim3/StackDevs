package com.example.stackoverflowuser

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.stackoverflowuser.Models.UserObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Dao
public interface StackUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(userObject: UserObject): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg userList: UserObject)

    @Query("select * from stackusers")
    suspend fun getAllUsers(): List<UserObject>

    @Query("SELECT COUNT(*) FROM stackusers")
    suspend fun getCount(): Int

    @Query("SELECT * FROM stackusers ORDER BY badge_gold DESC")
    suspend fun getAllUsersByGold(): List<UserObject>

//    @Query("SELECT * FROM stackusers ORDER BY reputation DESC")
//    suspend fun getAllUsersPaged(): DataSource.Factory<Int, UserObject>

    @Transaction
    suspend fun insertUser(userObject: List<UserObject>) {
        GlobalScope.launch {
            userObject.forEach {
                upsert(it)
            }
        }
    }
}