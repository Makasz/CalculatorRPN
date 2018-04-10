package org.mlesny.rpn

import java.math.BigDecimal
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.toast

class Main : Activity(), Update {

    private val buffer: Buffer = Buffer()
    private var calculator: Calculator = Calculator()
    private val state: State by lazy { State(this) }
    private var buffer_view: TextView? = null
    private val id_list = listOf(R.id.stack0, R.id.stack1, R.id.stack2, R.id.stack3, R.id.stack4)
    private var cells_list: Array<TextView?> = arrayOfNulls(id_list.size)

    override fun onCreate(savedInstanceState: Bundle?) {
        state.color("#1147a3")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        buffer_view = findViewById(R.id.buffer)
        for (i in 0..id_list.size - 1) {
            cells_list[i] = findViewById(id_list[i])
        }
    }

    fun launchSettings(v: View) {
        val intent = Intent(this, SettingsActivity::class.java).apply {}
        startActivityForResult(intent, 1)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val strEditText : String = data.getStringExtra("Color")
                val layoutMain = findViewById<View>(R.id.MainLayout)
                layoutMain.setBackgroundColor(Color.parseColor(strEditText))
                state.color(strEditText)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        calculator = Calculator(state.cache())
        val layoutMain = findViewById<View>(R.id.MainLayout)
        layoutMain.setBackgroundColor(Color.parseColor(state.color()))
        update()
    }

    private var previous: String = ""


    override fun update() {
//        val layoutMain = findViewById<View>(R.id.MainLayout)
//        layoutMain.setBackgroundColor(Color.parseColor(this.color_back))
        if (buffer.isEmpty()) buffer_view!!.visibility = View.GONE
        else {
            buffer_view!!.visibility = View.VISIBLE
            buffer_view!!.text = buffer.get()
        }

        val nb = Math.min(id_list.size - 1, calculator.size() - 1)
        for (i in 0..nb) {
            cells_list[i]?.text = "%f".format(calculator.stack[calculator.size() - 1 - i].setScale(4, BigDecimal.ROUND_HALF_UP))
        }
        for (i in nb + 1..id_list.size - 1) {
            cells_list[i]?.text = ""
        }

        previous = state.cache()
        state.cache(calculator.toString())
    }

    fun push() {
        if (buffer.isEmpty()) return
        calculator.push(buffer.get())
        buffer.reset()
    }

    fun digit(v: View) {
        val digit: String = v.tag as String
        Log.d("keyboard", "digit : " + digit)
        buffer.append(digit)
        update()
    }

    fun enter(v: View) {
        if (!buffer.isEmpty()) push()
        update()
    }

    fun drop(v: View) {
        push(); calculator.drop(); update(); }

    fun sqrt(v: View) {
        push()
        try {
            calculator.sqrt()
            update()
        } catch (e: ArithmeticException) {
            toast("Enter positive number!")
        }
    }

    fun negate(v: View) {
        push(); calculator.negate(); update(); }

    fun reciprocal(v: View) {
        push()
        try {
            calculator.reciprocal()
            update()
        } catch (e: ArithmeticException) {
            toast("Division by 0!")
        }
    }

    fun swap(v: View) {
        push(); calculator.swap(); update(); }

    fun add(v: View) {
        push(); calculator.add(); update(); }

    fun subtract(v: View) {
        push(); calculator.subtract(); update(); }

    fun multiply(v: View) {
        push(); calculator.multiply(); update(); }

    fun divide(v: View) {
        push()
        try {
            calculator.divide()
            update()
        } catch (e: ArithmeticException) {
            toast("Division by 0!")
        }
    }

    fun power(v: View) {
        push(); calculator.power(); update(); }

    // buffer operations
    fun delete(v: View) {
        buffer.delete(); update(); }

    fun dot(v: View) {
        buffer.dot(); update(); }

    fun undo(v: View) {
        calculator = Calculator(previous)
        update()
    }

}