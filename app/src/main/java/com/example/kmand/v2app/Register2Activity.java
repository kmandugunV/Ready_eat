package com.example.kmand.v2app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by kmand on 2018-06-06.
 */

public class Register2Activity extends AppCompatActivity{

    private EditText name_regi2;
    private EditText birth_regi2;
    private EditText id_regi2;
    private EditText pwd_regi2,pwd2_regi2;
    private EditText phone_regi2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        name_regi2 = (EditText)findViewById(R.id.name_regi2);
        birth_regi2 = (EditText)findViewById(R.id.birth_regi2);
        id_regi2 = (EditText)findViewById(R.id.id_regi2);
        pwd_regi2 = (EditText)findViewById(R.id.pwd_regi2);
        pwd2_regi2 = (EditText)findViewById(R.id.pwd2_regi2);
        phone_regi2 = (EditText)findViewById(R.id.phone_regi2);


        name_regi2.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    name_regi2.setText("");
                }
            }
        });
        birth_regi2.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    birth_regi2.setText("");
                }
            }
        });
        id_regi2.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                   id_regi2.setText("");
                }
            }
        });
        pwd_regi2.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    pwd_regi2.setText("");
                }
            }
        });

        pwd2_regi2.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    pwd2_regi2.setText("");
                }
            }
        });

        phone_regi2.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    phone_regi2.setText("");
                }
            }
        });

        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        ab.setTitle("회원가입");

        Button btn_regi = (Button)findViewById(R.id.btn_regi);
        btn_regi.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {


                String name = name_regi2.getText().toString();
                String birth = birth_regi2.getText().toString();
                String id = id_regi2.getText().toString();
                String pwd = pwd_regi2.getText().toString();
                String pwd2 = pwd2_regi2.getText().toString();
                String phone = phone_regi2.getText().toString();

                if(pwd.equals(pwd2))
                {
                    insertoToDatabase(name, birth, id, pwd, phone);

                    Intent intent = new Intent(Register2Activity.this, Register_CommitActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }else
                    Toast.makeText(Register2Activity.this, "CHECK PASSWORD!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void insertoToDatabase(String Name, String Birth, String Id, String Pwd, String Phone) {
        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register2Activity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    String Name = (String) params[0];
                    String Birth = (String) params[1];
                    String Id = (String) params[2];
                    String Pwd = (String) params[3];
                    String Phone = (String) params[4];

                    //String link = "http://localhost/post.php";
                    String link = "http://172.30.1.6/post.php";
                    String data = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8");
                    data += "&" + URLEncoder.encode("Birth", "UTF-8") + "=" + URLEncoder.encode(Birth, "UTF-8");
                    data += "&" + URLEncoder.encode("Id", "UTF-8") + "=" + URLEncoder.encode(Id, "UTF-8");
                    data += "&" + URLEncoder.encode("Pwd", "UTF-8") + "=" + URLEncoder.encode(Pwd, "UTF-8");
                    data += "&" + URLEncoder.encode("Phone", "UTF-8") + "=" + URLEncoder.encode(Phone, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }
            }
        }
        InsertData task = new InsertData();
        task.execute(Name, Birth, Id, Pwd, Phone);
    }//insertToDatabase()

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
