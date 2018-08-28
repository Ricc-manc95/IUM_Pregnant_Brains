// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

public class ButtonBarLayout extends LinearLayout
{

    private boolean mAllowStacking;
    private int mLastWidthSize;
    private int mMinimumHeight;

    public ButtonBarLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mLastWidthSize = -1;
        mMinimumHeight = 0;
        context = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ButtonBarLayout);
        mAllowStacking = context.getBoolean(android.support.v7.appcompat.R.styleable.ButtonBarLayout_allowStacking, true);
        context.recycle();
    }

    private final int getNextVisibleChildIndex(int i)
    {
        for (int j = getChildCount(); i < j; i++)
        {
            if (getChildAt(i).getVisibility() == 0)
            {
                return i;
            }
        }

        return -1;
    }

    private final void setStacked(boolean flag)
    {
        View view;
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        setOrientation(i);
        if (flag)
        {
            i = 5;
        } else
        {
            i = 80;
        }
        setGravity(i);
        view = findViewById(0x7f1000d6);
        if (view != null)
        {
            if (flag)
            {
                i = 8;
            } else
            {
                i = 4;
            }
            view.setVisibility(i);
        }
        for (i = getChildCount() - 2; i >= 0; i--)
        {
            bringChildToFront(getChildAt(i));
        }

    }

    public int getMinimumHeight()
    {
        return Math.max(0, super.getMinimumHeight());
    }

    protected void onMeasure(int i, int j)
    {
        int l = android.view.View.MeasureSpec.getSize(i);
        if (mAllowStacking)
        {
            if (l > mLastWidthSize && getOrientation() == 1)
            {
                setStacked(false);
            }
            mLastWidthSize = l;
        }
        int k;
        if (getOrientation() == 1)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k == 0 && android.view.View.MeasureSpec.getMode(i) == 0x40000000)
        {
            l = android.view.View.MeasureSpec.makeMeasureSpec(l, 0x80000000);
            k = 1;
        } else
        {
            l = i;
            k = 0;
        }
        super.onMeasure(l, j);
        l = k;
        if (mAllowStacking)
        {
            boolean flag;
            if (getOrientation() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            l = k;
            if (!flag)
            {
                View view;
                android.widget.LinearLayout.LayoutParams layoutparams;
                if ((getMeasuredWidthAndState() & 0xff000000) == 0x1000000)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                l = k;
                if (flag)
                {
                    setStacked(true);
                    l = 1;
                }
            }
        }
        if (l != 0)
        {
            super.onMeasure(i, j);
        }
        i = getNextVisibleChildIndex(0);
        if (i >= 0)
        {
            view = getChildAt(i);
            layoutparams = (android.widget.LinearLayout.LayoutParams)view.getLayoutParams();
            j = getPaddingTop();
            k = view.getMeasuredHeight();
            l = layoutparams.topMargin;
            j = layoutparams.bottomMargin + (k + j + l) + 0;
            if (getOrientation() == 1)
            {
                k = getNextVisibleChildIndex(i + 1);
                i = j;
                if (k >= 0)
                {
                    i = j + (getChildAt(k).getPaddingTop() + (int)(16F * getResources().getDisplayMetrics().density));
                }
            } else
            {
                i = j + getPaddingBottom();
            }
        } else
        {
            i = 0;
        }
        if (ViewCompat.getMinimumHeight(this) != i)
        {
            setMinimumHeight(i);
        }
    }
}
