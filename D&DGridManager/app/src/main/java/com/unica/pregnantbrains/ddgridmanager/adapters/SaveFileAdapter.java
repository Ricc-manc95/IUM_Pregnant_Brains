package com.unica.pregnantbrains.ddgridmanager.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.unica.pregnantbrains.ddgridmanager.R;

import java.util.List;

public class SaveFileAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> saveFileName;
    private List<Bitmap> saveFileImage;
    private SparseBooleanArray mSelectedItemsIds;

    public SaveFileAdapter(Context context, List<String> saveFileName, List<Bitmap> saveFileImage) {
        super(context, R.layout.saved_grid_list, saveFileName);
        this.context = context;
        this.saveFileName = saveFileName;
        this.saveFileImage = saveFileImage;
        this.mSelectedItemsIds = new SparseBooleanArray();

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View v = inflater.inflate(R.layout.saved_grid_list, null, true);
        TextView txtTitle = (TextView) v.findViewById(R.id.saved_grid_name);

        ImageView imageView = (ImageView) v.findViewById(R.id.saved_grid_image);
        txtTitle.setText(saveFileName.get(position));

        imageView.setImageBitmap(saveFileImage.get(position));
        return v;
    }

    @Override
    public void remove(String object) {
        saveFileName.remove(object);
        //saveFileImage.remove()
        notifyDataSetChanged();
    }

    public List<String> getSaveFileName() {
        return saveFileName;
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }





    /*private String[] saveFile;

    public SaveFileAdapter (Context context, int textViewResourceId, String[] saveFile) {
        super (context, textViewResourceId, saveFile);
        this.saveFile = saveFile;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.saved_grid_list, null);
        }

        String it = saveFile[position];
        if (it != null) {
            ImageView iv = (ImageView) v.findViewById(R.id.saved_grid_image);
            if (iv != null) {
                iv.setImageDrawable(it.getImage());
            }
        }

        return v;
    }
}

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        this.adapter.getItem(position).click(this.getApplicationContext());
    }*/
}
