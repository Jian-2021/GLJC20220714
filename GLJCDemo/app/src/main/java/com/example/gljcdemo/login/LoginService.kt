package com.example.gljcdemo.login

import retrofit2.Call
import retrofit2.http.GET

interface LoginService {

    @GET("http://1.117.154.150:2222/")
    fun getAppData(): Call<List<LoginApp>>

}