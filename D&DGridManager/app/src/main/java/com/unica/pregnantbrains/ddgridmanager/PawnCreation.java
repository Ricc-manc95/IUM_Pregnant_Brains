package com.unica.pregnantbrains.ddgridmanager;

import android.content.Intent;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.unica.pregnantbrains.ddgridmanager.model.primitives.Token;

public class PawnCreation extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pawn_creation);

        /**Toolbar*/
        mToolbar = (Toolbar) findViewById(R.id.creation_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pawn_creation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(PawnCreation.this, CombatGrid.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.save_pawn:
                Intent returnIntent = new Intent();
                //returnIntent.putExtra("result", result);
                setResult(CombatGrid.RESULT_OK, returnIntent);
                setResult(CombatGrid.RESULT_CANCELED, returnIntent);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
