package com.freelance.ascstb.mangapp.model.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {
    @GET("latest")
    fun getLatest(): Call<ResponseBody>

    @GET("latest/{page}.html")
    fun getLatestPage(@Path("page") page: Int): Call<ResponseBody>
}