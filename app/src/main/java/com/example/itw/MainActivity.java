package com.example.itw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;
import android.text.TextUtils;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;

    CheckBox captcha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        captcha = findViewById(R.id.captcha);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) || !captcha.isChecked()) {
                    Toast.makeText(MainActivity.this, "All fields compulsory!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        FileInputStream fis = openFileInput("user_data.csv");
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);
                        String line;
                        Boolean flag = false;

                        while ((line = br.readLine()) != null) {
                            String[] parts = line.split(",");
                            String db_name = parts[0];
                            String db_username = parts[1];
                            String db_password = parts[2];

                            if (db_username.equals(username.getText().toString()) && db_password.equals(password.getText().toString())) {
                                Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                                intent.putExtra("name", db_name);
                                startActivity(intent);
                                flag = true;
                            }
                        }
                        if (!flag) {
                            Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error reading file!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        TextView signupText = findViewById(R.id.signupText);

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}