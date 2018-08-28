// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.job;


// Referenced classes of package com.google.calendar.v2a.android.util.job:
//            FutureJobService

public final class I
    implements Runnable
{

    public static final Runnable $instance = new <init>();

    public final void run()
    {
        FutureJobService.lambda$scheduleJob$0$FutureJobService();
    }


    private I()
    {
    }
}
