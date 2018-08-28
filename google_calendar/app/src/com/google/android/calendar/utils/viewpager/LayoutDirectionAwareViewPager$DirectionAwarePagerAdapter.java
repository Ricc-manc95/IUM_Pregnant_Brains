// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.viewpager;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package com.google.android.calendar.utils.viewpager:
//            LayoutDirectionAwareViewPager

static final class directionUnawareDataSetObserver extends PagerAdapter
{

    public final DataSetObserver directionUnawareDataSetObserver = new _cls1();
    public final PagerAdapter directionUnawarePagerAdapter;
    public boolean usesRtl;

    public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        PagerAdapter pageradapter = directionUnawarePagerAdapter;
        int j = i;
        if (usesRtl)
        {
            j = getCount() - i - 1;
        }
        pageradapter.destroyItem(viewgroup, j, obj);
    }

    public final void finishUpdate(ViewGroup viewgroup)
    {
        directionUnawarePagerAdapter.finishUpdate(viewgroup);
    }

    public final int getCount()
    {
        return directionUnawarePagerAdapter.getCount();
    }

    public final int getItemPosition(Object obj)
    {
        int i;
        for (i = directionUnawarePagerAdapter.getItemPosition(obj); i < 0 || !usesRtl;)
        {
            return i;
        }

        return getCount() - i - 1;
    }

    public final float getPageWidth(int i)
    {
        PagerAdapter pageradapter = directionUnawarePagerAdapter;
        int j = i;
        if (usesRtl)
        {
            j = getCount() - i - 1;
        }
        return pageradapter.getPageWidth(j);
    }

    public final Object instantiateItem(ViewGroup viewgroup, int i)
    {
        PagerAdapter pageradapter = directionUnawarePagerAdapter;
        int j = i;
        if (usesRtl)
        {
            j = getCount() - i - 1;
        }
        return pageradapter.instantiateItem(viewgroup, j);
    }

    public final boolean isViewFromObject(View view, Object obj)
    {
        return directionUnawarePagerAdapter.isViewFromObject(view, obj);
    }

    public final Parcelable saveState()
    {
        return directionUnawarePagerAdapter.saveState();
    }

    public final void setPrimaryItem(ViewGroup viewgroup, int i, Object obj)
    {
        PagerAdapter pageradapter = directionUnawarePagerAdapter;
        int j = i;
        if (usesRtl)
        {
            j = getCount() - i - 1;
        }
        pageradapter.setPrimaryItem(viewgroup, j, obj);
    }

    public final void startUpdate(ViewGroup viewgroup)
    {
        directionUnawarePagerAdapter.startUpdate(viewgroup);
    }

    public _cls1.this._cls0(PagerAdapter pageradapter)
    {
        directionUnawarePagerAdapter = pageradapter;
        class _cls1 extends DataSetObserver
        {

            private final LayoutDirectionAwareViewPager.DirectionAwarePagerAdapter this$0;

            public final void onChanged()
            {
                notifyDataSetChanged();
            }

            _cls1()
            {
                this$0 = LayoutDirectionAwareViewPager.DirectionAwarePagerAdapter.this;
                super();
            }
        }

        pageradapter = directionUnawarePagerAdapter;
        DataSetObserver datasetobserver = directionUnawareDataSetObserver;
        pageradapter.mObservable.registerObserver(datasetobserver);
    }
}
