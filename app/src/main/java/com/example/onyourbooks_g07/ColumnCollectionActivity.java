package com.example.onyourbooks_g07;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ColumnCollectionActivity extends AppCompatActivity {
    boolean isVisible = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_collection);

        FloatingActionButton plusBtn = findViewById(R.id.plusBtn);
        FloatingActionButton addBookBtn = findViewById(R.id.addBookBtn);
        TextView addBook = findViewById(R.id.addBook);

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
                startActivity(intent);
            }
        });

    }
}
