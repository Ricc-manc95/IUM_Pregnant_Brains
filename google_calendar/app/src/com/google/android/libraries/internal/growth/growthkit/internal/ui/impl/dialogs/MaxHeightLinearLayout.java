// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class MaxHeightLinearLayout extends LinearLayout
{

    private int maxHeight;

    public MaxHeightLinearLayout(Context context)
    {
        super(context);
        maxHeight = -1;
    }

    public MaxHeightLinearLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        maxHeight = -1;
        maxHeight = context.obtainStyledAttributes(attributeset, R.styleable.MaxHeightLinearLayout).getDimensionPixelOffset(R.styleable.MaxHeightLinearLayout_maxHeight, -1);
    }

    public MaxHeightLinearLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        maxHeight = -1;
        maxHeight = context.obtainStyledAttributes(attributeset, R.styleable.MaxHeightLinearLayout).getDimensionPixelOffset(R.styleable.MaxHeightLinearLayout_maxHeight, -1);
    }

    public MaxHeightLinearLayout(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        maxHeight = -1;
        maxHeight = context.obtainStyledAttributes(attributeset, R.styleable.MaxHeightLinearLayout).getDimensionPixelOffset(R.styleable.MaxHeightLinearLayout_maxHeight, -1);
    }

    protected void onMeasure(int i, int j)
    {
        if (maxHeight != -1)
        {
            j = android.view.View.MeasureSpec.makeMeasureSpec(maxHeight, 0x80000000);
        }
        super.onMeasure(i, j);
    }

    public void setMaxHeight(int i)
    {
        maxHeight = i;
    }
}
