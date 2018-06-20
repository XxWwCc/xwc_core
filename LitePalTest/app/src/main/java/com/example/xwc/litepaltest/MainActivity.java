package com.example.xwc.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*创建数据库*/
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });
        /*添加2行数据*/
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setAuthor("Dan Brow");
                book.setPages(454);
                book.setName("The Da Vinci Code");
                book.setPress("Unknow");
                book.setPrice(16.96);
                book.save();
                Book book1 = new Book();
                book1.setAuthor("Dan Brown");
                book1.setPages(454);
                book1.setName("The Lost Symbol");
                book1.setPress("Unknow");
                book1.setPrice(15.95);
                book1.save();
            }
        });
        /*更新数据库*/
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.setPages(510);
                book.updateAll("name = ? and author = ?","The Lost Symbol","Dan Brown");
            }
        });
        /*删除数据*/
        Button deleteAll = findViewById(R.id.delete_data);
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class,"price < ?","15");
            }
        });
        /*查找整个数据*/
        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book:books){
                    Log.d("MainActivity","-----------------------------");
                    Log.d("MainActivity", "book name is "+book.getName());
                    Log.d("MainActivity", "book author is "+book.getAuthor());
                    Log.d("MainActivity", "book pages is "+book.getPages());
                    Log.d("MainActivity", "book price is "+book.getPrice());
                    Log.d("MainActivity", "book press is "+book.getPress());
                }
            }
        });
        /*查找第一行数据*/
        Button first = findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = LitePal.findFirst(Book.class);
                    Log.d("MainActivity","-----------------------------");
                    Log.d("MainActivity", "book name is "+book.getName());
                    Log.d("MainActivity", "book author is "+book.getAuthor());
                    Log.d("MainActivity", "book pages is "+book.getPages());
                    Log.d("MainActivity", "book price is "+book.getPrice());
                    Log.d("MainActivity", "book press is "+book.getPress());

            }
        });
        /*查找最后一行数据*/
        Button last = findViewById(R.id.last);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = LitePal.findLast(Book.class);
                    Log.d("MainActivity","-----------------------------");
                    Log.d("MainActivity", "book name is "+book.getName());
                    Log.d("MainActivity", "book author is "+book.getAuthor());
                    Log.d("MainActivity", "book pages is "+book.getPages());
                    Log.d("MainActivity", "book price is "+book.getPrice());
                    Log.d("MainActivity", "book press is "+book.getPress());
            }
        });
        /*select 关键字查找数据*/
        Button select = findViewById(R.id.Button1);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.select("name","author").find(Book.class);
                for (Book book:books){
                    Log.d("MainActivity","-----------------------------");
                    Log.d("MainActivity", "book name is "+book.getName());
                    Log.d("MainActivity", "book author is "+book.getAuthor());
                    Log.d("MainActivity", "book pages is "+book.getPages());
                    Log.d("MainActivity", "book price is "+book.getPrice());
                    Log.d("MainActivity", "book press is "+book.getPress());
                }
            }
        });
        /*where 关键字查找数据*/
        Button where = findViewById(R.id.Button2);
        where.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.where("pages > ?","400").find(Book.class);
                for (Book book:books){
                    Log.d("MainActivity","-----------------------------");
                    Log.d("MainActivity", "book name is "+book.getName());
                    Log.d("MainActivity", "book author is "+book.getAuthor());
                    Log.d("MainActivity", "book pages is "+book.getPages());
                    Log.d("MainActivity", "book price is "+book.getPrice());
                    Log.d("MainActivity", "book press is "+book.getPress());
                }
            }
        });
        /*order 关键字查找数据*/
        Button order = findViewById(R.id.Button3);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.order("price desc").find(Book.class);
                for (Book book:books){
                    Log.d("MainActivity","-----------------------------");
                    Log.d("MainActivity", "book name is "+book.getName());
                    Log.d("MainActivity", "book author is "+book.getAuthor());
                    Log.d("MainActivity", "book pages is "+book.getPages());
                    Log.d("MainActivity", "book price is "+book.getPrice());
                    Log.d("MainActivity", "book press is "+book.getPress());
                }
            }
        });
        /*limit 关键字查找数据
        * 查找的是前三行数据
        * */
        Button limit = findViewById(R.id.Button4);
        limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.limit(3).find(Book.class);
                for (Book book:books){
                    Log.d("MainActivity","-----------------------------");
                    Log.d("MainActivity", "book name is "+book.getName());
                    Log.d("MainActivity", "book author is "+book.getAuthor());
                    Log.d("MainActivity", "book pages is "+book.getPages());
                    Log.d("MainActivity", "book price is "+book.getPrice());
                    Log.d("MainActivity", "book press is "+book.getPress());
                }
            }
        });
        /*limit+offset 关键字查找数据
        * offset 表示查询结果的偏移量
        * 下面表示查找第2、3、4行总共三行数据
        * */
        Button offset = findViewById(R.id.Button5);
        offset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.limit(3).offset(1).find(Book.class);
                for (Book book:books){
                    Log.d("MainActivity","-----------------------------");
                    Log.d("MainActivity", "book name is "+book.getName());
                    Log.d("MainActivity", "book author is "+book.getAuthor());
                    Log.d("MainActivity", "book pages is "+book.getPages());
                    Log.d("MainActivity", "book price is "+book.getPrice());
                    Log.d("MainActivity", "book press is "+book.getPress());
                }
            }
        });
        /*5个关键字整合查找数据*/
        Button all = findViewById(R.id.Button6);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.select("name","author","pages")
                        .where("pages > ?","400")
                        .order("pages")
                        .limit(3)
                        .offset(1)
                        .find(Book.class);
                for (Book book:books){
                    Log.d("MainActivity","-----------------------------");
                    Log.d("MainActivity", "book name is "+book.getName());
                    Log.d("MainActivity", "book author is "+book.getAuthor());
                    Log.d("MainActivity", "book pages is "+book.getPages());
                    Log.d("MainActivity", "book price is "+book.getPrice());
                    Log.d("MainActivity", "book press is "+book.getPress());
                }
            }
        });
    }
}
