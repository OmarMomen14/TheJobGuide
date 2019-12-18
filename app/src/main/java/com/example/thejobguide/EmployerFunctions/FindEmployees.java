package com.example.thejobguide.EmployerFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.thejobguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class FindEmployees extends AppCompatActivity {
    private ListView simpleList;
    private ArrayList<String> listText = new ArrayList<String>();
    private ArrayList<String> namesKeys = new ArrayList<String>();
    private ArrayAdapter AppAdapter;
    private EditText Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_employees);
        simpleList = (ListView)findViewById(R.id.jobsInFieldListView);


        fetchDataForField(simpleList);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myintent = new Intent(getBaseContext(), FindEmployeesDetails.class);
                String jobKey = namesKeys.get(position);
                myintent.putExtra("name",jobKey);
                startActivity(myintent);
            }
        });
        Search = (EditText)findViewById(R.id.search);
        Search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                (FindEmployees.this).AppAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
                // TODO Auto-generated method stub

            }


            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

    }
    private void fetchDataForField(final ListView simpleList) {

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        ValueEventListener listener = rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.child("Users").getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                        String name = next.child("first name").getValue().toString();
                        String key = next.getKey();
                        listText.add(name);
                        namesKeys.add(key);
                    }


                if (listText.isEmpty()){
                    listText.add("No users yet.");
                    AppAdapter = new ArrayAdapter<>(FindEmployees.this, android.R.layout.simple_list_item_1, listText);
                    simpleList.setAdapter(AppAdapter);
                }
                else {
                    AppAdapter = new ArrayAdapter<>(FindEmployees.this, android.R.layout.simple_list_item_1, listText);
                    simpleList.setAdapter(AppAdapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
