package com.freelance.ascstb.mangapp.model.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.freelance.ascstb.mangapp.model.data.local.LocalDataSource
import com.freelance.ascstb.mangapp.model.data.remote.LatestCallback
import com.freelance.ascstb.mangapp.model.data.remote.RemoteDataSource
import com.freelance.ascstb.mangapp.model.entity.Manga

class MangaRepository(private var remoteDataSource: RemoteDataSource, private var localDataSource: LocalDataSource) {
    var data = MutableLiveData<List<Manga>>()

    fun getLatest(): LiveData<List<Manga>> {
        remoteDataSource.getLatest(object : LatestCallback {
            override fun onLatestResponse(result: List<Manga>) {
                data.value = result
            }

            override fun onRemoteFaliure(error: String) {
                Log.d(TAG, "onRemoteFaliure: ${error}")
            }
        })

        return data
    }

    companion object {
        private val TAG = MangaRepository::class.java.simpleName + "_TAG"
    }
}