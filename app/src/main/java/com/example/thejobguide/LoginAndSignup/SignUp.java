package com.example.thejobguide.LoginAndSignup;

import androidx.annotation.NonNull;
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

import com.example.thejobguide.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    private Spinner roleDropDown;
    private EditText fnameField, lnameField, emailField, phoneField, passwordField;
    private Button signUpBtn;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        roleDropDown = (Spinner) findViewById(R.id.roleSpin);
        fnameField = (EditText) findViewById(R.id.fnameET);
        lnameField = (EditText) findViewById(R.id.lnameET);
        emailField = (EditText) findViewById(R.id.emailET);
        phoneField = (EditText) findViewById(R.id.phoneET);
        passwordField = (EditText) findViewById(R.id.passET);
        signUpBtn = (Button) findViewById(R.id.signupBtnSignAct);
        loadingBar = new ProgressDialog(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_dropdown_item);
        roleDropDown.setAdapter(adapter);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }

    private void signup() {

        String fname = fnameField.getText().toString();
        String lname = lnameField.getText().toString();
        String email = emailField.getText().toString();
        email = encodeEmail(email);
        String phone = phoneField.getText().toString();
        String password = passwordField.getText().toString();
        String role = roleDropDown.getSelectedItem().toString();

        if(TextUtils.isEmpty(fname))
            Toast.makeText(this,"Please fill in your first name",Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(lname))
            Toast.makeText(this,"Please fill in your last name",Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(email))
            Toast.makeText(this,"Please fill in your email",Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(phone))
            Toast.makeText(this,"Please fill in your phone number",Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(password))
            Toast.makeText(this,"Please fill in your password",Toast.LENGTH_SHORT).show();
        else {

            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validateCredentials(fname, lname, email, phone, password, role);
        }
    }

    private void validateCredentials(final String fname, final String lname, final String email, final String phone, final String password, final String role) {

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (!dataSnapshot.child("Users").child(email).exists()){

                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("first name", fname);
                    userDataMap.put("last name", lname);
                    userDataMap.put("email", email);
                    userDataMap.put("phone", phone);
                    userDataMap.put("password", password);
                    userDataMap.put("role", role);

                    rootRef.child("Users").child(email).updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(SignUp.this, "Congratulations, your account is created successfully",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent2 = new Intent(SignUp.this, LogIn.class);
                                startActivity(intent2);
                            } else {

                                loadingBar.dismiss();
                                Toast.makeText(SignUp.this, "Network error, try again after some time",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                } else {

                    Toast.makeText(SignUp.this,"This email address "+email+" already exists, Please Try logging in with this email",Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();

                    Intent intent = new Intent(SignUp.this, LogIn.class);
                    startActivity(intent);
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
