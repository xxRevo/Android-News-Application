package com.example.myapplication.repository;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.example.myapplication.sample_classes.CategoriesClass;
import com.example.myapplication.sample_classes.CommentsClass;
import com.example.myapplication.sample_classes.DataListClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
// http://10.3.0.14:8080/newsapp/getnewsbyid/x
// http://10.3.0.14:8080/newsapp/getcommentsbynewsid/%7Bnews
public class repositoryApp {

    public void sendComment(ExecutorService srv, Handler uiHandler,String name, String text, String newsId){

        srv.execute(()->{

            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/savecomment");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");


                JSONObject outputData  = new JSONObject();

                outputData.put("name",name);
                outputData.put("text",text);
                outputData.put("news_id", newsId);

                BufferedOutputStream writer =
                        new BufferedOutputStream(conn.getOutputStream());


                writer.write(outputData.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();

                String line ="";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject retVal = new JSONObject(buffer.toString());

                    /*
                    PersonData data = new PersonData(retVal.getString("date"),
                            retVal.getString("fullname"));
                    */
                conn.disconnect();


                String retValStr = "Date:" + retVal.getString("date") +
                        ", fullname:" + retVal.getString("fullname");

                Message msg = new Message();
                msg.obj = retValStr;

                uiHandler.sendMessage(msg);





            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });



    }

    public void getCommentData(ExecutorService srv, Handler uiHandler, int commentId){

        srv.execute(()->{
            try {
                String stringId = String.valueOf(commentId);
                String link = "http://10.3.0.14:8080/newsapp/getcommentsbynewsid/" + stringId;
                URL url = new URL(link);
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject current2 = new JSONObject(buffer.toString());
                JSONArray arr = current2.getJSONArray("items");
                List<CommentsClass> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);

                    CommentsClass dataList = new CommentsClass(current.getInt("id"),
                            current.getInt("news_id"),
                            current.getString("text"),
                            current.getString("name"));
                    data.add(dataList);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });


    }

    public void getAllData(ExecutorService srv, Handler uiHandler){

        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getall");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject current2 = new JSONObject(buffer.toString());
                JSONArray arr = current2.getJSONArray("items");
                List<DataListClass> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);

                    DataListClass dataList = new DataListClass(current.getInt("id"),
                            current.getString("title"),
                            current.getString("text"),
                            current.getString("date"),
                            current.getString("categoryName"),
                            current.getString("image"));
                    int index = dataList.getDate().indexOf('T');
                    String dateData = dataList.getDate().substring(0,index);
                    String day,month,year;
                    day = dateData.substring(8,10);
                    month = dateData.substring(5,7);
                    year = dateData.substring(0,4);
                    dateData = "";
                    dateData = day + "/" + month + "/" + year;
                    dataList.setDate(dateData);
                    data.add(dataList);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });


    }

    public void getNewsByCategoryId1(ExecutorService srv, Handler uiHandler){

        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getbycategoryid/1");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject current2 = new JSONObject(buffer.toString());
                JSONArray arr = current2.getJSONArray("items");
                List<DataListClass> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);

                    DataListClass dataList = new DataListClass(current.getInt("id"),
                            current.getString("title"),
                            current.getString("text"),
                            current.getString("date"),
                            current.getString("categoryName"),
                            current.getString("image"));
                    int index = dataList.getDate().indexOf('T');
                    String dateData = dataList.getDate().substring(0,index);
                    String day,month,year;
                    day = dateData.substring(8,10);
                    month = dateData.substring(5,7);
                    year = dateData.substring(0,4);
                    dateData = "";
                    dateData = day + "/" + month + "/" + year;
                    dataList.setDate(dateData);
                    data.add(dataList);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });


    }

    public void getNewsByCategoryId2(ExecutorService srv, Handler uiHandler){

        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getbycategoryid/2");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject current2 = new JSONObject(buffer.toString());
                JSONArray arr = current2.getJSONArray("items");
                List<DataListClass> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);

                    DataListClass dataList = new DataListClass(current.getInt("id"),
                            current.getString("title"),
                            current.getString("text"),
                            current.getString("date"),
                            current.getString("categoryName"),
                            current.getString("image"));
                    int index = dataList.getDate().indexOf('T');
                    String dateData = dataList.getDate().substring(0,index);
                    String day,month,year;
                    day = dateData.substring(8,10);
                    month = dateData.substring(5,7);
                    year = dateData.substring(0,4);
                    dateData = "";
                    dateData = day + "/" + month + "/" + year;
                    dataList.setDate(dateData);
                    data.add(dataList);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });


    }

    public void getNewsByCategoryId3(ExecutorService srv, Handler uiHandler){

        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getbycategoryid/3");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject current2 = new JSONObject(buffer.toString());
                JSONArray arr = current2.getJSONArray("items");
                List<DataListClass> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);

                    DataListClass dataList = new DataListClass(current.getInt("id"),
                            current.getString("title"),
                            current.getString("text"),
                            current.getString("date"),
                            current.getString("categoryName"),
                            current.getString("image"));
                    int index = dataList.getDate().indexOf('T');
                    String dateData = dataList.getDate().substring(0,index);
                    String day,month,year;
                    day = dateData.substring(8,10);
                    month = dateData.substring(5,7);
                    year = dateData.substring(0,4);
                    dateData = "";
                    dateData = day + "/" + month + "/" + year;
                    dataList.setDate(dateData);
                    data.add(dataList);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });


    }

    public void getAllCategories(ExecutorService srv, Handler uiHandler){

        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getallnewscategories");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject current = new JSONObject(buffer.toString());
                JSONArray arr = current.getJSONArray("items");
                //JSONArray arr = new JSONArray(buffer.toString());
                List<CategoriesClass> data2 = new ArrayList<>();


                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current2 = arr.getJSONObject(i);

                    CategoriesClass dataList = new CategoriesClass(current2.getInt("id"),
                            current2.getString("name"));
                    data2.add(dataList);
                }




                //CategoriesClass data2 = new CategoriesClass(current.getInt("serviceMessageCode"), current.getString("serviceMessageText"));

                Message msg = new Message();
                msg.obj = data2;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });

    }

    public void downloadImage(ExecutorService srv, Handler uiHandler,String path){
        srv.execute(()->{
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                Bitmap bitmap =  BitmapFactory.decodeStream(conn.getInputStream());

                Message msg = new Message();
                msg.obj = bitmap;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }
}
