package com.viator42.ugo.module.mainpage;

import com.viator42.ugo.AppComponent;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = {MainpagePresenterModule.class})
public interface MainpageActivityComponent {
    void inject(MainpageFragment fragment);

}
