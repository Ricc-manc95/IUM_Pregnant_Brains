// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ScrollViewWithSizeCallback extends ScrollView
{
    static interface OnHeightChangedListener
    {

        public abstract void onHeightChanged(int i);
    }


    public OnHeightChangedListener onHeightChangedListener;

    public ScrollViewWithSizeCallback(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if (onHeightChangedListener != null && l != j)
        {
            onHeightChangedListener.onHeightChanged(j);
        }
    }
}
