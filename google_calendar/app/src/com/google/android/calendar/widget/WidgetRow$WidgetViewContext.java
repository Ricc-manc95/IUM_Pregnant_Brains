// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;

final class noMoreEventsToday
{

    public final Context context;
    public final int darkDayColor;
    public final int firstDayColor;
    public final int greyTextColor;
    public final int lightDayColor;
    public final String noMoreEventsToday;
    public final String timeLocation;
    public final int whiteTextColor;
    public final int widgetStyle;

    (Context context1, int i)
    {
        context = context1;
        widgetStyle = i;
        whiteTextColor = ContextCompat.getColor(context1, 0x7f0d0336);
        greyTextColor = ContextCompat.getColor(context1, 0x7f0d021e);
        firstDayColor = ContextCompat.getColor(context1, 0x7f0d0338);
        lightDayColor = ContextCompat.getColor(context1, 0x7f0d0339);
        darkDayColor = ContextCompat.getColor(context1, 0x7f0d0337);
        timeLocation = context1.getString(0x7f130482);
        noMoreEventsToday = context1.getString(0x7f130353);
    }
}
