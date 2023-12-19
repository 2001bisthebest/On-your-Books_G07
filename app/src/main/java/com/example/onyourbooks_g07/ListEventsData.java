package com.example.onyourbooks_g07;

import static android.provider.BaseColumns._ID;
import static com.example.onyourbooks_g07.Constants.COLLECTION_NAME;
import static com.example.onyourbooks_g07.Constants.DATE;
import static com.example.onyourbooks_g07.Constants.IMAGE;
import static com.example.onyourbooks_g07.Constants.LISTID;
import static com.example.onyourbooks_g07.Constants.NAME;
import static com.example.onyourbooks_g07.Constants.PRICE;
import static com.example.onyourbooks_g07.Constants.TABLE_NAME_LIST;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ListEventsData extends SQLiteOpenHelper {
    public ListEventsData(Context ctx){
        super(ctx, "list_table.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME_LIST + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLLECTION_NAME + " TEXT NOT NULL);"  );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS list_table");
        onCreate(db);
    }
}