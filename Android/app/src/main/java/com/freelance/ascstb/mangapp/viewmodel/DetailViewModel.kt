package com.freelance.ascstb.mangapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.util.Log
import com.freelance.ascstb.mangapp.model.data.MangaRepository
import com.freelance.ascstb.mangapp.model.data.local.LocalDataSource
import com.freelance.ascstb.mangapp.model.data.remote.RemoteDataSource
import com.freelance.ascstb.mangapp.model.entity.MangaDetail

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val mangaRepository = MangaRepository(RemoteDataSource(), LocalDataSource(application))
    private var mangaName = ""

    val detail: LiveData<MangaDetail>
        get() {
            Log.d(TAG, "get view model detail: mangaName: $mangaName")
            return mangaRepository.getDetail(mangaName)
        }

    fun setMangaName(mangaName: String) {
        Log.d(TAG, "setMangaName: $mangaName")
        this.mangaName = mangaName
    }

    companion object {
        private val TAG = DetailViewModel::class.java.simpleName + "_TAG"
    }
}