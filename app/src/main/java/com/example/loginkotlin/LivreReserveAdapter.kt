package com.example.loginkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LivreReserveAdapter(val data:ArrayList<livre>,val context: Context): RecyclerView.Adapter<LivreReserveAdapter.MyViewHolder3>() {


    class MyViewHolder3( val row: View):RecyclerView.ViewHolder(row){
        val nom = row.findViewById<TextView>(R.id.book_title_id)
        val imgv =row.findViewById<ImageView>(R.id.book_img_id)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder3 {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return MyViewHolder3(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder3, position: Int) {
        holder.nom.text=data.get(position).titre

        Glide.with(context)
            .asBitmap()
            .load(data[position].img)
            .into(holder.imgv);
    }

    override fun getItemCount(): Int {
        return data.size
    }

}