package com.acarrillo.touche.views.adapters;

import androidx.recyclerview.widget.RecyclerView;

import com.acarrillo.touche.databinding.ComicDetailCharacterItemBinding;
import com.acarrillo.touche.domain.entities.CharacterEntity;
import com.squareup.picasso.Picasso;

public class CharactersListViewHolder extends RecyclerView.ViewHolder {

    ComicDetailCharacterItemBinding mBinding;

    public CharactersListViewHolder(ComicDetailCharacterItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void setData(CharacterEntity characterEntity) {
        if(characterEntity.getImage()!="")
            Picasso.get().load(characterEntity.getImage()).into(mBinding.characterImage);
    }
}
