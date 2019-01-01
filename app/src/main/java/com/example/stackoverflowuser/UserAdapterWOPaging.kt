package com.example.stackoverflowuser

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stackoverflowuser.Models.UserObject

class UserAdapterWOPaging(var allUsers: List<UserObject>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var count:Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.user_row_item, parent, false)
        count++
        println("No of times without paging $count")
        return UserAdapter.UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allUsers.size
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.bindTo(allUsers.get(position))
    }

}