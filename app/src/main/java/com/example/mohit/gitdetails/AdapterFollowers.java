package com.example.mohit.gitdetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterFollowers extends ArrayAdapter {

    ArrayList<FollowerDetails> details;
    LayoutInflater layoutInflater;

    public AdapterFollowers(@NonNull Context context, ArrayList<FollowerDetails> details) {
        super(context,0, details);
        this.details=details;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return details.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View output=convertView;
        if(output==null)
        {
            output=layoutInflater.inflate(R.layout.row_layout_followers,parent,false);
            FollowersView followersView=new FollowersView();
            followersView.imageView=output.findViewById(R.id.imageViewid);
            followersView.textView=output.findViewById(R.id.loginid);
            output.setTag(followersView);
        }
        FollowersView followersView=(FollowersView) output.getTag();
        followersView.textView.setText(details.get(position).getLogin());
        Picasso.get()
                .load(details.get(position).getImage())
                .resize(100, 100)
                .centerCrop()
                .into(followersView.imageView);

        return output;
    }
}