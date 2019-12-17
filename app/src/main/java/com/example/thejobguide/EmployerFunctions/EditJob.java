package com.example.thejobguide.EmployerFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thejobguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditJob extends AppCompatActivity {

    private String jobID, userEmail;
    private EditText jobTitleET, jobDescET, jobSalaryET;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job);


        userEmail = getIntent().getExtras().get("userEmail").toString();
        jobID = getIntent().getExtras().get("key").toString();
        jobTitleET = (EditText) findViewById(R.id.jobTitleETEJA);
        jobDescET = (EditText) findViewById(R.id.jobDescETEJA);
        jobSalaryET = (EditText) findViewById(R.id.jobSalaryETEJA);
        editBtn = (Button) findViewById(R.id.editBtnEJA);


        fillFields(jobID);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference rootRef;
                rootRef = FirebaseDatabase.getInstance().getReference();

                String title = jobTitleET.getText().toString();
                String desc = jobDescET.getText().toString();
                String salary = jobSalaryET.getText().toString();

                rootRef.child("Jobs").child(jobID).child("title").setValue(title);
                rootRef.child("Jobs").child(jobID).child("description").setValue(desc);
                rootRef.child("Jobs").child(jobID).child("salary").setValue(salary);

                Toast.makeText(EditJob.this,"The job has been edited.",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditJob.this, ViewMyJobs.class);
                intent.putExtra("userEmail",userEmail);
                startActivity(intent);

            }
        });

    }

    private void fillFields(final String jobID) {

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String title = dataSnapshot.child("Jobs").child(jobID).child("title").getValue().toString();
                jobTitleET.setText(title);

                String desc = dataSnapshot.child("Jobs").child(jobID).child("description").getValue().toString();
                jobDescET.setText(desc);

                String salary = dataSnapshot.child("Jobs").child(jobID).child("salary").getValue().toString();
                jobSalaryET.setText(salary);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
