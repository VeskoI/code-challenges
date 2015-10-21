package com.veskoiliev.guidebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.veskoiliev.guidebook.model.Guide;
import com.veskoiliev.guidebook.model.GuideResponse;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.MoshiConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "guidebook";

    private GuideBookWebService mService;
    private RecyclerView mRecyclerView;
    private GuidebookAdapter mAdapter;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://guidebook.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        mService = retrofit.create(GuideBookWebService.class);
        requestGuideBooks();
    }

    private void requestGuideBooks() {
        Call<GuideResponse> call = mService.getGuides();
        call.enqueue(new Callback<GuideResponse>() {
            @Override
            public void onResponse(Response<GuideResponse> response, Retrofit retrofit) {
                Log.d(TAG, "onResponse() called with: " + "response = [" + response + "], retrofit = [" + retrofit + "]");
                List<Guide> guides = response.body().getData();
                if (guides == null || guides.isEmpty()) {
                    Log.d(TAG, "onResponse: empty guides response");
                    return;
                }

                mAdapter = new GuidebookAdapter();
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.setData(guides);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure() called with: " + "t = [" + t + "]");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
