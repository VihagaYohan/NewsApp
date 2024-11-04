package com.techtribeservices.jetnews.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.techtribeservices.jetnews.data.database.Converter

@Entity(tableName = "news")
@TypeConverters(Converter::class)
data class News(val summary: String = "",
                val image: String = "",
                val sentiment: Double = 0.0,
                val author: String = "",
                val language: String = "",
                val video: String = "",
                val title: String = "",
                val url: String = "",
                val sourceCountry: String = "",
                @PrimaryKey(autoGenerate = true) val id: Int? = null,
                val text: String = "",
                val category: String = "",
                val publishDate: String = "",
                val authors: List<String>?)