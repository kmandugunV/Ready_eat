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
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by kmand on 2018-06-06.
 */

public class RegisterActivity extends AppCompatActivity{

    boolean loginFlag;
    Button btn_next;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Intent intent = getIntent();
        loginFlag = intent.getBooleanExtra("loginFlag", false);

        btn_next = (Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new Button.OnClickListener() {
                                            public void onClick(View v) {
                                                CheckBox check1, check2, check3, check4;
                                                check1 = (CheckBox)findViewById(R.id.checkBox);
                                                check2 = (CheckBox)findViewById(R.id.checkBox2);
                                                check3 = (CheckBox)findViewById(R.id.checkBox3);
                                                check4 = (CheckBox)findViewById(R.id.checkBox4);
                                                if(check1.isChecked() == true && check2.isChecked() == true && check3.isChecked() == true && check4.isChecked() == true)
                                                {
                                                    Intent intent = new Intent(RegisterActivity.this, Register2Activity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                                    intent.putExtra("loginFlag",loginFlag);

                                                    startActivity(intent);
                                                }else
                                                    Toast.makeText(getApplicationContext(), "체크박스를 전부 체크해주세요.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
        );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu) ;

        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        ab.setTitle("회원가입");

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
