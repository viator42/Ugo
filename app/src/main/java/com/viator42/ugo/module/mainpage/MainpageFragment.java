package com.viator42.ugo.module.mainpage;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.viator42.ugo.AppContext;
import com.viator42.ugo.R;
import com.viator42.ugo.StaticValues;
import com.viator42.ugo.model.Activity;
import com.viator42.ugo.model.AppgoodsId;
import com.viator42.ugo.model.Category;
import com.viator42.ugo.model.Recommend;
import com.viator42.ugo.module.mainpage.param.HomeParam;
import com.viator42.ugo.module.mainpage.param.HomeReParam;
import com.viator42.ugo.module.mainpage.result.HomeReItem;
import com.viator42.ugo.module.mainpage.result.HomeResult;
import com.viator42.ugo.utils.CommonUtils;
import com.viator42.ugo.utils.EndlessGridRecyclerOnScrollListener;
import com.viator42.ugo.utils.EndlessParentScrollListener;
import com.viator42.ugo.utils.ExpRecyclerView;
import com.viator42.ugo.utils.TimeoutbleProgressDialog;
import com.viator42.ugo.widget.ActivityItemView;
import com.viator42.ugo.widget.CategoryItemView;
import com.viator42.ugo.widget.RecommendItemView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainpageFragment extends Fragment implements MainpageContract.View {
    private MainpagePresenter mainpagePresenter;
    private ExpRecyclerView homeReListView;
    private HomeReAdapter homeReAdapter;
    private GridLayoutManager gridLayoutManager;
    private AppContext appContext;
    private TimeoutbleProgressDialog loadingDialog;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NestedScrollView scrollView;
    private LinearLayout categoryLayout;
    private LinearLayout recommendLayout;
    private LinearLayout activityLayout;

    private int currentPage;
    private List<Map<String,Object>> homeReList;

    public MainpageFragment() {
        // Required empty public constructor
        mainpagePresenter = new MainpagePresenter(MainpageFragment.this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appContext = (AppContext) getActivity().getApplicationContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainpage, container, false);

        homeReListView = view.findViewById(R.id.home_re_list);
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        homeReListView.setLayoutManager(gridLayoutManager);

        loadingDialog = TimeoutbleProgressDialog.createProgressDialog(getContext(), StaticValues.CONNECTION_TIMEOUT, new TimeoutbleProgressDialog.OnTimeOutListener() {
            @Override
            public void onTimeOut(TimeoutbleProgressDialog dialog) {
                loadingDialog.dismiss();
                CommonUtils.makeToast(getContext(), "加载内容失败");
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reload();
            }
        });

        scrollView = view.findViewById(R.id.scroll_view);
        scrollView.setOnScrollChangeListener(new EndlessParentScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                load();
            }
        });

        categoryLayout = view.findViewById(R.id.category);
        recommendLayout = view.findViewById(R.id.recommend);
        activityLayout = view.findViewById(R.id.activity);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        reload();
    }

    private void reload() {
        currentPage = 0;
        homeReList = null;
        homeReAdapter = null;

        load();
    }

    private void load() {
        HomeParam homeParam = new HomeParam();
        homeParam.android_version = "5.6";
        homeParam.area = "370105";

        mainpagePresenter.loadHome(homeParam, true);

        HomeReParam homeReParam = new HomeReParam();
        homeReParam.android_version = "5.6";
        homeReParam.area = "370105";
        homeReParam.min = currentPage * StaticValues.PAGE_COUNT;
        homeReParam.max = homeReParam.min + StaticValues.PAGE_COUNT;

        mainpagePresenter.loadHomeRe(homeReParam);
    }

    @Override
    public void listHome(HomeResult homeResult) {
        appContext.aesKey = homeResult.aesKey;

        categoryLayout.removeAllViewsInLayout();
        for(Category category: homeResult.data.category) {
            CategoryItemView categoryItemView = new CategoryItemView(getContext(), null, category);

            categoryLayout.addView(categoryItemView);
        }


        recommendLayout.removeAllViewsInLayout();
        for(Recommend recommend: homeResult.data.recommend) {
            RecommendItemView recommendItemView = new RecommendItemView(getContext(), null, recommend);

            recommendLayout.addView(recommendItemView);
        }

        activityLayout.removeAllViewsInLayout();
        for(Activity activity: homeResult.data.activity){
            ActivityItemView activityItemView = new ActivityItemView(getContext(), null, activity);

            activityLayout.addView(activityItemView);
        }

    }

    @Override
    public void listHomeRe(ArrayList<HomeReItem> homeReItems) {
        if(homeReList == null) {
            homeReList = new ArrayList<Map<String,Object>>();
        }

        for (HomeReItem homeReItem: homeReItems) {
            AppgoodsId appgoodsId = homeReItem.appgoodsId;
            Map<String,Object> item = new HashMap();
            item.put("id", appgoodsId.id);
            item.put("obj", appgoodsId);

            homeReList.add(item);
        }

        if(homeReAdapter == null) {
            homeReAdapter = new HomeReAdapter(homeReList, getContext());
            homeReListView.setAdapter(homeReAdapter);
        }
        else {
            homeReAdapter.notifyDataSetChanged();
        }

        if(homeReItems.isEmpty() && currentPage > 0) {
            CommonUtils.makeToast(getContext(), "没有更多了");
        }
        else {
            currentPage += 1;
        }

    }

    @Override
    public void setProgressingDialog(boolean isShow) {
        if(isShow) {
            loadingDialog.show();
        }
        else {
            loadingDialog.dismiss();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setPresenter(MainpageContract.Presenter presenter) {

    }

    @Override
    public Context getContext() {
        return super.getContext();
    }
}
