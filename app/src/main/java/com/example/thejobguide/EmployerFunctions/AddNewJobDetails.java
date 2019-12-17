package com.example.thejobguide.EmployerFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thejobguide.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AddNewJobDetails extends AppCompatActivity {

    private String fieldName, userEmail;
    private EditText jobTitleET, jobDescET, jobTaskET, jobSkillNameET, jobSkillRateET, jobSkillProjectET, jobSkillCertET, jobSalaryET;
    private Button addTaskBtn, addSkillBtn, addJobBtn;
    private TextView taskDetailsTV, skillsDetailsTV;
    private ProgressDialog loadingBar;
    ArrayList<String> tasksArray = new ArrayList<String>();
    ArrayList<String> skillsArray = new ArrayList<String>();
    ArrayList<String> skillsRateArray = new ArrayList<String>();
    ArrayList<String> skillsProjectsArray = new ArrayList<String>();
    ArrayList<String> skillsCertsArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_job_details);

        fieldName = getIntent().getExtras().get("field").toString();
        userEmail = getIntent().getExtras().get("userEmail").toString();

        jobTitleET = (EditText) findViewById(R.id.jobTitleET);
        jobDescET = (EditText) findViewById(R.id.jobDescET);
        jobTaskET = (EditText) findViewById(R.id.jobTasksET);
        jobSkillNameET = (EditText) findViewById(R.id.jobSkillsET);
        jobSkillRateET = (EditText) findViewById(R.id.jobSkillsRateET);
        jobSkillProjectET = (EditText) findViewById(R.id.jobSkillsProjectsET);
        jobSkillCertET = (EditText) findViewById(R.id.jobSkillsCertET);
        jobSalaryET = (EditText) findViewById(R.id.jobSalaryET);

        addTaskBtn = (Button) findViewById(R.id.addTaskBtn);
        addSkillBtn = (Button) findViewById(R.id.addSkillBtn);
        addJobBtn = (Button) findViewById(R.id.addJobToDbBtn);

        taskDetailsTV = (TextView) findViewById(R.id.jobTasksDetailsTV);
        skillsDetailsTV = (TextView) findViewById(R.id.jobSkillsDetailsTV);

        loadingBar = new ProgressDialog(this);

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentDetails = taskDetailsTV.getText().toString();
                String task = jobTaskET.getText().toString();

                if(TextUtils.isEmpty(task))
                    Toast.makeText(AddNewJobDetails.this
                            ,"Please fill in the task",Toast.LENGTH_SHORT).show();
                else {
                    tasksArray.add(task);
                    if (currentDetails.equals("No tasks added yet.")) {
                        taskDetailsTV.setText(task);
                    } else {
                        String newDetails = currentDetails + "\n" + task;
                        taskDetailsTV.setText(newDetails);
                    }
                    jobTaskET.setText("");
                }
            }
        });




        addSkillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDetails = skillsDetailsTV.getText().toString();
                String skill = jobSkillNameET.getText().toString();
                String skillRate = jobSkillRateET.getText().toString();
                String skillProj = jobSkillProjectET.getText().toString();
                String skillCert = jobSkillCertET.getText().toString();

                if(TextUtils.isEmpty(skill))
                    Toast.makeText(AddNewJobDetails.this
                            ,"Please fill in the skill name",Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(skillRate))
                    Toast.makeText(AddNewJobDetails.this
                            ,"Please fill in the skill rate",Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(skillProj))
                    Toast.makeText(AddNewJobDetails.this
                            ,"Please fill in the skill project",Toast.LENGTH_SHORT).show();
                else {
                    if(TextUtils.isEmpty(skillCert))
                        skillCert = "none";
                    skillsArray.add(skill);
                    skillsRateArray.add(skillRate);
                    skillsProjectsArray.add(skillProj);
                    skillsCertsArray.add(skillCert);
                    if (currentDetails.equals("No skills added yet.")) {
                        skillsDetailsTV.setText(skill);
                    } else {
                        String newDetails = currentDetails + "\n" + skill;
                        skillsDetailsTV.setText(newDetails);
                    }
                    jobSkillNameET.setText("");
                    jobSkillRateET.setText("");
                    jobSkillProjectET.setText("");
                    jobSkillCertET.setText("");
                }
            }
        });

        addJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJobToDatabase();
            }
        });

    }

    private void addJobToDatabase() {

        String jobTitle = jobTitleET.getText().toString();
        String jobDesc = jobDescET.getText().toString();
        String jobTasks = taskDetailsTV.getText().toString();
        String jobSkills = skillsDetailsTV.getText().toString();
        String jobSalary = jobSalaryET.getText().toString();

        if(TextUtils.isEmpty(jobTitle))
            Toast.makeText(AddNewJobDetails.this,"Please fill in the job title",Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(jobDesc))
            Toast.makeText(AddNewJobDetails.this,"Please fill in the job description",Toast.LENGTH_SHORT).show();
        else if(jobTasks.split("[\n|\r]").length < 2)
            Toast.makeText(this,"Please add at least 2 tasks ",Toast.LENGTH_SHORT).show();
        else if(jobSkills.split("[\n|\r]").length < 3)
            Toast.makeText(this,"Please add at least 3 skills",Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(jobSalary))
            Toast.makeText(this,"Please fill in job salary",Toast.LENGTH_SHORT).show();
        else {

            proccessAddingJob(jobTitle, jobDesc, jobSalary);
            Intent intent = new Intent(AddNewJobDetails.this, EmployerOptions.class);
            intent.putExtra("userEmail",userEmail);
            startActivity(intent);
        }
    }

    private void proccessAddingJob(String title, String desc, String salary) {


        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> jobDataMap = new HashMap<>();
        jobDataMap.put("title", title);
        jobDataMap.put("description", desc);
        jobDataMap.put("salary", salary);
        jobDataMap.put("field", fieldName);
        jobDataMap.put("owner", userEmail);



        DatabaseReference  newJobref = rootRef.child("Jobs").push();
        String newJobKey = newJobref.getKey();
        newJobref.setValue(jobDataMap);


        for (int i=0; i<skillsArray.size(); i++) {

            String skillName = skillsArray.get(i);
            String skillRate = skillsRateArray.get(i);
            String skillProj = skillsProjectsArray.get(i);
            String skillCert = skillsCertsArray.get(i);

            HashMap<String, Object> skillMap = new HashMap<>();
            skillMap.put("name", skillName);
            skillMap.put("rate", skillRate);
            skillMap.put("projects", skillProj);
            skillMap.put("certs", skillCert);
            
            rootRef.child("Jobs").child(newJobKey).child("skills").child(skillName).updateChildren(skillMap);
        }

        
        
        for (int i=0; i<tasksArray.size(); i++) {

            String task = tasksArray.get(i);

            HashMap<String, Object> taskMap = new HashMap<>();
            taskMap.put("taskDesc", task);
            String key = Integer.toString(i);
            
            rootRef.child("Jobs").child(newJobKey).child("tasks").child(key).updateChildren(taskMap);
        }

        Toast.makeText(this,"A new job has been added.",Toast.LENGTH_LONG).show();
    }
}
