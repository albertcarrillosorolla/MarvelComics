package com.acarrillo.touche.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acarrillo.touche.R;
import com.acarrillo.touche.databinding.ComicsListFragmentBinding;
import com.acarrillo.touche.modelviews.ComicsListViewModel;

public class ComicsListFragment extends BaseFragment<ComicsListViewModel> {
    ComicsListFragmentBinding mViewBinding;

    public ComicsListFragment() { super(R.layout.comics_list_fragment); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewBinding = ComicsListFragmentBinding.bind(view);
        subscribeToData();
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
                               mViewBinding.tvTitle.setText(comics.get(0).getTitle());
                        }));
    }
}
