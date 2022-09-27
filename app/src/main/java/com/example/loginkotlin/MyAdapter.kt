package com.example.loginkotlin



import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class MyAdapter(val data:ArrayList<livre>,val context: Context): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val row: View):RecyclerView.ViewHolder(row){
        val nom = row.findViewById<TextView>(R.id.book_title_id)
        val imgv =row.findViewById<ImageView>(R.id.book_img_id)
        val togo=row.findViewById<ImageButton>(R.id.imageButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nom.text=data[position].titre


        val title=data[position].titre
        val auteur= data[position].nom_aut
        val type= data[position].type
        val img= data[position].img
        val page= data[position].pages
        val id = data[position].id
        val desc= data[position].desc
        val stock=data[position].Stock
        //FRAGEMENT
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
                    putInt("Stock", stock)

                    putString("Desc", desc)


                }
                frag.arguments=bundle
                Log.i("chaymae", "onClick: ${            bundle.getString("titre")
                }")
                val activity=v!!.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().replace(R.id.container,frag).addToBackStack(null).commit()
            }

        })//END OF FRAGMENT

        Glide.with(context)
            .asBitmap()
            .load(data[position].img)
            .into(holder.imgv);
        //holder.imgv.setImageResource(data[position].img)
    }

    override fun getItemCount(): Int {
        return data.size
    }



}