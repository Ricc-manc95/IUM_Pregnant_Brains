// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.ads.identifier;

import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.gms.ads.identifier:
//            AdvertisingIdClient

final class start extends Thread
{

    private WeakReference zzsw;
    private long zzsx;
    public CountDownLatch zzsy;
    public boolean zzsz;

    public final void run()
    {
        AdvertisingIdClient advertisingidclient;
        if (zzsy.await(zzsx, TimeUnit.MILLISECONDS))
        {
            break MISSING_BLOCK_LABEL_41;
        }
        advertisingidclient = (AdvertisingIdClient)zzsw.get();
        if (advertisingidclient == null)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        advertisingidclient.finish();
        zzsz = true;
_L1:
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        AdvertisingIdClient advertisingidclient1 = (AdvertisingIdClient)zzsw.get();
        if (advertisingidclient1 != null)
        {
            advertisingidclient1.finish();
            zzsz = true;
            return;
        }
          goto _L1
    }

    public (AdvertisingIdClient advertisingidclient, long l)
    {
        zzsw = new WeakReference(advertisingidclient);
        zzsx = l;
        zzsy = new CountDownLatch(1);
        zzsz = false;
        start();
    }
}
