package com.example.aisha_madsemesterproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    TextView tvRegister;
    DatabaseHelper db;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        tvRegister = (TextView)findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                registerIntent.setFlags(registerIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(registerIntent);
            }
        });


    }
    public void loginBtn(View view){
        String user = etUsername.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();
        boolean res = db.checkUser(user,pass);
        if(res == true){
            Toast.makeText(this,"successfully logged in",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username",user);
            editor.putString("pass",pass);
            editor.apply();

            Intent intent = new Intent(MainActivity.this,MainMenuActivity.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"log in failed",Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();


        String username = preferences.getString("username","");
        String pass = preferences.getString("pass","");

        if(username!="" && pass !="")
        {
            Intent intent =  new Intent(this,MainMenuActivity.class);
            startActivity(intent);
        }
    }
}
