package com.freelance.ascstb.mangapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.util.Log
import com.freelance.ascstb.mangapp.model.data.MangaRepository
import com.freelance.ascstb.mangapp.model.data.local.LocalDataSource
import com.freelance.ascstb.mangapp.model.data.remote.RemoteDataSource
import com.freelance.ascstb.mangapp.model.entity.Manga

class LatestViewModel(application: Application) : AndroidViewModel(application) {
    private val mangaRepository: MangaRepository = MangaRepository(RemoteDataSource(), LocalDataSource(application))
    private var page: Int = 0

    val mangaList: LiveData<List<Manga>>
        get() {
            //loadMangaList()
            return mangaRepository.getLatest(page)
        }

    private fun loadMangaList() {
        Log.d(TAG, "loadMangaList: ")
        mangaRepository.getLatest(page)
    }

    fun updatePage() {
        this.page = page + 1
        Log.d(TAG, "updatePage: " + page.toString())
    }

    companion object {
        private val TAG = LatestViewModel::class.java.simpleName + "_TAG"
    }
}