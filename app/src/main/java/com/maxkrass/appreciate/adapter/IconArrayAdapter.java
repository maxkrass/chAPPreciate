package com.maxkrass.appreciate.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.maxkrass.appreciate.Tools;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Max on 18.12.2015 for APPreciate for APPreciate.
 */
public class IconArrayAdapter extends ArrayAdapter<String> {

    private List<Integer> images;

    public IconArrayAdapter(Context context, String[] items, Integer[] images) {
        super(context, android.R.layout.select_dialog_item, items);
        this.images = Arrays.asList(images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        Drawable d = getContext().getResources().getDrawable(images.get(position));
        if (d != null) {
            d.setAlpha(117);
            textView.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
            textView.setCompoundDrawablePadding((int) Tools.dpToPixels(getContext(), 16));
            textView.setPadding((int) Tools.dpToPixels(getContext(), 24), 0, 0, 0);
        }
        return view;
    }

}