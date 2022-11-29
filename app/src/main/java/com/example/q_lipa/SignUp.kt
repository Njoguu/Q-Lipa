package com.example.q_lipa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class SignUp : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

//        Hide Action Bar
        supportActionBar?.hide()

//        OnClickListener for "Already have an Account? Login!"
        val accexists = findViewById<TextView>(R.id.loginTextView)
        accexists.setOnClickListener{
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
            finish()
        }

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()


        val signupName = findViewById<EditText>(R.id.userName)
        val signupEmail = findViewById<EditText>(R.id.emailAddress)
        val signupPassword = findViewById<EditText>(R.id.password)
        val signupConfirmPassword = findViewById<EditText>(R.id.confirmPassword)
        val signUpBtn = findViewById<Button>(R.id.signUpBtn)
        val signUpProgressbar = findViewById<ProgressBar>(R.id.progressBar)

        signUpBtn.setOnClickListener {
            val name = signupName.text.toString()
            val email = signupEmail.text.toString()
            val password = signupPassword.text.toString()
            val conPassword = signupConfirmPassword.text.toString()

            signUpProgressbar.visibility = View.VISIBLE
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || conPassword.isEmpty()){
                if(name.isEmpty()){
                    signupName.error = "Please enter your name"
                }
                if(email.isEmpty()){
                    signupEmail.error = "Please enter your email address"
                }
                if(password.isEmpty()){
                    signupPassword.error = "Please enter a password"
                }
                if(conPassword.isEmpty()){
                    signupConfirmPassword.error = "Please re-enter your password"
                }
                Toast.makeText(this, "Please enter valid details", Toast.LENGTH_SHORT).show()
                signUpProgressbar.visibility=View.GONE
            }else if(!email.matches(emailPattern.toRegex())){
                signUpProgressbar.visibility=View.GONE
                signupEmail.error = "Enter a valid email address"
                Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show()
            }else if(password.length < 6){
                signUpProgressbar.visibility=View.GONE
                signupPassword.error = "Password is too short"
                Toast.makeText(this, "Password should have at least 6 characters", Toast.LENGTH_SHORT).show()
            }else if (password != conPassword){
                signUpProgressbar.visibility=View.GONE
                signupConfirmPassword.error = "Passwords do not match"
                Toast.makeText(this, "Confirm you have entered the correct password", Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if (it.isSuccessful){
                        val databaseRef = database.reference.child("users").child(auth.currentUser!!.uid)
                        val users : Users = Users(name,email,password, auth.currentUser!!.uid)

                        databaseRef.setValue(users).addOnCompleteListener{
                            if(it.isSuccessful){
                                Toast.makeText(this, "Account created succesfully", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, SignIn::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                                Toast.makeText(this, "Something went wrong, try again", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        Toast.makeText(this, "Something went wrong, try again", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}