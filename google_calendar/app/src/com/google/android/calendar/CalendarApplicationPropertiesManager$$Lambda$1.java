// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.SharedPreferences;

// Referenced classes of package com.google.android.calendar:
//            CalendarApplicationPropertiesManager

final class arg._cls1
    implements android.content.ner
{

    private final CalendarApplicationPropertiesManager arg$1;

    public final void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
    {
        arg$1.updateSharedPreferencesValues();
    }

    (CalendarApplicationPropertiesManager calendarapplicationpropertiesmanager)
    {
        arg$1 = calendarapplicationpropertiesmanager;
    }
}
