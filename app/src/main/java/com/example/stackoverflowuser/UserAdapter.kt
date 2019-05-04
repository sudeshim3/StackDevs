package com.example.stackoverflowuser

import android.graphics.Color
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stackoverflowuser.Models.UserObject
import kotlinx.android.synthetic.main.badge_count.view.*
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

   companion object {

   }  class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val gold_badge = itemView.badge_gold
        val badge_silver = itemView.badge_silver
        val badge_bronze = itemView.badge_bronze
        init {

            gold_badge.img_badge.setColorFilter(Constants.goldColor)
            badge_silver.img_badge.setColorFilter(Constants.silverColor)
            badge_bronze.img_badge.setColorFilter(Constants.bronzeColor)
        }

        fun bindTo(user: UserObject) {
            itemView.txt_username.text = user.displayName
            itemView.txt_location.text = user.location
            itemView.profile_img.setImageURI(user.profileUri)
            itemView.txt_reputation.text = user.reputation.toString()
            user.badgeCounts?.gold?.let {
                if(it > 0)
                    gold_badge.txt_badge.text = it.toString()
            }

            user.badgeCounts?.silver?.let {
                if(it > 0)
                    badge_silver.txt_badge.text = it.toString()
            }

            user.badgeCounts?.bronze?.let {
                if(it > 0)
                    badge_bronze.txt_badge.text = it.toString()
            }
        }
    }
}


