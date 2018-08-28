// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction.helper;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;

// Referenced classes of package com.google.android.calendar.timely.interaction.helper:
//            DelayedUpdateHelper

final class arg._cls2
    implements Runnable
{

    private final DelayedUpdateHelper arg$1;
    private final Runnable arg$2;

    public final void run()
    {
        DelayedUpdateHelper delayedupdatehelper = arg$1;
        Runnable runnable = arg$2;
        CalendarExecutor.MAIN.checkOnThread();
        DelayedUpdateHelper.logFishfoodInfo("Attempt Update: %s", new Object[] {
            delayedupdatehelper.view
        });
        delayedupdatehelper.delayedUpdate = runnable;
        delayedupdatehelper.updateIfIdleAndNeeded();
    }

    Y(DelayedUpdateHelper delayedupdatehelper, Runnable runnable)
    {
        arg$1 = delayedupdatehelper;
        arg$2 = runnable;
    }
}
