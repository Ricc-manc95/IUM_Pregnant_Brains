package com.unica.pregnantbrains.ddgridmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.unica.pregnantbrains.ddgridmanager.adapters.ColorPickerListViewAdapter;
import com.unica.pregnantbrains.ddgridmanager.adapters.ColorPicker_Item;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.Token;

import java.util.ArrayList;
import java.util.List;

public class PawnCreation extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText text;
    private Button changecolor;

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

        changecolor=findViewById(R.id.colorbutton);

        changecolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(PawnCreation.this);
                builderSingle.setIcon(R.drawable.ic_eraser_white_24dp);
                builderSingle.setTitle("Select One Name:-");

                ListView lv = (ListView) view.findViewById(R.id.colorlistview);

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

                ColorPickerListViewAdapter clad = new ColorPickerListViewAdapter(PawnCreation.this, R.layout.colorpickeritem,listitem);

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(clad, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ColorPicker_Item element = clad.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(PawnCreation.this);
                        builderInner.setMessage(element.getS());
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
                builderSingle.show();
            }
        });
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
                text = (EditText) findViewById(R.id.pawn_name);
                Intent returnIntent = new Intent();
                //returnIntent.putExtra("result", result);
                setResult(CombatGrid.RESULT_OK, returnIntent);
                returnIntent.putExtra("result", text.getText().toString());
                //setResult(CombatGrid.RESULT_CANCELED, returnIntent);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
