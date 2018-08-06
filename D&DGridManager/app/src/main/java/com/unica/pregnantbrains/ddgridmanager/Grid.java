package com.unica.pregnantbrains.ddgridmanager;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
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

public class Grid extends AppCompatActivity {

    private static final String TAG = Grid.class.getSimpleName();

    private DrawerLayout nDrawerLayout;
    private ActionBarDrawerToggle nToggle;
    private Toolbar nToolbar;
    private SpeedDialView nSpeedDialView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        nSpeedDialView = findViewById(R.id.creation_speed_dial);
        nSpeedDialView.inflate(R.menu.creation_menu);

        nSpeedDialView.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                Toast.makeText(Grid.this, "Create Pawn clicked", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onToggleChanged(boolean isOpen) {
                Log.d(TAG, "Speed dial toggle state changed. Open = " + isOpen);
            }
        });

        nSpeedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                switch (actionItem.getId()) {
                    case R.id.action_create_obstacle:
                        Toast.makeText(Grid.this, "Create Obstacle clicked", Toast.LENGTH_SHORT).show();
                        nSpeedDialView.close();
                        return true;
                    case R.id.action_add_shape:
                        Toast.makeText(Grid.this, "Add Shape clicked", Toast.LENGTH_SHORT).show();
                        nSpeedDialView.close();
                        return true;
                    case R.id.action_draw_line:
                        Toast.makeText(Grid.this, "Draw Line clicked", Toast.LENGTH_SHORT).show();
                        nSpeedDialView.close();
                        return true;
                    default:
                            break;
                }
                return true;
            }
        });

        nSpeedDialView.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                Toast.makeText(Grid.this, "Create Pawn clicked", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onToggleChanged(boolean isOpen) {
                Log.d(TAG, "Speed dial toggle state changed. Open = " + isOpen);
            }
        });

        nToolbar = (Toolbar) findViewById(R.id.nav_action_bar);
        setSupportActionBar(nToolbar);

        nDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this, nDrawerLayout, R.string.open, R.string.close);

        nDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (nToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (nSpeedDialView.isOpen()) {
            nSpeedDialView.close();
        } else if (this.nDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.nDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
