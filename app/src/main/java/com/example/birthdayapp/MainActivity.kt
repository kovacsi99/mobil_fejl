package com.example.birthdayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.example.birthdayapp.date.DatePickerFragment
import com.example.birthdayapp.service.IpService
import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.JsonPath
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://httpbin.org/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()


        val service: IpService = retrofit.create(IpService::class.java)

        service.getIp().enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                response.body()?.let {
                    val json = it.string()
                    val query = ".origin"
                    val config: Configuration = Configuration.builder().build()

                    val ip = JsonPath.parse(json, config).read(query, List::class.java)

                    if (ip == null) {
                        Toast.makeText(applicationContext, "You've broken the system", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(applicationContext, "Your IP is ${ip.get(0).toString()}", Toast.LENGTH_LONG).show()
                    }

                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(applicationContext, "Dead Inside", Toast.LENGTH_SHORT).show()
            }
        })
    }
}