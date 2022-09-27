package com.example.loginkotlin


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.loginkotlin.contract.Livre.SQL_CREATE_TABLE
import com.example.loginkotlin.contract.Livre.SQL_DROP_TABLE
import com.example.loginkotlin.reservationLivre.reservation.SQL_CREATE_TABLE_RESERVATION
import com.example.loginkotlin.reservationLivre.reservation.SQL_DROP_TABLE_RESERVATION

class MyDbHelper (context: Context): SQLiteOpenHelper(context,db_name,null, db_version) {

    companion object{
        val db_name = "bibliotheque.db"
        val db_version=7
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
        db?.execSQL(SQL_CREATE_TABLE_RESERVATION)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DROP_TABLE)
        db?.execSQL(SQL_DROP_TABLE_RESERVATION)

        onCreate(db)
    }

}