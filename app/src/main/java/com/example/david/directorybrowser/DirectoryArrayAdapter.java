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
 * Created by David on 1/25/2018.
 */

public class DirectoryArrayAdapter extends ArrayAdapter<DirectoryEntry>{

    ArrayList<DirectoryEntry> directoryEntries = new ArrayList<>();

    public DirectoryArrayAdapter(Context context, int textViewResourceId, ArrayList<DirectoryEntry> objects) {
        super(context, textViewResourceId, objects);
        directoryEntries = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_view_files, null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        textView.setText(directoryEntries.get(position).getName());
        imageView.setImageResource(directoryEntries.get(position).getIcon());
        return view;

    }

}