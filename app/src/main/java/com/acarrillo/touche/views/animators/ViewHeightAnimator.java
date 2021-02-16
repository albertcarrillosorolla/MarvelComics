package com.acarrillo.touche.views.animators;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

public class ViewHeightAnimator {
    public static void animate(View v, int current, int target, int time)
    {
        ValueAnimator anim = ValueAnimator.ofInt(current, target);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
            layoutParams.height = val;
            v.setLayoutParams(layoutParams);
        });
        anim.setDuration(time);
        anim.start();
    }
}
