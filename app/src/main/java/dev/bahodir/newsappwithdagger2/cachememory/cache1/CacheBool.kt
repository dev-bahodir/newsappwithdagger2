package dev.bahodir.newsappwithdagger2.cachememory.cache1

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class CacheBool(private var context: Context) {
    private var cache: SharedPreferences =
        context.getSharedPreferences("cache_memory", Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits", "ApplySharedPref")
    fun setCacheBool(bool: Boolean) {
        val editor: SharedPreferences.Editor = cache.edit()
        editor.putBoolean("bool", bool)
        editor.commit()
    }

    fun getCacheBool(): Boolean {
        return cache.getBoolean("bool", false)
    }
}