package org.mlesny.rpn

import android.content.Context
import android.preference.PreferenceManager

class State ( context: Context){
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor = prefs.edit()
    private val CACHE = "stack-cache"
    private var color = prefs.edit()

    fun color( col: String) : State {
//        color.putString("Color", col).apply()
        return save("Color", col)
    }

    fun color() : String {
        return prefs.getString("Color", "")
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