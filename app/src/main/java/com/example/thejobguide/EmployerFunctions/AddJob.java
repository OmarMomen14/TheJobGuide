package com.example.thejobguide.EmployerFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.thejobguide.R;

public class AddJob extends AppCompatActivity {

    private ImageView software, datascience, game, networks, security, database, blockchain, computervision, robot;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        userEmail = getIntent().getExtras().get("userEmail").toString();

        software = (ImageView) findViewById(R.id.softwareBtnAJAct);
        datascience = (ImageView) findViewById(R.id.datascienceAJAct);
        game = (ImageView) findViewById(R.id.gameBtnAJAct);
        networks = (ImageView) findViewById(R.id.networksBtnAJAct);
        security = (ImageView) findViewById(R.id.securityBtnAJAct);
        database = (ImageView) findViewById(R.id.databaseBtnAJAct);
        blockchain = (ImageView) findViewById(R.id.blockchainBtnAJAct);
        computervision = (ImageView) findViewById(R.id.copmutervisionBtnAJAct);
        robot = (ImageView) findViewById(R.id.robotBtnAJAct);

        software.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJob.this, AddNewJobDetails.class);
                intent.putExtra("field","Software Development");
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);
            }
        });

        datascience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJob.this, AddNewJobDetails.class);
                intent.putExtra("field","Data Science");
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJob.this, AddNewJobDetails.class);
                intent.putExtra("field","Game Development");
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);
            }
        });


        networks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJob.this, AddNewJobDetails.class);
                intent.putExtra("field","Networks");
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);
            }
        });

        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJob.this, AddNewJobDetails.class);
                intent.putExtra("field","Security");
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);
            }
        });

        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJob.this, AddNewJobDetails.class);
                intent.putExtra("field","Database");
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);
            }
        });


        blockchain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJob.this, AddNewJobDetails.class);
                intent.putExtra("field","Blockchain");
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);
            }
        });

        computervision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJob.this, AddNewJobDetails.class);
                intent.putExtra("field","Computer Vision");
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);
            }
        });

        robot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJob.this, AddNewJobDetails.class);
                intent.putExtra("field","Robotics");
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);
            }
        });
    }
}
