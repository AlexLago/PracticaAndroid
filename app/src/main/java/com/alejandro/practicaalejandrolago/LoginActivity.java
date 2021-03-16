package com.alejandro.practicaalejandrolago;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText txtLoginEmail;
    private EditText txtLoginPass;
    private Button btnLogin;
    private Button btnBack;

    private String loginEmail = "";
    private String loginContraseña = "";

    FirebaseAuth loginAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginAuth = FirebaseAuth.getInstance();

        txtLoginEmail = (EditText) findViewById(R.id.txtLoginEmail);
        txtLoginPass = (EditText) findViewById(R.id.txtLoginPass);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnBack.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, AuthActivity.class));
                finish();
            }
        }));

        btnLogin.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginEmail = txtLoginEmail.getText().toString();
                loginContraseña = txtLoginPass.getText().toString();

                if(loginEmail.isEmpty() || loginContraseña.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Debes completar los campos", Toast.LENGTH_SHORT);
                    return;
                }

                loginAuth.signInWithEmailAndPassword(loginEmail, loginContraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, ImagenActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "No se pudo iniciar sesión", Toast.LENGTH_SHORT);
                        }
                    }
                });
            }
        }));
    }
}