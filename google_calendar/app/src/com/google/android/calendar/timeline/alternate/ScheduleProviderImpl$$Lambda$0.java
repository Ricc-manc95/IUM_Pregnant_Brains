// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.content.Context;
import android.graphics.BitmapFactory;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            ScheduleProviderImpl

final class arg._cls2
    implements Callable
{

    private final ScheduleProviderImpl arg$1;
    private final int arg$2;

    public final Object call()
    {
        ScheduleProviderImpl scheduleproviderimpl = arg$1;
        int i = arg$2;
        return BitmapFactory.decodeResource(scheduleproviderimpl.context.getResources(), i);
    }

    (ScheduleProviderImpl scheduleproviderimpl, int i)
    {
        arg$1 = scheduleproviderimpl;
        arg$2 = i;
    }
}
