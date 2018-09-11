package com.example.kmand.v2app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class FindIdActivity extends AppCompatActivity{

    Button btn_findid;
    boolean loginFlag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);

        Intent intent = getIntent();
        loginFlag = intent.getExtras().getBoolean("loginFlag");
/*
        btn_findid = (Button)findViewById(R.id.btn_findid);
        btn_findid.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {
                        //if(true) { }

                        Intent intent = new Intent(FindIdActivity.this , FindedIdActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        intent.putExtra("loginFlag",loginFlag);
                        startActivity(intent);
                    }
                }
        );
*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        ab.setTitle("ID 찾기");

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
                if(loginFlag == true)
                {

                }else
                {
                    Intent intent=new Intent(this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.putExtra("loginFlag",loginFlag);
                    startActivity(intent);
                }
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
