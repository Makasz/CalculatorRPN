package org.scoutant.rpn

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.widget.RadioButton
import android.widget.Button
import android.widget.RadioGroup


class SettingsActivity : Activity() {

    val color = "#0b720b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
//        val blue = findViewById<View>(R.id.)
        val red = findViewById<View>(R.id.redButton).id
        val blue = findViewById<View>(R.id.blueButton).id
        val green = findViewById<View>(R.id.greenButton).id
        val radioGroup : RadioGroup = findViewById(R.id.radio)
        val button : Button = findViewById(R.id.applyButton)
        button.setOnClickListener{
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == red) {
                val intent = Intent()
                intent.putExtra("Color", "#a31111")
                setResult(RESULT_OK, intent)
                finish()
            }
            if (selectedId == blue) {
                val intent = Intent()
                intent.putExtra("Color", "#1147a3")
                setResult(RESULT_OK, intent)
                finish()
            }
            if (selectedId == green) {
                val intent = Intent()
                intent.putExtra("Color", "#0b720b")
                setResult(RESULT_OK, intent)
                finish()
            }

        }
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
