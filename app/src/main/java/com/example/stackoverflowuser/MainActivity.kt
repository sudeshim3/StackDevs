package com.example.stackoverflowuser

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.example.stackoverflowuser.Models.UserObject
import com.example.stackoverflowuser.Network.RestApiFactory
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var userDatabase: UserDatabase
    lateinit var data: List<UserObject>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        userDatabase = UserDatabase.invoke(this);
        GlobalScope.launch {
            val response: JsonObject = RestApiFactory().fetchTopUsers(1).await()

            val type = object : TypeToken<List<UserObject>>() {}.type
            data = Gson().fromJson<List<UserObject>>(response.getAsJsonArray("items"), type)
            saveToDb(data)
            runOnUiThread {
                txt_tempResponse.setText(data.toString())
            }

        }

        btn_getallUsers.setOnClickListener {
            GlobalScope.launch {
                val response = userDatabase.userDatabaseDao().getAllUsers()
                println(response)
            }

        }
    }

    private fun saveToDb(data: List<UserObject>) {

//        var listOfAsyncTask: MutableList<Deferred<Unit>> = mutableListOf()

        for (i in 0 until data.size) {
            GlobalScope.launch(Dispatchers.Default) {
              val result =  userDatabase.userDatabaseDao().upsert(data.get(i))
            }
        }

    }
}
