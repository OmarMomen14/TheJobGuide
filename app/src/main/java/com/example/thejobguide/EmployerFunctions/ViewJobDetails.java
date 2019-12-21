package com.example.thejobguide.EmployerFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thejobguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewJobDetails extends AppCompatActivity {

    private String jobID, userEmail;
    private TextView titleTV, descTV, salaryTV, fieldTv;
    private ListView tasksListView, skillsListView;
    private ArrayList<String> skillsListText = new ArrayList<String>();
    private ArrayList<String> tasksListText = new ArrayList<String>();
    private ArrayAdapter skillsAdapter, tasksAdapter;
    private Button editBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job_details);

        jobID = getIntent().getExtras().get("key").toString();
        titleTV = (TextView) findViewById(R.id.jobTitleTvVMJA);
        descTV = (TextView) findViewById(R.id.jobDescTvVMJA);
        fieldTv = (TextView) findViewById(R.id.jobFieldTvVMJA);
        salaryTV = (TextView) findViewById(R.id.jobSalaryTvVMJA);
        skillsListView = (ListView) findViewById(R.id.skillsListView);
        tasksListView = (ListView) findViewById(R.id.tasksListView);
        editBtn = (Button) findViewById(R.id.editBtnVJDA);
        deleteBtn = (Button) findViewById(R.id.deleteBtnVJDA);
        userEmail = getIntent().getExtras().get("userEmail").toString();


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewJobDetails.this);
                builder.setCancelable(true);
                builder.setTitle("Delete Job");
                builder.setMessage("Are you sure you want to delete this job?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final DatabaseReference rootRef;
                                rootRef = FirebaseDatabase.getInstance().getReference();
                                rootRef.child("Jobs").child(jobID).removeValue();
                                Intent intent = new Intent(ViewJobDetails.this, ViewMyJobs.class);
                                intent.putExtra("userEmail",userEmail);
                                startActivity(intent);
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewJobDetails.this, EditJob.class);
                intent.putExtra("userEmail",userEmail);
                intent.putExtra("key",jobID);
                startActivity(intent);
            }
        });

        fetchJobData(jobID);


    }

    private void fetchJobData(final String jobID) {

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        ValueEventListener listener = rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String title = dataSnapshot.child("Jobs").child(jobID).child("title").getValue().toString();
                String description = dataSnapshot.child("Jobs").child(jobID).child("description").getValue().toString();
                String salary = dataSnapshot.child("Jobs").child(jobID).child("salary").getValue().toString();
                String field = dataSnapshot.child("Jobs").child(jobID).child("field").getValue().toString();

                titleTV.setText(title);
                descTV.setText(description);
                salaryTV.setText(salary);
                fieldTv.setText(field);

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.child("Jobs").child(jobID).child("skills").getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    String skillTitle = next.child("name").getValue().toString();
                    String skillRate = next.child("rate").getValue().toString();
                    String skillProjects = next.child("projects").getValue().toString();
                    String skillCert = next.child("certs").getValue().toString();

                    String skillAllText = skillTitle + "\n" +
                                          "Strength Level: " + skillRate + "\n" +
                                          "Suggested projects: " + skillProjects + "\n" +
                                          "Reccommended Certificates: " + skillCert;

                    skillsListText.add(skillAllText);


                }

                skillsAdapter = new ArrayAdapter<>(ViewJobDetails.this, R.layout.list_text2, skillsListText);
                skillsListView.setAdapter(skillsAdapter);


                Iterable<DataSnapshot> snapshotIterator2 = dataSnapshot.child("Jobs").child(jobID).child("tasks").getChildren();
                Iterator<DataSnapshot> iterator2 = snapshotIterator2.iterator();

                while (iterator2.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator2.next();
                    String theDescription = next.child("taskDesc").getValue().toString();

                    tasksListText.add(theDescription);
                }

                tasksAdapter = new ArrayAdapter<>(ViewJobDetails.this, R.layout.list_text2, tasksListText);
                tasksListView.setAdapter(tasksAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
