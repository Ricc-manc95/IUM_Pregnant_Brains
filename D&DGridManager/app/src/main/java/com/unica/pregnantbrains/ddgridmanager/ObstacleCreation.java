package com.unica.pregnantbrains.ddgridmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ObstacleCreation extends AppCompatActivity {
    private Toolbar mToolbarObstacle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_obstacle_creation);

        /**Toolbar*/
        mToolbarObstacle = (Toolbar) findViewById(R.id.creation_bar_obstacle);


        setSupportActionBar(mToolbarObstacle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("New Obstacle");
        mToolbarObstacle.setTitleTextColor(0xFFFFFFFF);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.obstacle_creation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(ObstacleCreation.this, CombatGrid.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.save_obstacle:
                /**text = (EditText) findViewById(R.id.pawn_name);
                Intent returnIntent = new Intent();
                //returnIntent.putExtra("result", result);
                setResult(CombatGrid.RESULT_OK, returnIntent);
                returnIntent.putExtra("result", text.getText().toString());
                //setResult(CombatGrid.RESULT_CANCELED, returnIntent);
                finish();*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
