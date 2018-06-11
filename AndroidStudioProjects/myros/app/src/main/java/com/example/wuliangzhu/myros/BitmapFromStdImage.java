package com.example.wuliangzhu.myros;

import android.graphics.Bitmap;

import org.jboss.netty.buffer.ChannelBuffer;
import org.ros.android.MessageCallable;

import sensor_msgs.Image;

/**
 * Created by wuliangzhu on 2018/3/19.
 */

public class BitmapFromStdImage implements MessageCallable<Bitmap, Image> {
    @Override
    public Bitmap call(Image e) {

        int w = e.getWidth();
        int h = e.getHeight();

        Bitmap tmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        ChannelBuffer fromBuffer = e.getData();
        int size = fromBuffer.readableBytes();

        System.out.println("the image data w x h ? size: " + (w * h) + "==" + size);

        if (size % 3 != 0) {
            System.out.println("Fatal Error 数据格式错误");
        }
        // the data is bgr8
        byte b = 0, g = 0, r = 0;
        int color = 0;
        int[] buffer = new int[size / 3];
        for(int i = 0; i < w * h; i++) {
            b = fromBuffer.readByte();
            g = fromBuffer.readByte();
            r = fromBuffer.readByte();
            color = (255 << 24) | ((b&0xFF) << 16) | ((g&0xFF) << 8) | (r&0xFF);

            buffer[i] = color;
        }

        tmp.setPixels(buffer, 0,  w, 0, 0, w, h);
        System.out.println("now time:" + (System.currentTimeMillis()/1000));
        return tmp;
    }
}
