// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.reminders;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.NamedCalendarColor;
import com.google.android.calendar.api.settings.GoogleSettingsModifications;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.api.settings.SettingsFactory;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.settings.SettingsFragment;
import com.google.android.calendar.settings.home.ReminderViewModel;
import com.google.android.calendar.settings.view.ViewUtils;

// Referenced classes of package com.google.android.calendar.settings.reminders:
//            RemindersBinder

public final class RemindersFragment extends SettingsFragment
    implements SingleChoiceDialogListener
{

    public RemindersBinder binder;

    public RemindersFragment()
    {
        super("reminders");
    }

    public static RemindersFragment newInstance(Account account)
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("EXTRA_ACCOUNT", account);
        account = new RemindersFragment();
        account.setArguments(bundle);
        return account;
    }

    public final void onCreatePreferences$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R9HL62TJ15TM62RJ75T9N8SJ9DPJJMAAM0()
    {
        class .Lambda._cls0
            implements Consumer
        {

            private final RemindersFragment arg$1;

            public final void accept(Object obj)
            {
                Object obj1 = arg$1;
                Object obj2 = (HomeViewModel)obj;
                ((PreferenceFragmentCompat) (obj1)).addPreferencesFromResource(0x7f09000e);
                obj1.binder = new RemindersBinder(((RemindersFragment) (obj1)), ((PreferenceFragmentCompat) (obj1)).mPreferenceManager.mPreferenceScreen);
                obj = ((RemindersFragment) (obj1)).binder;
                obj1 = (Account)((Fragment) (obj1)).getArguments().getParcelable("EXTRA_ACCOUNT");
                obj1 = (ReminderViewModel)((HomeViewModel) (obj2)).reminderViewModels.get(obj1);
                obj.viewModel = ((ReminderViewModel) (obj1));
                obj2 = ((RemindersBinder) (obj)).fragment;
                obj = ((RemindersBinder) (obj)).color;
                obj1.getClass();
                ViewUtils.bindColorPreference(((Fragment) (obj2)), ((android.support.v7.preference.Preference) (obj)), new RemindersBinder..Lambda._cls0(((ReminderViewModel) (obj1))), true);
            }

            .Lambda._cls0()
            {
                arg$1 = RemindersFragment.this;
            }
        }

        loadModel(new .Lambda._cls0());
    }

    public final void onDialogItemChosen(Object obj, int i)
    {
        Object obj1 = (NamedCalendarColor)obj;
        obj = binder;
        Object obj2 = ((RemindersBinder) (obj)).viewModel;
        com.google.android.calendar.api.color.CalendarColor calendarcolor = ((ReminderViewModel) (obj2)).color;
        if (obj1 == calendarcolor || obj1 != null && obj1.equals(calendarcolor))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            CalendarApi.Settings.update(CalendarApi.SettingsFactory.modifyGoogleSettings(((ReminderViewModel) (obj2)).settings).setTaskColor(((com.google.android.calendar.api.color.CalendarColor) (obj1))));
            obj2.color = ((com.google.android.calendar.api.color.CalendarColor) (obj1));
        }
        obj1 = ((RemindersBinder) (obj)).fragment;
        obj2 = ((RemindersBinder) (obj)).color;
        obj = ((RemindersBinder) (obj)).viewModel;
        obj.getClass();
        ViewUtils.bindColorPreference(((Fragment) (obj1)), ((android.support.v7.preference.Preference) (obj2)), new RemindersBinder..Lambda._cls1(((ReminderViewModel) (obj))), true);
    }

    public final void onStart()
    {
        super.onStart();
        setActionBarTitle(requireContext().getResources().getString(0x7f1303e2));
    }
}
