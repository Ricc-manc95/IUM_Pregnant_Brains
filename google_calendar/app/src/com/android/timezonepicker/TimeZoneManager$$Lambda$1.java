// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker;

import com.google.common.base.Predicate;

// Referenced classes of package com.android.timezonepicker:
//            TimeZoneParams, TimeZoneManager

final class arg._cls1
    implements Predicate
{

    private final String arg$1;

    public final boolean apply(Object obj)
    {
        return TimeZoneManager.lambda$findTimeZone$1$TimeZoneManager(arg$1, (TimeZoneParams)obj);
    }

    (String s)
    {
        arg$1 = s;
    }
}
