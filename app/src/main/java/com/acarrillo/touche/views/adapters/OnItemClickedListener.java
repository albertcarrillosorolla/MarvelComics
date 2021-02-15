package com.acarrillo.touche.views.adapters;

import android.view.View;

public interface OnItemClickedListener<T> {
    void onClick(View v, T item, int position);
}
