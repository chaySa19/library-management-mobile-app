package com.example.loginkotlin
import android.content.ContentValues
import android.provider.BaseColumns
import android.util.Log
import com.example.loginkotlin.contract.Livre.COLUMN_AUTEUR

import com.example.loginkotlin.contract.Livre.COLUMN_Desc
import com.example.loginkotlin.contract.Livre.COLUMN_IMAGES
import com.example.loginkotlin.contract.Livre.COLUMN_PAGES
import com.example.loginkotlin.contract.Livre.COLUMN_Stock
import com.example.loginkotlin.contract.Livre.COLUMN_TITRE
import com.example.loginkotlin.contract.Livre.COLUMN_TYPES
import com.example.loginkotlin.contract.Livre.TABLE_NAME_BOOK
import com.example.loginkotlin.reservationLivre.reservation.COLUMN_DateResv
import com.example.loginkotlin.reservationLivre.reservation.COLUMN_idBook

import com.example.loginkotlin.reservationLivre.reservation.COLUMN_mailUser

import com.example.loginkotlin.reservationLivre.reservation.TABLE_NAME_RESERVATION

object DataManager {
    fun insererInformation(
        myHelper: MyDbHelper, titre: String, nom_aut:String, pages:Int, img:String,
        desc:String,
        stock: Int,
        type:String){
        val db = myHelper.writableDatabase
        val values = ContentValues()
        with(values){
            put("$COLUMN_TITRE", titre)
            put("$COLUMN_AUTEUR", nom_aut)
            put("$COLUMN_PAGES", pages)
            put("$COLUMN_IMAGES", img)
            put("$COLUMN_Desc", desc)
            put("$COLUMN_Stock", stock)
            put("$COLUMN_TYPES", type)

            Log.i("chaymae", "INSERTED")

        }
        val newRowId =db?.insert(TABLE_NAME_BOOK,null,values)


    }
    fun resrever(
        myHelper: MyDbHelper,
        id_livre: Int?,

        mail_user:String,

        dateresv:String,
        stock:Int?){

        val db = myHelper.writableDatabase
        val values = ContentValues()
        with(values){
            put("$COLUMN_idBook", id_livre)
            put("$COLUMN_mailUser", mail_user)

            put("$COLUMN_DateResv", dateresv)


            Log.i("chaymae", "INSERTED")

        }
        val newRowId =db?.insert(TABLE_NAME_RESERVATION,null,values)
        val updateBook = ContentValues()
        updateBook.put("$COLUMN_Stock", stock?.minus(1))
        db.update(
            TABLE_NAME_BOOK,updateBook,"${BaseColumns._ID}=?",
            arrayOf(id_livre.toString())
        )
    }
    fun recuper_tt_Information(myHelper : MyDbHelper, TYPE:String): ArrayList<livre>?{
        val db = myHelper.readableDatabase
        val selectionArgs = arrayOf(TYPE)
        val livres = ArrayList<livre>()
        val cursor = db.query(
            TABLE_NAME_BOOK,   // The table to query
            null,             // The array of columns to return (pass null to get all)
            "$COLUMN_TYPES = ?",              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        with(cursor){


            while(moveToNext()) {
                val livre_titre = getString(getColumnIndexOrThrow(COLUMN_TITRE))
                val auteur_nom = getString(getColumnIndexOrThrow(COLUMN_AUTEUR))
                val livre_page = getInt(getColumnIndexOrThrow(COLUMN_PAGES))
                val livre_img = getString(getColumnIndexOrThrow(COLUMN_IMAGES))
                val livre_desc = getString(getColumnIndexOrThrow(COLUMN_Desc))

                val livre_type = getString(getColumnIndexOrThrow(COLUMN_TYPES))
                val stock = getInt(getColumnIndexOrThrow(COLUMN_Stock))

                Log.i("CHAYMAAAAaaaaaaaaaaE", "ANA HNA F RECUPERE")


                livres.add(livre(getString(0).toInt(),livre_titre, auteur_nom, livre_page, livre_img,livre_desc, livre_type,stock))
            }
        }

        return  livres
    }


    fun recuper_table_reserver_byId(myHelper : MyDbHelper, mail:String): ArrayList<reservation> {
        val db = myHelper.readableDatabase
        val selectionArgs = arrayOf(mail)
        val id_livres = ArrayList<reservation>()
        val cursor = db.query(
            TABLE_NAME_RESERVATION ,   // The table to query
            null,             // The array of columns to return (pass null to get all)
            "$COLUMN_mailUser= ?",              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        with(cursor){


            while(moveToNext()) {
                val id_livre = getInt(getColumnIndexOrThrow(COLUMN_idBook))
                val date = getString(getColumnIndexOrThrow(COLUMN_DateResv))





                Log.i("CHAYMAAAAaaaaaaaaaaE", "ANA HNA F RECUPERE")

                id_livres.add(reservation(getString(0).toInt(),mail,id_livre,date))

            }
        }

        return  id_livres
    }
    fun recupereLivreById(myHelper : MyDbHelper, id_book: Int):livre{
        val db = myHelper.readableDatabase

        var livre:livre=livre(0,"","",0,"","","",0)
        val cursor =db.rawQuery("select * from " +  TABLE_NAME_BOOK  + " where " + BaseColumns._ID+ "=" + id_book  , null);


        with(cursor){


            while(moveToNext()) {
                val livre_titre = getString(getColumnIndexOrThrow(COLUMN_TITRE))
                val auteur_nom = getString(getColumnIndexOrThrow(COLUMN_AUTEUR))
                val livre_page = getInt(getColumnIndexOrThrow(COLUMN_PAGES))
                val livre_img = getString(getColumnIndexOrThrow(COLUMN_IMAGES))
                val livre_desc = getString(getColumnIndexOrThrow(COLUMN_Desc))

                val livre_type = getString(getColumnIndexOrThrow(COLUMN_TYPES))
                val stock = getInt(getColumnIndexOrThrow(COLUMN_Stock))

                Log.i("CHAYMAAAAaaaaaaaaaaE", "ANA HNA F RECUPERE")


                livre=livre(getString(0).toInt(),livre_titre, auteur_nom, livre_page, livre_img,livre_desc, livre_type,stock)
            }
        }

        return  livre
    }
    fun recuper_livre(myHelper : MyDbHelper, titre:String): ArrayList<livre>?{
        Log.i("SAAD", "searchBook1111111: ")

        val db = myHelper.readableDatabase
        val selectQuery = "SELECT  * FROM ${TABLE_NAME_BOOK} WHERE ${COLUMN_TITRE} = ?"
        val livres = ArrayList<livre>()
        val cursor=db.rawQuery(selectQuery, arrayOf(titre))

        with(cursor){

            while(moveToNext()) {
                val livre_titre = getString(getColumnIndexOrThrow(COLUMN_TITRE))
                val auteur_nom = getString(getColumnIndexOrThrow(COLUMN_AUTEUR))
                val livre_page = getInt(getColumnIndexOrThrow(COLUMN_PAGES))
                val livre_img = getString(getColumnIndexOrThrow(COLUMN_IMAGES))
                val livre_desc = getString(getColumnIndexOrThrow(COLUMN_Desc))
                val stock = getInt(getColumnIndexOrThrow(COLUMN_Stock))
                val livre_type = getString(getColumnIndexOrThrow(COLUMN_TYPES))

                Log.i("hiba", livre_type)


                livres.add(livre(getString(0).toInt(),livre_titre, auteur_nom, livre_page, livre_img,livre_desc, livre_type,stock))
            }
        }

        return  livres

    }
}