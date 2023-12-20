package com.example.onyourbooks_g07;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.onyourbooks_g07.Constants.LISTID;
import static com.example.onyourbooks_g07.Constants.NAME;
import static com.example.onyourbooks_g07.Constants.DATE;
import static com.example.onyourbooks_g07.Constants.IMAGE;
import static com.example.onyourbooks_g07.Constants.PRICE;
import static com.example.onyourbooks_g07.Constants.TABLE_NAME_COL;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddBookList extends AppCompatActivity {
    DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    Calendar myCalendar = Calendar.getInstance();
    private CollectionEventsData collectionEventsData;
    private Uri image_uri;
    private String listId;
    String dateStr;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    try {
                        Uri uri = data.getData();
                        image_uri = uri;
                        try {
                            ImageView imageView = (ImageView) findViewById(R.id.imageView);
                            imageView.getLayoutParams().height = 400;
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imageView.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                        Log.e("Log", "Error from Gallery Activity");
                    }
                }
            }
        });
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book_list);

        dateStr = updateLabel();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                listId = null;
            } else {
                listId = extras.getString("id_of_item");
            }
        } else {
            listId = (String) savedInstanceState.getSerializable("id_of_item");
        }
        Log.d("ta", listId);

        collectionEventsData = new CollectionEventsData(AddBookList.this);
        ImageButton pickDateBtn = findViewById(R.id.pickDateBtn);
        EditText book_name = findViewById(R.id.book_name);
        EditText book_price = findViewById(R.id.book_price);
        Button submitBtn = findViewById(R.id.submitBtn);

        pickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddBookList.this, d,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ImageButton imageBtn = findViewById(R.id.imageBtn);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                activityResultLauncher.launch(Intent.createChooser(intent, "Select photo from"));
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String book_nameStr, book_priceStr;
                dateStr = updateLabel();
                book_nameStr = book_name.getText().toString();
                book_priceStr = book_price.getText().toString();
                addData(dateStr, book_nameStr, book_priceStr);
                finish();
            }
        });
    }
    public void addData(String dateStr,String book_name, String book_price){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Bitmap bitmap = null;
            String imageString = null;
            try{
                if(image_uri != null)
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_uri);
                else
                    bitmap = BitmapFactory.decodeResource(getResources(), R.id.pickDateBtn);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                byte[] imageBytes = baos.toByteArray();
                imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SQLiteDatabase db = collectionEventsData.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(LISTID, listId);
            values.put(DATE, dateStr);
            values.put(NAME, book_name);
            values.put(PRICE, book_price);
            values.put(IMAGE, imageString);
            db.insert(TABLE_NAME_COL, null, values);
        }catch (Exception e) {
            Log.e("Add Error", e.getMessage());
        }
    }
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };
    private String updateLabel() {
        TextView date_picker = findViewById(R.id.date_picker);
        date_picker.setText(dateFormat.format(myCalendar.getTime()));
        return date_picker.getText().toString();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collection_activity, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.back_btn) {
            startActivity(new Intent(this, ColumnCollectionActivity.class));
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
