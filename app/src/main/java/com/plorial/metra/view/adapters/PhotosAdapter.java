package com.plorial.metra.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.plorial.metra.R;
import com.plorial.metra.model.DownloadImageTask;

import java.util.Map;

/**
 * Created by plorial on 1/9/17.
 */

public class PhotosAdapter extends MapAdapter<String, String> {

    private Context context;
    private int resource;

    public PhotosAdapter(Context context, int resource, Map<String, String> data) {
        super(data);
        this.context = context;
        this.resource = resource;
    }

    @Override
    protected View onGetView(final int pos, String key, String value, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);
        }
        TextView name = (TextView) view.findViewById(R.id.tvPhotoName);
        TextView date = (TextView) view.findViewById(R.id.tvPhotoDate);
        ImageView image = (ImageView) view.findViewById(R.id.imageViewPhoto);
        ProgressBar pb = (ProgressBar) view.findViewById(R.id.pbDownloadingImage);
        name.setText(key);
        DownloadImageTask imageTask = new DownloadImageTask(image, date, pb);
        imageTask.execute(value);
        final Button delete = (Button) view.findViewById(R.id.bDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GridView) parent).performItemClick(v, pos, delete.getId());
            }
        });

        return view;
    }

}
