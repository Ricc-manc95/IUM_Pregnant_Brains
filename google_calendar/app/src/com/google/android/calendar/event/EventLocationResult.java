// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.Context;

public final class EventLocationResult
{

    public final String address;
    private final Context context;
    public final boolean isHeader;
    public final String name;
    public final String reference;

    public EventLocationResult(Context context1, String s)
    {
        context = context1;
        name = s;
        address = null;
        reference = null;
        isHeader = true;
    }

    public EventLocationResult(Context context1, String s, String s1, String s2)
    {
        context = context1;
        name = s;
        address = s1;
        reference = s2;
        isHeader = false;
    }

    public final String toString()
    {
        if (reference != null && context != null)
        {
            if (address != null)
            {
                return context.getString(0x7f130328, new Object[] {
                    name, address
                });
            } else
            {
                return context.getString(0x7f130329, new Object[] {
                    name
                });
            }
        } else
        {
            return address;
        }
    }
}
