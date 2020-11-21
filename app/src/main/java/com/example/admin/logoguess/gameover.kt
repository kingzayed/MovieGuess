package com.example.admin.logoguess

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView

class gameover : Activity() {
    var tv: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gameover)
        tv = findViewById<View>(R.id.textView2) as TextView
        tv!!.text = "Your Score is " + play1.Companion.score
    }

    override fun onBackPressed() {}
    fun playagain(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}