// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.dnd;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.timely.dnd:
//            InteractiveRescheduleManager

final class Snackbar
    implements Consumer
{

    public static final Consumer $instance = new <init>();

    public final void accept(Object obj)
    {
        InteractiveRescheduleManager.lambda$reschedule$5$InteractiveRescheduleManager((Snackbar)obj);
    }


    private Snackbar()
    {
    }
}
