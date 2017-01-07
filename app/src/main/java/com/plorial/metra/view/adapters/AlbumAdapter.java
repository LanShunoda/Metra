package com.plorial.metra.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.plorial.metra.R;
import com.plorial.metra.model.pojo.Album;

/**
 * Created by plorial on 1/7/17.
 */

public class AlbumAdapter extends ArrayAdapter<Album> {

    private Context context;
    private int resource;

    public AlbumAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);
        }
        Album album = super.getItem(position);
        if(album != null){
            TextView name = (TextView) view.findViewById(R.id.tvName);
            TextView date = (TextView) view.findViewById(R.id.tvDate);
            name.setText(album.getName());
            date.setText(album.getDate());
        }
        return view;
    }
}
