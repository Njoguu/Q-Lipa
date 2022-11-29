package com.example.q_lipa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var user : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Hide actionBar
        supportActionBar?.hide()

//        check if user is logged in
        user = FirebaseAuth.getInstance()


//        Splashscreen Logic
        val splashScreenIcon = findViewById<ImageView>(R.id.splashscreenIcon)
        splashScreenIcon.alpha = 0f
        splashScreenIcon.animate().setDuration(2000).alpha(1f).withEndAction{
            checkifLoggedIn()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

    private fun checkifLoggedIn() {
        if(user.currentUser != null){
            startActivity(Intent(this, Dashboard::class.java))
            finish()
        }else{
            startActivity(Intent(this, SignIn::class.java))
            finish()
        }
    }


}