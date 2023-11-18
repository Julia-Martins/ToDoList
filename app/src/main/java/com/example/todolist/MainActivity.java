package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText edt_email;
    private EditText edt_password;
    private Button btn_login;
    private Button btn_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login_main);
        btn_create = findViewById(R.id.btn_create);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });
    }

    private void createUser() {
        String email = edt_email.getText().toString();
        String password = edt_password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            edt_email.requestFocus();
            Toast.makeText(MainActivity.this, "EMAIL NÃO PODE SER VAZIO", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            edt_password.requestFocus();
            Toast.makeText(MainActivity.this, "SENHA NÃO PODE SER VAZIA", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "USER SUCCESSFULLY CREATED!", Toast.LENGTH_LONG).show();

                        clear();
                    } else {
                        Toast.makeText(MainActivity.this, "Erro ao criar usuário: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void openLoginActivity(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
    private void clear(){
        edt_email.setText("");
        edt_password.setText("");
    }
}