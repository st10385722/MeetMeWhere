package com.example.meetmewherejerry

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meetmewherejerry.databinding.ActivityRegisterBinding
import org.w3c.dom.Text


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private var uvm : UsersViewModel = UsersViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener {
            if(binding.usernameEt.text.toString() == "" || binding.passwordEt.text.toString() == "" || binding.confirmPasswordEt.text.toString() == ""){
                Toast.makeText(this, "One or more fields are not filled in", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(binding.passwordEt.text.toString() != binding.confirmPasswordEt.text.toString()){
                Toast.makeText(this, "The passwords do not match!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val isNewValid = uvm.getSingleUser(binding.usernameEt.text.toString())
            if(isNewValid != null){
                Toast.makeText(this, "This username is already in use!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            uvm.insertUser(binding.usernameEt.text.toString(), binding.passwordEt.text.toString())
            loginScreen()
        }

        binding.loginBtn.setOnClickListener {
            loginScreen()
        }
    }

    private fun loginScreen(){
        val i = Intent(applicationContext, LoginActivity:: class.java)
        startActivity(i)
    }
}

//binding.usernameEt.addTextChangedListener(object : TextWatcher {
//    override fun afterTextChanged(s: Editable) {}
//
//    override fun beforeTextChanged(s: CharSequence, start: Int,count: Int, after: Int){}
//    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
//})