package com.acarrillo.touche.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.acarrillo.touche.R;
import com.acarrillo.touche.databinding.ComicsListFragmentBinding;
import com.acarrillo.touche.domain.entities.ComicEntity;
import com.acarrillo.touche.modelviews.ComicsListViewModel;
import com.acarrillo.touche.views.adapters.ComicsListAdapter;
import com.acarrillo.touche.views.base.BaseFragment;

public class ComicsListFragment extends BaseFragment<ComicsListViewModel> {
    private ComicsListFragmentBinding mViewBinding;
    private ComicsListAdapter mComicsListAdapter;

    public ComicsListFragment() { super(R.layout.comics_list_fragment); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        subscribeToData();
        mViewBinding = ComicsListFragmentBinding.bind(view);
        initRecyclerView();
        return view;
    }

    private void subscribeToData() {
        mViewModel.getComics().observe(
            getViewLifecycleOwner(),
            comics ->{
                mComicsListAdapter.setData(comics);
                mActivity.setProgressbar(false);
        });
        mViewModel.getExpandedItemPosition().observe(
                getViewLifecycleOwner(),
            expandedId -> {
                mComicsListAdapter.setExpandedItem(expandedId);
            }
        );
        mViewModel.getErrors().observe(
                getViewLifecycleOwner(),
                error -> {
                    showSnackbar(error.getMessage());
                    mActivity.setProgressbar(false);
                }
        );
    }

    private void initRecyclerView() {
        mComicsListAdapter = new ComicsListAdapter();
        ((SimpleItemAnimator)mViewBinding.recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mViewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mViewBinding.recyclerView.setAdapter(mComicsListAdapter);
        //This fixes problems with Picasso cache and reused viewHolders
        mViewBinding.recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 0);

        mComicsListAdapter.setOnItemClickedListener(
                (v, item, position) -> {
                    mViewModel.selectExpandedItem(position);
                });
        mComicsListAdapter.setOnGoToDetailClickedListener(
                (v, item, position) -> {
                    goToComicDetail(v, item);
                });
        mViewBinding.recyclerView.setOnScrollChangeListener(
            (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (!mViewBinding.recyclerView.canScrollVertically(1)) {
                    mViewModel.loadMoreComics();
                }
            });
    }

    private void goToComicDetail(View v, ComicEntity item){
        Navigation.findNavController(v).navigate(
                ComicsListFragmentDirections.actionComicsListFragmentToComicDetailFragment(item));
    }
}