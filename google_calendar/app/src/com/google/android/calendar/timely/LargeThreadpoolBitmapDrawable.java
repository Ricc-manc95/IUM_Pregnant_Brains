// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.res.Resources;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.drawable.ExtendedBitmapDrawable;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import java.util.concurrent.Executor;

public final class LargeThreadpoolBitmapDrawable extends ExtendedBitmapDrawable
{

    public LargeThreadpoolBitmapDrawable(Resources resources, BitmapCache bitmapcache, boolean flag, com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions extendedoptions)
    {
        super(resources, bitmapcache, false, extendedoptions);
    }

    protected final Executor getExecutor()
    {
        return CalendarExecutor.BACKGROUND;
    }
}
