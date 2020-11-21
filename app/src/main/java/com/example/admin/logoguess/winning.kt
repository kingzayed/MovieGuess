package com.example.admin.logoguess

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class winning : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.winning)
    }

    fun gohome(view: View?) {
        val openStartingPoint = Intent(this, MainActivity::class.java)
        startActivity(openStartingPoint)
    }

    override fun onBackPressed() {}
}