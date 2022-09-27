package com.example.loginkotlin

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.loginkotlin.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private  lateinit var binding: ActivitySignUpBinding
    private  lateinit var actionBar: ActionBar
    private  lateinit var progressDialog: ProgressDialog
    private  lateinit var firebaseAuth: FirebaseAuth
    private var email=""
    private var password=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar =supportActionBar!!
        actionBar.title="Sign up"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("please wait")
        progressDialog.setMessage("create account in")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth= FirebaseAuth.getInstance()

        binding.button.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
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
            firebaseSignup()
        }

    }

    private fun firebaseSignup() {
        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser=firebaseAuth.currentUser
                val email= firebaseUser!!.email
                Toast.makeText(this,"account created with email $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            .addOnFailureListener{
                    e->
                progressDialog.dismiss()
                Toast.makeText(this,"sign up failed due to ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}