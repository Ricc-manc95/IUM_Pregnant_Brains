package com.unica.pregnantbrains.ddgridmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.unica.pregnantbrains.ddgridmanager.adapters.ColorPickerListViewAdapter;
import com.unica.pregnantbrains.ddgridmanager.adapters.ColorPicker_Item;

import java.util.ArrayList;

public class ObstacleCreation extends AppCompatActivity {
    private Toolbar mToolbarObstacle;
    private Button buttoncolorobstacle;
    private String color;

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
        buttoncolorobstacle = findViewById(R.id.buttoncolorobstacle);

        buttoncolorobstacle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(ObstacleCreation.this);
                builderSingle.setIcon(R.drawable.ic_eraser_white_24dp);
                builderSingle.setTitle("Colors");

                ListView lvc = (ListView) view.findViewById(R.id.colorlistview);

                ArrayList<ColorPicker_Item> listitemob = new ArrayList<>();
                ColorPicker_Item col1 = new ColorPicker_Item(R.drawable.pomodoro, "Pomodoro");
                ColorPicker_Item col2 = new ColorPicker_Item(R.drawable.mandarino, "Mandarino");
                ColorPicker_Item col3 = new ColorPicker_Item(R.drawable.banana, "Banana");
                ColorPicker_Item col4 = new ColorPicker_Item(R.drawable.basilico, "Basilico");
                ColorPicker_Item col5 = new ColorPicker_Item(R.drawable.salvia, "Salvia");
                ColorPicker_Item col6 = new ColorPicker_Item(R.drawable.pavone, "Pavone");
                ColorPicker_Item col7 = new ColorPicker_Item(R.drawable.mirtillo, "Mirtillo");
                ColorPicker_Item col8 = new ColorPicker_Item(R.drawable.lavanda, "Lavanda");
                ColorPicker_Item col9 = new ColorPicker_Item(R.drawable.vinaccia, "Vinaccia");
                ColorPicker_Item col10 = new ColorPicker_Item(R.drawable.fenicottero, "Fenicottero");
                ColorPicker_Item col11 = new ColorPicker_Item(R.drawable.grafite, "Grafite");
                ColorPicker_Item col12 = new ColorPicker_Item(R.drawable.classic, "Colore predefinito");

                listitemob.add(col1);
                listitemob.add(col2);
                listitemob.add(col3);
                listitemob.add(col4);
                listitemob.add(col5);
                listitemob.add(col6);
                listitemob.add(col7);
                listitemob.add(col8);
                listitemob.add(col9);
                listitemob.add(col10);
                listitemob.add(col11);
                listitemob.add(col12);

                ColorPickerListViewAdapter cladob = new ColorPickerListViewAdapter(ObstacleCreation.this, R.layout.colorpickeritem, listitemob);

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builderSingle.setAdapter(cladob, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ColorPicker_Item element = cladob.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(ObstacleCreation.this);
                        builderInner.setMessage(element.getS());
                        color = element.getS();
                        builderInner.setTitle("Your Selected Item is");
                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builderInner.show();
                    }
                });
                builderSingle.show();
            }
        });

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
    private int fromColorPicker(String color) {
        switch (color) {
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