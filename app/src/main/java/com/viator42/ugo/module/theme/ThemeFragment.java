package com.viator42.ugo.module.theme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viator42.ugo.R;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.model.Theme;
import com.viator42.ugo.module.mainpage.HomeReAdapter;
import com.viator42.ugo.module.mainpage.result.HomeReItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThemeFragment extends Fragment implements ThemeContract.View {
    private RecyclerView themeListView;
    private ThemePresenter themePresenter;
    private ThemeAdapter themeAdapter;
    private LinearLayoutManager layoutManager;
    private List<Map<String,Object>> themeList;

    public ThemeFragment() {
        // Required empty public constructor
        themePresenter = new ThemePresenter(ThemeFragment.this);
    }

    @Override
    public void onStart() {
        super.onStart();
        load();
    }

    private void load() {
        themePresenter.load();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_theme, container, false);

        themeListView = view.findViewById(R.id.theme_list);
        layoutManager = new LinearLayoutManager(getContext());
        themeListView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void list(ArrayList<Theme> themes) {
        themeList = new ArrayList<Map<String,Object>>();

        for (Theme theme: themes) {
            Map<String,Object> item = new HashMap();
            item.put("id", theme.id);
            item.put("obj", theme);

            themeList.add(item);
        }

        themeAdapter = new ThemeAdapter(themeList, getContext());
        themeListView.setAdapter(themeAdapter);

    }

    @Override
    public void setPresenter(ThemeContract.Presenter presenter) {

    }
}
