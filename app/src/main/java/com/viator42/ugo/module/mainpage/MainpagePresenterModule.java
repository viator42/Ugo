package com.viator42.ugo.module.mainpage;

import com.viator42.ugo.module.category.CategoryContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MainpagePresenterModule {
    private MainpageContract.View view;

    public MainpagePresenterModule(MainpageContract.View view) {
        this.view = view;
    }

    @Provides
    MainpageContract.View providePresenter() {
        return this.view;
    }

}
