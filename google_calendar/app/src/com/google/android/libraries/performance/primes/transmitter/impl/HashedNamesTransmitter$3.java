// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.transmitter.impl;

import logs.proto.wireless.performance.mobile.nano.Span;

final class tricNameAccess
    implements tricNameAccess
{

    public final String getConstantName(Object obj)
    {
        return ((Span)obj).constantName;
    }

    public final String getCustomName(Object obj)
    {
        return ((Span)obj).name;
    }

    public final void setCustomName(Object obj, String s)
    {
        ((Span)obj).name = s;
    }

    public final void setHashedName(Object obj, Long long1)
    {
        ((Span)obj).hashedName = long1;
    }

    tricNameAccess()
    {
    }
}
