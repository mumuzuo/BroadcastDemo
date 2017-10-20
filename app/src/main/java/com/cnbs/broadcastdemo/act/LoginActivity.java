package com.cnbs.broadcastdemo.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cnbs.broadcastdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.login_name)
    EditText loginName;
    @BindView(R.id.login_psw)
    EditText loginPsw;
    @BindView(R.id.login_btn)
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        title.setText(R.string.login_title);
    }

    @OnClick({R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                login();
                break;
        }
    }

    private void login() {
        String name = loginName.getText().toString().trim();
        String psw = loginPsw.getText().toString().trim();
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(psw)){
            Toast.makeText(this,"请输入用户名和密码",Toast.LENGTH_LONG).show();
        }else if("Admin".equals(name)&&"123456".equals(psw)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this,"请输入正确的用户名和密码",Toast.LENGTH_LONG).show();
        }
    }
}
