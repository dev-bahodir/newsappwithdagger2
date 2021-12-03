package dev.bahodir.newsappwithdagger2.cachememory.cache3

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class CacheLang(private var context: Context) {
    private var cache: SharedPreferences =
        context.getSharedPreferences("cache_lang", Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits", "ApplySharedPref")
    fun setCacheBool(lang: String) {
        val editor: SharedPreferences.Editor = cache.edit()
        editor.putString("lang", lang)
        editor.commit()
    }

    fun getCacheBool(): String? {
        return cache.getString("lang", "en")
    }
}