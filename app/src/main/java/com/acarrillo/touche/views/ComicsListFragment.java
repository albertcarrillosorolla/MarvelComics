package com.acarrillo.touche.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.acarrillo.touche.R;
import com.acarrillo.touche.databinding.ComicsListFragmentBinding;
import com.acarrillo.touche.modelviews.ComicsListViewModel;
import com.acarrillo.touche.views.adapters.ComicsListAdapter;
import com.acarrillo.touche.views.base.BaseFragment;

public class ComicsListFragment extends BaseFragment<ComicsListViewModel> {
    ComicsListFragmentBinding mViewBinding;
    ComicsListAdapter mComicsListAdapter;

    public ComicsListFragment() { super(R.layout.comics_list_fragment); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewBinding = ComicsListFragmentBinding.bind(view);
        subscribeToData();
        initRecyclerView();
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void subscribeToData() {
        mViewModel.getComics().observe(
            this,
            result -> result.apply(
                error -> {
                    showSnackbar(error.getMessage());
                },
                comics ->{
                    mComicsListAdapter.setData(comics);
                    showSnackbar(comics.get(3).getImage(0));
                }));
    }

    private void initRecyclerView()
    {
        mComicsListAdapter = new ComicsListAdapter();
        mViewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mViewBinding.recyclerView.setAdapter(mComicsListAdapter);
    }
}