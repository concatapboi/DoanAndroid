package com.example.hien.project;

import java.util.ArrayList;

public class Catalog {
    private int catId;
    private String catName;
    public Catalog() {
    }

    public Catalog(int catId, String catName) {
        this.catId = catId;
        this.catName = catName;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        return this.catId+" - "+this.catName;
    }

}
