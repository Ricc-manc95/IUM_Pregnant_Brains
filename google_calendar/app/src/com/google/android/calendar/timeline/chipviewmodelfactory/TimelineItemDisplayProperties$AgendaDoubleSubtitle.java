// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.calendar.timely.TimelineItem;

final class timeLocationPlaceholder extends timeLocationPlaceholder
{

    private final String timeLocationPlaceholder;

    public final volatile Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        return onAny(timelineitem, (Integer[])aobj);
    }

    public final transient String onAny(TimelineItem timelineitem, Integer ainteger[])
    {
        boolean flag;
        if (ainteger.length > 0 && ainteger.length <= 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        ainteger = super.onAny(timelineitem, ainteger);
        timelineitem = timelineitem.getLocation();
        if (TextUtils.isEmpty(ainteger))
        {
            return timelineitem;
        }
        if (TextUtils.isEmpty(timelineitem))
        {
            return ainteger;
        } else
        {
            return String.format(timeLocationPlaceholder, new Object[] {
                ainteger, timelineitem
            });
        }
    }

    public (Resources resources)
    {
        super(resources);
        timeLocationPlaceholder = resources.getString(0x7f130482);
    }
}
