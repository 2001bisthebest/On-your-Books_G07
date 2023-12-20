package com.example.onyourbooks_g07;

import static android.provider.BaseColumns._ID;
import static com.example.onyourbooks_g07.Constants.DATE;
import static com.example.onyourbooks_g07.Constants.IMAGE;
import static com.example.onyourbooks_g07.Constants.LISTID;
import static com.example.onyourbooks_g07.Constants.NAME;
import static com.example.onyourbooks_g07.Constants.PRICE;
import static com.example.onyourbooks_g07.Constants.TABLE_NAME_COL;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ResultList extends AppCompatActivity {
    private String idItemList;
    private CollectionEventsData collectionEventsData;
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
        collectionEventsData = new CollectionEventsData(ResultList.this);
        TextView item_name = findViewById(R.id.item_name);
        TextView item_price = findViewById(R.id.item_price);
        TextView item_date = findViewById(R.id.item_date);
        ImageView imageView = findViewById(R.id.item_img);

        Cursor cursor = getEvents();
        cursor.moveToNext();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(cursor.getString(5), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        imageView.setImageBitmap(decodedImage);
        item_name.setText(cursor.getString(3));
        item_price.setText(cursor.getString(4));
        item_date.setText(cursor.getString(2));
    }
    private Cursor getEvents() {
        String[] FROM = {_ID, LISTID, DATE, NAME, PRICE, IMAGE};
        String SELECTION = _ID+"="+idItemList;
        SQLiteDatabase db = collectionEventsData.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_COL, FROM, SELECTION, null, null, null, null);
        return cursor;
    }
}
