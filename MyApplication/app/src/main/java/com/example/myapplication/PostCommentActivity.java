package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.adapters.MyAdapter;
import com.example.myapplication.repository.ThreadApplication;
import com.example.myapplication.repository.repositoryApp;
import com.example.myapplication.sample_classes.DataListClass;

import java.util.List;

public class PostCommentActivity extends AppCompatActivity {
    Button postCommentButton;
    EditText editTextName;
    EditText editTextComment;
    String name, text, news_id;
    int news_id_int;
    DataListClass selected_data;
    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return true;
        }
    });

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);

        selected_data = (DataListClass) getIntent().getSerializableExtra("selecteddata");
        news_id_int = selected_data.getId();
        news_id = Integer.toString(news_id_int);
        postCommentButton = findViewById(R.id.SubmitCommentButton);
        editTextName = findViewById(R.id.editTextName);
        editTextComment = findViewById(R.id.editTextComment);
        postCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editTextName.getText().toString();
                text = editTextComment.getText().toString();
                repositoryApp repo = new repositoryApp();
                repo.sendComment(((ThreadApplication)getApplication()).srv, dataHandler, name, text, news_id);
                Intent intent = new Intent(PostCommentActivity.this, CommentsActivity.class);
                intent.putExtra("selecteddata",selected_data);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home) {
            finish();
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu,menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem ShowCommentsButton = menu.findItem(R.id.Button_ShowComments);
        ShowCommentsButton.setVisible(false);

        MenuItem PostCommentButton = menu.findItem(R.id.Button_SubmitComment);
        PostCommentButton.setVisible(false);
        return true;
    }
}