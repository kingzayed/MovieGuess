package com.example.admin.logoguess;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvq;
    public static String m_Text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvq = (TextView) findViewById(R.id.textView1);

        @SuppressLint("WrongConstant") SQLiteDatabase db = openOrCreateDatabase("logo2", MODE_APPEND, null);
        db.execSQL("create table if not exists logo2(highscore text)");
        Cursor c = db.rawQuery("select * from logo2", null);// where name='"+name+"'"
        int max = 0;
        while (c.moveToNext()) {
            if (max < Integer.parseInt(c.getString(c.getColumnIndex("highscore")))) {
                max = Integer.parseInt(c.getString(c.getColumnIndex("highscore")));
            }
        }
        tvq.setText("HIGHSCORE : " + max);
    }

    public void onBackPressed() {
        // TODO Auto-generated method stub

    }


    public void play(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter User Name");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Play", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                if (m_Text.equals("")) {
                    toast();
                } else {
                    play1.hints = 2;
                    play1.score = 0;
                    play1.i = 0;
                    play1.shuffleArray(play1.images, play1.names);
                    Intent openStartingPoint = new Intent("com.example.admin.logoguess.play1");
                    startActivity(openStartingPoint);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void exitgame(View view) {
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void howtoplay(View view) {
        Intent openStartingPoint = new Intent("com.example.admin.logoguess.howtoplay");
        startActivity(openStartingPoint);
    }

    public void getinfo(View view) {
        Intent openStartingPoint = new Intent("com.example.admin.logoguess.info");
        startActivity(openStartingPoint);
        setContentView(R.layout.info);
    }

    public void toast() {
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundResource(R.color.black);

        TextView tv = new TextView(this);
        // set the TextView properties like color, size etc
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(40);

        tv.setGravity(Gravity.CENTER);

        // set the text you want to show in  Toast
        tv.setText("ENTER VALID NAME");

        layout.addView(tv);

        Toast toast = new Toast(this); //context is object of Context write "this" if you are an Activity
        // Set The layout as Toast View
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_SHORT);

        // Position you toast here toast position is 50 dp from bottom you can give any integral value
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.show();
    }
}
