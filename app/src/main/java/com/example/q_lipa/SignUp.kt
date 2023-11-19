package com.example.q_lipa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

//        Hide Action Bar
        supportActionBar?.hide()

//        OnClickListener for "Already have an Account? Login!"
        val accexists = findViewById<TextView>(R.id.loginTextView)
        accexists.setOnClickListener {
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
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || conPassword.isEmpty()) {
                if (name.isEmpty()) {
                    signupName.error = getString(R.string.please_enter_your_name)
                }
                if (email.isEmpty()) {
                    signupEmail.error = getString(R.string.please_enter_your_email_address)
                }
                if (password.isEmpty()) {
                    signupPassword.error = getString(R.string.please_enter_a_password)
                }
                if (conPassword.isEmpty()) {
                    signupConfirmPassword.error = getString(R.string.please_re_enter_your_password)
                }
                Toast.makeText(
                    this,
                    getString(R.string.please_enter_valid_details), Toast.LENGTH_SHORT
                ).show()
                signUpProgressbar.visibility = View.GONE
            } else if (!email.matches(emailPattern.toRegex())) {
                signUpProgressbar.visibility = View.GONE
                signupEmail.error = getString(R.string.enter_a_valid_email_address)
                Toast.makeText(this, R.string.enter_a_valid_email_address, Toast.LENGTH_SHORT)
                    .show()
            } else if (password.length < 6) {
                signUpProgressbar.visibility = View.GONE
                signupPassword.error = getString(R.string.password_is_too_short)
                Toast.makeText(
                    this,
                    getString(R.string.password_should_have_at_least_6_characters),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (password != conPassword) {
                signUpProgressbar.visibility = View.GONE
                signupConfirmPassword.error = getString(R.string.passwords_do_not_match)
                Toast.makeText(
                    this,
                    getString(R.string.confirm_you_have_entered_the_correct_password),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val databaseRef =
                            database.reference.child("users").child(auth.currentUser!!.uid)
                        val users = Users(name, email, password, auth.currentUser!!.uid)

                        databaseRef.setValue(users).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    getString(R.string.account_created_successfully),
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this, SignIn::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    getString(R.string.something_went_wrong_try_again),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(
                            this,
                            R.string.something_went_wrong_try_again,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
    }
}