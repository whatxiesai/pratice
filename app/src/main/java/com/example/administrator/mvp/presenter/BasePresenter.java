package com.example.administrator.mvp.presenter;

import com.example.administrator.mvp.view.view.BaseView;

public interface BasePresenter<T> {

    void attachView(T view);
    void detachView();


}
