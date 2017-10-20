package com.cnbs.broadcastdemo.broadcast;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cnbs.broadcastdemo.R;
import com.cnbs.broadcastdemo.utils.Constant;

/**
 *创建广播接收器接收自定义广播
 * 1、在清单文件中对接收器进行注册，添加自定义广播标识
 * @author zuo
 * @date 2017/10/17 11:34
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private AlertDialog alertDialog;

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra(Constant.BR_Msg);
        showMsg(context, "收到"+msg);
    }

   private void showMsg(Context context, String msg) {
       Toast toast = new Toast(context);
       LinearLayout linearLayout = new LinearLayout(context);

       ImageView imageView = new ImageView(context);
       imageView.setPadding(40,20,20,20);
       imageView.setImageResource(R.drawable.class_action_logo);

       TextView textView = new TextView(context);
       textView.setText(msg);
       textView.setTextColor(Color.BLUE);
       textView.setGravity(Gravity.CENTER);
       textView.setPadding(0,20,40,20);
//       textView.setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(R.drawable.class_action_logo),null,null,null);
//       textView.setBackgroundColor(Color.argb(255,199,237,204));

       linearLayout.setOrientation(LinearLayout.HORIZONTAL);
       linearLayout.setGravity(Gravity.CENTER_VERTICAL);
       linearLayout.setBackgroundColor(Color.argb(255,199,237,204));
       linearLayout.addView(imageView, 0);
       linearLayout.addView(textView, 1);

       toast.setView(linearLayout);
       toast.setGravity(Gravity.CENTER,0,0);
       toast.setDuration(Toast.LENGTH_LONG);
       toast.show();

       /**
        * 1、我们程序发出的这样类型的广播是可以被其他应用程序接收到的，这些广播都属于系统全局广播
        * 2、如果我们要发送只能在自己应用程序中接收的广播，可以使用本地广播
        */

       //优先级高的广播可以截断广播，不让广播继续传递,需要调用如下的方法
       abortBroadcast();
   }
}
