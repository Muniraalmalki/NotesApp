package com.example.notesapp.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,"noteDB.db",null,2) {
    private val sqLiteDatabase:SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null){
            db?.execSQL("create table notes(pk INTEGER PRIMARY KEY AUTOINCREMENT,Note text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(db)
    }

        fun saveData(note:String): Long{
            val contentValues = ContentValues()
            contentValues.put("Note",note)
            return sqLiteDatabase.insert("notes",null,contentValues)
        }
    fun readData():ArrayList<Note>{
        val noteList = arrayListOf<Note>()

        // Read all data using cursor
        val cursor:Cursor = sqLiteDatabase.rawQuery("SELECT * FROM notes", null)
        if (cursor.count < 1){
            println("No Data Found")
        }else {
            while (cursor.moveToNext()){
                val pk = cursor.getInt(0)
                val note = cursor.getString(1)
                noteList.add(Note(pk, note))
            }
        }
        return noteList
    }
    fun updateData(note:Note): Int{
        val contentValues = ContentValues()
        contentValues.put("Note",note.note)
        return sqLiteDatabase.update("notes",contentValues,"pk = ${note.pk}",null)
    }
}


