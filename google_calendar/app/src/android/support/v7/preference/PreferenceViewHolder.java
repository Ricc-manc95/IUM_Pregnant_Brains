// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.util.SparseArray;
import android.view.View;

public final class PreferenceViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
{

    private final SparseArray mCachedViews = new SparseArray(4);
    public boolean mDividerAllowedAbove;
    public boolean mDividerAllowedBelow;

    PreferenceViewHolder(View view)
    {
        super(view);
        mCachedViews.put(0x1020016, view.findViewById(0x1020016));
        mCachedViews.put(0x1020010, view.findViewById(0x1020010));
        mCachedViews.put(0x1020006, view.findViewById(0x1020006));
        mCachedViews.put(0x7f10018f, view.findViewById(0x7f10018f));
        mCachedViews.put(0x102003e, view.findViewById(0x102003e));
    }

    public final View findViewById(int i)
    {
        View view = (View)mCachedViews.get(i);
        if (view == null)
        {
            View view1 = itemView.findViewById(i);
            view = view1;
            if (view1 != null)
            {
                mCachedViews.put(i, view1);
                return view1;
            }
        }
        return view;
    }
}
