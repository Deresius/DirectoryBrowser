package com.example.david.directorybrowser;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView listView ;
    ArrayAdapter<File> adapter;
    File[] currentFile;
    List<File> directories;
    List<File> files;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }

        listView = (ListView) findViewById(R.id.DirectoryListView);

        currentFile = new File("/sdcard/").listFiles() ;

        sortCurrentFile();
        updateView();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                int itemPosition     = position;

                File location = (File)listView.getItemAtPosition(position);
                currentFile = new File(location.getAbsolutePath()).listFiles();

                updateView();

               // Toast.makeText(getApplicationContext(),
                 //       "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                   //     .show();
            }
        });
    }

    private void sortCurrentFile() {
       // directories.clear();
        //files.clear();
        //for (File curr : currentFile) {
          //  if (curr.isDirectory()) {
            //    directories.add(curr);
            //} else {
              //  files.add(curr);
            //}
        //}
        //Collections.sort(files);
        //Collections.sort(directories);
        //directories.addAll(files);
        //directories.toArray(currentFile);
    }

    private void updateView() {
        adapter = new ArrayAdapter<File>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, currentFile);
        listView.setAdapter(adapter);
    }

    private void TestUpload() {
        int i = 1+3;
    }
}
