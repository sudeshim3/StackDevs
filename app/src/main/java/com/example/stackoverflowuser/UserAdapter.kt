package com.example.stackoverflowuser

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.stackoverflowuser.Models.UserObject

class UserAdapter : PagedListAdapter<UserObject, UserAdapter.UserViewHolder>(UserObject.DIFF_CALLBACK) {
    var size:Int = 0

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        if(user != null)
            holder.bindTo(user)
        println("number of times viewholder was created is: $size")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.user_row_item, parent, false)
        size++
        return UserViewHolder(view)
    }

    class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var txt_userName: TextView
        var txt_userLocation: TextView
        var txt_website: TextView
        var txt_reputation: TextView


        init {
            txt_userName = itemView.findViewById(R.id.txt_username)
            txt_userLocation = itemView.findViewById(R.id.txt_location)
            txt_website = itemView.findViewById(R.id.txt_website)
            txt_reputation = itemView.findViewById(R.id.txt_reputation)
        }

        fun bindTo(user: UserObject) {
                txt_userName.text = user.displayName
                txt_userLocation.text = user.location
                txt_website.text = user.websiteUrl
                txt_reputation.text = user.reputation.toString()
        }
    }
}


