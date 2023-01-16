package com.example.myapplication.adapters;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.repository.ThreadApplication;
import com.example.myapplication.repository.repositoryApp;
import com.example.myapplication.sample_classes.DataListClass;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<DataListClass> data;
    private ItemClickListener myItemListener;
    ExecutorService srv;

    public MyAdapter(Context context, List<DataListClass> data, ExecutorService srv ,ItemClickListener itemClickListener) {
        this.context = context;
        this.data = data;
        this.myItemListener = itemClickListener;
        this.srv = srv;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false));

        View root = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        MyViewHolder holder = new MyViewHolder(root);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleView.setText(data.get(position).getTitle());
        holder.dateView.setText(data.get(position).getDate());
        holder.downloadImage(srv, data.get(position).getImage());
        holder.itemView.setOnClickListener(view-> {
            myItemListener.onItemClick(data.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ItemClickListener {
        void onItemClick(DataListClass single_data);
    }

}
