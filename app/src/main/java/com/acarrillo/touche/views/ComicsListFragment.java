package com.acarrillo.touche.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.SimpleItemAnimator;

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
        mViewModel.loadMoreComics();
        mActivity.setProgressbar(true);
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void subscribeToData() {
        mViewModel.getComics().observe(
            this,
            comics ->{
                mComicsListAdapter.setData(comics);
                mActivity.setProgressbar(false);
        });
        mViewModel.getExpandedItemPosition().observe(
            this,
            expandedId -> {
                mComicsListAdapter.setExpandedItem(expandedId);
            }
        );
        mViewModel.getErrors().observe(
                this,
                error -> {
                    showSnackbar(error.getMessage());
                    mActivity.setProgressbar(false);
                }
        );
    }

    private void initRecyclerView()
    {
        mComicsListAdapter = new ComicsListAdapter();
        ((SimpleItemAnimator)mViewBinding.recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mViewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mViewBinding.recyclerView.setAdapter(mComicsListAdapter);
        mViewBinding.recyclerView.setHasFixedSize(true);
        //This fixes problems with Picasso cache and reused viewHolders
        mViewBinding.recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 0);

        mComicsListAdapter.setOnItemClickedListener(
                (v, item, position) -> {
                    mViewModel.selectExpandedItem(position);
                });
        mViewBinding.recyclerView.setOnScrollChangeListener(
            (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (!mViewBinding.recyclerView.canScrollVertically(1)) {
                    mViewModel.loadMoreComics();
                }
            });
    }
}