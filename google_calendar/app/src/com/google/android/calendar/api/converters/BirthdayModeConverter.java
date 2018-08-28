// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.converters;

import com.google.android.apps.calendar.timely.store.BirthdaySetting;

public final class BirthdayModeConverter
{

    public static BirthdaySetting apiToStore(com.google.android.calendar.api.settings.GoogleSettings.BirthdayMode birthdaymode)
    {
        switch (birthdaymode.ordinal())
        {
        default:
            return new BirthdaySetting(2);

        case 0: // '\0'
            return new BirthdaySetting(0);

        case 1: // '\001'
            return new BirthdaySetting(1);
        }
    }

    public static com.google.android.calendar.api.settings.GoogleSettings.BirthdayMode storeToApi(int i)
    {
        switch (i)
        {
        default:
            return com.google.android.calendar.api.settings.GoogleSettings.BirthdayMode.NONE;

        case 0: // '\0'
            return com.google.android.calendar.api.settings.GoogleSettings.BirthdayMode.GPLUS_AND_CONTACTS;

        case 1: // '\001'
            return com.google.android.calendar.api.settings.GoogleSettings.BirthdayMode.CONTACTS;
        }
    }
}
