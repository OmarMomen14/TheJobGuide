package com.example.thejobguide.JobSeekerFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.thejobguide.EmployerFunctions.AddJob;
import com.example.thejobguide.EmployerFunctions.AddNewJobDetails;
import com.example.thejobguide.R;

public class ViewJobs extends AppCompatActivity {

    private ImageView computerBtnIm, engineerBtnIm, medecineBtnIm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);

        computerBtnIm = (ImageView) findViewById(R.id.computerCategoryBtnVJAct);
        engineerBtnIm = (ImageView) findViewById(R.id.engineerCategoryBtnVJAct);
        medecineBtnIm = (ImageView) findViewById(R.id.medecineCategoryBtnVJAct);

        computerBtnIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobs.this, ViewJobsInField.class);
                intent.putExtra("field","computer");
                startActivity(intent);
            }
        });

        engineerBtnIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobs.this, ViewJobsInField.class);
                intent.putExtra("field","engineer");
                startActivity(intent);
            }
        });

        medecineBtnIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobs.this, ViewJobsInField.class);
                intent.putExtra("field","medicine");
                startActivity(intent);
            }
        });


    }
}
