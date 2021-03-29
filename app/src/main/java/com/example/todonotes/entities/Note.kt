package com.example.todonotes.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")

data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="_id")
    val id: Int = 0 ,
    @ColumnInfo(name ="_title")
    val title: String,
    @ColumnInfo(name ="_description")
    val description: String,
    @ColumnInfo(name ="_date")
    val date: String
    )
