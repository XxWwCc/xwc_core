package com.example.xwc.spinnertest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class plan_C extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner spinner = (Spinner) this.findViewById(R.id.spinner);
        List<Map<String, Object>> data = MyAdapter.getMapData();
        SimpleAdapter simpleAdapter = new SimpleAdapter(plan_C.this,
                data, R.layout.item, new String[]{"image", "text"},
                new int[]{R.id.imageView, R.id.textView});
        spinner.setAdapter(simpleAdapter);

        //选中事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String title = ((Map<String, Object>)spinner.getItemAtPosition(i)).get("text").toString();
                setTitle(title);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
