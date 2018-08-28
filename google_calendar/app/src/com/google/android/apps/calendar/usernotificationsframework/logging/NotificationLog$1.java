// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.logging;

import com.google.common.util.concurrent.FutureCallback;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.logging:
//            NotificationLog

final class val.args
    implements FutureCallback
{

    private final Object val$args[];
    private final String val$format;
    private final String val$tag;

    public final void onFailure(Throwable throwable)
    {
        String s = val$tag;
        String s1 = val$format;
        Object aobj[] = val$args;
        NotificationLog.SERIAL_EXECUTOR.execute(new ambda._cls5(s, throwable, s1, aobj));
    }

    public final void onSuccess(Object obj)
    {
    }

    ambda._cls5()
    {
        val$tag = s;
        val$format = s1;
        val$args = aobj;
        super();
    }
}
