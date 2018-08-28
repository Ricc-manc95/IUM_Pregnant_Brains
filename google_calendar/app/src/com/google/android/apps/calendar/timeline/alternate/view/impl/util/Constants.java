// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.util;

import java.util.concurrent.TimeUnit;

public final class Constants
{

    public static final long MIN_CHIP_HEIGHT_FP16;
    public static final long MIN_CHIP_HEIGHT_MS;

    static 
    {
        long l = TimeUnit.MINUTES.toMillis(30L);
        MIN_CHIP_HEIGHT_MS = l;
        MIN_CHIP_HEIGHT_FP16 = (l << 16) / TimeUnit.DAYS.toMillis(1L);
    }
}
