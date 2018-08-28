// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.widgets.spinner;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.calendar.utils.animation.Properties;
import com.google.common.collect.ImmutableList;

public class LabeledSpinner extends LinearLayout
{

    public int currentLabel;
    public final Animator fadeInAnimator;
    public final Animator fadeOutAnimator;
    public ImmutableList labels;
    public final TextView loadingTextView;

    public LabeledSpinner(Context context)
    {
        this(context, null);
    }

    public LabeledSpinner(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setGravity(17);
        setOrientation(1);
        LayoutInflater.from(context).inflate(0x7f050167, this);
        loadingTextView = (TextView)findViewById(0x7f10036b);
        fadeOutAnimator = ObjectAnimator.ofFloat(loadingTextView, Properties.VIEW_ALPHA, new float[] {
            1.0F, 0.0F
        });
        fadeOutAnimator.setDuration(150L);
        fadeOutAnimator.setStartDelay(1600L);
        fadeOutAnimator.addListener(new _cls1());
        fadeInAnimator = ObjectAnimator.ofFloat(loadingTextView, Properties.VIEW_ALPHA, new float[] {
            0.0F, 1.0F
        });
        fadeInAnimator.setDuration(150L);
        fadeInAnimator.addListener(new _cls2());
    }

    private final void stopAnimations()
    {
        if (fadeInAnimator.isStarted())
        {
            fadeInAnimator.cancel();
        }
        if (fadeOutAnimator.isStarted())
        {
            fadeOutAnimator.cancel();
        }
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        stopAnimations();
    }

    protected void onVisibilityChanged(View view, int i)
    {
        super.onVisibilityChanged(view, i);
        if (fadeInAnimator != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (i != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        setAlpha(0.0F);
        animate().alpha(1.0F).setDuration(500L).start();
        currentLabel = 0;
        loadingTextView.setText((CharSequence)labels.get(currentLabel));
        if (currentLabel >= labels.size() - 1) goto _L1; else goto _L3
_L3:
        fadeOutAnimator.start();
        return;
        if (i != 8) goto _L1; else goto _L4
_L4:
        stopAnimations();
        return;
    }

    public final void setLabels(ImmutableList immutablelist)
    {
        boolean flag1 = true;
        boolean flag;
        if (!immutablelist.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (getVisibility() == 8)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Cannot change labels while spinner is showing"));
        } else
        {
            labels = immutablelist;
            return;
        }
    }

    public void setSpinnerColor(int i)
    {
        ((ProgressBar)findViewById(0x7f10011d)).setIndeterminateTintList(ColorStateList.valueOf(i));
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final LabeledSpinner this$0;

        public final void onAnimationEnd(Animator animator)
        {
            animator = LabeledSpinner.this;
            animator.currentLabel = ((LabeledSpinner) (animator)).currentLabel + 1;
            loadingTextView.setText((CharSequence)labels.get(currentLabel));
            fadeInAnimator.start();
        }

        _cls1()
        {
            this$0 = LabeledSpinner.this;
            super();
        }
    }


    private class _cls2 extends AnimatorListenerAdapter
    {

        private final LabeledSpinner this$0;

        public final void onAnimationEnd(Animator animator)
        {
            animator = LabeledSpinner.this;
            if (((LabeledSpinner) (animator)).currentLabel < ((LabeledSpinner) (animator)).labels.size() - 1)
            {
                ((LabeledSpinner) (animator)).fadeOutAnimator.start();
            }
        }

        _cls2()
        {
            this$0 = LabeledSpinner.this;
            super();
        }
    }

}
