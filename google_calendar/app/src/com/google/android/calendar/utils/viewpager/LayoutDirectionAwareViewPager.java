// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.viewpager;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.ArrayList;

public class LayoutDirectionAwareViewPager extends ViewPager
{
    final class DirectionAwareOnPageChangeListener
        implements android.support.v4.view.ViewPager.OnPageChangeListener
    {

        private final android.support.v4.view.ViewPager.OnPageChangeListener directionUnawareListener;
        private final LayoutDirectionAwareViewPager this$0;

        public final void onPageScrollStateChanged(int i)
        {
            directionUnawareListener.onPageScrollStateChanged(i);
        }

        public final void onPageScrolled(int i, float f, int j)
        {
            android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener = directionUnawareListener;
            int k = getDirectionAwarePosition(i);
            float f1 = f;
            if (usesRtl)
            {
                f1 = -f;
            }
            i = j;
            if (usesRtl)
            {
                i = -j;
            }
            onpagechangelistener.onPageScrolled(k, f1, i);
        }

        public final void onPageSelected(int i)
        {
            directionUnawareListener.onPageSelected(getDirectionAwarePosition(i));
        }

        public DirectionAwareOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
        {
            this$0 = LayoutDirectionAwareViewPager.this;
            super();
            directionUnawareListener = onpagechangelistener;
        }
    }

    static final class DirectionAwarePagerAdapter extends PagerAdapter
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

        public DirectionAwarePagerAdapter(PagerAdapter pageradapter)
        {
            directionUnawarePagerAdapter = pageradapter;
            class _cls1 extends DataSetObserver
            {

                private final DirectionAwarePagerAdapter this$0;

                public final void onChanged()
                {
                    notifyDataSetChanged();
                }

                _cls1()
                {
                    this$0 = DirectionAwarePagerAdapter.this;
                    super();
                }
            }

            pageradapter = directionUnawarePagerAdapter;
            DataSetObserver datasetobserver = directionUnawareDataSetObserver;
            pageradapter.mObservable.registerObserver(datasetobserver);
        }
    }


    private final ArrayList directionAwareListeners;
    private DirectionAwarePagerAdapter directionAwarePagerAdapter;
    public boolean usesRtl;

    public LayoutDirectionAwareViewPager(Context context)
    {
        super(context);
        usesRtl = false;
        directionAwareListeners = new ArrayList(1);
    }

    public LayoutDirectionAwareViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        usesRtl = false;
        directionAwareListeners = new ArrayList(1);
    }

    public void addOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        onpagechangelistener = new DirectionAwareOnPageChangeListener(onpagechangelistener);
        directionAwareListeners.add(onpagechangelistener);
        super.addOnPageChangeListener(onpagechangelistener);
    }

    public final PagerAdapter getAdapter()
    {
        PagerAdapter pageradapter = super.getAdapter();
        if (pageradapter != null)
        {
            return ((DirectionAwarePagerAdapter)pageradapter).directionUnawarePagerAdapter;
        } else
        {
            return null;
        }
    }

    public final int getCurrentItem()
    {
        int j = super.getCurrentItem();
        int i = j;
        if (directionAwarePagerAdapter != null)
        {
            DirectionAwarePagerAdapter directionawarepageradapter = directionAwarePagerAdapter;
            i = j;
            if (directionawarepageradapter.usesRtl)
            {
                i = directionawarepageradapter.getCount() - j - 1;
            }
        }
        return i;
    }

    final int getDirectionAwarePosition(int i)
    {
        int j = i;
        if (directionAwarePagerAdapter != null)
        {
            DirectionAwarePagerAdapter directionawarepageradapter = directionAwarePagerAdapter;
            j = i;
            if (directionawarepageradapter.usesRtl)
            {
                j = directionawarepageradapter.getCount() - i - 1;
            }
        }
        return j;
    }

    public void onRtlPropertiesChanged(int i)
    {
        boolean flag = RtlUtils.isLayoutDirectionRtl(getContext());
        if (flag != usesRtl)
        {
            i = getCurrentItem();
            usesRtl = flag;
            if (directionAwarePagerAdapter != null)
            {
                DirectionAwarePagerAdapter directionawarepageradapter = directionAwarePagerAdapter;
                directionawarepageradapter.usesRtl = flag;
                directionawarepageradapter.notifyDataSetChanged();
            }
            setCurrentItem(i, false);
        }
    }

    public final void setAdapter(PagerAdapter pageradapter)
    {
        if (directionAwarePagerAdapter != null)
        {
            Object obj = directionAwarePagerAdapter;
            PagerAdapter pageradapter1 = ((DirectionAwarePagerAdapter) (obj)).directionUnawarePagerAdapter;
            obj = ((DirectionAwarePagerAdapter) (obj)).directionUnawareDataSetObserver;
            pageradapter1.mObservable.unregisterObserver(obj);
            directionAwarePagerAdapter = null;
        }
        if (pageradapter != null)
        {
            directionAwarePagerAdapter = new DirectionAwarePagerAdapter(pageradapter);
            pageradapter = directionAwarePagerAdapter;
            pageradapter.usesRtl = usesRtl;
            pageradapter.notifyDataSetChanged();
        }
        super.setAdapter(directionAwarePagerAdapter);
    }

    public void setCurrentItem(int i)
    {
        int j = i;
        if (directionAwarePagerAdapter != null)
        {
            DirectionAwarePagerAdapter directionawarepageradapter = directionAwarePagerAdapter;
            j = i;
            if (directionawarepageradapter.usesRtl)
            {
                j = directionawarepageradapter.getCount() - i - 1;
            }
        }
        super.setCurrentItem(j);
    }

    public void setCurrentItem(int i, boolean flag)
    {
        int j = i;
        if (directionAwarePagerAdapter != null)
        {
            DirectionAwarePagerAdapter directionawarepageradapter = directionAwarePagerAdapter;
            j = i;
            if (directionawarepageradapter.usesRtl)
            {
                j = directionawarepageradapter.getCount() - i - 1;
            }
        }
        super.setCurrentItem(j, flag);
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        super.setOnPageChangeListener(new DirectionAwareOnPageChangeListener(onpagechangelistener));
    }
}
