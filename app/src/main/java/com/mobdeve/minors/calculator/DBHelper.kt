package com.mobdeve.minors.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.mobdeve.minors.calculator.item.Item

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    @Override
    override fun onCreate(p0: SQLiteDatabase?) {

    }

    @Override
    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {

    }

    fun createTable() {
        val db = writableDatabase
        db.execSQL("CREATE TABLE IF NOT EXISTS tblcomputation (id INTEGER PRIMARY KEY,computation TEXT,answer TEXT)")
    }

    fun addComputation(computation: String,answer: String){
        val db = writableDatabase
        db.execSQL("INSERT INTO tblcomputation (computation,answer) VALUES ('$computation','$answer')")
    }

    @SuppressLint("Range")
    fun getHistory(): ArrayList<Item> {
        val items = ArrayList<Item>()
        val db = writableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery("select * from tblcomputation", null)
        } catch (e: SQLiteException) {
            return ArrayList()
        }

        var computation: String
        var answer: String

        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                computation = cursor.getString(cursor.getColumnIndex("computation"))
                answer = cursor.getString(cursor.getColumnIndex("answer"))

                items.add(Item(computation,answer))
                cursor.moveToNext()
            }
        }
        return items
    }

    companion object{
        private val DATABASE_NAME = "calculator"
        private val DATABASE_VERSION = 1
    }



}