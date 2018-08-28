// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.view;

import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.settings.SettingsShims;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;

final class arg._cls2
    implements android.support.v7.preference.nceClickListener
{

    private final Supplier arg$1;
    private final Fragment arg$2;

    public final boolean onPreferenceClick(Preference preference)
    {
        preference = arg$1;
        Fragment fragment = arg$2;
        ImmutableList immutablelist = CalendarApi.getColorFactory().getCalendarColorList();
        int i = immutablelist.indexOf(preference.get());
        SettingsShims.instance.showColorPicker(immutablelist, i, fragment);
        return true;
    }

    ener(Supplier supplier, Fragment fragment)
    {
        arg$1 = supplier;
        arg$2 = fragment;
    }
}
