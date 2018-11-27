package com.angelo.blackpearl;

import android.util.Log;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;


public class QueryUtils {


    private static final PostDatabase postDatabase = PostDatabase.getInstance(MainActivity.context);


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
                    Log.v("MyPost", "My post is:" + title + desc + link);
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




}
