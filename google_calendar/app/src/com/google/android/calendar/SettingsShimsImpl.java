// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.accounts.Account;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.format.Time;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import com.android.timezonepicker.TimeZonePickerUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListEntryModifications;
import com.google.android.calendar.api.calendarlist.CalendarListFactory;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.belong.BelongUtils;
import com.google.android.calendar.event.CustomNotificationBaseDialog;
import com.google.android.calendar.event.CustomNotificationSupportDialog;
import com.google.android.calendar.event.ReminderUtils;
import com.google.android.calendar.event.edit.CalendarNotificationSet;
import com.google.android.calendar.newapi.segment.color.SingleChoiceColorDialog;
import com.google.android.calendar.settings.SettingsShims;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.BirthdayManager;
import com.google.android.calendar.timely.GoogleFeedbackUtils;
import com.google.android.calendar.timely.settings.DirectoryLoader;
import com.google.android.calendar.timely.settings.RecentAndDefaultNotificationsFactory;
import com.google.calendar.v2.client.service.api.common.Reminder;
import com.google.calendar.v2.client.service.api.time.Duration;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar:
//            AccountUtils, Utils

public final class SettingsShimsImpl extends SettingsShims
{

    public SettingsShimsImpl()
    {
    }

    public final void broadcastWidgetUpdate(Context context)
    {
        Intent intent = new Intent(String.valueOf(context.getPackageName()).concat(".APPWIDGET_SCHEDULED_UPDATE"));
        intent.setDataAndType(CalendarContract.CONTENT_URI, "vnd.android.data/update");
        intent = (Intent)intent.clone();
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }

    public final DialogFragment createCustomNotificationDialog(Context context, final com.google.android.calendar.settings.SettingsShims.CustomNotificationListener listener, boolean flag, boolean flag1)
    {
        String s;
        Bundle bundle;
        int i;
        if (flag1)
        {
            i = 0x7f1301a7;
        } else
        {
            i = 0x7f1301a6;
        }
        s = context.getString(i);
        if (!flag1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        context = new CustomNotificationSupportDialog();
        context.mCancelable = true;
        if (((DialogFragment) (context)).mDialog != null)
        {
            ((DialogFragment) (context)).mDialog.setCancelable(true);
        }
        bundle = new Bundle();
        bundle.putBoolean("all_day", flag);
        bundle.putString("allowed_reminders", s);
        bundle.putBoolean("allow_notifications_after_event", flag1);
        context.setArguments(bundle);
        listener = new _cls1();
        ((CustomNotificationSupportDialog) (context)).dialog.listener = listener;
        return context;
    }

    public final void disableFitIntegration(Context context)
    {
        BelongUtils.startIntegrationDisabling(context);
    }

    public final String formatNotification(Context context, Notification notification, boolean flag)
    {
        return (new ReminderUtils(context)).constructLabel(notification.minutesBefore, notification.method, flag);
    }

    public final List getNotificationOptions(Context context, CalendarDescriptor calendardescriptor, boolean flag)
    {
        Object obj;
        if (RecentAndDefaultNotificationsFactory.instance == null)
        {
            RecentAndDefaultNotificationsFactory.instance = new RecentAndDefaultNotificationsFactory();
        }
        obj = RecentAndDefaultNotificationsFactory.instance;
        Account account = calendardescriptor.account;
        com.google.android.calendar.api.calendarlist.CalendarKey calendarkey = calendardescriptor.calendarKey;
        ArrayList arraylist = new ArrayList();
        calendardescriptor = new CalendarNotificationSet();
        calendardescriptor.allDayOptions = ReminderUtils.reminderEntriesToReminders(((RecentAndDefaultNotificationsFactory) (obj)).getRecentNotificationOptions(context, account, true));
        calendardescriptor.timedOptions = ReminderUtils.reminderEntriesToReminders(((RecentAndDefaultNotificationsFactory) (obj)).getRecentNotificationOptions(context, account, false));
        calendardescriptor.allDayNotifications = ReminderUtils.reminderEntriesToReminders(((RecentAndDefaultNotificationsFactory) (obj)).getDefaultNotifications(context, calendarkey, account, true));
        calendardescriptor.timedNotifications = ReminderUtils.reminderEntriesToReminders(((RecentAndDefaultNotificationsFactory) (obj)).getDefaultNotifications(context, calendarkey, account, false));
        if (flag)
        {
            arraylist.addAll(((CalendarNotificationSet) (calendardescriptor)).allDayNotifications);
        } else
        {
            arraylist.addAll(((CalendarNotificationSet) (calendardescriptor)).timedNotifications);
        }
        context = ((CalendarNotificationSet) (calendardescriptor)).timedNotifications.iterator();
        do
        {
            if (!context.hasNext())
            {
                break;
            }
            obj = (Reminder)context.next();
            if (!((CalendarNotificationSet) (calendardescriptor)).timedOptions.contains(obj))
            {
                ((CalendarNotificationSet) (calendardescriptor)).timedOptions.add(obj);
            }
        } while (true);
        context = ((CalendarNotificationSet) (calendardescriptor)).allDayNotifications.iterator();
        do
        {
            if (!context.hasNext())
            {
                break;
            }
            obj = (Reminder)context.next();
            if (!((CalendarNotificationSet) (calendardescriptor)).allDayOptions.contains(obj))
            {
                ((CalendarNotificationSet) (calendardescriptor)).allDayOptions.add(obj);
            }
        } while (true);
        Collections.sort(((CalendarNotificationSet) (calendardescriptor)).allDayNotifications, calendardescriptor);
        Collections.sort(((CalendarNotificationSet) (calendardescriptor)).allDayOptions, calendardescriptor);
        Collections.sort(((CalendarNotificationSet) (calendardescriptor)).timedNotifications, calendardescriptor);
        Collections.sort(((CalendarNotificationSet) (calendardescriptor)).timedOptions, calendardescriptor);
        if (flag)
        {
            context = ((CalendarNotificationSet) (calendardescriptor)).allDayOptions;
        } else
        {
            context = ((CalendarNotificationSet) (calendardescriptor)).timedOptions;
        }
        calendardescriptor = new ArrayList(context.size());
        context = context.iterator();
_L2:
        int i;
        if (!context.hasNext())
        {
            break MISSING_BLOCK_LABEL_452;
        }
        obj = (Reminder)context.next();
        switch (((Reminder) (obj)).deliveryMethod.ordinal())
        {
        default:
            throw new IllegalStateException();

        case 1: // '\001'
            break; /* Loop/switch isn't completed */

        case 2: // '\002'
            break MISSING_BLOCK_LABEL_446;

        case 0: // '\0'
            i = 1;
            break;
        }
_L3:
        calendardescriptor.add(new Notification(i, (int)TimeUnit.MINUTES.convert(((Reminder) (obj)).before.millis, TimeUnit.MILLISECONDS)));
        if (true) goto _L2; else goto _L1
_L1:
        i = 2;
          goto _L3
        i = 3;
          goto _L3
        return calendardescriptor;
    }

    public final String getTimezone(Context context, boolean flag)
    {
        if (flag)
        {
            return Time.getCurrentTimezone();
        } else
        {
            return context.getSharedPreferences("com.google.android.calendar_preferences", 0).getString("preferences_home_tz", Time.getCurrentTimezone());
        }
    }

    public final CharSequence getTimezoneName(Context context, String s)
    {
        int i;
        int j = 0;
        TimeZonePickerUtils timezonepickerutils = new TimeZonePickerUtils(context.getApplicationContext());
        context = context.getApplicationContext();
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        s = TimeZone.getTimeZone(s);
        if (s == null)
        {
            return null;
        }
        Object obj = Locale.getDefault();
        if (!((Locale) (obj)).equals(timezonepickerutils.defaultLocale))
        {
            timezonepickerutils.defaultLocale = ((Locale) (obj));
            context = context.getResources();
            timezonepickerutils.overrideIds = context.getStringArray(0x7f0b0055);
            timezonepickerutils.overrideLabels = context.getStringArray(0x7f0b0056);
        }
        context = new Time(s.getID());
        context.set(l);
        obj = new StringBuilder();
        boolean flag;
        if (((Time) (context)).isDst != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (timezonepickerutils.overrideIds == null || timezonepickerutils.overrideLabels == null)
        {
            context = s.getDisplayName(flag, 1, Locale.getDefault());
        } else
        {
label0:
            {
                int k;
                for (i = 0; i >= timezonepickerutils.overrideIds.length; i++)
                {
                    break MISSING_BLOCK_LABEL_398;
                }

                if (!s.getID().equals(timezonepickerutils.overrideIds[i]))
                {
                    break MISSING_BLOCK_LABEL_412;
                }
                if (timezonepickerutils.overrideLabels.length <= i)
                {
                    break label0;
                }
                context = timezonepickerutils.overrideLabels[i];
            }
        }
        ((StringBuilder) (obj)).append(context);
        ((StringBuilder) (obj)).append("  ");
        i = s.getOffset(l);
        ((StringBuilder) (obj)).length();
        TimeZonePickerUtils.appendGmtOffset(((StringBuilder) (obj)), i);
        ((StringBuilder) (obj)).length();
        if (s.useDaylightTime())
        {
            ((StringBuilder) (obj)).append(" ");
            i = ((StringBuilder) (obj)).length();
            ((StringBuilder) (obj)).append('\u2600');
            j = ((StringBuilder) (obj)).length();
        } else
        {
            i = 0;
        }
        context = TimeZonePickerUtils.spannableFactory.newSpannable(((CharSequence) (obj)));
        if (s.useDaylightTime())
        {
            context.setSpan(new ForegroundColorSpan(0xffbfbfbf), i, j, 33);
        }
        return context;
        i = timezonepickerutils.overrideIds.length;
        k = timezonepickerutils.overrideLabels.length;
        Log.e("TimeZonePickerUtils", (new StringBuilder(74)).append("timezone_rename_ids len=").append(i).append(" timezone_rename_labels len=").append(k).toString());
        context = s.getDisplayName(flag, 1, Locale.getDefault());
        break MISSING_BLOCK_LABEL_164;
    }

    public final ListenableFuture hasHabitsWithEnabledIntegration$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T66ISRKCLN62OJCCL37AT3LE9IJMAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQCD5PN8PBEC5H6OPA6ELQ7ASJ57C______0(ListenableFuture listenablefuture)
    {
        return BelongUtils.hasHabitsWithFitIntegrationAsync(listenablefuture);
    }

    public final boolean isSmartMailAccount(Context context, Account account)
    {
        return AccountUtils.doesAccountSupportSmartmail(context, account);
    }

    public final void launchHelpAndFeedback(Activity activity, String s)
    {
        GoogleFeedbackUtils.launchHelpAndFeedback(activity, s, null);
    }

    public final com.google.calendar.v2.libs.proto.DirectoryProto.Directory loadDirectory(Context context)
    {
        Locale locale = Locale.getDefault();
        com.google.calendar.v2.libs.proto.DirectoryProto.Directory directory1 = DirectoryLoader.directoryForLocale(context, locale.toString());
        com.google.calendar.v2.libs.proto.DirectoryProto.Directory directory = directory1;
        if (directory1 == null)
        {
            directory = DirectoryLoader.directoryForLocale(context, locale.getLanguage().toLowerCase());
        }
        return directory;
    }

    public final void manageNotificationChannel$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEDIN8T39DPJN6BQJCLQ78QBECTPL6Q39DLPI8JJFEHKMCQB3C5Q6IRRE8DK62RJECLM3MAAM0(Context context, int i)
    {
        i - 1;
        JVM INSTR tableswitch 1 1: default 20
    //                   1 65;
           goto _L1 _L2
_L1:
        String s = "REMINDERS";
_L4:
        Intent intent = new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.CHANNEL_ID", s);
        intent.putExtra("android.provider.extra.APP_PACKAGE", "com.google.android.calendar");
        context.startActivity(intent);
        return;
_L2:
        s = "DEBUG";
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void setAlternateCalendar(Context context, int i)
    {
        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putInt("preferences_alternate_calendar", i).apply();
        broadcastWidgetUpdate(context);
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "alternate_calendar", "update_setting", "type", Long.valueOf(i));
            return;
        }
    }

    public final void setBirthdayColor(Context context, CalendarColor calendarcolor)
    {
        boolean flag = false;
        Object obj = context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit();
        if (CalendarApi.colorFactory != null)
        {
            flag = true;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Must initialize API first."));
        }
        ((android.content.SharedPreferences.Editor) (obj)).putInt("preferences_birthdays_color", CalendarApi.colorFactory.getBirthdayColorKey(calendarcolor)).apply();
        obj = CalendarListEntryCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        context = BirthdayManager.getPrimaryBirthdayCalendar(context, (com.google.android.calendar.api.calendarlist.CalendarListEntry[])((ListenableFutureCache)obj).result);
        if (context != null)
        {
            CalendarApi.CalendarList.update(CalendarApi.CalendarListFactory.modifyCalendarListEntry(context).setColor(calendarcolor));
        }
    }

    public final void setTimezone(Context context, String s)
    {
        Utils.setTimeZone(context, s);
        broadcastWidgetUpdate(context);
    }

    public final boolean shouldShowFlingingSettings(Context context)
    {
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)context).flinging();
            return false;
        }
    }

    public final void showColorPicker(ImmutableList immutablelist, int i, Fragment fragment)
    {
        SingleChoiceColorDialog.newInstance(immutablelist, i, true, fragment).show(fragment.mFragmentManager, "");
    }

    private class _cls1
        implements com.google.android.calendar.event.CustomNotificationDialog.OnNotificationSetListener
    {

        private final com.google.android.calendar.settings.SettingsShims.CustomNotificationListener val$listener;

        public final void onCancel()
        {
        }

        public final void onCustomNotificationSet(int i, int j)
        {
            listener.onSet(i, j);
        }

        _cls1()
        {
            listener = customnotificationlistener;
            super();
        }
    }

}
