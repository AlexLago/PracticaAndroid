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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

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
        dataBase = FirebaseDatabase.getInstance().getReference();

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
                if(nombre.isEmpty() || email.isEmpty() || contraseña.isEmpty()) {
                    Toast.makeText(AuthActivity.this, "Debes completar los campos", Toast.LENGTH_SHORT);
                    return;
                }
                if (contraseña.length() < 6) {
                    Toast.makeText(AuthActivity.this, "La contraseña debe tener al menos 6 carácteres", Toast.LENGTH_SHORT);
                    return;
                }
                registerUser();
            }
        });

    }

    private void registerUser() {
        Auth.createUserWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", nombre);
                    map.put("email", email);
                    map.put("password", contraseña);

                    String id = Auth.getCurrentUser().getUid();

                    dataBase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(AuthActivity.this, ImagenActivity.class));
                                finish();
                            } else {
                                Toast.makeText(AuthActivity.this, "No se puedieron crear los datos correctamente", Toast.LENGTH_SHORT);
                            }
                        }
                    });

                } else {
                    Toast.makeText(AuthActivity.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT);
                }
            }
        });
    }

}