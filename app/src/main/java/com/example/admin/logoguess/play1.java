package com.example.admin.logoguess;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class play1 extends Activity {

    public static int i = 0;
    public static int hs = 0;
    public static int score = 0;
    public static int levels = 0;
    public static int hints = 2;
    public ImageView imgview;
    private SQLiteDatabase db;
    public EditText edittext;
    public TextView tvv, hr, tvv1, level;
    ContentValues contentv = new ContentValues();

    public static int[] images = {
            R.drawable.forestgump, R.drawable.avatar, R.drawable.darkknight, R.drawable.terminator, R.drawable.halloween, R.drawable.shawshankredemption
            , R.drawable.gladiator, R.drawable.godfather, R.drawable.iamlegend, R.drawable.seven, R.drawable.shining, R.drawable.rocky, R.drawable.departed
            , R.drawable.skyfall, R.drawable.inception, R.drawable.manofsteel, R.drawable.matrix, R.drawable.lordofring, R.drawable.starwars, R.drawable.usualsuspect
            , R.drawable.jaws, R.drawable.piratesofcarribean, R.drawable.harrypotter, R.drawable.diehard, R.drawable.madfury, R.drawable.indianajones
            , R.drawable.schindlerslist, R.drawable.hangover, R.drawable.slumdog, R.drawable.civilwar, R.drawable.lalaland, R.drawable.knightandday, R.drawable.interstellar, R.drawable.titanic, R.drawable.pyscho, R.drawable.goodfellas, R.drawable.argo, R.drawable.mechanic, R.drawable.southpaw, R.drawable.americanbeauty};
    public static String[] names = {"FORREST GUMP", "AVATAR", "THE DARK KNIGHT", "TERMINATOR", "HALLOWEEN", "The SHAWSHANK REDEMPTION", "GLADIATOR", "THE GODFATHER", "I AM LEGEND", "SEVEN", "THE SHINING", "ROCKY", "THE DEPARTED",
            "SKYFALL", "INCEPTION", "MAN OF STEEL", "THE MATRIX", "THE LORD OF THE RINGS", "STAR WARS", "THE USUAL SUSPECTS",
            "JAWS", "PIRATES OF THE CARRIBEAN", "HARRY POTTER", "DIE HARD", "MAD MAX", "INDIANA JONES", "SCHINDLER'S LIST", "THE HANGOVER", "SLUMDOG MILLIONAIRE", "CAPTAIN AMERICA:CIVIL WAR", "LA LA LAND", "KNIGHT AND DAY", "INTERSTELLAR", "TITANIC", "PSYCHO", "GOODFELLAS", "ARGO", "THE MECHANIC", "SOUTHPAW", "AMERICAN BEAUTY"};

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playquiz);
        tvv = (TextView) findViewById(R.id.textView4);
        level = (TextView) findViewById(R.id.textlevel);
        tvv1 = (TextView) findViewById(R.id.textView10);
        tvv.setText(score + "");
        tvv1.setText(MainActivity.m_Text);

        levels = (i / 10) + 1;
        if (i != 0) {
            if (i % 10 == 0) {
                hints++;
            }
        }
        level.setText("Level " + levels);
        hr = (TextView) findViewById(R.id.hintremaining);
        hr.setText("Remaining : " + hints);

        //shuffleArray(images);
        imgview = (ImageView) findViewById(R.id.imageView1);
        imgview.setImageResource(images[i]);

        db = openOrCreateDatabase("logo2", MODE_APPEND, null);

        edittext = (EditText) findViewById(R.id.guesslogo);

        edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
                }
            }
        });
    }

    public void onBackPressed() {

    }

    public void hintplease(View view) {
        if (hints > 0) {
            String p = names[i];
            toast(p.toUpperCase());
            hints--;
            hr.setText("Remaining : " + hints);
        } else {
            toast("SORRY !!! NO HINT AVAILABLE");
        }
    }

    public void over(View view) {
        contentv.put("highscore", (score + ""));
        db.insert("logo2", null, contentv);
        Intent openStartingPoint = new Intent("com.example.admin.logoguess.gameover");
        startActivity(openStartingPoint);
    }

    public void nextpage(View view) {

        String s = (((edittext.getText().toString()).toUpperCase()).trim());
        // Log.e("tag", s );
        if (s.equals((names[i]).toUpperCase())) {
            i++;
            score++;
            if (i % 10 == 0) {
                if (i != 0) {
                    toast("CONGRATS !! LEVEL " + levels + " passed");
                } else {
                    toast("CORRECT ANSWER");
                }
            } else {
                toast("CORRECT ANSWER");
            }


            if (i <= 39) {
                Intent openStartingPoint = new Intent("com.example.admin.logoguess.play1");
                startActivity(openStartingPoint);
            } else {
                Intent openStartingPoint = new Intent("com.example.admin.logoguess.winning");
                startActivity(openStartingPoint);
            }
        } else if (s.length() == 0) {
            toast("ENTER A NAME");
        } else {
            toast("WRONG ANSWER");
        }

    }

    public static void shuffleArray(int[] ar, String[] name) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            String b = name[index];
            ar[index] = ar[i];
            name[index] = name[i];
            ar[i] = a;
            name[i] = b;
        }

    }

    public void toast(String s) {
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundResource(R.color.black);

        TextView tv = new TextView(this);
        // set the TextView properties like color, size etc
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(45);

        tv.setGravity(Gravity.CENTER);

        // set the text you want to show in  Toast
        tv.setText(s);

        layout.addView(tv);

        Toast toast = new Toast(this); //context is object of Context write "this" if you are an Activity
        // Set The layout as Toast View
        toast.setView(layout);

        // Position you toast here toast position is 50 dp from bottom you can give any integral value
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.show();
    }
}