package com.example.todonotes.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todonotes.entities.Note

@Database(entities = [Note::class],
    version = 2, exportSchema = false)
abstract class  RoomDB: RoomDatabase() {
    abstract fun dao(): Dao




}