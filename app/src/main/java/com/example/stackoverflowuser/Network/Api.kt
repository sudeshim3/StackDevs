package com.example.stackoverflowuser.Network

import com.example.stackoverflowuser.Models.UserObject
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    //    @GET("/2.2/users?page=1&order=desc&sort=reputation&site=stackoverflow")
    @GET("/2.2/users?site=stackoverflow")
    fun fetchTopUsers(@Query("page") page: Int,
                      @Query("pagesize") pageSize: Int): Deferred<JsonObject>

    @GET("/2.2/users?site=stackoverflow")
    fun fetchTopUsers(@Query("page") page: Int): Deferred<JsonObject>

}