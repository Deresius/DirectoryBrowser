package com.example.david.directorybrowser;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
/**
 *  The MainActivity is a file browser, with simple sorting and navigation.
 *
 *  Created by David Spurlock and James Looney.
 */
public class MainActivity extends AppCompatActivity {


    ListView listView;
    File[] currentFile;
    ArrayList<DirectoryEntry> entries;
    DirectoryBuilder builder;
    DirectoryEntry location;
    String currentLocation;


    private static String start = "/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        requestPermission();



    }

    private void setupPage() {
        location = null;

        listView = findViewById(R.id.DirectoryListView);

        currentLocation = start;
        this.setTitle(new File(currentLocation).getPath());
        currentFile = new File(currentLocation).listFiles();
        builder = new DirectoryBuilder();
        entries = builder.buildDirectory(currentFile);
        updateView();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                location = (DirectoryEntry) listView.getItemAtPosition(position);

                if(!location.isDirectory()) {
                    launchImplicitIntent(location.getPath());
                } else {
                    currentFile = new File(location.getPath()).listFiles();

                    currentLocation = location.getPath();
                    //bool somethign = location.getPath()
                    entries = builder.buildDirectory(currentFile);

                    updateView();
                    updateTitle();
                }



            }
        });
    }

    private void requestPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        } else {
            setupPage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupPage();
                }
            }

        }
    }

    public void launchImplicitIntent(String path) {
        String ext2 = path.substring(path.lastIndexOf(".") + 1);

        Uri uri = Uri.fromFile(new File(path));
        String extension = MimeTypeMap.getFileExtensionFromUrl(path);

        String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext2);
        if (mime == null) {
            mime = "*/*";
        }
        Log.v("Ext2", ext2);
        Log.v("extension", "MimeType Extension: " +extension);
        Log.v("mimetype", mime);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, mime);
        Intent chooser = Intent.createChooser(intent, "choose app");
        if(chooser.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }else{
            Log.v("null resolveActivity", mime+"\n"+uri);
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            if (currentLocation.equals(start)) {
                onBackPressed();

            }else {
                String lastDirectory = currentLocation;

                //int index = lastDirectory.lastIndexOf("/");
                //if (index > 0) {
                  //  lastDirectory = lastDirectory.substring(0, index);
                //}
                String parentName = new File(lastDirectory).getParentFile().getPath();
                currentFile = new File(parentName).listFiles();
                currentLocation = new File (parentName).getPath();
                entries = builder.buildDirectory(currentFile);

                updateView();
                updateTitle();
            }

            return true;
        }
        return false;
    }



    private void updateView() {
        DirectoryArrayAdapter mine = new DirectoryArrayAdapter(this, R.layout.list_view_files, entries);
        listView.setAdapter(mine);
    }
    private void updateTitle() {
        this.setTitle(currentLocation);
    }

}
