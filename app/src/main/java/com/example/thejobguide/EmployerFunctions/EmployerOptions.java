package com.example.thejobguide.EmployerFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thejobguide.R;

public class EmployerOptions extends AppCompatActivity {


    private Button addJobBtn, findEmployBtn;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_options);

        userEmail = getIntent().getExtras().get("userEmail").toString();

        addJobBtn = (Button) findViewById(R.id.addJobBtnEOAct);
        findEmployBtn = (Button) findViewById(R.id.findEmployeeBtnEOAct);

        addJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployerOptions.this, AddJob.class);
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);
            }
        });

        findEmployBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployerOptions.this, FindEmployees.class);
                startActivity(intent);
            }
        });
    }
}
