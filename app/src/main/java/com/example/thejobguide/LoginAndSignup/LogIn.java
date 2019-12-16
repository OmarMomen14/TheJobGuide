package com.example.thejobguide.LoginAndSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thejobguide.EmployerFunctions.EmployerOptions;
import com.example.thejobguide.JobSeekerFunctions.JobSeekerOptions;
import com.example.thejobguide.Models.User;
import com.example.thejobguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {

    private Spinner roleDropDown;
    private EditText emailField, passwordField;
    private Button loginBtn;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        roleDropDown = (Spinner) findViewById(R.id.roleSpin);
        emailField = (EditText) findViewById(R.id.emailET);
        passwordField = (EditText) findViewById(R.id.passET);
        loginBtn = (Button) findViewById(R.id.loginBtnLogAct);
        loadingBar = new ProgressDialog(this);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_dropdown_item);
        roleDropDown.setAdapter(adapter);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    private void login() {

        String email = emailField.getText().toString();
        email = encodeEmail(email);
        String password = passwordField.getText().toString();
        String role = roleDropDown.getSelectedItem().toString();

        if(TextUtils.isEmpty(email))
            Toast.makeText(this,"Please fill in your email",Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(password))
            Toast.makeText(this,"Please fill in your password",Toast.LENGTH_SHORT).show();
        else {

            loadingBar.setTitle("Log In");
            loadingBar.setMessage("Please wait while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validateCredentials(email, password, role);
        }
    }

    private void validateCredentials(final String email, final String password, final String role) {

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Users").child(email).exists()) {

                    User userData = dataSnapshot.child("Users").child(email).getValue(User.class);

                    if (userData.getPassword().equals(password)){

                        if (userData.getRole().equals(role)){

                            if(role.equals("Job Seeker")) {
                                loadingBar.dismiss();
                                Toast.makeText(LogIn.this, "You logged in successfully",Toast.LENGTH_LONG).show();
                                Intent intent2 = new Intent(LogIn.this, JobSeekerOptions.class);
                                startActivity(intent2);

                            } else {
                                loadingBar.dismiss();
                                Toast.makeText(LogIn.this, "You logged in successfully",Toast.LENGTH_LONG).show();
                                Intent intent2 = new Intent(LogIn.this, EmployerOptions.class);
                                startActivity(intent2);
                            }
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(LogIn.this, "This email is registered with another role, please change the selected role...",Toast.LENGTH_LONG).show();
                        }
                    } else {
                        loadingBar.dismiss();
                        Toast.makeText(LogIn.this, "Password is incorrect...",Toast.LENGTH_LONG).show();
                    }

                } else {

                    loadingBar.dismiss();
                    Toast.makeText(LogIn.this, "This email doesn't exist, please sign up...",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static String encodeEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    public static String decodeEmail(String userEmail) {
        return userEmail.replace(",", ".");
    }


}
