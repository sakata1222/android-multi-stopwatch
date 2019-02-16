package jp.gr.java_conf.saka.multistopwatch.preference

import android.content.Context


class PreferenceAccessor(val preferenceFile: String, val mode: Int) {
    fun saveInt(context: Context, key: String, saveValue: Int) {
        val pref = context.getSharedPreferences(preferenceFile, mode)
        val editor = pref.edit()
        editor.putInt(key, saveValue)
        editor.commit()
    }

    fun loadInt(context: Context, key: String, defaultValue: Int): Int {
        val pref = context.getSharedPreferences(preferenceFile, mode)
        return pref.getInt(key, defaultValue)
    }

    fun saveString(context: Context, key: String, saveValue: String) {
        val pref = context.getSharedPreferences(preferenceFile, mode)
        val editor = pref.edit()
        editor.putString(key, saveValue)
        editor.commit()
    }

    fun loadString(context: Context, key: String, defaultValue: String): String {
        val pref = context.getSharedPreferences(preferenceFile, mode)
        return pref.getString(key, defaultValue)
    }
}
