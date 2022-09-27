package com.example.loginkotlin

import android.provider.BaseColumns

object reservationLivre {
    object reservation: BaseColumns {
        const val TABLE_NAME_RESERVATION = "reservation"
        //   const val  COLUMN_id = "id_livre"

        const val  COLUMN_idBook = "id_livre"

        const val  COLUMN_DateResv = "date_reservation"

        const val  COLUMN_mailUser= "mailUser"









        const val SQL_CREATE_TABLE_RESERVATION = "CREATE TABLE $TABLE_NAME_RESERVATION("+
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "$COLUMN_idBook INTEGER,"+
                "$COLUMN_DateResv TEXT," +

                "$COLUMN_mailUser TEXT)"

        const val SQL_DROP_TABLE_RESERVATION = "DROP TABLE  IF EXISTS $TABLE_NAME_RESERVATION"

    }
}