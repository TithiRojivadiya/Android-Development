package com.example.sharedpref;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText uname, pwd;
    Button loginBtn;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.txtName);
        pwd = findViewById(R.id.txtPwd);
        loginBtn = findViewById(R.id.btnLogin);

        pref = getSharedPreferences("user_details", MODE_PRIVATE);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = uname.getText().toString().trim();
                String password = pwd.getText().toString().trim();

                if (username.equals("admin") && password.equals("admin")) {
                    Intent i = new Intent(MainActivity.this, LoadingActivity.class);
                    i.putExtra("username", username); // pass username
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Credentials are not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
