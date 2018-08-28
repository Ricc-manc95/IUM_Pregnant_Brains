// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class BaselineLayout extends ViewGroup
{

    private int baseline;

    public BaselineLayout(Context context)
    {
        super(context, null, 0);
        baseline = -1;
    }

    public BaselineLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset, 0);
        baseline = -1;
    }

    public BaselineLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        baseline = -1;
    }

    public int getBaseline()
    {
        return baseline;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int j1 = getChildCount();
        int k1 = getPaddingLeft();
        int l1 = getPaddingRight();
        int i1 = getPaddingTop();
        j = 0;
        while (j < j1) 
        {
            View view = getChildAt(j);
            if (view.getVisibility() != 8)
            {
                int i2 = view.getMeasuredWidth();
                int j2 = view.getMeasuredHeight();
                int k2 = k1 + (k - i - l1 - k1 - i2) / 2;
                if (baseline != -1 && view.getBaseline() != -1)
                {
                    l = (baseline + i1) - view.getBaseline();
                } else
                {
                    l = i1;
                }
                view.layout(k2, l, i2 + k2, j2 + l);
            }
            j++;
        }
    }

    protected void onMeasure(int i, int j)
    {
        int i3 = getChildCount();
        int k2 = 0;
        int j1 = 0;
        int l = -1;
        int i1 = 0;
        int l2 = 0;
        int k = -1;
        while (k2 < i3) 
        {
            View view = getChildAt(k2);
            int l1;
            if (view.getVisibility() != 8)
            {
                measureChild(view, i, j);
                int j3 = view.getBaseline();
                int k1 = k;
                int j2 = l;
                if (j3 != -1)
                {
                    j2 = Math.max(l, j3);
                    k1 = Math.max(k, view.getMeasuredHeight() - j3);
                }
                l2 = Math.max(l2, view.getMeasuredWidth());
                i1 = Math.max(i1, view.getMeasuredHeight());
                k = View.combineMeasuredStates(j1, view.getMeasuredState());
                l = j2;
                j1 = k1;
            } else
            {
                int i2 = k;
                k = j1;
                j1 = i2;
            }
            k2++;
            l1 = k;
            k = j1;
            j1 = l1;
        }
        l1 = i1;
        if (l != -1)
        {
            l1 = Math.max(i1, Math.max(k, getPaddingBottom()) + l);
            baseline = l;
        }
        k = Math.max(l1, getSuggestedMinimumHeight());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(l2, getSuggestedMinimumWidth()), i, j1), View.resolveSizeAndState(k, j, j1 << 16));
    }
}
