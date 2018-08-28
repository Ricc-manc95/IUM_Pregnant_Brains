// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.growthkit;

import com.google.common.base.Supplier;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.growthkit:
//            GrowthKitCallback

final class arg._cls1
    implements Supplier
{

    private final TimeZone arg$1;

    public final Object get()
    {
        return GrowthKitCallback.lambda$getNumberOfVisibleEventsToday$2$GrowthKitCallback(arg$1);
    }

    (TimeZone timezone)
    {
        arg$1 = timezone;
    }
}
