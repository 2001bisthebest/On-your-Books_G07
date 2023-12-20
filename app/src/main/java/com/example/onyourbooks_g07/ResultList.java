package com.example.onyourbooks_g07;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ResultList extends AppCompatActivity {
    private String idItemList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_list);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                idItemList = null;
            } else {
                idItemList = extras.getString("id_of_item_list");
            }
        } else {
            idItemList = (String) savedInstanceState.getSerializable("id_of_item_list");
        }

    }
}
