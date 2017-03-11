package com.example.owner.regisprojmgmtapp2;

import android.content.Context;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.owner.regisprojmgmtapp2.domain.Products;
import com.example.owner.regisprojmgmtapp2.services.RVAdapter;
import com.google.firebase.database.*;
import com.google.firebase.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("ProjectCards");
    private Context context = null;
    private List<Products> productsList = new ArrayList<Products>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);



        //****************Add Button*****************//
        Button addButton = (Button) findViewById(R.id.myAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addProject(v);
                myRef.child("notes").push().setValue(productsList);


            }
        });

        //****************Delete Button*****************//
        Button deleteButton = (Button) findViewById(R.id.myDeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteAllProjects(v);
            }
        });

        initializeData();
        initializeAdapter();
        //initFirebaseListener();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Products products = dataSnapshot.getValue(Products.class);
                String one = dataSnapshot.getValue().toString();
                while (one.contains("project")) {
                    System.out.println("Test");
                }
                products.setTaskTwo((String) products.getTaskTwo());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void initializeData() {
        productsList.add(new Products("Project Placeholder", "1st Task"
                , "2nd Task"));

    }

    private void initializeAdapter() {
        RVAdapter adapter = new RVAdapter(productsList);
        rv.setAdapter(adapter);
    }

    public void addProject(View view) {
        RVAdapter rvAdapter = (RVAdapter) rv.getAdapter();
        String Help = "PLEASE WORK";
        productsList.add(new Products(Help, "Test One", "Test Two"));
        rvAdapter.updateProducts(productsList);
        rv.setAdapter(rvAdapter);
    }

    public void deleteAllProjects(View view) {
        RVAdapter rvAdapter = (RVAdapter) rv.getAdapter();
        productsList.removeAll(productsList);
        rvAdapter.updateProducts(productsList);
        rv.setAdapter(rvAdapter);
    }

//    private void initFirebaseListener() {
//        myRef.child("notes").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                HashMap<String, Object> notesJSON = dataSnapshot.getValue(HashMap.class);
//                ArrayList<Products> productsList = new ArrayList<Products>();
//
//                for (Map.Entry<String, Object> entry : notesJSON.entrySet()) {
//                    Map notes = (Map) entry.getValue();
//                    Products products = new Products();
//
//                    products.setProject((String) notes.get("project"));
//                    products.setTaskOne((String) notes.get("taskOne"));
//                    products.setTaskTwo((String) notes.get("taskTwo"));
//
//                    productsList.add(products);
//                }
//
//                RVAdapter rvAdapter = (RVAdapter) rv.getAdapter();
//                rv.setAdapter(rvAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }





}
