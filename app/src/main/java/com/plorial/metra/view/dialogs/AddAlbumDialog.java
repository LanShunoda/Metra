package com.plorial.metra.view.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.plorial.metra.R;
import com.plorial.metra.view.AlbumsView;

/**
 * Created by plorial on 1/7/17.
 */

public class AddAlbumDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.add_album_dialog,null));
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity activity = getActivity();
                if(activity instanceof AlbumsView){
                    EditText editText = (EditText) getDialog().findViewById(R.id.etAlbumName);
                    AlbumsView view = (AlbumsView) activity;
                    view.addAlbum(editText.getText().toString());
                }
            }
        });
        return builder.create();
    }
}
