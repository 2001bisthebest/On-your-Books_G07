package com.example.onyourbooks_g07;

import static android.provider.BaseColumns._ID;
import static com.example.onyourbooks_g07.Constants.COLLECTION_NAME;
import static com.example.onyourbooks_g07.Constants.DATE;
import static com.example.onyourbooks_g07.Constants.IMAGE;
import static com.example.onyourbooks_g07.Constants.NAME;
import static com.example.onyourbooks_g07.Constants.PRICE;
import static com.example.onyourbooks_g07.Constants.TABLE_NAME_COL;
import static com.example.onyourbooks_g07.Constants.TABLE_NAME_LIST;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CollectionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterBtn adapterBtn;
    private ListEventsData listEventsData;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.column_list_collection);

        listEventsData = new ListEventsData(CollectionActivity.this);
        Button new_collection_btn = findViewById(R.id.add_new_collection_btn);
        TextView add_name_collection_edt = findViewById(R.id.add_name_collection_edt);
        Button ok_btn = findViewById(R.id.ok_btn);
        recyclerView = findViewById(R.id.recycle_view_btn);
        recyclerView.setLayoutManager(new LinearLayoutManager(CollectionActivity.this));
        recyclerView.setHasFixedSize(true);

        adapterBtn = new AdapterBtn(setList());
        recyclerView.setAdapter(adapterBtn);
        new_collection_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_name_collection_edt.setVisibility(View.VISIBLE);
                ok_btn.setVisibility(View.VISIBLE);
                ok_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nameBtn = add_name_collection_edt.getText().toString();
                        if(nameBtn.equals("")){
                            showAlertDialog();
                        }else {
                            addData(nameBtn);
                        }

                        adapterBtn = new AdapterBtn(setList());
                        recyclerView.setAdapter(adapterBtn);
                        setOnItemClickAdapter();
                        add_name_collection_edt.setText("");
                        add_name_collection_edt.setVisibility(View.INVISIBLE);
                        ok_btn.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
        setOnItemClickAdapter();
    }
    public void addData(String colName){
        try{
            SQLiteDatabase db = listEventsData.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLLECTION_NAME, colName);
            long ja = db.insert(TABLE_NAME_LIST, null, values);
        }catch (Exception e) {
            Log.e("Add Error", e.getMessage());
        }
    }
    private Cursor getEvents() {
        String[] FROM = {_ID, COLLECTION_NAME};
        String ORDER_BY = _ID + " DESC";
        SQLiteDatabase db = listEventsData.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_LIST, FROM, null, null, null, null, ORDER_BY);
        return cursor;
    }
    private List<Data> setList(){
        Cursor cursor = getEvents();
        Data data;
        List<Data> list = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            data = new Data(id, title);
            list.add(data);
        }
        return list;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collection_activity, menu);
        return true;
    }
    private void setOnItemClickAdapter(){
        ((AdapterBtn) adapterBtn).setOnItemClickListener(new AdapterBtn.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Data item = adapterBtn.getDataSet().get(position);
                Intent intent = new Intent(CollectionActivity.this, ColumnCollectionActivity.class);
                String idItem = item.getId() + "";
                String titleItem = item.getTitle();
                intent.putExtra("id_of_item", idItem);
                intent.putExtra("title", titleItem);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.back_btn) {
            startActivity(new Intent(this, MainActivity.class));
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CollectionActivity.this);

        builder.setTitle("Please try again!");
        builder.setMessage("You forget to enter name of collection.");
        builder.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
}
