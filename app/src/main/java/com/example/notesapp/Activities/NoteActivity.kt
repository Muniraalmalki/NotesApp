package com.example.notesapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Model.DBHelper
import com.example.notesapp.Model.Note
import com.example.notesapp.R
import com.example.notesapp.Adapter.RecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteActivity : AppCompatActivity() {
    val DBHelper by lazy { DBHelper(applicationContext) }

    private lateinit var etNote: EditText
    private lateinit var saveNote: FloatingActionButton

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var noteList: ArrayList<Note>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        saveNote = findViewById(R.id.saveButton)
        etNote = findViewById(R.id.etNote)
        recyclerView = findViewById(R.id.recyclerView)
        noteList = arrayListOf()

        recyclerViewAdapter = RecyclerViewAdapter(noteList)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        // save DB
        saveNote.setOnClickListener {
            if (!etNote.text.isNullOrEmpty()) {
                val note = etNote.text.toString()
                DBHelper.saveData(note)
                Toast.makeText(this, "save Successfully", Toast.LENGTH_SHORT).show()
                noteList = DBHelper.readData()
                recyclerViewAdapter.update(noteList)
                recyclerView.adapter = recyclerViewAdapter
            } else {
                Toast.makeText(this, "Not save Successfully", Toast.LENGTH_SHORT).show()
            }
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.getData -> {
                val intent = Intent(this, GetDataListNoteActivity::class.java)
                startActivity(intent)
                return true
            }
        }
            return super.onOptionsItemSelected(item)
        }


}


