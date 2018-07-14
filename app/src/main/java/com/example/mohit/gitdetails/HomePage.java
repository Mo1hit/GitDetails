package com.example.mohit.gitdetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePage extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    ImageView imageView;
    TextView nameView,loginView,reposView,gistsView,followerView,followingView;
    Button followersButton,repositoryButton;
    ProgressBar progressBar;

    LinearLayout linearLayout;

    String loginName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        linearLayout=findViewById(R.id.linear_layout);

        imageView=findViewById(R.id.imageid);

        nameView=findViewById(R.id.nameid);
        loginView=findViewById(R.id.loginid);
        reposView=findViewById(R.id.reposid);
        gistsView=findViewById(R.id.gistsid);
        followerView=findViewById(R.id.followerid);
        followingView=findViewById(R.id.followingid);

        followersButton=findViewById(R.id.followersbuttonid);
        repositoryButton=findViewById(R.id.repositorybuttonid);

        progressBar=findViewById(R.id.progressBar);

        linearLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        followersButton.setOnClickListener(this);
        repositoryButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar,menu);
        SearchView searchView = (SearchView)menu.findItem(R.id.search_icon).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        result(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        result(s);
        return true;
    }

    public void result(String s)
    {
        progressBar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);

        Retrofit.Builder retrofitBuilder=new Retrofit.Builder().
                baseUrl("https://api.github.com/users/").
                addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit=retrofitBuilder.build();

        ProfileDetailService service=retrofit.create(ProfileDetailService.class);

        Call<GitProfileDetails> call=service.getProfileDetails(s);

        call.enqueue(new Callback<GitProfileDetails>() {
            @Override
            public void onResponse(Call<GitProfileDetails> call, Response<GitProfileDetails> response) {
                if(response.body()!=null) {
                    GitProfileDetails details = response.body();

                    loginName=details.getLoginName();

                    Picasso.get()
                            .load(details.getImage())
                            .resize(500, 400)
                            .centerCrop()
                            .into(imageView);
                    nameView.setText(details.getName());
                    loginView.setText(details.getLoginName());
                    reposView.setText(details.getPublicRepos()+"");
                    gistsView.setText(details.getPublicGists()+"");
                    followerView.setText(details.getFollower()+"");
                    followingView.setText(details.getFollowing()+"");

                    progressBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<GitProfileDetails> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view==followersButton)
        {
            Intent intent=new Intent(this,Followers.class);
            intent.putExtra("loginName",loginName);
            startActivity(intent);
        }
        else if(view==repositoryButton)
        {

        }
    }
}