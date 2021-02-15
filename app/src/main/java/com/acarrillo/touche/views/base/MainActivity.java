package com.acarrillo.touche.views.base;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.acarrillo.touche.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setProgressbar(boolean active)
    {
        findViewById(R.id.progressBar).setVisibility(active? View.VISIBLE: View.GONE);
        findViewById(R.id.nav_host_fragment).setVisibility(!active? View.VISIBLE: View.GONE);
    }

}