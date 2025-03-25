package com.example.firebase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity()
{
    lateinit var loginEmail : EditText
    lateinit var loginPassword : EditText
    lateinit var loginBtn : Button
    lateinit var redirectSignup : TextView
    lateinit var firebaseAuthentication : FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginEmail = findViewById(R.id.loginEmail)
        loginPassword = findViewById(R.id.loginPassword)
        loginBtn = findViewById(R.id.loginBtn)
        redirectSignup = findViewById(R.id.signupRedirectTxtview)
        firebaseAuthentication = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {

            val email = loginEmail.text.toString().trim()
            val pass = loginPassword.text.toString().trim()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuthentication.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val user = firebaseAuthentication.currentUser
                    Toast.makeText(this, "Welcome ${user?.email}!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,WelcomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Login Failed: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

        }

        redirectSignup.setOnClickListener {

            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }
}