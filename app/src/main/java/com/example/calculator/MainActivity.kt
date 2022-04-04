package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException
import java.security.KeyStore

class MainActivity : AppCompatActivity() {

    var display:TextView?=null
    var prevNumeric:Boolean= true
    var prevdecimal:Boolean= false
    var prevcal:Boolean=false
    lateinit var cal:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display=findViewById(R.id.displays)
    }


    fun clear(view:View)
    {
        display?.text=""
        prevNumeric= true
        prevdecimal= false

    }


    fun display_number(view: View)
    {
        // view stand for ui elements here view as button and we are displaying it as text
        display?.append((view as Button).text)
        prevcal=false
        prevNumeric=true
        prevdecimal=false

    }

    fun displaydot(view: View)
    {
        if(prevNumeric and !prevdecimal)
        {
            display?.append(".")
            prevNumeric =false
            prevdecimal =true
        }
    }

    fun delete(view:View)
    {
        var str:String?=display?.text.toString()
        str?.let {
            display?.text=str?.dropLast(1)
        }

    }

    fun calculationsDisplay(view: View)
    {
        display?.text.let {
            if ( !prevcal )
            {
                display?.append((view as  Button).text)
                prevcal=true
            }
            else
            {
                var string:String?=display?.text.toString().dropLast(1)
                string?.let {
                    display?.text=string
                    display?.append((view as Button).text)
                }

            }
        }

    }

    private fun removezero(str:String):String{
        var Str=str
        if(Str.contains(".0"))
        {
            Str=str.substring(0,str.length-2)

        }

        return Str
    }

    fun equalDisplay(view: View)
    {


        if(prevNumeric) {
            cal = display?.text.toString()
            var prefix = ""




            try {
                if (cal.startsWith("-")) {
                    cal = cal.substring(1)
                    prefix = "-"

                }

                else if (cal.startsWith("/") or  cal.startsWith("*") or cal.startsWith("+")
                or cal.startsWith("%"))
                {
                    cal =cal.substring(1)
                }



                if(cal.contains("-"))
                {
                    // like 99-5 where 99 is at o index and 5 is at index 1
                    val split = cal?.split("-")
                    var one = split[0]
                    var two = split[1]



                    one = prefix + one
                    display?.text = removezero((one.toDouble() - two.toDouble()).toString())
                }

                else if(cal.contains("%"))
                {
                    // like 99-5 where 99 is at o index and 5 is at index 1
                    val split = cal?.split("%")
                    var one = split[0]
                    var two = split[1]



                    one = prefix + one
                    display?.text = removezero((one.toDouble() % two.toDouble()).toString())
                }
                else if(cal.contains("+"))
                {
                    // like 99-5 where 99 is at o index and 5 is at index 1
                    val split = cal?.split("+")
                    var one = split[0]
                    var two = split[1]



                    one = prefix + one
                    display?.text = removezero((one.toDouble() + two.toDouble()).toString())
                }
                else if(cal.contains("*"))
                {
                    // like 99-5 where 99 is at o index and 5 is at index 1
                    val split = cal?.split("*")
                    var one = split[0]
                    var two = split[1]



                    one = prefix + one
                    display?.text = removezero((one.toDouble() * two.toDouble()).toString())
                }
                else if(cal.contains("/"))
                {
                    // like 99-5 where 99 is at o index and 5 is at index 1
                    val split = cal?.split("/")
                    var one = split[0]
                    var two = split[1]



                    one = prefix + one
                    display?.text = removezero((one.toDouble() / two.toDouble()).toString())
                }

                else
                {
                    display?.text=removezero(cal)
                }


            }
            catch (e: ArithmeticException) {
                e.printStackTrace()
            }

        }



    }
}