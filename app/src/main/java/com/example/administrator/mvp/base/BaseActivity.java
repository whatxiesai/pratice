package com.example.administrator.mvp.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.mvp.view.view.BaseView;

public class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
