// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;

import android.text.format.Time;

// Referenced classes of package com.android.calendarcommon2:
//            EventRecurrence

public final class time
{

    public int days;
    public int month;
    public EventRecurrence r;
    public Time time;
    public int year;

    public ()
    {
        time = new Time("UTC");
    }
}
