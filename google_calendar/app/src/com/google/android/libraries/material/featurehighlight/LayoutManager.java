// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightView

final class LayoutManager
{

    public int contentMaxWidth;
    private final int horizontalTextOffset;
    public final Rect innerBounds = new Rect();
    public boolean pinToClosestVerticalEdge;
    public final Rect textBounds = new Rect();
    public int textVerticalGravityHint;
    public final int verticalTextOffset;
    public final FeatureHighlightView view;

    LayoutManager(FeatureHighlightView featurehighlightview)
    {
        pinToClosestVerticalEdge = false;
        textVerticalGravityHint = 0;
        if (featurehighlightview == null)
        {
            throw new NullPointerException();
        } else
        {
            view = (FeatureHighlightView)featurehighlightview;
            featurehighlightview = featurehighlightview.getResources();
            contentMaxWidth = featurehighlightview.getDimensionPixelSize(0x7f0e0290);
            horizontalTextOffset = featurehighlightview.getDimensionPixelSize(0x7f0e028f);
            verticalTextOffset = featurehighlightview.getDimensionPixelSize(0x7f0e0291);
            return;
        }
    }

    final int getContentLeft(View view1, int i, int j, int k, int l)
    {
        view1 = (android.view.ViewGroup.MarginLayoutParams)view1.getLayoutParams();
        int i1 = k / 2;
        boolean flag;
        if (l - i <= j - l)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            l = (l - i1) + horizontalTextOffset;
        } else
        {
            l = l - i1 - horizontalTextOffset;
        }
        if (l - ((android.view.ViewGroup.MarginLayoutParams) (view1)).leftMargin < i)
        {
            i += ((android.view.ViewGroup.MarginLayoutParams) (view1)).leftMargin;
        } else
        {
            i = l;
            if (l + k + ((android.view.ViewGroup.MarginLayoutParams) (view1)).rightMargin > j)
            {
                return j - k - ((android.view.ViewGroup.MarginLayoutParams) (view1)).rightMargin;
            }
        }
        return i;
    }

    final void measureContent(View view1, int i, int j)
    {
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view1.getLayoutParams();
        view1.measure(android.view.View.MeasureSpec.makeMeasureSpec(Math.min(i - marginlayoutparams.leftMargin - marginlayoutparams.rightMargin, contentMaxWidth), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j, 0x80000000));
    }
}
