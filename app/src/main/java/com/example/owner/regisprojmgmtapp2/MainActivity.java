/**
 * Daniel Hernandez
 * Regis CS 338 - Professor Robert Sjodin
 * Project Management App Project
 * ****Version 0.3 -
 *
 * This project management app which will be created for the Regis class CS338 is focused around
 * being simple and easy to use. Specifically, this app is going to border on something between a
 * reminder app. The idea is to have the user create different projects.The projects will then be
 * created on an interface of a “card” and on the day they are active will be front and center
 * when user opens app.
 *
 * -Priority Updates Needed-
 *
 * 1. Need to get setText to work on cards after 1st card view.
 * 2. Swipe animations to delete.
 *
 ***Random notes - *
 *
 *
 *
 *
 * 3/12/17 - Recreated Project with cardViews. -> Now using Firebase -> SetText only works on
 * initial project card. Sync and other buttons cause Edit Text to reappear.
 */

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
import android.widget.EditText;
import android.widget.TextView;
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
    private Products products = new Products("Project PlaceHolder", "Task One", "Task Two ");
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

        //Refreshes app by syncing with firebase every time app reopened.
        syncButton.performClick();

    }


    /**
     * Add a project card to the recycler view. THe listener will automatically add project
     * to firebase.
     * @param view
     */
    public void addProject(View view) {
        final Products products = new Products("Project PlaceHolder", "Task One", "Task Two ");
        productsList.add(products);
        adapter.notifyDataSetChanged();


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //productsList.add(products);
                //myRef.setValue(productsList);
                //

                for (int i = 0; i < productsList.size(); i++) {
                    productsList.set(i, products);
                    myRef.setValue(productsList);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /**
     * Deletes all project cards from device as well as firebase.
     * @param view
     */
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

    /**
     * Syncs phone with firebase and autopopulates recycler view. EditTexts are still
     * reappearing.
     * @param view
     */
    public void syncNow(View view) {
        rv.setAdapter(adapter);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productsList.clear();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Products product = child.getValue(Products.class);

                    productsList.add(product);

                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    /**
     * Sets the edit texts to the textviews. Only works on initial cardview.
     * @param view
     */
    public void setText(View view) {
            for (int i=0; i < productsList.size(); i++) {

                TextView myProject = (TextView) findViewById(R.id.myProject);
                EditText myProjectEdit = (EditText) findViewById(R.id.myProjectEdit);
                myProject.setText((myProjectEdit.getText().toString()));
                myProject.setAlpha(1);
                myProjectEdit.setVisibility(View.GONE);

                TextView myTaskOne = (TextView) findViewById(R.id.myTaskOne);
                EditText myTaskOneEdit = (EditText) findViewById(R.id.myTaskOneEdit);
                myTaskOne.setText((myTaskOneEdit.getText().toString()));
                myTaskOne.setAlpha(1);
                myTaskOneEdit.setVisibility(View.GONE);

                TextView myTaskTwo = (TextView) findViewById(R.id.myTaskTwo);
                EditText myTaskTwoEdit = (EditText) findViewById(R.id.myTaskTwoEdit);
                myTaskTwo.setText((myTaskTwoEdit.getText().toString()));
                myTaskTwo.setAlpha(1);
                myTaskTwoEdit.setVisibility(View.GONE);

                products.setProject(myProject.getText().toString());
                products.setTaskOne(myTaskOne.getText().toString());
                products.setTaskTwo(myTaskTwo.getText().toString());

                productsList.set(i, products);

                adapter.notifyDataSetChanged();
                myRef.setValue(productsList);
                i++;

            }

    }

}


