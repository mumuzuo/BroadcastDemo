package com.cnbs.broadcastdemo.act;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

import com.cnbs.broadcastdemo.R;
import com.cnbs.broadcastdemo.broadcast.LocalBroadcastReceiver;
import com.cnbs.broadcastdemo.broadcast.NetworkChangeReceiver;
import com.cnbs.broadcastdemo.utils.Constant;

/**
 * 注册广播监听
 * @author zuo
 * @date 2017/10/19 14:10
 */
public class MainActivity extends BaseActivity {

    private NetworkChangeReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brNetwork();        //网络状态监听广播
        brSendMy();          //自定义广播
        brSendOrder();        //有序广播
        brLocalBroadcast();     //本地广播
        brLoginOut();           //强制下线广播
    }

    /**
     * 强制下线的广播
     */
    private void brLoginOut() {
        Button sendMyBr = (Button) findViewById(R.id.send_loginout_br_btn);
        sendMyBr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.cnbs.broadcastdemo.LOGINOUT_BROADCAST");
                intent.putExtra(Constant.BR_Msg,"您的账号已被强制下线，请重新登录");
                sendBroadcast(intent);
            }
        });
    }

    /**
     * 本地广播，发出的广播只能在程序内部进行传递，本地广播的接收器也只能接收来自本应用发出的广播
     * 主要使用了一个LocalBroadcastManager
     */
    private LocalBroadcastManager localBroadcastManager;
    private LocalBroadcastReceiver localBroadcastReceiver;
    private void brLocalBroadcast() {
        //1、获取本地广播管理类的实例  send_local_br_btn
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        Button sendMyBr = (Button) findViewById(R.id.send_local_br_btn);
        sendMyBr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送本地广播
                Intent intent = new Intent("com.cnbs.broadcastdemo.LOCAL_BROADCAST");
                intent.putExtra(Constant.BR_Msg,"本地广播");
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        //2、注册本地广播监听器(动态注册) , 注意一定要注销
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.cnbs.broadcastdemo.LOCAL_BROADCAST");
        localBroadcastReceiver = new LocalBroadcastReceiver();
        localBroadcastManager.registerReceiver(localBroadcastReceiver,intentFilter);

    }

    /**
     * 发送有序广播，可以解决有信息传递优先顺序要求的相关问题
     * 只是发送广播的方法不一样
     * 标准广播(无序广播)的发送方法为sendBroadcast(intent);
     * 有序广播的发送方法为sendOrderedBroadcast(intent,null);
     */
    private void brSendOrder() {
        Button sendMyBr = (Button) findViewById(R.id.send_order_br_btn);
        sendMyBr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.cnbs.broadcastdemo.MY_BROADCAST");
                intent.putExtra(Constant.BR_Msg,"有序广播");
                sendOrderedBroadcast(intent,null);  //第二个参数是个与权相相关的字符串，传入null即可
            }
        });
    }

    /**
     * 发送自定义广播
     */
    private void brSendMy() {
        Button sendMyBr = (Button) findViewById(R.id.send_my_br_btn);
        sendMyBr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.cnbs.broadcastdemo.MY_BROADCAST");
                intent.putExtra(Constant.BR_Msg,"自定义广播");
                sendBroadcast(intent);
            }
        });
    }

    /**
     * 网络变化广播监听
     */
    private void brNetwork() {
        //广播接收器
        receiver = new NetworkChangeReceiver();
        IntentFilter intentFilter = new IntentFilter();//new 一个意图过滤器
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE"); //网络状态改变的action
        registerReceiver(receiver,intentFilter);//注册广播
        //动态注册的广播接收器一定要注销
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
    }
}
