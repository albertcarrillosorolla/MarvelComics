package com.acarrillo.touche.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acarrillo.touche.databinding.ComicListItemBinding;
import com.acarrillo.touche.domain.entities.ComicEntity;

import java.util.List;

public class ComicsListAdapter extends RecyclerView.Adapter<ComicsListViewHolder> {

    List<ComicEntity> mData;
    OnItemClickedListener<ComicEntity> mOnItemClickedListener;
    int mPositionExpanded = -1;

    @NonNull
    @Override
    public ComicsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ComicListItemBinding binding = ComicListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ComicsListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicsListViewHolder holder, int position) {
        holder.setData(mData.get(position), mPositionExpanded == position);
        holder.mBinding.comicItemContainer.setOnClickListener(v -> {
            mOnItemClickedListener.onClick(v, mData.get(position), position);
        });
    }

    @Override
    public int getItemCount() {
        return (mData==null) ? 0 : mData.size();
    }

    public void setData(List<ComicEntity> data)
    {
        mData = data;
        notifyDataSetChanged();
    }

    public void setExpandedItem(int position)
    {
        notifyItemChanged(mPositionExpanded);
        mPositionExpanded = position;
        notifyItemChanged(mPositionExpanded);
    }

    public void setOnItemClickedListener(OnItemClickedListener<ComicEntity> onItemClickedListener)
    {
        mOnItemClickedListener = onItemClickedListener;
    }

}