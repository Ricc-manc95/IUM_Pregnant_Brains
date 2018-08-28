// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import com.google.android.calendar.utils.animation.QuantumInterpolators;

public abstract class BottomSheet extends RelativeLayout
{

    public View fadeLayer;
    public Context mContext;
    private Handler mHandler;
    private Resources mResources;
    public Runnable mRunnable;
    public View mainLayer;

    public BottomSheet(Context context, AttributeSet attributeset, int i)
    {
        super(context, null, 0);
        mContext = context;
        mResources = mContext.getResources();
    }

    private final void findLayers()
    {
        mainLayer = findViewById(0x7f10000c);
        if (mainLayer == null)
        {
            mainLayer = this;
            return;
        } else
        {
            fadeLayer = findViewById(0x7f10000b);
            return;
        }
    }

    public final void hide(final boolean triggerAction, final boolean positiveClicked)
    {
        findLayers();
        Animation animation1 = AnimationUtils.loadAnimation(mContext, 0x7f06000e);
        animation1.setInterpolator(QuantumInterpolators.FAST_OUT_LINEAR_IN);
        Animation animation;
        int i;
        if (fadeLayer == null)
        {
            animation = null;
        } else
        {
            animation = AnimationUtils.loadAnimation(mContext, 0x7f06000d);
        }
        i = mContext.getResources().getInteger(0x7f11000a);
        (new Handler()).postDelayed(new _cls2(), i + 30);
        onHide();
        mainLayer.startAnimation(animation1);
        if (animation != null)
        {
            fadeLayer.startAnimation(animation);
        }
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (mHandler != null && mRunnable != null)
        {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    protected void onHide()
    {
    }

    public abstract void onNegativeButtonClicked();

    public abstract void onPositiveButtonClicked();

    public void onShow()
    {
    }

    public void show()
    {
        showDelayed(-1);
    }

    protected final void showDelayed(int i)
    {
        findLayers();
        final Animation bottomUp = AnimationUtils.loadAnimation(mContext, 0x7f06000f);
        bottomUp.setInterpolator(QuantumInterpolators.LINEAR_OUT_SLOW_IN);
        final Animation fadeIn;
        if (fadeLayer == null)
        {
            fadeIn = null;
        } else
        {
            fadeIn = AnimationUtils.loadAnimation(mContext, 0x7f06000c);
        }
        mHandler = new Handler();
        mRunnable = new _cls1();
        if (i == -1)
        {
            i = 0;
        } else
        {
            i = mResources.getInteger(i);
        }
        mHandler.postDelayed(mRunnable, i);
    }

    public final void tweakLayout()
    {
        int i = mResources.getDimensionPixelSize(0x7f0e0075);
        android.widget.FrameLayout.LayoutParams layoutparams;
        if (i == 0)
        {
            i = -1;
        }
        layoutparams = new android.widget.FrameLayout.LayoutParams(i, -2);
        layoutparams.gravity = 0x800055;
        layoutparams.bottomMargin = mResources.getDimensionPixelSize(0x7f0e006f);
        layoutparams.rightMargin = mResources.getDimensionPixelSize(0x7f0e0070);
        setLayoutParams(layoutparams);
        setClickable(true);
        setVisibility(8);
        setBackgroundResource(0x7f020074);
        setPadding(0, mResources.getDimensionPixelSize(0x7f0e0073), 0, mResources.getDimensionPixelSize(0x7f0e0071));
        ViewCompat.setElevation(this, mResources.getDimensionPixelSize(0x7f0e006e));
    }

    private class _cls2
        implements Runnable
    {

        private final BottomSheet this$0;
        private final boolean val$positiveClicked;
        private final boolean val$triggerAction;

        public final void run()
        {
            setVisibility(8);
            if (triggerAction)
            {
                if (positiveClicked)
                {
                    onPositiveButtonClicked();
                } else
                {
                    onNegativeButtonClicked();
                }
            }
            mainLayer.clearAnimation();
            if (fadeLayer != null)
            {
                fadeLayer.clearAnimation();
            }
        }

        _cls2()
        {
            this$0 = BottomSheet.this;
            triggerAction = flag;
            positiveClicked = flag1;
            super();
        }
    }


    private class _cls1
        implements Runnable
    {

        private final BottomSheet this$0;
        private final Animation val$bottomUp;
        private final Animation val$fadeIn;

        public final void run()
        {
            onShow();
            setVisibility(0);
            mainLayer.startAnimation(bottomUp);
            if (fadeIn != null)
            {
                fadeLayer.startAnimation(fadeIn);
            }
            mRunnable = null;
        }

        _cls1()
        {
            this$0 = BottomSheet.this;
            bottomUp = animation;
            fadeIn = animation1;
            super();
        }
    }

}
