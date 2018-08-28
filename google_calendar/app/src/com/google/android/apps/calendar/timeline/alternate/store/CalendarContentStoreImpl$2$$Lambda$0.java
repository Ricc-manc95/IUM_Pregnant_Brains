// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarContentStoreImpl

final class arg._cls1
    implements Runnable
{

    private final arg._cls1 arg$1;

    public final void run()
    {
        arg._cls1 _lcls1 = arg$1;
        CalendarContentStoreImpl calendarcontentstoreimpl = _lcls1._fld1;
        calendarcontentstoreimpl.updateCount = calendarcontentstoreimpl.updateCount - 1;
        _lcls1._fld1.invalidate();
    }

    ( )
    {
        arg$1 = ;
    }
}
