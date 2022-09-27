package com.example.loginkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.loginkotlin.DataManager.recuper_table_reserver_byId
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var title:String?=null
    private var auteur:String?=null
    private var type:String?=null
    private var img:String?=null
    private var desc:String?=null

    private var page:Int?=null
    private var id_livre:Int?=null
    private var Stock:Int?=null

    lateinit var myHelper: MyDbHelper
    private  lateinit var firebaseAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        arguments?.let {
           title= it.getString("titre","hhhhh")
            auteur = it.getString("auteur","b9iiiti fiya")
            type = it.getString("type")
            img = it.getString("img")
            id_livre = it.getInt("id_livre")
            page= it.getInt("Pages")
            desc= it.getString("Desc")
            Stock=it.getInt("Stock")


        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) :View?{

        // Inflate the layout for this fragment
      // val bindig:FragmentDetailBinding = FragmentDetailBinding.inflate(inflater,container,false);

       val view:View= inflater.inflate(R.layout.fragment_detail, container, false)
        Log.i("HLLO", "onCreateView: ${this.arguments?.getString("titre")}")
        val activity=view!!.context as AppCompatActivity
        myHelper = MyDbHelper(activity)
        view.findViewById<TextView>(R.id.title).text=title
        view.findViewById<TextView>(R.id.author).text=auteur
        view.findViewById<Button>(R.id.button).text=type
        view.findViewById<TextView>(R.id.titlevalue).text=title
        view.findViewById<TextView>(R.id.authorvalue).text=auteur
        view.findViewById<TextView>(R.id.nmbpage).text=page.toString()
        view.findViewById<TextView>(R.id.descContenu).text=desc
        //reserver button
        view.findViewById<Button>(R.id.Reserver).setOnClickListener(){
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            firebaseAuth= FirebaseAuth.getInstance()
           val ID= firebaseAuth.currentUser?.email
            DataManager.resrever(myHelper,id_livre,ID.toString(),currentDate.toString(),Stock)
          val bn = Bundle()
            bn.putString("mailUser",ID.toString())



            val act2= Intent(context,livresReserveActivity::class.java)
            act2.putExtras(bn)
            context?.startActivity(act2)
        }



        Glide.with(this)
            .asBitmap()
            .load(img)
            .into(view.findViewById(R.id.book_img_id));

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}