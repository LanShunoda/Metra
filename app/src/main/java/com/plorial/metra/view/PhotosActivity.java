package com.plorial.metra.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.plorial.metra.R;
import com.plorial.metra.model.pojo.Album;
import com.plorial.metra.presenter.PhotosPresenter;
import com.plorial.metra.view.adapters.PhotosAdapter;

import java.io.File;

/**
 * Created by plorial on 1/8/17.
 */

public class PhotosActivity extends AppCompatActivity implements PhotosView {

    public static final String TAG = PhotosActivity.class.getSimpleName();
    private static final int CHOOSE_PHOTO_REQUEST = 1;
    private static final int MAKE_PHOTO_REQUEST = 2;

    private Album album;
    private Toolbar toolbar;
    private PhotosPresenter presenter;
    private PhotosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        album = getIntent().getParcelableExtra(TAG);
        GridView gridView = (GridView) findViewById(R.id.grid_view_photos);
        initHeader();
        adapter = new PhotosAdapter(this, R.layout.photo_item, album.getPhotos());
        gridView.setAdapter(adapter);
        presenter = new PhotosPresenter(this);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent backIntent = new Intent(this, AlbumsActivity.class);
                backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backIntent);
                return true;
            case R.id.action_choose_photo:
                Intent chosePhotoIntent = new Intent();
                chosePhotoIntent.setType("image/*");
                chosePhotoIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(chosePhotoIntent, "Select Picture"), CHOOSE_PHOTO_REQUEST);
                return true;
            case R.id.action_make_photo:
                Intent makePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (makePhotoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(makePhotoIntent, MAKE_PHOTO_REQUEST);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photos, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case CHOOSE_PHOTO_REQUEST:
                    Uri uri = data.getData();
                    presenter.addPhoto(uri);
                    break;
                case MAKE_PHOTO_REQUEST:
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    Uri bitmapUri = presenter.saveBitmapToFile(photo, Environment.getExternalStorageDirectory() + File.separator
                            + getApplicationContext().getPackageName());
                    presenter.addPhoto(bitmapUri);
                    break;
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public Album getAlbum() {
        return album;
    }

    @Override
    public void updatePhotos() {
        adapter.notifyDataSetChanged();
    }
}
