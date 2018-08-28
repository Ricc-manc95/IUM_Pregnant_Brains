// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.Context;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.TwoStatePreference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.settings.SettingsShims;
import com.google.android.calendar.settings.home.HomeViewModel;
import com.google.android.calendar.utils.version.NycUtils;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceBinder, GeneralPreferenceFragment, GeneralPreferenceViewModel

final class arg._cls1
    implements Consumer
{

    private final GeneralPreferenceFragment arg$1;

    public final void accept(Object obj)
    {
        Object obj1 = arg$1;
        obj = (HomeViewModel)obj;
        ((PreferenceFragmentCompat) (obj1)).addPreferencesFromResource(0x7f090006);
        obj1.binder = new GeneralPreferenceBinder(((android.support.v4.app.Fragment) (obj1)), ((PreferenceFragmentCompat) (obj1)).mPreferenceManager.mPreferenceScreen);
        obj1 = ((GeneralPreferenceFragment) (obj1)).binder;
        GeneralPreferenceViewModel generalpreferenceviewmodel = ((HomeViewModel) (obj)).generalPreferenceViewModel;
        obj1.viewModel = generalpreferenceviewmodel;
        obj = ((Preference) (((GeneralPreferenceBinder) (obj1)).preferenceScreen)).mContext.getResources();
        Object obj3 = Integer.toString(generalpreferenceviewmodel.firstDayOfWeek);
        ((GeneralPreferenceBinder) (obj1)).firstDayOfWeekPreference.setValue(((String) (obj3)));
        ((GeneralPreferenceBinder) (obj1)).setFirstDayOfWeekSummary(((android.content.res.Resources) (obj)), ((String) (obj3)));
        ((GeneralPreferenceBinder) (obj1)).firstDayOfWeekPreference.mOnChangeListener = new init>(((GeneralPreferenceBinder) (obj1)), ((android.content.res.Resources) (obj)));
        ((GeneralPreferenceBinder) (obj1)).useDeviceTimezonePreference.setChecked(generalpreferenceviewmodel.useDeviceTimezone);
        ((GeneralPreferenceBinder) (obj1)).useDeviceTimezonePreference.mOnChangeListener = new init>(((GeneralPreferenceBinder) (obj1)));
        obj3 = ((GeneralPreferenceBinder) (obj1)).timezonePreference;
        obj = ((GeneralPreferenceBinder) (obj1)).viewModel;
        Object obj4 = SettingsShims.instance;
        Context context = ((GeneralPreferenceViewModel) (obj)).context;
        boolean flag;
        if (((GeneralPreferenceViewModel) (obj)).useDeviceTimezone)
        {
            obj = ((GeneralPreferenceViewModel) (obj)).deviceTimezone;
        } else
        {
            obj = ((GeneralPreferenceViewModel) (obj)).timezone;
        }
        ((Preference) (obj3)).setSummary(((SettingsShims) (obj4)).getTimezoneName(context, ((String) (obj))));
        obj = ((GeneralPreferenceBinder) (obj1)).timezonePreference;
        if (!((GeneralPreferenceBinder) (obj1)).viewModel.useDeviceTimezone)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (((Preference) (obj)).mSelectable != flag)
        {
            obj.mSelectable = flag;
            ((Preference) (obj)).notifyChanged();
        }
        ((GeneralPreferenceBinder) (obj1)).timezonePreference.mOnClickListener = new init>(((GeneralPreferenceBinder) (obj1)));
        ((GeneralPreferenceBinder) (obj1)).bindAlternateCalendar(generalpreferenceviewmodel);
        obj = ((GeneralPreferenceBinder) (obj1)).showWeekNumberPreference;
        generalpreferenceviewmodel.getClass();
        obj3 = new nit>(generalpreferenceviewmodel);
        generalpreferenceviewmodel.getClass();
        obj4 = new nit>(generalpreferenceviewmodel);
        ((TwoStatePreference) (obj)).setChecked(((Boolean)((Supplier) (obj3)).get()).booleanValue());
        obj.mOnChangeListener = new init>(((Consumer) (obj4)), ((android.support.v14.preference.SwitchPreference) (obj)), ((Supplier) (obj3)));
        obj = ((GeneralPreferenceBinder) (obj1)).showDeclinedEvents;
        generalpreferenceviewmodel.getClass();
        obj3 = new nit>(generalpreferenceviewmodel);
        generalpreferenceviewmodel.getClass();
        obj4 = new nit>(generalpreferenceviewmodel);
        ((TwoStatePreference) (obj)).setChecked(((Boolean)((Supplier) (obj3)).get()).booleanValue());
        obj.mOnChangeListener = new init>(((Consumer) (obj4)), ((android.support.v14.preference.SwitchPreference) (obj)), ((Supplier) (obj3)));
        obj = ((GeneralPreferenceBinder) (obj1)).showMoreEvents;
        flag = NycUtils.isDeviceTablet(((GeneralPreferenceBinder) (obj1)).viewModel.context);
        if (((Preference) (obj)).mVisible != flag)
        {
            obj.mVisible = flag;
            if (((Preference) (obj)).mListener != null)
            {
                ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
            }
        }
        obj = ((GeneralPreferenceBinder) (obj1)).showMoreEvents;
        generalpreferenceviewmodel.getClass();
        obj3 = new nit>(generalpreferenceviewmodel);
        generalpreferenceviewmodel.getClass();
        obj4 = new nit>(generalpreferenceviewmodel);
        ((TwoStatePreference) (obj)).setChecked(((Boolean)((Supplier) (obj3)).get()).booleanValue());
        obj.mOnChangeListener = new init>(((Consumer) (obj4)), ((android.support.v14.preference.SwitchPreference) (obj)), ((Supplier) (obj3)));
        ((GeneralPreferenceBinder) (obj1)).bindNotifications(generalpreferenceviewmodel);
        ((GeneralPreferenceBinder) (obj1)).eventDuration.mOnClickListener = new init>(((GeneralPreferenceBinder) (obj1)));
        ((GeneralPreferenceBinder) (obj1)).quickResponse.mOnClickListener = new init>(((GeneralPreferenceBinder) (obj1)));
        obj = ((GeneralPreferenceBinder) (obj1)).goals;
        flag = ((GeneralPreferenceBinder) (obj1)).viewModel.hasHabits;
        if (((Preference) (obj)).mVisible != flag)
        {
            obj.mVisible = flag;
            if (((Preference) (obj)).mListener != null)
            {
                ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
            }
        }
        obj = ((GeneralPreferenceBinder) (obj1)).goalsDisconnect;
        flag = ((GeneralPreferenceBinder) (obj1)).viewModel.hasHabits;
        if (((Preference) (obj)).mVisible != flag)
        {
            obj.mVisible = flag;
            if (((Preference) (obj)).mListener != null)
            {
                ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
            }
        }
        if (((GeneralPreferenceBinder) (obj1)).viewModel.hasHabits)
        {
            ((GeneralPreferenceBinder) (obj1)).goalsDisconnect.mOnClickListener = new init>(((GeneralPreferenceBinder) (obj1)));
        }
        obj = ((GeneralPreferenceBinder) (obj1)).flingingBluetooth;
        flag = ((GeneralPreferenceBinder) (obj1)).viewModel.showFlinging;
        if (((Preference) (obj)).mVisible != flag)
        {
            obj.mVisible = flag;
            if (((Preference) (obj)).mListener != null)
            {
                ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
            }
        }
        if (((GeneralPreferenceBinder) (obj1)).viewModel.showFlinging)
        {
            obj = ((GeneralPreferenceBinder) (obj1)).flingingBluetooth;
            Object obj2 = ((GeneralPreferenceBinder) (obj1)).viewModel;
            obj2.getClass();
            obj2 = new init>(((GeneralPreferenceViewModel) (obj2)));
            obj1 = ((GeneralPreferenceBinder) (obj1)).viewModel;
            obj1.getClass();
            obj1 = new init>(((GeneralPreferenceViewModel) (obj1)));
            ((TwoStatePreference) (obj)).setChecked(((Boolean)((Supplier) (obj2)).get()).booleanValue());
            obj.mOnChangeListener = new init>(((Consumer) (obj1)), ((android.support.v14.preference.SwitchPreference) (obj)), ((Supplier) (obj2)));
        }
    }

    (GeneralPreferenceFragment generalpreferencefragment)
    {
        arg$1 = generalpreferencefragment;
    }
}
