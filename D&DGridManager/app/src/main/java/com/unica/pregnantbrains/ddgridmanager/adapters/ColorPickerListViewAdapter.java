package com.unica.pregnantbrains.ddgridmanager.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unica.pregnantbrains.ddgridmanager.R;

import java.util.ArrayList;

public class ColorPickerListViewAdapter extends ArrayAdapter<ColorPicker_Item>
{
    private Context context;

    public ColorPickerListViewAdapter(Context context, int ResourceId, ArrayList<ColorPicker_Item> lista) {
        super(context, ResourceId, lista);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.colorpickeritem, null);

        ImageView img = (ImageView)convertView.findViewById(R.id.immcolor);
        TextView text = (TextView)convertView.findViewById(R.id.textViewColor);

        ColorPicker_Item cpI = getItem(position);
        img.setImageResource(cpI.getColor());
        text.setText(cpI.getS());

        return convertView;
    }
}
