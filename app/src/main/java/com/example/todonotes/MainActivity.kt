package com.example.todonotes

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todonotes.R.color.design_default_color_secondary
import com.example.todonotes.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewmodel: myviewmodel by viewModels()
    private lateinit var adapter: NotesAdapter

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        adapter = NotesAdapter()

        val recycler = binding.recyclerView
            .apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(this@MainActivity)
            }


        GlobalScope.launch(Dispatchers.Main) {
            viewmodel.getallnotes().buffer().collect {

                adapter.setNotesList(it)
            }

        }


        val floating_Button = binding.floatBott
        floating_Button.setOnClickListener {

            val addIntent = Intent(
                this@MainActivity,
                DetailsActivity::class.java
            )
                .apply {
                    action = DetailsActivity.INTENT_ACTION_ADD_NEW


                }
            startActivity(addIntent)
        }


        recycler.adapter = adapter


        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback
            (
            0,
            ItemTouchHelper.LEFT
                    or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder, direction: Int
            ) {
                val note = adapter.getItemAtPosition(viewHolder.adapterPosition)
                viewmodel.Deletenote(note)
                makeSnack(binding.root, "Note deleted successfully") {
                    viewmodel.InsertNewNote(note)
                }.show()


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


                    action = DetailsActivity.INTENT_ACTION_UPDATE

                }
            startActivity(updateIntent)

        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewmodel.DeleteAllNote()


        return true
    }

    private fun makeSnack(
        view: View,
        text: String,
        action: () -> Unit
    )
            : Snackbar {
        return Snackbar.make(view, text, Snackbar.LENGTH_LONG).setAction(
            "Undo"
        ) { action.invoke() }
            .setActionTextColor(resources.getColor(design_default_color_secondary))
            .setBackgroundTint(resources.getColor(R.color.black))
            .setTextColor(resources.getColor(R.color.design_default_color_background))
    }


}