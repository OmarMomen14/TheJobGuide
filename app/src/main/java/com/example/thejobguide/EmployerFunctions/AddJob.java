package com.example.thejobguide.EmployerFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.thejobguide.R;

public class AddJob extends AppCompatActivity {

    private ImageView computerBtnIm, engineerBtnIm, medecineBtnIm;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        userEmail = getIntent().getExtras().get("userEmail").toString();

        computerBtnIm = (ImageView) findViewById(R.id.computerCategoryBtnAJAct);
        engineerBtnIm = (ImageView) findViewById(R.id.engineerCategoryBtnAJAct);
        medecineBtnIm = (ImageView) findViewById(R.id.medecineCategoryBtnAJAct);

        computerBtnIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJob.this, AddNewJobDetails.class);
                intent.putExtra("field","computer");
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);
            }
        });

        engineerBtnIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJob.this, AddNewJobDetails.class);
                intent.putExtra("field","engineer");
                startActivity(intent);
            }
        });

        medecineBtnIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJob.this, AddNewJobDetails.class);
                intent.putExtra("field","medicine");
                startActivity(intent);
            }
        });
    }
}
