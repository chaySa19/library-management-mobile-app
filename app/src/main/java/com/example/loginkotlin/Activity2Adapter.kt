package com.example.loginkotlin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Activity2Adapter (val data:ArrayList<livre>,val context: Context): RecyclerView.Adapter<Activity2Adapter.MyViewHolder2>() {


    class MyViewHolder2( val row: View):RecyclerView.ViewHolder(row){
    val nom = row.findViewById<TextView>(R.id.book_title_id)
    val imgv =row.findViewById<ImageView>(R.id.book_img_id)
}
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder2 {

        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return MyViewHolder2(layout)
    }

    override fun onBindViewHolder(holder:MyViewHolder2, position: Int) {
        holder.nom.text=data.get(position).titre

        val title=data[position].titre
        val auteur= data[position].nom_aut
        val type= data[position].type
        val img= data[position].img
        val page= data[position].pages
        val id = data[position].id
        val desc= data[position].desc
        holder.row.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val frag=FragmentDetail()
                val bundle = Bundle().apply {
                    putInt("id_livre", id)
                    putString("titre", title)
                    putString("auteur", auteur)
                    putString("type", type)
                    putString("img", img)
                    putInt("Pages", page)
                    putString("Desc", desc)


                }
                frag.arguments=bundle
                Log.i("CHAYMAE", "onClick: ${            bundle.getString("titre")
                }")
                val activity=v!!.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().replace(R.id.container,frag).addToBackStack(null).commit()
            }

        })












        Glide.with(context)
            .asBitmap()
            .load(data[position].img)
            .into(holder.imgv);
    }

    override fun getItemCount(): Int {
        return data.size
    }


}