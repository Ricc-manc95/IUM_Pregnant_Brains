// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.reminders;

import android.accounts.Account;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.settings.home.HomeViewModel;
import com.google.android.calendar.settings.home.ReminderViewModel;
import com.google.android.calendar.settings.view.ViewUtils;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.settings.reminders:
//            RemindersBinder, RemindersFragment

final class arg._cls1
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
        ViewUtils.bindColorPreference(((Fragment) (obj2)), ((android.support.v7.preference.Preference) (obj)), new nit>(((ReminderViewModel) (obj1))), true);
    }

    (RemindersFragment remindersfragment)
    {
        arg$1 = remindersfragment;
    }
}
