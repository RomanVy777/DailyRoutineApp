package com.example.dailyroutineapp.controller

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "DailyRoutine.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Crea la tabla para cumplir con el registro interno obligatorio
        db?.execSQL("CREATE TABLE Usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Usuarios")
        onCreate(db)
    }

    fun registrarAcceso(email: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply { put("email", email) }
        db.insert("Usuarios", null, values)
        db.close()
    }
}