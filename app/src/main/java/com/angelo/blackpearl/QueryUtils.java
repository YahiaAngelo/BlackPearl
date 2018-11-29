package com.angelo.blackpearl;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.json.JSONObject;

import androidx.annotation.NonNull;
import eu.amirs.JSON;


public class QueryUtils {


    private static final PostDatabase postDatabase = PostDatabase.getInstance(MainActivity.context);
    public static final String githubReleasesUrl = "https://api.github.com/repos/YahiaAngelo/BlackPearl/releases/latest";

    private QueryUtils() {
    }

    public static void extractPosts() {


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("hamza");
        String post = "post";

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                    String title = (String) dataSnapshot.child(post + i).child("title").getValue();
                    String desc = (String) dataSnapshot.child(post + i).child("desc").getValue();
                    String link = (String) dataSnapshot.child(post + i).child("link").getValue();
                    String imgLink = (String) dataSnapshot.child(post + i).child("img").getValue();
                    Post post = new Post();
                    post.setTitle(title);
                    post.setDesc(desc);
                    post.setLink(link);
                    post.setImg(imgLink);
                    post.setId(i);
                    postDatabase.postDao().save(post);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public static void getLatestRelease(Context context){

        RequestQueue mRequestQueue;

        Cache cache = new DiskBasedCache(MainActivity.context.getCacheDir(), 1024 * 1024); // 1MB cap

        Network network = new BasicNetwork(new HurlStack());

        mRequestQueue = new RequestQueue(cache, network);

        mRequestQueue.start();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, githubReleasesUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPreferences",Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                JSON json = new JSON(response.toString());
                String version = json.key("tag_name").stringValue();
                editor.putString("latestRelease", version);
                editor.apply();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("LatestVersion", "Something went wrong"+ error);

            }
        });

        mRequestQueue.add(jsonObjectRequest);

    }




}
