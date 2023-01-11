package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button addBtn;
    EditText textView;
    ListView listView;
    ArrayList<String> listOfTask;
    ArrayAdapter<String> adpt;
    databaseContext db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
        textView = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);
        db = new databaseContext(this);

        listOfTask = db.getAll();
        adpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.listOfTask);

        listView.setAdapter(adpt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                db.remove(listOfTask.get(i));
                listOfTask.remove(i);
                adpt.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.addBtn:
                String task = textView.getText().toString().equals("")?"😑 🙄":textView.getText().toString();
                db.insert(task);
                adpt.add(task);
                adpt.notifyDataSetChanged();
                textView.setText("");
                break;
        }
    }
}