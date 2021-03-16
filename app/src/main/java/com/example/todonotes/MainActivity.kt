package com.example.todonotes

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todonotes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //private val viewmodel:myviewmodel by viewModels()
    lateinit var viewmodel: myviewmodel
    private lateinit var adapter: NotesAdapter


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        viewmodel = ViewModelProviders.of(this).get(myviewmodel::class.java)
        adapter = NotesAdapter()
        val recycler = binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewmodel.getallnotes().observe(this) {
            adapter.setNotesList(it)
        }

        val F_Button = binding.floatBott
        F_Button.setOnClickListener {

            val addIntent = Intent(
                this@MainActivity,
                DetailsActivity::class.java
            )
                .apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_DOCUMENT


                }
            startActivity(addIntent)
        }


        recycler.adapter = adapter


        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback
            (0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val note = adapter.getItemAtPosition(viewHolder.adapterPosition)
                viewmodel.Deletenote(note)
            }
        }).attachToRecyclerView(recycler)
        adapter.onItemClick = {
            val updateIntent = Intent(
                this@MainActivity,
                DetailsActivity::class.java
            )
                .apply {
                    putExtra(DetailsActivity.Extras_Title, it.title)
                    putExtra(DetailsActivity.Extras_Description, it.description)
                    putExtra(DetailsActivity.Extras_Date, it.date)
                    putExtra(DetailsActivity.Extras_ID, it.id)
                    flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT

                }
            startActivity(updateIntent)

        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mymenu = menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewmodel.DeleteAllNote()


        return true
    }


}