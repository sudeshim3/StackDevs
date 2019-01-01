package com.example.stackoverflowuser.Models

import android.arch.persistence.room.Entity

@Entity
data class BadgeCounts(
    var bronze: Int,
    var silver: Int,
    var gold: Int
)