package com.example.kmand.v2app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class FindedPwdActivity extends AppCompatActivity{

    Button btn_login, btn_find_pwd;
    String str, pwd;
    TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finded_pwd);

        Intent intent = getIntent();
        pwd = intent.getStringExtra("userPwd");
        Log.e("getintent pwd : ", pwd);

        tv = (TextView)findViewById(R.id.finded_pwd);
        str = "회원님의 비밀번호는 '";
        str = str.concat(pwd);
        str = str.concat("' 입니다.");
        
        tv.setText(str);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new Button.OnClickListener() {
                                           public void onClick(View v) {
                                               Intent intent = new Intent(FindedPwdActivity.this , LoginActivity.class);
                                               intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                               startActivity(intent);
                                           }
                                       }
        );


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
