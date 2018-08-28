// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.time;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.text.format.Time;
import com.google.android.apps.calendar.util.android.BroadcastReceivers;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import java.util.Calendar;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.time:
//            Time

public final class TimeUtils
{

    public static final TimeZoneUtils tZUtils = new TimeZoneUtils();

    public static long convertAlldayLocalToUTC(com.google.android.calendar.time.Time time, long l, String s)
    {
        com.google.android.calendar.time.Time time1 = time;
        if (time == null)
        {
            time1 = new com.google.android.calendar.time.Time();
        }
        time1.timezone = s;
        time1.set(l);
        time1.timezone = "UTC";
        time1.writeFieldsToImpl();
        l = time1.impl.normalize(true);
        time1.copyFieldsFromImpl();
        return l;
    }

    public static long convertAlldayUtcToLocal(Context context, long l)
    {
        return convertAlldayUtcToLocal(null, l, getTimeZoneId(context));
    }

    public static long convertAlldayUtcToLocal(com.google.android.calendar.time.Time time, long l, String s)
    {
        com.google.android.calendar.time.Time time1 = time;
        if (time == null)
        {
            time1 = new com.google.android.calendar.time.Time();
        }
        time1.timezone = "UTC";
        time1.set(l);
        time1.timezone = s;
        time1.writeFieldsToImpl();
        l = time1.impl.normalize(true);
        time1.copyFieldsFromImpl();
        return l;
    }

    public static long eightPmPreviousDay(long l, TimeZone timezone)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l);
        calendar.setTimeZone(timezone);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(5, -1);
        calendar.set(11, 20);
        return calendar.getTimeInMillis();
    }

    public static long eightPmPreviousDay(Context context, long l)
    {
        return eightPmPreviousDay(l, TimeZone.getTimeZone(getTimeZoneId(context)));
    }

    public static String getTimeZoneId(Context context)
    {
        TimeZoneUtils timezoneutils = tZUtils;
        if (TimeZoneUtils.firstTZRequest)
        {
            TimeZoneUtils.getTimeZone(context, null, false);
        }
        if (TimeZoneUtils.useHomeTZ)
        {
            return TimeZoneUtils.homeTZ;
        } else
        {
            return Time.getCurrentTimezone();
        }
    }

    public static String getTimeZoneId(Context context, Runnable runnable)
    {
        return TimeZoneUtils.getTimeZone(context, runnable, false);
    }

    public static int previousHourBefore(com.google.android.calendar.time.Time time, int i)
    {
        if (time.minute >= 30)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        return Math.max(time.hour - i, 0);
    }

    public static void roundTime(com.google.android.calendar.time.Time time, int i, int j)
    {
        j = Math.round((float)((time.hour * 60 + time.minute) - j) / 15F) * 15 + j;
        if (j >= 0) goto _L2; else goto _L1
_L1:
        i = j + 15;
_L4:
        time.hour = i / 60;
        time.minute = i % 60;
        time.second = 0;
        time.normalizeSafe();
        return;
_L2:
        i = j;
        if (j >= 1440)
        {
            i = j - 15;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    static void setSharedPreference(SharedPreferences sharedpreferences, String s, String s1)
    {
        sharedpreferences = sharedpreferences.edit();
        sharedpreferences.putString(s, s1);
        sharedpreferences.apply();
    }

    static void setSharedPreference(SharedPreferences sharedpreferences, String s, boolean flag)
    {
        sharedpreferences = sharedpreferences.edit();
        sharedpreferences.putBoolean(s, flag);
        sharedpreferences.apply();
    }

    public static Subscription subscribeToTimeChanges(Context context, Runnable runnable)
    {
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.TIME_SET");
        intentfilter.addAction("android.intent.action.DATE_CHANGED");
        intentfilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        intentfilter.addAction("android.intent.action.LOCALE_CHANGED");
        return BroadcastReceivers.subscribeToBroadcast(context, intentfilter, new com.google.android.apps.calendar.util.function.Consumer..Lambda._cls0(runnable));
    }


    private class TimeZoneUtils
    {

        private static final String CALENDAR_CACHE_POJECTION[] = {
            "key", "value"
        };
        private static final String TIMEZONE_INSTANCES_ARGS[] = {
            "timezoneInstances"
        };
        private static final String TIMEZONE_TYPE_ARGS[] = {
            "timezoneType"
        };
        public static volatile boolean firstTZRequest = true;
        private static Formatter formatter;
        private static AsyncTZHandler handler;
        public static volatile String homeTZ = Time.getCurrentTimezone();
        private static StringBuilder sB;
        public static HashSet tZCallbacks = new HashSet();
        public static volatile boolean tZQueryInProgress = false;
        private static int token = 1;
        public static volatile boolean useHomeTZ = false;

        public static String formatDateRange(Context context, Formatter formatter1, long l, long l1, int i, String s)
        {
            return DateUtils.formatDateRange(context, formatter1, l, l1, i, s).toString();
        }

        public static String getTimeZone(Context context, Runnable runnable, boolean flag)
        {
            HashSet hashset = tZCallbacks;
            hashset;
            JVM INSTR monitorenter ;
            class AsyncTZHandler extends AsyncQueryHandler
            {

                protected final void onQueryComplete(int i, Object obj, Cursor cursor)
                {
                    HashSet hashset1 = TimeZoneUtils.tZCallbacks;
                    hashset1;
                    JVM INSTR monitorenter ;
                    if (cursor != null)
                    {
                        break MISSING_BLOCK_LABEL_24;
                    }
                    TimeZoneUtils.tZQueryInProgress = false;
                    TimeZoneUtils.firstTZRequest = true;
                    hashset1;
                    JVM INSTR monitorexit ;
                    return;
                    int j;
                    int k;
                    j = cursor.getColumnIndexOrThrow("key");
                    k = cursor.getColumnIndexOrThrow("value");
                    i = 0;
_L3:
                    String s;
                    String s1;
                    if (!cursor.moveToNext())
                    {
                        break MISSING_BLOCK_LABEL_157;
                    }
                    s = cursor.getString(j);
                    s1 = cursor.getString(k);
                    if (!TextUtils.equals(s, "timezoneType"))
                    {
                        break MISSING_BLOCK_LABEL_114;
                    }
                    boolean flag1;
                    if (!TextUtils.equals(s1, "auto"))
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (flag1 == TimeZoneUtils.useHomeTZ)
                    {
                        continue; /* Loop/switch isn't completed */
                    }
                    TimeZoneUtils.useHomeTZ = flag1;
                    break MISSING_BLOCK_LABEL_249;
                    if (!TextUtils.equals(s, "timezoneInstancesPrevious") || TextUtils.isEmpty(s1) || TextUtils.equals(TimeZoneUtils.homeTZ, s1))
                    {
                        continue; /* Loop/switch isn't completed */
                    }
                    TimeZoneUtils.homeTZ = s1;
                    break MISSING_BLOCK_LABEL_249;
                    obj;
                    hashset1;
                    JVM INSTR monitorexit ;
                    throw obj;
                    cursor.close();
                    if (i == 0)
                    {
                        break MISSING_BLOCK_LABEL_196;
                    }
                    obj = ((Context)obj).getSharedPreferences("com.google.android.calendar_preferences", 0);
                    TimeUtils.setSharedPreference(((SharedPreferences) (obj)), "preferences_home_tz_enabled", TimeZoneUtils.useHomeTZ);
                    TimeUtils.setSharedPreference(((SharedPreferences) (obj)), "preferences_home_tz", TimeZoneUtils.homeTZ);
                    TimeZoneUtils.tZQueryInProgress = false;
                    obj = TimeZoneUtils.tZCallbacks.iterator();
_L1:
                    do
                    {
                        if (!((Iterator) (obj)).hasNext())
                        {
                            break MISSING_BLOCK_LABEL_239;
                        }
                        cursor = (Runnable)((Iterator) (obj)).next();
                    } while (cursor == null);
                    cursor.run();
                      goto _L1
                    TimeZoneUtils.tZCallbacks.clear();
                    hashset1;
                    JVM INSTR monitorexit ;
                    return;
                    i = 1;
                    if (true) goto _L3; else goto _L2
_L2:
                }

                public AsyncTZHandler(ContentResolver contentresolver)
                {
                    super(contentresolver);
                }
            }

            if (firstTZRequest)
            {
                SharedPreferences sharedpreferences = context.getSharedPreferences("com.google.android.calendar_preferences", 0);
                useHomeTZ = sharedpreferences.getBoolean("preferences_home_tz_enabled", false);
                homeTZ = sharedpreferences.getString("preferences_home_tz", Time.getCurrentTimezone());
                if (Looper.myLooper() != null)
                {
                    tZQueryInProgress = true;
                    firstTZRequest = false;
                    if (handler == null)
                    {
                        handler = new AsyncTZHandler(context.getContentResolver());
                    }
                    handler.startQuery(0, context, android.provider.CalendarContract.CalendarCache.URI, CALENDAR_CACHE_POJECTION, null, null, null);
                }
            }
            if (!tZQueryInProgress || runnable == null) goto _L2; else goto _L1
_L1:
            tZCallbacks.add(runnable);
_L4:
            hashset;
            JVM INSTR monitorexit ;
            if (useHomeTZ)
            {
                return homeTZ;
            } else
            {
                return Time.getCurrentTimezone();
            }
_L2:
            if (!flag) goto _L4; else goto _L3
_L3:
            if (tZQueryInProgress || runnable == null) goto _L4; else goto _L5
_L5:
            if (handler == null)
            {
                handler = new AsyncTZHandler(context.getContentResolver());
            }
            handler.post(runnable);
              goto _L4
            context;
            hashset;
            JVM INSTR monitorexit ;
            throw context;
        }

        public static void setTimeZone(Context context, String s)
        {
            if (!TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
            LogUtils.d("TimeUtils", "Empty time zone, nothing to be done.", new Object[0]);
_L6:
            return;
_L2:
            HashSet hashset = tZCallbacks;
            hashset;
            JVM INSTR monitorenter ;
            if (!"auto".equals(s)) goto _L4; else goto _L3
_L3:
            int i;
            if (useHomeTZ)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            useHomeTZ = false;
_L8:
            hashset;
            JVM INSTR monitorexit ;
            if (i == 0) goto _L6; else goto _L5
_L5:
            s = context.getSharedPreferences("com.google.android.calendar_preferences", 0);
            TimeUtils.setSharedPreference(s, "preferences_home_tz_enabled", useHomeTZ);
            TimeUtils.setSharedPreference(s, "preferences_home_tz", homeTZ);
            s = new ContentValues();
            if (handler != null)
            {
                handler.cancelOperation(token);
            }
            handler = new AsyncTZHandler(context.getContentResolver());
            i = token + 1;
            token = i;
            if (i == 0)
            {
                token = 1;
            }
            if (useHomeTZ)
            {
                context = "home";
            } else
            {
                context = "auto";
            }
            s.put("value", context);
            handler.startUpdate(token, null, android.provider.CalendarContract.CalendarCache.URI, s, "key=?", TIMEZONE_TYPE_ARGS);
            if (!useHomeTZ) goto _L6; else goto _L7
_L7:
            context = new ContentValues();
            context.put("value", homeTZ);
            handler.startUpdate(token, null, android.provider.CalendarContract.CalendarCache.URI, context, "key=?", TIMEZONE_INSTANCES_ARGS);
            return;
_L4:
            if (useHomeTZ && TextUtils.equals(homeTZ, s))
            {
                i = 0;
            } else
            {
                i = 1;
            }
            useHomeTZ = true;
            homeTZ = s;
              goto _L8
            context;
            hashset;
            JVM INSTR monitorexit ;
            throw context;
        }

        public final String formatDateRange(Context context, long l, long l1, int i)
        {
            String s;
            if ((i & 0x2000) != 0)
            {
                s = "UTC";
            } else
            {
                s = getTimeZone(context, null, false);
            }
            return formatDateRange(context, l, l1, i, s);
        }

        public final String formatDateRange(Context context, long l, long l1, int i, String s)
        {
            synchronized (sB)
            {
                sB.setLength(0);
                context = DateUtils.formatDateRange(context, formatter, l, l1, i, s).toString();
            }
            return context;
            context;
            stringbuilder;
            JVM INSTR monitorexit ;
            throw context;
        }

        static 
        {
            sB = new StringBuilder(50);
            formatter = new Formatter(sB, Locale.getDefault());
        }

        public TimeZoneUtils()
        {
        }
    }

}
