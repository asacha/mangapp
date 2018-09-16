package com.freelance.ascstb.mangapp.model.data.converter

import android.util.Log
import com.freelance.ascstb.mangapp.model.entity.Manga
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class MyConverterFactory : Converter.Factory() {
    companion object {
        private val TAG = MyConverterFactory::class.java.simpleName + "_TAG"
    }

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        Log.d(TAG, "responseBodyConverter: ")
        return super.responseBodyConverter(type, annotations, retrofit)
    }
}
