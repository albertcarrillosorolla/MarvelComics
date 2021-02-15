package com.acarrillo.touche.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acarrillo.touche.R;
import com.acarrillo.touche.databinding.ComicListItemBinding;
import com.acarrillo.touche.domain.models.ComicModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ComicsListAdapter extends RecyclerView.Adapter<ComicsListAdapter.ComicsListViewHolder> {

    List<ComicModel> mData;

    @NonNull
    @Override
    public ComicsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ComicListItemBinding binding = ComicListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ComicsListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicsListViewHolder holder, int position) {
        holder.mBinding.comicItemTitle.setText(mData.get(position).getTitle());
        if(mData.get(position).getImage(0)!="") {
            Picasso.get()
                    .load(mData.get(position).getImage(0))
                    .into(holder.mBinding.comicItemImage);
        }
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

    public class ComicsListViewHolder extends RecyclerView.ViewHolder {
        ComicListItemBinding mBinding;
        public ComicsListViewHolder(ComicListItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
