// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineAccessibilityDelegate

final class 
    implements Executor
{

    public static final Executor $instance = new <init>();

    public final void execute(Runnable runnable)
    {
        TimelineAccessibilityDelegate.lambda$new$1$TimelineAccessibilityDelegate(runnable);
    }


    private ()
    {
    }
}
