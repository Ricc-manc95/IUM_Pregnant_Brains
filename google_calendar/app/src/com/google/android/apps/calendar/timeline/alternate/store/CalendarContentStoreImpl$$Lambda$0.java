// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarContentStoreImpl

final class arg._cls1
    implements Consumer
{

    private final CalendarContentStoreImpl arg$1;

    public final void accept(Object obj)
    {
        arg$1.invalidate();
    }

    (CalendarContentStoreImpl calendarcontentstoreimpl)
    {
        arg$1 = calendarcontentstoreimpl;
    }
}
