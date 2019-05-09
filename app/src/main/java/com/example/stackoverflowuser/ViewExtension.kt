package com.example.stackoverflowuser

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Context.toast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}