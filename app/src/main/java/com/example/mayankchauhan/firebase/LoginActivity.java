package com.example.mayankchauhan.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etusername;
    private EditText etpassword;
    private Button signup;
    private TextView txtviewSignup;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null)
        {
            finish();
            startActivity(new Intent(LoginActivity.this,ProfileActivity.class));

        }


        etusername = (EditText) findViewById(R.id.user_name_signup);
        etpassword = (EditText) findViewById(R.id.pass_word_signup);
        signup = (Button) findViewById(R.id.buttonSignIn);
        txtviewSignup = (TextView) findViewById(R.id.txtviewSignUp);

        signup.setOnClickListener(this);
        txtviewSignup.setOnClickListener(this);

    }

    private void userLogin()
    {
        String eemail = etusername.getText().toString().trim();
        String epass = etpassword.getText().toString().trim();

        if (TextUtils.isEmpty(eemail))
        {
            Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(epass)) {
            Toast.makeText(this, "Password empty", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering please wait ...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(eemail,epass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful())
                {
                    finish();
                    startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
                }
            }
        });
    }
    @Override
    public void onClick(View v) {

        if(v == signup)
        {
            userLogin();
        }
        if(v == txtviewSignup)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
