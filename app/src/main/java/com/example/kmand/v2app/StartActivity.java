package com.example.kmand.v2app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class StartActivity extends AppCompatActivity {

    Adfragment adFragment;

    Button btn_reserve,btn_mypage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        FragmentManager manager = getSupportFragmentManager();
        adFragment = (Adfragment) manager.findFragmentById(R.id.adFragment);
        adFragment.changeimage();

        btn_reserve = (Button)findViewById(R.id.btn_reserve);
        btn_reserve.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                //
            }
        });
        btn_mypage = (Button)findViewById(R.id.btn_mypage);
        btn_mypage.setOnClickListener(new Button.OnClickListener() {
            @Override
             public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this , LoginActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu) ;

        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        ab.setTitle("READY EAT");

        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_menu:
                break;

            case R.id.action_user:

                break;

            case android.R.id.home:
                onBackPressed();
                return super.onOptionsItemSelected(item);

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
