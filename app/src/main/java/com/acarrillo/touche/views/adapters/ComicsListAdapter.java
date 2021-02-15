package com.acarrillo.touche.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acarrillo.touche.R;
import com.acarrillo.touche.databinding.ComicListItemBinding;
import com.acarrillo.touche.domain.models.ComicModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComicsListAdapter extends RecyclerView.Adapter<ComicsListAdapter.ComicsListViewHolder> {

    List<ComicModel> mData;
    OnItemClickedListener<ComicModel> mOnItemClickedListener;
    int mIdExpanded = -1;

    @NonNull
    @Override
    public ComicsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ComicListItemBinding binding = ComicListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ComicsListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicsListViewHolder holder, int position) {
        holder.mBinding.comicItemTitle.setText(mData.get(position).getTitle());
        holder.setExpandedView(mIdExpanded == mData.get(position).getId());
        if(mData.get(position).getNumImages()!=0){
            Picasso.get()
                    .load(mData.get(position).getImage(0))
                    .into(holder.mBinding.comicItemImage);
        }else{
            holder.mBinding.comicItemImage.setVisibility(View.GONE);
        }
        holder.mBinding.comicItemContainer.setOnClickListener(v -> {
            mOnItemClickedListener.onClick(v, mData.get(position), position);
        });
    }

    @Override
    public int getItemCount() {
        return (mData==null) ? 0 : mData.size();
    }

    public void setData(List<ComicModel> data)
    {
        mData = data;
        notifyDataSetChanged();
    }

    public void setExpandedItem(int id)
    {
        mIdExpanded = id;
        notifyDataSetChanged();
    }

    public void setOnItemClickedListener(OnItemClickedListener<ComicModel> onItemClickedListener)
    {
        mOnItemClickedListener = onItemClickedListener;
    }

    public class ComicsListViewHolder extends RecyclerView.ViewHolder {
        ComicListItemBinding mBinding;
        public ComicsListViewHolder(ComicListItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
        public void setExpandedView(boolean expanded)
        {
            mBinding.comicItemDetails.setVisibility( expanded ? View.VISIBLE : View.GONE );
            int height = 0;
            if(expanded) height = mBinding.getRoot().getContext().getResources().getDimensionPixelSize(R.dimen.list_item_expanded_height);
            else height = mBinding.getRoot().getContext().getResources().getDimensionPixelSize(R.dimen.list_items_height);
            mBinding.comicItemContainer.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        }
    }
}