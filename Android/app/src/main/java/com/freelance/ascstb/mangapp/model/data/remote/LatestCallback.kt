package com.freelance.ascstb.mangapp.model.data.remote

import com.freelance.ascstb.mangapp.model.entity.Manga

interface LatestCallback {
    fun onLatestResponse(result: List<Manga>)
    fun onRemoteFaliure(error: String)
}
