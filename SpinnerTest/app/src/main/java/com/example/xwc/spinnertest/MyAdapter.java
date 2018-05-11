package com.example.xwc.spinnertest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter {
    public MyAdapter() {

    }
/*

    public static List<String> getData() {
        List<String> list = new ArrayList<String>();
        list.add("上海");
        list.add("北京");
        list.add("深圳");
        return list;
    }
*/

    public static List<Map<String, Object>> getMapData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("image", R.drawable.face1);
        map1.put("text", "表情1");

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("image", R.drawable.face2);
        map2.put("text", "表情2");

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("image", R.drawable.face3);
        map3.put("text", "表情3");


        list.add(map1);
        list.add(map2);
        list.add(map3);

        return list;

    }
}
