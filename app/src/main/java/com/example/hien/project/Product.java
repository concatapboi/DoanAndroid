package com.example.hien.project;

public class Product {
    private int proID;
    private String proName;
    private String proCatName;
    private String proImgName;
    private int proPrice;
    private int proDiscount;

    public Product() {
    }

    public Product(int proID, String proName, String proCatName, String proImgName, int proPrice, int proDiscount) {
        this.proID = proID;
        this.proName = proName;
        this.proCatName = proCatName;
        this.proImgName = proImgName;
        this.proPrice = proPrice;
        this.proDiscount = proDiscount;
    }

    public int getProID() {
        return proID;
    }

    public void setProID(int proID) {
        this.proID = proID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProCatName() {
        return proCatName;
    }

    public void setProCatName(String proCatName) {
        this.proCatName = proCatName;
    }

    public String getProImgName() {
        return proImgName;
    }

    public void setProImgName(String proImgName) {
        this.proImgName = proImgName;
    }

    public int getProPrice() {
        return proPrice;
    }

    public void setProPrice(int proPrice) {
        this.proPrice = proPrice;
    }

    public int getProDiscount() {
        return proDiscount;
    }

    public void setProDiscount(int proDiscount) {
        this.proDiscount = proDiscount;
    }

    @Override
    public String toString() {
        return "Name: "+this.proName+"- Price: "+this.proPrice+"đ";
    }
}
