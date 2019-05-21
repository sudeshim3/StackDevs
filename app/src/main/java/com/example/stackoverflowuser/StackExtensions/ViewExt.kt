package com.example.stackoverflowuser.StackExtensions

import android.content.Context
import android.view.View
import android.widget.Toast

object ViewExt {

    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }


}
inline fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}