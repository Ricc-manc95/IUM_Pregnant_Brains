// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.accounts.Account;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.commonsync.constants.ExtendedPropertiesConstants;
import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutors;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.time.DateTimeUtils;
import com.google.android.calendar.time.TimeUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.geometry.PartitionItem;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.calendar.utils.customchooser.SendEmailChooserHelper;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.calendar.utils.permission.PermissionsUtil;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Executor;

public final class Utils
    implements ExtendedPropertiesConstants
{

    public static final TimeInterpolator SINE_INTERPOLATOR = new _cls1();
    private static final Map colorsIndices;
    private static final Executor periodSyncExecutor;
    public static final com.google.android.calendar.time.TimeUtils.TimeZoneUtils tZUtils = new com.google.android.calendar.time.TimeUtils.TimeZoneUtils();

    public static void addBorderlessTouchFeedback(Context context, View view)
    {
        context = context.getTheme().obtainStyledAttributes(new int[] {
            0x7f0100a3
        });
        int i = context.getResourceId(0, 0);
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        view.setBackgroundResource(i);
        context.recycle();
        return;
        view;
        context.recycle();
        throw view;
    }

    public static void appendSyncFlags(Bundle bundle)
    {
        bundle.putBoolean("sync_extra_get_settings", true);
        bundle.putBoolean("sync_extra_get_recent_notifications", true);
        bundle.putBoolean("sync_extra_get_default_notifications", true);
    }

    public static Set computePartitionItemsToHide(Iterable iterable, int i, int j, int k, List list)
    {
        HashSet hashset = new HashSet();
        Iterator iterator = iterable.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            PartitionItem partitionitem1 = (PartitionItem)iterator.next();
            if (partitionitem1.getPartition() >= k)
            {
                updateHiddenItemCount(partitionitem1, i, j, list);
                hashset.add(partitionitem1);
            }
        } while (true);
        iterable = iterable.iterator();
label0:
        do
        {
            if (iterable.hasNext())
            {
                PartitionItem partitionitem = (PartitionItem)iterable.next();
                if (partitionitem.getPartition() != k - 1)
                {
                    continue;
                }
                int l = Math.max(i, partitionitem.getStartDay());
                int i1 = Math.min(j, partitionitem.getEndDay());
                do
                {
                    if (l > i1)
                    {
                        continue label0;
                    }
                    if (((Integer)list.get(l - i)).intValue() > 0)
                    {
                        updateHiddenItemCount(partitionitem, i, j, list);
                        hashset.add(partitionitem);
                        continue label0;
                    }
                    l++;
                } while (true);
            }
            return hashset;
        } while (true);
    }

    public static long convertAlldayUtcToLocal(com.google.android.calendar.time.Time time, long l, String s)
    {
        return TimeUtils.convertAlldayUtcToLocal(time, l, s);
    }

    public static Intent createEmailAttendeesIntent(Context context, String s, String s1, List list, List list1, String s2)
    {
        int i = 1;
        Resources resources = context.getResources();
        List list2 = list;
        List list3 = list1;
        if (list.size() <= 0)
        {
            if (list1.size() <= 0)
            {
                throw new IllegalArgumentException("Both toEmails and ccEmails are empty.");
            }
            list3 = null;
            list2 = list1;
        }
        if (s != null)
        {
            list = String.valueOf(resources.getString(0x7f1301c4));
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = list.concat(s);
            } else
            {
                s = new String(list);
            }
        } else
        {
            s = null;
        }
        list = new android.net.Uri.Builder();
        list.scheme("mailto");
        if (list2.size() > 1)
        {
            for (; i < list2.size(); i++)
            {
                list.appendQueryParameter("to", (String)list2.get(i));
            }

        }
        if (s != null)
        {
            list.appendQueryParameter("subject", s);
        }
        if (s1 != null)
        {
            list.appendQueryParameter("body", s1);
        }
        if (list3 != null && list3.size() > 0)
        {
            for (s = list3.iterator(); s.hasNext(); list.appendQueryParameter("cc", (String)s.next())) { }
        }
        list = list.toString();
        s = list;
        if (list.startsWith("mailto:"))
        {
            s = new StringBuilder(list);
            s.insert(7, Uri.encode((String)list2.get(0)));
            s = s.toString();
        }
        s = new Intent("android.intent.action.SENDTO", Uri.parse(s));
        if (s1 != null)
        {
            s.putExtra("android.intent.extra.TEXT", s1);
        }
        return SendEmailChooserHelper.instance.createCustomChooser(context, resources.getString(0x7f1301c1), s, s2);
    }

    public static Typeface createProductSans(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(), "fonts/ProductSans-Regular.ttf");
    }

    public static void drawRtlCompatibleDrawable(Drawable drawable, Canvas canvas, boolean flag)
    {
        if (flag)
        {
            canvas.save();
            Matrix matrix = new Matrix();
            matrix.setScale(-1F, 1.0F);
            matrix.postTranslate(canvas.getWidth(), 0.0F);
            canvas.concat(matrix);
            drawable.draw(canvas);
            canvas.restore();
            return;
        } else
        {
            drawable.draw(canvas);
            return;
        }
    }

    public static String formatDateRange(Context context, Formatter formatter, long l, long l1, int i)
    {
        Object obj = tZUtils;
        if ((i & 0x2000) != 0)
        {
            obj = "UTC";
        } else
        {
            if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.firstTZRequest)
            {
                com.google.android.calendar.time.TimeUtils.TimeZoneUtils.getTimeZone(context, null, false);
            }
            if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.useHomeTZ)
            {
                obj = com.google.android.calendar.time.TimeUtils.TimeZoneUtils.homeTZ;
            } else
            {
                obj = Time.getCurrentTimezone();
            }
        }
        return com.google.android.calendar.time.TimeUtils.TimeZoneUtils.formatDateRange(context, formatter, l, l1, i, ((String) (obj)));
    }

    public static String formatDateTime(Context context, long l, int i, String s)
    {
        return tZUtils.formatDateRange(context, l, l, i, s);
    }

    private static String formatSingleDay(Context context, long l, int i, long l1)
    {
        Resources resources = context.getResources();
        int k = 16;
        if ((i & 1) == 0)
        {
            k = 18;
        }
        int j = k;
        if ((i & 2) != 0)
        {
            j = k | 0x80000;
        }
        k = j;
        if ((i & 0x10) != 0)
        {
            k = j | 0x10000;
        }
        long l2;
        if (Clock.mockedTimestamp > 0L)
        {
            l2 = Clock.mockedTimestamp;
        } else
        {
            l2 = System.currentTimeMillis();
        }
        j = Time.getJulianDay(l, l1) - Time.getJulianDay(l2, l1);
        if (j == 1)
        {
            j = 2;
        } else
        if (j == 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (1 == j)
        {
            if ((i & 4) == 0)
            {
                return resources.getString(0x7f130488);
            } else
            {
                return "";
            }
        }
        if (2 == j)
        {
            return resources.getString(0x7f130490);
        } else
        {
            return tZUtils.formatDateRange(context, l, l, k);
        }
    }

    public static CharSequence getAccessibilityDateTimes(Context context, int i, boolean flag, long l, long l1, String s)
    {
        Resources resources = context.getResources();
        if (flag || DateFormat.is24HourFormat(context) || l == l1 || !singleDayEvent(l, l1, s, flag))
        {
            return null;
        }
        StringBuilder stringbuilder = new StringBuilder();
        if ((i & 8) == 0)
        {
            s = new com.google.android.calendar.time.Time(s);
            ((com.google.android.calendar.time.Time) (s)).impl.timezone = ((com.google.android.calendar.time.Time) (s)).timezone;
            ((com.google.android.calendar.time.Time) (s)).impl.set(l);
            ((com.google.android.calendar.time.Time) (s)).impl.toMillis(true);
            s.copyFieldsFromImpl();
            stringbuilder.append(formatSingleDay(context, l, i, ((com.google.android.calendar.time.Time) (s)).gmtoff));
            stringbuilder.append(resources.getString(0x7f13014d));
        }
        stringbuilder.append(tZUtils.formatDateRange(context, l, l, 1));
        stringbuilder.append(resources.getString(0x7f130151));
        stringbuilder.append(tZUtils.formatDateRange(context, l1, l1, 1));
        return stringbuilder.toString();
    }

    private static int getAppApkVersion(Context context, String s)
    {
        int i = -1;
        try
        {
            context = context.getPackageManager().getPackageInfo(s, 0);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return -1;
        }
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        i = ((PackageInfo) (context)).versionCode;
        return i;
    }

    public static String getCalendarNameToDisplay(boolean flag, String s, String s1, String s2)
    {
        while (!flag || !AccountUtil.isGoogleType(s1)) 
        {
            return s;
        }
        return s2;
    }

    public static String getCalendarOwnerAccount(CalendarKey calendarkey)
    {
        ListenableFutureCache listenablefuturecache = CalendarListEntryCache.instance;
        if (listenablefuturecache == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])((ListenableFutureCache)listenablefuturecache).result;
        if (acalendarlistentry != null)
        {
            int j = acalendarlistentry.length;
            for (int i = 0; i < j; i++)
            {
                CalendarListEntry calendarlistentry = acalendarlistentry[i];
                if (calendarlistentry.getDescriptor().calendarKey.equals(calendarkey))
                {
                    return calendarlistentry.getDescriptor().account.name;
                }
            }

        }
        return null;
    }

    public static boolean getConfigBool(Context context, int i)
    {
        return context.getResources().getBoolean(i);
    }

    public static int getCurrentYear(Context context)
    {
        context = Calendar.getInstance(TimeZone.getTimeZone(getTimeZoneId(context)));
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        context.setTimeInMillis(l);
        return context.get(1);
    }

    public static int[] getDateInfo(int i)
    {
        com.google.android.calendar.time.Time time = new com.google.android.calendar.time.Time();
        time.writeFieldsToImpl();
        time.impl.setJulianDay(i);
        time.copyFieldsFromImpl();
        time.writeFieldsToImpl();
        time.impl.normalize(true);
        time.copyFieldsFromImpl();
        return (new int[] {
            time.year, time.month, time.monthDay
        });
    }

    public static boolean getDisplayedDateTimes(long l, long l1, long l2, String s, boolean flag, 
            int i, Context context, StringBuilder stringbuilder, StringBuilder stringbuilder1)
    {
        return getDisplayedDateTimes(l, l1, l2, s, flag, i, context, stringbuilder, stringbuilder1, false);
    }

    private static boolean getDisplayedDateTimes(long l, long l1, long l2, String s, boolean flag, 
            int i, Context context, StringBuilder stringbuilder, StringBuilder stringbuilder1, boolean flag1)
    {
        int k = 16;
        if ((i & 1) == 0)
        {
            k = 18;
        }
        int j = k;
        if ((i & 0x10) != 0)
        {
            j = k | 0x10000;
        }
        com.google.android.calendar.time.Time time;
        Resources resources;
        boolean flag2;
        if ((i & 2) != 0)
        {
            k = 0x81401;
            j |= 0x80000;
        } else
        {
            k = 5121;
        }
        time = new com.google.android.calendar.time.Time(s);
        time.impl.timezone = time.timezone;
        time.impl.set(l);
        time.impl.toMillis(true);
        time.copyFieldsFromImpl();
        resources = context.getResources();
        flag2 = false;
        if (flag)
        {
            l = TimeUtils.convertAlldayUtcToLocal(null, l, s);
            l1 = TimeUtils.convertAlldayUtcToLocal(null, l1, s);
            if (singleDayEvent(l, l1, s, flag))
            {
                l1 = time.gmtoff;
                i = Time.getJulianDay(l, l1) - Time.getJulianDay(l2, l1);
                if (i == 1)
                {
                    i = 2;
                } else
                if (i == 0)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (1 == i)
                {
                    stringbuilder.append(resources.getString(0x7f130488));
                    flag = false;
                } else
                if (2 == i)
                {
                    stringbuilder.append(resources.getString(0x7f130490));
                    flag = false;
                } else
                {
                    stringbuilder.append(DateUtils.formatDateTime(context, l, j));
                    flag = flag2;
                }
            } else
            {
                if (flag1)
                {
                    stringbuilder.append(formatDateTime(context, l, j, s));
                    stringbuilder1.append(formatDateTime(context, l1 - 1L, j, s));
                } else
                {
                    stringbuilder.append(DateUtils.formatDateTime(context, l, j));
                    stringbuilder1.append(DateUtils.formatDateTime(context, l1 - 1L, j));
                }
                flag = true;
            }
        } else
        if (singleDayEvent(l, l1, time.gmtoff, time.gmtoff, flag))
        {
            stringbuilder.append(formatSingleDay(context, l, i, time.gmtoff));
            if (flag1)
            {
                stringbuilder1.append(tZUtils.formatDateRange(context, l, l1, k, s));
                flag = flag2;
            } else
            {
                stringbuilder1.append(tZUtils.formatDateRange(context, l, l1, k));
                flag = flag2;
            }
        } else
        {
            i = j | k;
            if (flag1)
            {
                stringbuilder.append(tZUtils.formatDateRange(context, l, l, i, s));
                stringbuilder1.append(tZUtils.formatDateRange(context, l1, l1, i, s));
            } else
            {
                stringbuilder.append(tZUtils.formatDateRange(context, l, l, i));
                stringbuilder1.append(tZUtils.formatDateRange(context, l1, l1, i));
            }
            flag = true;
        }
        if (stringbuilder.length() == 0)
        {
            stringbuilder.append(stringbuilder1);
            stringbuilder1.setLength(0);
            flag = false;
        }
        return flag;
    }

    public static boolean getDisplayedDateTimesInTimezone(long l, long l1, long l2, String s, boolean flag, 
            int i, Context context, StringBuilder stringbuilder, StringBuilder stringbuilder1)
    {
        return getDisplayedDateTimes(l, l1, l2, s, flag, i, context, stringbuilder, stringbuilder1, true);
    }

    public static String getDisplayedDatetime(long l, long l1, long l2, String s, boolean flag, 
            boolean flag1, Context context)
    {
        Object obj = context.getResources().getConfiguration().locale;
        Object obj1 = Arrays.asList(new String[] {
            "ja", "ko"
        });
        int i;
        int j;
        long l3;
        long l4;
        if (obj != null && ((List) (obj1)).contains(((Locale) (obj)).getLanguage()))
        {
            j = 32786;
        } else
        {
            j = 18;
        }
        obj = new com.google.android.calendar.time.Time(s);
        ((com.google.android.calendar.time.Time) (obj)).impl.timezone = ((com.google.android.calendar.time.Time) (obj)).timezone;
        ((com.google.android.calendar.time.Time) (obj)).impl.set(l2);
        ((com.google.android.calendar.time.Time) (obj)).impl.toMillis(true);
        ((com.google.android.calendar.time.Time) (obj)).copyFieldsFromImpl();
        obj1 = context.getResources();
        if (!flag) goto _L2; else goto _L1
_L1:
        l3 = TimeUtils.convertAlldayUtcToLocal(null, l, s);
        if (!singleDayEvent(l3, TimeUtils.convertAlldayUtcToLocal(null, l1, s), ((com.google.android.calendar.time.Time) (obj)).gmtoff, ((com.google.android.calendar.time.Time) (obj)).gmtoff, flag)) goto _L4; else goto _L3
_L3:
        l4 = ((com.google.android.calendar.time.Time) (obj)).gmtoff;
        i = Time.getJulianDay(l3, l4) - Time.getJulianDay(l2, l4);
        if (i == 1)
        {
            i = 2;
        } else
        if (i == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (1 != i) goto _L6; else goto _L5
_L5:
        if (flag1)
        {
            i = 0x7f130488;
        } else
        {
            i = 0x7f13048c;
        }
        s = ((Resources) (obj1)).getString(i);
_L8:
        obj = s;
        if (s == null)
        {
            obj = DateUtils.formatDateRange(context, new Formatter(new StringBuilder(50), Locale.getDefault()), l, l1, j, "UTC").toString();
        }
        return ((String) (obj));
_L6:
        if (2 == i)
        {
            if (flag1)
            {
                i = 0x7f130490;
            } else
            {
                i = 0x7f130493;
            }
            s = ((Resources) (obj1)).getString(i);
            continue; /* Loop/switch isn't completed */
        }
          goto _L4
_L2:
        if (singleDayEvent(l, l1, ((com.google.android.calendar.time.Time) (obj)).gmtoff, ((com.google.android.calendar.time.Time) (obj)).gmtoff, flag))
        {
            s = tZUtils.formatDateRange(context, l, l1, 1);
            l3 = ((com.google.android.calendar.time.Time) (obj)).gmtoff;
            i = Time.getJulianDay(l, l3) - Time.getJulianDay(l2, l3);
            if (i == 1)
            {
                i = 2;
            } else
            if (i == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (1 == i)
            {
                if (flag1)
                {
                    i = 0x7f130489;
                } else
                {
                    i = 0x7f13048a;
                }
                return ((Resources) (obj1)).getString(i, new Object[] {
                    s
                });
            }
            if (2 == i)
            {
                if (flag1)
                {
                    i = 0x7f130491;
                } else
                {
                    i = 0x7f130492;
                }
                return ((Resources) (obj1)).getString(i, new Object[] {
                    s
                });
            } else
            {
                return ((Resources) (obj1)).getString(0x7f130152, new Object[] {
                    tZUtils.formatDateRange(context, l, l1, j), s
                });
            }
        } else
        {
            return tZUtils.formatDateRange(context, l, l1, 0x18013);
        }
_L4:
        s = null;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static String getDisplayedRangeForKnownContext(long l, long l1, long l2, String s, Context context)
    {
        s = new com.google.android.calendar.time.Time(s);
        ((com.google.android.calendar.time.Time) (s)).impl.timezone = ((com.google.android.calendar.time.Time) (s)).timezone;
        ((com.google.android.calendar.time.Time) (s)).impl.set(l2);
        ((com.google.android.calendar.time.Time) (s)).impl.toMillis(true);
        s.copyFieldsFromImpl();
        Resources resources = context.getResources();
        if (singleDayEvent(l, l1, ((com.google.android.calendar.time.Time) (s)).gmtoff, ((com.google.android.calendar.time.Time) (s)).gmtoff, false))
        {
            return tZUtils.formatDateRange(context, l, l1, 1);
        }
        String s1 = tZUtils.formatDateRange(context, l, l, 1);
        String s2 = tZUtils.formatDateRange(context, l1, l1, 1);
        l = ((com.google.android.calendar.time.Time) (s)).gmtoff;
        int i = Time.getJulianDay(l1, l) - Time.getJulianDay(l2, l);
        if (i == 0)
        {
            s = resources.getString(0x7f130489, new Object[] {
                s2
            });
        } else
        if (1 == i)
        {
            s = resources.getString(0x7f130491, new Object[] {
                s2
            });
        } else
        {
            if (i < 7)
            {
                i = 2;
            } else
            {
                i = 0x10010;
            }
            s = tZUtils.formatDateRange(context, l1, l1, i | 1);
        }
        return resources.getString(0x7f1303d1, new Object[] {
            s1, s
        });
    }

    public static String getDisplayedSingleDate(long l, String s, int i, Context context)
    {
        s = new com.google.android.calendar.time.Time(s);
        ((com.google.android.calendar.time.Time) (s)).impl.timezone = ((com.google.android.calendar.time.Time) (s)).timezone;
        ((com.google.android.calendar.time.Time) (s)).impl.set(l);
        ((com.google.android.calendar.time.Time) (s)).impl.toMillis(true);
        s.copyFieldsFromImpl();
        return formatSingleDay(context, l, 16, ((com.google.android.calendar.time.Time) (s)).gmtoff);
    }

    public static Bundle getExtraEventBundle(long l, Long long1, boolean flag)
    {
        Bundle bundle = new Bundle();
        if (l > 0L)
        {
            bundle.putLong("beginTime", l);
            if (long1 != null)
            {
                bundle.putLong("endTime", long1.longValue());
            }
            bundle.putBoolean("allDay", false);
        }
        return bundle;
    }

    public static int getFirstDayOfWeekAsTime(Context context)
    {
        return com.android.datetimepicker.Utils.convertDayOfWeekFromCalendarToTime(PreferenceUtils.getFirstDayOfWeekAsCalendar(context));
    }

    public static boolean getHideDeclinedEvents(Context context)
    {
        return context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_hide_declined", false);
    }

    public static int getJulianDay(int i, int j, int k)
    {
        return (getJulianFirstDayFromMonth(j, i) + k) - 1;
    }

    public static int getJulianDay(Context context, long l)
    {
        context = new com.google.android.calendar.time.Time(TimeUtils.getTimeZoneId(context, null));
        long l1;
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        ((com.google.android.calendar.time.Time) (context)).impl.timezone = ((com.google.android.calendar.time.Time) (context)).timezone;
        ((com.google.android.calendar.time.Time) (context)).impl.set(l1);
        ((com.google.android.calendar.time.Time) (context)).impl.toMillis(true);
        context.copyFieldsFromImpl();
        context.normalizeSafe();
        return Time.getJulianDay(l, ((com.google.android.calendar.time.Time) (context)).gmtoff);
    }

    public static int getJulianFirstDayFromMonth(int i, int j)
    {
        com.google.android.calendar.time.Time time = new com.google.android.calendar.time.Time("UTC");
        time.writeFieldsToImpl();
        time.impl.set(1, i, j);
        time.copyFieldsFromImpl();
        time.writeFieldsToImpl();
        return Time.getJulianDay(time.impl.toMillis(false), time.gmtoff);
    }

    public static int getJulianFirstDayFromWeeksSinceEpoch(int i, int j)
    {
        int k = 4 - j;
        j = k;
        if (k < 0)
        {
            j = k + 7;
        }
        return (0x253d8c - j) + i * 7;
    }

    public static Bundle getLaunchExtras(Intent intent)
    {
        if (intent == null)
        {
            return null;
        } else
        {
            return intent.getBundleExtra("key_launch_extras");
        }
    }

    public static Rect getLocationInWindow(View view)
    {
        Rect rect = new Rect();
        if (!view.getGlobalVisibleRect(rect))
        {
            int ai[] = new int[2];
            view.getLocationInWindow(ai);
            rect.set(ai[0], ai[1], ai[0] + view.getWidth(), ai[1] + view.getHeight());
        }
        return rect;
    }

    public static long getMillisFromJulianDay(int i)
    {
        com.google.android.calendar.time.Time time = new com.google.android.calendar.time.Time();
        time.setJulianDaySafe(i);
        return time.toMillisWithFallback();
    }

    public static long getNextMidnight(com.google.android.calendar.time.Time time, long l, String s)
    {
        time = new com.google.android.calendar.time.Time();
        time.timezone = s;
        time.impl.timezone = time.timezone;
        time.impl.set(l);
        time.impl.toMillis(true);
        time.copyFieldsFromImpl();
        time.monthDay = time.monthDay + 1;
        time.hour = 0;
        time.minute = 0;
        time.second = 0;
        time.writeFieldsToImpl();
        l = time.impl.normalize(true);
        time.copyFieldsFromImpl();
        return l;
    }

    public static String[] getQuickResponses(Context context)
    {
        String as[] = null;
        Set set = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getStringSet("preferences_quick_responses", null);
        if (set != null)
        {
            as = (String[])set.toArray(new String[set.size()]);
        }
        String as1[] = as;
        if (as == null)
        {
            as1 = context.getResources().getStringArray(0x7f0b0049);
        }
        return as1;
    }

    public static Bitmap getRtlAdjustedImage(Context context, Bitmap bitmap)
    {
        if (!RtlUtils.isLayoutDirectionRtl(context))
        {
            return bitmap;
        } else
        {
            context = new Matrix();
            context.preScale(-1F, 1.0F);
            context = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), context, false);
            context.setDensity(160);
            return context;
        }
    }

    public static String getSearchAuthority(Context context)
    {
        return String.valueOf(context.getPackageName()).concat(".CalendarRecentSuggestionsProvider");
    }

    public static String getShortDayOfWeek(Date date)
    {
        return (new SimpleDateFormat("EEEEE", Locale.getDefault())).format(date).toUpperCase(Locale.getDefault());
    }

    public static int getStartCoordinate(View view)
    {
        if (!RtlUtils.isLayoutDirectionRtl(view.getContext()))
        {
            return view.getLeft();
        }
        android.view.ViewParent viewparent = view.getParent();
        if (viewparent instanceof View)
        {
            return ((View)viewparent).getWidth() - view.getRight();
        } else
        {
            LogUtils.wtf("CalUtils", "No parent to compute start coordinate.", new Object[0]);
            return view.getLeft();
        }
    }

    public static TimeZone getTimeZone(Context context)
    {
        return TimeZone.getTimeZone(getTimeZoneId(context));
    }

    public static String getTimeZoneId(Context context)
    {
        com.google.android.calendar.time.TimeUtils.TimeZoneUtils timezoneutils = TimeUtils.tZUtils;
        if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.firstTZRequest)
        {
            com.google.android.calendar.time.TimeUtils.TimeZoneUtils.getTimeZone(context, null, false);
        }
        if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.useHomeTZ)
        {
            return com.google.android.calendar.time.TimeUtils.TimeZoneUtils.homeTZ;
        } else
        {
            return Time.getCurrentTimezone();
        }
    }

    public static String getTimeZoneId(Context context, Runnable runnable)
    {
        return TimeUtils.getTimeZoneId(context, runnable);
    }

    public static int getTodayJulianDay(Context context)
    {
        context = new com.google.android.calendar.time.Time(TimeUtils.getTimeZoneId(context, null));
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        ((com.google.android.calendar.time.Time) (context)).impl.timezone = ((com.google.android.calendar.time.Time) (context)).timezone;
        ((com.google.android.calendar.time.Time) (context)).impl.set(l);
        ((com.google.android.calendar.time.Time) (context)).impl.toMillis(true);
        context.copyFieldsFromImpl();
        context.writeFieldsToImpl();
        ((com.google.android.calendar.time.Time) (context)).impl.normalize(true);
        context.copyFieldsFromImpl();
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        return Time.getJulianDay(l, ((com.google.android.calendar.time.Time) (context)).gmtoff);
    }

    public static int getWeekNumberFromTime(com.google.android.calendar.time.Time time, Context context)
    {
        time.writeFieldsToImpl();
        int k = Time.getJulianDay(time.impl.toMillis(false), time.gmtoff);
        int j = 4 - com.android.datetimepicker.Utils.convertDayOfWeekFromCalendarToTime(PreferenceUtils.getFirstDayOfWeekAsCalendar(context));
        int i = j;
        if (j < 0)
        {
            i = j + 7;
        }
        return (k - (0x253d8c - i)) / 7;
    }

    public static boolean hasSourceMonthWidget(Intent intent)
    {
        return intent != null && "monthwidget".equals(intent.getStringExtra("intent_source"));
    }

    public static List hideAllDayChips(Iterable iterable, int i, int j, int k)
    {
        ArrayList arraylist = new ArrayList(Collections.nCopies((j - i) + 1, Integer.valueOf(0)));
        Set set = computePartitionItemsToHide(new com.google.android.calendar.utils.collection.Iterables2..Lambda._cls0(iterable), i, j, k, arraylist);
        iterable = iterable.iterator();
        do
        {
            if (!iterable.hasNext())
            {
                break;
            }
            Pair pair = (Pair)iterable.next();
            View view = (View)pair.second;
            if (!set.contains(pair.first))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (view != null)
            {
                if (i != 0)
                {
                    i = 0;
                } else
                {
                    i = 8;
                }
                view.setVisibility(i);
            }
        } while (true);
        return arraylist;
    }

    public static boolean isApkVersionAtLeast(Context context, String s, int i)
    {
        return getAppApkVersion(context, s) >= i;
    }

    public static boolean isCalendarStorageEnabled(Context context)
    {
        String s = android.provider.CalendarContract.Calendars.CONTENT_URI.getAuthority();
        context = context.getContentResolver().acquireContentProviderClient(s);
        if (context == null)
        {
            return false;
        }
        try
        {
            context.release();
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            LogUtils.wtf("CalUtils", context, "Unexpected exception closing or opening the content provider.", new Object[0]);
        }
        return true;
    }

    public static boolean isClearTaskFlagSet(Intent intent)
    {
        return (intent.getFlags() & 0x8000) != 0;
    }

    public static boolean isCurrentYear(Context context, int i)
    {
        context = TimeZone.getTimeZone(getTimeZoneId(context));
        Calendar calendar = Calendar.getInstance(context);
        int j;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        calendar.setTimeInMillis(l);
        j = calendar.get(1);
        calendar.setTimeInMillis(TimeBoxUtil.julianDayToMs(context, i));
        return j == calendar.get(1);
    }

    public static boolean isEmailableFrom(String s, String s1)
    {
        boolean flag;
        if (!TextUtils.isEmpty(s) && !s.endsWith("calendar.google.com"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag && !s.equals(s1);
    }

    public static boolean isInternalSource(Intent intent)
    {
        intent = intent.getStringExtra("intent_source");
        return TextUtils.equals(intent, "notification") || TextUtils.equals(intent, "widget") || TextUtils.equals(intent, "monthwidget");
    }

    public static boolean isPortrait(Context context)
    {
        int i = context.getResources().getConfiguration().orientation;
        if (i == 1)
        {
            return true;
        }
        if (i == 2)
        {
            return false;
        } else
        {
            Log.wtf("CalUtils", "Orientation is neither portrait nor landscape.", new RuntimeException());
            return false;
        }
    }

    public static boolean isTargetAllInOne(Intent intent)
    {
        return intent.getBooleanExtra("open_in_calendar_activity", false);
    }

    public static boolean isTimedMidnightToNextMidnightEvent(long l, long l1, long l2, long l3, 
            boolean flag)
    {
label0:
        {
            if (!flag && Time.getJulianDay(l, l2) != Time.getJulianDay(l - 1L, l2))
            {
                boolean flag1;
                if (Time.getJulianDay(l1, l3) != Time.getJulianDay(l1 - 1L, l3))
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    break label0;
                }
            }
            return false;
        }
        l = Time.getJulianDay(l, l2);
        return (long)Time.getJulianDay(l1, l3) - l == 1L;
    }

    public static boolean isValidEventTime(Calendar calendar, Calendar calendar1, boolean flag)
    {
        return DateTimeUtils.isValidEventTime(calendar.getTimeInMillis(), calendar.getTimeZone(), calendar1.getTimeInMillis(), calendar1.getTimeZone(), flag);
    }

    public static String join(List list, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        boolean flag = true;
        list = list.iterator();
        while (list.hasNext()) 
        {
            Object obj = list.next();
            if (flag)
            {
                flag = false;
            } else
            {
                stringbuilder.append(s);
            }
            stringbuilder.append(obj.toString());
        }
        return stringbuilder.toString();
    }

    public static String julianToDebugString(int i)
    {
        Object obj = new com.google.android.calendar.time.Time();
        ((com.google.android.calendar.time.Time) (obj)).writeFieldsToImpl();
        ((com.google.android.calendar.time.Time) (obj)).impl.setJulianDay(i);
        ((com.google.android.calendar.time.Time) (obj)).copyFieldsFromImpl();
        ((com.google.android.calendar.time.Time) (obj)).writeFieldsToImpl();
        long l = ((com.google.android.calendar.time.Time) (obj)).impl.toMillis(false);
        obj = Calendar.getInstance();
        ((Calendar) (obj)).setTimeInMillis(l);
        i = ((Calendar) (obj)).get(1);
        int j = ((Calendar) (obj)).get(2);
        int k = ((Calendar) (obj)).get(5);
        return (new StringBuilder(35)).append(i).append("/").append(j + 1).append("/").append(k).toString();
    }

    static final void lambda$schedulePeriodicSyncs$0$Utils(Context context, Bundle bundle, Bundle bundle1, Bundle bundle2, Bundle bundle3, Bundle bundle4, Bundle bundle5)
    {
        Account aaccount[] = AccountsUtil.getGoogleAccounts(context);
        HashSet hashset = new HashSet();
        context = AccountsUtil.getGoogleAccounts(context, AccountsUtil.GOOGLE_CALENDAR_SYNC_FEATURE);
        int k = context.length;
        for (int i = 0; i < k; i++)
        {
            hashset.add(context[i]);
        }

        int i1 = aaccount.length;
        int j = 0;
        while (j < i1) 
        {
            context = aaccount[j];
            int j1 = ContentResolver.getIsSyncable(context, "com.android.calendar");
            int l;
            if (hashset.contains(context))
            {
                l = 1;
            } else
            {
                l = 0;
            }
            if (j1 >= 0 && l != j1)
            {
                ContentResolver.setIsSyncable(context, "com.android.calendar", l);
                if (l == 1)
                {
                    ContentResolver.setSyncAutomatically(context, "com.android.calendar", true);
                }
            }
            if (l == 1)
            {
                ContentResolver.addPeriodicSync(context, "com.android.calendar", bundle, 0x15180L);
            } else
            {
                ContentResolver.removePeriodicSync(context, "com.android.calendar", bundle);
            }
            if (bundle1.size() != bundle.size())
            {
                ContentResolver.removePeriodicSync(context, "com.android.calendar", bundle1);
            }
            if (bundle2.size() < bundle.size())
            {
                ContentResolver.removePeriodicSync(context, "com.android.calendar", bundle2);
                ContentResolver.removePeriodicSync(context, "com.android.calendar", bundle3);
            }
            if (!bundle.containsKey("sync_extra_get_server_date"))
            {
                ContentResolver.removePeriodicSync(context, "com.android.calendar", bundle4);
            }
            if (bundle5.size() < bundle.size())
            {
                ContentResolver.removePeriodicSync(context, "com.android.calendar", bundle5);
            }
            context = ContentResolver.getPeriodicSyncs(context, "com.android.calendar");
            if (context.size() != l)
            {
                LogUtils.w("CalUtils", "Unexpected number of periodic syncs: %d", new Object[] {
                    Integer.valueOf(context.size())
                });
            }
            j++;
        }
    }

    public static ArrayList loadIntegerArray(Resources resources, int i)
    {
        resources = resources.getIntArray(i);
        ArrayList arraylist = new ArrayList(resources.length);
        int j = resources.length;
        for (i = 0; i < j; i++)
        {
            arraylist.add(Integer.valueOf(resources[i]));
        }

        return arraylist;
    }

    public static ArrayList loadStringArray(Resources resources, int i)
    {
        return new ArrayList(Arrays.asList(resources.getStringArray(i)));
    }

    public static void loadTimeZone(Context context, Runnable runnable)
    {
        com.google.android.calendar.time.TimeUtils.TimeZoneUtils.getTimeZone(context, runnable, true);
    }

    public static void requestCallPermissions(Activity activity)
    {
        if (PermissionsUtil.canRequestPermissions())
        {
            PermissionsUtil.requestPermissions(activity, AndroidPermissionUtils.PERMISSIONS_CALL, 1004);
        }
    }

    public static void resetMidnightUpdater(Handler handler, Runnable runnable)
    {
        if (handler == null || runnable == null)
        {
            return;
        } else
        {
            handler.removeCallbacks(runnable);
            return;
        }
    }

    public static void schedulePeriodicSyncs(Context context)
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("sync_extra_get_settings", true);
        bundle.putBoolean("sync_extra_get_recent_notifications", true);
        bundle.putBoolean("sync_extra_get_default_notifications", true);
        Bundle bundle1 = Bundle.EMPTY;
        Bundle bundle2 = new Bundle(2);
        bundle2.putBoolean("sync_extra_get_settings", true);
        bundle2.putBoolean("sync_extra_get_recent_notifications", true);
        Bundle bundle3 = new Bundle(bundle2);
        bundle3.putBoolean("sync_extra_get_default_notifications", true);
        Bundle bundle4 = new Bundle(bundle3);
        bundle4.putBoolean("sync_extra_get_server_date", true);
        bundle.putBoolean("sync_periodic", true);
        Bundle bundle5 = new Bundle(bundle);
        bundle5.putBoolean("require_charging", true);
        if (bundle1.size() >= bundle.size() || bundle3.size() >= bundle.size() || bundle.containsKey("sync_extra_get_server_date") || bundle5.size() == bundle.size())
        {
            LogUtils.wtf("CalUtils", "Unexpected extras", new Object[0]);
        }
        class .Lambda._cls0
            implements Runnable
        {

            private final Context arg$1;
            private final Bundle arg$2;
            private final Bundle arg$3;
            private final Bundle arg$4;
            private final Bundle arg$5;
            private final Bundle arg$6;
            private final Bundle arg$7;

            public final void run()
            {
                Utils.lambda$schedulePeriodicSyncs$0$Utils(arg$1, arg$2, arg$3, arg$4, arg$5, arg$6, arg$7);
            }

            .Lambda._cls0(Context context, Bundle bundle, Bundle bundle1, Bundle bundle2, Bundle bundle3, Bundle bundle4, Bundle bundle5)
            {
                arg$1 = context;
                arg$2 = bundle;
                arg$3 = bundle1;
                arg$4 = bundle2;
                arg$5 = bundle3;
                arg$6 = bundle4;
                arg$7 = bundle5;
            }
        }

        periodSyncExecutor.execute(new .Lambda._cls0(context, bundle, bundle5, bundle3, bundle2, bundle4, bundle1));
    }

    public static void setMidnightUpdater(Handler handler, Runnable runnable, String s)
    {
        if (handler == null || runnable == null || s == null)
        {
            return;
        }
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        s = new com.google.android.calendar.time.Time(s);
        ((com.google.android.calendar.time.Time) (s)).impl.timezone = ((com.google.android.calendar.time.Time) (s)).timezone;
        ((com.google.android.calendar.time.Time) (s)).impl.set(l);
        ((com.google.android.calendar.time.Time) (s)).impl.toMillis(true);
        s.copyFieldsFromImpl();
        l = ((0x15180 - ((com.google.android.calendar.time.Time) (s)).hour * 3600 - ((com.google.android.calendar.time.Time) (s)).minute * 60 - ((com.google.android.calendar.time.Time) (s)).second) + 1) * 1000;
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, l);
    }

    public static void setThirdPartyEidSource(Intent intent)
    {
        intent.putExtra("intent_source", "external_eid");
    }

    public static void setThirdPartySourceIfNone(Intent intent)
    {
        if (intent.getStringExtra("intent_source") == null)
        {
            intent.putExtra("intent_source", "external");
        }
    }

    public static void setTimeZone(Context context, String s)
    {
        com.google.android.calendar.time.TimeUtils.TimeZoneUtils.setTimeZone(context, s);
    }

    public static String sharedPrefKeyForAccount(String s, String s1)
    {
        return (new StringBuilder(String.valueOf(s).length() + 9 + String.valueOf(s1).length())).append("account:").append(s).append("|").append(s1).toString();
    }

    private static boolean singleDayEvent(long l, long l1, long l2, long l3, 
            boolean flag)
    {
        int i;
        if (l != l1)
        {
            if ((i = Time.getJulianDay(l, l2)) != Time.getJulianDay(l1, l3) && (i != Time.getJulianDay(l1 - 1L, l3) || isTimedMidnightToNextMidnightEvent(l, l1, l2, l3, flag)))
            {
                return false;
            }
        }
        return true;
    }

    private static boolean singleDayEvent(long l, long l1, String s, boolean flag)
    {
        com.google.android.calendar.time.Time time = new com.google.android.calendar.time.Time(s);
        time.impl.timezone = time.timezone;
        time.impl.set(l);
        time.impl.toMillis(true);
        time.copyFieldsFromImpl();
        s = new com.google.android.calendar.time.Time(s);
        ((com.google.android.calendar.time.Time) (s)).impl.timezone = ((com.google.android.calendar.time.Time) (s)).timezone;
        ((com.google.android.calendar.time.Time) (s)).impl.set(l1);
        ((com.google.android.calendar.time.Time) (s)).impl.toMillis(true);
        s.copyFieldsFromImpl();
        return singleDayEvent(l, l1, time.gmtoff, ((com.google.android.calendar.time.Time) (s)).gmtoff, flag);
    }

    public static long timeFromIntentInMillis(Intent intent)
    {
        Uri uri;
        long l;
        uri = intent.getData();
        l = intent.getLongExtra("beginTime", -1L);
        if (l != -1L || uri == null || !uri.isHierarchical())
        {
            break MISSING_BLOCK_LABEL_88;
        }
        intent = uri.getPathSegments();
        if (intent == null || intent.size() != 2 || !((String)intent.get(0)).equals("time"))
        {
            break MISSING_BLOCK_LABEL_88;
        }
        long l1 = Long.valueOf(uri.getLastPathSegment()).longValue();
        l = l1;
_L2:
        long l2 = l;
        if (l <= 0L)
        {
            if (Clock.mockedTimestamp <= 0L)
            {
                break; /* Loop/switch isn't completed */
            }
            l2 = Clock.mockedTimestamp;
        }
        return l2;
        intent;
        LogUtils.i("Calendar", "timeFromIntentInMillis: Data existed but no valid time found. Using current time.", new Object[0]);
        if (true) goto _L2; else goto _L1
_L1:
        return System.currentTimeMillis();
    }

    public static void tryAccessibilityAnnounce(View view, CharSequence charsequence)
    {
        if (view != null && charsequence != null)
        {
            view.announceForAccessibility(charsequence);
        }
    }

    public static Object uncheckedCast(Object obj)
    {
        return obj;
    }

    private static void updateHiddenItemCount(PartitionItem partitionitem, int i, int j, List list)
    {
        int k = Math.max(i, partitionitem.getStartDay());
        int l = Math.min(j, partitionitem.getEndDay());
        for (j = k; j <= l; j++)
        {
            list.set(j - i, Integer.valueOf(((Integer)list.get(j - i)).intValue() + 1));
        }

    }

    static 
    {
        HashMap hashmap = new HashMap();
        colorsIndices = hashmap;
        hashmap.put("d50000", Integer.valueOf(0));
        colorsIndices.put("f4511e", Integer.valueOf(1));
        colorsIndices.put("ef6c00", Integer.valueOf(2));
        colorsIndices.put("f09300", Integer.valueOf(3));
        colorsIndices.put("f6bf26", Integer.valueOf(4));
        colorsIndices.put("e4c441", Integer.valueOf(5));
        colorsIndices.put("c0ca33", Integer.valueOf(6));
        colorsIndices.put("7cb342", Integer.valueOf(7));
        colorsIndices.put("0b8043", Integer.valueOf(8));
        colorsIndices.put("009688", Integer.valueOf(9));
        colorsIndices.put("33b679", Integer.valueOf(10));
        colorsIndices.put("039be5", Integer.valueOf(11));
        colorsIndices.put("4285f4", Integer.valueOf(12));
        colorsIndices.put("3f51b5", Integer.valueOf(13));
        colorsIndices.put("7986cb", Integer.valueOf(14));
        colorsIndices.put("b39ddb", Integer.valueOf(15));
        colorsIndices.put("9e69af", Integer.valueOf(16));
        colorsIndices.put("8e24aa", Integer.valueOf(17));
        colorsIndices.put("ad1457", Integer.valueOf(18));
        colorsIndices.put("d81b60", Integer.valueOf(19));
        colorsIndices.put("e67c73", Integer.valueOf(20));
        colorsIndices.put("795548", Integer.valueOf(21));
        colorsIndices.put("616161", Integer.valueOf(22));
        colorsIndices.put("a79b8e", Integer.valueOf(23));
        periodSyncExecutor = CalendarExecutors.serialExecutor(CalendarExecutor.DISK);
    }

    private class _cls1
        implements TimeInterpolator
    {

        public final float getInterpolation(float f)
        {
            return (float)Math.sin(3.141593F * f);
        }

        _cls1()
        {
        }
    }

}
