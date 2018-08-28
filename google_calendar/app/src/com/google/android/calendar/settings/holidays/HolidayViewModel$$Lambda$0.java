// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.holidays;

import java.util.Comparator;

final class endar
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        obj = (endar)obj;
        obj1 = (endar)obj1;
        return ((endar) (obj)).getDisplayName().compareTo(((endar) (obj1)).getDisplayName());
    }


    private endar()
    {
    }
}
