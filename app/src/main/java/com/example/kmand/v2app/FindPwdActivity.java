package com.example.kmand.v2app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class FindPwdActivity extends AppCompatActivity{

    Button btn_findpwd;
    EditText name_Edit, phone_Edit, id_Edit;
    String name ,phone, id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pwd);


        name_Edit = (EditText)findViewById(R.id.name_findpwd2);
        name_Edit.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    name_Edit.setText("");
                }
            }
        });
        phone_Edit = (EditText)findViewById(R.id.phone_findpwd2);
        phone_Edit.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    phone_Edit.setText("");
                }
            }
        });

        id_Edit = (EditText)findViewById(R.id.id_findpwd2);
        id_Edit.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    id_Edit.setText("");
                }
            }
        });

        btn_findpwd = (Button)findViewById(R.id.btn_findpwd);
        btn_findpwd.setOnClickListener(new Button.OnClickListener(){
                                          public void onClick(View v) {
                                              //if(true) { }
                                              try{
                                                  name = name_Edit.getText().toString();
                                                  phone = phone_Edit.getText().toString();
                                                  id = id_Edit.getText().toString();
                                              }catch(NullPointerException e)
                                              {
                                                  Log.e("err", e.getMessage());
                                              }
                                              findPDB fPDB = new findPDB();
                                              fPDB.execute();
                                          }
                                      }
        );

    }

    public class findPDB extends AsyncTask<Void, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... unused) {

            /* 인풋 파라메터값 생성 */
            String param = "u_name=" + name + "&u_phone=" + phone + "&u_id=" + id + "";

            Log.e("POST",param);
            try {
                /* 서버연결 */
                URL url = new URL("http://172.30.1.6/findpwd.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.connect();

                /* 안드로이드 -> 서버 파라메터값 전달 */
                OutputStream outs = conn.getOutputStream();
                outs.write(param.getBytes("UTF-8"));
                outs.flush();
                outs.close();

                /* 서버 -> 안드로이드 파라메터값 전달 */
                InputStream is = null;
                BufferedReader in = null;
                String data = "";

                is = conn.getInputStream();
                in = new BufferedReader(new InputStreamReader(is), 8 * 1024);
                String line = null;
                StringBuffer buff = new StringBuffer();
                while ( ( line = in.readLine() ) != null )
                {
                    buff.append(line + "\n");
                    Log.e("###GET DATA",line);
                }
                data = buff.toString().trim();

                /* 서버에서 응답 */
                Log.e("RECV DATA",data);

                if(data.equals("failure"))
                {
                    Log.e("RESULT","정보가 일치하지 않습니다.");
                    return data;
                }
                else
                {
                    Log.e("RESULT","성공적으로 처리되었습니다!");
                    return data;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("failure"))
            {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }else
            {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(FindPwdActivity.this, FindedPwdActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("userPwd", s);
                startActivity(intent);
                finish();
            }

        }
    }//findPDB

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        ab.setTitle("비밀번호 찾기");

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
