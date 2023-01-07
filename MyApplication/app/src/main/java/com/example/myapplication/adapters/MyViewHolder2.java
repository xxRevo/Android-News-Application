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

public class MyViewHolder2 extends RecyclerView.ViewHolder {
    TextView nameView;
    TextView textView;
    ImageView imageView;

    public MyViewHolder2(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.textNameView);
        textView = itemView.findViewById(R.id.textTextView);
        imageView = itemView.findViewById(R.id.imageImageView);
    }

}
