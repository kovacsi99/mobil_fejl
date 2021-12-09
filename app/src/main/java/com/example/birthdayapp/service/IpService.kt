package com.example.birthdayapp.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface IpService {
    @GET("/ip")
    fun getIp(): Call<ResponseBody>
}