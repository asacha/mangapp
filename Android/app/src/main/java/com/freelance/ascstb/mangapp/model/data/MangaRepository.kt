package com.freelance.ascstb.mangapp.model.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.freelance.ascstb.mangapp.model.data.local.LocalDataSource
import com.freelance.ascstb.mangapp.model.data.remote.DetailCallback
import com.freelance.ascstb.mangapp.model.data.remote.LatestCallback
import com.freelance.ascstb.mangapp.model.data.remote.RemoteDataSource
import com.freelance.ascstb.mangapp.model.entity.Manga
import com.freelance.ascstb.mangapp.model.entity.MangaDetail

class MangaRepository(private var remoteDataSource: RemoteDataSource, private var localDataSource: LocalDataSource) {
    var mangaData = MutableLiveData<List<Manga>>()
    var detailData = MutableLiveData<MangaDetail>()

    fun getLatest(page: Int): LiveData<List<Manga>> {
        remoteDataSource.getLatest(page, object : LatestCallback {
            override fun onLatestResponse(result: List<Manga>) {
                Log.d(TAG, "onLatestResponse: page: $page. resultSize: ${result.size}")
                mangaData.value = result
            }

            override fun onRemoteFailure(error: String) {
                Log.d(TAG, "onRemoteFailure: $error")
            }
        })

        /*remoteDataSource.getLatestConverted(page, object : LatestCallback {
            override fun onLatestResponse(result: List<Manga>) {
                Log.d(TAG, "onLatestResponse: page: $page. resultSize: ${result.size}")
                mangaData.value = result
            }

            override fun onRemoteFailure(error: String) {
                Log.d(TAG, "onRemoteFailure: $error")
            }
        })*/

        return mangaData
    }

    fun getDetail(mangaName: String): LiveData<MangaDetail> {
        Log.d(TAG, "getDetail: mangaName: $mangaName")
        remoteDataSource.getMangaDetail(mangaName, object: DetailCallback {
            override fun onDetailResponse(result: MangaDetail) {
                Log.d(TAG, "onDetailResponse: ")
                detailData.value = result
            }

            override fun onRemoteFailure(error: String) {
                Log.d(TAG, "onRemoteFailure: $error")
            }
        })

        return detailData
    }

    companion object {
        private val TAG = MangaRepository::class.java.simpleName + "_TAG"
    }
}