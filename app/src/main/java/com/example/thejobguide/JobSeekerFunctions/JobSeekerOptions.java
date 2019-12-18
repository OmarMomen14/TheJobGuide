package com.example.thejobguide.JobSeekerFunctions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.thejobguide.EmployerFunctions.EmployerOptions;
import com.example.thejobguide.MainActivity;
import com.example.thejobguide.R;

public class JobSeekerOptions extends AppCompatActivity {

    private Button viewJobsBtn, editCVBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_options);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.TB);
        setSupportActionBar(myToolbar);

        viewJobsBtn = (Button) findViewById(R.id.viewJobsBtnJSOAct);
        editCVBtn = (Button) findViewById(R.id.editCVBtnJSOAct);

        viewJobsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobSeekerOptions.this, ViewJobs.class);
                startActivity(intent);
            }
        });

        editCVBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobSeekerOptions.this, EditCV.class);
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
                Intent intent = new Intent(JobSeekerOptions.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
