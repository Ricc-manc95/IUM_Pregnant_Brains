// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.common.collect.ComparisonChain;

public final class EventComparators
{

    static int compareByDayAndLongest(ItemAdapter itemadapter, Object obj, Object obj1)
    {
        return ComparisonChain.ACTIVE.compare(itemadapter.getStartDay(obj), itemadapter.getStartDay(obj1)).compare(itemadapter.getEndDay(obj1), itemadapter.getEndDay(obj)).result();
    }

    static int compareTime(ItemAdapter itemadapter, Object obj, Object obj1)
    {
        int i = ComparisonChain.ACTIVE.compare(itemadapter.getStartDay(obj), itemadapter.getStartDay(obj1)).compareTrueFirst(itemadapter.isAllDay(obj), itemadapter.isAllDay(obj1)).result();
        if (i != 0)
        {
            return i;
        }
        if (itemadapter.isAllDay(obj))
        {
            return ComparisonChain.ACTIVE.compare(itemadapter.getEndDay(obj1), itemadapter.getEndDay(obj)).result();
        } else
        {
            return ComparisonChain.ACTIVE.compare(itemadapter.getLocalStartMillis(obj), itemadapter.getLocalStartMillis(obj1)).compare(itemadapter.getLocalEndMillis(obj), itemadapter.getLocalEndMillis(obj1)).result();
        }
    }
}
