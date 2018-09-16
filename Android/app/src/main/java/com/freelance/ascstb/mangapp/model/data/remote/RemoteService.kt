package com.freelance.ascstb.mangapp.model.data.remote

import com.freelance.ascstb.mangapp.model.entity.Manga
import com.freelance.ascstb.mangapp.model.entity.MangaDetail
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface RemoteService {
    @GET("latest")
    fun getLatest(): Call<ResponseBody>

    @GET("latest/{page}.html")
    fun getLatestPage(@Path("page") page: Int): Call<ResponseBody>

    @GET("latest/{page}.html")
    fun getLatestPageConverted(@Path("page") page: Int): Observable<List<Manga>>

    @GET("manga/{mangaName}")
    fun getMangaDetail(@Path("mangaName") mangaName: String): Observable<MangaDetail>
}