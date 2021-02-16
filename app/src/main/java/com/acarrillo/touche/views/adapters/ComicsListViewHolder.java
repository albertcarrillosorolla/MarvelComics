package com.acarrillo.touche.views.adapters;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.acarrillo.touche.R;
import com.acarrillo.touche.databinding.ComicListItemBinding;
import com.acarrillo.touche.domain.entities.ComicEntity;
import com.acarrillo.touche.views.animators.ViewHeightAnimator;
import com.squareup.picasso.Picasso;

public class ComicsListViewHolder extends RecyclerView.ViewHolder {

    ComicListItemBinding mBinding;
    boolean mWasExpanded;

    public ComicsListViewHolder(ComicListItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void setData(ComicEntity comic, boolean expanded)
    {
        mBinding.comicItemTitle.setText(comic.getTitle());
        if(comic.getImage()!=""){
            Picasso.get()
                    .load(comic.getImage())
                    .into(mBinding.comicItemImage);
        }else{
            mBinding.comicItemImage.setVisibility(View.GONE);
        }
        mBinding.comicItemImage.setTransitionName(comic.getId()+"");
        setExpanded(expanded);
    }

    public void setExpanded(boolean expanded)
    {
        if(expanded && !mWasExpanded) {
            expand();
        }
        if(!expanded && mWasExpanded) {
            collapse();
        }
        mWasExpanded = expanded;
    }

    public void expand()
    {
        int smallSize = mBinding.getRoot().getContext().getResources().getDimensionPixelSize(R.dimen.list_items_height);
        int bigSize = mBinding.getRoot().getContext().getResources().getDimensionPixelSize(R.dimen.list_item_expanded_height);
        mBinding.comicItemDetails.setVisibility( View.VISIBLE );
        ViewHeightAnimator.animate(mBinding.comicItemContainer, smallSize, bigSize, 300);
    }

    public void collapse()
    {
        int smallSize = mBinding.getRoot().getContext().getResources().getDimensionPixelSize(R.dimen.list_items_height);
        int bigSize = mBinding.getRoot().getContext().getResources().getDimensionPixelSize(R.dimen.list_item_expanded_height);
        mBinding.comicItemDetails.setVisibility( View.GONE );
        ViewHeightAnimator.animate(mBinding.comicItemContainer, bigSize, smallSize, 300);
    }

}
