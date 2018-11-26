package com.angelo.blackpearl;


import android.content.Intent;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<Post> postList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleText;
        public TextView descText;
        public ImageView imageView;
        public MaterialButton openButton;
        public MyViewHolder(View view){
            super(view);
            titleText = (TextView) view.findViewById(R.id.title_text);
            descText = (TextView) view.findViewById(R.id.desc_text);
            imageView = (ImageView) view.findViewById(R.id.img_icon);
            openButton = (MaterialButton) view.findViewById(R.id.open_button);
    }

    }

    public PostAdapter(List<Post> postList){
        this.postList = postList;
        notifyDataSetChanged();

    }

    public void clear(){
        this.postList.clear();
        notifyDataSetChanged();
    }

    public void setData(List<Post> newData) {
        this.postList = newData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        setFadeAnimation(holder.itemView);
        final Post post = postList.get(position);
        holder.titleText.setText(post.getTitle());
        holder.descText.setText(post.getDesc());
        GlideApp
                .with(MainActivity.context)
                .load(post.getImg())
                .centerCrop()
                .placeholder(R.drawable.xda)
                .transition(withCrossFade())
                .into(holder.imageView);

        String url = post.getLink();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        
        holder.openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.itemView.clearAnimation();
    }

    public void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }
}
