package com.example.stackoverflowuser.Models

import androidx.room.Entity

@Entity
data class BadgeCounts(
    var bronze: Int?,
    var silver: Int?,
    var gold: Int?
)