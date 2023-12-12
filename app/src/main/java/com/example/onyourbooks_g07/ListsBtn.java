package com.example.onyourbooks_g07;

import java.util.ArrayList;
import java.util.List;

public class ListsBtn {
    private List<Data> list;

    public ListsBtn(){
        list = new ArrayList<>();
    }
    public void addListsBtn(String title){
        list.add(new Data("" + title));
        //nameBtn
    }
    public List<Data> getListsBtn(){
        return list;
    }

}
