package com.example.david.directorybrowser;

/**
 * Created by David on 1/25/2018.
 */

public class DirectoryEntry implements Comparable<DirectoryEntry>{
    private String name;
    private String path;
    private boolean isDirectory;
    private int icon;

    public DirectoryEntry(String n, String p, boolean d)
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
    public String getPath()
    {
        return path;
    }
    public boolean isDirectory() {
        return isDirectory;
    }
    public int compareTo(DirectoryEntry o) {
        if(this.name != null)
            return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
        else
            throw new IllegalArgumentException();
    }
}