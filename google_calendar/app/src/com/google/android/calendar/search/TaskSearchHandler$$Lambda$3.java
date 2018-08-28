// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.search;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.search:
//            TaskSearchHandler

final class arg._cls1
    implements Predicate
{

    private final String arg$1;

    public final boolean apply(Object obj)
    {
        return TaskSearchHandler.lambda$search$0$TaskSearchHandler(arg$1, (TimeRangeEntry)obj);
    }

    (String s)
    {
        arg$1 = s;
    }
}
