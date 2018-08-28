// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;


// Referenced classes of package com.google.analytics.tracking.android:
//            AnalyticsStoreStateListener, GAServiceManager

final class this._cls0
    implements AnalyticsStoreStateListener
{

    private final GAServiceManager this$0;

    public final void reportStoreIsEmpty(boolean flag)
    {
        updatePowerSaveMode(flag, connected);
    }

    Listener()
    {
        this$0 = GAServiceManager.this;
        super();
    }
}
