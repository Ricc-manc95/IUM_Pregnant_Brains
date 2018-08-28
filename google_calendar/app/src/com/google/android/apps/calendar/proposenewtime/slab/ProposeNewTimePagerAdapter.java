// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.slab;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.calendar.proposenewtime.slab.views.ProposeSlabItem;
import com.google.android.apps.calendar.proposenewtime.slab.views.ReviewSlabItem;
import com.google.android.apps.calendar.proposenewtime.slab.views.SlabItem;
import com.google.android.apps.calendar.proposenewtime.state.Attendee;
import com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState;
import com.google.android.apps.calendar.proposenewtime.state.StateHolder;
import java.util.Iterator;
import java.util.List;

public final class ProposeNewTimePagerAdapter extends PagerAdapter
{

    public final DateClickListener dateClickListener;
    private final com.google.android.apps.calendar.proposenewtime.slab.views.ProposeSlabItem.OnCommentChangeListener onCommentChangedListener;
    private final StateHolder stateHolder;

    public ProposeNewTimePagerAdapter(StateHolder stateholder, DateClickListener dateclicklistener, com.google.android.apps.calendar.proposenewtime.slab.views.ProposeSlabItem.OnCommentChangeListener oncommentchangelistener)
    {
        stateHolder = stateholder;
        dateClickListener = dateclicklistener;
        onCommentChangedListener = oncommentchangelistener;
    }

    public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        viewgroup.removeView((View)obj);
    }

    public final int getCount()
    {
        Object obj = stateHolder.getState();
        if (((ProposeNewTimeState) (obj)).getMode() != com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE) goto _L2; else goto _L1
_L1:
        int j = 1;
_L4:
        return j;
_L2:
        obj = ((ProposeNewTimeState) (obj)).getAttendees().iterator();
        int i = 0;
        do
        {
            j = i;
            if (!((Iterator) (obj)).hasNext())
            {
                continue;
            }
            if (((Attendee)((Iterator) (obj)).next()).getProposal() != null)
            {
                i++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final Object instantiateItem(ViewGroup viewgroup, int i)
    {
        Object obj2 = null;
        Object obj;
        Object obj1;
        if (stateHolder.getState().getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            obj1 = new ProposeSlabItem(viewgroup.getContext());
        } else
        {
            obj1 = new ReviewSlabItem(viewgroup.getContext());
        }
        ((SlabItem) (obj1)).setClickable(false);
        ((SlabItem) (obj1)).setTag(Integer.valueOf(i));
        obj = stateHolder.getState();
        if (((ProposeNewTimeState) (obj)).getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            obj = ((ProposeNewTimeState) (obj)).getTimeProposal();
        } else
        {
            obj = ((ProposeNewTimeState) (obj)).getAttendeeForNthProposal(i);
            if (obj != null)
            {
                obj = ((Attendee) (obj)).getProposal();
            } else
            {
                obj = null;
            }
        }
        ((SlabItem) (obj1)).setTimeProposal(((com.google.android.apps.calendar.proposenewtime.state.TimeProposal) (obj)));
        obj = stateHolder.getState();
        if (((ProposeNewTimeState) (obj)).getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            obj = obj2;
        } else
        {
            obj = ((ProposeNewTimeState) (obj)).getAttendeeForNthProposal(i);
        }
        ((SlabItem) (obj1)).setAttendee(((Attendee) (obj)));
        if (dateClickListener != null)
        {
            class .Lambda._cls0
                implements android.view.View.OnClickListener
            {

                private final ProposeNewTimePagerAdapter arg$1;

                public final void onClick(View view)
                {
                    arg$1.dateClickListener.onStartDateClicked();
                }

            .Lambda._cls0()
            {
                arg$1 = ProposeNewTimePagerAdapter.this;
            }

                private class DateClickListener
                {

                    public abstract void onEndDateClicked();

                    public abstract void onEndTimeClicked();

                    public abstract void onStartDateClicked();

                    public abstract void onStartTimeClicked();
                }

            }

            ((SlabItem) (obj1)).setStartDateClickListener(new .Lambda._cls0());
            class .Lambda._cls1
                implements android.view.View.OnClickListener
            {

                private final ProposeNewTimePagerAdapter arg$1;

                public final void onClick(View view)
                {
                    arg$1.dateClickListener.onStartTimeClicked();
                }

            .Lambda._cls1()
            {
                arg$1 = ProposeNewTimePagerAdapter.this;
            }
            }

            ((SlabItem) (obj1)).setStartTimeClickListener(new .Lambda._cls1());
            class .Lambda._cls2
                implements android.view.View.OnClickListener
            {

                private final ProposeNewTimePagerAdapter arg$1;

                public final void onClick(View view)
                {
                    arg$1.dateClickListener.onEndDateClicked();
                }

            .Lambda._cls2()
            {
                arg$1 = ProposeNewTimePagerAdapter.this;
            }
            }

            ((SlabItem) (obj1)).setEndDateClickListener(new .Lambda._cls2());
            class .Lambda._cls3
                implements android.view.View.OnClickListener
            {

                private final ProposeNewTimePagerAdapter arg$1;

                public final void onClick(View view)
                {
                    arg$1.dateClickListener.onEndTimeClicked();
                }

            .Lambda._cls3()
            {
                arg$1 = ProposeNewTimePagerAdapter.this;
            }
            }

            ((SlabItem) (obj1)).setEndTimeClickListener(new .Lambda._cls3());
        }
        if (onCommentChangedListener != null)
        {
            ((SlabItem) (obj1)).setCommentChangeListener(onCommentChangedListener);
        }
        viewgroup.addView(((View) (obj1)));
        return obj1;
    }

    public final boolean isViewFromObject(View view, Object obj)
    {
        return view == obj;
    }
}
