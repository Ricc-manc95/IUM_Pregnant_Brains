// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timely.store.AccountSettings;
import com.google.android.apps.calendar.timely.store.AccountSettingsStore;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.task.ArpTaskDateTimeInCalendar;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.android.calendar.utils.notification.NotificationChannels;
import com.google.android.calendar.utils.notification.NotificationPrefs;
import com.google.android.calendar.utils.notification.NotificationUtil;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.android.calendar.widget.WidgetUtils;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.RecurrenceInfo;
import com.google.android.gms.reminders.model.ReminderEvent;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.alerts:
//            TaskAlertsManager, TaskAlertsFactory, TaskAlertsActionService, AlertUtils

public class TasksListenerHelper
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/alerts/TasksListenerHelper);

    public TasksListenerHelper()
    {
    }

    private static String formatReminderEventType(ReminderEvent reminderevent)
    {
        int i = reminderevent.getType();
        if (i == 2)
        {
            return "deleted";
        }
        if (i == 1)
        {
            return "changed";
        } else
        {
            return "unknown";
        }
    }

    private static String formatTask(Context context, Task task)
    {
        String s1 = task.getTitle();
        String s2 = task.getTaskId().getClientAssignedId();
        com.google.android.gms.reminders.model.Location location = task.getLocation();
        Boolean boolean1 = task.getArchived();
        Boolean boolean2 = task.getSnoozed();
        String s;
        if (task.getDueDate() != null && !task.getDueDate().getUnspecifiedFutureTime().booleanValue())
        {
            long l;
            if (task.getDueDate() != null)
            {
                l = ArpTaskDateTimeInCalendar.getDueTimeMillis(task.getDueDate(), new DateTimeService(context));
            } else
            {
                l = 0L;
            }
            context = DateTimeFormatHelper.instance;
            if (context == null)
            {
                throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
            }
            s = ((DateTimeFormatHelper)context).getTimeRangeText(l, l, 0);
        } else
        {
            s = "null";
        }
        if (task.getRecurrenceInfo() != null)
        {
            if (task.getRecurrenceInfo().getMaster().booleanValue())
            {
                context = "Master";
            } else
            {
                context = "inst";
            }
        } else
        {
            context = "null";
        }
        return String.format("\"%s\"[%s], loc=%s arch=%s snooze=%s due=%s r=%s", new Object[] {
            s1, s2, location, boolean1, boolean2, s, context
        });
    }

    public static void handleReminderFired(Context context, TaskAlertsManager taskalertsmanager, Task task, String s, String s1, boolean flag)
    {
        if (AndroidPermissionUtils.hasMandatoryPermissions(context)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_alerts", true))
        {
            break; /* Loop/switch isn't completed */
        }
        if (flag)
        {
            LogUtils.d(TAG, "alert preference is OFF", new Object[0]);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (TimelyStore.acquire(context).accountSettingsStore.getSettings(s1).tasksVisible)
        {
            break; /* Loop/switch isn't completed */
        }
        if (flag)
        {
            LogUtils.d(TAG, "Visibility for account %s is OFF", new Object[] {
                s1
            });
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
        DateTime datetime = task.getDueDate();
        if (datetime == null || !ArpTaskDateTimeInCalendar.isDueAllDay(datetime))
        {
            break; /* Loop/switch isn't completed */
        }
        if (flag)
        {
            LogUtils.d(TAG, "ignoring all-day reminder", new Object[0]);
            return;
        }
        if (true) goto _L1; else goto _L5
_L5:
        if (flag)
        {
            LogUtils.d(TAG, " notify for event %s: acct=%s %s", new Object[] {
                s, s1, formatTask(context, task)
            });
        }
        if (TaskAlertsManager.DEBUG)
        {
            LogUtils.d(TaskAlertsManager.TAG, "  notify", new Object[0]);
        }
        int j = task.getTaskId().getClientAssignedId().hashCode();
        s = taskalertsmanager.context;
        LogUtils.d(TaskAlertsFactory.TAG, "  buildSingleNotification %s", new Object[] {
            task.getTitle()
        });
        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(s);
        Resources resources = s.getResources();
        flag = RtlUtils.isLayoutDirectionRtl(s);
        String s2 = RtlUtils.forceTextAlignment(task.getTitle(), flag);
        Object obj = RtlUtils.forceTextAlignment(s1, flag);
        builder.mPriority = 2;
        android.support.v4.app.NotificationCompat.Builder builder1 = builder.setContentTitle(s2);
        builder1.mNotification.icon = 0x7f020229;
        builder1.mColor = resources.getColor(TaskAlertsFactory.COLOR);
        boolean flag1;
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            context = "reminder";
        } else
        {
            context = "event";
        }
        builder1.mCategory = context;
        builder1.setStyle((new android.support.v4.app.NotificationCompat.BigTextStyle()).bigText(((CharSequence) (obj))));
        context = new Intent();
        context.setClass(s, com/google/android/calendar/alerts/TaskAlertsActionService);
        context.setAction("com.google.android.calendar.intent.action.SHOW_CONTENT");
        context.putExtra("intent.extra.account_name", s1);
        context.putExtra("intent.extra.client_id", task.getTaskId().getClientAssignedId());
        context.putExtra("intent.extra.notification_id", j);
        context.setData(ContentUris.withAppendedId(com.google.android.apps.calendar.timely.contract.TimelyContract.Alerts.CONTENT_URI, j));
        builder.mContentIntent = PendingIntent.getService(s, 0, context, 0x8000000);
        context = new Intent();
        context.setClass(s, com/google/android/calendar/alerts/TaskAlertsActionService);
        context.setAction("com.google.android.calendar.intent.action.ALERT_DISMISS");
        context.putExtra("intent.extra.account_name", s1);
        context.putExtra("intent.extra.client_id", task.getTaskId().getClientAssignedId());
        context.putExtra("intent.extra.notification_id", j);
        context.setData(ContentUris.withAppendedId(com.google.android.apps.calendar.timely.contract.TimelyContract.Alerts.CONTENT_URI, j));
        context = PendingIntent.getService(s, 0, context, 0x8000000);
        builder.mNotification.deleteIntent = context;
        context = s.getString(0x7f130473);
        obj = new Intent();
        ((Intent) (obj)).setClass(s, com/google/android/calendar/alerts/TaskAlertsActionService);
        ((Intent) (obj)).setAction("com.google.android.calendar.intent.action.MARK_TASK_AS_DONE");
        ((Intent) (obj)).putExtra("intent.extra.account_name", s1);
        ((Intent) (obj)).putExtra("intent.extra.client_id", task.getTaskId().getClientAssignedId());
        ((Intent) (obj)).putExtra("intent.extra.notification_id", j);
        ((Intent) (obj)).setData(ContentUris.withAppendedId(com.google.android.apps.calendar.timely.contract.TimelyContract.Alerts.CONTENT_URI, j));
        s1 = PendingIntent.getService(s, 0, ((Intent) (obj)), 0x8000000);
        builder.mActions.add(new android.support.v4.app.NotificationCompat.Action(0x7f0201e4, context, s1));
        context = task.getDueDate();
        if (context != null)
        {
            long l = ArpTaskDateTimeInCalendar.getDueTimeMillis(context, new DateTimeService(s));
            int i;
            if (ArpTaskDateTimeInCalendar.isDueAllDay(context))
            {
                builder.setTicker(s2);
            } else
            {
                context = DateTimeFormatHelper.instance;
                if (context == null)
                {
                    throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
                }
                context = RtlUtils.forceTextAlignment(((DateTimeFormatHelper)context).getTimeRangeText(l, l, 0), flag);
                builder.setContentText(context);
                builder.setTicker(resources.getString(0x7f13035e, new Object[] {
                    s2, context
                }));
            }
        }
        task = new NotificationPrefs(s, s.getSharedPreferences("com.google.android.calendar_preferences", 0));
        i = AlertUtils.getNotificationDefaults(task);
        builder.mNotification.defaults = i;
        if ((i & 4) != 0)
        {
            context = builder.mNotification;
            context.flags = ((Notification) (context)).flags | 1;
        }
        if (((NotificationPrefs) (task)).silenced)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            context = null;
        } else
        {
            if (((NotificationPrefs) (task)).ringtone == null)
            {
                task.ringtone = PreferencesUtils.getRingtonePreference(((NotificationPrefs) (task)).context);
            }
            context = ((NotificationPrefs) (task)).ringtone;
        }
        task.silenced = true;
        if (!TextUtils.isEmpty(context))
        {
            builder.setSound(Uri.parse(context));
        }
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            NotificationChannels.initialize(s, NotificationChannels.channelsCreated);
            builder.mChannelId = "REMINDERS";
        }
        context = builder.build();
        if (context != null)
        {
            NotificationUtil.notify(taskalertsmanager.notificationManager, "tasks", j, context);
            return;
        }
        if (true) goto _L1; else goto _L6
_L6:
    }

    public static void handleRemindersChanged$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFC5M6ASJKECNL8OBJDD0MOPBIEHPKQOBEC5JMASHR9HL62TJ15TQN8QBC5T66ISRK7DD2ILG_0(Context context, List list, boolean flag)
    {
        ArrayList arraylist = new ArrayList();
        list = list.iterator();
        do
        {
            if (list.hasNext())
            {
                ReminderEvent reminderevent = (ReminderEvent)list.next();
                Task task = reminderevent.getTask();
                String s = reminderevent.getAccountName();
                Integer integer = task.getTaskList();
                if (integer == null || integer.intValue() != 7)
                {
                    if (flag)
                    {
                        LogUtils.v(TAG, "Skipping event %s Account=%s ClientAssignedId=%s TaskList=%s", new Object[] {
                            formatReminderEventType(reminderevent), s, task.getTaskId().getClientAssignedId(), integer
                        });
                    }
                } else
                {
                    DateTime datetime = task.getDueDate();
                    if (flag)
                    {
                        try
                        {
                            LogUtils.d(TAG, " event %s: acct=%s %s", new Object[] {
                                formatReminderEventType(reminderevent), s, formatTask(context, task)
                            });
                        }
                        catch (Exception exception)
                        {
                            boolean flag1;
                            if (datetime != null)
                            {
                                flag1 = true;
                            } else
                            {
                                flag1 = false;
                            }
                            if (flag1)
                            {
                                LogUtils.w(TAG, " event %s acct=%s ClientAssignedId=%s loc=%s", new Object[] {
                                    formatReminderEventType(reminderevent), s, task.getTaskId().getClientAssignedId(), task.getLocation()
                                });
                                LogUtils.w(TAG, "  Title=\"%s\"", new Object[] {
                                    task.getTitle()
                                });
                                LogUtils.w(TAG, "  TaskList=%s", new Object[] {
                                    task.getTaskList()
                                });
                                LogUtils.w(TAG, "  AllDay=%s, Time=%s, Period=%s", new Object[] {
                                    datetime.getAllDay(), datetime.getTime(), datetime.getPeriod()
                                });
                                LogUtils.w(TAG, "  Archived=%s, ArchivedTimeMs=%s", new Object[] {
                                    task.getArchived(), task.getArchivedTimeMs()
                                });
                                LogUtils.w(TAG, "  Deleted=%s, Pinned=%s, Snoozed=%s", new Object[] {
                                    task.getDeleted(), task.getPinned(), task.getSnoozed()
                                });
                                LogUtils.w(TAG, "  EventDate=%s, FiredTimeMs=%s, SnoozedTimeMs=%s", new Object[] {
                                    task.getEventDate(), task.getFiredTimeMillis(), task.getSnoozedTimeMillis()
                                });
                                LogUtils.w(TAG, "  UnspecifiedFutureTime=%s", new Object[] {
                                    datetime.getUnspecifiedFutureTime()
                                });
                                LogUtils.wtf(TAG, exception, "  Year=%s, Month=%s, Day=%s", new Object[] {
                                    datetime.getYear(), datetime.getMonth(), datetime.getDay()
                                });
                            } else
                            {
                                LogUtils.wtf(TAG, exception, " event %s acct=%s id=%s loc=%s", new Object[] {
                                    formatReminderEventType(reminderevent), s, task.getTaskId().getClientAssignedId(), task.getLocation()
                                });
                            }
                        }
                    }
                    if (reminderevent.getType() == 2 || Boolean.TRUE.equals(task.getArchived()))
                    {
                        arraylist.add((Task)task.freeze());
                    }
                    if (reminderevent.getType() == 1 && datetime != null && Boolean.TRUE.equals(task.getSnoozed()))
                    {
                        arraylist.add((Task)task.freeze());
                    }
                }
            } else
            {
                list = WidgetUtils.getWidgetTaskChanged(context);
                if (list != null)
                {
                    list = (Intent)(new Intent(list)).clone();
                    list.setPackage(context.getPackageName());
                    context.sendBroadcast(list);
                }
                return;
            }
        } while (true);
    }

}
