// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import java.util.Comparator;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry:
//            PositionOnGrid

public final class GeometryUtils
{

    public static final Comparator START_FRACTION_COMPARATOR;
    public static final Comparator START_TIME_COMPARATOR;

    public static long clampStartToDayFp16(com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder, TimeUtils timeutils)
    {
        return Math.max(builder.getDisplayStartFp16(), timeutils.msToFp16(timeutils.julianDateToMs(builder.getJulianDay())));
    }

    public static boolean intersectsTime(com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder, com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder1)
    {
        return builder1.getDisplayStartFp16() < builder.getDisplayEndFp16() && builder.getDisplayStartFp16() < builder1.getDisplayEndFp16();
    }

    static final int lambda$static$0$GeometryUtils(com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder, com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder1)
    {
        return Float.compare(builder.getGridTimedPosition().startFraction, builder1.getGridTimedPosition().startFraction);
    }

    static final int lambda$static$1$GeometryUtils(com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder, com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder1)
    {
        long l = builder.getDisplayStartFp16();
        long l1 = builder1.getDisplayStartFp16();
        if (l < l1)
        {
            return -1;
        }
        return l <= l1 ? 0 : 1;
    }

    public static boolean overlap(com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder, com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder builder1)
    {
        boolean flag;
        if (builder1.getDisplayStartFp16() < builder.getDisplayEndFp16() && builder.getDisplayStartFp16() < builder1.getDisplayEndFp16())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag && builder.getGridTimedPosition().startFraction < builder1.getGridTimedPosition().endFraction && builder.getGridTimedPosition().endFraction > builder1.getGridTimedPosition().startFraction;
    }

    static 
    {
        class .Lambda._cls0
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls0();

            public final int compare(Object obj, Object obj1)
            {
                return GeometryUtils.lambda$static$0$GeometryUtils((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)obj, (com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)obj1);
            }


            private .Lambda._cls0()
            {
            }
        }

        START_FRACTION_COMPARATOR = .Lambda._cls0..instance;
        class .Lambda._cls1
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls1();

            public final int compare(Object obj, Object obj1)
            {
                return GeometryUtils.lambda$static$1$GeometryUtils((com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)obj, (com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterEvent.Builder)obj1);
            }


            private .Lambda._cls1()
            {
            }
        }

        START_TIME_COMPARATOR = .Lambda._cls1..instance;
    }
}
