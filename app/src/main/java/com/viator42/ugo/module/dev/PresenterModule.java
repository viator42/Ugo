package com.viator42.ugo.module.dev;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    private DevContract.View view;

    public PresenterModule(DevContract.View view) {
        this.view = view;
    }

    @Provides
    DevContract.View providePresenter() {
        return view;
    };
}
