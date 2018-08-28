// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;


// Referenced classes of package com.google.android.calendar.newapi.screen:
//            HostedFragment

public final class arg._cls1
    implements Runnable
{

    private final HostedFragment arg$1;

    public final void run()
    {
        arg$1.dismiss();
    }

    public (HostedFragment hostedfragment)
    {
        arg$1 = hostedfragment;
    }
}
