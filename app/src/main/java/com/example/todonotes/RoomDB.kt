package com.example.todonotes

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class  RoomDB: RoomDatabase() {
    abstract fun dao():Dao




}