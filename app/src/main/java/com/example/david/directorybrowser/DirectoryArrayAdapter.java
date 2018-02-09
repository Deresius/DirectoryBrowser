package com.example.david.directorybrowser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom ArrayAdapter class for directoryEntries to be shown in the main activity list view.
 *
 * Created by David Spurlock and James Looney.
 */

public class DirectoryArrayAdapter extends ArrayAdapter<DirectoryEntry>{

    private ArrayList<DirectoryEntry> directoryEntries = new ArrayList<>();

    DirectoryArrayAdapter(Context context, int textViewResourceId, ArrayList<DirectoryEntry> objects) {
        super(context, textViewResourceId, objects);
        directoryEntries = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_view_files, null);
        TextView textView = view.findViewById(R.id.textView);
        TextView sizeView = view.findViewById(R.id.textSizeView); //TODO
        ImageView imageView = view.findViewById(R.id.imageView);
        textView.setText(directoryEntries.get(position).getName());
        sizeView.setText(directoryEntries.get(position).getSize()); //TODO
        imageView.setImageResource(directoryEntries.get(position).getIcon());
        return view;

    }

}