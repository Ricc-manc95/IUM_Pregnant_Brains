// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.function.BiFunction;
import com.google.common.collect.ImmutableList;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            EventsApiImpl

final class arg._cls1
    implements BiFunction
{

    private final int arg$1;

    public final Object apply(Object obj, Object obj1)
    {
        int i = arg$1;
        obj = (List)obj;
        obj1 = (List)obj1;
        LogUtils.d(EventsApiImpl.TAG, "%s> V2A Events: %s", new Object[] {
            Integer.valueOf(i), Integer.valueOf(((List) (obj)).size())
        });
        LogUtils.d(EventsApiImpl.TAG, "%s> CP Events: %s", new Object[] {
            Integer.valueOf(i), Integer.valueOf(((List) (obj1)).size())
        });
        obj = (com.google.common.collect.Impl.TAG)((com.google.common.collect.Impl.TAG)ImmutableList.builder().addAll(((Iterable) (obj)))).addAll(((Iterable) (obj1)));
        obj.rceCopy = true;
        return ImmutableList.asImmutableList(((com.google.common.collect.eList) (obj)).ntents, ((com.google.common.collect.ntents) (obj)).ze);
    }

    (int i)
    {
        arg$1 = i;
    }
}
