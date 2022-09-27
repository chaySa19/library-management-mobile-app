package com.example.loginkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginkotlin.DataManager.recupereLivreById

class livresReserveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livres_reserve)
        val textV= findViewById<TextView>(R.id.textView4)
        var arraylist=ArrayList<livre>()
        val monInt= intent;
        val bn2 = monInt.extras
        val mail=bn2?.getString("mailUser")
        val myHelper = MyDbHelper(this)
        var livres= ArrayList<livre>()
        var id_livres: ArrayList<reservation>
        id_livres = DataManager.recuper_table_reserver_byId(myHelper, mail.toString())
        if(id_livres.isEmpty()){
            textV.text="Vouz n'avez pas encore réserver aucun livre"
        }else{
            textV.text="Les livres que vous avez réserver"

        }
        for (id in id_livres){
            livres.add(recupereLivreById(myHelper,id.idBook))
        }

        arraylist.add(recupereLivreById(myHelper,1))



        val myAdapter1 = LivreReserveAdapter(livres,this)

        val rv1 :RecyclerView = findViewById(R.id.rv1)


        rv1.layoutManager=  GridLayoutManager(this, 3)
        rv1.adapter =myAdapter1



    }


}