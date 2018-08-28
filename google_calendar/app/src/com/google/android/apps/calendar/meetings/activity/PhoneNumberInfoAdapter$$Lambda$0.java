// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.meetings.activity;

import com.google.android.calendar.event.conference.PhoneNumberDetails;
import java.util.Comparator;
import java.util.Locale;

final class Y
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        obj = (PhoneNumberDetails)obj;
        obj1 = (PhoneNumberDetails)obj1;
        return ((PhoneNumberDetails) (obj)).region().getDisplayCountry().compareTo(((PhoneNumberDetails) (obj1)).region().getDisplayCountry());
    }


    private Y()
    {
    }
}
