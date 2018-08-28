// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            LoadingStateManager

final class arg._cls1
    implements Runnable
{

    private final LoadingStateManager arg$1;

    public final void run()
    {
        arg$1.runLoadingPhase();
    }

    (LoadingStateManager loadingstatemanager)
    {
        arg$1 = loadingstatemanager;
    }
}
