package com.tcs.sharedpreferencedemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditUserName;
    private EditText mEditPassword;

    private Button mBtnLogin;

    public static final String PREFERENCE = "ILPPreference";

    public static final String USER_NAME = "username";
    public static final String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("SharedPreference Demo");
        toolbar.setTitleTextColor(Color.WHITE);
        mEditUserName = (EditText) findViewById(R.id.edt_uname);
        mEditPassword = (EditText) findViewById(R.id.edt_pwd);

        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.btn_login:

                if (!isEmpty(mEditUserName) && !isEmpty(mEditPassword)) {

                    //Getting shared preference instance
                    SharedPreferences preferences = getSharedPreferences(PREFERENCE, MODE_PRIVATE);

                    //getting editor instance
                    SharedPreferences.Editor editor = preferences.edit();

                    //inserting values in to shared preference.
                    editor.putString(USER_NAME, mEditUserName.getText().toString());
                    editor.putString(PASSWORD, mEditPassword.getText().toString());

                    //Saving the changes to shared preference.
                    editor.commit();

                    Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(this,HomeActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isEmpty(EditText e) {

        if (e == null) {
            return true;
        } else if (e.getText().toString().trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
