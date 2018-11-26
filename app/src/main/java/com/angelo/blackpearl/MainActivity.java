package com.angelo.blackpearl;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    public static Context context;
    private PostAdapter postAdapter = new PostAdapter(new ArrayList<Post>());
    private SwipeRefreshLayout mswipeRefresh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();


        final PostDatabase postDatabase = PostDatabase.getInstance(this);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        mswipeRefresh = swipeRefreshLayout;

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout != null){
                    swipeRefreshLayout.setRefreshing(true);
                }
                QueryUtils.extractPosts();
                swipeRefreshLayout.setRefreshing(false);
            }
        });




        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        recyclerView.setAdapter(new ScaleInAnimationAdapter(postAdapter));

        PostViewModel postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);

        postViewModel.getAllPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> postList) {
                postAdapter.setData(postDatabase.postDao().findAll());
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AccountHeader headerResults = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.ripple)
                .addProfiles(
                        new ProfileDrawerItem().withName(getString(R.string.kill_switch)).withEmail(getString(R.string.kill_switch_email)).withIcon(getResources().getDrawable(R.drawable.hamza))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                .build();
        Drawable feedIcon = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_home)
                .color(getColor(R.color.colorPrimary))
                .sizeDp(16);
        Drawable otaIcon = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_file_download)
                .color(getColor(R.color.colorPrimary))
                .sizeDp(16);

        Drawable settingsIcon = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_cog)
                .color(getColor(R.color.colorPrimary))
                .sizeDp(16);
        Drawable socialIcon = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_ad)
                .color(getColor(R.color.colorPrimary))
                .sizeDp(16);
        Drawable aboutIcon = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_info_circle)
                .color(getColor(R.color.colorPrimary))
                .sizeDp(16);
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Feed").withSelectedColor(getResources().getColor(R.color.blueGray)).withIcon(feedIcon);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("OTA").withSelectedColor(getResources().getColor(R.color.blueGray)).withIcon(otaIcon);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withName("Settings").withSelectedColor(getResources().getColor(R.color.blueGray)).withIcon(settingsIcon).withSelectable(false);
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withIdentifier(5).withName("About").withSelectedColor(getResources().getColor(R.color.blueGray)).withIcon(aboutIcon).withSelectable(false);

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResults)
                .addDrawerItems(
                        item1,
                        item2,
                        new DividerDrawerItem(),
                        item3,
                        new DividerDrawerItem(),
                        item5
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position){
                            case 1:
                            break;
                            case 2: Toast.makeText(MainActivity.context, "Coming Soon :D", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                startActivity(new Intent(view.getContext(), SettingsActivity.class));
                                break;
                            case 4:
                                startActivity(new Intent(view.getContext(), SettingsActivity.class));
                            break;
                            case 5:
                            break;
                            case 6:
                                startActivity(new Intent(view.getContext(), InfoActivity.class));
                                break;
                            case 7:
                            break;
                            default:
                            break;
                        }

                        return true;
                    }
                })
                .build();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
    QueryUtils.extractPosts();
        PostDatabase postDatabase = PostDatabase.getInstance(this);
        postAdapter.setData(postDatabase.postDao().findAll());
        mswipeRefresh.setRefreshing(false);


    }
}
