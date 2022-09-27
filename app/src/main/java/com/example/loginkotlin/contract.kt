package com.example.loginkotlin

import android.provider.BaseColumns

object contract {
    object Livre: BaseColumns {
        const val TABLE_NAME_BOOK = "livres"
        //   const val  COLUMN_id = "id_livre"

        const val  COLUMN_TITRE = "titre_livre"
        const val  COLUMN_AUTEUR = "nom_aut"
        const val  COLUMN_PAGES = "pages_livre"
        const val  COLUMN_IMAGES = "images_livre"
        const val  COLUMN_TYPES= "type_livre"
        const val  COLUMN_Desc= "Description_livre"
        const val  COLUMN_Stock= "Stock_livre"







        const val SQL_CREATE_TABLE = "CREATE TABLE $TABLE_NAME_BOOK("+
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "$COLUMN_TITRE TEXT,"+ "$COLUMN_AUTEUR TEXT,"+
                "$COLUMN_PAGES INTEGER,"+
                "$COLUMN_IMAGES TEXT," +
                "$COLUMN_Desc TEXT," +
                "$COLUMN_Stock INTEGER," +


                "$COLUMN_TYPES INTEGER)"

        const val SQL_DROP_TABLE = "DROP TABLE  IF EXISTS $TABLE_NAME_BOOK"

    }
}