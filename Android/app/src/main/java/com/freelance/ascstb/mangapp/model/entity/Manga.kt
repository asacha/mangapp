package com.freelance.ascstb.mangapp.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "manga")
class Manga {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var title: String = ""
    var titleUrl: String = ""
    var genre: String = ""
    var authors: String = ""
    var updated: String = ""
    var coverUrl: String = ""
    var latestChapter: String = ""
    var latestChapterUrl: String = ""
}