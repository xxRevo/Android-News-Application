package com.example.myapplication.repository;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadApplication extends Application {
    public ExecutorService srv = Executors.newCachedThreadPool();
}
