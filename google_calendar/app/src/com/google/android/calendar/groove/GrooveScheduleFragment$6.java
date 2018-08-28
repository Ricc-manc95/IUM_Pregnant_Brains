// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.support.v4.graphics.ColorUtils;

final class val.backgroundColor
    implements android.support.v7.graphics.ment._cls6
{

    private final int val$backgroundColor;

    public final boolean isAllowed(int i, float af[])
    {
        return ColorUtils.calculateContrast(i, val$backgroundColor) > 1.5D;
    }

    A()
    {
        val$backgroundColor = i;
        super();
    }
}
