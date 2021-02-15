package com.acarrillo.touche.views.base;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.acarrillo.touche.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}