package org.mlesny.rpn

import android.content.Context
import android.preference.PreferenceManager

class State ( context: Context){
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor = prefs.edit()
    private val CACHE = "stack-cache"

    fun color( col: String) : State {
        return save("color-cache", col)
    }

    fun color() : String {
        return prefs.getString("color-cache", "")
    }

    fun cache (list: String) : State {
        if (cache().equals(list)) return this
        return save( CACHE, list)
    }
    fun cache() : String {
        return prefs.getString( CACHE, "")
    }

    private fun save( key:String, value: String) : State {
        editor.putString( key, value).apply()
        return this
    }
}