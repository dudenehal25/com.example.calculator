package com.example.calculator

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    var lastNumeric = false
    var lastDecimal = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onClick(view: View) {
        tvMain.append(view.tag.toString())
        lastNumeric  = true

    }

    fun onClear(view: View) {
        tvMain.text = ""
        lastNumeric = false
        lastDecimal = false
    }



    fun decimalCheck(view: View){
        if (lastNumeric && !lastDecimal){
            tvMain.append(".")
            lastNumeric = false
            lastDecimal = true
        }
    }

    fun operatorInput(view: View){

        if (lastNumeric && !isOperatorAdded(tvMain.text.toString())){
            tvMain.append((view as Button).text)
            lastNumeric = false
            lastDecimal = false
        }
    }

    private fun isOperatorAdded(value: String) : Boolean {
        return if (value.startsWith("-")) {
                 false
             }
        else {value.contains("+") || value.contains("-") || value.contains("*") || value.contains("/")

        }
    }

    fun onEquals(view: View){

        try{
        if (lastNumeric){
            var value = tvMain.text.toString()
            var prefix = ""

            if (value.startsWith("-")) {
                prefix = "-"
                value = value.substring(1);
            }

          if (value.contains("-") ){
              val splitvalue = value.split("-")

              var one = splitvalue[0]
              var two = splitvalue[1]

              if (!prefix.isEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                  one = prefix + one
              }

              tvMain.text = (one.toDouble() - two.toDouble()).toString()
          }
          else if (value.contains("+") ){
                val splitvalue = value.split("+")

                var one = splitvalue[0]
                var two = splitvalue[1]

              if (!prefix.isEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                  one = prefix + one
              }

                tvMain.text = (one.toDouble() + two.toDouble()).toString()
            }
          else if (value.contains("*") ){
              val splitvalue = value.split("*")

              var one = splitvalue[0]
              var two = splitvalue[1]

              if (!prefix.isEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                  one = prefix + one
              }

              tvMain.text = (one.toDouble() * two.toDouble()).toString()
          }
          else if (value.contains("/") ){
              val splitvalue = value.split("/")

              var one = splitvalue[0]
              var two = splitvalue[1]

              if (!prefix.isEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                  one = prefix + one
              }

              tvMain.text = (one.toDouble() + two.toDouble()).toString()
          }

        }}
        catch (e:Exception){
            e.printStackTrace()
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
        }



    }
}