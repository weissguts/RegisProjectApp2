package com.example.owner.regisprojmgmtapp2;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.owner.regisprojmgmtapp2.domain.Products;
import com.example.owner.regisprojmgmtapp2.services.RVAdapter;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Handler;

import static android.R.id.button1;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getInstance().getReference();


    private Context context = null;
    private List<Products> productsList = new ArrayList<Products>();
    private Products products = new Products("TEst", "Testtt", "testt");
    final RVAdapter adapter = new RVAdapter(productsList);





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
            }
        });

        //****************Delete Button*****************//
        Button deleteButton = (Button) findViewById(R.id.myDeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteAllProjects(v);
            }
        });

        //****************Sync Button*****************//
        Button syncButton = (Button) findViewById(R.id.mySyncButton);
        syncButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                syncNow(v);
            }
        });

        syncButton.performClick();

    }



    public void addProject(View view) {

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    productsList.add(products);
                    myRef.setValue(productsList);
                    rv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void deleteAllProjects(View view) {

        productsList.removeAll(productsList);
        rv.setAdapter(adapter);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    productsList.clear();
                    myRef.setValue(productsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void syncNow(View view) {

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productsList.clear();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Products product = child.getValue(Products.class);
                    productsList.add(product);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        

    }





}
