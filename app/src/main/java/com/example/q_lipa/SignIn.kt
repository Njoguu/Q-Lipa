package com.example.q_lipa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.q_lipa.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_in)

//        Hide Action Bar
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

//        OnClickListener for "Already have an Account? Login!"
        val accdonexists = findViewById<TextView>(R.id.noAcc)
        accdonexists.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }

//        TODO --> OncCLickListener for "Forgot Password"
//        val forgotPass = findViewById<TextView>(R.id.forgotPassword)
//        forgotPass.setOnClickListener{
//            auth.currentUser?.email?.let { it1 -> auth.sendPasswordResetEmail(it1) }
//        }


        val signInEmail = findViewById<EditText>(R.id.emailSignIn)
        val signInPassword = findViewById<EditText>(R.id.passwordSignIn)
        val loginBtn = findViewById<Button>(R.id.loginBtn)



        loginBtn.setOnClickListener {
            val email = signInEmail.text.toString()
            val password = signInPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                if(email.isEmpty()){
                    signInEmail.error = "Please enter your email address"
                }
                if(password.isEmpty()){
                    signInPassword.error = "Please enter a password"
                }
//                TODO -- > Add Progressbar

                Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()
            }else if(!email.matches(emailPattern.toRegex())){
//                signUpProgressbar.visibility= View.GONE
                signInEmail.error = "Enter a valid email address"
                Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show()
            }else if(password.length < 6){
//                signUpProgressbar.visibility=View.GONE
                signInPassword.error = "Password is too short"
                Toast.makeText(this, "Password should have at least 6 characters", Toast.LENGTH_SHORT).show()
            }else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Dashboard::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, "Something went wrong! Cannot Log In at the time", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}