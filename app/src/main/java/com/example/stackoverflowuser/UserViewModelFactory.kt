package com.example.stackoverflowuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.stackoverflowuser.Network.RestApiFactory

class UserViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//todo
//        if(modelClass.isAssignableFrom(UserViewModel.javaClass))
//            return UserViewModel(UserRepository(RestApiFactory(), UserDatabase(applicationContext).userDatabaseDao())) as T
        return modelClass as T
    }
}