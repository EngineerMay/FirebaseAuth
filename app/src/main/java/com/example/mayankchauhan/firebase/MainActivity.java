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


public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private EditText username;
    private EditText password;
    private Button button;
    private TextView txtview;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        txtview = (TextView) findViewById(R.id.txtview);
        username = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.pass_word);
        button = (Button) findViewById(R.id.button);

        if (firebaseAuth.getCurrentUser() != null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,ProfileActivity.class));
        }
        button.setOnClickListener(MainActivity.this);
        txtview.setOnClickListener(MainActivity.this);
    }

    private void registerUser()
    {
        String email = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Password empty", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("REGISTERING USER ...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    if (firebaseAuth.getCurrentUser() !=null)
                    {
                        finish();
                        startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "REGISTERATION UNSUCCESSFUL", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onClick(View v) {

        if ( v == button)
        {
            registerUser();
        }
        if(v == txtview)
        {
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}