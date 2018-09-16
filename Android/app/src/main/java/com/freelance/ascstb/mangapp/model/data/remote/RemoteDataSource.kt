package com.freelance.ascstb.mangapp.model.data.remote

import android.util.Log
import com.freelance.ascstb.mangapp.model.data.converter.MyConverter
import com.freelance.ascstb.mangapp.model.data.converter.MyCustomDeserializer
import com.freelance.ascstb.mangapp.model.entity.Manga
import com.freelance.ascstb.mangapp.model.entity.MangaDetail
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class RemoteDataSource {
    private fun createInstance(): Retrofit {
        Log.d(TAG, "createInstance:")
        val result: Retrofit

        /*val gson = GsonBuilder()
                .registerTypeAdapter(MangaDetail::class.java, MangaDetailDeserializer())
                .setLenient()
                .create()*/

        result = Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(MyCustomDeserializer()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return result
    }

    //region getLatest
    private fun getLatestFromNetwork(page: Int): Call<ResponseBody> {
        Log.d(TAG, "getLatestFromNetwork: ")
        return createInstance().create(RemoteService::class.java).getLatestPage(page)
    }

    private fun getLatestConvertedFromNetwork(page: Int): Observable<List<Manga>> {
        Log.d(TAG, "getLatestFromNetwork: ")
        return createInstance().create(RemoteService::class.java).getLatestPageConverted(page)
    }

    fun getLatest(page: Int, callback: LatestCallback) {
        Log.d(TAG, "getLatest: page: $page")
        getLatestFromNetwork(page).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                Log.d(TAG, "onResponse: ")
                val latestResult: ByteArray = response!!.body()!!.source().readByteArray()

                val result = MyConverter().fromLatestResult(latestResult)
                callback.onLatestResponse(result)
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.d(TAG, "onFailure: " + t!!.message.toString())
                callback.onRemoteFailure(t.message.toString())
            }
        })
    }

    fun getLatestConverted(page: Int, callback: LatestCallback) {
        Log.d(TAG, "getLatestConverted")
        getLatestConvertedFromNetwork(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Manga>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(result: List<Manga>) {
                        Log.d(TAG, "onNext: ${result.size}")
                        callback.onLatestResponse(result)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: ${e.message}")
                    }
                })
    }
    //endregion

    //region MangaDetail
    private fun getDetailFromNetwork(mangaName: String): Observable<MangaDetail> {
        Log.d(TAG, "getDetailFromNetwork: ")
        return createInstance().create(RemoteService::class.java).getMangaDetail(mangaName)
    }

    fun getMangaDetail(mangaUrl: String, callback: DetailCallback) {
        Log.d(TAG, "getMangaDetail: $mangaUrl")
        getDetailFromNetwork(mangaUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MangaDetail> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(result: MangaDetail) {
                        Log.d(TAG, "onNext: ")
                        callback.onDetailResponse(result)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: ${e.stackTrace}")
                        callback.onRemoteFailure(e.message!!)
                    }
                })
    }

    //endregion

    companion object {
        private const val BASE_URL = "http://m.mangatown.com/"
        private val TAG = RemoteDataSource::class.java.simpleName + "_TAG"
    }
}