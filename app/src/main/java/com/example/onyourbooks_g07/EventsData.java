package com.example.onyourbooks_g07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;
import static com.example.onyourbooks_g07.Constants.DATE;
import static com.example.onyourbooks_g07.Constants.NAME;
import static com.example.onyourbooks_g07.Constants.PRICE;
import static com.example.onyourbooks_g07.Constants.IMAGE;

public class EventsData extends SQLiteOpenHelper {

    String table_name;
    private ListsBtn listsBtn;
    public EventsData(Context ctx){
        super(ctx, "events.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        listsBtn = new ListsBtn();
        table_name = listsBtn.getListsBtn().toString();
        db.execSQL("CREATE TABLE " + table_name + " ("
        + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + DATE + " TEXT NOT NULL, "
        + NAME + " TEXT NOT NULL, "
        + PRICE + " INTEGER, "
        + IMAGE + " TEXT NOT NULL);" );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS Events");
        onCreate(db);
    }
}
