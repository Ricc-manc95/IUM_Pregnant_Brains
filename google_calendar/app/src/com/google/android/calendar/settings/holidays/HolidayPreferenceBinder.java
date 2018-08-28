// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.holidays;

import android.content.Context;
import android.support.v14.preference.MultiSelectListPreference;
import android.support.v4.app.Fragment;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceDataStore;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.internal.AbstractMultiSelectListPreference;
import android.support.v7.view.ContextThemeWrapper;
import android.text.TextUtils;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.settings.InMemoryPreferenceDataStore;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// Referenced classes of package com.google.android.calendar.settings.holidays:
//            HolidayViewModel

public final class HolidayPreferenceBinder
{

    public final Preference color;
    public final InMemoryPreferenceDataStore dataStore;
    public final Fragment fragment;
    public final PreferenceScreen preferenceScreen;
    public HolidayViewModel viewModel;

    HolidayPreferenceBinder(Fragment fragment1, PreferenceScreen preferencescreen)
    {
        fragment = fragment1;
        preferenceScreen = preferencescreen;
        fragment1 = preferenceScreen.findPreference("color");
        if (fragment1 == null)
        {
            throw new NullPointerException();
        } else
        {
            color = (Preference)fragment1;
            dataStore = new InMemoryPreferenceDataStore();
            return;
        }
    }

    final MultiSelectListPreference createHolidayPreference(Context context, List list, Set set, String s, int i, int j, Consumer consumer)
    {
        context = new MultiSelectListPreference(new ContextThemeWrapper(context, 0x7f1400de));
        String as[] = new String[list.size()];
        String as1[] = new String[list.size()];
        HashSet hashset = new HashSet();
        for (int k = 0; k < list.size(); k++)
        {
            HolidayViewModel.HolidayCalendar holidaycalendar = (HolidayViewModel.HolidayCalendar)list.get(k);
            as[k] = holidaycalendar.getDisplayName();
            as1[k] = holidaycalendar.getId();
            if (set.contains(holidaycalendar))
            {
                hashset.add(holidaycalendar.getId());
            }
        }

        context.mEntries = as;
        context.mEntryValues = as1;
        context.setValues(hashset);
        context.setKey(s);
        dataStore.putStringSet(s, hashset);
        context.mDialogTitle = ((Preference) (context)).mContext.getString(j);
        setHolidaySummary(context, i, set);
        class .Lambda._cls3
            implements android.support.v7.preference.Preference.OnPreferenceChangeListener
        {

            private final HolidayPreferenceBinder arg$1;
            private final Consumer arg$2;
            private final MultiSelectListPreference arg$3;
            private final int arg$4;

            public final boolean onPreferenceChange(Preference preference, Object obj)
            {
                preference = arg$1;
                Consumer consumer1 = arg$2;
                MultiSelectListPreference multiselectlistpreference = arg$3;
                int l = arg$4;
                class .Lambda._cls4
                    implements Function
                {

                    private final HolidayPreferenceBinder arg$1;

                    public final Object apply(Object obj1)
                    {
                        Object obj2 = arg$1;
                        obj1 = (String)obj1;
                        obj2 = ((HolidayPreferenceBinder) (obj2)).viewModel;
                        HolidayViewModel.HolidayCalendar holidaycalendar1 = (HolidayViewModel.HolidayCalendar)((HolidayViewModel) (obj2)).countryHolidaysById.get(obj1);
                        if (holidaycalendar1 != null)
                        {
                            return holidaycalendar1;
                        } else
                        {
                            return (HolidayViewModel.HolidayCalendar)((HolidayViewModel) (obj2)).religiousHolidaysById.get(obj1);
                        }
                    }

                        .Lambda._cls4()
                        {
                            arg$1 = HolidayPreferenceBinder.this;
                        }
                }

                obj = new HashSet(new com.google.common.collect.Collections2.TransformedCollection((Set)obj, preference. new .Lambda._cls4()));
                consumer1.accept(obj);
                preference.setHolidaySummary(multiselectlistpreference, l, ((Set) (obj)));
                return true;
            }

            .Lambda._cls3(Consumer consumer, MultiSelectListPreference multiselectlistpreference, int i)
            {
                arg$1 = HolidayPreferenceBinder.this;
                arg$2 = consumer;
                arg$3 = multiselectlistpreference;
                arg$4 = i;
            }
        }

        context.mOnChangeListener = new .Lambda._cls3(consumer, context, i);
        return context;
    }

    final void setHolidaySummary(MultiSelectListPreference multiselectlistpreference, int i, Set set)
    {
        TreeSet treeset = new TreeSet();
        for (set = set.iterator(); set.hasNext(); treeset.add(((HolidayViewModel.HolidayCalendar)set.next()).getDisplayName())) { }
        if (treeset.isEmpty())
        {
            set = fragment.getContext().getString(i);
        } else
        {
            set = TextUtils.join(", ", treeset);
        }
        multiselectlistpreference.setSummary(set);
    }
}
