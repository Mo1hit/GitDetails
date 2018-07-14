package com.example.mohit.gitdetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Followers extends AppCompatActivity {

    LinearLayout linearLayout;
    ProgressBar progressBar;

    String loginName;
    ArrayList<FollowerDetails> followers;

    AdapterFollowers adapterFollowers;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        followers=new ArrayList<>();

        progressBar=findViewById(R.id.progressBar);
        linearLayout=findViewById(R.id.linear_layout);

        progressBar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);

        adapterFollowers=new AdapterFollowers(this,followers);

        listView=findViewById(R.id.listView);
        listView.setAdapter(adapterFollowers);

        Intent intent=getIntent();
        loginName=intent.getStringExtra("loginName");

        Retrofit.Builder builder=new Retrofit.Builder().
                baseUrl("https://api.github.com/users/").
                addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();

        FollowersDetailService service=retrofit.create(FollowersDetailService.class);

        Call<ArrayList<FollowerDetails>> call=service.getFollowersDetails(loginName);

        call.enqueue(new Callback<ArrayList<FollowerDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<FollowerDetails>> call, Response<ArrayList<FollowerDetails>> response) {

                progressBar.setVisibility(View.GONE);

                if(response.body()!=null) {
                    listView.setVisibility(View.VISIBLE);
                    followers.clear();
                    followers.addAll(response.body());
                    adapterFollowers.notifyDataSetChanged();
                    Toast.makeText(Followers.this, "yuup!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Followers.this, "nope", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<FollowerDetails>> call, Throwable t) {

            }
        });
    }
}