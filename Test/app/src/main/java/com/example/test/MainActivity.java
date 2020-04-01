package com.example.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DataInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        showList();
    }

    private void showList() {
        Adapter adapter = new Adapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        Random r = new Random(1);
        for (int i = 0; i < 20; i++) {
            DataInfo info = new DataInfo();
            info.height = r.nextInt(1000);
            info.width = r.nextInt(1000);
            info.title = String.format(Locale.CANADA, "这是第%d个", i);
            list.add(info);
        }
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycle_view);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //防止item 交换位置
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments(); //防止第一行到顶部有空白区域
            }
        });
        recyclerView.setLayoutManager(layoutManager);
    }

}
