package com.example.david.directorybrowser;

import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 *  Directory Builder class is responsible for the method to build a directory from a list of files.
 *
 * Created by David Spurlock and James Looney.
 */

class DirectoryBuilder {

    private ArrayList<DirectoryEntry> directories;
    private ArrayList<DirectoryEntry> files;

    /**
     *  buildDirectory builds a sorted directoryEntry array list from a list of files.
     *
     * @param allFiles the provided list of files
     * @return the sorted, and labeled directory list.
     */
    ArrayList<DirectoryEntry> buildDirectory(File[] allFiles) {
        directories = new ArrayList<>();
        files = new ArrayList<>();

        for (File curr : allFiles) {


            DirectoryEntry newEntry = new DirectoryEntry(curr.getName(), curr.getAbsolutePath(), curr.isDirectory());
            if (newEntry.getPath().toLowerCase().endsWith("mp3")) {
                newEntry.setIcon(R.drawable.mp3);
            } else if (newEntry.getPath().toLowerCase().endsWith("txt")) {
                newEntry.setIcon(R.drawable.txt);

//            }else if (newEntry.getPath().toLowerCase().endsWith("jpg")){
//                newEntry.setThumb(ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(curr.getPath()), 50, 50));
//                newEntry.setIcon(newEntry.getThumb().getGenerationId());
//            }else if (newEntry.getPath().toLowerCase().endsWith("jpeg")){
//                newEntry.setThumb(ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(curr.getPath()), 50, 50));
//                newEntry.setIcon(newEntry.getThumb().getGenerationId());
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
