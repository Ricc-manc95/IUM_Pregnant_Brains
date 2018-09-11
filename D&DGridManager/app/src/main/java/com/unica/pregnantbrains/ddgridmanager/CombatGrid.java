package com.unica.pregnantbrains.ddgridmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.unica.pregnantbrains.ddgridmanager.adapters.ColorPickerListViewAdapter;
import com.unica.pregnantbrains.ddgridmanager.adapters.ColorPicker_Item;
import com.unica.pregnantbrains.ddgridmanager.data.DataManager;
import com.unica.pregnantbrains.ddgridmanager.model.Grid;
import com.unica.pregnantbrains.ddgridmanager.model.GridData;
import com.unica.pregnantbrains.ddgridmanager.model.Token;
import com.unica.pregnantbrains.ddgridmanager.view.GridView;

import java.util.ArrayList;

public final class CombatGrid extends AppCompatActivity {

    private static final String TAG = CombatGrid.class.getSimpleName();
    private static final int NEW_PAWN_REQUEST = 1;
    private static final int NEW_OBSTACLE_REQUEST=1;

    private ActionMode mActionMode;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private SpeedDialView mSpeedDialView;
    private NavigationView mNavigationView;
    private String colorNameline;

    private GridView mGridView;
    private static GridData mData; //= new GridData();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat_grid);

        Intent intent = getIntent();

        mGridView = new GridView(this/*, mData*/);

        FrameLayout gridContentFrame = (FrameLayout) this.findViewById(R.id.grid_content_frame);
        gridContentFrame.addView(mGridView);

        mGridView.setTokenManipulationMode();
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
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        // Attempt to load map data.  If we can't load map data, create a new map.
        String filename = sharedPreferences.getString("filename", null);
        if (filename == null) {
            GridData.clear();
            mData = GridData.getInstance();
            mGridView.setData(mData);
            setTitle("untitled map");
        } else {
            loadMap(filename);
            setTitle(filename);
        }
        mData.grid = Grid.createGrid(mData.grid.gridSpaceToWorldSpaceTransformer());

        mGridView.invalidate();
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String filename = sharedPreferences.getString("filename", null);
        if (filename == null) {
            setFilenamePreference("tmp");
            filename = "tmp";
        }
        saveMap(filename);
    }

    private void saveMap(String name) {
        // TODO Auto-generated method stub
        try {
            new DataManager(getApplicationContext()).saveMapName(name);
            DataManager dm = new DataManager(getApplicationContext());
            dm.saveMapName(name);
            dm.saveImage(name + ".preview", mGridView.getPreview());
        } catch (Exception e) {
            reportIOException(e, "save");
            GridData.clear();
            setFilenamePreference(null);
        }
    }

    private void reportIOException(Exception e, String attemptedAction) {
        Log.e(TAG, "Could not " + attemptedAction + " file.  Reason:");
        Log.e(TAG, e.toString());
        e.printStackTrace();
        Toast toast = Toast.makeText(this.getApplicationContext(), "Could not " + attemptedAction + " file.  Reason:" + e.toString(), Toast.LENGTH_LONG);
        toast.show();
    }
    private void setFilenamePreference(String newFilename) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        // Persist the filename that we saved to so that we can load from that file again.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("filename", newFilename);
        editor.commit();
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


                    AlertDialog.Builder builder = new AlertDialog.Builder(CombatGrid.this);

                    builder.setTitle("Colors");

                    ArrayList<ColorPicker_Item> listitem = new ArrayList<>();
                    ColorPicker_Item col1 = new ColorPicker_Item(R.drawable.pomodoro, "Pomodoro");
                    ColorPicker_Item col2 = new ColorPicker_Item(R.drawable.mandarino,"Mandarino");
                    ColorPicker_Item col3 = new ColorPicker_Item(R.drawable.banana,"Banana");
                    ColorPicker_Item col4 = new ColorPicker_Item(R.drawable.basilico,"Basilico");
                    ColorPicker_Item col5 = new ColorPicker_Item(R.drawable.salvia,"Salvia");
                    ColorPicker_Item col6 = new ColorPicker_Item(R.drawable.pavone, "Pavone");
                    ColorPicker_Item col7 = new ColorPicker_Item(R.drawable.mirtillo,"Mirtillo");
                    ColorPicker_Item col8 = new ColorPicker_Item(R.drawable.lavanda,"Lavanda");
                    ColorPicker_Item col9 = new ColorPicker_Item(R.drawable.vinaccia,"Vinaccia");
                    ColorPicker_Item col10 = new ColorPicker_Item(R.drawable.fenicottero,"Fenicottero");
                    ColorPicker_Item col11 = new ColorPicker_Item(R.drawable.grafite,"Grafite");
                    ColorPicker_Item col12 = new ColorPicker_Item(R.drawable.classic,"Colore predefinito");

                    listitem.add(col1);
                    listitem.add(col2);
                    listitem.add(col3);
                    listitem.add(col4);
                    listitem.add(col5);
                    listitem.add(col6);
                    listitem.add(col7);
                    listitem.add(col8);
                    listitem.add(col9);
                    listitem.add(col10);
                    listitem.add(col11);
                    listitem.add(col12);
                    ColorPickerListViewAdapter clad = new ColorPickerListViewAdapter(CombatGrid.this, R.layout.colorpickeritem,listitem);

                    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.setAdapter(clad, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ColorPicker_Item element = clad.getItem(which);
                            AlertDialog.Builder builderInner = new AlertDialog.Builder(CombatGrid.this);
                            builderInner.setMessage(element.getS());
                            colorNameline = element.getS();
                            mGridView.setNewLineColor(fromColorPicker(element.getS()));

                            builderInner.setTitle("Your Selected Item is");
                            builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,int which) {
                                    dialog.dismiss();
                                }
                            });
                            builderInner.show();
                        }
                    });
                    AlertDialog alertDialog = builder.create();

                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1050,1300);


                    return true;
                case R.id.line_stroke:
                    /**LINE STROKE*/
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(CombatGrid.this);
                    builder1.setTitle("Select line stroke width").setItems(R.array.line_stroke, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mGridView.setNewLineStroke(i);
                            //mGridView.setDrawMode();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder1.show();
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

    private void setTitle(String gridName) {

        if (this.getSupportActionBar() == null) return;
        this.getSupportActionBar().setTitle(gridName);
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
                        mDrawerLayout.closeDrawers();
                        AlertDialog.Builder builder = new AlertDialog.Builder(CombatGrid.this);
                        LayoutInflater inflater = CombatGrid.this.getLayoutInflater();
                        View mView = inflater.inflate(R.layout.save_dialog, null);
                        final EditText mText = (EditText) mView.findViewById(R.id.grid_name);
                        builder.setTitle("Grid name").setView(mView)
                                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                String name = mText.getText().toString();
                                saveMap(name);
                                setFilenamePreference(name);
                            }
                        })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                        break;
                    case R.id.nav_load:
                        startActivity(new Intent(CombatGrid.this, GridsList.class));
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
        switch (id) {
            case R.id.eraser_tool:
                if (mActionMode != null) {
                    mSpeedDialView.close();
                    return true;
                } else {
                    mActionMode = startSupportActionMode(mActionModeEraserCallback);
                    Toast.makeText(CombatGrid.this, "Erase Tool clicked", Toast.LENGTH_SHORT).show();
                    mGridView.setEraseMode();
                    mSpeedDialView.close();
                }
                return true;
            case R.id.ruler_tool:
                Toast.makeText(CombatGrid.this, "Ruler Tool clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.zoom_to_fit:
                mData.zoomToFit(mGridView.getWidth(), mGridView.getHeight());
                return true;
            case R.id.clear_all:
                GridData.clear();
                setFilenamePreference(null);
                mData = GridData.getInstance();
                mGridView.setData(mData);
                return true;
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
                float size = data.getFloatExtra("size", 1.0f);
                mGridView.placeToken(new Token(color, name, size));
            } else if (resultCode == RESULT_CANCELED) {

            }
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String filename = sharedPreferences.getString("filename", null);
        if (filename == null) {
            setFilenamePreference("tmp");
            filename = "tmp";
        }
        saveMap(filename);
    }

    public void loadMap(String name) {
        try {
            new DataManager(getApplicationContext()).loadMapName(name);
        } catch (Exception e) {
            reportIOException(e, "load");
            GridData.clear();
            setFilenamePreference(null);
        }
        mData = GridData.getInstance();
        mGridView.setData(mData);
    }

    private int fromColorPicker(String colorName) {
        switch (colorName) {
            case "Pomodoro":
                return Color.rgb(220, 33, 39);
            case "Mandarino":
                return Color.rgb(244, 81, 30);
            case "Banana":
                return Color.rgb(251, 215, 91);
            case "Basilico":
                return Color.rgb(81, 183, 73);
            case "Salvia":
                return Color.rgb(122, 231, 191);
            case "Pavone":
                return Color.rgb(84, 132, 237);
            case "Mirtillo":
                return Color.rgb(63, 81, 181);
            case "Lavanda":
                return Color.rgb(164, 189, 252);
            case "Vinaccia":
                return Color.rgb(142, 36, 170);
            case "Fenicottero":
                return Color.rgb(230, 124, 115);
            case "Grafite":
                return Color.rgb(97, 97, 97);
            default:
                return Color.rgb(164, 189, 252);
        }
    }
}
