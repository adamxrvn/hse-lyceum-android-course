package com.adammz.hse_lyceum_room_notes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(

    val title: String,
    val text: String,
    val dateCreated: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)
