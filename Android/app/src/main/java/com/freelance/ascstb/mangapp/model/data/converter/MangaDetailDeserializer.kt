package com.freelance.ascstb.mangapp.model.data.converter

import android.util.Log
import com.freelance.ascstb.mangapp.model.entity.MangaDetail
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MangaDetailDeserializer : JsonDeserializer<MangaDetail> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): MangaDetail {
        Log.d(TAG, "deserialize: ${json.toString()}")

        return MangaDetail()
    }

    companion object {
        private val TAG = MangaDetailDeserializer::class.java.simpleName + "_TAG"
    }
}