// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.store.CalendarDay;
import com.google.android.apps.calendar.timeline.alternate.store.VersionedItem;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.geometry.PositionOnGrid;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.Constants;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.EventViewPositionHelper;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterConverter

final class arg._cls2
    implements Function
{

    private final AdapterConverter arg$1;
    private final CalendarDay arg$2;

    public final Object apply(Object obj)
    {
        AdapterConverter adapterconverter;
        Object obj1;
        Integer integer;
        int i;
        int j;
        int k;
        long l1;
        long l2;
        long l3;
        long l4;
        long l5;
        boolean flag;
        adapterconverter = arg$1;
        obj1 = arg$2;
        obj = (VersionedItem)obj;
        i = ((CalendarDay) (obj1)).getJulianDate();
        obj1 = ((VersionedItem) (obj)).getItem();
        j = adapterconverter.adapter.getStartDay(obj1);
        k = adapterconverter.adapter.getEndDay(obj1);
        integer = adapterconverter.getEventPosition(obj1, i - j);
        flag = adapterconverter.isTimedEvent(obj1);
        l4 = adapterconverter.adapter.getLocalEndMillis(obj1);
        l5 = adapterconverter.adapter.getLocalStartMillis(obj1);
        l2 = adapterconverter.timeUtils.msToFp16(l4);
        l3 = adapterconverter.timeUtils.msToFp16(l5);
        l1 = l2;
        if (!flag) goto _L2; else goto _L1
_L1:
        long l;
        l1 = Math.max(l3, (long)k << 16);
        l = l2;
        if (l2 - l1 < Constants.MIN_CHIP_HEIGHT_FP16)
        {
            l = Math.max(Constants.MIN_CHIP_HEIGHT_FP16 + l1, (long)k << 16);
        }
        l2 = Math.min(l, (long)(j + 1) << 16);
        l1 = l;
        if (l2 - l3 >= Constants.MIN_CHIP_HEIGHT_FP16) goto _L2; else goto _L3
_L3:
        l1 = Math.min(l2 - Constants.MIN_CHIP_HEIGHT_FP16, (long)(j + 1) << 16);
        l2 = l;
        l = l1;
_L5:
        return (new der()).m(obj1).mVersion(((VersionedItem) (obj)).getVersion()).ition(integer.intValue() + EventViewPositionHelper.MIN_POS).imedEvent(adapterconverter.isTimedEvent(obj1)).ianDay(i).rtTimeMs(l5).TimeMs(l4).playStartFp16(l).playEndFp16(l2).dTimedPosition(new PositionOnGrid());
_L2:
        l = l3;
        l2 = l1;
        if (true) goto _L5; else goto _L4
_L4:
    }

    der(AdapterConverter adapterconverter, CalendarDay calendarday)
    {
        arg$1 = adapterconverter;
        arg$2 = calendarday;
    }
}
