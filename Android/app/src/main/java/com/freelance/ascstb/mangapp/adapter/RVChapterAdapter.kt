package com.freelance.ascstb.mangapp.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.freelance.ascstb.mangapp.R
import com.freelance.ascstb.mangapp.model.entity.Chapter
import kotlinx.android.synthetic.main.activity_detail.view.*

class RVChapterAdapter(private var chapterList: MutableList<Chapter>, private val listener: (Chapter) -> Unit) : RecyclerView.Adapter<RVChapterAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chapter_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chapter = chapterList.get(position)


        holder.tvChapterDisplay.isClickable = true
        holder.tvChapterDisplay.movementMethod = LinkMovementMethod.getInstance()
        val chapterText = "<a href='${chapter.chapterUrl}'>${chapter.name}</a>"

        @Suppress("DEPRECATION")
        holder.tvChapterDisplay.text = Html.fromHtml(chapterText)

        holder.tvChapterName.text = chapter.volumne
        holder.tvChapterUpdate.text = chapter.updateDate

        holder.bind(chapter, listener)
    }

    fun updateChapterList(chapterList: List<Chapter>) {
        Log.d(TAG, "updateChapterList: ")
        this.chapterList.addAll(chapterList)

        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvChapterDisplay = itemView.findViewById<TextView>(R.id.tvChapterDisplay)
        val tvChapterName = itemView.findViewById<TextView>(R.id.tvChapterName)
        val tvChapterUpdate = itemView.findViewById<TextView>(R.id.tvChapterUpdate)

        fun bind(chapter: Chapter, clickListener: (Chapter) -> Unit) {
            itemView.setOnClickListener { clickListener(chapter) }
        }
    }

    companion object {
        private val TAG = RVChapterAdapter::class.java.simpleName + "_TAG"
    }
}