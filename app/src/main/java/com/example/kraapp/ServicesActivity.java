package com.example.kraapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import adapters.DataAdapter;
import models.Services;
import modelviews.ServiceModelView;
import repositories.ServiceRepo;

public class ServicesActivity extends AppCompatActivity  {



    private RecyclerView recyclerView;
    private List<Services> data;
    private DataAdapter adapter;
    private ServiceRepo serviceRepo;

    public static int fee;
    public static int service_id;

    //viewmodel
    private ServiceModelView sViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FloatingActionButton logout = findViewById(R.id.fab);

        logout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                }
        );
        initViews();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadFromDB();
    }

    private void loadFromDB() {

       
        adapter = new DataAdapter();
        recyclerView.setAdapter(adapter);

        sViewModel = ViewModelProviders.of(this).get(ServiceModelView.class);

        sViewModel.getLiveServices().observe(this, new Observer<List<Services>>() {
            @Override
            public void onChanged(@Nullable List<Services> notes) {
                //update RecyclerView
                adapter.submitList(notes);
            }
        });

        adapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Services note) {
                Toast.makeText(getApplicationContext(), "Pay for service", Toast.LENGTH_LONG).show();
                fee = note.getFee();
                service_id = note.getId();
                startActivity(new Intent(getApplicationContext(), Payments.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return  false;
    }
}
