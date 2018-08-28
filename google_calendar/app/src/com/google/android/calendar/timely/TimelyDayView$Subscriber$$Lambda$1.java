// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.calendar.timely.interaction.helper.DelayedUpdateHelper;
import java.util.concurrent.Executor;

final class arg._cls1
    implements Executor
{

    private final DelayedUpdateHelper arg$1;

    public final void execute(Runnable runnable)
    {
        arg$1.postUpdate(runnable);
    }

    per(DelayedUpdateHelper delayedupdatehelper)
    {
        arg$1 = delayedupdatehelper;
    }
}
