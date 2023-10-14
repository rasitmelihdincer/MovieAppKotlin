package com.example.movieappkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity("favoriteTable")
data class FavoriteModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id : Int? = null,

    @ColumnInfo("title")
    var title : String? = null,

    @SerializedName("original_title")
    @ColumnInfo("original_title")
    var originalTitle : String?  = null,

    @ColumnInfo("poster_path")
    @SerializedName("poster_path") var posterPath : String?  = null,
)
