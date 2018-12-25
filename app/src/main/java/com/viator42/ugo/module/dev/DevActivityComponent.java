package com.viator42.ugo.module.dev;

import com.viator42.ugo.AppComponent;

import dagger.Component;
import dagger.Module;

@Component(dependencies = AppComponent.class, modules={ComputerModule.class, PresenterModule.class})
//@Component(modules=ComputerModule.class)
public interface DevActivityComponent {
    void inject(DevActivity activity);
}
