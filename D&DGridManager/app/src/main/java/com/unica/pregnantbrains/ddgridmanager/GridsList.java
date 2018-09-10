package com.unica.pregnantbrains.ddgridmanager;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.unica.pregnantbrains.ddgridmanager.data.DataManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridsList extends ListActivity {
    private List<String> mSavedFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grids_list);

        List<Map<String, String>> filenameMapList = new ArrayList<Map<String, String>>();

        DataManager dataMgr = new DataManager(this.getApplicationContext());

        mSavedFiles = dataMgr.savedFiles();
        for (String mapName : mSavedFiles) {
            Map<String, String> data = new HashMap<String, String>();
            data.put("mapName", mapName);
            filenameMapList.add(data);
        }

        ListAdapter adapter = new SimpleAdapter(
                /*this.getApplicationContext()*/getListView().getContext(),
                filenameMapList,
                R.layout.saved_grid_list,
                new String[] {"mapName"},
                new int[] {R.id.saved_grid_name}
        );

        setListAdapter(adapter);
        //getListView().setAdapter(adapter);
    }

    protected void onListItemClick(ListView i, View v, int position, long id) {
        setFilenamePreference(mSavedFiles.get(position));
        startActivity(new Intent(this, CombatGrid.class));
        //finish();
    }

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
