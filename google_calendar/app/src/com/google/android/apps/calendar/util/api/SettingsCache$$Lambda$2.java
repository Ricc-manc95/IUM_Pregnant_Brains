// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.api;

import com.google.android.calendar.api.settings.Settings;
import com.google.common.base.Function;

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        com.google.android.calendar.api.settings.t t = (com.google.android.calendar.api.settings.t)obj;
        obj = new com.google.common.collect.it>();
        Settings asettings[] = t.getSettingsList();
        int j = asettings.length;
        for (int i = 0; i < j; i++)
        {
            Settings settings = asettings[i];
            ((com.google.common.collect.ingsList) (obj)).(settings.getDescriptor(), settings);
        }

        return ((com.google.common.collect.getDescriptor) (obj)).ld();
    }


    private ()
    {
    }
}
