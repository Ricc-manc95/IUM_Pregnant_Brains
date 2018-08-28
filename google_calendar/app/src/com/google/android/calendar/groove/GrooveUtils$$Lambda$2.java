// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveUtils

final class arg._cls2
    implements Predicate
{

    private final long arg$1;
    private final long arg$2;

    public final boolean apply(Object obj)
    {
        return GrooveUtils.lambda$getStats$1$GrooveUtils(arg$1, arg$2, (TimeRangeEntry)obj);
    }

    (long l, long l1)
    {
        arg$1 = l;
        arg$2 = l1;
    }
}
