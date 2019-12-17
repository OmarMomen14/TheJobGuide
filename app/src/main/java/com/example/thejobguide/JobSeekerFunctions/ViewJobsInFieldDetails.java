package com.example.thejobguide.JobSeekerFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thejobguide.EmployerFunctions.ViewJobDetails;
import com.example.thejobguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewJobsInFieldDetails extends AppCompatActivity {

    private String jobID;
    private TextView titleTV, descTV, salaryTV, fieldTv;
    private ListView tasksListView, skillsListView;
    private ArrayList<String> skillsListText = new ArrayList<String>();
    private ArrayList<String> tasksListText = new ArrayList<String>();
    private ArrayAdapter skillsAdapter, tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs_in_field_details);

        jobID = getIntent().getExtras().get("key").toString();
        titleTV = (TextView) findViewById(R.id.jobTitleTvVJIFA);
        descTV = (TextView) findViewById(R.id.jobDescTvVJIFA);
        fieldTv = (TextView) findViewById(R.id.jobFieldTvVJIFA);
        salaryTV = (TextView) findViewById(R.id.jobSalaryTvVJIFA);
        skillsListView = (ListView) findViewById(R.id.skillsListViewVJIFA);
        tasksListView = (ListView) findViewById(R.id.tasksListViewVJIFA);

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

                skillsAdapter = new ArrayAdapter<>(ViewJobsInFieldDetails.this, android.R.layout.simple_list_item_1, skillsListText);
                skillsListView.setAdapter(skillsAdapter);


                Iterable<DataSnapshot> snapshotIterator2 = dataSnapshot.child("Jobs").child(jobID).child("tasks").getChildren();
                Iterator<DataSnapshot> iterator2 = snapshotIterator2.iterator();

                while (iterator2.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator2.next();
                    String theDescription = next.child("taskDesc").getValue().toString();

                    tasksListText.add(theDescription);
                }

                tasksAdapter = new ArrayAdapter<>(ViewJobsInFieldDetails.this, android.R.layout.simple_list_item_1, tasksListText);
                tasksListView.setAdapter(tasksAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
