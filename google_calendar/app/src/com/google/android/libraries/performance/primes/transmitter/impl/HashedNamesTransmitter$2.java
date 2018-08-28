// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.transmitter.impl;

import logs.proto.wireless.performance.mobile.nano.BatteryStatsDiff;

final class tricNameAccess
    implements tricNameAccess
{

    public final String getConstantName(Object obj)
    {
        return ((BatteryStatsDiff)obj).startConstantEventName;
    }

    public final String getCustomName(Object obj)
    {
        return ((BatteryStatsDiff)obj).startCustomEventName;
    }

    public final void setCustomName(Object obj, String s)
    {
        ((BatteryStatsDiff)obj).startCustomEventName = s;
    }

    public final void setHashedName(Object obj, Long long1)
    {
        ((BatteryStatsDiff)obj).startHashedCustomEventName = long1;
    }

    tricNameAccess()
    {
    }
}
