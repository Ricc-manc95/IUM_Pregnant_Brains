// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridSlabPage, FindTimeGridFragment, FindTimeGridData, FindTimeGridSlabItem, 
//            FindTimeUtil, TimelineSuggestion

final class itemsToRemove extends PagerAdapter
{

    private ArrayList itemsAdded;
    private ArrayList itemsToAdd;
    private ArrayList itemsToRemove;
    private ArrayList recycledViews;
    public final FindTimeGridFragment this$0;

    public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        viewgroup = (FindTimeGridSlabPage)obj;
        recycledViews.add(viewgroup);
        itemsToRemove.add(viewgroup);
        itemsAdded.remove(viewgroup);
    }

    public final void finishUpdate(ViewGroup viewgroup)
    {
        boolean flag = false;
        ArrayList arraylist = (ArrayList)itemsToRemove;
        int k = arraylist.size();
        for (int i = 0; i < k;)
        {
            Object obj = arraylist.get(i);
            i++;
            viewgroup.removeView((View)obj);
        }

        arraylist = (ArrayList)itemsToAdd;
        k = arraylist.size();
        for (int j = ((flag) ? 1 : 0); j < k;)
        {
            Object obj1 = arraylist.get(j);
            j++;
            viewgroup.addView((View)obj1);
        }

    }

    public final int getCount()
    {
        return gridData.suggestions.size();
    }

    public final Object instantiateItem(final ViewGroup container, int i)
    {
        boolean flag = false;
        Object obj;
        TimelineSuggestion timelinesuggestion;
        if (recycledViews.size() > 0)
        {
            container = (FindTimeGridSlabPage)recycledViews.remove(0);
        } else
        {
            Object obj1 = FindTimeGridFragment.this;
            class _cls1
                implements android.view.View.OnClickListener
            {

                private final FindTimeGridFragment.FindTimeGridPagerAdapter this$1;

                public final void onClick(View view)
                {
                    boolean flag1;
                    if (!isManualTimeSlot && suggestionIndex < bestTimesCount)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    listener.onGridTimeSlotSelected(currentSuggestion, flag1, isManualTimeSlot);
                }

            _cls1()
            {
                this$1 = FindTimeGridFragment.FindTimeGridPagerAdapter.this;
                super();
            }
            }

            class _cls2
                implements FindTimeGridSlabPage.OnSlabPageUpdatedListener
            {

                private final FindTimeGridFragment.FindTimeGridPagerAdapter this$1;
                private final ViewGroup val$container;

                public final void onSlabBarHeightUpdated(FindTimeGridSlabPage findtimegridslabpage)
                {
                    if (((FindTimeGridViewPager)container).getCurrentItem() == ((Integer)findtimegridslabpage.getTag()).intValue())
                    {
                        updateMainContentMargin(findtimegridslabpage);
                    }
                }

            _cls2()
            {
                this$1 = FindTimeGridFragment.FindTimeGridPagerAdapter.this;
                container = viewgroup;
                super();
            }
            }

            if (((Fragment) (obj1)).mHost == null)
            {
                obj1 = null;
            } else
            {
                obj1 = (FragmentActivity)((Fragment) (obj1)).mHost.mActivity;
            }
            obj1 = new FindTimeGridSlabPage(((android.content.Context) (obj1)), timezone);
            ((FindTimeGridSlabPage) (obj1)).setLayoutParams(new android.view.LayoutParams(-1, -1));
            ((FindTimeGridSlabPage) (obj1)).setClickable(false);
            ((FindTimeGridSlabPage) (obj1)).doneFab.setOnClickListener(new _cls1());
            obj1.listener = new _cls2();
            container = ((ViewGroup) (obj1));
        }
        obj = timezone;
        ((FindTimeGridSlabPage) (container)).itemView.timezone = ((java.util.TimeZone) (obj));
        obj = FindTimeUtil.getInstance(context).getDescription((TimelineSuggestion)gridData.suggestions.get(i), accountName, accountType);
        timelinesuggestion = (TimelineSuggestion)gridData.suggestions.get(i);
        if (i == gridData.suggestions.size() - 1)
        {
            flag = true;
        }
        container.setTimelineSuggestion(timelinesuggestion, ((String) (obj)), flag);
        container.setTag(Integer.valueOf(i));
        itemsToAdd.add(container);
        itemsAdded.add(container);
        return container;
    }

    public final boolean isViewFromObject(View view, Object obj)
    {
        return view == obj;
    }

    public final void startUpdate(ViewGroup viewgroup)
    {
        itemsToAdd.clear();
        itemsToRemove.clear();
    }

    public _cls2()
    {
        this$0 = FindTimeGridFragment.this;
        super();
        recycledViews = new ArrayList();
        itemsToAdd = new ArrayList();
        itemsAdded = new ArrayList();
        itemsToRemove = new ArrayList();
    }
}
