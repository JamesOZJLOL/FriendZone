package com.example.friendzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCancel;
    private Button buttonSave;
    private EditText editTextUsername;
    private EditText editTextInterest;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextInterest = (EditText) findViewById(R.id.editTextInterest);

        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);

        buttonSave.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    private void save(){
        String username = editTextUsername.getText().toString().trim();
        String interest = editTextInterest.getText().toString().trim();

        UserInfo userInfo = new UserInfo(username, interest);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInfo);

        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        if(v == buttonSave){
            save();
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }
        if(v == buttonCancel){
            finish();
            startActivity(new Intent(this, HomeActivity.class));

        }
    }
}
