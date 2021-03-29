package com.example.todonotes.DataBase

import androidx.room.*
import androidx.room.Dao
import com.example.todonotes.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("select * from Note order by _id ASC")
     fun getallnotes(): Flow<List<Note>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertnote(note: Note)
    @Update
    suspend fun updatenote(note: Note)
    @Delete
    suspend fun deletenote(note: Note)

    @Query("DELETE FROM Note")
   suspend fun  delete_all_notes()

}