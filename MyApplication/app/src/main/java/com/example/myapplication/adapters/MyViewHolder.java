package com.example.myapplication.adapters;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.repository.repositoryApp;

import java.util.concurrent.ExecutorService;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView titleView;
    TextView dateView;
    boolean imageDownloaded;

    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Bitmap img = (Bitmap)msg.obj;
            imageView.setImageBitmap(img);
            imageDownloaded = true;
            return true;
        }
    });

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.txtListTitle);
        imageView = itemView.findViewById(R.id.txtListImage);
        dateView = itemView.findViewById(R.id.txtListDate);
    }

    public void downloadImage(ExecutorService srv, String path){

        if (!imageDownloaded){

            repositoryApp repo = new repositoryApp();
            repo.downloadImage(srv,imgHandler,path);
        }
    }
}
