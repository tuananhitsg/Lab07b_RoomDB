package com.example.lab07b_roomdb;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private Button btnAdd, btnRemove, btnCancel;
    private EditText edt;
    private RecyclerView recyclerView;

    private UserAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        btnAdd = findViewById(R.id.lvLayout_btnAdd);
        btnRemove = findViewById(R.id.lvLayout_btnRemove);
        btnCancel = findViewById(R.id.lvLayout_btnCancel);
        edt = findViewById(R.id.lvLayout_edtSearch);
        recyclerView = findViewById(R.id.rcvLayout_recyle);

        userAdapter = new UserAdapter();
        userList = new ArrayList<>();
        userAdapter.setData(userList);


        recyclerView.setAdapter(userAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

    }
    private void addUser(){
        String name = edt.getText().toString().trim();
        if(TextUtils.isEmpty(name))
            return;

        User user = new User(name);
        AppDatabase.getInstance(this).userDAO().insertUsers(user);
        Toast.makeText(this,"Them thanh cong",Toast.LENGTH_LONG).show();
        edt.setText("");

        userList=AppDatabase.getInstance(this).userDAO().getAllUser();
        userAdapter.setData(userList);
    }
}