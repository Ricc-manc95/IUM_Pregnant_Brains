// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            ScheduleItem

final class AutoValue_ScheduleItem extends ScheduleItem
{

    private final int bottom;
    private final Long endTimeMs;
    private final int left;
    private final int position;
    private final int right;
    private final Long startTimeMs;
    private final boolean timedItem;
    private final int top;

    AutoValue_ScheduleItem(int i, int j, int k, int l, int i1, Long long1, Long long2, 
            boolean flag)
    {
        position = i;
        left = j;
        top = k;
        right = l;
        bottom = i1;
        startTimeMs = long1;
        endTimeMs = long2;
        timedItem = flag;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ScheduleItem)
            {
                if (position != ((ScheduleItem) (obj = (ScheduleItem)obj)).getPosition() || left != ((ScheduleItem) (obj)).getLeft() || top != ((ScheduleItem) (obj)).getTop() || right != ((ScheduleItem) (obj)).getRight() || bottom != ((ScheduleItem) (obj)).getBottom() || (startTimeMs != null ? !startTimeMs.equals(((ScheduleItem) (obj)).getStartTimeMs()) : ((ScheduleItem) (obj)).getStartTimeMs() != null) || (endTimeMs != null ? !endTimeMs.equals(((ScheduleItem) (obj)).getEndTimeMs()) : ((ScheduleItem) (obj)).getEndTimeMs() != null) || timedItem != ((ScheduleItem) (obj)).isTimedItem())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    final int getBottom()
    {
        return bottom;
    }

    final Long getEndTimeMs()
    {
        return endTimeMs;
    }

    final int getLeft()
    {
        return left;
    }

    final int getPosition()
    {
        return position;
    }

    final int getRight()
    {
        return right;
    }

    final Long getStartTimeMs()
    {
        return startTimeMs;
    }

    final int getTop()
    {
        return top;
    }

    public final int hashCode()
    {
        int j = 0;
        int k = position;
        int l = left;
        int i1 = top;
        int j1 = right;
        int k1 = bottom;
        int i;
        char c;
        if (startTimeMs == null)
        {
            i = 0;
        } else
        {
            i = startTimeMs.hashCode();
        }
        if (endTimeMs != null)
        {
            j = endTimeMs.hashCode();
        }
        if (timedItem)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return c ^ ((i ^ (((((k ^ 0xf4243) * 0xf4243 ^ l) * 0xf4243 ^ i1) * 0xf4243 ^ j1) * 0xf4243 ^ k1) * 0xf4243) * 0xf4243 ^ j) * 0xf4243;
    }

    final boolean isTimedItem()
    {
        return timedItem;
    }

    public final String toString()
    {
        int i = position;
        int j = left;
        int k = top;
        int l = right;
        int i1 = bottom;
        String s = String.valueOf(startTimeMs);
        String s1 = String.valueOf(endTimeMs);
        boolean flag = timedItem;
        return (new StringBuilder(String.valueOf(s).length() + 151 + String.valueOf(s1).length())).append("ScheduleItem{position=").append(i).append(", left=").append(j).append(", top=").append(k).append(", right=").append(l).append(", bottom=").append(i1).append(", startTimeMs=").append(s).append(", endTimeMs=").append(s1).append(", timedItem=").append(flag).append("}").toString();
    }
}
