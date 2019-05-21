package com.example.stackoverflowuser

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.facebook.drawee.backends.pipeline.Fresco
import com.example.stackoverflowuser.StackExtensions.toast
import timber.log.Timber



class StackApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        fun exceMsg(type: Constants.ExceptionType) {
            when(type) {
                Constants.ExceptionType.NETWORK -> Timber.e("💣\uD83D\uDCA3\uD83D\uDCA3 Network error, Please check your network")
            }
        }
    }
}