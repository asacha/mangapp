package com.freelance.ascstb.mangapp.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "mangaDetail")
class MangaDetail {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var title = ""
    var authors = ""
    var artists = ""
    var status = ""
    var alternativeName = ""
    var genres = ""
    var summary = ""

    var chapters: MutableList<Chapter> = ArrayList<Chapter>()
}