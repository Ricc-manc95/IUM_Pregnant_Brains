// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.dnd;

import android.support.design.widget.Snackbar;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.timely.dnd:
//            InteractiveRescheduleManager

static final class snackbar
{

    public final ListenableFuture rescheduleConfig;
    public final Snackbar snackbar;

    (ListenableFuture listenablefuture, Snackbar snackbar1)
    {
        rescheduleConfig = listenablefuture;
        snackbar = snackbar1;
    }
}
