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

    private ImageView software, datascience, game, networks, security, database, blockchain, computervision, robot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);

        software = (ImageView) findViewById(R.id.softwareBtnAJAct2);
        datascience = (ImageView) findViewById(R.id.datascienceAJAct2);
        game = (ImageView) findViewById(R.id.gameBtnAJAct2);
        networks = (ImageView) findViewById(R.id.networksBtnAJAct2);
        security = (ImageView) findViewById(R.id.securityBtnAJAct2);
        database = (ImageView) findViewById(R.id.databaseBtnAJAct2);
        blockchain = (ImageView) findViewById(R.id.blockchainBtnAJAct2);
        computervision = (ImageView) findViewById(R.id.copmutervisionBtnAJAct2);
        robot = (ImageView) findViewById(R.id.robotBtnAJAct2);

        software.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobs.this, ViewJobsInField.class);
                intent.putExtra("field","Software Development");
                startActivity(intent);
            }
        });

        datascience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobs.this, ViewJobsInField.class);
                intent.putExtra("field","Data Science");
                startActivity(intent);
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobs.this, ViewJobsInField.class);
                intent.putExtra("field","Game Development");
                startActivity(intent);
            }
        });


        networks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobs.this, ViewJobsInField.class);
                intent.putExtra("field","Networks");
                startActivity(intent);
            }
        });

        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobs.this, ViewJobsInField.class);
                intent.putExtra("field","Security");
                startActivity(intent);
            }
        });

        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobs.this, ViewJobsInField.class);
                intent.putExtra("field","Database");
                startActivity(intent);
            }
        });


        blockchain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobs.this, ViewJobsInField.class);
                intent.putExtra("field","Blockchain");
                startActivity(intent);
            }
        });

        computervision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobs.this, ViewJobsInField.class);
                intent.putExtra("field","Computer Vision");
                startActivity(intent);
            }
        });

        robot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobs.this, ViewJobsInField.class);
                intent.putExtra("field","Robotics");
                startActivity(intent);
            }
        });


    }
}
