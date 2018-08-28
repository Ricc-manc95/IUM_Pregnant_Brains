// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.calendar.time.Time;

public final class monthLabelTextBottomOffset
{

    public final int julianDay;
    public final int monthLabelTextBottomOffset;
    public final int monthViewTopOffset;
    public final float scrollDeltaY;
    public final Time time;

    public (Time time1, int i, float f, int j, int k)
    {
        time = time1;
        julianDay = i;
        scrollDeltaY = f;
        monthViewTopOffset = j;
        monthLabelTextBottomOffset = k;
    }
}
