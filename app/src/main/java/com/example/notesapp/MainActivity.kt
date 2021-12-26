package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val DBHelper by lazy { DBHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val saveNote = findViewById<Button>(R.id.saveButton)
        val etNote = findViewById<EditText>(R.id.etNote)
        saveNote.setOnClickListener {
            if (!etNote.text.isNullOrEmpty()) {
                val note = etNote.text.toString()
                DBHelper.saveData(note)
                Toast.makeText(this, "save Successfully", Toast.LENGTH_SHORT).show()
            }
        }

    }
}

