package com.example.onyourbooks_g07;

import java.util.ArrayList;
import java.util.List;

public class ListsBtn {
    private ArrayList<Data> list;

    public ListsBtn(ArrayList<Data> list){
//        list = new ArrayList<>();
        this.list = list;
    }
    public void addListsBtn(int id, String title){
        list.add(new Data(id, title));
        //nameBtn
    }
    public List<Data> getListsBtn(){
        return list;
    }

}
