package com.example.admin.logoguess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class gameover extends Activity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        tv = (TextView) findViewById(R.id.textView2);
        tv.setText("Your Score is " + play1.score);

    }

    public void onBackPressed() {

    }

    public void playagain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
