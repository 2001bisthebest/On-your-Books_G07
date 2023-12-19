package com.example.onyourbooks_g07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;
import static com.example.onyourbooks_g07.Constants.DATE;
import static com.example.onyourbooks_g07.Constants.LISTID;
import static com.example.onyourbooks_g07.Constants.NAME;
import static com.example.onyourbooks_g07.Constants.PRICE;
import static com.example.onyourbooks_g07.Constants.IMAGE;
import static com.example.onyourbooks_g07.Constants.TABLE_NAME_COL;
import static com.example.onyourbooks_g07.Constants.TABLE_NAME_COL;

public class CollectionEventsData extends SQLiteOpenHelper {

    public CollectionEventsData(Context ctx){
        super(ctx, "collection_list_table.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME_COL + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LISTID + " TEXT NOT NULL, "
                + DATE + " TEXT NOT NULL, "
                + NAME + " TEXT NOT NULL, "
                + PRICE + " TEXT NOT NULL, "
                + IMAGE + " TEXT NOT NULL);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS collection_list_table");
        onCreate(db);
    }
}
