package com.cnbs.broadcastdemo.broadcast;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

import com.cnbs.broadcastdemo.act.LoginActivity;
import com.cnbs.broadcastdemo.utils.ActivityCollector;
import com.cnbs.broadcastdemo.utils.Constant;

/**
 * 强制下线广播，使用不可取消的dialog来实现强制下线功能
 * 需要注意的是要设置dialog的类型，以保证可以在广播接收器中正常弹出
 * 广播的触发可以由后台推送完成
 * @author zuo
 * @date 2017/10/17 11:34
 */
public class LoginoutBroadcastReceiver extends BroadcastReceiver {

    private AlertDialog alertDialog;

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra(Constant.BR_Msg);
        showMsg(context, msg);
    }

   private void showMsg(final Context context, String msg) {
       AlertDialog.Builder builder = new AlertDialog.Builder(context);
       builder.setTitle("警告");
       builder.setMessage(msg);
       builder.setCancelable(false);        //点击背景不可消失
       builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               //强制下线,销毁所有活动
               ActivityCollector.finishAll();
               //新建任务栈启动LoginActivity
               Intent intent = new Intent(context, LoginActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(intent);
           }
       });
       AlertDialog alertDialog = builder.create();
       //将dialog的类型设置为系统窗口，保证在广播接收器中可以正常弹出
       alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
       alertDialog.show();
   }
}
