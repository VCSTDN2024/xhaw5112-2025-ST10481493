package com.example.empowerwebapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var edtUsername: TextInputEditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var edtPhone: TextInputEditText
    private lateinit var btnSignin: Button
    private lateinit var btnGoogleSignin: Button
    private lateinit var btnFacebookSignin: Button
    private lateinit var btnLinkedinSignin: Button
    private lateinit var btnGuestSignin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views
        edtUsername = findViewById(R.id.edt_Username)
        edtPassword = findViewById(R.id.edt_Password)
        edtPhone = findViewById(R.id.edt_Phonenum)
        btnSignin = findViewById(R.id.btn_Signin)
        btnGoogleSignin = findViewById(R.id.btn_google_signin)
        btnFacebookSignin = findViewById(R.id.btn_facebook_signin)
        btnLinkedinSignin = findViewById(R.id.btn_linkedin_signin)
        btnGuestSignin = findViewById(R.id.btn_guest_signin)

        setupClickListeners()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupClickListeners() {
        btnSignin.setOnClickListener {
            validateAndProceed()
        }

        btnGoogleSignin.setOnClickListener {
            handleSocialLogin("Google")
        }

        btnFacebookSignin.setOnClickListener {
            handleSocialLogin("Facebook")
        }

        btnLinkedinSignin.setOnClickListener {
            handleSocialLogin("LinkedIn")
        }

        btnGuestSignin.setOnClickListener {
            handleGuestLogin()
        }
    }

    private fun handleSocialLogin(provider: String) {
        // Show loading message
        Toast.makeText(this, "Signing in with $provider...", Toast.LENGTH_SHORT).show()

        // Simulate social login success (in real app, implement actual OAuth)
        // For demo purposes, we'll proceed to main screen after a short delay
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, "Welcome! Signed in with $provider", Toast.LENGTH_SHORT).show()
            proceedToMainScreen("$provider User")
        }, 1500)
    }

    private fun handleGuestLogin() {
        Toast.makeText(this, "Continuing as Guest", Toast.LENGTH_SHORT).show()
        proceedToMainScreen("Guest")
    }

    private fun validateAndProceed() {
        val username = edtUsername.text.toString().trim()
        val password = edtPassword.text.toString().trim()
        val phone = edtPhone.text.toString().trim()

        // Username: longer than 3 chars, only letters
        val usernameRegex = "^[A-Za-z]{3,}$".toRegex()

        // Password: at least 8 characters
        val passwordValid = password.length >= 8

        // Phone: starts with 0 and exactly 10 digits
        val phoneRegex = "^0\\d{9}$".toRegex()

        when {
            username.isEmpty() || password.isEmpty() || phone.isEmpty() -> {
                showError("All fields are required")
            }
            !username.matches(usernameRegex) -> {
                showError("Username must be at least 4 letters and contain only letters")
            }
            !passwordValid -> {
                showError("Password must be at least 8 characters")
            }
            !phone.matches(phoneRegex) -> {
                showError("Phone number must start with 0 and be 10 digits")
            }
            else -> {
                // All good, navigate to next screen
                proceedToMainScreen(username)
            }
        }
    }

    private fun proceedToMainScreen(username: String) {
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
        finish()
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}