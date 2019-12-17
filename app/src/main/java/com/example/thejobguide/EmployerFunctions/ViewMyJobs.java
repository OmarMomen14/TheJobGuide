package com.example.thejobguide.EmployerFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thejobguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewMyJobs extends AppCompatActivity {

    private ListView simpleList;
    private ArrayList<String> listText = new ArrayList<String>();
    private  ArrayAdapter AppAdapter;

    private String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_jobs);


        userEmail = getIntent().getExtras().get("userEmail").toString();
        simpleList = (ListView)findViewById(R.id.myJobsListView);

        fillTheList(userEmail, simpleList);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myintent = new Intent(getBaseContext(),ViewJobDetails.class);
                myintent.putExtra("key",position);
                startActivity(myintent);
            }
        });

    }




    private void fillTheList(final String userEmail, final ListView simpleList) {

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        ValueEventListener listener = rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.child("Jobs").getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    String owner = next.child("owner").getValue().toString();
                    if (owner.equals(userEmail)) {
                        String job = next.child("title").getValue().toString();
                        listText.add(job);
                    }
                }

                if (listText.isEmpty()){
                    listText.add("No jobs added yet.");
                    AppAdapter = new ArrayAdapter<>(ViewMyJobs.this, android.R.layout.simple_list_item_1, listText);
                    simpleList.setAdapter(AppAdapter);
                } else {
                    AppAdapter = new ArrayAdapter<>(ViewMyJobs.this, android.R.layout.simple_list_item_1, listText);
                    simpleList.setAdapter(AppAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rootRef.removeEventListener(listener);
        return;

    }



}
