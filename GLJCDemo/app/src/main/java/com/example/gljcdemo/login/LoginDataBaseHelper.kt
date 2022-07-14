package com.example.gljcdemo.login

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class LoginDataBaseHelper(val context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {


    private val createGL = "create table PSW (" +
            "IDofDB integer primary key autoincrement," +
            "id text," +
            "account text," +
            "password text)"

    private val createCategory = "create table Category (" +
            "IDofDB integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createGL)
        db.execSQL(createCategory)
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion <= 1) {
            db.execSQL(createCategory)
        }
        if (oldVersion <= 2) {
            db.execSQL("alter table GL add column category_id integer")
        }
    }




}