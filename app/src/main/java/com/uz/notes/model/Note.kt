package com.uz.notes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


/**
 * Created by Eldor Turgunov on 04.09.2022.
 * Notes
 * eldorturgunov777@gmail.com
 */
@Entity
data class Note(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val content: String,
    val date: String,
    val color: Int = -1,

    ) : Serializable
