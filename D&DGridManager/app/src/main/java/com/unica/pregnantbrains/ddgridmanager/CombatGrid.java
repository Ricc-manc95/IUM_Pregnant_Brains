package com.unica.pregnantbrains.ddgridmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.unica.pregnantbrains.ddgridmanager.adapters.ColorPickerListViewAdapter;
import com.unica.pregnantbrains.ddgridmanager.adapters.ColorPicker_Item;
import com.unica.pregnantbrains.ddgridmanager.model.GridData;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.Token;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

import java.util.ArrayList;

public class CombatGrid extends AppCompatActivity {

    private static final String TAG = CombatGrid.class.getSimpleName();
    private static final int NEW_PAWN_REQUEST = 1;
    private static final int NEW_OBSTACLE_REQUEST=1;

    private ActionMode mActionMode;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private SpeedDialView mSpeedDialView;
    private NavigationView mNavigationView;

    private GridView mGridView;
    private GridData mData = new GridData();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat_grid);

        Intent intent = getIntent();

        mGridView = new GridView(this, mData);

        FrameLayout gridContentFrame = (FrameLayout) this.findViewById(R.id.grid_content_frame);
        gridContentFrame.addView(mGridView);

        mGridView.requestFocus();

        if (this.getApplicationContext() == null) {
            return;
        }

        /***/
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

        /**FAB & Speed Dial*/
        mSpeedDialView = findViewById(R.id.creation_speed_dial);
        //nSpeedDialView.inflate(R.menu.creation_menu);

        //AGGIUNGERE CONTROLLO nSpeedDialView NOT NULL
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
                        newObstacle();
                        mSpeedDialView.close();
                        return true;
                    case R.id.fab_area:
                        Toast.makeText(CombatGrid.this, "Add Area clicked", Toast.LENGTH_SHORT).show();
                        mSpeedDialView.close();
                        return true;
                    case R.id.fab_line:
                        if (mActionMode != null) {
                            mToolbar.collapseActionView();
                            mSpeedDialView.close();
                            return true;
                        } else {
                            mActionMode = startSupportActionMode(mActionModeDrawLineCallback);
                            Toast.makeText(CombatGrid.this, "Draw Line clicked", Toast.LENGTH_SHORT).show();
                            mGridView.setDrawMode();
                            mSpeedDialView.close();
                        }
                        return true;
                    default:
                        break;
                }
                return true;
            }
        });

        setTitle();
    }

    private ActionMode.Callback mActionModeEraserCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mSpeedDialView.hide();
            getMenuInflater().inflate(R.menu.eraser_options_menu, menu);
            mode.setTitle("Eraser");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();

            switch (id) {
                case R.id.eraser_dimension:
                    /**ERASER DIMENSION*/
                    Toast.makeText(CombatGrid.this, "Ersaer Dimension clicked", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mSpeedDialView.show();
            mGridView.setTokenManipulationMode();
            mGridView.setOnDragListener(mGridView.mOnDrag);
            mActionMode = null;
        }
    };

    private ActionMode.Callback mActionModeDrawLineCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mSpeedDialView.hide();
            getMenuInflater().inflate(R.menu.draw_line_options_menu, menu);
            mode.setTitle("Draw line");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();

            switch (id) {
                case R.id.line_color:
                    /**LINE COLOR*/

                    Toast.makeText(CombatGrid.this, "Line Color clicked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.line_stroke:
                    /**LINE STROKE*/
                    AlertDialog.Builder builder = new AlertDialog.Builder(CombatGrid.this);
                    builder.setTitle("Select line stroke width").setItems(R.array.line_stroke, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mGridView.setNewLineStroke(i);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mSpeedDialView.show();
            mGridView.setTokenManipulationMode();
            mGridView.setOnDragListener(mGridView.mOnDrag);
            mActionMode = null;
        }
    };

    private void setTitle() {

        if (this.getSupportActionBar() == null) return;
        this.getSupportActionBar().setTitle("untitled map");
    }

    private void newPawn() {
        Intent newPawnIntent = new Intent(this, PawnCreation.class);
        startActivityForResult(newPawnIntent, NEW_PAWN_REQUEST);
    }
    private void newObstacle() {
        Intent newObstacleIntent = new Intent(this, ObstacleCreation.class);
        startActivityForResult(newObstacleIntent, NEW_OBSTACLE_REQUEST);
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

        int id = item.getItemId();

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (id == R.id.eraser_tool) {
            if (mActionMode != null) {
                mSpeedDialView.close();
                return true;
            } else {
                mActionMode = startSupportActionMode(mActionModeEraserCallback);
                Toast.makeText(CombatGrid.this, "Erase Tool clicked", Toast.LENGTH_SHORT).show();
                mGridView.setEraseMode();
                mSpeedDialView.close();
            }
        } else if (id == R.id.ruler_tool) {

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.grid_manipulation_menu, menu);
        return true;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == NEW_PAWN_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                int color = data.getIntExtra("color", 1);
                mGridView.placeToken(new Token(color, name));
            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }
}
