package com.example.stackoverflowuser

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.stackoverflowuser.Models.UserObject
import com.example.stackoverflowuser.Network.RestApiFactory
import com.example.stackoverflowuser.R.id.rv_user
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var userDatabase: UserDatabase
    lateinit var data: List<UserObject>
    lateinit var userViewModel: UserViewModel
    lateinit var userAdapter: UserAdapter
    lateinit var userAdapterWOPaging: UserAdapterWOPaging
    lateinit var userDao: StackUserDao
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        userDatabase = UserDatabase.invoke(this);
        userDao = userDatabase.userDatabaseDao()
//        setUpRecyclerWithPaging()
        setUpRecyclerWithoutPaging()
        getUpdateFromServer()

        /*btn_getallUsers.setOnClickListener {
            GlobalScope.launch {
                val response = userDatabase.userDatabaseDao().getAllUsers()
                println(response)
            }

        }*/
    }

    private fun getUpdateFromServer() {
        /*GlobalScope.launch {
            val response: JsonObject = RestApiFactory().fetchTopUsers(1, 100).await()

            val type = object : TypeToken<List<UserObject>>() {}.type
            data = Gson().fromJson<List<UserObject>>(response.getAsJsonArray("items"), type)
            saveToDb(data)



        }*/
        progress.visible()
        RestApiFactory().fetchTopUsers(1, 100).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                val type = object : TypeToken<List<UserObject>>() {}.type
                data = Gson().fromJson<List<UserObject>>(result.getAsJsonArray("items"), type)

            }, {error ->
                toast("Request TimeOut! Please Try again")
                println(error.localizedMessage)
            },{
                userAdapterWOPaging.setData(data)
                progress.hide()
            },{

            })
    }

    private fun setUpRecyclerWithoutPaging() {
        GlobalScope.launch {
//            userAdapterWOPaging = UserAdapterWOPaging(userDao.getAllUsers())
            userAdapterWOPaging = UserAdapterWOPaging(emptyList())
        }.invokeOnCompletion {
            runOnUiThread {
                rv_user.adapter = userAdapterWOPaging
                val layoutManager = LinearLayoutManager(applicationContext)
                rv_user.layoutManager = layoutManager
                val lineDecoration = DividerItemDecoration(applicationContext, layoutManager.orientation)
                rv_user.addItemDecoration(lineDecoration)
            }
        }
    }

    private fun setUpRecyclerWithPaging() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.init(userDao)
        userAdapter = UserAdapter()
        rv_user.adapter = userAdapter
        rv_user.layoutManager = LinearLayoutManager(applicationContext)

        userViewModel.userList?.observe(this, Observer {
            userAdapter.submitList(it)
        })
    }

    private fun saveToDb(data: List<UserObject>) {

        for (i in 0 until data.size) {
            GlobalScope.launch(Dispatchers.IO) {
                async { userDatabase.userDatabaseDao().insertUser(data.get(i)) }.await()
            }
        }

    }
}
