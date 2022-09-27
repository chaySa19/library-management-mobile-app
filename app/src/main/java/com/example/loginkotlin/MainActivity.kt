package com.example.loginkotlin

import android.app.ProgressDialog
import android.content.Intent
import androidx.core.util.PatternsCompat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import com.example.loginkotlin.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    private  lateinit var actionBar: ActionBar
    private  lateinit var progressDialog:ProgressDialog
    private  lateinit var firebaseAuth: FirebaseAuth
private var email=""
    private var password=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar = supportActionBar!!
        actionBar.title="Login"
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("please wait")
        progressDialog.setMessage("Login in...")
        progressDialog.setCanceledOnTouchOutside(false)
        firebaseAuth= FirebaseAuth.getInstance()

        binding.textView.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        binding.button.setOnClickListener {
            validateData()
        }

    }

    private fun validateData() {
        email=binding.editTextTextPersonName.text.toString().trim()
        password=binding.editTextTextPassword.text.toString().trim()
         if(TextUtils.isEmpty(password)){
            binding.editTextTextPassword.error="Please enter password"
        }
        else{
            firebaseLogin()
        }

    }

    private fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser=firebaseAuth.currentUser
                val email= firebaseUser!!.email
                Toast.makeText(this,"LoggedIn as $email",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,pageone::class.java))
                finish()
            }
            .addOnFailureListener{
                e->
                progressDialog.dismiss()
                Toast.makeText(this,"Login failed due to ${e.message}",Toast.LENGTH_SHORT).show()

            }
    }

    private fun checkuser() {
        val firebaseUser=firebaseAuth.currentUser


        if (firebaseUser !=null){
            startActivity(Intent(this,pageone::class.java))
            finish()
        }
    }

}