// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.common.base.Supplier;

final class 
    implements Supplier
{

    public static final Supplier $instance = new <init>();

    public final Object get()
    {
        IllegalArgumentException illegalargumentexception = new IllegalArgumentException("Absent");
        if (illegalargumentexception == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.util.concurrent.ailedFuture(illegalargumentexception);
        }
    }


    private ()
    {
    }
}
