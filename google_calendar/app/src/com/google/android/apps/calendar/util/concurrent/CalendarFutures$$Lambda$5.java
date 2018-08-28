// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.function.TriFunction;
import com.google.common.base.Function;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            CalendarFutures

final class arg._cls2
    implements Function
{

    private final Object arg$1;
    private final TriFunction arg$2;

    public final Object apply(Object obj)
    {
        return CalendarFutures.lambda$mapFold$5$CalendarFutures(arg$1, arg$2, (List)obj);
    }

    (Object obj, TriFunction trifunction)
    {
        arg$1 = obj;
        arg$2 = trifunction;
    }
}
