// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

// Referenced classes of package android.support.v7.preference:
//            PreferenceViewHolder, PreferenceFragmentCompat

final class mAllowDividerAfterLastItem extends android.support.v7.widget.ion
{

    public boolean mAllowDividerAfterLastItem;
    public Drawable mDivider;
    public int mDividerHeight;
    public final PreferenceFragmentCompat this$0;

    private final boolean shouldDrawDividerBelow(View view, RecyclerView recyclerview)
    {
        android.support.v7.widget.ion ion = recyclerview.getChildViewHolder(view);
        boolean flag;
        if ((ion instanceof PreferenceViewHolder) && ((PreferenceViewHolder)ion).mDividerAllowedBelow)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return false;
        }
        boolean flag1 = mAllowDividerAfterLastItem;
        int i = recyclerview.indexOfChild(view);
        if (i < recyclerview.getChildCount() - 1)
        {
            view = recyclerview.getChildViewHolder(recyclerview.getChildAt(i + 1));
            if ((view instanceof PreferenceViewHolder) && ((PreferenceViewHolder)view).mDividerAllowedAbove)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
        }
        return flag1;
    }

    public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerview, android.support.v7.widget.ion ion)
    {
        if (shouldDrawDividerBelow(view, recyclerview))
        {
            rect.bottom = mDividerHeight;
        }
    }

    public final void onDrawOver$51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H9N8OBKCKTIILG_0(Canvas canvas, RecyclerView recyclerview)
    {
        if (mDivider != null)
        {
            int j = recyclerview.getChildCount();
            int k = recyclerview.getWidth();
            int i = 0;
            while (i < j) 
            {
                View view = recyclerview.getChildAt(i);
                if (shouldDrawDividerBelow(view, recyclerview))
                {
                    int l = (int)view.getY();
                    l = view.getHeight() + l;
                    mDivider.setBounds(0, l, k, mDividerHeight + l);
                    mDivider.draw(canvas);
                }
                i++;
            }
        }
    }

    ()
    {
        this$0 = PreferenceFragmentCompat.this;
        super();
        mAllowDividerAfterLastItem = true;
    }
}
