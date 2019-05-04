package com.example.stackoverflowuser

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stackoverflowuser.Models.UserObject
import android.view.animation.AnimationUtils


class UserAdapterWOPaging(var applicationContext: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var count: Int = 0
    var lastPosition: Int = 0
    var userList: List<UserObject> = mutableListOf()
    lateinit var userDatabase: UserDatabase

    init {
        userDatabase = UserDatabase.invoke(applicationContext)
    }

    fun setData(data: List<UserObject>) {
        userList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.user_row_item, parent, false)
        count++
        println("No of times without paging $count")
        return UserAdapter.UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.bindTo(userList.get(position))
        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(itemView: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_in_left)
            itemView.startAnimation(animation)
            lastPosition = position
        }
    }


}