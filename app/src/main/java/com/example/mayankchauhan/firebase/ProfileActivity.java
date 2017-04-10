package com.example.mayankchauhan.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView txtUSerEmail;
    private Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        txtUSerEmail = (TextView) findViewById(R.id.logEmail);
        txtUSerEmail.setText("Welcome" + user.getEmail());
        but  = (Button) findViewById(R.id.signout_button);
        but.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(ProfileActivity.this,LoginActivity.class));

    }
}
