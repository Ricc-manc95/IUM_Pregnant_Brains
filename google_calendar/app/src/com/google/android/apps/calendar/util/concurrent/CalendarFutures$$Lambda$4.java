// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            CalendarFutures

final class arg._cls1
    implements Function
{

    private final Object arg$1;

    public final Object apply(Object obj)
    {
        return CalendarFutures.lambda$mapFold$4$CalendarFutures(arg$1, obj);
    }

    (Object obj)
    {
        arg$1 = obj;
    }
}
