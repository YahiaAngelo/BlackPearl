package com.angelo.blackpearl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class SocialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        setupActionBar();
        // Layouts
        LinearLayout blackPearlTgChat = findViewById(R.id.blackpearl_support_tg_layout);
        LinearLayout blackPearlTgChannel = findViewById(R.id.blackpearl_channel_tg_layout);
        LinearLayout killswitchTg = findViewById(R.id.killswitch_tg_layout);
        LinearLayout killswitchIg = findViewById(R.id.killswitch_ig_layout);
        LinearLayout killswitchPaypal = findViewById(R.id.killswitch_pp_layout);
        LinearLayout angeloTg = findViewById(R.id.angelo_tg_layout);
        LinearLayout angeloGithub = findViewById(R.id.angelo_github_layout);
        LinearLayout angeloPaypal = findViewById(R.id.angelo_pp_layout);


        blackPearlTgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(getString(R.string.blackPearlTgChat));
            }
        });

        blackPearlTgChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(getString(R.string.blackPearlTgChannel));
            }
        });

        killswitchTg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(getString(R.string.killswitchTg));
            }
        });

        killswitchIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(getString(R.string.killswitchIg));
            }
        });

        killswitchPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(getString(R.string.killswitchPaypal));
            }
        });

        angeloTg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(getString(R.string.angeloTg));
            }
        });

        angeloGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(getString(R.string.angeloGithub));
            }
        });

        angeloPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(getString(R.string.angeloPaypal));
            }
        });



    }

    private void openLink(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setupActionBar() {
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

}
