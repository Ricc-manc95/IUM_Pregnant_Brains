// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.drawable;

import android.content.res.Resources;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.drawable.StyledCornersBitmapDrawable;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import java.util.concurrent.Executor;

public final class CalendarStyledCornersBitmapDrawable extends StyledCornersBitmapDrawable
{

    public CalendarStyledCornersBitmapDrawable(Resources resources, BitmapCache bitmapcache, boolean flag, com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions extendedoptions, float f, float f1)
    {
        super(resources, bitmapcache, false, extendedoptions, f, f1);
    }

    protected final Executor getExecutor()
    {
        return CalendarExecutor.BACKGROUND;
    }
}
