package com.viator42.ugo.module.theme;

import com.viator42.ugo.base.BaseView;
import com.viator42.ugo.model.Theme;

import java.util.ArrayList;

public interface ThemeContract {
    interface View extends BaseView<ThemeContract.Presenter> {
        public void list(ArrayList<Theme> themes);
    }

    interface Presenter {
        public void load();
    }
}
