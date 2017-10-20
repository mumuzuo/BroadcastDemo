package com.cnbs.broadcastdemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 验证静态注册实现开机启动监听
 * 1、在清单文件中声明手机开机广播的权限
 * 2、在清单文件中注册广播接收器
 * author: zuo
 * date: 2017/10/19 15:38
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"开机启动",Toast.LENGTH_LONG).show();
    }
}
