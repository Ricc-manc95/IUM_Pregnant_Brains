// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.Set;

// Referenced classes of package com.google.common.util.concurrent:
//            AggregateFutureState

static abstract class I
{

    abstract void compareAndSetSeenExceptions(AggregateFutureState aggregatefuturestate, Set set, Set set1);

    abstract int decrementAndGetRemainingCount(AggregateFutureState aggregatefuturestate);

    I()
    {
    }
}
