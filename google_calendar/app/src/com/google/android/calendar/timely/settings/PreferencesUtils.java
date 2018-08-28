// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.sharedprefs.SharedPrefs;
import com.google.android.calendar.utils.timely.TimelyUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PreferencesUtils
{

    private static final ImmutableList SUPPORTED_TABLET_VIEWS = ImmutableList.of("prerence_value_agenda_view", "preference_value_hourly_view", "preferences_value_week_view", "preferences_value_month_view");
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/settings/PreferencesUtils);

    public PreferencesUtils()
    {
    }

    public static int getLastUsedView(Context context, boolean flag)
    {
label0:
        {
label1:
            {
label2:
                {
label3:
                    {
                        boolean flag1 = true;
                        String s1 = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getString("preference_key_last_view", null);
                        String s = s1;
                        int i;
                        if (s1 == null)
                        {
                            {
                                i = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("preferred_startView", 0);
                                switch (i)
                                {
                                default:
                                    if (flag)
                                    {
                                        s = "preferences_value_month_view";
                                    } else
                                    {
                                        s = "prerence_value_agenda_view";
                                    }
                                    break;

                                case 1: // '\001'
                                    break label3;

                                case 2: // '\002'
                                    break label2;

                                case 3: // '\003'
                                    break label1;

                                case 4: // '\004'
                                    break label0;
                                }
                            }
                            context = context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit();
                            context.putString("preference_key_last_view", s);
                            if (i == 1 || i == 2)
                            {
                                if (i != 2)
                                {
                                    flag1 = false;
                                }
                                context.putBoolean("preference_key_grid_mode", flag1);
                            }
                            context.apply();
                        }
                        if (s.equals("preferences_value_list_week_view") || s.equals("preferences_value_list_week_view_7_days"))
                        {
                            i = 0x7f100050;
                        } else
                        {
                            context = s;
                            if (flag)
                            {
                                context = s;
                                if (!SUPPORTED_TABLET_VIEWS.contains(s))
                                {
                                    context = "preferences_value_month_view";
                                }
                            }
                            int j = viewIdForPreferenceValue(context);
                            i = j;
                            if (j == 0)
                            {
                                LogUtils.wtf(TAG, "Unfamiliar last view, setting to default.", new Object[0]);
                                if (flag)
                                {
                                    context = "preferences_value_month_view";
                                } else
                                {
                                    context = "prerence_value_agenda_view";
                                }
                                return viewIdForPreferenceValue(context);
                            }
                        }
                        return i;
                    }
                    s = "prerence_value_agenda_view";
                    break MISSING_BLOCK_LABEL_83;
                }
                s = "preference_value_hourly_view";
                break MISSING_BLOCK_LABEL_83;
            }
            s = "preferences_value_week_view";
            break MISSING_BLOCK_LABEL_83;
        }
        s = "preferences_value_month_view";
        break MISSING_BLOCK_LABEL_83;
    }

    public static long getLocationPermissionRequests(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong("location_permissions_request_count", 0L);
    }

    public static boolean getPrefersGridMode(Context context)
    {
        return context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_key_grid_mode", false);
    }

    public static String getRingtonePreference(Context context)
    {
label0:
        {
            Object obj;
label1:
            {
                obj = context.getSharedPreferences("com.google.android.calendar_preferences_no_backup", 0).getString("preferences_alerts_ringtone", null);
                String s;
                String s1;
                Object aobj[];
                if (!context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_use_standard_tone", true))
                {
                    boolean flag;
                    if (obj == null || ((String) (obj)).contains(context.getPackageName()))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        break label1;
                    }
                }
                obj = new Joiner("/");
                s = context.getPackageName();
                s1 = context.getResources().getResourceTypeName(0x7f0a0004);
                aobj = new Object[1];
                aobj[0] = context.getResources().getResourceEntryName(0x7f0a0004);
                if (aobj == null)
                {
                    throw new NullPointerException();
                }
                context = (new com.google.common.base.Joiner._cls3(aobj, s, s1)).iterator();
                context = String.valueOf(((Joiner) (obj)).appendTo(new StringBuilder(), context).toString());
                if (context.length() == 0)
                {
                    break label0;
                }
                obj = "android.resource://".concat(context);
            }
            return ((String) (obj));
        }
        return new String("android.resource://");
    }

    public static String getRingtoneTitleFromUri(Context context, String s)
    {
        if (TextUtils.isEmpty(s))
        {
            return context.getString(0x7f130398);
        }
        boolean flag;
        if (s == null || s.contains(context.getPackageName()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return context.getString(0x7f13035f);
        }
        s = RingtoneManager.getRingtone(context, Uri.parse(s));
        if (s != null)
        {
            return s.getTitle(context);
        } else
        {
            return null;
        }
    }

    public static boolean hasOobeBeenSeen(Context context)
    {
        if (context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("seenOOBE", false))
        {
            return true;
        }
        Object obj = TimelyUtils.getVersionSharedPreferences(context);
        if (((SharedPreferences) (obj)).getBoolean("anySeenOOBE", false))
        {
            SharedPrefs.setSharedPreference(context, "seenOOBE", true);
            return true;
        }
        for (obj = ((SharedPreferences) (obj)).getAll().keySet().iterator(); ((Iterator) (obj)).hasNext();)
        {
            if (((String)((Iterator) (obj)).next()).startsWith("seenOOBE_"))
            {
                SharedPrefs.setSharedPreference(context, "seenOOBE", true);
                return true;
            }
        }

        return false;
    }

    public static void incrementLocationPermissionRequest(Context context)
    {
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context);
        long l = PreferenceManager.getDefaultSharedPreferences(context).getLong("location_permissions_request_count", 0L);
        sharedpreferences.edit().putLong("location_permissions_request_count", l + 1L).apply();
    }

    public static void markOobeAsSeen(Context context)
    {
        SharedPrefs.setSharedPreference(context, "seenOOBE", true);
    }

    public static void setLastUsedDayView(Context context, int i)
    {
        if (i == 0x7f100004 || i == 0x7f100022)
        {
            boolean flag;
            if (i == 0x7f100022)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            SharedPrefs.setSharedPreference(context, "preference_key_grid_mode", flag);
        }
    }

    public static void setLastUsedView(Context context, boolean flag, int i)
    {
        String s;
        if (i == 0x7f100004)
        {
            s = "prerence_value_agenda_view";
        } else
        if (i == 0x7f100022)
        {
            s = "preference_value_hourly_view";
        } else
        if (i == 0x7f100026)
        {
            s = "preference_value_3_day_view";
        } else
        if (i == 0x7f100050)
        {
            s = "preferences_value_week_view";
        } else
        if (i == 0x7f100027)
        {
            s = "preferences_value_month_view";
        } else
        {
            if (flag)
            {
                s = "preferences_value_month_view";
            } else
            {
                s = "prerence_value_agenda_view";
            }
            LogUtils.wtf(TAG, "Unable to save itemId=%d defaulting to %s", new Object[] {
                Integer.valueOf(i), s
            });
        }
        SharedPrefs.setSharedPreference(context, "preference_key_last_view", s);
    }

    public static void setRingtonePreference(Context context, String s)
    {
        boolean flag;
        if (s == null || s.contains(context.getPackageName()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            s = null;
        }
        context.getSharedPreferences("com.google.android.calendar_preferences_no_backup", 0).edit().putString("preferences_alerts_ringtone", s).apply();
    }

    private static int viewIdForPreferenceValue(String s)
    {
        if (s.equals("prerence_value_agenda_view"))
        {
            return 0x7f100004;
        }
        if (s.equals("preference_value_hourly_view"))
        {
            return 0x7f100022;
        }
        if (s.equals("preference_value_3_day_view"))
        {
            return 0x7f100026;
        }
        if (s.equals("preferences_value_week_view"))
        {
            return 0x7f100050;
        }
        return !s.equals("preferences_value_month_view") ? 0 : 0x7f100027;
    }

}
