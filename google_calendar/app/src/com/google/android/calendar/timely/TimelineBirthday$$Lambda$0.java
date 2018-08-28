// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.android.calendarcommon2.LogUtils;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineBirthday

final class arg._cls3
    implements Runnable
{

    private final TimelineBirthday arg$1;
    private final ListenableFuture arg$2;
    private final SettableFuture arg$3;

    public final void run()
    {
        TimelineBirthday timelinebirthday;
        ListenableFuture listenablefuture;
        timelinebirthday = arg$1;
        listenablefuture = arg$2;
        SettableFuture settablefuture = arg$3;
        timelinebirthday;
        JVM INSTR monitorenter ;
        timelinebirthday.birthdays = (List)listenablefuture.get();
        timelinebirthday.titlesValid = false;
        timelinebirthday;
        JVM INSTR monitorexit ;
_L2:
        settablefuture.set(null);
        return;
        Exception exception1;
        exception1;
        timelinebirthday;
        JVM INSTR monitorexit ;
        try
        {
            throw exception1;
        }
        catch (Exception exception)
        {
            LogUtils.wtf(TimelineBirthday.TAG, exception, "Unable to load birthdays", new Object[0]);
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    (TimelineBirthday timelinebirthday, ListenableFuture listenablefuture, SettableFuture settablefuture)
    {
        arg$1 = timelinebirthday;
        arg$2 = listenablefuture;
        arg$3 = settablefuture;
    }
}
