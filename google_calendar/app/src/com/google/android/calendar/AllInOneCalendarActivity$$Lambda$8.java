// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.collect.Range;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

final class arg._cls1
    implements Consumer
{

    private final AllInOneCalendarActivity arg$1;

    public final void accept(Object obj)
    {
        arg$1.onNewRange((Range)obj);
    }

    (AllInOneCalendarActivity allinonecalendaractivity)
    {
        arg$1 = allinonecalendaractivity;
    }
}
