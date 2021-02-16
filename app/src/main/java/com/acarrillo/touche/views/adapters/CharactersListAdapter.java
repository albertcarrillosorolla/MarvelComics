package com.acarrillo.touche.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acarrillo.touche.databinding.ComicDetailCharacterItemBinding;
import com.acarrillo.touche.domain.entities.CharacterEntity;

import java.util.List;

public class CharactersListAdapter extends RecyclerView.Adapter<CharactersListViewHolder> {

    List<CharacterEntity> mData;

    @NonNull
    @Override
    public CharactersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ComicDetailCharacterItemBinding binding = ComicDetailCharacterItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CharactersListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharactersListViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return (mData==null) ? 0 : mData.size();
    }

    public void setData(List<CharacterEntity> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
