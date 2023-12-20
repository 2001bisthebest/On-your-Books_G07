package com.example.onyourbooks_g07;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private String title;
    private int id;
    public Data(int id, String title){
        this.title = title;
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getTitle(){
        return title;
    }
}