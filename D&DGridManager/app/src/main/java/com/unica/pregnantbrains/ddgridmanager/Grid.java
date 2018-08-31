package com.unica.pregnantbrains.ddgridmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.leinardi.android.speeddial.FabWithLabelView;
import com.unica.pregnantbrains.ddgridmanager.model.CoordinateTransformer;
import com.unica.pregnantbrains.ddgridmanager.view.CombatGrid;
import com.unica.pregnantbrains.ddgridmanager.view.GridCanvas;

public class Grid extends AppCompatActivity {

    private static final String TAG = Grid.class.getSimpleName();
    private static final float INITIAL_ZOOM = 1.0f;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private SpeedDialView mSpeedDialView;
    private NavigationView mNavigationView;
    private Canvas canvas;
    private Bitmap mBitmap;
    private ImageView mImageView;
    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();
    private CoordinateTransformer transformer;
    CombatGrid grid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        Intent intent = getIntent();

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
                        Toast.makeText(Grid.this, "Create Obstacle clicked", Toast.LENGTH_SHORT).show();
                        mSpeedDialView.close();
                        return true;
                    case R.id.fab_area:
                        Toast.makeText(Grid.this, "Add Area clicked", Toast.LENGTH_SHORT).show();
                        mSpeedDialView.close();
                        return true;
                    case R.id.fab_line:
                        Toast.makeText(Grid.this, "Draw Line clicked", Toast.LENGTH_SHORT).show();
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

        transformer = new CoordinateTransformer(0,0, INITIAL_ZOOM);
        ui(mDrawerLayout);
    }

    private void ui (View view) {
        /*Bitmap bitmap =
                Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);*/


        mBitmap =  Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        //mImageView.setImageBitmap(mBitmap);
        canvas = new Canvas(mBitmap);
        draw(canvas, canvas.getClipBounds());
    }

    private void draw(Canvas canvas, Rect bounds) {
        PointF wsOrigin = transformer.screenSpaceToWorldSpace(bounds.left, bounds.top);
        float wsWidth = transformer.screenSpaceToWorldSpace(bounds.width());
        float wsHeight = transformer.screenSpaceToWorldSpace(bounds.height());
        RectF worldSpaceBounds = new RectF(wsOrigin.x, wsOrigin.y, wsOrigin.x + wsWidth, wsOrigin.y + wsHeight);

        grid.drawBackground(canvas);

        canvas.save();
        grid.drawToCanvas(canvas, transformer);
        canvas.save();
        //CoordinateTransformer gridSpace = grid.gridSpaceToScreenSpaceTransformer(transformer);
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
                        Toast.makeText(Grid.this, "Save Grid clicked", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_load:
                        Toast.makeText(Grid.this, "Load Grid clicked", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_settings:
                        Toast.makeText(Grid.this, "Settings clicked", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_guide:
                        Toast.makeText(Grid.this, "App Guide clicked", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_about_us:
                        Toast.makeText(Grid.this, "About Us clicked", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_license:
                        Toast.makeText(Grid.this, "License clicked", Toast.LENGTH_SHORT).show();
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
