package com.freelance.ascstb.mangapp.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.freelance.ascstb.mangapp.R
import com.freelance.ascstb.mangapp.model.entity.Manga

class RVMangaAdapter(var context: Context, private var mangaList: MutableList<Manga>) : RecyclerView.Adapter<RVMangaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        this.context = parent.context
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.manga_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mangaList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val manga = mangaList.get(position)

        Glide.with(context).load(manga.coverUrl).into(holder.ivCover)

        holder.tvTitle.text = manga.title
        holder.tvChapter.text = manga.latestChapter
    }

    fun updateMangaList(pMangaList: List<Manga>) {
        Log.d(TAG, "updateMangaList: this.MangaList.Size: ${this.mangaList.size} + pMangaList.Size: ${pMangaList.size}")

        //this.mangaList.clear()
        this.mangaList.addAll(pMangaList)

        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val ivCover: ImageView = itemView.findViewById(R.id.ivCover)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvChapter: TextView = itemView.findViewById(R.id.tvChapter)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d(TAG, "onClick: ")
            Toast.makeText(context, "", Toast.LENGTH_SHORT)
        }
    }

    companion object {
        private val TAG = RVMangaAdapter::class.java.simpleName + "_TAG"
    }

}