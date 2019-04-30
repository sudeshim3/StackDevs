package com.example.stackoverflowuser

import android.animation.Animator
import androidx.paging.PagedListAdapter
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.TextView
import com.example.stackoverflowuser.Models.UserObject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_row_item.view.*

class UserAdapter(var row_item: Int) :
    PagedListAdapter<UserObject, UserAdapter.UserViewHolder>(UserObject.DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null)
            holder.bindTo(user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(row_item, parent, false)
        return UserViewHolder(view)
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        /*var txt_userName: TextView
        var txt_userLocation: TextView
        var txt_website: TextView
        var txt_reputation: TextView*/


        init {
            /*txt_userName = itemView.findViewById(R.id.txt_username)
            txt_userLocation = itemView.findViewById(R.id.txt_location)
            txt_website = itemView.findViewById(R.id.txt_website)
            txt_reputation = itemView.findViewById(R.id.txt_reputation)*/

        }

        fun bindTo(user: UserObject) {
            itemView.txt_username.text = user.displayName
            itemView.txt_location.text = user.location
            itemView.txt_website.text = user.websiteUrl
            itemView.txt_reputation.text = user.reputation.toString()
        }
    }
}


