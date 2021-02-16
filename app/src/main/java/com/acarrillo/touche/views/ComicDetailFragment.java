package com.acarrillo.touche.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.transition.TransitionInflater;

import com.acarrillo.touche.R;
import com.acarrillo.touche.databinding.ComicDetailFragmentBinding;
import com.acarrillo.touche.modelviews.ComicDetailViewModel;
import com.acarrillo.touche.views.adapters.CharactersListAdapter;
import com.acarrillo.touche.views.base.BaseFragment;
import com.squareup.picasso.Picasso;

public class ComicDetailFragment extends BaseFragment<ComicDetailViewModel> {

    private ComicDetailFragmentBinding mViewBinding;
    private CharactersListAdapter mCharactersListAdapter;

    public ComicDetailFragment() { super(R.layout.comic_detail_fragment); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewBinding = ComicDetailFragmentBinding.bind(view);
        mViewModel.setComic(getArguments().getParcelable("comic"));
        subscribeToData();
        initRecyclerView();
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void subscribeToData() {
        mViewModel.getComic().observe(
                this,
                comic -> {
                    mViewBinding.title.setText(comic.getTitle());
                    mViewBinding.description.setText(comic.getDescription());
                    if(comic.getImage()!="")
                        Picasso.get().load(comic.getImage()).into(mViewBinding.headerImage);
                });
        mViewModel.getCharacters().observe(
                this,
                characters ->{
                    mCharactersListAdapter.setData(characters);
                }
        );
        mViewModel.getError().observe(
                this,
                error -> showSnackbar(error.getMessage())
        );
    }

    void initRecyclerView() {
        mCharactersListAdapter = new CharactersListAdapter();
        mViewBinding.characterRecyclerView.setLayoutManager(
                new GridLayoutManager(mActivity, 1, GridLayoutManager.HORIZONTAL, false));
        mViewBinding.characterRecyclerView.setAdapter(mCharactersListAdapter);
    }
}
