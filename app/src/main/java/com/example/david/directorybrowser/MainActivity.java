package com.example.david.directorybrowser;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    File[] currentFile;
    ArrayList<DirectoryEntry> entries;
    DirectoryBuilder builder;
    DirectoryEntry location;
    String currentLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        requestPermission();
        location = null;

        listView = (ListView) findViewById(R.id.DirectoryListView);

        currentLocation = "/sdcard/";

        this.setTitle(new File(currentLocation).getName());
        currentFile = new File(currentLocation).listFiles();
        builder = new DirectoryBuilder();
        entries = builder.buildDirectory(currentFile);

        updateView();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                location = (DirectoryEntry) listView.getItemAtPosition(position);
                currentFile = new File(location.getPath()).listFiles();

                currentLocation = location.getPath();
                entries = builder.buildDirectory(currentFile);

                updateView();
                updateTitle();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            if (currentLocation.equals("/sdcard/" ) || currentLocation.equals("/sdcard")) {
                onBackPressed();

            }else {
                String lastDirectory = currentLocation;

                int index = lastDirectory.lastIndexOf("/");
                if (index > 0) {
                    lastDirectory = lastDirectory.substring(0, index);
                }

                currentFile = new File(lastDirectory).listFiles();
                currentLocation = lastDirectory;
                entries = builder.buildDirectory(currentFile);

                updateView();
                updateTitle();
            }

            return true;
        }
        return false;
    }

    private void requestPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
    }

    private void updateView() {
        DirectoryArrayAdapter mine = new DirectoryArrayAdapter(this, R.layout.list_view_files, entries);
        listView.setAdapter(mine);
    }
    private void updateTitle() {
        this.setTitle(currentLocation);
    }

}
