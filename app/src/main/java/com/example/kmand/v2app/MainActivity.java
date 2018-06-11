package com.example.kmand.v2app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {

    boolean loginFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        Thread myThread = new Thread(new Runnable() {
            public void run() {
                try
                {
                    Thread.sleep(2000);
                }catch(Exception e){}

                loginFlag = false;
                Intent intent=new Intent(MainActivity.this, StartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("loginFlag",loginFlag);

                startActivity(intent);
                finish();
            }
        });

        myThread.start();

    }

}

