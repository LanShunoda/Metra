package com.plorial.metra.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.plorial.metra.R;
import com.plorial.metra.model.pojo.Album;
import com.plorial.metra.view.adapters.PhotosAdapter;

/**
 * Created by plorial on 1/8/17.
 */

public class PhotosActivity extends AppCompatActivity implements PhotosView {

    public static final String TAG = PhotosActivity.class.getSimpleName();

    private Album album;
    private GridView gridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        album = getIntent().getParcelableExtra(TAG);
        gridView = (GridView) findViewById(R.id.grid_view_photos);
        initHeader();
        PhotosAdapter adapter = new PhotosAdapter(this, R.layout.photo_item, album.getPhotos());
        gridView.setAdapter(adapter);
    }

    private void initHeader(){
        TextView tvOverall = (TextView) findViewById(R.id.tvOverall);
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvDate = (TextView) findViewById(R.id.tvDate);
        tvOverall.setText(getString(R.string.overall) + album.getPhotos().size());
        tvName.setText(getString(R.string.name) + album.getName());
        tvDate.setText(getString(R.string.date) + album.getDate());
    }

    @Override
    public void showError(String error) {
        Log.e(TAG, error);
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
