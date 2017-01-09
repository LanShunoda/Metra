package com.plorial.metra.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by plorial on 3/24/16.
 */
public class DownloadImageTask extends AsyncTask<String, String, Bitmap> {

    private ImageView imageView;
    private TextView tvDate;
    private ProgressBar progressBar;

    public DownloadImageTask(ImageView imageView, TextView tvDate, ProgressBar progressBar) {
        this.imageView = imageView;
        this.tvDate = tvDate;
        this.progressBar = progressBar;
    }

    protected Bitmap doInBackground(String... url) {
        String urlDisplay = url[0];
        Bitmap icon = null;
        InputStream in = null;
        try {
            URL url1 = new URL(urlDisplay);
            in = url1.openStream();
            ExifInterface exifInterface = new ExifInterface(url1.getPath());
            String date = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
            publishProgress(date);
            icon = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return icon;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        tvDate.setText(values[0]);
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
        progressBar.setVisibility(View.GONE);
    }
}
