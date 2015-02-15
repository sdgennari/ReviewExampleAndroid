package edu.hooapps.android.reviewexample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import edu.hooapps.android.reviewexample.adapter.CountryAdapter;
import edu.hooapps.android.reviewexample.R;
import edu.hooapps.android.reviewexample.model.CountryManager;

public class RecyclerActivity extends Activity {

    private RecyclerView mRecyclerView;
    private CountryAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new CountryAdapter(CountryManager.getInstance().getCountries(),
                R.layout.row_country,
                this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
