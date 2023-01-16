package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.repository.ThreadApplication;
import com.example.myapplication.repository.repositoryApp;
import com.example.myapplication.sample_classes.DataListClass;

import java.util.concurrent.ExecutorService;

public class DetailsActivity extends AppCompatActivity {
    int id;
    DataListClass selected_data;
    ImageView imgView;

    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Bitmap img = (Bitmap)msg.obj;
            imgView.setImageBitmap(img);
            return true;
        }
    });

    public void downloadImage(ExecutorService srv, String path){
        repositoryApp repo = new repositoryApp();
        repo.downloadImage(srv,imgHandler,path);
    }

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DataListClass selected =
                (DataListClass)getIntent().getSerializableExtra("selecteddata");
        id = selected.getId();
        selected_data = selected;
        imgView = findViewById(R.id.d_imageView);
        downloadImage(((ThreadApplication)getApplication()).srv, selected.getImage());
        //img.setImageResource(selected.getImage());

        TextView txtTitle = findViewById(R.id.d_textTitle);
        txtTitle.setText(String.valueOf(selected.getTitle()));

        TextView txtDate = findViewById(R.id.d_textDate);
        txtDate.setText(String.valueOf(selected.getDate()));

        TextView txtText = findViewById(R.id.d_textText);
        txtText.setText(String.valueOf(selected.getText()));

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu,menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem CommentButton = menu.findItem(R.id.Button_ShowComments);
        CommentButton.setVisible(true);

        CommentButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Switch to the new activity when the menu item is clicked
                Intent intent = new Intent(DetailsActivity.this, CommentsActivity.class);
                intent.putExtra("selecteddata",selected_data);
                startActivity(intent);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home) {
            finish();
        }
        return true;
    }

    /*
    @Override
    protected void onStart() {
        super.onStart();
        MenuItem CommentButton = findViewById(R.id.Button_ShowComments);
        CommentButton.setVisible(true);
    }
    */
}
