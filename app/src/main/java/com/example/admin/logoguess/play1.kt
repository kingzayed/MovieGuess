package com.example.admin.logoguess

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import android.widget.*
import java.util.*

class play1 : Activity() {
    var imgview: ImageView? = null
    private var db: SQLiteDatabase? = null
    var edittext: EditText? = null
    var tvv: TextView? = null
    var hr: TextView? = null
    var tvv1: TextView? = null
    var level: TextView? = null
    var contentv = ContentValues()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playquiz)
        tvv = findViewById<View>(R.id.textView4) as TextView
        level = findViewById<View>(R.id.textlevel) as TextView
        tvv1 = findViewById<View>(R.id.textView10) as TextView
        tvv!!.text = score.toString() + ""
        tvv1!!.setText(MainActivity.Companion.m_Text)
        levels = i / 10 + 1
        if (i != 0) {
            if (i % 10 == 0) {
                hints++
            }
        }
        level!!.text = "Level $levels"
        hr = findViewById<View>(R.id.hintremaining) as TextView
        hr!!.text = "Remaining : $hints"

        //shuffleArray(images);
        imgview = findViewById<View>(R.id.imageView1) as ImageView
        imgview!!.setImageResource(images[i])
        db = openOrCreateDatabase("logo2", Context.MODE_APPEND, null)
        edittext = findViewById<View>(R.id.guesslogo) as EditText
        edittext!!.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(edittext!!.windowToken, 0)
            }
        }
    }

    override fun onBackPressed() {}
    fun hintplease(view: View?) {
        if (hints > 0) {
            val p = names[i]
            toast(p.toUpperCase())
            hints--
            hr!!.text = "Remaining : $hints"
        } else {
            toast("SORRY !!! NO HINT AVAILABLE")
        }
    }

    fun over(view: View?) {
        contentv.put("highscore", score.toString() + "")
        db!!.insert("logo2", null, contentv)
        val openStartingPoint = Intent("com.example.admin.logoguess.gameover")
        startActivity(openStartingPoint)
    }

    fun nextpage(view: View?) {
        val s = edittext!!.text.toString().toUpperCase().trim { it <= ' ' }
        // Log.e("tag", s );
        if (s == names[i].toUpperCase()) {
            i++
            score++
            if (i % 10 == 0) {
                if (i != 0) {
                    toast("CONGRATS !! LEVEL $levels passed")
                } else {
                    toast("CORRECT ANSWER")
                }
            } else {
                toast("CORRECT ANSWER")
            }
            if (i <= 39) {
                val openStartingPoint = Intent("com.example.admin.logoguess.play1")
                startActivity(openStartingPoint)
            } else {
                val openStartingPoint = Intent("com.example.admin.logoguess.winning")
                startActivity(openStartingPoint)
            }
        } else if (s.length == 0) {
            toast("ENTER A NAME")
        } else {
            toast("WRONG ANSWER")
        }
    }

    fun toast(s: String?) {
        val layout = LinearLayout(this)
        layout.setBackgroundResource(R.color.black)
        val tv = TextView(this)
        // set the TextView properties like color, size etc
        tv.setTextColor(Color.WHITE)
        tv.textSize = 45f
        tv.gravity = Gravity.CENTER

        // set the text you want to show in  Toast
        tv.text = s
        layout.addView(tv)
        val toast = Toast(this) //context is object of Context write "this" if you are an Activity
        // Set The layout as Toast View
        toast.view = layout

        // Position you toast here toast position is 50 dp from bottom you can give any integral value
        toast.setGravity(Gravity.BOTTOM, 0, 50)
        toast.show()
    }

    companion object {
        var i = 0
        var hs = 0
        var score = 0
        var levels = 0
        var hints = 2
        var images = intArrayOf(
                R.drawable.forestgump, R.drawable.avatar, R.drawable.darkknight, R.drawable.terminator, R.drawable.halloween, R.drawable.shawshankredemption
                , R.drawable.gladiator, R.drawable.godfather, R.drawable.iamlegend, R.drawable.seven, R.drawable.shining, R.drawable.rocky, R.drawable.departed
                , R.drawable.skyfall, R.drawable.inception, R.drawable.manofsteel, R.drawable.matrix, R.drawable.lordofring, R.drawable.starwars, R.drawable.usualsuspect
                , R.drawable.jaws, R.drawable.piratesofcarribean, R.drawable.harrypotter, R.drawable.diehard, R.drawable.madfury, R.drawable.indianajones
                , R.drawable.schindlerslist, R.drawable.hangover, R.drawable.slumdog, R.drawable.civilwar, R.drawable.lalaland, R.drawable.knightandday, R.drawable.interstellar, R.drawable.titanic, R.drawable.pyscho, R.drawable.goodfellas, R.drawable.argo, R.drawable.mechanic, R.drawable.southpaw, R.drawable.americanbeauty)
        var names = arrayOf("FORREST GUMP", "AVATAR", "THE DARK KNIGHT", "TERMINATOR", "HALLOWEEN", "The SHAWSHANK REDEMPTION", "GLADIATOR", "THE GODFATHER", "I AM LEGEND", "SEVEN", "THE SHINING", "ROCKY", "THE DEPARTED",
                "SKYFALL", "INCEPTION", "MAN OF STEEL", "THE MATRIX", "THE LORD OF THE RINGS", "STAR WARS", "THE USUAL SUSPECTS",
                "JAWS", "PIRATES OF THE CARRIBEAN", "HARRY POTTER", "DIE HARD", "MAD MAX", "INDIANA JONES", "SCHINDLER'S LIST", "THE HANGOVER", "SLUMDOG MILLIONAIRE", "CAPTAIN AMERICA:CIVIL WAR", "LA LA LAND", "KNIGHT AND DAY", "INTERSTELLAR", "TITANIC", "PSYCHO", "GOODFELLAS", "ARGO", "THE MECHANIC", "SOUTHPAW", "AMERICAN BEAUTY")

        fun shuffleArray(ar: IntArray, name: Array<String>) {
            val rnd = Random()
            for (i in ar.size - 1 downTo 1) {
                val index = rnd.nextInt(i + 1)
                // Simple swap
                val a = ar[index]
                val b = name[index]
                ar[index] = ar[i]
                name[index] = name[i]
                ar[i] = a
                name[i] = b
            }
        }
    }
}