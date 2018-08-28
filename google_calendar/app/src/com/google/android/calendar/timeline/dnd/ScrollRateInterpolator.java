// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.dnd;

import android.content.res.Resources;

final class ScrollRateInterpolator
{

    public final int maxScroll;

    private ScrollRateInterpolator(int i)
    {
        maxScroll = i;
    }

    ScrollRateInterpolator(Resources resources)
    {
        this(resources.getDimensionPixelSize(0x7f0e027a));
    }
}
