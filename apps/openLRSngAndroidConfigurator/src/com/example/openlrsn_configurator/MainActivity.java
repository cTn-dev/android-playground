package com.example.openlrsn_configurator;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    private DrawerLayout DrawerLayout;
    private ListView DrawerList;
    private ActionBarDrawerToggle DrawerToggle;
    
    private CharSequence Title;
    private String[] TabTitles;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        
        Title = getTitle();
        TabTitles = getResources().getStringArray(R.array.tabs_array);
        
        DrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        DrawerList = (ListView) findViewById(R.id.main_drawer);
        
        // set up the drawer's list view with items and click listener
        DrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, TabTitles));
        DrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        DrawerToggle = new ActionBarDrawerToggle(this, DrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                Log.d("onDrawerClosed", "Fired");
                
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            
            public void onDrawerOpened(View drawerView) {
                Log.d("onDrawerOpened", "Fired");
                
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        DrawerLayout.setDrawerListener(DrawerToggle);

        // Open drawer
        //DrawerLayout.openDrawer(GravityCompat.START);
        
        Log.d("onCreate", "Fired");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("onCreateOptionsMenu", "Fired");
        
        // MenuInflater inflater = getMenuInflater();
        // inflater.inflate(R.menu.main, menu);
        
        return super.onCreateOptionsMenu(menu);
    }
    
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (DrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        
        // Handle action buttons
        switch(item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
        
        //return super.onOptionsItemSelected(item);
    }
    
    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    
    /* Swaps fragments in the main content view */
    private void selectItem(int position) {
        Log.d("selectItem", Integer.toString(position));
        
        DrawerList.setItemChecked(position, true);
        getActionBar().setTitle(TabTitles[position]);
        DrawerLayout.closeDrawer(DrawerList);
    }
    
    /* When using the ActionBarDrawerToggle, you must call it during onPostCreate() and onConfigurationChanged()... */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        DrawerToggle.syncState();
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        DrawerToggle.onConfigurationChanged(newConfig);
    }    
}
