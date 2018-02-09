package com.example.david.directorybrowser;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.io.File;

/**
 *  Class DirectoryEntry defines the parameters of a directory tree object.
 *
 * Created by David Spurlock and James Looney.
 */

public class DirectoryEntry implements Comparable<DirectoryEntry>{
    private String name;
    private String path;
    private boolean isDirectory;
    private int icon;
    private Bitmap bm;

    /**
     *  Constructs a new entry for the directory tree.
     *
     * @param n - the name of the file
     * @param p - the path of the file
     * @param d - where the entry is a directory
     */
    DirectoryEntry(String n, String p, boolean d)
    {
        name = n;
        path = p;
        isDirectory = d;
        icon = R.drawable.gen;

    }
    public void setIcon(int icon) {
        this.icon = icon;
    }
    public int getIcon() {
        return this.icon;
    }
    public String getName()
    {
        return name;
    }
    String getPath()
    {
        return path;
    }
    boolean isDirectory() {
        return isDirectory;
    }

    public Bitmap getThumb() {
        return bm;
    }

    public void setThumb(Bitmap map) {
         this.bm = map;
    }

    public String getSize() {
        String returnValue = "";
        if (isDirectory == true) {
            File aFile = new File(this.getPath());
            try {
                if (aFile.listFiles().length > 0) {
                    return String.valueOf(aFile.listFiles().length);
                }
            } catch (Exception e) {

            }
            //return "empty";
        }
        return returnValue;
    }

    public int compareTo(@NonNull DirectoryEntry o) {
        if(this.name != null)
            return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
        else
            throw new IllegalArgumentException();
    }
}