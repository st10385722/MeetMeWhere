package com.example.meetmewherejerry

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meetmewherejerry.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private var uvm : UsersViewModel = UsersViewModel()
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

        binding.loginBtn.setOnClickListener {
            if(binding.usernameEt.text.toString() == "" || binding.passwordEt.text.toString() == ""){
                Toast.makeText(this, "One or more fields are not filled in", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val loginUser = uvm.isValidUser(binding.usernameEt.text.toString(), binding.passwordEt.text.toString())
            if(loginUser != null){
                eventHubScreen()
            }
        }

        binding.registerBtn.setOnClickListener {
            registerScreen()
        }
    }
    private fun eventHubScreen(){
        val i = Intent(applicationContext, EventHubActivity:: class.java)
        i.putExtra("Username", binding.usernameEt.text.toString())
        startActivity(i)
    }
    private fun registerScreen(){
        val i = Intent(applicationContext, RegisterActivity:: class.java)
        startActivity(i)
    }

}