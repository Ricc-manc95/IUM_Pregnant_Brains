// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.InsetDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v7.preference.Preference;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.color.NamedCalendarColor;
import com.google.android.calendar.common.drawable.ColorCircle;
import com.google.common.base.Supplier;

public final class ViewUtils
{

    public static void bindColorPreference(Fragment fragment, Preference preference, Supplier supplier, boolean flag)
    {
        Object obj1 = null;
        CalendarColor calendarcolor = (CalendarColor)supplier.get();
        class .Lambda._cls0
            implements android.support.v7.preference.Preference.OnPreferenceClickListener
        {

            private final Supplier arg$1;
            private final Fragment arg$2;

            public final boolean onPreferenceClick(Preference preference1)
            {
                preference1 = arg$1;
                Fragment fragment1 = arg$2;
                ImmutableList immutablelist = CalendarApi.getColorFactory().getCalendarColorList();
                int j = immutablelist.indexOf(preference1.get());
                SettingsShims.instance.showColorPicker(immutablelist, j, fragment1);
                return true;
            }

            .Lambda._cls0(Supplier supplier, Fragment fragment)
            {
                arg$1 = supplier;
                arg$2 = fragment;
            }
        }

        Object obj;
        int i;
        if (calendarcolor instanceof NamedCalendarColor)
        {
            obj = (NamedCalendarColor)calendarcolor;
            obj = fragment.requireContext().getResources().getStringArray(((NamedCalendarColor) (obj)).getNamesArray())[((NamedCalendarColor) (obj)).getNameIndex()];
        } else
        {
            if (fragment.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)fragment.mHost.mActivity;
            }
            obj = ((FragmentActivity) (obj)).getString(0x7f1303a8);
        }
        preference.setSummary(((CharSequence) (obj)));
        obj = fragment.requireContext().getResources();
        i = calendarcolor.getValue();
        preference.setIcon(new InsetDrawable((new ColorCircle(((Resources) (obj)), 0x7f0e00af)).setColor(i), ((Resources) (obj)).getDimensionPixelOffset(0x7f0e00b0)));
        if (preference.mVisible != flag)
        {
            preference.mVisible = flag;
            if (preference.mListener != null)
            {
                preference.mListener.onPreferenceVisibilityChange(preference);
            }
        }
        obj = obj1;
        if (flag)
        {
            obj = new .Lambda._cls0(supplier, fragment);
        }
        preference.mOnClickListener = ((android.support.v7.preference.Preference.OnPreferenceClickListener) (obj));
    }
}
