package com.plorial.metra.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.plorial.metra.R;
import com.plorial.metra.model.pojo.Album;
import com.plorial.metra.presenter.AlbumsListPresenter;
import com.plorial.metra.view.adapters.AlbumAdapter;
import com.plorial.metra.view.dialogs.AddAlbumDialog;

import java.util.List;

public class AlbumsActivity extends AppCompatActivity implements AlbumsView, AdapterView.OnItemClickListener {

    private final static String TAG = AlbumsActivity.class.getSimpleName();

    private AlbumAdapter adapter;
    private AlbumsListPresenter presenter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.list_view);
        adapter = new AlbumAdapter(this,R.layout.album_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        presenter = new AlbumsListPresenter(this);
        presenter.loadAlbums();
    }

    @Override
    public void showAlbums(List<Album> albums) {
        adapter.clear();
        adapter.addAll(albums);
    }

    @Override
    public void addAlbum(String name) {
        presenter.addAlbum(name);
    }

    @Override
    public void showError(String error) {
        Log.e(TAG, error);
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onAlbumClick(adapter.getItem(position));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_albums, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            AddAlbumDialog albumDialog = new AddAlbumDialog();
            albumDialog.show(getFragmentManager(),TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
