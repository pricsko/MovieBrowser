package com.mbh.moviebrowser.data.accessories

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LongListConverter {

    @TypeConverter
    fun fromStringToLongList(value: String?): List<Long?>? {
        val listType: Type = object : TypeToken<List<Long?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromLongListToString(list: List<Long?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}