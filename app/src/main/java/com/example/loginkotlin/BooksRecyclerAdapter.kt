package com.example.loginkotlin



import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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


class BooksRecyclerAdapter(var cont: Context, private val listBooks: ArrayList<livre>) : RecyclerView.Adapter<BooksRecyclerAdapter.BookViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder  {
        // inflating recycler item view
        val activity3:MainActivity3
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_found, parent, false)
        return BookViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        holder.BookName.text = listBooks[position].titre
        holder.BookAuthor.text = listBooks[position].nom_aut
        val title=listBooks[position].titre
        val auteur= listBooks[position].nom_aut
        val type= listBooks[position].type
        val img= listBooks[position].img
        val page= listBooks[position].pages
        val id = listBooks[position].id
        val desc= listBooks[position].desc
        val stock=listBooks[position].Stock
        Glide.with(cont)
            .asBitmap()
            .load(listBooks[position].img)
            .into(holder.BookImage);

        holder.itemView.setOnClickListener (
            object :View.OnClickListener{
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

                    val activity=v!!.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().replace(R.id.containero,frag).addToBackStack(null).commit()
                }

            })//END OF FRAGMENT



    }




    override fun getItemCount(): Int {
        return listBooks.size
    }
    /**
     * ViewHolder class
     */
    inner class BookViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

        var BookName = view.findViewById<TextView>(R.id.bookName)
        var BookAuthor=view.findViewById<TextView>(R.id.author)
        var BookImage=view.findViewById<ImageView>(R.id.imageBook)


    }
    private fun getBimap(image: ByteArray): Bitmap? {
        val bitmap= BitmapFactory.decodeByteArray(image,0,image.size)
        return bitmap
    }
}