// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import com.google.android.apps.calendar.util.function.Consumer;

final class Y
    implements Consumer
{

    public static final Consumer $instance = new <init>();

    public final void accept(Object obj)
    {
        ((Y)obj).askDoneStateChanged(true);
    }


    private Y()
    {
    }
}
