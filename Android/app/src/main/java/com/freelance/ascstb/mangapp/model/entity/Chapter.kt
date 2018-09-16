package com.freelance.ascstb.mangapp.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "")
class Chapter {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var name = ""
    var volumne = ""
    var chapterUrl = ""
    var updateDate = ""
}