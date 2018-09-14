package com.freelance.ascstb.mangapp.model.data.remote

import android.util.Log
import com.freelance.ascstb.mangapp.model.data.converter.MyConverterFactory
import com.freelance.ascstb.mangapp.model.data.converter.MyConverter
import com.freelance.ascstb.mangapp.model.entity.Manga
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class RemoteDataSource {
    private fun createInstance(): Retrofit {
        Log.d(TAG, "createInstance: ")
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MyConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun getLatestFromNetwork(page: Int): Call<ResponseBody> {
        Log.d(TAG, "getLatestFromNetwork: ")
        return createInstance().create(RemoteService::class.java).getLatestPage(page)
    }

    private fun getLatestConvertedFromNetwork(page: Int): List<Manga> {
        Log.d(TAG, "getLatestFromNetwork: ")
        return createInstance().create(RemoteService::class.java).getLatestPageConverted(page)
    }

    fun getLatest(page: Int, callback: LatestCallback) {
        Log.d(TAG, "getLatest: ")
        getLatestFromNetwork(page).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                Log.d(TAG, "onResponse: ")
                val latestResult: ByteArray = response!!.body()!!.source().readByteArray()

                val result = MyConverter().fromLatestResult(latestResult)
                callback.onLatestResponse(result)
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.d(TAG, "onFailure: " + t!!.message.toString())
                callback.onRemoteFaliure(t.message.toString())
            }
        })
    }

    fun getLatestConverted(page: Int, callback: LatestCallback) {
        Log.d(TAG, "getLatestConverted")

    }

    companion object {
        private const val BASE_URL = "http://m.mangatown.com/"
        private val TAG = RemoteDataSource::class.java.simpleName + "_TAG"
    }
}