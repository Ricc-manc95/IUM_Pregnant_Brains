// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;

public final class AndroidClock
    implements Clock
{

    public AndroidClock()
    {
    }

    public final long currentTimeMillis()
    {
        return System.currentTimeMillis();
    }
}
