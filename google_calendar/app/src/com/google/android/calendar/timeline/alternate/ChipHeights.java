// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.content.Context;
import android.content.res.Resources;

public final class ChipHeights
{

    private final int chipAssistHeight;
    private final int chipDoubleHeight;
    public final int chipSingleHeight;
    private final int chipTripleHeight;

    public ChipHeights(Context context)
    {
        context = context.getResources();
        chipSingleHeight = context.getDimensionPixelSize(0x7f0e0362);
        chipDoubleHeight = context.getDimensionPixelSize(0x7f0e0118);
        chipTripleHeight = context.getDimensionPixelSize(0x7f0e03ce);
        chipAssistHeight = context.getDimensionPixelSize(0x7f0e03ce);
    }

    public final int getAgendaViewHeight(int i)
    {
        switch (i)
        {
        default:
            return 0;

        case 1: // '\001'
            return chipSingleHeight;

        case 2: // '\002'
            return chipDoubleHeight;

        case 3: // '\003'
            return chipTripleHeight;

        case 4: // '\004'
            return chipAssistHeight;
        }
    }
}
