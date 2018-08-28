// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.view.ContextThemeWrapper;
import com.google.common.base.Supplier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.calendar.settings.home:
//            CalendarListItemViewModel, HomePreferenceFragment

final class HomePreferenceBinder
{

    public final Preference birthdayPreference;
    private final List colorUpdaters = new ArrayList();
    public final HomePreferenceFragment fragment;
    public final Preference generalPreference;
    public final Preference holidayPreference;
    public final Preference more;
    public final Preference smartMailPreference;

    HomePreferenceBinder(HomePreferenceFragment homepreferencefragment)
    {
        fragment = homepreferencefragment;
        Preference preference = homepreferencefragment.findPreference(homepreferencefragment.requireContext().getResources().getString(0x7f130307));
        if (preference == null)
        {
            throw new NullPointerException();
        }
        generalPreference = (Preference)preference;
        preference = homepreferencefragment.findPreference(homepreferencefragment.requireContext().getResources().getString(0x7f130308));
        if (preference == null)
        {
            throw new NullPointerException();
        }
        holidayPreference = (Preference)preference;
        preference = fragment.findPreference(homepreferencefragment.requireContext().getResources().getString(0x7f130306));
        if (preference == null)
        {
            throw new NullPointerException();
        }
        birthdayPreference = (Preference)preference;
        preference = homepreferencefragment.findPreference(homepreferencefragment.requireContext().getResources().getString(0x7f130309));
        if (preference == null)
        {
            throw new NullPointerException();
        }
        smartMailPreference = (Preference)preference;
        homepreferencefragment = homepreferencefragment.findPreference("more");
        if (homepreferencefragment == null)
        {
            throw new NullPointerException();
        } else
        {
            more = (Preference)homepreferencefragment;
            return;
        }
    }

    final void addColorUpdater(Preference preference, Supplier supplier)
    {
        class .Lambda._cls9
            implements Runnable
        {

            private final HomePreferenceBinder arg$1;
            private final Supplier arg$2;
            private final AtomicReference arg$3;
            private final Preference arg$4;

            public final void run()
            {
                Object obj = arg$1;
                Object obj1 = arg$2;
                Object obj2 = arg$3;
                Preference preference1 = arg$4;
                obj1 = (EntityColor)((Supplier) (obj1)).get();
                obj2 = (EntityColor)((AtomicReference) (obj2)).getAndSet(obj1);
                if (obj2 == null || ((EntityColor) (obj2)).getValue() != ((EntityColor) (obj1)).getValue())
                {
                    obj = ((HomePreferenceBinder) (obj)).fragment.requireContext().getResources();
                    int i = ((EntityColor) (obj1)).getValue();
                    preference1.setIcon(new InsetDrawable((new ColorCircle(((Resources) (obj)), 0x7f0e00af)).setColor(i), ((Resources) (obj)).getDimensionPixelOffset(0x7f0e00b0)));
                }
            }

            .Lambda._cls9(Supplier supplier, AtomicReference atomicreference, Preference preference)
            {
                arg$1 = HomePreferenceBinder.this;
                arg$2 = supplier;
                arg$3 = atomicreference;
                arg$4 = preference;
            }
        }

        preference = new .Lambda._cls9(supplier, new AtomicReference(), preference);
        preference.run();
        colorUpdaters.add(preference);
    }

    final void addPreferenceForCalendar(PreferenceCategory preferencecategory, CalendarListItemViewModel calendarlistitemviewmodel)
    {
        Object obj = ((Preference) (preferencecategory)).mPreferenceManager;
        PreferenceScreen preferencescreen = new PreferenceScreen(new ContextThemeWrapper(((Preference) (preferencecategory)).mContext, 0x7f1400de), null);
        preferencescreen.onAttachedToHierarchy(((android.support.v7.preference.PreferenceManager) (obj)));
        obj = fragment.requireContext().getResources();
        calendarlistitemviewmodel.getClass();
        class .Lambda._cls7
            implements Supplier
        {

            private final CalendarListItemViewModel arg$1;

            public final Object get()
            {
                return arg$1.getColor();
            }

            .Lambda._cls7(CalendarListItemViewModel calendarlistitemviewmodel)
            {
                arg$1 = calendarlistitemviewmodel;
            }
        }

        addColorUpdater(preferencescreen, new .Lambda._cls7(calendarlistitemviewmodel));
        preferencescreen.setTitle(calendarlistitemviewmodel.getDisplayName(((Resources) (obj))));
        class .Lambda._cls8
            implements android.support.v7.preference.Preference.OnPreferenceClickListener
        {

            private final HomePreferenceBinder arg$1;
            private final CalendarListItemViewModel arg$2;
            private final Resources arg$3;

            public final boolean onPreferenceClick(Preference preference)
            {
                Resources resources;
                Object obj1;
                preference = arg$1;
                obj1 = arg$2;
                resources = arg$3;
                if (!(obj1 instanceof CalendarViewModel)) goto _L2; else goto _L1
_L1:
                obj1 = (CalendarViewModel)obj1;
                preference.showFragment(CalendarPreferenceFragment.newInstance(((CalendarViewModel) (obj1)).calendar.getDescriptor(), (String)((CalendarViewModel) (obj1)).getDisplayName(resources)));
_L4:
                return true;
_L2:
                if (obj1 instanceof ReminderViewModel)
                {
                    preference.showFragment(RemindersFragment.newInstance(((ReminderViewModel)obj1).settings.getDescriptor()));
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            .Lambda._cls8(CalendarListItemViewModel calendarlistitemviewmodel, Resources resources)
            {
                arg$1 = HomePreferenceBinder.this;
                arg$2 = calendarlistitemviewmodel;
                arg$3 = resources;
            }
        }

        preferencescreen.mOnClickListener = new .Lambda._cls8(calendarlistitemviewmodel, ((Resources) (obj)));
        preferencecategory.addPreference(preferencescreen);
    }

    final void showFragment(Fragment fragment1)
    {
        ((Fragment) (fragment)).mFragmentManager.beginTransaction().addToBackStack(null).replace(0x1020002, fragment1).setTransition(4099).commit();
    }

    final void updateColors()
    {
        for (Iterator iterator = colorUpdaters.iterator(); iterator.hasNext(); ((Runnable)iterator.next()).run()) { }
    }
}
