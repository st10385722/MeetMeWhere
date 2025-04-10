package com.example.meetmewherejerry.ActivityAccess

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meetmewherejerry.LoginActivity
import com.example.meetmewherejerry.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

abstract class AuthActivityAccess : AppCompatActivity() {
    protected lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        if (!isUserAuthenticated()) {
            redirectToLogin()
            return
        }

        // Only proceed if user is authenticated
        onAuthenticatedCreate(savedInstanceState)
    }

    protected open fun onAuthenticatedCreate(savedInstanceState: Bundle?) {
        // To be implemented by child activities
    }

    private fun isUserAuthenticated(): Boolean {
        return auth.currentUser != null
    }

    private fun redirectToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}