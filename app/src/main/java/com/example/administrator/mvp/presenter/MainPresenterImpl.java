package com.example.administrator.mvp.presenter;

import com.example.administrator.mvp.model.User;
import com.example.administrator.mvp.view.view.MainBaseView;

import javax.inject.Inject;

public class MainPresenterImpl extends BasePresenterImpl<MainBaseView> implements MainPresenter {

    @Inject
    public MainPresenterImpl(MainBaseView baseView){

    }

    @Override
    public void login(User user) {
        if (user.name.equals("张三") && user.pwd.equals("123")) {
            mPresenterView.loadingSuccess();
        } else {
            mPresenterView.loadingError();
        }
    }
}
