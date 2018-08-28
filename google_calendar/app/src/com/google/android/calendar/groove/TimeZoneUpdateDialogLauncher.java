// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.app.Dialog;
import android.app.backup.BackupManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.time.clock.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public final class TimeZoneUpdateDialogLauncher
{
    public static class TimeZoneUpdateDialog extends DialogFragment
    {

        public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/groove/TimeZoneUpdateDialogLauncher$TimeZoneUpdateDialog);

        static void saveCurrentTimeZoneAsLastDisplayed(Context context, String s)
        {
            if (context != null)
            {
                LogUtils.d(TAG, "Saving currentTimezone %s as last displayed.", new Object[] {
                    s
                });
                context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putString("preferences_last_display_tz", s).apply();
                (new BackupManager(context)).dataChanged();
            }
        }

        public static void show(FragmentActivity fragmentactivity, ArrayList arraylist, String s)
        {
            Bundle bundle = new Bundle(2);
            bundle.putParcelableArrayList("accountsToUpdate", arraylist);
            bundle.putString("currentTimeZoneId", s);
            arraylist = new TimeZoneUpdateDialog();
            arraylist.setArguments(bundle);
            fragmentactivity.mFragments.mHost.mFragmentManager.beginTransaction().add(arraylist, TAG).commitAllowingStateLoss();
            if (fragmentactivity != null)
            {
                arraylist = AnalyticsLoggerHolder.instance;
                if (arraylist == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)arraylist).trackEvent(fragmentactivity, "groove", "timezone_dialog_shown", "", null);
            }
        }

        public void onCancel(DialogInterface dialoginterface)
        {
            super.onCancel(dialoginterface);
            LogUtils.d(TAG, "Cancelling dialog.", new Object[0]);
            String s;
            if (super.mHost == null)
            {
                dialoginterface = null;
            } else
            {
                dialoginterface = (FragmentActivity)super.mHost.mActivity;
            }
            s = getArguments().getString("currentTimeZoneId");
            if (dialoginterface != null)
            {
                LogUtils.d(TAG, "Saving currentTimezone %s as last displayed.", new Object[] {
                    s
                });
                dialoginterface.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putString("preferences_last_display_tz", s).apply();
                (new BackupManager(dialoginterface)).dataChanged();
            }
        }

        public final Dialog onCreateDialog(final Bundle context)
        {
            final ArrayList accountsToUpdate = getArguments().getParcelableArrayList("accountsToUpdate");
            final String currentTimeZoneId = getArguments().getString("currentTimeZoneId");
            class _cls2
                implements android.content.DialogInterface.OnClickListener
            {

                private final TimeZoneUpdateDialog this$0;
                private final Context val$context;
                private final String val$currentTimeZoneId;

                public final void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface = context;
                    if (dialoginterface != null)
                    {
                        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                        if (analyticslogger == null)
                        {
                            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                        }
                        ((AnalyticsLogger)analyticslogger).trackEvent(dialoginterface, "groove", "timezone_dialog_decline", "", null);
                    }
                    dialoginterface = TimeZoneUpdateDialog.this;
                    TimeZoneUpdateDialog.saveCurrentTimeZoneAsLastDisplayed(context, currentTimeZoneId);
                }

                _cls2()
                {
                    this$0 = TimeZoneUpdateDialog.this;
                    context = context1;
                    currentTimeZoneId = s;
                    super();
                }
            }

            class _cls1
                implements android.content.DialogInterface.OnClickListener
            {

                public final TimeZoneUpdateDialog this$0;
                public final List val$accountsToUpdate;
                public final Context val$context;
                public final String val$currentTimeZoneId;

                public final void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface = context;
                    if (dialoginterface != null)
                    {
                        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                        if (analyticslogger == null)
                        {
                            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                        }
                        ((AnalyticsLogger)analyticslogger).trackEvent(dialoginterface, "groove", "timezone_dialog_accept", "", null);
                    }
                    class _cls1 extends AsyncTask
                    {

                        private final _cls1 this$1;

                        protected final Object doInBackground(Object aobj[])
                        {
                            for (aobj = accountsToUpdate.iterator(); ((Iterator) (aobj)).hasNext(); SettingsUtils.updateTimezoneSettings((Account)((Iterator) (aobj)).next(), currentTimeZoneId)) { }
                            LogUtils.d(TimeZoneUpdateDialog.TAG, "Server timezones updated.", new Object[0]);
                            return null;
                        }

                        protected final void onPostExecute(Object obj1)
                        {
                            obj1 = _fld0;
                            TimeZoneUpdateDialog.saveCurrentTimeZoneAsLastDisplayed(context, currentTimeZoneId);
                        }

                            _cls1()
                            {
                                this$1 = _cls1.this;
                                super();
                            }
                    }

                    (new _cls1()).execute(new Void[0]);
                }

                _cls1()
                {
                    this$0 = TimeZoneUpdateDialog.this;
                    context = context1;
                    accountsToUpdate = list;
                    currentTimeZoneId = s;
                    super();
                }
            }

            Object obj;
            long l;
            if (super.mHost == null)
            {
                context = null;
            } else
            {
                context = (FragmentActivity)super.mHost.mActivity;
            }
            obj = TimeZone.getTimeZone(currentTimeZoneId);
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            obj = ((TimeZone) (obj)).getDisplayName(((TimeZone) (obj)).inDaylightTime(new Date(l)), 1);
            return (new android.app.AlertDialog.Builder(context)).setTitle(requireContext().getResources().getString(0x7f130487)).setMessage(requireContext().getResources().getString(0x7f130484, new Object[] {
                obj
            })).setNegativeButton(requireContext().getResources().getString(0x7f130485), new _cls2()).setPositiveButton(requireContext().getResources().getString(0x7f130486), new _cls1()).create();
        }


        public TimeZoneUpdateDialog()
        {
        }
    }


    public static final String TAG;
    public boolean inProgress;
    public boolean onSaveInstanceStateCalled;

    public TimeZoneUpdateDialogLauncher()
    {
        inProgress = false;
    }

    static 
    {
        TAG = TimeZoneUpdateDialog.TAG;
    }
}
