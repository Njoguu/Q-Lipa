package com.example.q_lipa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Dashboard : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard2)

        supportActionBar?.show()
        auth = FirebaseAuth.getInstance()

//        TODO --> Fetch Data From Database to display Car Details

//        TODO --> Add nav to SCANQR
        findViewById<ImageView>(R.id.lopha).setOnClickListener{
            startActivity(Intent(this, ScanQR::class.java))
        }

//        Logging Out
        findViewById<TextView>(R.id.log_out).setOnClickListener{
            auth.signOut()
            Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SignIn::class.java))
            finish()
        }
    }
}