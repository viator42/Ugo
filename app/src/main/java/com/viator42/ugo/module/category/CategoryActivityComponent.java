package com.viator42.ugo.module.category;

import com.viator42.ugo.AppComponent;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = {CategoryPresenterModule.class})
public interface CategoryActivityComponent {
    void inject(CategoryActivity activity);
}
