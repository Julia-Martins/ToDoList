package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button logoutButton;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<ItemModel> itemList;
    private TextView txt_no_tasks_todo;
    private TextView txt_title_todoList;
    private FloatingActionButton fab_add_todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        mAuth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logoutButton);
        txt_no_tasks_todo = findViewById(R.id.txt_no_tasks_todo);
        txt_title_todoList = findViewById(R.id.txt_title_todoList);

        fab_add_todoList = findViewById(R.id.fab_add_todoList);

        recyclerView = findViewById(R.id.rv_todoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        itemList.add(new ItemModel("Wolf", "Wolf's Tasks."));
        itemList.add(new ItemModel("Lion", "Lion's Tasks."));
        itemList.add(new ItemModel("Seal", "Seal's Tasks."));
        itemList.add(new ItemModel("Raven", "Raven's Tasks."));
        itemList.add(new ItemModel("Tiger", "Tiger's Tasks."));

        adapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

        if(itemList.size() != 0){
            recyclerView.setVisibility(View.VISIBLE);
            txt_title_todoList.setVisibility(View.VISIBLE);
            txt_no_tasks_todo.setVisibility(View.GONE);
        }else{
            recyclerView.setVisibility(View.GONE);
            txt_title_todoList.setVisibility(View.GONE);
            txt_no_tasks_todo.setVisibility(View.VISIBLE);
        }

        fab_add_todoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewTask();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
            }
        });
    }

    private void createNewTask(){
        Toast.makeText(this, "Breve será criado", Toast.LENGTH_SHORT).show();
    }

    private void logoutUser() {
        mAuth.signOut();
        logoutButton.setVisibility(View.GONE);
        Intent intent = new Intent(ToDoListActivity.this, MainActivity.class);
        startActivity(intent);
    }
}