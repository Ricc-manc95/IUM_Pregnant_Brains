// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import com.google.common.base.Function;

final class Result
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return Integer.valueOf(((com.google.android.calendar.api.habit.ult)obj).getCount());
    }


    private Result()
    {
    }
}