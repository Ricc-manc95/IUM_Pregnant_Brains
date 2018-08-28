// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.settings.data;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.calendarlist.CalendarListEntryModifications;
import com.google.android.calendar.api.calendarlist.CalendarListFactory;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.GoogleSettingsModifications;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.api.settings.SettingsFactory;
import com.google.android.calendar.sharedprefs.SharedPrefs;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.timely.settings.data:
//            DefaultCalendarHelper

public class CalendarProperties
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/settings/data/CalendarProperties);
    public static CalendarProperties instance;
    private static HashSet southernHemisphereTimezones;
    public ImmutableMap calendars;
    public final Context context;
    private final int defaultColor;
    private final int defaultGridHourHeight;
    private final ArrayList propertyChangedListeners = new ArrayList(15);
    private final HashMap propertyValues = new HashMap();
    private final Resources resources;
    public ImmutableMap settings;

    private CalendarProperties(Context context1)
    {
        calendars = RegularImmutableMap.EMPTY;
        settings = RegularImmutableMap.EMPTY;
        context = context1;
        resources = context.getResources();
        defaultColor = resources.getColor(0x7f0d004a);
        defaultGridHourHeight = resources.getDimensionPixelSize(0x7f0e01d5);
        context1 = SettingsCache.instance;
        if (context1 == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        context1 = (ListenableFutureCache)context1;
        class .Lambda._cls0
            implements Consumer
        {

            private final CalendarProperties arg$1;

            public final void accept(Object obj)
            {
                CalendarProperties calendarproperties = arg$1;
                calendarproperties.settings = (ImmutableMap)obj;
                obj = (CalendarDescriptor)DefaultCalendarHelper.readDefaultCalendarDescriptorToSharedPrefs((ImmutableSet)calendarproperties.calendars.keySet(), calendarproperties.context).orNull();
                if (obj != null)
                {
                    obj = (CalendarListEntry)calendarproperties.calendars.get(obj);
                    if (obj != null)
                    {
                        calendarproperties.setPropertyValue(3, Integer.valueOf(calendarproperties.getDefaultEventDuration(((CalendarListEntry) (obj)).getDescriptor().account)));
                    }
                }
            }

            .Lambda._cls0()
            {
                arg$1 = CalendarProperties.this;
            }
        }

        context1.subscribe(new .Lambda._cls0(), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), false);
        ImmutableMap immutablemap = (ImmutableMap)((ListenableFutureCache) (context1)).result;
        context1 = immutablemap;
        if (immutablemap == null)
        {
            context1 = RegularImmutableMap.EMPTY;
        }
        settings = context1;
        context1 = CalendarListEntryCache.instance;
        if (context1 == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        context1 = (ListenableFutureCache)context1;
        class .Lambda._cls1
            implements Consumer
        {

            private final CalendarProperties arg$1;

            public final void accept(Object obj)
            {
                CalendarProperties calendarproperties;
                calendarproperties = arg$1;
                calendarproperties.setCalendars((CalendarListEntry[])obj);
                obj = calendarproperties.context.getSharedPreferences("com.google.android.calendar_preferences", 0).getString("preference_defaultTaskAccount", null);
                if (obj == null) goto _L2; else goto _L1
_L1:
                UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)((ImmutableCollection)calendarproperties.calendars.values()).iterator();
_L5:
                if (!unmodifiableiterator.hasNext()) goto _L2; else goto _L3
_L3:
                if (!TextUtils.equals(((CharSequence) (obj)), ((CalendarListEntry)unmodifiableiterator.next()).getDescriptor().account.name)) goto _L5; else goto _L4
_L4:
                obj = (CalendarDescriptor)DefaultCalendarHelper.readDefaultCalendarDescriptorToSharedPrefs((ImmutableSet)calendarproperties.calendars.keySet(), calendarproperties.context).orNull();
                if (obj == null)
                {
                    calendarproperties.computeNewDefaultCalendar();
                    return;
                }
                break; /* Loop/switch isn't completed */
_L2:
                calendarproperties.computeNewDefaultTaskAccount();
                if (true) goto _L4; else goto _L6
_L6:
                obj = (CalendarListEntry)calendarproperties.calendars.get(obj);
                if (obj == null || !((CalendarListEntry) (obj)).isPrimary() && !((CalendarListEntry) (obj)).isVisible())
                {
                    calendarproperties.computeNewDefaultCalendar();
                    return;
                } else
                {
                    calendarproperties.setPropertyValue(3, Integer.valueOf(calendarproperties.getDefaultEventDuration(((CalendarListEntry) (obj)).getDescriptor().account)));
                    calendarproperties.setPropertyValue(4, Integer.valueOf(ColorUtils.getDisplayColorFromColor(((CalendarListEntry) (obj)).getColor().getValue())));
                    return;
                }
            }

            .Lambda._cls1()
            {
                arg$1 = CalendarProperties.this;
            }
        }

        context1.subscribe(new .Lambda._cls1(), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), false);
        setCalendars((CalendarListEntry[])((ListenableFutureCache) (context1)).result);
        for (int i = 0; i < 15; i++)
        {
            propertyChangedListeners.add(new HashSet());
        }

        if (southernHemisphereTimezones == null)
        {
            southernHemisphereTimezones = new HashSet(Arrays.asList(resources.getStringArray(0x7f0b0054)));
        }
        context1 = Utils.getTimeZoneId(context);
        propertyValues.put(Integer.valueOf(0), context1);
        int j;
        boolean flag;
        if (southernHemisphereTimezones.contains(context1.toLowerCase()))
        {
            j = 6;
        } else
        {
            j = 0;
        }
        propertyValues.put(Integer.valueOf(1), Integer.valueOf(j));
        context1 = (CalendarDescriptor)DefaultCalendarHelper.readDefaultCalendarDescriptorToSharedPrefs((ImmutableSet)calendars.keySet(), context).orNull();
        propertyValues.put(Integer.valueOf(2), context1);
        j = defaultColor;
        if (context1 == null)
        {
            computeNewDefaultCalendar();
        } else
        {
            context1 = (CalendarListEntry)calendars.get(context1);
            int k;
            if (context1 != null)
            {
                j = context1.getColor().getValue();
                k = getDefaultEventDuration(context1.getDescriptor().account);
            } else
            {
                k = -1;
            }
            propertyValues.put(Integer.valueOf(3), Integer.valueOf(k));
            propertyValues.put(Integer.valueOf(4), Integer.valueOf(j));
        }
        context1 = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getString("preference_defaultTaskAccount", null);
        propertyValues.put(Integer.valueOf(12), context1);
        if (context1 == null)
        {
            computeNewDefaultTaskAccount();
        }
        j = PreferenceUtils.getFirstDayOfWeekAsCalendar(context);
        propertyValues.put(Integer.valueOf(5), Integer.valueOf(j));
        j = PreferencesConstants.getAlternateCalendarPref(context);
        propertyValues.put(Integer.valueOf(13), Integer.valueOf(j));
        flag = Utils.getHideDeclinedEvents(context);
        propertyValues.put(Integer.valueOf(14), Boolean.valueOf(flag));
        flag = AccessibilityUtils.isAccessibilityEnabled(context);
        propertyValues.put(Integer.valueOf(6), Boolean.valueOf(flag));
        flag = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_show_week_num", false);
        propertyValues.put(Integer.valueOf(7), Boolean.valueOf(flag));
        if (!context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_showMoreEvents", false))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        propertyValues.put(Integer.valueOf(11), Boolean.valueOf(flag));
        context1 = context;
        j = defaultGridHourHeight;
        j = context1.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("preferences_grid_hour_height_p", j);
        propertyValues.put(Integer.valueOf(8), Integer.valueOf(j));
        context1 = context;
        j = defaultGridHourHeight;
        j = context1.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("preferences_grid_hour_height_l", j);
        propertyValues.put(Integer.valueOf(9), Integer.valueOf(j));
    }

    private final void checkPropertiesChanged(int i)
    {
        boolean flag = false;
        switch (i)
        {
        case 10: // '\n'
        default:
            return;

        case 0: // '\0'
            setPropertyValue(i, Utils.getTimeZoneId(context));
            return;

        case 1: // '\001'
            String s = (String)getPropertyValue(0);
            if (southernHemisphereTimezones.contains(s.toLowerCase()))
            {
                i = 6;
            } else
            {
                i = 0;
            }
            setPropertyValue(1, Integer.valueOf(i));
            return;

        case 2: // '\002'
            setPropertyValue(2, (CalendarDescriptor)DefaultCalendarHelper.readDefaultCalendarDescriptorToSharedPrefs((ImmutableSet)calendars.keySet(), context).orNull());
            return;

        case 4: // '\004'
            Object obj = (CalendarDescriptor)getPropertyValue(2);
            obj = (CalendarListEntry)calendars.get(obj);
            if (obj == null)
            {
                i = defaultColor;
            } else
            {
                i = ((CalendarListEntry) (obj)).getColor().getValue();
            }
            setPropertyValue(4, Integer.valueOf(i));
            return;

        case 3: // '\003'
            Object obj1 = (CalendarDescriptor)getPropertyValue(2);
            obj1 = (CalendarListEntry)calendars.get(obj1);
            if (obj1 == null)
            {
                i = -1;
            } else
            {
                i = getDefaultEventDuration(((CalendarListEntry) (obj1)).getDescriptor().account);
            }
            setPropertyValue(3, Integer.valueOf(i));
            return;

        case 5: // '\005'
            setPropertyValue(5, Integer.valueOf(PreferenceUtils.getFirstDayOfWeekAsCalendar(context)));
            return;

        case 13: // '\r'
            setPropertyValue(13, Integer.valueOf(PreferencesConstants.getAlternateCalendarPref(context)));
            // fall through

        case 14: // '\016'
            setPropertyValue(14, Boolean.valueOf(Utils.getHideDeclinedEvents(context)));
            // fall through

        case 6: // '\006'
            setPropertyValue(6, Boolean.valueOf(AccessibilityUtils.isAccessibilityEnabled(context)));
            return;

        case 7: // '\007'
            setPropertyValue(7, Boolean.valueOf(context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_show_week_num", false)));
            return;

        case 11: // '\013'
            if (!context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_showMoreEvents", false))
            {
                flag = true;
            }
            setPropertyValue(11, Boolean.valueOf(flag));
            return;

        case 8: // '\b'
            Context context1 = context;
            i = defaultGridHourHeight;
            setPropertyValue(8, Integer.valueOf(context1.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("preferences_grid_hour_height_p", i)));
            return;

        case 9: // '\t'
            Context context2 = context;
            i = defaultGridHourHeight;
            setPropertyValue(9, Integer.valueOf(context2.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("preferences_grid_hour_height_l", i)));
            return;

        case 12: // '\f'
            setPropertyValue(12, context.getSharedPreferences("com.google.android.calendar_preferences", 0).getString("preference_defaultTaskAccount", null));
            return;
        }
    }

    public static Boolean getBooleanProperty(int i)
    {
        return (Boolean)getProperty(i);
    }

    public static CalendarDescriptor getDefaultCalendarId()
    {
        return (CalendarDescriptor)getProperty(2);
    }

    public static Integer getIntProperty(int i)
    {
        return (Integer)getProperty(10);
    }

    private static Object getProperty(int i)
    {
        CalendarProperties calendarproperties = instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            return ((CalendarProperties)calendarproperties).getPropertyValue(i);
        }
    }

    public static void initialize(Context context1)
    {
        com/google/android/calendar/timely/settings/data/CalendarProperties;
        JVM INSTR monitorenter ;
        CalendarProperties calendarproperties = instance;
        if (calendarproperties == null) goto _L2; else goto _L1
_L1:
        com/google/android/calendar/timely/settings/data/CalendarProperties;
        JVM INSTR monitorexit ;
        return;
_L2:
        instance = new CalendarProperties(context1);
        if (true) goto _L1; else goto _L3
_L3:
        context1;
        throw context1;
    }

    public final void checkPropertiesChanged()
    {
        checkPropertiesChanged(0);
        checkPropertiesChanged(1);
        checkPropertiesChanged(2);
        checkPropertiesChanged(4);
        checkPropertiesChanged(3);
        checkPropertiesChanged(5);
        checkPropertiesChanged(6);
        checkPropertiesChanged(7);
        checkPropertiesChanged(11);
        checkPropertiesChanged(8);
        checkPropertiesChanged(9);
        checkPropertiesChanged(12);
        checkPropertiesChanged(13);
        checkPropertiesChanged(14);
    }

    final void computeNewDefaultCalendar()
    {
        Object obj = new ArrayList();
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)((ImmutableCollection)calendars.values()).iterator();
        do
        {
            if (!unmodifiableiterator.hasNext())
            {
                break;
            }
            CalendarListEntry calendarlistentry = (CalendarListEntry)unmodifiableiterator.next();
            int i = CalendarType.calculateType(calendarlistentry.getDescriptor().calendarId);
            if (3 != i && 1 != i && 2 != i && calendarlistentry.getAccessLevel().isGreaterOrEqual(CalendarAccessLevel.WRITER) && (calendarlistentry.isPrimary() || calendarlistentry.isVisible()))
            {
                ((List) (obj)).add(new com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem(calendarlistentry));
            }
        } while (true);
        Collections.sort(((List) (obj)), new com.google.android.calendar.calendarlist.common.CalendarListUtils.ListItemComparator(AccountsUtil.getGoogleAccounts(context)));
        if (((List) (obj)).isEmpty())
        {
            obj = null;
        } else
        {
            obj = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)((List) (obj)).get(0)).calendarDescriptor;
        }
        setDefaultCalendarIdValue(((CalendarDescriptor) (obj)), false);
    }

    final void computeNewDefaultTaskAccount()
    {
        HashSet hashset = new HashSet();
        for (UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)((ImmutableCollection)calendars.values()).iterator(); unmodifiableiterator.hasNext(); hashset.add(((CalendarListEntry)unmodifiableiterator.next()).getDescriptor().account)) { }
        Account aaccount[] = AccountsUtil.getGoogleAccounts(context);
        if (aaccount != null && hashset.size() > 0)
        {
            int j = aaccount.length;
            for (int i = 0; i < j; i++)
            {
                Account account = aaccount[i];
                if (AccountUtil.isGoogleAccount(account) && hashset.contains(account))
                {
                    setDefaultTaskAccountValue(account.name, false);
                    return;
                }
            }

        }
        setDefaultTaskAccountValue(null, false);
    }

    final int getDefaultEventDuration(Account account)
    {
        account = (Settings)settings.get(account);
        if (account instanceof GoogleSettings)
        {
            account = (GoogleSettings)account;
        } else
        {
            account = null;
        }
        if (account == null || account.isEndTimeUnspecifiedByDefault())
        {
            return -1;
        } else
        {
            return (int)TimeUnit.MINUTES.convert(account.getDefaultEventDurationMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public final Object getPropertyValue(int i)
    {
label0:
        {
            Object obj;
            if (i == 10)
            {
                if (resources.getConfiguration().orientation == 2)
                {
                    i = 9;
                } else
                {
                    i = 8;
                }
            }
            synchronized (propertyValues)
            {
                if (propertyValues.containsKey(Integer.valueOf(i)))
                {
                    break label0;
                }
            }
            return null;
        }
        obj = propertyValues.get(Integer.valueOf(i));
        hashmap;
        JVM INSTR monitorexit ;
        return obj;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void registerListener(int i, OnPropertyChangedListener onpropertychangedlistener)
    {
        if (i == 10)
        {
            if (resources.getConfiguration().orientation == 2)
            {
                i = 9;
            } else
            {
                i = 8;
            }
        }
        synchronized ((HashSet)propertyChangedListeners.get(i))
        {
            hashset.add(onpropertychangedlistener);
        }
        return;
        onpropertychangedlistener;
        hashset;
        JVM INSTR monitorexit ;
        throw onpropertychangedlistener;
    }

    final void setCalendars(CalendarListEntry acalendarlistentry[])
    {
        com.google.common.collect.ImmutableMap.Builder builder = new com.google.common.collect.ImmutableMap.Builder();
        if (acalendarlistentry != null)
        {
            int j = acalendarlistentry.length;
            for (int i = 0; i < j; i++)
            {
                CalendarListEntry calendarlistentry = acalendarlistentry[i];
                if (calendarlistentry.getDescriptor().calendarKey != null)
                {
                    builder.put(calendarlistentry.getDescriptor(), calendarlistentry);
                }
            }

        }
        calendars = builder.build();
    }

    public final void setDefaultCalendarIdValue(CalendarDescriptor calendardescriptor, boolean flag)
    {
        if (flag)
        {
            Object obj = (CalendarListEntry)calendars.get(calendardescriptor);
            if (obj != null && !((CalendarListEntry) (obj)).isVisible())
            {
                obj = CalendarApi.CalendarListFactory.modifyCalendarListEntry(((CalendarListEntry) (obj)));
                ((CalendarListEntryModifications) (obj)).setIsVisible(true);
                CalendarApi.CalendarList.update(((CalendarListEntryModifications) (obj)));
            }
        }
        setPropertyValue(2, calendardescriptor);
        DefaultCalendarHelper.writeDefaultCalendarDescriptorToSharedPrefs(context, calendardescriptor);
        checkPropertiesChanged(3);
        checkPropertiesChanged(4);
    }

    public final void setDefaultTaskAccountValue(String s, boolean flag)
    {
        if (flag)
        {
            Object obj = (Settings)settings.get(AccountUtil.newGoogleAccount(s));
            if (obj instanceof GoogleSettings)
            {
                obj = (GoogleSettings)obj;
            } else
            {
                obj = null;
            }
            if (obj != null && !((GoogleSettings) (obj)).areTasksVisible())
            {
                obj = CalendarApi.SettingsFactory.modifyGoogleSettings(((GoogleSettings) (obj)));
                ((GoogleSettingsModifications) (obj)).setAreTasksVisible(true);
                CalendarApi.Settings.update(((com.google.android.calendar.api.settings.SettingsModifications) (obj)));
            }
        }
        setPropertyValue(12, s);
        SharedPrefs.setSharedPreference(context, "preference_defaultTaskAccount", s);
    }

    public final boolean setPropertyValue(int i, Object obj)
    {
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        int j;
        if (CalendarExecutor.currentExecutor() == calendarexecutor)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0)
        {
            LogUtils.wtf(TAG, "must be called on main UI thread", new Object[0]);
        }
        j = i;
        if (i == 10)
        {
            Object obj2;
            int k;
            int l;
            if (resources.getConfiguration().orientation == 2)
            {
                i = 9;
            } else
            {
                i = 8;
            }
            j = i;
        }
        obj1 = propertyValues;
        obj1;
        JVM INSTR monitorenter ;
        obj2 = propertyValues.get(Integer.valueOf(j));
        if (obj2 == obj) goto _L2; else goto _L1
_L1:
        if (obj2 == null) goto _L4; else goto _L3
_L3:
        if (!obj2.equals(obj)) goto _L4; else goto _L2
_L6:
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        obj1;
        JVM INSTR monitorexit ;
        return false;
        propertyValues.put(Integer.valueOf(j), obj);
        obj1;
        JVM INSTR monitorexit ;
        synchronized (propertyChangedListeners)
        {
            obj2 = new ArrayList((Collection)propertyChangedListeners.get(j));
        }
        obj1 = (ArrayList)obj2;
        l = ((ArrayList) (obj1)).size();
        k = 0;
        i = j;
        j = k;
label0:
        do
        {
label1:
            {
                if (j >= l)
                {
                    break label0;
                }
                obj2 = ((ArrayList) (obj1)).get(j);
                k = j + 1;
                obj2 = (OnPropertyChangedListener)obj2;
                if (i != 9)
                {
                    j = i;
                    if (i != 8)
                    {
                        break label1;
                    }
                }
                j = 10;
            }
            ((OnPropertyChangedListener) (obj2)).onCalendarPropertyChanged(j, obj);
            i = j;
            j = k;
        } while (true);
        break MISSING_BLOCK_LABEL_259;
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
        return true;
_L2:
        i = 1;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 0;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void unregisterListener(int i, OnPropertyChangedListener onpropertychangedlistener)
    {
        ArrayList arraylist = new ArrayList();
        int j;
        if (i == 10)
        {
            arraylist.add(Integer.valueOf(9));
            arraylist.add(Integer.valueOf(8));
        } else
        {
            arraylist.add(Integer.valueOf(i));
        }
        arraylist = (ArrayList)arraylist;
        j = arraylist.size();
        for (i = 0; i < j;)
        {
            Object obj = arraylist.get(i);
            i++;
            int k = ((Integer)obj).intValue();
            synchronized ((HashSet)propertyChangedListeners.get(k))
            {
                hashset.remove(onpropertychangedlistener);
            }
        }

        break MISSING_BLOCK_LABEL_128;
        onpropertychangedlistener;
        hashset;
        JVM INSTR monitorexit ;
        throw onpropertychangedlistener;
    }

}
