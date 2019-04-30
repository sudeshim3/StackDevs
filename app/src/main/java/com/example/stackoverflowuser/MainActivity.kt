package com.example.stackoverflowuser

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stackoverflowuser.Constants.ExceptionType
import com.example.stackoverflowuser.Models.UserObject
import com.example.stackoverflowuser.Network.RestApiFactory
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import com.example.stackoverflowuser.StackExtensions.ViewExt.visible
import com.example.stackoverflowuser.StackExtensions.ViewExt.gone
import java.lang.Exception
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {

    lateinit var userDatabase: UserDatabase
    lateinit var data: List<UserObject>
    lateinit var userViewModel: UserViewModel
    private val userAdapter by lazy {
        UserAdapter(R.layout.user_row_item)
    }
    lateinit var userAdapterWOPaging: UserAdapterWOPaging
    lateinit var userDao: StackUserDao
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        btnAmigo.visible()
        btnAmigo.gone()

        userDatabase = UserDatabase.invoke(this)
        userDao = userDatabase.userDatabaseDao()
//        setUpRecyclerWithPaging()
//        setUpRecyclerWithoutPaging(data)

        GlobalScope.launch(Dispatchers.IO) {
            getUpdateFromServer()
        }

        /*btn_getallUsers.setOnClickListener {
            GlobalScope.launch {
                val response = userDatabase.userDatabaseDao().getAllUsers()
                println(response)
            }

        }*/
    }

    suspend fun getUpdateFromServer() {
        try {

            val response: JsonObject = RestApiFactory().fetchTopUsers(1, 50).await()
            val type = object : TypeToken<List<UserObject>>() {}.type
            data = Gson().fromJson<List<UserObject>>(response.getAsJsonArray("items"), type)
            setUpRecyclerWithoutPaging(data)
            saveToDb(data)
        } catch (e: Exception) {
            if (e is UnknownHostException)
                StackApplication.exceMsg(ExceptionType.NETWORK)
            GlobalScope.launch(Dispatchers.Main) {
                showExceptionToast(ExceptionType.NETWORK)
            }
        }
    }

    private fun showExceptionToast(network: ExceptionType) {
        when (network) {
            ExceptionType.NETWORK -> Toast.makeText(
                this,
                resources.getString(R.string.network_error),
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    private fun setUpRecyclerWithoutPaging(data: List<UserObject>) {
        GlobalScope.launch {
            userAdapterWOPaging = UserAdapterWOPaging(data)
        }.invokeOnCompletion {
            runOnUiThread {
                rv_user.adapter = userAdapterWOPaging
                rv_user.layoutManager = LinearLayoutManager(applicationContext)
            }
        }
    }

    private fun setUpRecyclerWithPaging() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.init(userDao)
        rv_user.adapter = userAdapter
        rv_user.layoutManager = LinearLayoutManager(applicationContext)

        userViewModel.userList?.observe(this, Observer {
            userAdapter.submitList(it)
        })
    }

    private fun saveToDb(data: List<UserObject>) {


        GlobalScope.launch(Dispatchers.IO) {
            userDatabase.userDatabaseDao().insertAll(*data.toTypedArray())
        }


    }
}
