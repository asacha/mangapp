package com.freelance.ascstb.mangapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.util.Log
import com.freelance.ascstb.mangapp.model.data.MangaRepository
import com.freelance.ascstb.mangapp.model.data.local.LocalDataSource
import com.freelance.ascstb.mangapp.model.data.remote.RemoteDataSource
import com.freelance.ascstb.mangapp.model.entity.Manga

class LatestViewModel(application: Application): AndroidViewModel(application) {
    private val mangaRepository: MangaRepository

    val mangaList: LiveData<List<Manga>>
    get() {
        //loadMangaList()
        return mangaRepository.getLatest()
    }

    init {
        mangaRepository = MangaRepository(RemoteDataSource(), LocalDataSource(application))
    }

    private fun loadMangaList() {
        Log.d(TAG, "loadMangaList: ")
        mangaRepository.getLatest()
    }

    companion object {
        private val TAG = LatestViewModel::class.java.simpleName + "_TAG"
    }
}