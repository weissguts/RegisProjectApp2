package com.example.owner.regisprojmgmtapp2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

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



        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        products = new ArrayList<>();
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
}
