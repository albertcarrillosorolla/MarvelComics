package com.acarrillo.touche.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mViewBinding = ComicDetailFragmentBinding.bind(view);
        mViewModel.setComic(getArguments().getParcelable("comic"));
        subscribeToData();
        initRecyclerView();
        return view;
    }

    private void subscribeToData() {
        mViewModel.getComic().observe(
                getViewLifecycleOwner(),
                comic -> {
                    mViewBinding.title.setText(comic.getTitle());
                    mViewBinding.description.setText(comic.getDescription());
                    if(comic.getImage()!="")
                        Picasso.get().load(comic.getImage()).into(mViewBinding.headerImage);
                });
        mViewModel.getCharacters().observe(
                getViewLifecycleOwner(),
                characters ->{
                    if(characters.size()==0) showNoCharacters();
                    mCharactersListAdapter.setData(characters);
                }
        );
        mViewModel.getError().observe(
                getViewLifecycleOwner(),
                error -> showSnackbar(error.getMessage())
        );
    }

    void initRecyclerView() {
        mCharactersListAdapter = new CharactersListAdapter();
        mViewBinding.characterRecyclerView.setLayoutManager(
                new GridLayoutManager(mActivity, 1, GridLayoutManager.HORIZONTAL, false));
        mViewBinding.characterRecyclerView.setAdapter(mCharactersListAdapter);
    }

    void showNoCharacters(){
        mViewBinding.characterRecyclerView.setVisibility(View.INVISIBLE);
        mViewBinding.noCharactersTv.setVisibility(View.VISIBLE);
    }
}
