// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.flags;

import android.content.Context;
import android.content.IntentFilter;
import com.google.android.libraries.performance.primes.Shutdown;
import com.google.android.libraries.performance.primes.Supplier;

public final class r extends Shutdown
{

    public final void init$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFADQN0S3CD5IN4EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BRGCLP6CRRIDLGMSOR55TO74QBDCLPIUKRLE1O6OQB5E8TIILG_0(Context context, Supplier supplier)
    {
        r r = new it>(context);
        updateShutdownFlag(r);
        if (!super.shutdown)
        {
            context.registerReceiver(new r(this, r, supplier), new IntentFilter("com.google.gservices.intent.action.GSERVICES_CHANGED"));
        }
    }

    public r()
    {
    }
}
