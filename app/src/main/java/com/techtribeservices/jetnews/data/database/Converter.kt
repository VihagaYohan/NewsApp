package com.techtribeservices.jetnews.data.database

import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverters
    fun fromString(value:String): List<String> {
        val gson = Gson()
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverters
    fun fromList(list:List<String>):String {
       val gson = Gson()
        return gson.toJson(list)
    }
}