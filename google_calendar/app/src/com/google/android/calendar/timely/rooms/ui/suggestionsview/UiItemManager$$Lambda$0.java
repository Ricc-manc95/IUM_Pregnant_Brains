// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import com.google.android.calendar.timely.rooms.data.AttendeeGroup;
import com.google.android.calendar.timely.rooms.data.RoomCriteria;
import com.google.common.base.Function;

public final class Y
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return ((AttendeeGroup)obj).getCriteria().getPreferredBuildingId();
    }


    private Y()
    {
    }
}
