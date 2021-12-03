package dev.bahodir.newsappwithdagger2.cachememory.cache2

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class CacheTheme(private var context: Context) {
    private var cache: SharedPreferences =
        context.getSharedPreferences("cache_theme", Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits", "ApplySharedPref")
    fun setCacheBool(bool: Boolean) {
        val editor: SharedPreferences.Editor = cache.edit()
        editor.putBoolean("theme", bool)
        editor.commit()
    }

    fun getCacheBool(): Boolean {
        return cache.getBoolean("theme", false)
    }
}