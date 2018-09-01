package com.unica.pregnantbrains.ddgridmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

public class CombatGrid extends AppCompatActivity {

    private static final String TAG = CombatGrid.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private SpeedDialView mSpeedDialView;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat_grid);

        Intent intent = getIntent();

        if (this.getApplicationContext() == null) return;

        /***/
        /**FAB & Speed Dial*/
        mSpeedDialView = findViewById(R.id.creation_speed_dial);
        //nSpeedDialView.inflate(R.menu.creation_menu);

        //AGGIUNGERE CONTROLLO nSpeedDialViwe NOT NULL
        mSpeedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_obstacle, R.drawable.ic_obstacle_white_24dp)
                .setLabel(getString(R.string.fab_obstacle))
                .setTheme(R.style.AppTheme_Fab)
                .create()
        );
        mSpeedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_area, R.drawable.ic_area_white_24dp)
                .setLabel(getString(R.string.fab_area))
                .setTheme(R.style.AppTheme_Fab)
                .create()
        );
        mSpeedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_line, R.drawable.ic_line_white_24dp)
                .setLabel(getString(R.string.fab_line))
                .setTheme(R.style.AppTheme_Fab)
                .create()
        );

        mSpeedDialView.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                newPawn();
                mSpeedDialView.close();
                return true;
            }

            @Override
            public void onToggleChanged(boolean isOpen) {
                Log.d(TAG, "Speed dial toggle state changed. Open = " + isOpen);
            }
        });

        mSpeedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                switch (actionItem.getId()) {
                    case R.id.fab_obstacle:
                        Toast.makeText(CombatGrid.this, "Create Obstacle clicked", Toast.LENGTH_SHORT).show();
                        mSpeedDialView.close();
                        return true;
                    case R.id.fab_area:
                        Toast.makeText(CombatGrid.this, "Add Area clicked", Toast.LENGTH_SHORT).show();
                        mSpeedDialView.close();
                        return true;
                    case R.id.fab_line:
                        Toast.makeText(CombatGrid.this, "Draw Line clicked", Toast.LENGTH_SHORT).show();
                        mSpeedDialView.close();
                        return true;
                    default:
                        break;
                }
                return true;
            }
        });

        /**Toolbar*/
        mToolbar = (Toolbar) findViewById(R.id.nav_action_bar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpNavigationView();
        /***/
        setTitle();
    }

    private void setTitle() {

        if (this.getSupportActionBar() == null) return;
        this.getSupportActionBar().setTitle("untitled map");
    }

    private void newPawn() {
        Intent newPawnIntent = new Intent(this, PawnCreation.class);
        startActivity(newPawnIntent);
    }

    private void setUpNavigationView() {
        //Navigation View item listener
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            //Method will trigger on item Click
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check wich item was being clicked
                switch (menuItem.getItemId()){
                    case R.id.nav_save:
                        Toast.makeText(CombatGrid.this, "Save Grid clicked", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_load:
                        Toast.makeText(CombatGrid.this, "Load Grid clicked", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_settings:
                        Toast.makeText(CombatGrid.this, "Settings clicked", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_guide:
                        Toast.makeText(CombatGrid.this, "App Guide clicked", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_about_us:
                        Toast.makeText(CombatGrid.this, "About Us clicked", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_license:
                        Toast.makeText(CombatGrid.this, "License clicked", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**Closing Speed Dial & Drawer on back pressed*/
    @Override
    public void onBackPressed() {
        if (mSpeedDialView.isOpen()) {
            mSpeedDialView.close();
        } else if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
