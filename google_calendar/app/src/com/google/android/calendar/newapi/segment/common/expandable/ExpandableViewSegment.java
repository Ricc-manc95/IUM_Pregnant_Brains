// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common.expandable;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;

public abstract class ExpandableViewSegment extends LinearLayout
    implements android.view.View.OnClickListener, ViewSegment
{

    private final ImageView arrowIconImageView = (ImageView)findViewById(0x7f10027c);
    public final View contentView;
    public boolean expanded;
    private final ViewGroup headerViewGroup = (ViewGroup)findViewById(0x7f10027a);
    public final TextTileView titleView = (TextTileView)findViewById(0x7f10027b);

    public ExpandableViewSegment(Context context, int i)
    {
        super(context);
        expanded = false;
        inflate(context, 0x7f0500d4, this);
        setOrientation(1);
        context = LayoutInflater.from(getContext()).inflate(i, this, false);
        addView(context);
        contentView = context;
        contentView.getLayoutParams().height = 0;
        contentView.setVisibility(0);
        ViewCompat.setAccessibilityDelegate(headerViewGroup, new _cls1());
        context = arrowIconImageView;
        if (expanded)
        {
            i = 0x7f0201f5;
        } else
        {
            i = 0x7f0201f6;
        }
        context.setImageResource(i);
        headerViewGroup.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        int i;
        boolean flag;
        if (!expanded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        expanded = flag;
        view = arrowIconImageView;
        if (expanded)
        {
            i = 0x7f0201f5;
        } else
        {
            i = 0x7f0201f6;
        }
        view.setImageResource(i);
        view = new ExpandAnimation(expanded);
        view.setDuration(150L);
        contentView.startAnimation(view);
    }

    private class _cls1 extends AccessibilityDelegateCompat
    {

        private final ExpandableViewSegment this$0;

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            if (expanded)
            {
                view = android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE;
            } else
            {
                view = android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND;
            }
            accessibilitynodeinfocompat.mInfo.addAction((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat) (view)).mAction);
        }

        _cls1()
        {
            this$0 = ExpandableViewSegment.this;
            super();
        }
    }


    private class ExpandAnimation extends Animation
    {

        private final boolean expand;
        private final int startHeight;
        private final int targetHeight;
        private final ExpandableViewSegment this$0;

        protected final void applyTransformation(float f, Transformation transformation)
        {
            transformation = contentView.getLayoutParams();
            if (f == 1.0F)
            {
                int i;
                if (expand)
                {
                    i = -2;
                } else
                {
                    i = 0;
                }
                transformation.height = i;
            } else
            {
                int j = targetHeight;
                int k = startHeight;
                int l = startHeight;
                transformation.height = (int)((float)(j - k) * f) + l;
            }
            contentView.setLayoutParams(transformation);
        }

        public final boolean willChangeBounds()
        {
            return true;
        }

        ExpandAnimation(boolean flag)
        {
            this$0 = ExpandableViewSegment.this;
            super();
            expand = flag;
            if (flag)
            {
                contentView.measure(android.view.View.MeasureSpec.makeMeasureSpec(getWidth(), 0x40000000), 0);
                startHeight = contentView.getHeight();
                targetHeight = contentView.getMeasuredHeight();
                return;
            } else
            {
                targetHeight = 0;
                startHeight = contentView.getHeight();
                return;
            }
        }
    }

}
