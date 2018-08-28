// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            MonthBannerView

final class arg._cls1
    implements Runnable
{

    private final MonthBannerView arg$1;

    public final void run()
    {
        arg$1.onBitmapFutureComplete(true);
    }

    (MonthBannerView monthbannerview)
    {
        arg$1 = monthbannerview;
    }
}
