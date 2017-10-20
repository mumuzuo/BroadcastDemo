package com.cnbs.broadcastdemo.broadcast;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *创建广播接收器
 * @author zuo
 * @date 2017/10/17 11:34
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private AlertDialog alertDialog;

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = "";
        //获取管理网络连接的系统服务类
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取网络连接信息,这里一定要去声明权限
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo !=null && networkInfo.isAvailable()){
            int type = networkInfo.getType();
            switch (type){
                case ConnectivityManager.TYPE_WIFI:
                    msg = "已连接wifi";
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    msg = "已连接数据流量";
                    break;
                case ConnectivityManager.TYPE_BLUETOOTH:
                    msg = "已连接蓝牙";
                    break;
                case ConnectivityManager.TYPE_VPN:
                    msg = "已连接VPN";
                    break;
                default:
                    break;
            }
        }else {
            msg = "网络断开连接！！";
        }
        showMsg(context, msg);
    }

    private void showMsg(Context context, String msg) {
        if (alertDialog!=null){
            alertDialog.dismiss();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Dialog);
        alertDialog = builder.create();
        alertDialog.setTitle("消息通知！");
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
