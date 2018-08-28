// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.metriccapture;

import android.content.pm.IPackageStatsObserver;

final class ckageStatsInvocation extends ckageStatsInvocation
{

    final Object[] params(String s, int i, IPackageStatsObserver ipackagestatsobserver)
    {
        return (new Object[] {
            s, Integer.valueOf(i), ipackagestatsobserver
        });
    }

    ckageStatsInvocation(String s, Class aclass[])
    {
        super(s, aclass);
    }
}
