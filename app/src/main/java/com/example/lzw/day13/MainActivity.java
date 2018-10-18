package com.example.lzw.day13;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_view = (Button) findViewById(R.id.btn_view);
        findViewById(R.id.btn_click).setOnClickListener(this);
        findViewById(R.id.btn_click2).setOnClickListener(this);
        findViewById(R.id.btn_click3).setOnClickListener(this);
        findViewById(R.id.btn_click4).setOnClickListener(this);
        findViewById(R.id.btn_click5).setOnClickListener(this);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast();
            }
        });
    }


    //平移
    private void trance() {

//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 600);
//        valueAnimator.setDuration(3000);
//        valueAnimator.start();
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                btn_view.setTranslationX((Float) valueAnimator.getAnimatedValue());
//
//            }
//        });
        //子类动画

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn_view, "translationX", 0, 200, -100, 300, 0);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }

    //旋转
    public void rotation() {
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 200, -100, 500, 0);
//        valueAnimator.setDuration(3000);
//        valueAnimator.start();
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                btn_view.setRotation((Float) valueAnimator.getAnimatedValue());
//            }
//        });
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn_view, "rotationY", 0, 360);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }

    //淡入淡出
    public void alpha() {
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 0.8f, 0.5f, 0, 0.5f, 0.8f, 1);
//        valueAnimator.setDuration(3000);
//        valueAnimator.start();
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                btn_view.setAlpha((Float) valueAnimator.getAnimatedValue());
//            }
//        });
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn_view,"alpha",1,0.8f,0.5f,0,0.5f,0.8f,1);
        objectAnimator.setDuration(3000);
        objectAnimator.setRepeatCount(10);//执行次数
        objectAnimator.start();
    }

    //渐变

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void argb() {
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(2);
//        valueAnimator.setDuration(3000);
//        valueAnimator.start();
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                btn_view.setScaleX((Float) valueAnimator.getAnimatedValue());
//            }
//        });

        ObjectAnimator objectAnimator = ObjectAnimator.ofArgb(btn_view,"backgroundColor",0xffff00ff, 0xffffff00, 0xffff00ff);
        objectAnimator.setDuration(3000);
        objectAnimator.setRepeatCount(-1);//执行次数
        objectAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_view:
                toast();
                break;

            case R.id.btn_click:
                //旋转
                trance();
                break;
            case R.id.btn_click2:
                //旋转
                trance();
                rotation();
                break;
            case R.id.btn_click3:
                alpha();//淡入淡出
                break;
            case R.id.btn_click4:
                //渐变
                argb();
                break;
            case R.id.btn_click5:
                    show();
                break;

        }
    }

    private void toast() {
        Toast.makeText(this, "---", Toast.LENGTH_SHORT).show();
    }
    //显示状态栏
    private void show() {
        NotificationManager mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("用户更新了")//设置通知栏标题
                .setContentText("新版本来了！！！！")
                .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL, this)) //设置通知栏点击意图
                .setTicker("新版本来了") //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                .setSound(Uri.withAppendedPath(
                        MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "5"))
                .setSmallIcon(R.mipmap.ic_launcher);//设置通知小ICON
        mNotificationManager.notify(3, mBuilder.build());
    }

    private PendingIntent getDefalutIntent(int flagAutoCancel, MainActivity mainActivity) {
        return  null;
    }
}
