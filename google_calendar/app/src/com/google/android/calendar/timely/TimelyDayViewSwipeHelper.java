// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.interaction.InteractionTracker;
import com.google.android.calendar.timely.timeline.TimelineItemCollection;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayView, TimelineItem

public class TimelyDayViewSwipeHelper
    implements com.google.android.calendar.timeline.chip.ChipSwipeHelper.Delegate, com.google.android.calendar.timeline.chipviewmodelfactory.ChipViewModelFactory.SwipeConfigProvider
{

    public final TimelyDayView dayView;
    public TimelyDayView.SwipeGestureDelegate _flddelegate;

    public TimelyDayViewSwipeHelper(TimelyDayView timelydayview)
    {
        dayView = timelydayview;
    }

    public final int getSupportedSwipeDirections(TimelineItem timelineitem)
    {
        if (_flddelegate == null)
        {
            return 0;
        } else
        {
            return _flddelegate.getSupportedSwipeDirections(timelineitem);
        }
    }

    public final Integer getSwipeIcon(TimelineItem timelineitem, int i)
    {
        if (_flddelegate == null)
        {
            return null;
        } else
        {
            return _flddelegate.getSwipeIcon(timelineitem, i);
        }
    }

    public final boolean isSwipeEnabled()
    {
        if (_flddelegate == null)
        {
            return false;
        } else
        {
            return _flddelegate.isSwipeEnabled(dayView);
        }
    }

    public final boolean isSwipePossible()
    {
        return _flddelegate != null;
    }

    public final void onSwipeGestureCancel(Chip chip)
    {
        InteractionTracker.getInstance().trackInteractionEnd(this, chip);
    }

    public final boolean onSwipeGestureComplete(Chip chip, int i)
    {
        boolean flag;
        if (_flddelegate == null)
        {
            flag = false;
        } else
        {
            flag = _flddelegate.onSwipeGestureComplete$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM7IH31F5B6IPBN7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM6IRJ595Q6AR9R94KLK___0(dayView, dayView.items.getItemForChip(chip));
        }
        InteractionTracker.getInstance().trackInteractionEnd(this, chip);
        return flag;
    }

    public final void onSwipeGestureStart(Chip chip)
    {
        InteractionTracker.getInstance().trackInteractionStart(this, chip);
    }

    static 
    {
        LogUtils.getLogTag(com/google/android/calendar/timely/TimelyDayViewSwipeHelper);
    }
}
