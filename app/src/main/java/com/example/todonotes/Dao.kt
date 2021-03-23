package com.example.todonotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface Dao {
    @Query("select * from Note order by _id ASC")
     fun getallnotes(): Flow<List<Note>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertnote(note: Note)
    @Update
    suspend fun updatenote(note: Note)
    @Delete
    suspend fun deletenote(note: Note )

    @Query("DELETE FROM Note")
   suspend fun  delete_all_notes()

}