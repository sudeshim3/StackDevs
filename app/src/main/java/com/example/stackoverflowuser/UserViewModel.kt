package com.example.stackoverflowuser

import android.renderscript.Sampler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.stackoverflowuser.Models.UserObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture

class UserViewModel : ViewModel() {

    var userList: LiveData<PagedList<UserObject>>? = null
    lateinit var userDatabase: UserDatabase
    private val _snackBar = MutableLiveData<String>()

    val snackbar: LiveData<String>
        get() = _snackBar

    private val _spinner = MutableLiveData<Boolean>()

    val spinner: LiveData<Boolean>
        get() = _spinner

    fun init(userDao: StackUserDao) {
        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(true)
            .setPrefetchDistance(2)
            .setEnablePlaceholders(false)
            .setPageSize(3).build()
        fooAsyc()
//        userList = LivePagedListBuilder(userDao.getAllUsersPaged(), pagedListConfig)
//            .build()
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            } catch (error: Exception) {
                _snackBar.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }


    private fun saveToDb(data: List<UserObject>) {

        GlobalScope.launch(Dispatchers.IO) {
            userDatabase.userDatabaseDao().insertAll(*data.toTypedArray())
        }
    }

    fun fooAsyc(): CompletableFuture<UserObject> = CompletableFuture.supplyAsync { }


}