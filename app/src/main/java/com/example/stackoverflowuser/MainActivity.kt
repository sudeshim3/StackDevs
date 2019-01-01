package com.example.stackoverflowuser

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.stackoverflowuser.Models.UserObject
import com.example.stackoverflowuser.Network.RestApiFactory
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var userDatabase: UserDatabase
    lateinit var data: List<UserObject>
    lateinit var userViewModel: UserViewModel
    lateinit var userAdapter: UserAdapter
    lateinit var userDao: StackUserDao
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        userDatabase = UserDatabase.invoke(this);
        userDao = userDatabase.userDatabaseDao()
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.init(userDao)
        userAdapter = UserAdapter()
        rv_user.layoutManager = LinearLayoutManager(applicationContext)

        userViewModel.userList?.observe(this, Observer {
            userAdapter.submitList(it)
        })
        rv_user.adapter = userAdapter

        /*GlobalScope.launch {
            val response: JsonObject = RestApiFactory().fetchTopUsers(1).await()

            val type = object : TypeToken<List<UserObject>>() {}.type
            data = Gson().fromJson<List<UserObject>>(response.getAsJsonArray("items"), type)
            saveToDb(data)
        }*/

        /*btn_getallUsers.setOnClickListener {
            GlobalScope.launch {
                val response = userDatabase.userDatabaseDao().getAllUsers()
                println(response)
            }

        }*/
    }

    private fun saveToDb(data: List<UserObject>) {

        for (i in 0 until data.size) {
            GlobalScope.launch(Dispatchers.IO) {
                async { userDatabase.userDatabaseDao().insertUser(data.get(i)) }.await()
            }
        }

    }
}
