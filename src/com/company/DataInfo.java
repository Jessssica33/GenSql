package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class DataInfo {
    public String asin;
    public String title;
    public String group;
    public int salerank;
    public ArrayList<String> similar;
    public HashMap<Integer, String> cat;
    public ArrayList<Review> review;

    public DataInfo() {
        similar = new ArrayList<>();
        cat = new HashMap<>();
        review = new ArrayList<>();
    }

    //For debug
    public void print() {
        System.out.println(asin);
        System.out.println(title);
        System.out.println(salerank);
        System.out.println(group);

        System.out.println("Similar Group: ");
        for (int i = 0; i < similar.size(); ++i) {
            System.out.print(similar.get(i) + " ");
        }
        System.out.println();

        /*System.out.println("Category: ");
        Category c;
        for (int i = 0; i < cat.size(); ++i) {
            c = cat.get(i);
            System.out.println(c.catid + " " + c.name);
        }*/

        System.out.println("Review: ");
        Review r;
        for (int i = 0; i < review.size(); ++i) {
            r = review.get(i);
            System.out.println(r.asin + " " + r.date + " " + r.cid + " " + r.rating + " " + r.vote);
        }
    }


}
