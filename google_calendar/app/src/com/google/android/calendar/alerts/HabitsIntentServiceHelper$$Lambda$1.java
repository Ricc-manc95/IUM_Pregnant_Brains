// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import com.google.android.calendar.api.event.Event;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.alerts:
//            HabitsIntentServiceHelper

final class arg._cls1
    implements AsyncFunction
{

    private final int arg$1;

    public final ListenableFuture apply(Object obj)
    {
        return HabitsIntentServiceHelper.lambda$updateHabitInstanceStatusAsync$1$HabitsIntentServiceHelper(arg$1, (Event)obj);
    }

    (int i)
    {
        arg$1 = i;
    }
}
