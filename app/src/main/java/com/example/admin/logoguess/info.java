package com.example.admin.logoguess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
    }

    public void backhome(View view) {
        Intent openStartingPoint = new Intent(this, MainActivity.class);
        startActivity(openStartingPoint);
    }

    public void onBackPressed() {


    }

}
