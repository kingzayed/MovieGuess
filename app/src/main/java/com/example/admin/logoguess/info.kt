package com.example.admin.logoguess

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class info : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)
    }

    fun backhome(view: View?) {
        val openStartingPoint = Intent(this, MainActivity::class.java)
        startActivity(openStartingPoint)
    }

    override fun onBackPressed() {}
}