// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.EventsApiImpl;
import com.google.android.apps.calendar.timebox.GoalItem;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timely.store.PreferredNotification;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitContract;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.event.ReminderUtils;
import com.google.android.calendar.groove.stats.AutoValue_GrooveStats;
import com.google.android.calendar.groove.stats.GrooveStats;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.calendar.utils.network.NetworkUtil;
import com.google.android.syncadapters.calendar.Utilities;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public class GrooveUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/groove/GrooveUtils);

    public GrooveUtils()
    {
    }

    public static boolean allOrNoPreferredTimesSelected(HabitContract habitcontract)
    {
        while (habitcontract.isMorningPreferable() && habitcontract.isAfternoonPreferable() && habitcontract.isEveningPreferable() || !habitcontract.isMorningPreferable() && !habitcontract.isAfternoonPreferable() && !habitcontract.isEveningPreferable()) 
        {
            return true;
        }
        return false;
    }

    public static Bitmap decodeScaledBitmapFromResource(Context context, int i, int j, int k)
    {
        int i1 = 1;
        int l = 1;
        Resources resources = context.getResources();
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        int j1 = options.outHeight;
        int k1 = options.outWidth;
        if (j1 > k || k1 > j)
        {
            j1 /= 2;
            k1 /= 2;
            do
            {
                i1 = l;
                if (j1 / l <= k)
                {
                    break;
                }
                i1 = l;
                if (k1 / l <= j)
                {
                    break;
                }
                l <<= 1;
            } while (true);
        }
        options.inSampleSize = i1;
        long l1 = options.inSampleSize;
        if (context != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "groove", "decode_image", "sample_size", Long.valueOf(l1));
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static int getDefaultReminderMinutes(Context context, Account account, String s)
    {
        CalendarKey calendarkey = getLocalCalendarId(account, s);
        s = null;
        if (calendarkey != null)
        {
            s = TimelyStore.acquire(context).loadNotifications(String.valueOf(calendarkey.getLocalId()), account, false, 1, "method=1", "minutes ASC");
        }
        if (s == null || s.length == 0)
        {
            return !Utilities.isGoogleConsumerAccount(account) ? 10 : 30;
        } else
        {
            return ((PreferredNotification) (s[0])).minutes;
        }
    }

    public static String getDurationAndPreferredTimesAccessibilityString(Resources resources, HabitContract habitcontract)
    {
        return resources.getString(0x7f130007, new Object[] {
            ReminderUtils.constructTimeIntervalString(resources, habitcontract.getDurationMinutes()), getPreferredTimeString(resources, habitcontract).toLowerCase()
        });
    }

    public static String getDurationAndPreferredTimesString(Resources resources, HabitContract habitcontract)
    {
        return resources.getString(0x7f1301a0, new Object[] {
            ReminderUtils.constructTimeIntervalString(resources, habitcontract.getDurationMinutes()), getPreferredTimeString(resources, habitcontract).toLowerCase()
        });
    }

    public static String getFrequencyString(Resources resources, int i, int j)
    {
        if (i == 3)
        {
            return resources.getQuantityString(0x7f12001c, j, new Object[] {
                Integer.valueOf(j)
            });
        }
        if (j == 7)
        {
            return resources.getString(0x7f1301da);
        } else
        {
            return resources.getQuantityString(0x7f12001d, j, new Object[] {
                Integer.valueOf(j)
            });
        }
    }

    public static String getFrequencyString(Resources resources, HabitContract habitcontract)
    {
        return getFrequencyString(resources, habitcontract.getInterval(), habitcontract.getNumInstancesPerInterval());
    }

    public static String getGrooveFeedbackMessage(Context context, boolean flag, boolean flag1)
    {
        if (context == null)
        {
            return null;
        }
        if (flag1 && !flag)
        {
            return context.getString(0x7f130254);
        }
        if (flag)
        {
            if (!NetworkUtil.isConnectedToInternet(context))
            {
                return context.getString(0x7f130255);
            } else
            {
                return context.getString(0x7f130256);
            }
        } else
        {
            return context.getString(0x7f130253);
        }
    }

    public static CalendarListEntry getGrooveSupportedCalendar(Context context, CalendarListEntry acalendarlistentry[])
    {
        boolean flag = false;
        if (acalendarlistentry != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        CalendarDescriptor calendardescriptor;
        int i;
        int k;
        calendardescriptor = CalendarProperties.getDefaultCalendarId();
        k = acalendarlistentry.length;
        i = 0;
_L5:
        CalendarListEntry calendarlistentry;
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        calendarlistentry = acalendarlistentry[i];
        if (!calendarlistentry.getDescriptor().equals(calendardescriptor)) goto _L4; else goto _L3
_L3:
        if (isPrimaryGoogleCalendar(calendarlistentry))
        {
            return calendarlistentry;
        }
        break MISSING_BLOCK_LABEL_68;
_L4:
        i++;
          goto _L5
        calendarlistentry = null;
          goto _L3
        context = AccountsUtil.getGoogleAccounts(context);
        if (context.length > 0)
        {
            context = context[0];
            int l = acalendarlistentry.length;
            int j = ((flag) ? 1 : 0);
            while (j < l) 
            {
                CalendarListEntry calendarlistentry1 = acalendarlistentry[j];
                if (context.equals(calendarlistentry1.getDescriptor().account) && calendarlistentry1.isPrimary())
                {
                    return calendarlistentry1;
                }
                j++;
            }
        }
        if (true) goto _L1; else goto _L6
_L6:
    }

    public static ListenableFuture getInstanceInfoForParent(Context context, Account account, String s, long l, long l1)
    {
        class .Lambda._cls3
            implements Supplier
        {

            private final Context arg$1;

            public final Object get()
            {
                return GrooveUtils.lambda$getInstanceInfoForParent$2$GrooveUtils(arg$1);
            }

            .Lambda._cls3(Context context)
            {
                arg$1 = context;
            }
        }

        return (new EventsApiImpl(context, new .Lambda._cls3(context))).searchGoalsAsync(account, s, l, l1);
    }

    public static long[] getIntervalStartAndEnd(Context context, int i, long l)
    {
        if (i == 3)
        {
            context = Calendar.getInstance(Utils.getTimeZone(context));
            context.setTimeInMillis(l);
            context.set(11, 0);
            context.set(12, 0);
            context.set(13, 0);
            context.set(14, 0);
            context.set(5, 1);
            l = context.getTimeInMillis();
            context.add(2, 1);
            return (new long[] {
                l, context.getTimeInMillis()
            });
        } else
        {
            return getStartAndEndOfWeek(context, l);
        }
    }

    private static CalendarKey getLocalCalendarId(Account account, String s)
    {
        int i = 0;
        ListenableFutureCache listenablefuturecache = CalendarListEntryCache.instance;
        if (listenablefuturecache == null)
        {
            try
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            // Misplaced declaration of an exception variable
            catch (Account account)
            {
                LogUtils.e(TAG, account, "Unable to load calendars", new Object[0]);
            }
            return null;
        }
        CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])((ListenableFutureCache)listenablefuturecache).getValueAsync().get();
        for (int j = acalendarlistentry.length; i < j; i++)
        {
            CalendarListEntry calendarlistentry = acalendarlistentry[i];
            if (s.equals(calendarlistentry.getDescriptor().calendarId) && account.equals(calendarlistentry.getDescriptor().account))
            {
                return calendarlistentry.getDescriptor().calendarKey;
            }
        }

        return null;
    }

    public static String getPreferredTimeString(Resources resources, HabitContract habitcontract)
    {
        if (allOrNoPreferredTimesSelected(habitcontract))
        {
            return resources.getString(0x7f1302cd);
        }
        ArrayList arraylist = new ArrayList(2);
        if (habitcontract.isMorningPreferable())
        {
            arraylist.add(resources.getString(0x7f1302cb));
        }
        if (habitcontract.isAfternoonPreferable())
        {
            arraylist.add(resources.getString(0x7f1302c9));
        }
        if (habitcontract.isEveningPreferable())
        {
            arraylist.add(resources.getString(0x7f1302ca));
        }
        if (arraylist.size() == 2)
        {
            return resources.getString(0x7f1303b0, new Object[] {
                arraylist.get(0), ((String)arraylist.get(1)).toLowerCase()
            });
        } else
        {
            return (String)arraylist.get(0);
        }
    }

    private static long[] getStartAndEndOfWeek(Context context, long l)
    {
        Calendar calendar = Calendar.getInstance(Utils.getTimeZone(context));
        calendar.setTimeInMillis(l);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.set(7, PreferenceUtils.getFirstDayOfWeekAsCalendar(context));
        if (calendar.getTimeInMillis() > l)
        {
            calendar.add(5, -7);
        }
        l = calendar.getTimeInMillis();
        calendar.add(5, 7);
        return (new long[] {
            l, calendar.getTimeInMillis()
        });
    }

    public static GrooveStats getStats(List list, long l, long l1)
    {
        class .Lambda._cls2
            implements Predicate
        {

            private final long arg$1;
            private final long arg$2;

            public final boolean apply(Object obj)
            {
                return GrooveUtils.lambda$getStats$1$GrooveUtils(arg$1, arg$2, (TimeRangeEntry)obj);
            }

            .Lambda._cls2(long l, long l1)
            {
                arg$1 = l;
                arg$2 = l1;
            }
        }

        .Lambda._cls2 _lcls2 = new .Lambda._cls2(l, l1);
        if (list == null)
        {
            throw new NullPointerException();
        }
        if (_lcls2 == null)
        {
            throw new NullPointerException();
        }
        list = (new com.google.common.collect.Iterables._cls4(list, _lcls2)).iterator();
        int i = 0;
        int j = 0;
        do
        {
            if (!list.hasNext())
            {
                break;
            }
            GoalItem goalitem = (GoalItem)((TimeRangeEntry)list.next()).getValue();
            j++;
            if (goalitem.getGoal().isDone())
            {
                i++;
            }
        } while (true);
        return new AutoValue_GrooveStats(j, i);
    }

    public static boolean hasContractChanges(HabitModifications habitmodifications)
    {
        if (habitmodifications != null && habitmodifications.getContract() != null && habitmodifications.getOriginal() != null)
        {
            HabitContract habitcontract = habitmodifications.getContract();
            habitmodifications = habitmodifications.getOriginal().getContract();
            boolean flag;
            if (habitcontract.getDurationMinutes() == habitmodifications.getDurationMinutes() && habitcontract.getInterval() == habitmodifications.getInterval() && habitcontract.getNumInstancesPerInterval() == habitmodifications.getNumInstancesPerInterval() && habitcontract.isAnyDayTimeAcceptable() == habitmodifications.isAnyDayTimeAcceptable() && habitcontract.isMorningPreferable() == habitmodifications.isMorningPreferable() && habitcontract.isAfternoonPreferable() == habitmodifications.isAfternoonPreferable() && habitcontract.isEveningPreferable() == habitmodifications.isEveningPreferable() && habitcontract.getUntilMillisUtc() == habitmodifications.getUntilMillisUtc())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isInLastWeek(Context context, long l)
    {
        Calendar calendar = Calendar.getInstance(Utils.getTimeZone(context));
        long l1;
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        calendar.setTimeInMillis(l1);
        calendar.add(5, -7);
        context = getStartAndEndOfWeek(context, calendar.getTimeInMillis());
        return l >= context[0] && l < context[1];
    }

    public static boolean isInThisWeek(Context context, long l)
    {
        long l1;
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        context = getStartAndEndOfWeek(context, l1);
        return l >= context[0] && l < context[1];
    }

    public static boolean isPrimaryGoogleCalendar(CalendarListEntry calendarlistentry)
    {
        if (calendarlistentry != null && calendarlistentry.isPrimary())
        {
            if ((calendarlistentry = calendarlistentry.getDescriptor().account) != null && "com.google".equals(((Account) (calendarlistentry)).type))
            {
                return true;
            }
        }
        return false;
    }

    static final TimeZone lambda$getInstanceInfoForParent$2$GrooveUtils(Context context)
    {
        return Utils.getTimeZone(context);
    }

    static final boolean lambda$getStats$1$GrooveUtils(long l, long l1, TimeRangeEntry timerangeentry)
    {
        return l <= timerangeentry.getRange().getUtcStartMillis() && timerangeentry.getRange().getUtcStartMillis() < l1;
    }

}
