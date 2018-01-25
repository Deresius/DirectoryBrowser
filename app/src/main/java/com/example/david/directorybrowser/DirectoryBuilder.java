package com.example.david.directorybrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by David on 1/25/2018.
 */

public class DirectoryBuilder {

    ArrayList<DirectoryEntry> directories;
    ArrayList<DirectoryEntry> files;

    public ArrayList<DirectoryEntry> buildDirectory(File[] allFiles) {
        directories = new ArrayList<>();
        files = new ArrayList<>();

        for (File curr : allFiles) {

            DirectoryEntry newEntry = new DirectoryEntry(curr.getName(), curr.getAbsolutePath(), curr.isDirectory());
            if (newEntry.getPath().toLowerCase().endsWith("mp3")) {
                newEntry.setIcon(R.drawable.mp3);
            } else if (newEntry.getPath().toLowerCase().endsWith("txt")) {
                newEntry.setIcon(R.drawable.txt);
            } else if (newEntry.isDirectory()) {
                newEntry.setIcon(R.drawable.folder_icon);
            } else {
                newEntry.setIcon(R.drawable.gen);
            }

            if (newEntry.isDirectory()) {
                directories.add(newEntry);
            } else {
                files.add(newEntry);
            }
        }
        Collections.sort(files);
        Collections.sort(directories);
        directories.addAll(files);
        return directories;
    }
}
