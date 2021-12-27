package com.example.notesapp.Activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Model.DBHelper
import com.example.notesapp.Model.Note
import com.example.notesapp.R
import com.example.notesapp.Adapter.RecyclerViewAdapter

class GetDataListNoteActivity : AppCompatActivity() {

    val DBHelper by lazy { DBHelper(applicationContext) }

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var noteList: ArrayList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_data_list_note)

        title = "Notes List"
        recyclerView = findViewById(R.id.recyclerView)
        noteList = arrayListOf()


        recyclerViewAdapter = RecyclerViewAdapter(noteList)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Get data
        noteList = DBHelper.readData()
        recyclerViewAdapter.update(noteList)
        Toast.makeText(this, "get data Successfully", Toast.LENGTH_SHORT).show()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.back,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.back -> {
                val intent = Intent(this, NoteActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}