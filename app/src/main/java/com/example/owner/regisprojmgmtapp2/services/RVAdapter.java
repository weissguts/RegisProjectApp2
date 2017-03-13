package com.example.owner.regisprojmgmtapp2.services;

/**
 * Created by Owner on 3/9/2017.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.owner.regisprojmgmtapp2.domain.Products;
import com.example.owner.regisprojmgmtapp2.*;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    private static final String TAG = "VIVZ" ;

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView project;
        TextView taskOne;
        TextView taskTwo;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            project = (TextView)itemView.findViewById(R.id.myProject);
            taskOne = (TextView)itemView.findViewById(R.id.myTaskOne);
            taskTwo = (TextView)itemView.findViewById(R.id.myTaskTwo);

        }
    }

    List<Products> products;

    public RVAdapter(List<Products> products){
        this.products = products;
    }


    public void updateProducts(List<Products> products){
        this.products = products;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_project_card, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        Log.d(TAG, "onCreateViewHolder: ");
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.project.setText(products.get(i).project);
        personViewHolder.taskOne.setText(products.get(i).taskOne);
        personViewHolder.taskTwo.setText(products.get(i).taskTwo);
        Log.d(TAG, "onBindViewHolder: " + i);
    }


    @Override
    public int getItemCount() {

        return products.size();
    }
}