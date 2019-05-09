package com.example.stackoverflowuser.Network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RestApiFactory {

        private val BASE_URL = "https://api.stackexchange.com/2.2/"

        operator fun invoke(): Api {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val gson = GsonBuilder().create()

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(Api::class.java)
        }
    }
