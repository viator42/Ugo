package com.viator42.ugo.module.dev;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viator42.ugo.R;
import com.viator42.ugo.databinding.ItemEmployeeBinding;

import java.util.List;

public class BindingListAdapter extends RecyclerView.Adapter<BindingListAdapter.BindingListViewHolder> {
    private List<Employee> list;

    public BindingListAdapter(List<Employee> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BindingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEmployeeBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_employee, parent, false
        );

        return new BindingListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingListViewHolder holder, int position) {
        Employee employee = list.get(position);
        holder.getBinding().setEmployee(employee);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BindingListViewHolder extends RecyclerView.ViewHolder {
        ItemEmployeeBinding binding;

        public BindingListViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemEmployeeBinding) binding;
        }

        public ItemEmployeeBinding getBinding() {
            return binding;
        }
    }

}
