package com.freelance.ascstb.mangapp.model.data.converter

import android.util.Log
import com.freelance.ascstb.mangapp.model.entity.Chapter
import com.freelance.ascstb.mangapp.model.entity.Manga
import com.freelance.ascstb.mangapp.model.entity.MangaDetail
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Converter
import java.io.InputStream
import java.nio.charset.Charset

class MyConverter : Converter<ResponseBody, List<Manga>> {
    override fun convert(responseBody: ResponseBody): List<Manga> {
        Log.d(TAG, "convert: ")
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
        Log.d(TAG, "fromResponseBody: ")
//        return response!!.body()!!.source().readByteArray()
        return responseBody.source().readByteArray()
    }

    fun fromDetailResult(source: InputStream?): MangaDetail {
        Log.d(TAG, "fromDetailResult: ")

        val result = MangaDetail()
        val strHtml = source!!.bufferedReader().use { it.readText() }
        val document = Jsoup.parse(strHtml)

        result.title = document.getElementsByClass("title")[0].text()

        val detail = document.getElementsByClass("detail-info")[0].getElementsByTag("p")
        result.authors = detail[1].getElementsByTag("a")[0].text()
        result.artists = detail[2].getElementsByTag("a")[0].text()
        result.status = detail[3].text()


        val detailMiddle = document.getElementsByClass("detail-info-middle")[0].getElementsByTag("p")
        result.alternativeName = detailMiddle[0].text()
        result.summary = detailMiddle[3].getElementById("hide").text()

        val chaptersList = document.getElementsByClass("detail-ch-list")[0].getElementsByTag("li")

        for (chapterLi in chaptersList) {
            val chapter = Chapter()

            chapter.chapterUrl = chapterLi.getElementsByTag("a")[0].attr("href")
            chapter.volumne = chapterLi.getElementsByClass("vol")[0].text()
            chapter.updateDate = chapterLi.getElementsByClass("time")[0].text()

            val content = chapterLi.getElementsByTag("a")[0].html()
            val startIndex = content.indexOf("<")
            chapter.name = content.substring(0, startIndex)

            //Log.d(TAG, "fromDetailResult: chapter: name: ${chapter.name}. vol: ${chapter.volumne}. update: ${chapter.updateDate}. url: ${chapter.chapterUrl}")

            result.chapters.add(chapter)
        }

        return result
    }

    companion object {
        private val TAG = MyConverter::class.java.simpleName + "_TAG"
    }
}