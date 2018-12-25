package com.viator42.ugo.module.category;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryPresenterModule {
    private CategoryContract.View view;

    public CategoryPresenterModule(CategoryContract.View view) {
        this.view = view;
    }

    @Provides
    CategoryContract.View providePresenter() {
        return this.view;
    }

}
