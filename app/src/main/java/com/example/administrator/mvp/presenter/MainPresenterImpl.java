package com.example.administrator.mvp.presenter;

import com.example.administrator.mvp.model.User;
import com.example.administrator.mvp.view.view.MainBaseView;

public class MainPresenterImpl extends BasePresenterImpl<MainBaseView> implements MainPresenter {

    @Override
    public void login(User user) {
        if (user.name.equals("张三") && user.pwd.equals("123")) {
            mPresenterView.loadingSuccess();
        } else {
            mPresenterView.loadingError();
        }
    }
}
