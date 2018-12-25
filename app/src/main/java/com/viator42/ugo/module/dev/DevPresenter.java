package com.viator42.ugo.module.dev;

import com.viator42.ugo.utils.CommonUtils;

import javax.inject.Inject;

public class DevPresenter implements DevContract.Presenter {
    DevContract.View view;

    @Inject
    public DevPresenter(DevContract.View view) {
        this.view = view;
    }

    @Override
    public String iamHere() {
        return "Dev Presenter loaded";
    }
}
