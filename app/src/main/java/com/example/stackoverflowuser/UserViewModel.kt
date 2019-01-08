package com.example.stackoverflowuser

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.stackoverflowuser.Models.UserObject

class UserViewModel: ViewModel() {

    var userList: LiveData<PagedList<UserObject>>? = null

    fun init(userDao: StackUserDao) {
        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(true)
            .setPrefetchDistance(2)
            .setEnablePlaceholders(false)
            .setPageSize(3).build()

        userList = LivePagedListBuilder(userDao.getAllUsersPaged(), pagedListConfig)
            .build()
    }
}