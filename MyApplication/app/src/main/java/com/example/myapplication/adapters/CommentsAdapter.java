package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.sample_classes.CommentsClass;
import com.example.myapplication.sample_classes.DataListClass;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<MyViewHolder2> {
    Context context;
    List<CommentsClass> data;

    public CommentsAdapter(Context context, List<CommentsClass> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder2(LayoutInflater.from(context).inflate(R.layout.row_layout_comments,parent,false));
        /*
        View root = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        MyViewHolder holder = new MyViewHolder(root);
        holder.setIsRecyclable(false);
        return holder;
        */
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        holder.nameView.setText(data.get(position).getName());
        holder.textView.setText(data.get(position).getText());
        //holder.imageView.setImageResource(R.drawable.);
        //@android:drawable/btn_star_big_on
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
