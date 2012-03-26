package com.example.AndroidWidget;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        intent = new Intent().setClass(this,FullListTab.class);
        spec = tabHost.newTabSpec("FullList").setIndicator("Full List",
                res.getDrawable(R.drawable.fulllist))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, FavoriteTab.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("Bookmark").setIndicator("Bookmark",
                res.getDrawable(R.drawable.favorite))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}

//res.getDrawable(R.drawable.ic_tab_artists)