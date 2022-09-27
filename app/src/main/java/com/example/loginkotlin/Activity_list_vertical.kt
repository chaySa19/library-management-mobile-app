package com.example.loginkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class Activity_list_vertical : AppCompatActivity() {
    private  lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_vertical)
        lateinit var myHelper1: MyDbHelper

        val monInt= intent;
        val bn2 = monInt.extras

        val title=bn2?.getString("title")

        findViewById<TextView>(R.id.type).text=title


        myHelper1 = MyDbHelper(this)
        val arrayList11:ArrayList<livre> = DataManager.recuper_tt_Information(myHelper1,"${title}")!!

       // Toast.makeText(this, "${arrayList11.size}", Toast.LENGTH_SHORT).show()

        val rv: RecyclerView = findViewById(R.id.rv)
        rv.layoutManager= GridLayoutManager(this, 3)
        Log.i("TAG", "::::::::::::!!!!!!! Z3MA")


        rv.adapter =Activity2Adapter(arrayList11,this)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.livres -> {
                firebaseAuth= FirebaseAuth.getInstance()
                val ID= firebaseAuth.currentUser?.email
                val bn = Bundle()
                bn.putString("mailUser",ID.toString())



                val act2= Intent(this,livresReserveActivity::class.java)
                act2.putExtras(bn)
                this?.startActivity(act2)
               // Toast.makeText(applicationContext, "click on liivre", Toast.LENGTH_LONG).show()
                true
            }
            R.id.logout ->{
               // Toast.makeText(applicationContext, "click on logout", Toast.LENGTH_LONG).show()
                firebaseAuth= FirebaseAuth.getInstance()

                    firebaseAuth.signOut()
                    Intent(this, MainActivity::class.java).also {
                        it.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                return true
            }
            R.id.search ->{
                val searchIntent = Intent(this,MainActivity3::class.java)
                startActivity(searchIntent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}