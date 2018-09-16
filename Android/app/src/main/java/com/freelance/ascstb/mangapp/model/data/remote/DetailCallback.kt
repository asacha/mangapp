package com.freelance.ascstb.mangapp.model.data.remote

import com.freelance.ascstb.mangapp.model.entity.MangaDetail

interface DetailCallback {
    fun onDetailResponse(result: MangaDetail)
    fun onRemoteFailure(error: String)
}