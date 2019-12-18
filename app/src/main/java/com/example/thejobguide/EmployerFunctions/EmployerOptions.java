package com.example.thejobguide.EmployerFunctions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.thejobguide.MainActivity;
import com.example.thejobguide.R;

public class EmployerOptions extends AppCompatActivity {


    private Button addJobBtn, findEmployBtn, viewMyJobsBtn;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_options);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.TB);
        setSupportActionBar(myToolbar);

        userEmail = getIntent().getExtras().get("userEmail").toString();

        addJobBtn = (Button) findViewById(R.id.addJobBtnEOAct);
        findEmployBtn = (Button) findViewById(R.id.findEmployeeBtnEOAct);
        viewMyJobsBtn = (Button) findViewById(R.id.viewJobsBtnEOAct);

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

        viewMyJobsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployerOptions.this, ViewMyJobs.class);
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.logout:
                Intent intent = new Intent(EmployerOptions.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
