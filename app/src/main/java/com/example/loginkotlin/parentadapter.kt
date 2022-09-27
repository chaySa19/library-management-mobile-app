package com.example.loginkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class parentadapter(val data:ArrayList<parentItem>,val context: Context): RecyclerView.Adapter<parentadapter.MyViewHolder1>() {

    val viewPool = RecyclerView.RecycledViewPool()


    class MyViewHolder1(val row1: View): RecyclerView.ViewHolder(row1){
        val nom = row1.findViewById<TextView>(R.id.type)
        val ChildRecyclerView: RecyclerView = row1
        .findViewById(R.id.rv1)
        val togo= row1.findViewById<ImageButton>(R.id.imageButton)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): parentadapter.MyViewHolder1 {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.parent_layout,parent,false)
        return parentadapter.MyViewHolder1(layout)
    }

    override fun onBindViewHolder(holder1: parentadapter.MyViewHolder1, position: Int) {
          holder1.nom.text =data[position].ParentItemTitle



        holder1.togo.setOnClickListener(){

            val act2= Intent(context,Activity_list_vertical::class.java)
            val bn = Bundle()
            bn.putString("title",data[position].ParentItemTitle)
            act2.putExtras(bn)
            context.startActivity(act2)
        }
        val layoutManager1 = LinearLayoutManager(
            holder1.ChildRecyclerView.context, LinearLayoutManager.HORIZONTAL,
            false
        )
        layoutManager1.initialPrefetchItemCount =data[position].livreList.size
        val livreItemAdapter= MyAdapter(data[position].livreList, this.context)
       val titre= data[position].livreList.size

        holder1.ChildRecyclerView.apply{
            layoutManager=layoutManager1
            adapter=livreItemAdapter
            setRecycledViewPool(viewPool)

        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}