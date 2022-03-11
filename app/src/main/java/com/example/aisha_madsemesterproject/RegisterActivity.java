package com.example.aisha_madsemesterproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    EditText etCnfPassword;
    Button btnRegister;
    TextView tvlogin;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        etUsername = (EditText)findViewById(R.id.etUsername);

        etPassword = (EditText)findViewById(R.id.etPassword);
        etCnfPassword = (EditText)findViewById(R.id.etCnfPassword);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        tvlogin = (TextView)findViewById(R.id.tvlogin);
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(RegisterActivity.this,MainActivity.class);
                registerIntent.setFlags(registerIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(registerIntent);
            }
        });
    }
    public void btnRegisterClicked(View view){
        String user = etUsername.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();
        String cnfpass = etCnfPassword.getText().toString().trim();
        boolean res = db.checkUser(user,pass);
        if(pass.equals(cnfpass)){
            long val= db.addUser(user,pass);
            if (val > 0) {
                Toast.makeText(this,"You have registered",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);

            }
            else{
                Toast.makeText(this,"Registration Error ",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this,"Passwords not Matching ",Toast.LENGTH_SHORT).show();
        }

    }

}
