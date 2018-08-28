// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alternatecalendar;

import android.content.Context;
import android.content.res.Resources;
import android.icu.text.NumberFormat;
import android.icu.util.Calendar;
import android.icu.util.ChineseCalendar;
import android.icu.util.HebrewCalendar;
import android.icu.util.IndianCalendar;
import android.icu.util.IslamicCalendar;
import android.icu.util.TimeZone;
import android.icu.util.ULocale;
import android.util.SparseArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import java.util.Locale;

public class AlternateCalendarUtils
{
    static final class StringCache
        implements OnPropertyChangedListener
    {

        public static StringCache instance;
        public final SparseArray fullDateString = new SparseArray();
        public Locale locale;

        public final void onCalendarPropertyChanged(int i, Object obj)
        {
            fullDateString.clear();
        }

        StringCache()
        {
            locale = Locale.getDefault();
            CalendarProperties calendarproperties = CalendarProperties.instance;
            if (calendarproperties == null)
            {
                throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
            } else
            {
                ((CalendarProperties)calendarproperties).registerListener(13, this);
                return;
            }
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/alternatecalendar/AlternateCalendarUtils);
    private static Locale hebrewLocale;
    private static NumberFormat hebrewNumberFormat;
    private static TimeZone icuUtcTimeZone;

    public AlternateCalendarUtils()
    {
    }

    public static String getAlternateAccessibilityDateRange(int i, int j, Resources resources, int k)
    {
        Object obj;
        Object obj1;
        Object obj2;
        boolean flag;
        boolean flag1;
        int l;
        int i1;
        int j1;
        int k1;
        long l1 = getUtcNoonTimestampWithJulianDay(i);
        if (icuUtcTimeZone == null && android.os.Build.VERSION.SDK_INT >= 24)
        {
            icuUtcTimeZone = TimeZone.getTimeZone("UTC");
        }
        obj = getCalendarWithPreference(l1, icuUtcTimeZone, k);
        l = ((Calendar) (obj)).get(1);
        i1 = ((Calendar) (obj)).get(2);
        boolean flag2;
        if (k == 1 || k == 2 || k == 9)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (((Calendar) (obj)).get(22) == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
        } else
        {
            flag = false;
        }
        l1 = getUtcNoonTimestampWithJulianDay(j);
        if (icuUtcTimeZone == null && android.os.Build.VERSION.SDK_INT >= 24)
        {
            icuUtcTimeZone = TimeZone.getTimeZone("UTC");
        }
        obj2 = getCalendarWithPreference(l1, icuUtcTimeZone, k);
        j1 = ((Calendar) (obj2)).get(1);
        k1 = ((Calendar) (obj2)).get(2);
        if (k == 1 || k == 2 || k == 9)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            if (((Calendar) (obj2)).get(22) == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
        } else
        {
            flag1 = false;
        }
        if (k == 1 || k == 2 || k == 9)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!flag2) goto _L2; else goto _L1
_L1:
        obj1 = resources.getString(0x7f1300a6, new Object[] {
            getAlternateYearString(((Calendar) (obj)), resources, k), getAlternateMonthString(((Calendar) (obj)), resources, k, false), getAlternateDayOfMonthString(((Calendar) (obj)), resources, k, false)
        });
        if (l != j1)
        {
            obj = getAlternateFullDate(j, resources, k);
        } else
        if (i1 != k1 || flag != flag1)
        {
            obj = getAlternateMonthDayString(j, resources, k);
        } else
        {
            obj = getAlternateDayOfMonthString(((Calendar) (obj2)), resources, k, false);
        }
_L4:
        return resources.getString(0x7f1300a2, new Object[] {
            obj1, obj
        });
_L2:
        if (l == j1)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = getAlternateFullDate(i, resources, k);
_L5:
        obj2 = resources.getString(0x7f1300a5, new Object[] {
            getAlternateYearString(((Calendar) (obj2)), resources, k), getAlternateMonthString(((Calendar) (obj2)), resources, k, false), getAlternateDayOfMonthString(((Calendar) (obj2)), resources, k, false)
        });
        obj1 = obj;
        obj = obj2;
        if (true) goto _L4; else goto _L3
_L3:
        if (i1 != k1 || flag != flag1)
        {
            obj = getAlternateMonthDayString(i, resources, k);
        } else
        {
            obj = getAlternateDayOfMonthString(((Calendar) (obj)), resources, k, false);
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
    }

    public static String getAlternateDayOfMonthString(int i, Resources resources, int j)
    {
        long l = getUtcNoonTimestampWithJulianDay(i);
        if (icuUtcTimeZone == null && android.os.Build.VERSION.SDK_INT >= 24)
        {
            icuUtcTimeZone = TimeZone.getTimeZone("UTC");
        }
        return getAlternateDayOfMonthString(getCalendarWithPreference(l, icuUtcTimeZone, j), resources, j, false);
    }

    private static String getAlternateDayOfMonthString(Calendar calendar, Resources resources, int i, boolean flag)
    {
        int j;
        if (calendar == null)
        {
            return null;
        }
        j = calendar.get(5);
        if (flag && j == 1)
        {
            return getAlternateMonthString(calendar, resources, i, true);
        }
        if (i == 1 || i == 2)
        {
            return resources.getStringArray(0x7f0b0001)[j - 1];
        }
        if (i != 3)
        {
            break MISSING_BLOCK_LABEL_131;
        }
        if (hebrewLocale == null)
        {
            hebrewLocale = new Locale("he");
        }
        if (!hebrewLocale.getLanguage().equals(Locale.getDefault().getLanguage()))
        {
            break MISSING_BLOCK_LABEL_131;
        }
        i = 1;
_L1:
        if (i != 0)
        {
            if (hebrewNumberFormat == null)
            {
                hebrewNumberFormat = NumberFormat.getInstance(new ULocale("@numbers=hebr"));
            }
            return hebrewNumberFormat.format(j);
        } else
        {
            return String.format(Locale.getDefault(), "%d", new Object[] {
                Integer.valueOf(j)
            });
        }
        i = 0;
          goto _L1
    }

    public static String getAlternateFullDate(int i, Resources resources, int j)
    {
        if (StringCache.instance == null)
        {
            StringCache.instance = new StringCache();
        }
        Object obj = StringCache.instance;
        Object obj1 = Locale.getDefault();
        if (!((StringCache) (obj)).locale.equals(obj1))
        {
            ((StringCache) (obj)).fullDateString.clear();
            obj.locale = ((Locale) (obj1));
        }
        obj1 = (String)((StringCache) (obj)).fullDateString.get(i);
        obj = obj1;
        if (obj1 == null)
        {
            long l = getUtcNoonTimestampWithJulianDay(i);
            if (icuUtcTimeZone == null && android.os.Build.VERSION.SDK_INT >= 24)
            {
                icuUtcTimeZone = TimeZone.getTimeZone("UTC");
            }
            obj = getCalendarWithPreference(l, icuUtcTimeZone, j);
            Locale locale;
            int k;
            if (j == 1 || j == 2 || j == 9)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if (k != 0)
            {
                k = 0x7f1300a6;
            } else
            {
                k = 0x7f1300a5;
            }
            obj = resources.getString(k, new Object[] {
                getAlternateYearString(((Calendar) (obj)), resources, j), getAlternateMonthString(((Calendar) (obj)), resources, j, false), getAlternateDayOfMonthString(((Calendar) (obj)), resources, j, false)
            });
            if (StringCache.instance == null)
            {
                StringCache.instance = new StringCache();
            }
            resources = StringCache.instance;
            locale = Locale.getDefault();
            if (!((StringCache) (resources)).locale.equals(locale))
            {
                ((StringCache) (resources)).fullDateString.clear();
                resources.locale = locale;
            }
            ((StringCache) (resources)).fullDateString.put(i, obj);
        }
        return ((String) (obj));
    }

    public static String getAlternateMonthDayString(int i, Resources resources, int j)
    {
        String s = getAlternateDayOfMonthString(i, resources, j);
        long l = getUtcNoonTimestampWithJulianDay(i);
        if (icuUtcTimeZone == null && android.os.Build.VERSION.SDK_INT >= 24)
        {
            icuUtcTimeZone = TimeZone.getTimeZone("UTC");
        }
        String s1 = getAlternateMonthString(getCalendarWithPreference(l, icuUtcTimeZone, j), resources, j, false);
        if (j == 1 || j == 2 || j == 9)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = 0x7f1300ab;
        } else
        {
            i = 0x7f1300aa;
        }
        return resources.getString(i, new Object[] {
            s, s1
        });
    }

    private static String getAlternateMonthString(Calendar calendar, Resources resources, int i, boolean flag)
    {
        i;
        JVM INSTR tableswitch 1 9: default 52
    //                   1 75
    //                   2 160
    //                   3 171
    //                   4 208
    //                   5 182
    //                   6 182
    //                   7 182
    //                   8 219
    //                   9 230;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L6 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_230;
_L1:
        LogUtils.wtf(TAG, "Month names cannot be retrieved. pref: %d", new Object[] {
            Integer.valueOf(i)
        });
        return "";
_L2:
        Object obj = resources.getStringArray(0x7f0b0003);
_L9:
        obj = obj[calendar.get(2)];
        boolean flag1;
        if (i == 1 || i == 2 || i == 9)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 && calendar.get(22) == 1)
        {
            if (i == 9)
            {
                i = 0x7f1300a8;
            } else
            if (i == 2)
            {
                i = 0x7f1300a4;
            } else
            {
                i = 0x7f1300a3;
            }
            return resources.getString(0x7f1300a9, new Object[] {
                resources.getString(i), obj
            });
        }
        break MISSING_BLOCK_LABEL_264;
_L3:
        obj = resources.getStringArray(0x7f0b0004);
          goto _L9
_L4:
        obj = resources.getStringArray(0x7f0b0005);
          goto _L9
_L6:
        if (flag)
        {
            obj = resources.getStringArray(0x7f0b0008);
        } else
        {
            obj = resources.getStringArray(0x7f0b0007);
        }
          goto _L9
_L5:
        obj = resources.getStringArray(0x7f0b0006);
          goto _L9
_L7:
        obj = resources.getStringArray(0x7f0b000c);
          goto _L9
        obj = resources.getStringArray(0x7f0b000b);
          goto _L9
        if (i == 3 && (new HebrewCalendar(calendar.get(1), 5, 1, 0, 0, 0)).get(2) == 5 && calendar.get(2) == 6)
        {
            return resources.getString(0x7f1300a7);
        } else
        {
            return ((String) (obj));
        }
    }

    public static String getAlternateYearMonthRangeString(int i, int j, Resources resources, int k)
    {
        long l1 = getUtcNoonTimestampWithJulianDay(i);
        long l2 = getUtcNoonTimestampWithJulianDay(j);
        if (icuUtcTimeZone == null && android.os.Build.VERSION.SDK_INT >= 24)
        {
            icuUtcTimeZone = TimeZone.getTimeZone("UTC");
        }
        Object obj = getCalendarWithPreference(l1, icuUtcTimeZone, k);
        int l = ((Calendar) (obj)).get(1);
        int i1 = ((Calendar) (obj)).get(2);
        Object obj1;
        int j1;
        int k1;
        if (k == 1 || k == 2 || k == 9)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            if (((Calendar) (obj)).get(22) == 1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
        } else
        {
            i = 0;
        }
        if (icuUtcTimeZone == null && android.os.Build.VERSION.SDK_INT >= 24)
        {
            icuUtcTimeZone = TimeZone.getTimeZone("UTC");
        }
        obj1 = getCalendarWithPreference(l2, icuUtcTimeZone, k);
        j1 = ((Calendar) (obj1)).get(1);
        k1 = ((Calendar) (obj1)).get(2);
        if (k == 1 || k == 2 || k == 9)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            if (((Calendar) (obj1)).get(22) == 1)
            {
                j = 1;
            } else
            {
                j = 0;
            }
        } else
        {
            j = 0;
        }
        if (l == j1 && i1 == k1 && i == j)
        {
            obj1 = getAlternateYearString(((Calendar) (obj)), resources, k);
            obj = getAlternateMonthString(((Calendar) (obj)), resources, k, false);
            if (k == 1 || k == 2 || k == 9)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = 0x7f1300af;
            } else
            {
                i = 0x7f1300ae;
            }
            return resources.getString(i, new Object[] {
                obj1, obj
            });
        }
        if (l == j1)
        {
            if (k == 1 || k == 2 || k == 9)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = 0x7f1300b3;
            } else
            {
                i = 0x7f1300b2;
            }
            return resources.getString(i, new Object[] {
                getAlternateYearString(((Calendar) (obj)), resources, k), getAlternateMonthString(((Calendar) (obj)), resources, k, false), getAlternateMonthString(((Calendar) (obj1)), resources, k, false)
            });
        }
        if (k == 1 || k == 2 || k == 9)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = 0x7f1300b1;
        } else
        {
            i = 0x7f1300b0;
        }
        return resources.getString(i, new Object[] {
            getAlternateYearString(((Calendar) (obj)), resources, k), getAlternateMonthString(((Calendar) (obj)), resources, k, false), getAlternateYearString(((Calendar) (obj1)), resources, k), getAlternateMonthString(((Calendar) (obj1)), resources, k, false)
        });
    }

    private static String getAlternateYearString(Calendar calendar, Resources resources, int i)
    {
        int k = calendar.get(1);
        i;
        JVM INSTR tableswitch 1 9: default 60
    //                   1 83
    //                   2 83
    //                   3 180
    //                   4 263
    //                   5 263
    //                   6 263
    //                   7 263
    //                   8 263
    //                   9 83;
           goto _L1 _L2 _L2 _L3 _L4 _L4 _L4 _L4 _L4 _L2
_L1:
        LogUtils.wtf(TAG, "Unknown calendar type while getting alternate year: %d", new Object[] {
            Integer.valueOf(i)
        });
        return "";
_L2:
        String as[];
        int j;
        if (i == 9)
        {
            j = 0x7f0b0009;
        } else
        {
            j = 0x7f0b0000;
        }
        calendar = resources.getStringArray(j);
        if (i == 9)
        {
            j = 0x7f0b000a;
        } else
        {
            j = 0x7f0b0002;
        }
        as = resources.getStringArray(j);
        if (i == 9)
        {
            i = 0x7f1300ad;
        } else
        {
            i = 0x7f1300ac;
        }
        return resources.getString(i, new Object[] {
            calendar[(k - 1) % calendar.length], as[(k - 1) % as.length]
        });
_L3:
        if (i != 3) goto _L6; else goto _L5
_L5:
        if (hebrewLocale == null)
        {
            hebrewLocale = new Locale("he");
        }
        if (!hebrewLocale.getLanguage().equals(Locale.getDefault().getLanguage())) goto _L6; else goto _L7
_L7:
        i = 1;
_L8:
        if (i != 0)
        {
            if (hebrewNumberFormat == null)
            {
                hebrewNumberFormat = NumberFormat.getInstance(new ULocale("@numbers=hebr"));
            }
            return hebrewNumberFormat.format(k);
        }
          goto _L4
_L6:
        i = 0;
          goto _L8
_L4:
        return String.format(Locale.getDefault(), "%d", new Object[] {
            Integer.valueOf(k)
        });
    }

    private static Calendar getCalendarWithPreference(long l, TimeZone timezone, int i)
    {
        i;
        JVM INSTR tableswitch 1 9: default 52
    //                   1 74
    //                   2 74
    //                   3 156
    //                   4 168
    //                   5 90
    //                   6 112
    //                   7 134
    //                   8 180
    //                   9 207;
           goto _L1 _L2 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        LogUtils.wtf(TAG, "Unknown calendar type: %d", new Object[] {
            Integer.valueOf(i)
        });
        return null;
_L2:
        timezone = new ChineseCalendar(timezone);
_L11:
        timezone.setTimeInMillis(l);
        return timezone;
_L5:
        timezone = new IslamicCalendar(timezone);
        ((IslamicCalendar)timezone).setCalculationType(android.icu.util.IslamicCalendar.CalculationType.ISLAMIC_CIVIL);
        continue; /* Loop/switch isn't completed */
_L6:
        timezone = new IslamicCalendar(timezone);
        ((IslamicCalendar)timezone).setCalculationType(android.icu.util.IslamicCalendar.CalculationType.ISLAMIC_TBLA);
        continue; /* Loop/switch isn't completed */
_L7:
        timezone = new IslamicCalendar(timezone);
        ((IslamicCalendar)timezone).setCalculationType(android.icu.util.IslamicCalendar.CalculationType.ISLAMIC_UMALQURA);
        continue; /* Loop/switch isn't completed */
_L3:
        timezone = new HebrewCalendar(timezone);
        continue; /* Loop/switch isn't completed */
_L4:
        timezone = new IndianCalendar(timezone);
        continue; /* Loop/switch isn't completed */
_L8:
        Calendar calendar = Calendar.getInstance(new ULocale("@calendar=persian"));
        calendar.setTimeZone(timezone);
        timezone = calendar;
        continue; /* Loop/switch isn't completed */
_L9:
        Calendar calendar1 = Calendar.getInstance(new ULocale("@calendar=dangi"));
        calendar1.setTimeZone(timezone);
        timezone = calendar1;
        if (true) goto _L11; else goto _L10
_L10:
    }

    private static long getUtcNoonTimestampWithJulianDay(int i)
    {
        Time time = new Time("UTC");
        time.writeFieldsToImpl();
        time.impl.setJulianDay(i);
        time.copyFieldsFromImpl();
        time.hour = 12;
        time.minute = 0;
        time.second = 0;
        time.writeFieldsToImpl();
        return time.impl.toMillis(false);
    }

    public static boolean isAlternateCalendarEnabled(Context context)
    {
        return PreferencesConstants.getAlternateCalendarPref(context) != 0;
    }

}
