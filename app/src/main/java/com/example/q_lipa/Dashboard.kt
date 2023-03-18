package com.example.q_lipa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jikapi.up.railway.app/"

class Dashboard : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard2)

        supportActionBar?.show()
        auth = FirebaseAuth.getInstance()

//        TODO --> Fetch Data From Database to display Car Details
//        TEST
        getMyData()

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

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<AvailableJob>?> {
            override fun onResponse(
                call: Call<List<AvailableJob>?>,
                response: Response<List<AvailableJob>?>
            ) {
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                for (myData in responseBody){
                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
                }

                findViewById<TextView>(R.id.txtId).text = myStringBuilder

            }

            override fun onFailure(call: Call<List<AvailableJob>?>, t: Throwable) {
                Log.d("Dashboard","onFailure:"+t.message)
            }
        })
    }
}