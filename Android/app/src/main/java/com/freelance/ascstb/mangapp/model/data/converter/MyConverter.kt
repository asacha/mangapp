package com.freelance.ascstb.mangapp.model.data.converter

import android.util.Log
import com.freelance.ascstb.mangapp.model.entity.Manga
import okhttp3.ResponseBody
import org.json.JSONObject
import org.jsoup.Jsoup
import retrofit2.Converter
import java.nio.charset.Charset

class MyConverter : Converter<ResponseBody, List<Manga>> {
    override fun convert(responseBody: ResponseBody): List<Manga> {
        return fromLatestResult(fromResponseBody(responseBody))
    }
    //Create a new Gson Converter
    //Extend converter factory class as singleton

    fun fromLatestResult(latestResult: ByteArray): List<Manga> {
        Log.d(TAG, "fromLatestResult: ")
        val result = ArrayList<Manga>()

        val strHtml = String(latestResult, Charset.forName("UTF-8"))
        val document = Jsoup.parse(strHtml)

        val ul = document.body().getElementsByClass("post-list")[0]
        val series = ul.getElementsByTag("li")

        for (serie in series) {
            val manga = Manga()
            manga.title = serie.getElementsByClass("title")[0].text()
            manga.coverUrl = serie.getElementsByTag("img")[0].attr("src")
            manga.titleUrl = serie.getElementsByClass("manga-cover")[0].attr("href")

            val detail = serie.getElementsByClass("post-info")[0].getElementsByTag("p")
            manga.genre = detail[1].text()
            manga.authors = detail[2].text()
            manga.updated = detail[4].text()

            manga.latestChapterUrl = serie.getElementsByClass("read-btn")[0].attr("href")
            manga.latestChapter = serie.getElementsByClass("read-btn")[0].text()

            if (manga.coverUrl.trim { it <= ' ' } != "") {
                result.add(manga)
            }
        }

        return result
    }

    fun fromResponseBody(responseBody: ResponseBody): ByteArray {
//        return response!!.body()!!.source().readByteArray()
        return responseBody.source().readByteArray()
    }

    companion object {
        private val TAG = MyConverter::class.java.simpleName + "_TAG"
    }
}