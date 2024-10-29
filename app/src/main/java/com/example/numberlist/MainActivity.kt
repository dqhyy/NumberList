package com.example.numberlist

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khai báo
        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val textViewError = findViewById<TextView>(R.id.textViewError)
        val listViewResult = findViewById<ListView>(R.id.listViewResult)

        // Show button
        buttonShow.setOnClickListener {
            // Xóa thông báo lỗi cũ
            textViewError.text = ""

            // Lấy giá trị từ EditText
            val inputText = editTextNumber.text.toString()
            if (inputText.isEmpty() || inputText.toIntOrNull() == null || inputText.toInt() <= 0) {
                textViewError.text = "Vui lòng nhập số nguyên dương hợp lệ."
                return@setOnClickListener
            }

            val n = inputText.toInt()
            val resultList = when {
                radioEven.isChecked -> generateEvenNumbers(n)
                radioOdd.isChecked -> generateOddNumbers(n)
                radioSquare.isChecked -> generateSquareNumbers(n)
                else -> emptyList()
            }

            // show listView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listViewResult.adapter = adapter
        }
    }

    // Số chẵn
    private fun generateEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    // Số lẻ
    private fun generateOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    // Số chính phương
    private fun generateSquareNumbers(n: Int): List<Int> {
        val squareNumbers = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squareNumbers.add(i * i)
            i++
        }
        return squareNumbers
    }
}