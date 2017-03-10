package com.example.owner.regisprojmgmtapp2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.owner.regisprojmgmtapp2.domain.Products;
import com.example.owner.regisprojmgmtapp2.services.RVAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Products> products;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        products = new ArrayList<>();

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        Button addButton = (Button) findViewById(R.id.myAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addProject(v);
            }
        });
        initializeData();
        initializeAdapter();

    }

    private void initializeData(){

        products.add(new Products("Project Placeholder", "1st Task"
                , "2nd Task"));
        products.add(new Products("Project Placeholder", "1st Task"
                , "2nd Task"));
        products.add(new Products("Project Placeholder", "1st Task"
                , "2nd Task"));
        products.add(new Products("Project Placeholder", "1st Task"
                , "2nd Task"));
        products.add(new Products("Project Placeholder", "1st Task"
                , "2nd Task"));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(products);
        rv.setAdapter(adapter);
    }

    public void addProject(View view) {
        RVAdapter rvAdapter = (RVAdapter) rv.getAdapter();
        String Help = "PLEASE WORK";
        products.add(new Products(Help, "Test One", "Test Two"));
        rvAdapter.updateProducts(products);
        rv.setAdapter(rvAdapter);


    }


}
