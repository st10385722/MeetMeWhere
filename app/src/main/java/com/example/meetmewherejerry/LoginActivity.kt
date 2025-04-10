package com.example.meetmewherejerry

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.meetmewherejerry.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    //private var uvm : UsersViewModel = UsersViewModel()
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.loginBtn.setOnClickListener {
            loginUser()
        }

        binding.registerBtn.setOnClickListener {
            registerScreen()
        }
    }
    private fun loginUser(){
        if(binding.emailEt.text.toString() == "" || binding.passwordEt.text.toString() == ""){
            Toast.makeText(this, "One or more fields are not filled in", Toast.LENGTH_LONG).show()
            return
        }
        auth.signInWithEmailAndPassword(binding.emailEt.text.toString(), binding.passwordEt.text.toString())
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Login successful. Welcome to MeetMeWhere", Toast.LENGTH_SHORT).show()
                    eventHubScreen()
                }
                else {
                    Toast.makeText(this, "Login failed ", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun eventHubScreen(){
        val i = Intent(applicationContext, EventHubActivity:: class.java)
        i.putExtra("Username", binding.emailEt.text.toString())
        startActivity(i)
    }
    private fun registerScreen(){
        val i = Intent(applicationContext, RegisterActivity:: class.java)
        startActivity(i)
    }

}