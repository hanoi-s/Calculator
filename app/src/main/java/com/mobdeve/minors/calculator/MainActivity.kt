package com.mobdeve.minors.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var DBHelper : DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Number */
        val btn1 : TextView = findViewById(R.id.btn1)
        val btn2 : TextView = findViewById(R.id.btn2)
        val btn3 : TextView = findViewById(R.id.btn3)
        val btn4 : TextView = findViewById(R.id.btn4)
        val btn5 : TextView = findViewById(R.id.btn5)
        val btn6 : TextView = findViewById(R.id.btn6)
        val btn7 : TextView = findViewById(R.id.btn7)
        val btn8 : TextView = findViewById(R.id.btn8)
        val btn9 : TextView = findViewById(R.id.btn9)
        val btn0 : TextView = findViewById(R.id.btn0)

        /* Operators */
        val btnPlus : TextView = findViewById(R.id.btnPlus)
        val btnMinus : TextView = findViewById(R.id.btnMinus)
        val btnDivide : TextView = findViewById(R.id.btnDivide)
        val btnMultiply : TextView = findViewById(R.id.btnMultiply)
        val btnEquals : TextView = findViewById(R.id.btnEquals)
        val btnC : TextView = findViewById(R.id.btnC)

        /* Output */
        val tvComputation : TextView = findViewById(R.id.tvComputation)
        val tvOutput : TextView = findViewById(R.id.tvOutput)

        DBHelper = DBHelper(this)
        DBHelper.createTable()

        btn1.setOnClickListener {
            evaluateExpression("1", clear = true)
        }

        btn2.setOnClickListener {
            evaluateExpression("2", clear = true)
        }

        btn3.setOnClickListener {
            evaluateExpression("3", clear = true)
        }

        btn4.setOnClickListener {
            evaluateExpression("4", clear = true)
        }

        btn5.setOnClickListener {
            evaluateExpression("5", clear = true)
        }

        btn6.setOnClickListener {
            evaluateExpression("6", clear = true)
        }

        btn7.setOnClickListener {
            evaluateExpression("7", clear = true)
        }

        btn8.setOnClickListener {
            evaluateExpression("8", clear = true)
        }

        btn9.setOnClickListener {
            evaluateExpression("9", clear = true)
        }

        btn0.setOnClickListener {
            evaluateExpression("0", clear = true)
        }


        btnPlus.setOnClickListener {
            evaluateExpression("+", clear = true)
        }

        btnMinus.setOnClickListener {
            evaluateExpression("-", clear = true)
        }

        btnDivide.setOnClickListener {
            evaluateExpression("/", clear = true)
        }

        btnMultiply.setOnClickListener {
            evaluateExpression("*", clear = true)
        }

        btnEquals.setOnClickListener {
            try {
                val text = tvComputation.text.toString()
                val expression = ExpressionBuilder(text).build()

                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    tvOutput.text = longResult.toString()
                } else {
                    tvOutput.text = result.toString()
                }
                tvComputation.text = ""
                DBHelper.addComputation(text,tvOutput.text.toString())
            } catch (e : Exception) {
                Toast.makeText(this,"Unable to compute please check your entry", Toast.LENGTH_SHORT).show()
            }
        }

        btnC.setOnClickListener {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }

    }

    fun evaluateExpression(string: String, clear: Boolean) {
        /* Output */
        val tvComputation : TextView = findViewById(R.id.tvComputation)
        val tvOutput : TextView = findViewById(R.id.tvOutput)

        if(clear) {
            tvOutput.text = ""
            tvComputation.append(string)
        } else {
            tvComputation.append(tvOutput.text)
            tvComputation.append(string)
            tvOutput.text = ""
        }
    }
}