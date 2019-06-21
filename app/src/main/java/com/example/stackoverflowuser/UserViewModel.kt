package com.example.stackoverflowuser

import android.os.Bundle
import android.renderscript.Sampler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.stackoverflowuser.Models.UserObject
import com.example.stackoverflowuser.Util.SingleLiveEvent
import com.example.stackoverflowuser.Util.singleArgViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture
import kotlin.reflect.KClass

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::UserViewModel)
    }

    var userList: LiveData<PagedList<UserObject>>? = null
    lateinit var userDatabase: UserDatabase
    private val _snackBar = MutableLiveData<String>()

    val snackbar: LiveData<String>
        get() = _snackBar

    private val _spinner = MutableLiveData<Boolean>()

    val spinner: LiveData<Boolean>
        get() = _spinner

    val uiEventLiveData = SingleLiveEvent<Pair<KClass<*>, Bundle>>()

    val users = repository.users

    fun init(userDao: StackUserDao) {
        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(true)
            .setPrefetchDistance(2)
            .setEnablePlaceholders(false)
            .setPageSize(3).build()
//        fooAsyc()
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

    fun fetchData() {
        launchDataLoad {
//            repository.fetchStackUsers()
            repository.fetchReputation()
        }
    }

    /*
*val activityToStart = MutableLiveData<Pair<KClass<*>, Bundle?>>()
This allows you to check the class of Activity started, and the data passed in the Bundle. Then, in your Activity, you can add this code:

 *  */
}