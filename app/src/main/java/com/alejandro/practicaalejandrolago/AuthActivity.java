 package com.alejandro.practicaalejandrolago;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

 public class AuthActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtEmail;
    private EditText txtContraseña;
    private Button btnRegistrarse;

    // Variables de los datos que vamos a registrar
     private String nombre = "";
     private String email = "";
     private String contraseña = "";

     FirebaseAuth Auth;
     DatabaseReference dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        Auth = FirebaseAuth.getInstance();

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtContraseña = (EditText) findViewById(R.id.txtContraseña);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = txtNombre.getText().toString();
                email = txtEmail.getText().toString();
                contraseña = txtContraseña.getText().toString();

                if(!nombre.isEmpty() && !email.isEmpty() && contraseña.isEmpty()) {

                    if (contraseña.length() >= 6) {
                        registerUser();
                    } else {
                        Toast.makeText(AuthActivity.this, "La contraseña debe tener al menos 6 carácteres", Toast.LENGTH_SHORT);
                    }

                } else {
                    Toast.makeText(AuthActivity.this, "Debes completar los campos", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private void registerUser() {
        Auth.createUserWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                } else {
                    Toast.makeText(AuthActivity.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT);
                }
            }
        });
    }

}