package com.example.onyourbooks_g07;

import static android.provider.BaseColumns._ID;

import static com.example.onyourbooks_g07.Constants.LISTID;
import static com.example.onyourbooks_g07.Constants.NAME;
import static com.example.onyourbooks_g07.Constants.DATE;
import static com.example.onyourbooks_g07.Constants.IMAGE;
import static com.example.onyourbooks_g07.Constants.PRICE;
import static com.example.onyourbooks_g07.Constants.TABLE_NAME_COL;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class ColumnCollectionActivity extends AppCompatActivity {
    boolean isVisible = false;
    private CollectionEventsData collectionEventsData;
    private String idItem, idItemList, titleItem;
    ListView listView;
    Cursor cursor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_collection);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                idItem = null;
                titleItem = null;
            } else {
                idItem = extras.getString("id_of_item");
                titleItem = extras.getString("title");
            }
        } else {
            idItem = (String) savedInstanceState.getSerializable("id_of_item");
            titleItem = (String) savedInstanceState.getSerializable("title");
        }
        collectionEventsData = new CollectionEventsData(ColumnCollectionActivity.this);
        FloatingActionButton plusBtn = findViewById(R.id.plusBtn);
        FloatingActionButton addBookBtn = findViewById(R.id.addBookBtn);
        TextView addBook = findViewById(R.id.addBook);
        listView = (ListView)findViewById(R.id.listView);
        TextView name_list = findViewById(R.id.name_list);
        SearchView search_icon = findViewById(R.id.search_icon);

        search_icon.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                cursor = search(s);
                showEvents(cursor);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                cursor = search(s);
                showEvents(cursor);
                return true;
            }
        });
        name_list.setText(titleItem);
        addBookBtn.setVisibility(View.GONE);
        addBook.setVisibility(View.GONE);
        plusBtn.setOnClickListener(view -> {
            if(!isVisible){
                addBookBtn.show();
                addBook.setVisibility(View.VISIBLE);
                isVisible = true;
            }else {
                addBookBtn.hide();
                addBook.setVisibility(View.GONE);
                isVisible = false;
            }
        });
        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ColumnCollectionActivity.this, AddBookList.class);
                intent.putExtra("id_of_item", idItem);
                startActivity(intent);
            }
        });

        try{
            cursor = getEvents();
            showEvents(cursor);
        }finally {
            collectionEventsData.close();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(cursor.moveToPosition(i)){
                    idItemList = cursor.getInt(0) + "";
                }

                Intent intent = new Intent(ColumnCollectionActivity.this, ResultList.class);
                intent.putExtra("id_of_item_list", idItemList);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        try{
            cursor = getEvents();
            showEvents(cursor);
        }finally {
            collectionEventsData.close();
        }
    }
    private void showEvents(Cursor cursor) {
        final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        while(cursor.moveToNext()) {
            map = new HashMap<String, String>();
            map.put(DATE, cursor.getString(2));
            map.put(NAME, cursor.getString(3));
            map.put(PRICE, cursor.getString(4));
            map.put(IMAGE, cursor.getString(5));
            MyArrList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(ColumnCollectionActivity.this, MyArrList, R.layout.column_collection, new String[]{"date", "name", "price", "img"}, new int[]{R.id.col_date, R.id.col_name, R.id.col_price, R.id.col_img});
        listView.setAdapter(simpleAdapter);
    }
    private Cursor getEvents() {
        String[] FROM = {_ID, LISTID, DATE, NAME, PRICE, IMAGE};
        String ORDER_BY = _ID + " DESC";
        String SELECTION = LISTID+"="+idItem;
        SQLiteDatabase db = collectionEventsData.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_COL, FROM, SELECTION, null, null, null, ORDER_BY);
        return cursor;
    }
    private Cursor search(String search) {
        String[] FROM = {_ID, LISTID, DATE, NAME, PRICE, IMAGE};
        String ORDER_BY = _ID + " DESC";
        String SELECTION = LISTID+"="+idItem + " AND " +NAME + " LIKE ?" ;
        String[] SELECTIONARGS = {"%" + search + "%"};
        SQLiteDatabase db = collectionEventsData.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_COL, FROM, SELECTION, SELECTIONARGS, null, null, ORDER_BY);
        return cursor;
    }
}
