package com.example.thejobguide.JobSeekerFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thejobguide.R;

public class JobSeekerOptions extends AppCompatActivity {

    private Button viewJobsBtn, editCVBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_options);

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
}
