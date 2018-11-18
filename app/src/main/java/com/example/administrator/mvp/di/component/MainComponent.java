package com.example.administrator.mvp.di.component;

import com.example.administrator.mvp.di.model.MainModel;
import com.example.administrator.mvp.view.MainActivity;

import dagger.Component;
import dagger.Module;

/**
 * 提供注入
 */
@Component(modules = MainModel.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
