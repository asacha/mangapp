package com.freelance.ascstb.mangapp.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.freelance.ascstb.mangapp.R
import com.freelance.ascstb.mangapp.adapter.RVChapterAdapter
import com.freelance.ascstb.mangapp.model.entity.Chapter
import com.freelance.ascstb.mangapp.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var adapter: RVChapterAdapter
    private lateinit var tvDetailTitle: TextView
    private lateinit var tvDetailAuthors: TextView
    private lateinit var tvDetailArtists: TextView
    private lateinit var tvDetailStatus: TextView
    private lateinit var tvDetailAlternativeName: TextView
    private lateinit var tvDetailSummary: TextView
    private lateinit var ivDetailCover: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.d(TAG, "onCreate: ")

        initRecyclerView()
        getControls()
        setViewModel()
    }

    private fun setViewModel() {
        val mangaName = checkMangaName(intent.getStringExtra("TitleUrl"))
        Log.d(TAG, "setViewModel: mangaName: $mangaName")

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.setMangaName(mangaName)
        viewModel.detail.observe(this, Observer { mangaDetail ->
            Log.d(TAG, "mangaDetail.observe ")
            tvDetailTitle.text = mangaDetail!!.title
            tvDetailAuthors.text = mangaDetail.authors
            tvDetailArtists.text = mangaDetail.artists
            tvDetailStatus.text = mangaDetail.status
            tvDetailAlternativeName.text = mangaDetail.alternativeName
            tvDetailSummary.text = mangaDetail.summary
            adapter.updateChapterList(mangaDetail.chapters)
        })
    }

    private fun getControls() {
        tvDetailTitle = findViewById(R.id.tvDetailTitle)
        tvDetailAuthors = findViewById(R.id.tvDetailAuthors)
        tvDetailArtists = findViewById(R.id.tvDetailArtists)
        tvDetailStatus = findViewById(R.id.tvDetailStatus)
        tvDetailAlternativeName = findViewById(R.id.tvDetailAlternativeName)
        tvDetailSummary = findViewById(R.id.tvDetailSummary)
        ivDetailCover = findViewById(R.id.ivDetailCover)

        val coverUrl = intent.getStringExtra("coverUrl")
        Glide.with(this).load(coverUrl).into(ivDetailCover)
    }

    private fun checkMangaName(mangaName: String): String {
        return mangaName.replace("http://m.mangatown.com/manga/", "")
    }

    private fun initRecyclerView() {
        Log.d(TAG, "initRecyclerView: ")
        val rvChapterList = findViewById<RecyclerView>(R.id.rvChapters)
        val layoutManager = LinearLayoutManager(applicationContext)
        this.adapter = RVChapterAdapter(ArrayList<Chapter>()) { chapter: Chapter ->
            chapterClicked(chapter)
        }
        rvChapterList.layoutManager = layoutManager
        rvChapterList.adapter = adapter
    }

    private fun chapterClicked(chapter: Chapter) {
        Log.d(TAG, "chapterClicked: ${chapter.name}")
    }

    fun onStartReading(view: View) {
        Log.d(TAG, "onStartReading: ")
    }

    companion object {
        private val TAG = DetailActivity::class.java.simpleName + "_TAG"
    }
}
