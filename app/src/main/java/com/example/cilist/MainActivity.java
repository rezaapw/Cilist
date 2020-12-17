package com.example.cilist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cilist.alarm.AlarmActivity;
import com.example.cilist.api.MovieModel;
import com.example.cilist.api.ResultsItem;
import com.example.cilist.source.ApiConfig;
import com.example.cilist.source.ApiService;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView scroll;
    private ArrayList<ResultsItem> resultsItems;
    private Adapter adapter;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.cilist";
    private Text alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        resultsItems = new ArrayList<>();
        getData();
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
    }

    private void getData() {
        ApiService apiService = ApiConfig.getApiService();
        apiService.getData()
                .enqueue(new Callback<MovieModel>() {
                    @Override
                    public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                        if (response.isSuccessful()){
                            resultsItems = response.body().getResults();
                            adapter = new Adapter(resultsItems, getApplicationContext());
                            adapter.notifyDataSetChanged();
                            scroll.setAdapter(adapter);
                            int kolom = 2;
                            scroll.setLayoutManager(new GridLayoutManager(getApplicationContext(), kolom));
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieModel> call, Throwable t) {

                    }
                });
    }

    private void initView() {
        scroll = findViewById(R.id.scroll);
        alarm = findViewById(R.id.alarm);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.apply();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alarm:
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                String mOrderMessage = null;
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}