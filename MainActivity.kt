package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private var currentInput: String = ""
    private var operator: String? = null
    private var operand1: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonCE: Button = findViewById(R.id.buttonCE)  // Atualizado aqui
        val buttonEquals: Button = findViewById(R.id.buttonEquals)

        val numberButtons = listOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)

        numberButtons.forEach { button ->
            button.setOnClickListener {
                currentInput += (it as Button).text
                updateResult()
            }
        }

        buttonAdd.setOnClickListener { performOperation("+") }
        buttonSubtract.setOnClickListener { performOperation("-") }
        buttonMultiply.setOnClickListener { performOperation("*") }
        buttonDivide.setOnClickListener { performOperation("/") }
        buttonCE.setOnClickListener { clear() }  // Atualizado aqui
        buttonEquals.setOnClickListener { calculateResult() }
    }

    private fun updateResult() {
        resultTextView.text = currentInput
    }

    private fun clear() {
        currentInput = ""
        operator = null
        operand1 = null
        updateResult()
    }

    private fun performOperation(op: String) {
        if (currentInput.isNotEmpty()) {
            operand1 = currentInput.toDouble()
            operator = op
            currentInput = ""
        }
    }

    private fun calculateResult() {
        if (operator != null && currentInput.isNotEmpty() && operand1 != null) {
            val operand2 = currentInput.toDouble()
            val result = when (operator) {
                "+" -> operand1!! + operand2
                "-" -> operand1!! - operand2
                "*" -> operand1!! * operand2
                "/" -> operand1!! / operand2
                else -> 0.0
            }
            resultTextView.text = result.toString()
            currentInput = ""
            operator = null
            operand1 = null
        }
    }
}
