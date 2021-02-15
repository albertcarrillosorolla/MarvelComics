package com.acarrillo.touche.views;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.ParameterizedType;

public class BaseFragment<VM extends ViewModel> extends Fragment {

    protected MainActivity mActivity;
    protected VM mViewModel;
    protected View mView;

    public BaseFragment(@LayoutRes int view){ super(view); }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mViewModel = getViewModel();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    public void showSnackbar(String message)
    {
        Snackbar.make(mView, message, Snackbar.LENGTH_SHORT).show();
    }

    private VM getViewModel() {
        Class<VM> vmClass = (Class<VM>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return new ViewModelProvider(this).get(vmClass);
    }
}

