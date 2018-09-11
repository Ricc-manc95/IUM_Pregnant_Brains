package com.unica.pregnantbrains.ddgridmanager.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unica.pregnantbrains.ddgridmanager.R;

public class SaveFileButton extends LinearLayout{
    ImageView preview;
    TextView text;

    public SaveFileButton(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.saved_grid_list, this);
        //preview = (ImageView) findViewById(R.id.saved_map_preview);
        //text = (TextView) findViewById(R.id.saved_map_file_name);
        preview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                performClick();
            }
        });
    }

    public void setFileName(String name) {
        text.setText(name);
    }

    public void setPreviewImage(Bitmap image) {
        preview.setImageBitmap(image);
    }

    public String getFileName() {
        return text.getText().toString();
    }
}
