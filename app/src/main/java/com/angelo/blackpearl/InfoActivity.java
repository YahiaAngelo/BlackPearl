package com.angelo.blackpearl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikepenz.iconics.context.IconicsLayoutInflater2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

public class InfoActivity extends AppCompatActivity {

    InfoActivity infoActivityContext = this;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        QueryUtils.getLatestRelease(this);
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setupActionBar();

        RelativeLayout infoButton =  findViewById(R.id.infButton);
        LinearLayout changelogButton = findViewById(R.id.changelogButton);
        LinearLayout sourceCodeButton = findViewById(R.id.source_code_button);
        LinearLayout checkUpdatesButton = findViewById(R.id.updates_check_button);
        LinearLayout aboutUsButton = findViewById(R.id.aboutus_button);
        TextView checkUpdatesText = findViewById(R.id.updates_check_text);

        SharedPreferences sharedPref = this.infoActivityContext.getPreferences(Context.MODE_PRIVATE);
        String latestRelease =sharedPref.getString("latestRelease", getString(R.string.app_version));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("BlackPearl v" + latestRelease + " is available!" );
        builder.setTitle("New Update!");
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openLink("https://github.com/YahiaAngelo/BlackPearl/releases");
            }
        });
        AlertDialog dialog = builder.create();

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        changelogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.activity_changelog, null);
                popupView.setAnimation(AnimationUtils.loadAnimation(infoActivityContext , R.anim.up_from_bottom));

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });

            }
        });

        sourceCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startActivity(new Intent(infoActivityContext, SocialActivity.class));

            }
        });

        checkUpdatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUpdatesText.setText(getString(R.string.checking_for_updates));
                if (!checkForAppUpdates()){
                    checkUpdatesText.setText(getString(R.string.new_update));
                    dialog.show();
                }else {
                    checkUpdatesText.setText(getString(R.string.uptodate));
                }


                     }
        });

        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.activity_about_us, null);
                popupView.setAnimation(AnimationUtils.loadAnimation(infoActivityContext , R.anim.up_from_bottom));


                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);



                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });


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

    private boolean checkForAppUpdates(){
        QueryUtils.getLatestRelease(this.infoActivityContext);
        Boolean isUpToDate;
        Double versionCode = Double.valueOf(BuildConfig.VERSION_NAME);
        SharedPreferences sharedPref = this.infoActivityContext.getSharedPreferences("sharedPreferences" ,Context.MODE_PRIVATE);
        String latestVersion = sharedPref.getString("latestRelease", getString(R.string.app_version));
        Double latestVersionDouble = Double.valueOf(latestVersion);
        if(latestVersionDouble.equals(versionCode)){
            isUpToDate = true;
        }else{
            isUpToDate = false;
        }
      return isUpToDate;
    }

    private void openLink(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}