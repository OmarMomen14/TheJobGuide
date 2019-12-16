package com.example.thejobguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thejobguide.JobSeekerFunctions.ViewJobs;
import com.example.thejobguide.LoginAndSignup.LogIn;
import com.example.thejobguide.LoginAndSignup.SignUp;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn, signupBtn, guestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = (Button) findViewById(R.id.loginBtnMainAct);
        signupBtn = (Button) findViewById(R.id.signupBtnMainAct);
        guestBtn = (Button) findViewById(R.id.guestBtnMainAct);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogIn.class);
                startActivity(intent);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        guestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewJobs.class);
                startActivity(intent);
            }
        });

    }


}
