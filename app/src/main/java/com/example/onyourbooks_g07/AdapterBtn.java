package com.example.onyourbooks_g07;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AdapterBtn extends RecyclerView.Adapter<AdapterBtn.ViewHolder> {
    private List<Data> dataSet;
    private MyClickListener mCallback;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.button_recycleview, parent, false));
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.newBtn.setText(dataSet.get(position).getTitle());
    }
    @Override
    public int getItemCount(){
        return dataSet.size();
    }
    public void setOnItemClickListener(MyClickListener mCallback){
        this.mCallback = mCallback;
    }

    public AdapterBtn(List<Data> myDataSet){
        dataSet = myDataSet;
    }

    public interface MyClickListener{
        public void onItemClick(int position, View v);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Button newBtn;

        public ViewHolder(View itemView){
            super(itemView);
            newBtn = (Button) itemView.findViewById(R.id.recycle_btn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            mCallback.onItemClick(getAdapterPosition(), v);
        }
    }
}
