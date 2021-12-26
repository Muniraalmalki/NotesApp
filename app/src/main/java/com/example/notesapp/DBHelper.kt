package com.example.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,"noteDB.db",null,1) {
    private val sqLiteDatabase:SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null){
            db.execSQL("create table notes(Note text)")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
        fun saveData(note:String){
            val contentValues = ContentValues()
            contentValues.put("Note",note)
            sqLiteDatabase.insert("notes",null,contentValues)
        }
}