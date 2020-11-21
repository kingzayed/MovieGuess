package com.example.admin.logoguess

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var tvq: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvq = findViewById<View>(R.id.textView1) as TextView
        @SuppressLint("WrongConstant") val db = openOrCreateDatabase("logo2", Context.MODE_APPEND, null)
        db.execSQL("create table if not exists logo2(highscore text)")
        val c = db.rawQuery("select * from logo2", null) // where name='"+name+"'"
        var max = 0
        while (c.moveToNext()) {
            if (max < c.getString(c.getColumnIndex("highscore")).toInt()) {
                max = c.getString(c.getColumnIndex("highscore")).toInt()
            }
        }
        tvq!!.text = "HIGHSCORE : $max"
    }

    override fun onBackPressed() {
        // TODO Auto-generated method stub
    }

    fun play(view: View?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter User Name")

        // Set up the input
        val input = EditText(this)
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_NORMAL
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("Play") { dialog, which ->
            m_Text = input.text.toString()
            if (m_Text == "") {
                toast()
            } else {
                play1.Companion.hints = 2
                play1.Companion.score = 0
                play1.Companion.i = 0
                play1.Companion.shuffleArray(play1.Companion.images, play1.Companion.names)
                val openStartingPoint = Intent("com.example.admin.logoguess.play1")
                startActivity(openStartingPoint)
            }
        }
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
        builder.show()
    }

    fun exitgame(view: View?) {
        finish()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun howtoplay(view: View?) {
        val openStartingPoint = Intent("com.example.admin.logoguess.howtoplay")
        startActivity(openStartingPoint)
    }

    fun getinfo(view: View?) {
        val openStartingPoint = Intent("com.example.admin.logoguess.info")
        startActivity(openStartingPoint)
        setContentView(R.layout.info)
    }

    fun toast() {
        val layout = LinearLayout(this)
        layout.setBackgroundResource(R.color.black)
        val tv = TextView(this)
        // set the TextView properties like color, size etc
        tv.setTextColor(Color.WHITE)
        tv.textSize = 40f
        tv.gravity = Gravity.CENTER

        // set the text you want to show in  Toast
        tv.text = "ENTER VALID NAME"
        layout.addView(tv)
        val toast = Toast(this) //context is object of Context write "this" if you are an Activity
        // Set The layout as Toast View
        toast.view = layout
        toast.duration = Toast.LENGTH_SHORT

        // Position you toast here toast position is 50 dp from bottom you can give any integral value
        toast.setGravity(Gravity.BOTTOM, 0, 50)
        toast.show()
    }

    companion object {
        var m_Text = ""
    }
}