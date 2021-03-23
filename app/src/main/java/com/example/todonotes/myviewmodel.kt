package com.example.todonotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class myviewmodel @Inject constructor(
    private val repo: Repo,
    val app:Application

): AndroidViewModel(app) {

    fun getallnotes(): Flow<List<Note>>{


        return repo.getallnotes()

    }
    fun UpdateNote(note: Note){

            repo.updateNote(note)




    }
    fun Deletenote(note: Note){

            repo.deletNote(note)

    }
    fun DeleteAllNote(){


            repo.deleteAllNote()

    }
    fun InsertNewNote(note: Note){

            repo.insertNewNote(note)


    }
    fun hg(){}
}