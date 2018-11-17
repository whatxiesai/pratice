package com.example.administrator.mvp.presenter;

import com.example.administrator.mvp.view.view.BaseView;

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T>{

    public T mPresenterView;

    @Override
    public void attachView(T view) {
        this.mPresenterView = view;
    }

    @Override
    public void detachView() {
        this.mPresenterView = null;
    }
}
