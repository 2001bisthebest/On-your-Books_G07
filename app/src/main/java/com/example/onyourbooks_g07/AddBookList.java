package com.example.onyourbooks_g07;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddBookList extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book_list);

        Resources res = getResources();
        final EditText date = findViewById(R.id.editTextDate2);
        final EditText name = findViewById(R.id.editTextText4);
        final EditText price = findViewById(R.id.editprice);
        final Button submit = findViewById(R.id.Button_submit);

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
