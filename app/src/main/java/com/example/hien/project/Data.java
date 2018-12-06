package com.example.hien.project;

import java.util.ArrayList;

public class Data {
    public static ArrayList<Catalog> catArrList = new ArrayList<>();
    public static ArrayList<Product> proArrList = new ArrayList<>();
    public static int maxProArrId(){
        int tempt=0;
        boolean f= false;
        for(Product p : proArrList){
            if(f) {
                tempt = p.getProID();
                f=true;
            }else if(tempt<p.getProID()) {
                tempt=p.getProID();
            }
        }
        return tempt;
    }
    public static int maxCatArrId(){
        int tempt=0;
        boolean f= false;
        for(Catalog c : catArrList){
            if(f) {
                tempt = c.getCatId();
                f=true;
            }else if(tempt<c.getCatId()) {
                tempt=c.getCatId();
            }
        }
        return tempt;
    }
    public static void updateCatNameInProductsArr(Catalog oldCat, Catalog newCat){
        for(Product c : proArrList){
            if(c.getProCatName()==oldCat.getCatName())
                c.setProCatName(newCat.getCatName());
        }
    }
    public static boolean checkDataCat(int pos) {
        String nameCat = catArrList.get(pos).getCatName();
        for (Product p : proArrList)
            if(p.getProCatName().compareTo(nameCat)==0) {
                return true;
            }
        return false;
    }
}
