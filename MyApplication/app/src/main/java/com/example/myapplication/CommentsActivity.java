package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.adapters.CommentsAdapter;
import com.example.myapplication.adapters.MyAdapter;
import com.example.myapplication.repository.ThreadApplication;
import com.example.myapplication.repository.repositoryApp;
import com.example.myapplication.sample_classes.CommentsClass;
import com.example.myapplication.sample_classes.DataListClass;

import java.util.List;

public class CommentsActivity extends AppCompatActivity {
    RecyclerView recView4;
    int id;
    DataListClass selected_data;
    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<CommentsClass> data = (List<CommentsClass>)msg.obj;
            recView4.setAdapter(new CommentsAdapter(getApplicationContext(), data) {
            });
            return true;
        }
    });

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home) {
            finish();
        }
        return true;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        selected_data = (DataListClass) getIntent().getSerializableExtra("selecteddata");
        id = selected_data.getId();
        repositoryApp repo = new repositoryApp();
        recView4 = findViewById(R.id.rec_view_page4);
        recView4.setLayoutManager(new LinearLayoutManager(getApplication().getBaseContext())); // MAY BE WRONG
        repo.getCommentData(((ThreadApplication)getApplication()).srv, dataHandler, id);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu,menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem ShowCommentsButton = menu.findItem(R.id.Button_ShowComments);
        ShowCommentsButton.setVisible(false);

        MenuItem PostCommentButton = menu.findItem(R.id.Button_SubmitComment);
        PostCommentButton.setVisible(true);

        PostCommentButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Switch to the new activity when the menu item is clicked
                Intent intent = new Intent(CommentsActivity.this, PostCommentActivity.class);
                intent.putExtra("selecteddata",selected_data);
                startActivity(intent);
                return true;
            }
        });
        return true;

    }
}