// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;


// Referenced classes of package com.google.analytics.tracking.android:
//            GAThread, ServiceProxy

final class this._cls0
    implements Runnable
{

    private final GAThread this$0;

    public final void run()
    {
        mServiceProxy.dispatch();
    }

    y()
    {
        this$0 = GAThread.this;
        super();
    }
}
