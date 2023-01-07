package com.example.myapplication.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.DetailsActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapters.MyAdapter;
import com.example.myapplication.repository.ThreadApplication;
import com.example.myapplication.repository.repositoryApp;
import com.example.myapplication.sample_classes.DataListClass;

import java.io.Serializable;
import java.util.List;

public class first_tab extends Fragment {
    RecyclerView recView1;
    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<DataListClass> data = (List<DataListClass>)msg.obj;

            recView1.setAdapter(new MyAdapter(getActivity().getApplicationContext(), data, new MyAdapter.ItemClickListener() {
                @Override
                public void onItemClick(DataListClass single_data) {
                    Intent i = new Intent(getContext(), DetailsActivity.class);
                    i.putExtra("selecteddata",single_data);
                    ((AppCompatActivity)getContext()).startActivity(i);
                }
            }));
            return true;
        }
    });
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first_tab, container, false);
        repositoryApp repo = new repositoryApp();
        recView1 = v.findViewById(R.id.rec_view_page1);
        recView1.setLayoutManager(new LinearLayoutManager(getContext()));
        repo.getNewsByCategoryId1(((ThreadApplication)getActivity().getApplication()).srv, dataHandler);
        return v;
    }
}