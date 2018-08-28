// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import java.util.TimerTask;

// Referenced classes of package com.google.analytics.tracking.android:
//            GAServiceProxy

final class this._cls0 extends TimerTask
{

    private final GAServiceProxy this$0;

    public final void run()
    {
        connectToService();
    }

    ()
    {
        this$0 = GAServiceProxy.this;
        super();
    }
}
