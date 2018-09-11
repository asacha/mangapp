package com.freelance.ascstb.mangapp.utils

import android.util.Log
import com.freelance.ascstb.mangapp.model.entity.Manga
import org.jsoup.Jsoup
import java.nio.charset.Charset

class MyConverter {
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

            if (manga.coverUrl.trim{it <= ' '} != "") {
                result.add(manga)
            }
        }

        return result
    }

    companion object {
        private val TAG = MyConverter::class.java.simpleName + "_TAG"
    }
}