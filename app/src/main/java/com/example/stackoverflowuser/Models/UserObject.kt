package com.example.stackoverflowuser.Models

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import android.support.v7.util.DiffUtil
import com.google.gson.annotations.SerializedName

@Entity(tableName = "stackusers")
data class UserObject(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("user_id")
    var userId: Int? = 999,
    @SerializedName("badge_counts")
    @Embedded(prefix = "badge_")
    var badgeCounts: BadgeCounts?,
    @SerializedName("account_id")
    var accountId: Int? = 999,
    @SerializedName("is_employee")
    var isEmployee: Boolean? = false,
    @SerializedName("last_access_date")
    var lastAccessDate: Int? = 999,
    @SerializedName("reputation_change_month")
    var reputationChangeMonth: Int? = 999,
    var reputation: Int? = 999,
    @SerializedName("creation_date")
    var creationDate: Int? = 999,
    @SerializedName("user_type")
    var userType: String? = "",
    @SerializedName("accept_rate")
    var acceptRate: Int? = 999,
    var location: String? = "",
    @SerializedName("website_url")
    var websiteUrl: String? = "",
    var link: String? = "",
    @SerializedName("profile_image")
    var profileImage: String? = "",
    @SerializedName("display_name")
    var displayName: String? = ""
) {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<UserObject> = object : DiffUtil.ItemCallback<UserObject>() {
            override fun areItemsTheSame(@NonNull oldItem: UserObject, @NonNull newItem: UserObject): Boolean {
                return oldItem.userId == newItem.userId
            }

            override fun areContentsTheSame(@NonNull oldItem: UserObject, @NonNull newItem: UserObject): Boolean {
                return true
            }
        }
    }


    /*override fun equals(other: Any?): Boolean {
        if(other == this)
            return true

        var userObject: UserObject = other as UserObject
        return userObject.userId == this.userId
    }*/
}