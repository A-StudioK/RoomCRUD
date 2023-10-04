package com.example.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Contact (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo
    var name: String,

    @ColumnInfo
    var telephone: String
)