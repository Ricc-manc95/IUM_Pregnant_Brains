// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.color.NamedCalendarColor;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.settings.SettingsFragment;
import com.google.android.calendar.settings.SettingsShims;
import com.google.android.calendar.settings.home.CalendarViewModel;

// Referenced classes of package com.google.android.calendar.settings.calendar:
//            CalendarPreferenceBinder

public final class CalendarPreferenceFragment extends SettingsFragment
    implements SingleChoiceDialogListener
{

    public CalendarPreferenceBinder binder;

    public CalendarPreferenceFragment()
    {
        super("calendar");
    }

    public static CalendarPreferenceFragment newInstance(CalendarDescriptor calendardescriptor, String s)
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("EXTRA_CALENDAR_DESCRIPTOR", calendardescriptor);
        bundle.putString("EXTRA_NAME", s);
        calendardescriptor = new CalendarPreferenceFragment();
        calendardescriptor.setArguments(bundle);
        return calendardescriptor;
    }

    public final void onCreatePreferences$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R9HL62TJ15TM62RJ75T9N8SJ9DPJJMAAM0()
    {
        class .Lambda._cls0
            implements Consumer
        {

            private final CalendarPreferenceFragment arg$1;

            public final void accept(Object obj)
            {
                CalendarPreferenceFragment calendarpreferencefragment = arg$1;
                obj = (HomeViewModel)obj;
                CalendarDescriptor calendardescriptor = (CalendarDescriptor)calendarpreferencefragment.getArguments().getParcelable("EXTRA_CALENDAR_DESCRIPTOR");
                obj = (CalendarViewModel)((HomeViewModel) (obj)).calendarViewModels.get(calendardescriptor);
                if (obj != null)
                {
                    calendarpreferencefragment.addPreferencesFromResource(0x7f090003);
                    calendarpreferencefragment.binder = new CalendarPreferenceBinder(calendarpreferencefragment.getContext(), calendarpreferencefragment, ((PreferenceFragmentCompat) (calendarpreferencefragment)).mPreferenceManager.mPreferenceScreen);
                    calendarpreferencefragment.binder.bind(((CalendarViewModel) (obj)));
                }
            }

            .Lambda._cls0()
            {
                arg$1 = CalendarPreferenceFragment.this;
            }
        }

        loadModel(new .Lambda._cls0());
    }

    public final void onDialogItemChosen(Object obj, int i)
    {
        obj = (NamedCalendarColor)obj;
        CalendarPreferenceBinder calendarpreferencebinder = binder;
        CalendarViewModel calendarviewmodel = calendarpreferencebinder.viewModel;
        com.google.android.calendar.api.color.CalendarColor calendarcolor = calendarviewmodel.calendarColor;
        if (obj == calendarcolor || obj != null && obj.equals(calendarcolor))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            calendarviewmodel.calendarColor = ((com.google.android.calendar.api.color.CalendarColor) (obj));
            calendarviewmodel.updateCalendar(new com.google.android.calendar.settings.home.CalendarViewModel..Lambda._cls4(((com.google.android.calendar.api.color.CalendarColor) (obj))));
        }
        calendarpreferencebinder.bindColor();
    }

    public final void onStart()
    {
        super.onStart();
        setActionBarTitle(getArguments().getString("EXTRA_NAME"));
    }

    public final boolean onStartHelp(AppCompatActivity appcompatactivity)
    {
        SettingsShims.instance.launchHelpAndFeedback(appcompatactivity, requireContext().getResources().getString(0x7f1300fa));
        return true;
    }
}
