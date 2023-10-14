package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var opscreen:TextView? = null
    var lastnumerical:Boolean=false
    var lastdot:Boolean=false

    private fun isoperatoradded(value: String):Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-") ||
                    value.contains("/")
        }
    }
    private fun removezeroafterdot(value: String):String{
        var result=value
        if(result.contains(".0")){
            result.substring(0,value.length-2)
        }
        return result
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        opscreen=findViewById(R.id.calciop)
    }

    fun btnclicked(view: View){
        opscreen?.append((view as Button).text)
        lastnumerical=true
        lastdot=false
    }

    fun clrclicked(view: View){
        opscreen?.text=""
    }

    fun decimalclicked(view: View){
        if(lastnumerical && !lastdot){
            opscreen?.append(".")
            lastnumerical=false
            lastdot=true
        }
    }

    fun operatorclicked(view: View){
        opscreen?.text?.let {
            if(lastnumerical && !isoperatoradded(it.toString())){
                opscreen?.append((view as Button).text)
                lastnumerical=false
                lastdot=false
            }
        }

    }

    fun equalclicked(view: View){
        if(lastnumerical){
            var opvalue=opscreen?.text.toString()
            var prefix=""
            try{
                if(opvalue.startsWith("-")){
                    prefix="-"
                    opvalue.substring(1)
                }
                if(opvalue.contains("-")) {
                    var splitted = opvalue.split("-")
                    var first = splitted[0]
                    var second = splitted[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix+first
                    }
                    opscreen?.text = removezeroafterdot((first.toDouble() - second.toDouble()).toString())
                }
                else if(opvalue.contains("+")) {
                    var splitted = opvalue.split("+")
                    var first = splitted[0]
                    var second = splitted[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix+first
                    }
                    opscreen?.text = removezeroafterdot((first.toDouble() + second.toDouble()).toString())
                }
                else if(opvalue.contains("*")) {
                    var splitted = opvalue.split("*")
                    var first = splitted[0]
                    var second = splitted[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix+first
                    }
                    opscreen?.text = removezeroafterdot((first.toDouble() * second.toDouble()).toString())
                }
                else if(opvalue.contains("/")) {
                    var splitted = opvalue.split("/")
                    var first = splitted[0]
                    var second = splitted[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix+first
                    }
                    opscreen?.text = removezeroafterdot((first.toDouble() / second.toDouble()).toString())
                }
            }
            catch(e:ArithmeticException) {
                e.printStackTrace()
            }

        }
    }
}



