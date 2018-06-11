package com.example.wuliangzhu.myros;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.SurfaceView;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wuliangzhu on 2018/3/20.
 */

public class RosCameraView extends SurfaceView implements Callback, Runnable{
        private static final int MAX_QUEUE_SIZE = 20;

        // 用于控制SurfaceView 的大小、格式等，并且主要用于监听SurfaceView 的状态
        private SurfaceHolder sfh;
        // 声明一个画笔
        private Paint paint;
        // 文本坐标
        private int textX = 30, textY = 30;
        private float dx = 0, dy = 0;
        // 声明一个线程
        private Thread th;
        private boolean isCreated; // 标示surfacecreate是否被调用
        // 线程消亡的标识符
        private boolean flag;
        // 声明一个画布
        private Canvas canvas;

        // 主要的显示资源
        private Bitmap mainImage;

        // 控制接收到的图片队列
        private BlockingQueue<Bitmap> queue;
        // 控制图片缩放
        private Matrix scale;


        // 声明屏幕的宽高
        private int screenW, screenH;

        /**
         * SurfaceView 初始化函数
         *
         * @param context
         */
        public RosCameraView(Context context) {
            super(context);
            _init();

        }

    private void _init() {
        // 实例SurfaceView
        sfh = this.getHolder();
        // 为SurfaceView添加状态监听
        sfh.addCallback(this);
        // 实例一个画笔
        paint = new Paint();
        // 设置字体大小
        paint.setTextSize(20);
        // 设置画笔的颜色
        paint.setColor(Color.WHITE);
        // 设置焦点
        setFocusable(true);

        this.queue = new LinkedBlockingQueue<Bitmap>();
        this.scale = new Matrix();

        // 最好用setscale 注意 pre post的调用，就像冒泡法
        this.scale.postScale(0.5f, 0.5f);
        this.scale.postTranslate(80, 0);
    }

    public RosCameraView(Context context, AttributeSet attrs) {
            super(context, attrs);
            _init();
    }

    public RosCameraView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        _init();
    }
        /**
         * SurfaceView 视图创建，响应此函数
         * 当出现界面pause的时候 create会被调用多次
         */
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            screenW = this.getWidth();
            screenH = this.getHeight();



            flag = true;
            // 实例线程
            th = new Thread(this);
            // 启动线程
            th.start();

            System.out.println("=================surfaceCreate====================");
        }

        public void updateMainImage(Bitmap mainImage) {
            if (this.queue.size() >= MAX_QUEUE_SIZE) {
                this.queue.poll();
            }

            this.queue.offer(mainImage);
        }

        /**
         * SurfaceView 视图状态发生改变时，响应此函数
         */
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        /**
         * SurfaceView 视图消亡时，响应此函数
         */
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            flag = false;
        }


        /**
         * 游戏绘图
         */
        public void myDraw() {
            try {
                canvas = sfh.lockCanvas();
                if (canvas != null) {
                    Bitmap buf = null;

                    buf = this.queue.poll();
                    if (buf != null) {
                        this.mainImage = buf;
                    }

                    if (this.mainImage != null) {
                        canvas.drawBitmap(this.mainImage, this.scale,null);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    sfh.unlockCanvasAndPost(canvas);
                }
            }
        }

        /**
         * 触屏事件监听
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            textX = (int) event.getX();
            textY = (int) event.getY();
            return true;
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            return super.onKeyDown(keyCode, event);
        }

        /**
         * 游戏逻辑
         */
        private void logic() {
        }

        private boolean isRunning = false;
        @Override
        public void run() {
            while (flag) {
                isRunning = true;
                long start = System.currentTimeMillis();
                myDraw();
                logic();
                long end = System.currentTimeMillis();
                try {
                    if (end - start < 50) {
                        Thread.sleep(50 - (end - start));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

}
