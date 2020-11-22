package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.user.adapter.AdapterUser;
import com.example.user.api.ApiRequest;
import com.example.user.api.Retroserver;
import com.example.user.model.DataModel;
import com.example.user.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager manager;
    private List<DataModel> mItems = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pd = new ProgressDialog(this);
        rv = findViewById(R.id.rvList);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);

        pd.setMessage("Loading....");
        pd.setCancelable(false);
        pd.show();

        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
        Call<ResponseModel> getData = api.getUser();
        getData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pd.hide();
                Log.d("Retro", "Response :" + response.body().getKode());
                mItems = response.body().getResult();

                mAdapter = new AdapterUser(MainActivity.this, mItems);
                rv.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.hide();
                Log.d("Retro", "Failed : respon gagal");
            }
        });
    }
}