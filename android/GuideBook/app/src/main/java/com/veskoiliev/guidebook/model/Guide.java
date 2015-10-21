package com.veskoiliev.guidebook.model;

public class Guide {
    private String name;
    private String icon;

    @Override
    public String toString() {
        return name + ", ";
    }

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return icon;
    }
}
