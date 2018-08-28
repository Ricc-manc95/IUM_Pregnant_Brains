// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.support.v14.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.TwoStatePreference;
import android.support.v7.view.ContextThemeWrapper;
import com.google.android.calendar.api.calendarlist.CalendarCategory;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.settings.InMemoryPreferenceDataStore;
import com.google.android.calendar.settings.SettingsShims;
import com.google.android.calendar.settings.home.CalendarViewModel;
import com.google.android.calendar.settings.view.ViewUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.collect.ImmutableCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

final class CalendarPreferenceBinder
{

    private final PreferenceCategory allDayList;
    private final Preference color;
    public final Context context;
    private final Preference familyDisclaimer;
    private final Preference familyManage;
    public final Fragment fragment;
    public final EditTextPreference name;
    private final PreferenceScreen preferenceScreen;
    private final SwitchPreference sync;
    private final PreferenceCategory timedList;
    public CalendarViewModel viewModel;

    CalendarPreferenceBinder(Context context1, Fragment fragment1, PreferenceScreen preferencescreen)
    {
        context = context1;
        fragment = fragment1;
        preferenceScreen = preferencescreen;
        context1 = preferencescreen.findPreference("family_disclaimer");
        if (context1 == null)
        {
            throw new NullPointerException();
        }
        familyDisclaimer = (Preference)context1;
        context1 = preferencescreen.findPreference("manage_family");
        if (context1 == null)
        {
            throw new NullPointerException();
        }
        familyManage = (Preference)context1;
        context1 = preferencescreen.findPreference("name");
        if (context1 == null)
        {
            throw new NullPointerException();
        }
        name = (EditTextPreference)context1;
        context1 = preferencescreen.findPreference("sync");
        if (context1 == null)
        {
            throw new NullPointerException();
        }
        sync = (SwitchPreference)context1;
        context1 = preferencescreen.findPreference("timed_notifications");
        if (context1 == null)
        {
            throw new NullPointerException();
        }
        timedList = (PreferenceCategory)context1;
        context1 = preferencescreen.findPreference("all_day_notifications");
        if (context1 == null)
        {
            throw new NullPointerException();
        }
        allDayList = (PreferenceCategory)context1;
        if (preferencescreen == null)
        {
            throw new NullPointerException();
        } else
        {
            color = ((PreferenceScreen)preferencescreen).findPreference("color");
            return;
        }
    }

    private final void addNotificationOptions(ListPreference listpreference, Notification notification, boolean flag, Iterable iterable)
    {
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        arraylist.add(context.getString(0x7f1301b6));
        arraylist1.add("no");
        Object obj = null;
        Iterator iterator = iterable.iterator();
        iterable = obj;
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Notification notification1 = (Notification)iterator.next();
            String s1 = SettingsShims.instance.formatNotification(context, notification1, flag);
            int i = notification1.method;
            int j = notification1.minutesBefore;
            String s = (new StringBuilder(23)).append(i).append(",").append(j).toString();
            arraylist1.add(s);
            arraylist.add(s1);
            if (notification1.equals(notification))
            {
                iterable = s;
            }
        } while (true);
        arraylist.add(context.getString(0x7f1301a4));
        arraylist1.add("custom");
        listpreference.setEntries((CharSequence[])arraylist.toArray(new String[arraylist.size()]));
        listpreference.mEntryValues = (CharSequence[])arraylist1.toArray(new String[arraylist1.size()]);
        notification = iterable;
        if (iterable == null)
        {
            notification = "no";
        }
        listpreference.setValue(notification);
    }

    private final void addNotificationPreferences(boolean flag)
    {
        int i = 0;
        class .Lambda._cls4
            implements android.support.v7.preference.Preference.OnPreferenceChangeListener
        {

            private final CalendarPreferenceBinder arg$1;
            private final int arg$2;
            private final boolean arg$3;

            public final boolean onPreferenceChange(Preference preference, Object obj2)
            {
                CalendarPreferenceBinder calendarpreferencebinder = arg$1;
                int k = arg$2;
                boolean flag1 = arg$3;
                if ("no".equals(obj2))
                {
                    if (k >= 0)
                    {
                        obj2 = calendarpreferencebinder.viewModel;
                        if (flag1)
                        {
                            preference = ((CalendarViewModel) (obj2)).allDayNotifications;
                        } else
                        {
                            preference = ((CalendarViewModel) (obj2)).timedNotifications;
                        }
                        preference.remove(k);
                        ((CalendarViewModel) (obj2)).updateStore(flag1);
                        calendarpreferencebinder.bind(calendarpreferencebinder.viewModel);
                    }
                    return true;
                }
                if ("custom".equals(obj2))
                {
                    preference = SettingsShims.instance;
                    obj2 = calendarpreferencebinder.context;
                    class .Lambda._cls5
                        implements com.google.android.calendar.settings.SettingsShims.CustomNotificationListener
                    {

                        private final CalendarPreferenceBinder arg$1;
                        private final boolean arg$2;
                        private final int arg$3;

                        public final void onSet(int l, int i1)
                        {
                            arg$1.setOrAddNotification(arg$2, arg$3, new Notification(i1, l));
                        }

                        .Lambda._cls5(boolean flag, int i)
                        {
                            arg$1 = CalendarPreferenceBinder.this;
                            arg$2 = flag;
                            arg$3 = i;
                        }
                    }

                    .Lambda._cls5 _lcls5 = calendarpreferencebinder. new .Lambda._cls5(flag1, k);
                    String s2 = calendarpreferencebinder.viewModel.calendar.getDescriptor().account.type;
                    preference.createCustomNotificationDialog(((Context) (obj2)), _lcls5, flag1, AccountUtil.EXCHANGE_TYPES.contains(s2)).show(calendarpreferencebinder.fragment.mFragmentManager, null);
                    return true;
                } else
                {
                    preference = ((String)obj2).split(",");
                    calendarpreferencebinder.setOrAddNotification(flag1, k, new Notification(Notification.checkMethod(Integer.parseInt(preference[0])), Integer.parseInt(preference[1])));
                    return true;
                }
            }

            .Lambda._cls4(int i, boolean flag)
            {
                arg$1 = CalendarPreferenceBinder.this;
                arg$2 = i;
                arg$3 = flag;
            }
        }

        PreferenceCategory preferencecategory;
        String s;
        Object obj;
        Object obj1;
        ContextThemeWrapper contextthemewrapper;
        if (flag)
        {
            preferencecategory = allDayList;
        } else
        {
            preferencecategory = timedList;
        }
        if (flag)
        {
            s = "all_day_";
        } else
        {
            s = "timed_";
        }
        obj = viewModel;
        if (flag)
        {
            obj1 = ((CalendarViewModel) (obj)).allDayNotifications;
        } else
        {
            obj1 = ((CalendarViewModel) (obj)).timedNotifications;
        }
        obj = viewModel;
        if (flag)
        {
            obj = ((CalendarViewModel) (obj)).allDayOptions;
        } else
        {
            obj = ((CalendarViewModel) (obj)).timedOptions;
        }
        preferencecategory.removeAll();
        contextthemewrapper = new ContextThemeWrapper(context, 0x7f1400de);
        for (obj1 = ((List) (obj1)).iterator(); ((Iterator) (obj1)).hasNext();)
        {
            Notification notification = (Notification)((Iterator) (obj1)).next();
            String s1 = SettingsShims.instance.formatNotification(context, notification, flag);
            ListPreference listpreference1 = new ListPreference(contextthemewrapper);
            listpreference1.setTitle(s1);
            listpreference1.setKey((new StringBuilder(String.valueOf(s).length() + 11)).append(s).append(i).toString());
            addNotificationOptions(listpreference1, notification, flag, ((Iterable) (obj)));
            listpreference1.mOnChangeListener = new .Lambda._cls4(i, flag);
            preferencecategory.addPreference(listpreference1);
            i++;
        }

        int j = ((PreferenceGroup) (preferencecategory)).mPreferenceList.size();
        obj1 = viewModel.calendar.getDescriptor().account.type;
        if (AccountUtil.EXCHANGE_TYPES.contains(obj1))
        {
            i = 1;
        } else
        {
            i = 5;
        }
        if (j < i)
        {
            ListPreference listpreference = new ListPreference(contextthemewrapper);
            if (((PreferenceGroup) (preferencecategory)).mPreferenceList.size() == 0)
            {
                i = 0x7f130097;
            } else
            {
                i = 0x7f13008b;
            }
            listpreference.setTitle(((Preference) (listpreference)).mContext.getString(i));
            listpreference.setKey(String.valueOf(s).concat("add"));
            addNotificationOptions(listpreference, null, flag, ((Iterable) (obj)));
            listpreference.mOnChangeListener = new .Lambda._cls4(-1, flag);
            preferencecategory.addPreference(listpreference);
        }
    }

    public final void bind(CalendarViewModel calendarviewmodel)
    {
        viewModel = calendarviewmodel;
        ((Preference) (preferenceScreen)).mPreferenceManager.mPreferenceDataStore = new InMemoryPreferenceDataStore();
        Object obj = familyDisclaimer;
        boolean flag = viewModel.calendar.getCategories().contains(CalendarCategory.FAMILY);
        if (((Preference) (obj)).mVisible != flag)
        {
            obj.mVisible = flag;
            if (((Preference) (obj)).mListener != null)
            {
                ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
            }
        }
        obj = familyManage;
        flag = viewModel.calendar.getCategories().contains(CalendarCategory.FAMILY);
        if (((Preference) (obj)).mVisible != flag)
        {
            obj.mVisible = flag;
            if (((Preference) (obj)).mListener != null)
            {
                ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
            }
        }
        class .Lambda._cls0
            implements android.support.v7.preference.Preference.OnPreferenceClickListener
        {

            private final CalendarPreferenceBinder arg$1;
            private final CalendarViewModel arg$2;

            public final boolean onPreferenceClick(Preference preference)
            {
                preference = arg$1;
                Object obj2 = arg$2;
                obj2 = (new Intent()).setAction("com.google.android.gms.family.v2.MANAGE").putExtra("accountName", ((CalendarViewModel) (obj2)).calendar.getDescriptor().account.name).putExtra("appId", "calendar");
                ((Activity)((CalendarPreferenceBinder) (preference)).context).startActivityForResult(((Intent) (obj2)), 0);
                return true;
            }

            .Lambda._cls0(CalendarViewModel calendarviewmodel)
            {
                arg$1 = CalendarPreferenceBinder.this;
                arg$2 = calendarviewmodel;
            }
        }

        familyManage.mOnClickListener = new .Lambda._cls0(calendarviewmodel);
        obj = sync;
        flag = viewModel.isSyncEditable();
        if (((Preference) (obj)).mVisible != flag)
        {
            obj.mVisible = flag;
            if (((Preference) (obj)).mListener != null)
            {
                ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
            }
        }
        sync.setChecked(viewModel.syncEnabled);
        class .Lambda._cls1
            implements android.support.v7.preference.Preference.OnPreferenceChangeListener
        {

            private final CalendarViewModel arg$1;

            public final boolean onPreferenceChange(Preference preference, Object obj2)
            {
                preference = arg$1;
                boolean flag2 = ((Boolean)obj2).booleanValue();
                if (!preference.isSyncEditable())
                {
                    throw new IllegalStateException();
                }
                if (flag2 != ((CalendarViewModel) (preference)).syncEnabled)
                {
                    preference.syncEnabled = flag2;
                    preference.updateCalendar(new com.google.android.calendar.settings.home.CalendarViewModel..Lambda._cls3(flag2));
                }
                return true;
            }

            .Lambda._cls1(CalendarViewModel calendarviewmodel)
            {
                arg$1 = calendarviewmodel;
            }
        }

        sync.mOnChangeListener = new .Lambda._cls1(calendarviewmodel);
        obj = context.getResources();
        class .Lambda._cls2
            implements android.support.v7.preference.Preference.OnPreferenceChangeListener
        {

            private final CalendarPreferenceBinder arg$1;
            private final CalendarViewModel arg$2;

            public final boolean onPreferenceChange(Preference preference, Object obj2)
            {
                preference = arg$1;
                CalendarViewModel calendarviewmodel1 = arg$2;
                String s = (String)obj2;
                if (!calendarviewmodel1.isNameEditable())
                {
                    throw new IllegalStateException();
                }
                String s1 = calendarviewmodel1.displayName;
                boolean flag2;
                if (s == s1 || s != null && s.equals(s1))
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (!flag2)
                {
                    calendarviewmodel1.displayName = s;
                    calendarviewmodel1.updateCalendar(new com.google.android.calendar.settings.home.CalendarViewModel..Lambda._cls2(calendarviewmodel1));
                }
                ((CalendarPreferenceBinder) (preference)).name.setSummary((String)obj2);
                return true;
            }

            .Lambda._cls2(CalendarViewModel calendarviewmodel)
            {
                arg$1 = CalendarPreferenceBinder.this;
                arg$2 = calendarviewmodel;
            }
        }

        class .Lambda._cls3
            implements Supplier
        {

            private final CalendarViewModel arg$1;

            public final Object get()
            {
                return arg$1.calendarColor;
            }

            .Lambda._cls3(CalendarViewModel calendarviewmodel)
            {
                arg$1 = calendarviewmodel;
            }
        }

        Object obj1;
        boolean flag1;
        if (calendarviewmodel.calendar.isPrimary() && AccountUtil.isGoogleAccount(calendarviewmodel.calendar.getDescriptor().account))
        {
            obj = ((Resources) (obj)).getString(0x7f1303b3);
        } else
        {
            obj = calendarviewmodel.displayName;
        }
        obj = (String)obj;
        name.setSummary(((CharSequence) (obj)));
        obj1 = name;
        flag = ((Preference) (obj1)).shouldDisableDependents();
        obj1.mText = ((String) (obj));
        ((Preference) (obj1)).persistString(((String) (obj)));
        flag1 = ((Preference) (obj1)).shouldDisableDependents();
        if (flag1 != flag)
        {
            ((Preference) (obj1)).notifyDependencyChange(flag1);
        }
        obj = name;
        flag = viewModel.isNameEditable();
        if (((Preference) (obj)).mVisible != flag)
        {
            obj.mVisible = flag;
            if (((Preference) (obj)).mListener != null)
            {
                ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
            }
        }
        name.mOnChangeListener = new .Lambda._cls2(calendarviewmodel);
        addNotificationPreferences(false);
        addNotificationPreferences(true);
        calendarviewmodel = fragment;
        obj = color;
        obj1 = viewModel;
        obj1.getClass();
        ViewUtils.bindColorPreference(calendarviewmodel, ((Preference) (obj)), new .Lambda._cls3(((CalendarViewModel) (obj1))), viewModel.calendar.getDescriptor().account.type.equals("com.google"));
    }

    final void bindColor()
    {
        Fragment fragment1 = fragment;
        Preference preference = color;
        CalendarViewModel calendarviewmodel = viewModel;
        calendarviewmodel.getClass();
        ViewUtils.bindColorPreference(fragment1, preference, new .Lambda._cls3(calendarviewmodel), viewModel.calendar.getDescriptor().account.type.equals("com.google"));
    }

    final void setOrAddNotification(boolean flag, int i, Notification notification)
    {
        if (i >= 0)
        {
            CalendarViewModel calendarviewmodel = viewModel;
            Object obj;
            if (flag)
            {
                obj = calendarviewmodel.allDayNotifications;
            } else
            {
                obj = calendarviewmodel.timedNotifications;
            }
            obj = (Notification)((List) (obj)).set(i, notification);
            if (notification == obj || notification != null && notification.equals(obj))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                calendarviewmodel.updateStore(flag);
            }
        } else
        {
            CalendarViewModel calendarviewmodel1 = viewModel;
            Object obj1;
            if (flag)
            {
                obj1 = calendarviewmodel1.allDayNotifications;
            } else
            {
                obj1 = calendarviewmodel1.timedNotifications;
            }
            ((List) (obj1)).add(notification);
            if (flag)
            {
                obj1 = calendarviewmodel1.allDayOptions;
            } else
            {
                obj1 = calendarviewmodel1.timedOptions;
            }
            ((Set) (obj1)).add(notification);
            calendarviewmodel1.updateStore(flag);
        }
        bind(viewModel);
    }
}
