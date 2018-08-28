// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;

import com.google.android.calendar.timely.TimelineTask;
import com.google.common.base.Function;

final class Y
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return (String)((TimelineTask)obj).id;
    }


    private Y()
    {
    }
}
