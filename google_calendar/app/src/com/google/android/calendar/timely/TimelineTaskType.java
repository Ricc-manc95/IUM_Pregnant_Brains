// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItem

public interface TimelineTaskType
    extends TimelineItem
{

    public static final Comparator DATE_COMPARATOR = .Lambda._cls0..instance;

    public abstract boolean isDone();

    public abstract void setTransientDoneState(int i);


    class .Lambda._cls0
        implements Comparator
    {

        public static final Comparator $instance = new .Lambda._cls0();

        public final int compare(Object obj, Object obj1)
        {
            double d1 = 0.0D;
            obj = (TimelineTaskType)obj;
            obj1 = (TimelineTaskType)obj1;
            double d2 = ((TimelineTaskType) (obj)).getStartMillis();
            double d;
            double d3;
            if (((TimelineTaskType) (obj)).isAllDay())
            {
                d = 0.0D;
            } else
            {
                d = 0.10000000000000001D;
            }
            d3 = ((TimelineTaskType) (obj1)).getStartMillis();
            if (!((TimelineTaskType) (obj1)).isAllDay())
            {
                d1 = 0.10000000000000001D;
            }
            return Double.valueOf(d + d2).compareTo(Double.valueOf(d1 + d3));
        }


            private .Lambda._cls0()
            {
            }
    }

}
