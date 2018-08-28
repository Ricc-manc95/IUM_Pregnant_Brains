// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

// Referenced classes of package android.support.v7.app:
//            TwilightManager, AppCompatDelegateImpl

final class mIsNight
{

    public BroadcastReceiver mAutoTimeChangeReceiver;
    public IntentFilter mAutoTimeChangeReceiverFilter;
    public boolean mIsNight;
    public TwilightManager mTwilightManager;
    public final AppCompatDelegateImpl this$0;

    (TwilightManager twilightmanager)
    {
        this$0 = AppCompatDelegateImpl.this;
        super();
        mTwilightManager = twilightmanager;
        mIsNight = twilightmanager.isNight();
    }
}
