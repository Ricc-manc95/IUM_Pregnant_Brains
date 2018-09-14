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
import android.widget.ListView;

import com.unica.pregnantbrains.ddgridmanager.adapters.ColorPickerListViewAdapter;
import com.unica.pregnantbrains.ddgridmanager.adapters.ColorPicker_Item;

import java.util.ArrayList;

public class AreaCreation extends AppCompatActivity {
    private Toolbar mToolbarArea;
    private Button buttoncolorArea;
    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_area_creation);

        /**Toolbar*/
        mToolbarArea = (Toolbar) findViewById(R.id.creation_bar_area);


        setSupportActionBar(mToolbarArea);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("New Area");
        mToolbarArea.setTitleTextColor(0xFFFFFFFF);
        buttoncolorArea = findViewById(R.id.buttoncolorarea);

        buttoncolorArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(AreaCreation.this);
                builderSingle.setIcon(R.drawable.ic_eraser_white_24dp);
                builderSingle.setTitle("Colors");

                ListView lvc = (ListView) view.findViewById(R.id.colorlistview);

                ArrayList<ColorPicker_Item> listitemar = new ArrayList<>();
                ColorPicker_Item col1 = new ColorPicker_Item(R.drawable.pomodoro, "Tomato");
                ColorPicker_Item col2 = new ColorPicker_Item(R.drawable.mandarino, "Tangerine");
                ColorPicker_Item col3 = new ColorPicker_Item(R.drawable.banana, "Banana");
                ColorPicker_Item col4 = new ColorPicker_Item(R.drawable.basilico, "Basil");
                ColorPicker_Item col5 = new ColorPicker_Item(R.drawable.salvia, "Sage");
                ColorPicker_Item col6 = new ColorPicker_Item(R.drawable.pavone, "Peacock");
                ColorPicker_Item col7 = new ColorPicker_Item(R.drawable.mirtillo, "Blueberry");
                ColorPicker_Item col8 = new ColorPicker_Item(R.drawable.lavanda, "Lavender");
                ColorPicker_Item col9 = new ColorPicker_Item(R.drawable.vinaccia, "Grape");
                ColorPicker_Item col10 = new ColorPicker_Item(R.drawable.fenicottero, "Flamingo");
                ColorPicker_Item col11 = new ColorPicker_Item(R.drawable.grafite, "Graphite");
                ColorPicker_Item col12 = new ColorPicker_Item(R.drawable.classic, "Default color");

                listitemar.add(col1);
                listitemar.add(col2);
                listitemar.add(col3);
                listitemar.add(col4);
                listitemar.add(col5);
                listitemar.add(col6);
                listitemar.add(col7);
                listitemar.add(col8);
                listitemar.add(col9);
                listitemar.add(col10);
                listitemar.add(col11);
                listitemar.add(col12);

                ColorPickerListViewAdapter cladar = new ColorPickerListViewAdapter(AreaCreation.this, R.layout.colorpickeritem, listitemar);

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builderSingle.setAdapter(cladar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ColorPicker_Item element = cladar.getItem(which);
                        //AlertDialog.Builder builderInner = new AlertDialog.Builder(AreaCreation.this);
                        //builderInner.setMessage(element.getS());
                        color = element.getS();
                       // builderInner.setTitle("Your Selected Item is");
                        /*builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })*/;
                        //builderInner.show();
                    }
                });
                builderSingle.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.area_creation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(AreaCreation.this, CombatGrid.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.save_area:
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
            case "Tomato":
                return Color.rgb(220, 33, 39);
            case "tangerine":
                return Color.rgb(244, 81, 30);
            case "Banana":
                return Color.rgb(251, 215, 91);
            case "Basil":
                return Color.rgb(81, 183, 73);
            case "Sage":
                return Color.rgb(122, 231, 191);
            case "Peacock":
                return Color.rgb(84, 132, 237);
            case "Blueberry":
                return Color.rgb(63, 81, 181);
            case "Lavender":
                return Color.rgb(164, 189, 252);
            case "Grape":
                return Color.rgb(142, 36, 170);
            case "Flamingo":
                return Color.rgb(230, 124, 115);
            case "Graphite":
                return Color.rgb(97, 97, 97);
            default:
                return Color.rgb(164, 189, 252);
        }
    }

}
