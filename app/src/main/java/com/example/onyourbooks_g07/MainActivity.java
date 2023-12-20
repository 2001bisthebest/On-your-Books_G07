package com.example.onyourbooks_g07;

import static android.provider.BaseColumns._ID;
import static com.example.onyourbooks_g07.Constants.COLLECTION_NAME;
import static com.example.onyourbooks_g07.Constants.TABLE_NAME_LIST;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListEventsData listEventsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listEventsData = new ListEventsData(MainActivity.this);
        Button col_btn = findViewById(R.id.collection);
        col_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });
    }
    private Cursor getEvents() {
        String[] FROM = {_ID, COLLECTION_NAME};
        String ORDER_BY = _ID + " DESC";
        SQLiteDatabase db = listEventsData.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_LIST, FROM, null, null, null, null, ORDER_BY);
        return cursor;
    }
}