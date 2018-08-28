// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

public class PreferenceImageView extends ImageView
{

    private int mMaxHeight;
    private int mMaxWidth;

    public PreferenceImageView(Context context)
    {
        this(context, null);
    }

    public PreferenceImageView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public PreferenceImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mMaxWidth = 0x7fffffff;
        mMaxHeight = 0x7fffffff;
        context = context.obtainStyledAttributes(attributeset, android.support.v7.preference.R.styleable.PreferenceImageView, i, 0);
        setMaxWidth(context.getDimensionPixelSize(android.support.v7.preference.R.styleable.PreferenceImageView_maxWidth, 0x7fffffff));
        setMaxHeight(context.getDimensionPixelSize(android.support.v7.preference.R.styleable.PreferenceImageView_maxHeight, 0x7fffffff));
        context.recycle();
    }

    public int getMaxHeight()
    {
        return mMaxHeight;
    }

    public int getMaxWidth()
    {
        return mMaxWidth;
    }

    protected void onMeasure(int i, int j)
    {
        int k;
label0:
        {
            int l = android.view.View.MeasureSpec.getMode(i);
            if (l != 0x80000000)
            {
                k = i;
                if (l != 0)
                {
                    break label0;
                }
            }
            int j1 = android.view.View.MeasureSpec.getSize(i);
            int l1 = getMaxWidth();
            k = i;
            if (l1 == 0x7fffffff)
            {
                break label0;
            }
            if (l1 >= j1)
            {
                k = i;
                if (l != 0)
                {
                    break label0;
                }
            }
            k = android.view.View.MeasureSpec.makeMeasureSpec(l1, 0x80000000);
        }
label1:
        {
            int i1 = android.view.View.MeasureSpec.getMode(j);
            if (i1 != 0x80000000)
            {
                i = j;
                if (i1 != 0)
                {
                    break label1;
                }
            }
            int k1 = android.view.View.MeasureSpec.getSize(j);
            int i2 = getMaxHeight();
            i = j;
            if (i2 == 0x7fffffff)
            {
                break label1;
            }
            if (i2 >= k1)
            {
                i = j;
                if (i1 != 0)
                {
                    break label1;
                }
            }
            i = android.view.View.MeasureSpec.makeMeasureSpec(i2, 0x80000000);
        }
        super.onMeasure(k, i);
    }

    public void setMaxHeight(int i)
    {
        mMaxHeight = i;
        super.setMaxHeight(i);
    }

    public void setMaxWidth(int i)
    {
        mMaxWidth = i;
        super.setMaxWidth(i);
    }
}
