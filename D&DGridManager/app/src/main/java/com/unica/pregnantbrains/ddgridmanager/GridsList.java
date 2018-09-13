package com.unica.pregnantbrains.ddgridmanager;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.Toolbar;

import com.unica.pregnantbrains.ddgridmanager.adapters.SaveFileAdapter;
import com.unica.pregnantbrains.ddgridmanager.data.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridsList extends AppCompatActivity /*implements ActionMode.Callback*/ {
    private ActionMode actionMode;
    private boolean isMultiSelect = false;
    private List<String> mSavedFiles;
    private List<Bitmap> mSavedImages;
    private ListView listView;
    private android.support.v7.widget.Toolbar mToolbar;
    private List<String> list;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grids_list);


        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.nav_action_bar);
        setSupportActionBar(mToolbar);

        List<Map<String, String>> filenameMapList = new ArrayList<Map<String, String>>();

        DataManager dataMgr = new DataManager(this.getApplicationContext());

        mSavedFiles = dataMgr.savedFiles();
        mSavedImages = new ArrayList<Bitmap>();
        list = new ArrayList<String>();
        for (String mapName : mSavedFiles) {
            /*Map<String, String> data = new HashMap<String, String>();
            data.put("mapName", mapName);
            filenameMapList.add(data);*/
            try {
                mSavedImages.add(dataMgr.loadImage(mapName + ".preview.jpg")); //TODO: make this code not suck (preview filename creation logic should be in DataManager)
            } catch (IOException e) {
                //no-op
            }
        }

        SaveFileAdapter adapter = new SaveFileAdapter(GridsList.this, mSavedFiles, mSavedImages);
        listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                final int checkedCount = listView.getCheckedItemCount();
                actionMode.setTitle(checkedCount + " items selected");
                adapter.toggleSelection(i);

                //count += 1;
                //list.add(mSavedFiles.get(i));
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.grid_list_context_menu, menu);

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.delete_grid:
                        SparseBooleanArray selected = adapter.getSelectedIds();
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                String selectedItem = adapter.getItem(selected.keyAt(i));
                                dataMgr.deleteSaveFile(selectedItem);
                                adapter.remove(selectedItem);
                            }
                        }
                        /*for (String s : list) {
                            adapter.remove(s);
                        }
                        Toast.makeText(getBaseContext(), count + " items deleted", Toast.LENGTH_SHORT).show();
                        count = 0;*/
                        actionMode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                adapter.removeSelection();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setFilenamePreference(mSavedFiles.get(position));
                startActivity(new Intent(GridsList.this, CombatGrid.class));

            }
        });

        /*ListAdapter adapter = new SimpleAdapter(
                /*this.getApplicationContext()*getListView().getContext(),
                filenameMapList,
                R.layout.saved_grid_list,
                new String[] {"mapName"},
                new int[] {R.id.saved_grid_name}
        );*/

        //setListAdapter(adapter);
    }

    /*protected void onListItemClick(ListView i, View v, int position, long id) {
        setFilenamePreference(mSavedFiles.get(position));
        startActivity(new Intent(this, CombatGrid.class));
        //finish();
    }*/

    private void setFilenamePreference(String newFilename) {
        //TODO(tim.bocek): De-dupe from method in CombatMap
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        // Persist the filename that we saved to so that we can load from that file again.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("filename", newFilename);
        editor.commit();
    }

    public void newGrid(View view) {
        Intent intent = new Intent(this, CombatGrid.class);
        startActivity(intent);
    }
}
