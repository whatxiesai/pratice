package com.example.administrator.mvp.di.model;

import com.example.administrator.mvp.view.view.MainBaseView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModel {
    private MainBaseView mainBaseView;

    public MainModel(MainBaseView view){
        this.mainBaseView = view;
    }

    @Provides
    public MainBaseView provideMainBase() {
        return mainBaseView;
    }
}
