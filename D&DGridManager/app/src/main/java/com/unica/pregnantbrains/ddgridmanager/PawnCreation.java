package com.unica.pregnantbrains.ddgridmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.unica.pregnantbrains.ddgridmanager.adapters.ColorPickerListViewAdapter;
import com.unica.pregnantbrains.ddgridmanager.adapters.ColorPicker_Item;

import java.util.ArrayList;

public class PawnCreation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar mToolbar;
    private EditText text;
    private Button changecolor;

    private String name;
    private int color;
    private String colorName;
    private float size;

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

        Spinner mSpinner = (Spinner) this.findViewById(R.id.pawn_size);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.size_pawn, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

        changecolor = findViewById(R.id.colorbutton);

        changecolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(PawnCreation.this);
                builderSingle.setIcon(R.drawable.ic_eraser_white_24dp);
                builderSingle.setTitle("Colors");

                ListView lv = (ListView) view.findViewById(R.id.colorlistview);

                ArrayList<ColorPicker_Item> listitem = new ArrayList<>();
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

                ColorPickerListViewAdapter clad = new ColorPickerListViewAdapter(PawnCreation.this, R.layout.colorpickeritem, listitem);

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
                        colorName = element.getS();
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
                returnIntent.putExtra("name", text.getText().toString());
                returnIntent.putExtra("color", fromColorPicker(colorName));
                returnIntent.putExtra("size", size);
                //setResult(CombatGrid.RESULT_CANCELED, returnIntent);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
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

    private float fromSpinner(String size) {
        switch (size) {
            case "Fine":
                return 0.125f;
            case "Diminutive":
                return 0.25f;
            case "Tiny":
                return 0.5f;
            case "Small":
                return 0.75f;
            case "Medium":
                return 1.0f;
            case "Large":
                return 2.0f;
            case "Huge":
                return 3.0f;
            case "Gargantuan":
                return 4.0f;
            case "Colossal":
                return 5.0f;
            default:
                return 1.0f;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String s = adapterView.getItemAtPosition(i).toString();
        size = fromSpinner(s);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        size = fromSpinner("Medium");
    }
}

