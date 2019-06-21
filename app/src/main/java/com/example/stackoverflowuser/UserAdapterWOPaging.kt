package com.example.stackoverflowuser

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stackoverflowuser.Models.UserObject
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.badge_count.view.*
import kotlinx.android.synthetic.main.user_row_item.view.*
import java.text.DecimalFormat


class UserAdapterWOPaging(
    var applicationContext: Context,
    var userViewModel: UserViewModel
) :
    RecyclerView.Adapter<UserAdapterWOPaging.UserViewHolder>() {

    var lastPosition: Int = 0
    var userList: List<UserObject> = mutableListOf()
    var onItemClick: ((UserObject) -> Unit)? = null
    val formatter = DecimalFormat("#,##,##0")

    fun setData(data: List<UserObject>) {
        userList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_row_item, parent, false))


    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindTo(userList.get(position))
//        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(itemView: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_in_left)
            itemView.startAnimation(animation)
            lastPosition = position
        }
    }


    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val gold_badge = itemView.badge_gold
        val badge_silver = itemView.badge_silver
        val badge_bronze = itemView.badge_bronze

        init {
            gold_badge.img_badge.setColorFilter(Constants.goldColor)
            badge_silver.img_badge.setColorFilter(Constants.silverColor)
            badge_bronze.img_badge.setColorFilter(Constants.bronzeColor)
            setOnClick()
        }

        private fun setOnClick() {
            itemView.setOnClickListener {
                val bundle = Bundle()
//                userViewModel.uiEventLiveData.value = Pair(UserDetailActivity::class, Bundle())
//                userList[adapterPosition]
            }
        }

        fun bindTo(user: UserObject) {
            itemView.txt_username.text = user.displayName
            itemView.txt_location.text = user.location
            itemView.profile_img.setImageURI(user.profileUri)

            itemView.txt_reputation.text = formatter.format(user.reputation)
            user.badgeCounts?.gold?.let {
                if (it > 0)
                    gold_badge.txt_badge.text = it.toString()
            }

            user.badgeCounts?.silver?.let {
                if (it > 0)
                    badge_silver.txt_badge.text = it.toString()
            }

            user.badgeCounts?.bronze?.let {
                if (it > 0)
                    badge_bronze.txt_badge.text = it.toString()
            }
        }

    }
}