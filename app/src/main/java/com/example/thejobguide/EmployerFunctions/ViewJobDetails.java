package com.example.thejobguide.EmployerFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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

    private String jobID;
    private TextView titleTV, descTV;
    private ListView tasksListView, skillsListView;
    private ArrayList<String> skillsListText = new ArrayList<String>();
    private ArrayList<String> tasksListText = new ArrayList<String>();
    private ArrayAdapter skillsAdapter, tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job_details);

        jobID = getIntent().getExtras().get("key").toString();
        titleTV = (TextView) findViewById(R.id.jobTitleTvVMJA);
        descTV = (TextView) findViewById(R.id.jobDescTvVMJA);
        skillsListView = (ListView) findViewById(R.id.skillsListView);
        tasksListView = (ListView) findViewById(R.id.tasksListView);

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

                titleTV.setText(title);
                descTV.setText(description);

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

                skillsAdapter = new ArrayAdapter<>(ViewJobDetails.this, android.R.layout.simple_list_item_1, skillsListText);
                skillsListView.setAdapter(skillsAdapter);


                Iterable<DataSnapshot> snapshotIterator2 = dataSnapshot.child("Jobs").child(jobID).child("tasks").getChildren();
                Iterator<DataSnapshot> iterator2 = snapshotIterator2.iterator();

                while (iterator2.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator2.next();
                    String theDescription = next.child("taskDesc").getValue().toString();

                    tasksListText.add(theDescription);
                }

                tasksAdapter = new ArrayAdapter<>(ViewJobDetails.this, android.R.layout.simple_list_item_1, tasksListText);
                tasksListView.setAdapter(tasksAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
