package com.acarrillo.touche.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acarrillo.touche.R;
import com.acarrillo.touche.databinding.ComicDetailFragmentBinding;
import com.acarrillo.touche.modelviews.ComicDetailViewModel;
import com.acarrillo.touche.views.base.BaseFragment;
import com.squareup.picasso.Picasso;

public class ComicDetailFragment extends BaseFragment<ComicDetailViewModel> {

    private ComicDetailFragmentBinding mViewBinding;

    public ComicDetailFragment() { super(R.layout.comic_detail_fragment); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewBinding = ComicDetailFragmentBinding.bind(view);
        mViewModel.setComic(getArguments().getParcelable("comic"));
        subscribeToData();
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void subscribeToData() {
        mViewModel.getComic().observe(
                this,
                comic -> {
                    mViewBinding.title.setText(comic.getTitle());
                    mViewBinding.description.setText(comic.getDescription());
                    Picasso.get().load(comic.getImage()).into(mViewBinding.headerImage);
                });
    }
}
