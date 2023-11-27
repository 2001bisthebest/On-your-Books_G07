package com.example.onyourbooks_g07;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends AppCompatActivity {
    private boolean isClicked = false;
    private RecyclerView recyclerView;
    private List<Data> dataList = new ArrayList<>();
    private AdapterBtn adapterBtn;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.column_list_collection);

        Button new_collection_btn = findViewById(R.id.add_new_collection_btn);
        TextView add_name_collection_edt = findViewById(R.id.add_name_collection_edt);
        Button ok_btn = findViewById(R.id.ok_btn);
        new_collection_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked = true;
                if(isClicked){
                    add_name_collection_edt.setVisibility(View.VISIBLE);
                    ok_btn.setVisibility(View.VISIBLE);
                    ok_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String nameBtn = add_name_collection_edt.getText().toString();
                            dataList.add(new Data(""+nameBtn));
                            RecyclerView recyclerView = findViewById(R.id.recycle_view_btn);
                            recyclerView.setLayoutManager(new LinearLayoutManager(CollectionActivity.this));
                            recyclerView.setHasFixedSize(true);

                            adapterBtn = new AdapterBtn(dataList);
                            recyclerView.setAdapter(adapterBtn);
                            ((AdapterBtn) adapterBtn).setOnItemClickListener(new AdapterBtn.MyClickListener() {
                                @Override
                                public void onItemClick(int position, View v) {
                                    Toast.makeText(getApplication(), "Clicked item "+Integer.toString(position),Toast.LENGTH_SHORT).show();
                                }
                            });
                            add_name_collection_edt.setText("");
                            add_name_collection_edt.setVisibility(View.INVISIBLE);
                            ok_btn.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collection_activity, menu);
        return true;
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

}
