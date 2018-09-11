package com.example.kmand.v2app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class FindChooseActivity extends AppCompatActivity{

    Button btn_find_id, btn_find_pwd;
    boolean loginFlag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_choose);

        Intent intent = getIntent();
        loginFlag = intent.getExtras().getBoolean("loginFlag");

        btn_find_id = (Button)findViewById(R.id.btn_find_id);
        btn_find_id.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(FindChooseActivity.this , FindIdActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        intent.putExtra("loginFlag",loginFlag);
                        startActivity(intent);
                    }
                }
        );

        btn_find_pwd = (Button)findViewById(R.id.btn_find_pwd);
        btn_find_pwd.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindChooseActivity.this , FindPwdActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("loginFlag",loginFlag);
                startActivity(intent);
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        ab.setTitle("ID/비밀번호 찾기");

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
