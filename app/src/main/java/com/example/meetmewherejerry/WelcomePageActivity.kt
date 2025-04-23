package com.example.meetmewherejerry

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meetmewherejerry.databinding.ActivityWelcomePageBinding

class WelcomePageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWelcomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityWelcomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val i = Intent(applicationContext, RegisterActivity:: class.java)
            startActivity(i)
        }
        binding.btnLogin.setOnClickListener {
            val i = Intent(applicationContext, LoginActivity:: class.java)
            startActivity(i)
        }
    }
}