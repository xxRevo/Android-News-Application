package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.adapters.MyAdapter;
import com.example.myapplication.adapters.ViewPageAdapter;
import com.example.myapplication.fragments.first_tab;
import com.example.myapplication.fragments.second_tab;
import com.example.myapplication.fragments.third_tab;
import com.example.myapplication.repository.ThreadApplication;
import com.example.myapplication.repository.repositoryApp;
import com.example.myapplication.sample_classes.CategoriesClass;
import com.example.myapplication.sample_classes.DataListClass;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    ViewPageAdapter viewPageAdapter;
    TabLayout tabLayout;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<CategoriesClass> data = (List<CategoriesClass>)msg.obj;
            boolean process_complete = false;
            int current_id = 1;
            while(process_complete == false) {
                for (int i = 0; i < data.size(); i++) {
                    if(data.get(i).getId() == current_id) {
                        String tabName = data.get(i).getName();
                        tabLayout.addTab(tabLayout.newTab().setText(tabName));
                        current_id++;
                    }
                }
                if (current_id == 4) {
                    process_complete = true;
                }
            }
            return true;
        }
    });

    @SuppressLint({"MissingInflatedId", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        second_tab frg2 = new second_tab();

        third_tab frg3 = new third_tab();


        repositoryApp repo = new repositoryApp();
        tabLayout = findViewById(R.id.tab_host);
        repo.getAllCategories(((ThreadApplication)getApplication()).srv, dataHandler);


        viewPager2 = findViewById(R.id.view_pager);
        viewPageAdapter = new ViewPageAdapter(this);
        viewPager2.setAdapter(viewPageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
                super.onPageSelected(position);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu,menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem CommentButton = menu.findItem(R.id.Button_ShowComments);
        CommentButton.setVisible(false);
        return true;
    }
}