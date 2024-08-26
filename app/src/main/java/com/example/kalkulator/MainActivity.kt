package com.example.kalkulator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var firstValue: Double = Double.NaN
    private var secondValue: Double = 0.0
    private var currentOperator: Char = '0'
    private var newOperator: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        // Listener untuk angka
        val numberListener = View.OnClickListener { v ->
            val b = v as Button
            if (newOperator) {
                tvDisplay.text = b.text
                newOperator = false
            } else {
                tvDisplay.append(b.text)
            }
        }

        findViewById<Button>(R.id.btn0).setOnClickListener(numberListener)
        findViewById<Button>(R.id.btn1).setOnClickListener(numberListener)
        findViewById<Button>(R.id.btn2).setOnClickListener(numberListener)
        findViewById<Button>(R.id.btn3).setOnClickListener(numberListener)
        findViewById<Button>(R.id.btn4).setOnClickListener(numberListener)
        findViewById<Button>(R.id.btn5).setOnClickListener(numberListener)
        findViewById<Button>(R.id.btn6).setOnClickListener(numberListener)
        findViewById<Button>(R.id.btn7).setOnClickListener(numberListener)
        findViewById<Button>(R.id.btn8).setOnClickListener(numberListener)
        findViewById<Button>(R.id.btn9).setOnClickListener(numberListener)

        // Listener untuk operasi
        val operatorListener = View.OnClickListener { v ->
            val b = v as Button
            if (!firstValue.isNaN()) {
                secondValue = tvDisplay.text.toString().toDouble()
                firstValue = performOperation(firstValue, secondValue, currentOperator)
                tvDisplay.text = firstValue.toString()
            } else {
                firstValue = tvDisplay.text.toString().toDouble()
            }
            currentOperator = b.text[0]
            newOperator = true
        }

        findViewById<Button>(R.id.btnPlus).setOnClickListener(operatorListener)
        findViewById<Button>(R.id.btnMinus).setOnClickListener(operatorListener)
        findViewById<Button>(R.id.btnMultiply).setOnClickListener(operatorListener)
        findViewById<Button>(R.id.btnDivide).setOnClickListener(operatorListener)

        // Listener untuk sama dengan (=)
        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            if (!firstValue.isNaN()) {
                secondValue = tvDisplay.text.toString().toDouble()
                firstValue = performOperation(firstValue, secondValue, currentOperator)
                tvDisplay.text = firstValue.toString()
                firstValue = Double.NaN
                currentOperator = '0'
            }
        }

        // Listener untuk clear (C)
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            tvDisplay.text = "0"
            firstValue = Double.NaN
            currentOperator = '0'
            newOperator = true
        }
    }

    private fun performOperation(a: Double, b: Double, operator: Char): Double {
        return when (operator) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> if (b != 0.0) a / b else 0.0
            else -> 0.0
        }
    }
}