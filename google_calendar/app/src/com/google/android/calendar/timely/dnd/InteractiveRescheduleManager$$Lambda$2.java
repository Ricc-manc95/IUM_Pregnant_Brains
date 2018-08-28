// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.dnd;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.timely.dnd:
//            InteractiveRescheduleManager

final class Snackbar
    implements AsyncFunction
{

    public static final AsyncFunction $instance = new <init>();

    public final ListenableFuture apply(Object obj)
    {
        return InteractiveRescheduleManager.lambda$reschedule$1$InteractiveRescheduleManager((Snackbar)obj);
    }


    private Snackbar()
    {
    }
}
