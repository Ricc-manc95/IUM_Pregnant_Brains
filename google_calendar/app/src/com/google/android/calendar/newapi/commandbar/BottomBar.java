// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.calendar.material.Material;

public abstract class BottomBar extends LinearLayout
    implements android.view.View.OnClickListener
{

    public static boolean animationsEnabled = true;
    private int checkedResponseColor;
    public ViewGroup container;
    public TextView descriptionView;
    public OnCommandBarActionClickListener listener;
    public OnHeightChanged onHeightChangedListener;
    public int primaryActionIds[];
    private int responseColor;

    public BottomBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public ValueAnimator createEnterAnimation()
    {
        if (getHeight() == 0)
        {
            measure(0, 0);
        }
        int i = getMeasuredHeight();
        ((View)getParent()).setTranslationY(i - 0);
        onHeightChangedListener.onHeightChanged();
        ValueAnimator valueanimator = ValueAnimator.ofInt(new int[] {
            0, getMeasuredHeight()
        });
        valueanimator.addUpdateListener(new AnimationListener());
        return valueanimator;
    }

    protected abstract int getActionContainerResId();

    protected abstract int getContainerResId();

    public void initialize(int i, int ai[])
    {
        if (i != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        primaryActionIds = ai;
        responseColor = getResources().getColor(0x7f0d00b2);
        checkedResponseColor = getResources().getColor(0x7f0d00b0);
        container = (ViewGroup)findViewById(getContainerResId());
        descriptionView = (TextView)container.findViewById(0x7f100255);
        ViewGroup viewgroup = (ViewGroup)findViewById(getActionContainerResId());
        inflate(getContext(), i, viewgroup);
        int ai1[] = primaryActionIds;
        int j = ai1.length;
        i = 0;
        while (i < j) 
        {
            Button button = (Button)viewgroup.findViewById(ai1[i]);
            if (Material.robotoMedium != null)
            {
                ai = Material.robotoMedium;
            } else
            {
                ai = Typeface.create("sans-serif-medium", 0);
                Material.robotoMedium = ai;
            }
            button.setTypeface(ai);
            button.setOnClickListener(this);
            i++;
        }
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            int k = descriptionView.getId();
            ai = primaryActionIds;
            int i1 = ai.length;
            i = 0;
            while (i < i1) 
            {
                int l = ai[i];
                container.findViewById(l).setAccessibilityTraversalAfter(k);
                i++;
                k = l;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected final void initializeButton(View view)
    {
        Button button = (Button)view;
        if (Material.robotoMedium != null)
        {
            view = Material.robotoMedium;
        } else
        {
            view = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = view;
        }
        button.setTypeface(view);
        button.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        if (listener != null)
        {
            listener.onCommandBarActionClick(view.getId());
        }
    }

    public void setAccessibilityTraversalAfter(int i)
    {
        descriptionView.setAccessibilityTraversalAfter(i);
    }

    public final void setActionSelectionState(int i, boolean flag)
    {
        Button button = (Button)findViewById(i);
        if (flag)
        {
            i = checkedResponseColor;
        } else
        {
            i = responseColor;
        }
        button.setTextColor(i);
    }

    public final void setDescription(CharSequence charsequence)
    {
        boolean flag = false;
        descriptionView.setText(charsequence);
        TextView textview = descriptionView;
        int i;
        if (!TextUtils.isEmpty(charsequence))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (textview != null)
        {
            if (i != 0)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 8;
            }
            textview.setVisibility(i);
        }
    }


    private class OnHeightChanged
    {

        public abstract void onHeightChanged();
    }


    private class AnimationListener
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        private final BottomBar this$0;

        public final void onAnimationUpdate(ValueAnimator valueanimator)
        {
            BottomBar bottombar = BottomBar.this;
            int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
            int j = bottombar.getMeasuredHeight();
            ((View)bottombar.getParent()).setTranslationY(j - i);
            bottombar.onHeightChangedListener.onHeightChanged();
        }

        protected AnimationListener()
        {
            this$0 = BottomBar.this;
            super();
        }
    }


    private class OnCommandBarActionClickListener
    {

        public abstract void onCommandBarActionClick(int i);
    }

}
