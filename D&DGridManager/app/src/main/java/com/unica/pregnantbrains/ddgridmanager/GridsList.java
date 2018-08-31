package com.unica.pregnantbrains.ddgridmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GridsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grids_list);
    }

    public void newGrid(View view) {
        Intent intent = new Intent(this, CombatGrid.class);
        startActivity(intent);
    }
}
