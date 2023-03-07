package com.example.q_lipa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.q_lipa.databinding.ActivityPaymentBinding

class Payment : AppCompatActivity() {


    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Hide ActionBar
        supportActionBar?.hide()

        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Initialize Daraja
//        daraja = getDaraja()
//        accessToken()

//        Implement Payment
    }

//    private fun getDaraja(): Daraja {
////        return Daraja.builder(, "")
////            .setBusinessShortCode("")
////            .setPassKey("")
////            .setTransactionType()
////            .setCallbackUrl("")
////            .setEnvironment(Environment.SANDBOX)
////            .build()
//    }

    private fun accessToken() {
        TODO("Not yet implemented")
    }
}