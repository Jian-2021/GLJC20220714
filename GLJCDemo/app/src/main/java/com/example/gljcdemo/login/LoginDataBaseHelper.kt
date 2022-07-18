package com.example.gljcdemo.login

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class LoginDataBaseHelper(val context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {


    private val createLogin = "create table LoginData (" +
            "IDofDB integer primary key autoincrement," +
            "id text," +
            "account text," +
            "password text)"

    private val createLocalLogin = "create table LocalLoginData (" +
            "IDofDB integer primary key autoincrement," +
            "localId text," +
            "localAccount text," +
            "localPassword text)"
//    private val createCategory = "create table Category (" +
//            "IDofDB integer primary key autoincrement," +
//            "category_name text," +
//            "category_code integer)"

    private val createRememberPassword = "create table RememberPassword (" +
            "IDofDB integer primary key autoincrement," +
            "RememberPassword Boolean)"


    private val createAutoLogin = "create table AutoLogin (" +
            "IDofDB integer primary key autoincrement," +
            "AutoLogin Boolean)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createLogin)
        db.execSQL(createLocalLogin)
//        db.execSQL(createCategory)
        db.execSQL(createRememberPassword)
        db.execSQL(createAutoLogin)
        Toast.makeText(context, "Create LoginDataBase succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion <= 1) {
//            db.execSQL(createCategory)
        }
        if (oldVersion <= 2) {
//            db.execSQL("alter table LoginData add column category_id integer")
        }
    }




}