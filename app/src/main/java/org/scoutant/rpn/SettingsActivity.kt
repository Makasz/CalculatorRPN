package org.scoutant.rpn

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.content.Intent


class SettingsActivity : Activity() {

    val color = "#0b720b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
//        val blue = findViewById<View>(R.id.)
    }

    fun goToMenu(v: View){
        val intent = Intent()
        intent.putExtra("Color", "value_here")
        setResult(RESULT_OK, intent)
        finish()
    }

    fun redButton(v: View){

    }

    fun blueButton(v: View){

    }

    fun greenButton(v: View){

    }

    fun applyButtom(v: View){

    }
}
