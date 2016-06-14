package com.tcs.dbsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.tcs.dbsample.adapter.EmployeeAdapter;
import com.tcs.dbsample.async.EmployeeAsync;
import com.tcs.dbsample.callbacks.OnDataReceived;
import com.tcs.dbsample.utils.Constants;

public class MainActivity extends AppCompatActivity implements OnDataReceived {


    private RecyclerView mRecycleView;

    private RecyclerView.LayoutManager mLayoutManager;

    private EmployeeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, AddEmployeeActivity.class);
                startActivity(i);
            }
        });

        mRecycleView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecycleView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new EmployeeAdapter();
        mRecycleView.setAdapter(mAdapter);

        //Starts AsyncTask to retrieve data from database.
        new EmployeeAsync().retrieve(this, Constants.RETRIEVE_EMPLOYEE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //Starts AsyncTask to retrieve data from database.
        new EmployeeAsync().retrieve(this, Constants.RETRIEVE_EMPLOYEE);
    }

    @Override
    public void onDataReceived(int result, int trn, String data) {

        if (result == Constants.SUCCESS) {

            if (trn == Constants.RETRIEVE_EMPLOYEE) {
                mAdapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(this, "Problem with data retrieval", Toast.LENGTH_SHORT).show();
        }
    }
}
