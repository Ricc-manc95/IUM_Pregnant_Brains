// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.calendar.belong.BelongUtils;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

final class arg._cls1
    implements Runnable
{

    private final AllInOneCalendarActivity arg$1;

    public final void run()
    {
        BelongUtils.startActivityCheck(arg$1, false);
    }

    (AllInOneCalendarActivity allinonecalendaractivity)
    {
        arg$1 = allinonecalendaractivity;
    }
}
