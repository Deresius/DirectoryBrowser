package com.example.david.directorybrowser;

import android.provider.ContactsContract;
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

    public int getSize() {
        if (isDirectory == true) {
            int size = 0;
            File aFile = new File(this.getPath());
            for (File curr: aFile.listFiles()) {
                size++;
            }
            return size;
        }
        return 0;
    }

    public int compareTo(@NonNull DirectoryEntry o) {
        if(this.name != null)
            return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
        else
            throw new IllegalArgumentException();
    }
}