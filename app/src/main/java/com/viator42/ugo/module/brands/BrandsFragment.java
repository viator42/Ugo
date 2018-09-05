package com.viator42.ugo.module.brands;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viator42.ugo.R;
import com.viator42.ugo.model.AppBrandAll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrandsFragment extends Fragment implements BrandsContract.View {
    private BrandsPresenter brandsPresenter;
    private RecyclerView brandsListView;
    private BrandsAdapter brandsAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<Map<String,Object>> brandsList;

    public BrandsFragment() {
        // Required empty public constructor
        brandsPresenter = new BrandsPresenter(BrandsFragment.this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brands, container, false);

        brandsListView = view.findViewById(R.id.brands_listview);
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        brandsListView.setLayoutManager(gridLayoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        load();

    }

    private void load() {
        brandsPresenter.loadAll();
    }

    @Override
    public void listAll(ArrayList<AppBrandAll> appBrandAlls) {
        brandsList = new ArrayList<Map<String,Object>>();

        for (AppBrandAll appBrandAll: appBrandAlls) {
            Map<String,Object> item = new HashMap();
            item.put("id", appBrandAll.id);
            item.put("obj", appBrandAll);

            brandsList.add(item);
        }

        brandsAdapter = new BrandsAdapter(brandsList, getContext());
        brandsListView.setAdapter(brandsAdapter);

    }

    @Override
    public void setPresenter(BrandsContract.Presenter presenter) {

    }
}
