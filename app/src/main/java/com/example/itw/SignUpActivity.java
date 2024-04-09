package com.example.itw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    EditText name;
    EditText username;
    EditText password;
    Button signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        FileOutputStream fos = openFileOutput("user_data.csv", MODE_PRIVATE);
                        OutputStreamWriter osw = new OutputStreamWriter(fos);
                        osw.write(name.getText().toString() + "," + username.getText().toString() + "," + password.getText().toString() + "\n");
                        osw.flush();
                        osw.close();
                        Toast.makeText(SignUpActivity.this, "Registration Successful!, Login now!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(SignUpActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        TextView loginText = findViewById(R.id.loginText);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}