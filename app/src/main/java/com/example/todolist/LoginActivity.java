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
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button cancelButton;
    private TextView resultTextView;

    private androidx.appcompat.widget.Toolbar toolBar_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.btn_login);
        cancelButton = findViewById(R.id.cancelButton);
        resultTextView = findViewById(R.id.resultTextView);

        toolBar_login = findViewById(R.id.toolBar_login);
        setSupportActionBar(toolBar_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        toolBar_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void loginUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailEditText.requestFocus();
            Toast.makeText(LoginActivity.this, "EMAIL NÃO PODE SER VAZIO", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            passwordEditText.requestFocus();
            Toast.makeText(LoginActivity.this, "SENHA NÃO PODE SER VAZIA", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(LoginActivity.this, ToDoListActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Login Efetuado com Sucesso!!", Toast.LENGTH_SHORT).show();

                        clear();
                    } else {
                        resultTextView.setVisibility(View.VISIBLE);
                        resultTextView.setText("Login falhou");
                    }
                }
            });
        }
    }

    private void clear(){
        emailEditText.setText("");
        passwordEditText.setText("");
        resultTextView.setText("");
        resultTextView.setVisibility(View.GONE);
    }
}