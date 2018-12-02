package com.example.administrator.mvp.view;


/**
 * Factory - 生成注入对象
 * Component - 建立注入对象和被注入对象的联系
 * Injector - 被注入对象和注入对象赋值
 */

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.base.BaseActivity;
import com.example.administrator.mvp.presenter.MainPresenterImpl;
import com.example.administrator.mvp.view.view.MainBaseView;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainBaseView {

    @Inject
    public MainPresenterImpl mainPresenter;
    private EditText et_pwd;
    private EditText et_username;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        et_username = findViewById(R.id.et_username);
//        et_pwd = findViewById(R.id.et_pwd);
//        btn_login = findViewById(R.id.btn_login);
//
//        DaggerMainComponent.builder().mainModel(new MainModel(this)).build().inject(this);
////        mainPresenter = new MainPresenterImpl();
//        mainPresenter.attachView(this);
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = et_username.getText().toString();
//                String pwd = et_pwd.getText().toString();
//                User user = new User();
//                user.name = name;
//                user.pwd = pwd;
//                mainPresenter.login(user);
//            }
//        });
    }

    @Override
    public void loadingSuccess() {

    }

    @Override
    public void loadingError() {

    }


//    @Override
//    public void loadingSuccess() {
//        showToast("登录成功");
//    }
//
//    @Override
//    public void loadingError() {
//        showToast("登录失败");
//    }
}
