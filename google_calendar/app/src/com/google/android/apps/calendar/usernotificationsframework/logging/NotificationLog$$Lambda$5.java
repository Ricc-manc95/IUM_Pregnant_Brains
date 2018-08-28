// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.logging;


// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.logging:
//            NotificationLog

public final class arg._cls4
    implements Runnable
{

    private final String arg$1;
    private final Throwable arg$2;
    private final String arg$3;
    private final Object arg$4[];

    public final void run()
    {
        NotificationLog.lambda$e$5$NotificationLog(arg$1, arg$2, arg$3, arg$4);
    }

    public (String s, Throwable throwable, String s1, Object aobj[])
    {
        arg$1 = s;
        arg$2 = throwable;
        arg$3 = s1;
        arg$4 = aobj;
    }
}
