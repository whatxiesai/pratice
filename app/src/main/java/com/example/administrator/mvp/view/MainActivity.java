package com.example.administrator.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.base.BaseActivity;
import com.example.administrator.mvp.model.User;
import com.example.administrator.mvp.presenter.MainPresenter;
import com.example.administrator.mvp.presenter.MainPresenterImpl;
import com.example.administrator.mvp.view.view.BaseView;
import com.example.administrator.mvp.view.view.MainBaseView;

public class MainActivity extends BaseActivity implements MainBaseView {

    private MainPresenterImpl mainPresenter;
    private EditText et_pwd;
    private EditText et_username;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = findViewById(R.id.et_username);
        et_pwd = findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.btn_login);

        mainPresenter = new MainPresenterImpl();
        mainPresenter.attachView(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_username.getText().toString();
                String pwd = et_pwd.getText().toString();
                User user = new User();
                user.name = name;
                user.pwd = pwd;
                mainPresenter.login(user);
            }
        });
    }


    @Override
    public void loadingSuccess() {
        showToast("登录成功");
    }

    @Override
    public void loadingError() {
        showToast("登录失败");
    }
}