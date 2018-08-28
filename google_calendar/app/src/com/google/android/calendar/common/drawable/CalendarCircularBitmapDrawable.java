// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.drawable;

import android.content.res.Resources;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.drawable.CircularBitmapDrawable;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import java.util.concurrent.Executor;

public class CalendarCircularBitmapDrawable extends CircularBitmapDrawable
{

    public CalendarCircularBitmapDrawable(Resources resources, BitmapCache bitmapcache, boolean flag)
    {
        super(resources, bitmapcache, flag);
    }

    protected final Executor getExecutor()
    {
        return CalendarExecutor.BACKGROUND;
    }
}
