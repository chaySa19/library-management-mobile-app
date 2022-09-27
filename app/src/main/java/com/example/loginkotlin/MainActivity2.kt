package com.example.loginkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginkotlin.DataManager
import com.example.loginkotlin.MyDbHelper
import com.example.loginkotlin.R
import com.example.loginkotlin.livre

class MainActivity3 : AppCompatActivity() ,View.OnClickListener{

    private val activity = this
    private val context=this
    private lateinit var recyclerViewBooks: RecyclerView
    private lateinit var inputSearch: EditText
    private lateinit var searchBtn: Button
    private lateinit var list: ArrayList<livre>
    lateinit var myHelper: MyDbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        searchBtn=findViewById(R.id.search)
        inputSearch=findViewById(R.id.input)

        initViews()
        initObjects()
        initListeners()
        searchBook()

    }

    private fun initListeners() {
        searchBtn!!.setOnClickListener(this)
    }
    private fun initObjects() {
        myHelper = MyDbHelper(this)

    }

    private fun initViews() {
        recyclerViewBooks = findViewById(R.id.recyclerViewBooks)
    }
    private fun searchBook() {
        Log.i("SAAD", "searchBook: ")
        list = DataManager.recuper_livre(myHelper,inputSearch.text.toString())!!
        recyclerViewBooks.layoutManager = LinearLayoutManager(this)
        recyclerViewBooks.adapter = BooksRecyclerAdapter(this,list)

    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.search -> searchBook()
        }
    }



}