package com.example.onyourbooks_g07;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;

//public class AdapterBtn extends RecyclerView.Adapter<AdapterBtn.ViewHolder> {
//
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
//        RecyclerView.ViewHolder dataObjHolder = new ViewHolder(view);
//        return dataObjHolder;
//    }
//    public void onBindViewHolder(ViewHolder holder, int position){
//        holder.title.setText(dataSet.get(position).getTitle());
//        holder.desc.setText(dataSet.get(position).getDesc());
//        //decode base64 string to image
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] imageBytes = baos.toByteArray();
//        imageBytes = Base64.decode(dataSet.get(position).getIcon(), Base64.DEFAULT);
//        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//        holder.icon.setImageBitmap(decodedImage);
//    }
//    public int getItemCount(){
//        return dataSet.size();
//    }
//}
