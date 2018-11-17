package com.example.administrator.mvp.presenter;

import com.example.administrator.mvp.model.User;
import com.example.administrator.mvp.view.view.MainBaseView;

public interface MainPresenter extends BasePresenter<MainBaseView> {
    void login(User user);
}
