package com.example.todonotes

import androidx.lifecycle.LiveData

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class Repo @Inject constructor(private val dao: Dao) {


    fun getallnotes(): Flow<List<Note>> {

        return dao.getallnotes()
    }


    fun insertNewNote(note: Note) {
        GlobalScope.launch {
            dao.insertnote(note)
        }


    }

    fun updateNote(note: Note) {
        GlobalScope.launch {
            dao.updatenote(note)
        }
    }

    fun deletNote(note: Note) {
        GlobalScope.launch {
            dao.deletenote(note)
        }

    }

    fun deleteAllNote() {
        GlobalScope.launch {
            dao.delete_all_notes()
        }

    }


}