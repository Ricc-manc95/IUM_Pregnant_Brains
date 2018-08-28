// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.google.android.apps.calendar.timely.store.AccountSettings;
import com.google.android.apps.calendar.timely.store.AccountSettingsStore;
import com.google.android.apps.calendar.timely.store.BirthdaySetting;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.apps.calendar.timely.store.TimelyStoreUtils;
import com.google.android.apps.calendar.util.database.Cursors;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.common.ApiOperation;
import com.google.android.calendar.api.converters.BirthdayModeConverter;
import com.google.android.calendar.api.event.notification.NotificationsTimelyStoreUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.calendar.v2a.android.util.metric.MetricUtils;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsApiStore, Settings, SettingsImpl, GoogleSettingsImpl, 
//            SettingsModifications, GoogleSettingsModifications

public final class SettingsApiStoreImpl
    implements SettingsApiStore
{

    private static final String ACCOUNTS_FROM_CALENDARS_PROJECTION[] = {
        "account_name", "account_type"
    };
    private static final String ACCOUNT_EXISTS_SELECTION = String.format("%s=? AND %s=?", new Object[] {
        "account_name", "account_type"
    });

    public SettingsApiStoreImpl()
    {
    }

    protected static Object callWithMetrics(Callable callable, ApiOperation apioperation)
        throws IOException
    {
        try
        {
            callable = ((Callable) (MetricUtils.withMetrics(com.google.common.base.Predicates.ObjectPredicate.ALWAYS_TRUE, callable, apioperation).call()));
        }
        // Misplaced declaration of an exception variable
        catch (Callable callable)
        {
            throw callable;
        }
        // Misplaced declaration of an exception variable
        catch (Callable callable)
        {
            throw new IOException(callable);
        }
        return callable;
    }

    protected static boolean doesCalendarWithAccountExist(Account account)
        throws IOException
    {
        return Cursors.count(CalendarApi.getApiContentResolver().query(android.provider.CalendarContract.Calendars.CONTENT_URI, ACCOUNTS_FROM_CALENDARS_PROJECTION, ACCOUNT_EXISTS_SELECTION, new String[] {
            account.name, account.type
        }, null), "Calendar exists") > 0;
    }

    static final Optional lambda$list$2$SettingsApiStoreImpl(String s)
    {
        return AccountUtil.newAccount(s, "com.google");
    }

    static final boolean lambda$list$3$SettingsApiStoreImpl(Set set, Account account)
    {
        return !AccountUtil.isGoogleAccount(account) || set.contains(account);
    }

    static final Settings[] lambda$list$4$SettingsApiStoreImpl()
        throws Exception
    {
        Account aaccount[] = AccountsUtil.getGoogleAccounts(CalendarApi.getApiAppContext());
        int i = aaccount.length;
        class .Lambda._cls4
            implements Function
        {

            public static final Function $instance = new .Lambda._cls4();

            public final Object apply(Object obj3)
            {
                return SettingsApiStoreImpl.lambda$list$2$SettingsApiStoreImpl((String)obj3);
            }


            private .Lambda._cls4()
            {
            }
        }

        Object obj;
        Map map;
        Object obj1;
        Object obj2;
        Function function;
        if (i < 3)
        {
            CollectPreconditions.checkNonnegative(i, "expectedSize");
            i++;
        } else
        if (i < 0x40000000)
        {
            i = (int)((float)i / 0.75F + 1.0F);
        } else
        {
            i = 0x7fffffff;
        }
        obj = new HashSet(i);
        Collections.addAll(((Collection) (obj)), aaccount);
        map = Collections.unmodifiableMap(TimelyStore.acquire(CalendarApi.getApiAppContext()).accountSettingsStore.cache);
        obj1 = listAccounts();
        obj2 = map.keySet();
        function = .Lambda._cls4..instance;
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        if (function == null)
        {
            throw new NullPointerException();
        }
        obj2 = new com.google.common.collect.Iterables._cls5(((Iterable) (obj2)), function);
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        obj1 = FluentIterable.concatNoDefensiveCopy(new Iterable[] {
            obj1, new com.google.common.base.Optional._cls1(((Iterable) (obj2)))
        });
        class .Lambda._cls5
            implements Predicate
        {

            private final Set arg$1;

            public final boolean apply(Object obj3)
            {
                return SettingsApiStoreImpl.lambda$list$3$SettingsApiStoreImpl(arg$1, (Account)obj3);
            }

            .Lambda._cls5(Set set)
            {
                arg$1 = set;
            }
        }

        obj = new .Lambda._cls5(((Set) (obj)));
        obj1 = (Iterable)((FluentIterable) (obj1)).iterableDelegate.or(obj1);
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
        obj = new com.google.common.collect.Iterables._cls4(((Iterable) (obj1)), ((Predicate) (obj)));
        Settings asettings[];
        if (obj instanceof FluentIterable)
        {
            obj = (FluentIterable)obj;
        } else
        {
            obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
        }
        obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
        if (obj instanceof Collection)
        {
            obj = ImmutableSet.copyOf((Collection)obj);
        } else
        {
            obj = ((Iterable) (obj)).iterator();
            if (!((Iterator) (obj)).hasNext())
            {
                obj = RegularImmutableSet.EMPTY;
            } else
            {
                asettings = ((Settings []) (((Iterator) (obj)).next()));
                if (!((Iterator) (obj)).hasNext())
                {
                    obj = new SingletonImmutableSet(asettings);
                } else
                {
                    obj = ((com.google.common.collect.ImmutableSet.Builder)((com.google.common.collect.ImmutableSet.Builder)(new com.google.common.collect.ImmutableSet.Builder()).add(asettings)).addAll(((Iterator) (obj)))).build();
                }
            }
        }
        asettings = new Settings[((ImmutableSet) (obj)).size()];
        obj = (UnmodifiableIterator)((ImmutableCollection) (obj)).iterator();
        for (int j = 0; ((Iterator) (obj)).hasNext(); j++)
        {
            Account account = (Account)((Iterator) (obj)).next();
            asettings[j] = toApiSettings(account, (AccountSettings)map.get(account.name));
        }

        return asettings;
    }

    static final Optional lambda$listAccounts$5$SettingsApiStoreImpl(Cursor cursor)
        throws IOException
    {
        return AccountUtil.newAccount(cursor.getString(0), cursor.getString(1));
    }

    static final Settings lambda$read$0$SettingsApiStoreImpl(Account account)
        throws Exception
    {
        if (doesCalendarWithAccountExist(account))
        {
            if (!AccountUtil.isGoogleAccount(account))
            {
                return toApiSettings(account, null);
            }
            AccountSettingsStore accountsettingsstore = TimelyStore.acquire(CalendarApi.getApiAppContext()).accountSettingsStore;
            String s = account.name;
            if (accountsettingsstore.cache.containsKey(s))
            {
                return toApiSettings(account, accountsettingsstore.getSettings(account.name));
            }
        }
        return null;
    }

    protected static Iterable listAccounts()
        throws IOException
    {
        class .Lambda._cls3
            implements com.google.android.apps.calendar.util.database.Cursors.Extractor
        {

            public static final com.google.android.apps.calendar.util.database.Cursors.Extractor $instance = new .Lambda._cls3();

            public final Object extract(Cursor cursor)
            {
                return SettingsApiStoreImpl.lambda$listAccounts$5$SettingsApiStoreImpl(cursor);
            }


            private .Lambda._cls3()
            {
            }
        }

        java.util.List list1 = Cursors.apply(CalendarApi.getApiContentResolver().query(android.provider.CalendarContract.Calendars.CONTENT_URI, ACCOUNTS_FROM_CALENDARS_PROJECTION, null, null, null), .Lambda._cls3..instance, "Account list");
        if (list1 == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.base.Optional._cls1(list1);
        }
    }

    private static Settings toApiSettings(Account account, AccountSettings accountsettings)
    {
        com.google.android.calendar.api.event.notification.Notification anotification[];
        com.google.android.calendar.api.event.notification.Notification anotification1[];
        boolean flag1;
        java.util.List list1 = Arrays.asList(TimelyStore.acquire(CalendarApi.getApiAppContext()).loadRecentlyUsedNotificationsForAccount(account, false));
        Collections.reverse(list1);
        anotification = NotificationsTimelyStoreUtils.convertNotifications(list1);
        list1 = Arrays.asList(TimelyStore.acquire(CalendarApi.getApiAppContext()).loadRecentlyUsedNotificationsForAccount(account, true));
        Collections.reverse(list1);
        anotification1 = NotificationsTimelyStoreUtils.convertNotifications(list1);
        if (!AccountUtil.isGoogleAccount(account))
        {
            return new SettingsImpl(account, anotification, anotification1);
        }
        if (accountsettings == null)
        {
            return GoogleSettingsImpl.createDefault(account, anotification, anotification1);
        }
        flag1 = accountsettings.autoAddHangouts;
        accountsettings.qualityOfService;
        JVM INSTR tableswitch 1 4: default 124
    //                   1 370
    //                   2 364
    //                   3 358
    //                   4 352;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        int i = 0;
_L15:
        Object obj;
        int j;
        String s = accountsettings.timezoneId;
        j = accountsettings.defaultEventDuration;
        Object obj1;
        CalendarColor calendarcolor;
        long l;
        boolean flag;
        boolean flag2;
        if (j == -1 || j < 0)
        {
            l = 0x36ee80L;
        } else
        {
            l = (long)j * 60000L;
        }
        if (accountsettings.defaultEventDuration == -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        flag2 = accountsettings.tasksVisible;
        calendarcolor = CalendarApi.getColorFactory().createTaskColor(accountsettings.taskColor);
        j = accountsettings.holidayColor;
        obj = CalendarApi.getColorFactory();
        if (j == 0)
        {
            obj1 = ((ColorFactory) (obj)).defaultHolidayColor();
        } else
        {
            obj1 = ((ColorFactory) (obj)).createHolidayColor(j);
        }
        obj = accountsettings.smartmailSetting;
        j = -1;
        ((String) (obj)).hashCode();
        JVM INSTR lookupswitch 4: default 264
    //                   -2137067054: 454
    //                   -459319469: 438
    //                   656290080: 422
    //                   1996002556: 406;
           goto _L6 _L7 _L8 _L9 _L10
_L6:
        j;
        JVM INSTR tableswitch 0 2: default 292
    //                   0 470
    //                   1 477
    //                   2 484;
           goto _L11 _L12 _L13 _L14
_L11:
        obj = GoogleSettings.SmartMailMode.IGNORE;
_L16:
        accountsettings = accountsettings.birthdaySetting;
        if (((BirthdaySetting) (accountsettings)).visibility)
        {
            if (((BirthdaySetting) (accountsettings)).includeGplusBirthday)
            {
                j = 0;
            } else
            {
                j = 1;
            }
        } else
        {
            j = 2;
        }
        return new GoogleSettingsImpl(account, anotification, anotification1, flag1, i, s, l, flag, flag2, calendarcolor, ((CalendarColor) (obj1)), ((GoogleSettings.SmartMailMode) (obj)), BirthdayModeConverter.storeToApi(j));
_L5:
        i = 4;
          goto _L15
_L4:
        i = 3;
          goto _L15
_L3:
        i = 2;
          goto _L15
_L2:
        i = 1;
          goto _L15
_L10:
        if (((String) (obj)).equals("CREATE"))
        {
            j = 0;
        }
          goto _L6
_L9:
        if (((String) (obj)).equals("CREATE_PRIVATE"))
        {
            j = 1;
        }
          goto _L6
_L8:
        if (((String) (obj)).equals("CREATE_SECRET"))
        {
            j = 2;
        }
          goto _L6
_L7:
        if (((String) (obj)).equals("IGNORE"))
        {
            j = 3;
        }
          goto _L6
_L12:
        obj = GoogleSettings.SmartMailMode.CREATE;
          goto _L16
_L13:
        obj = GoogleSettings.SmartMailMode.CREATE_PRIVATE;
          goto _L16
_L14:
        obj = GoogleSettings.SmartMailMode.CREATE_SECRET;
          goto _L16
    }

    public final void initialize(Context context)
    {
    }

    final Settings lambda$update$1$SettingsApiStoreImpl(SettingsModifications settingsmodifications)
        throws Exception
    {
        Account account;
        boolean flag;
        flag = true;
        account = settingsmodifications.getDescriptor();
        if (settingsmodifications.arePreferredNotificationsModified(1))
        {
            NotificationsTimelyStoreUtils.storePreferredNotifications(settingsmodifications.getPreferredNotifications(1), account, false);
        }
        if (settingsmodifications.arePreferredNotificationsModified(2))
        {
            NotificationsTimelyStoreUtils.storePreferredNotifications(settingsmodifications.getPreferredNotifications(2), account, true);
        }
        if (!(settingsmodifications instanceof GoogleSettingsModifications)) goto _L2; else goto _L1
_L1:
        AccountSettingsStore accountsettingsstore = TimelyStore.acquire(CalendarApi.getApiAppContext()).accountSettingsStore;
        if (!settingsmodifications.isModified()) goto _L4; else goto _L3
_L3:
        Object obj = account.name;
        if (accountsettingsstore.cache.containsKey(obj)) goto _L5; else goto _L4
_L4:
        return null;
_L5:
        String s;
        com.google.android.apps.calendar.timely.store.AccountSettingsStore.UpdateBuilder updatebuilder;
        GoogleSettingsModifications googlesettingsmodifications;
        int i;
        googlesettingsmodifications = (GoogleSettingsModifications)settingsmodifications;
        updatebuilder = new com.google.android.apps.calendar.timely.store.AccountSettingsStore.UpdateBuilder();
        if (googlesettingsmodifications.isTimezoneIdModified())
        {
            obj = googlesettingsmodifications.getTimezoneId();
            updatebuilder.values.put("timezone", ((String) (obj)));
            updatebuilder.triggerSyncAdapterUpdate = true;
        }
        if (googlesettingsmodifications.isTasksVisibleModified())
        {
            boolean flag1 = googlesettingsmodifications.areTasksVisible();
            obj = updatebuilder.values;
            class .Lambda._cls0
                implements Callable
            {

                private final Account arg$1;

                public final Object call()
                {
                    return SettingsApiStoreImpl.lambda$read$0$SettingsApiStoreImpl(arg$1);
                }

            .Lambda._cls0(Account account)
            {
                arg$1 = account;
            }
            }

            if (flag1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            ((ContentValues) (obj)).put("tasksselected", Integer.valueOf(i));
        }
        if (googlesettingsmodifications.isHolidayColorModified())
        {
            i = googlesettingsmodifications.getHolidayColor().getValue();
            updatebuilder.values.put("holidayscolor", TimelyStoreUtils.colorIntToHex(i));
            updatebuilder.triggerSyncAdapterUpdate = true;
        }
        if (googlesettingsmodifications.isTaskColorModified())
        {
            i = googlesettingsmodifications.getTaskColor().getValue();
            updatebuilder.values.put("taskscolor", TimelyStoreUtils.colorIntToHex(i));
        }
        if (googlesettingsmodifications.isBirthdayModeModified())
        {
            updatebuilder.setBirthdaySetting(BirthdayModeConverter.apiToStore(googlesettingsmodifications.getBirthdayMode()));
        }
        if (!googlesettingsmodifications.isSmartMailModeModified()) goto _L7; else goto _L6
_L6:
        googlesettingsmodifications.getSmartMailMode().ordinal();
        JVM INSTR tableswitch 0 2: default 360
    //                   0 555
    //                   1 562
    //                   2 569;
           goto _L8 _L9 _L10 _L11
_L8:
        obj = "IGNORE";
_L15:
        googlesettingsmodifications.getSmartMailUpdateMode().ordinal();
        JVM INSTR tableswitch 1 2: default 396
    //                   1 576
    //                   2 583;
           goto _L12 _L13 _L14
_L12:
        s = "APPLY_TO_ALL_EVENTS";
_L16:
        updatebuilder.values.put("smartMailDelivery", ((String) (obj)));
        if (Platform.stringIsNullOrEmpty(s))
        {
            obj = null;
        } else
        {
            obj = TimelyStoreUtils.jsonFromValues("smartMailDeliveryChangeBehavior", s);
        }
        updatebuilder.jsonFlags = ((String) (obj));
        updatebuilder.triggerSyncAdapterUpdate = true;
_L7:
        if (googlesettingsmodifications.isDefaultEventDurationMillisModified() || googlesettingsmodifications.isEndTimeUnspecifiedByDefaultModified())
        {
            if (googlesettingsmodifications.isEndTimeUnspecifiedByDefault())
            {
                i = -1;
            } else
            {
                i = (int)TimeUnit.MILLISECONDS.toMinutes(googlesettingsmodifications.getDefaultEventDurationMillis());
            }
            if (i == -1)
            {
                updatebuilder.values.put("defaultNoEndTime", Integer.valueOf(1));
            } else
            {
                updatebuilder.values.put("defaultNoEndTime", Integer.valueOf(0));
                updatebuilder.values.put("defaultEventLength", Integer.valueOf(i));
            }
            updatebuilder.triggerSyncAdapterUpdate = true;
        }
        if (updatebuilder.values.size() == 0)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            obj = null;
        } else
        {
            obj = updatebuilder;
        }
        if (accountsettingsstore.updateFromClient(account, ((com.google.android.apps.calendar.timely.store.AccountSettingsStore.UpdateBuilder) (obj))) == null) goto _L4; else goto _L2
_L2:
        return (Settings)callWithMetrics(new .Lambda._cls0(settingsmodifications.getDescriptor()), ApiOperation.SETTINGS_READ);
_L9:
        obj = "CREATE";
          goto _L15
_L10:
        obj = "CREATE_PRIVATE";
          goto _L15
_L11:
        obj = "CREATE_SECRET";
          goto _L15
_L13:
        s = "APPLY_TO_NEW_EVENTS";
          goto _L16
_L14:
        s = "APPLY_TO_UPCOMING_EVENTS";
          goto _L16
    }

    public final Settings[] list()
        throws IOException
    {
        class .Lambda._cls2
            implements Callable
        {

            public static final Callable $instance = new .Lambda._cls2();

            public final Object call()
            {
                return SettingsApiStoreImpl.lambda$list$4$SettingsApiStoreImpl();
            }


            private .Lambda._cls2()
            {
            }
        }

        return (Settings[])callWithMetrics(.Lambda._cls2..instance, ApiOperation.SETTINGS_LIST);
    }

    public final Settings read(Account account)
        throws IOException
    {
        return (Settings)callWithMetrics(new .Lambda._cls0(account), ApiOperation.SETTINGS_READ);
    }

    public final Settings update(SettingsModifications settingsmodifications)
        throws IOException
    {
        class .Lambda._cls1
            implements Callable
        {

            private final SettingsApiStoreImpl arg$1;
            private final SettingsModifications arg$2;

            public final Object call()
            {
                return arg$1.lambda$update$1$SettingsApiStoreImpl(arg$2);
            }

            .Lambda._cls1(SettingsModifications settingsmodifications)
            {
                arg$1 = SettingsApiStoreImpl.this;
                arg$2 = settingsmodifications;
            }
        }

        return (Settings)callWithMetrics(new .Lambda._cls1(settingsmodifications), ApiOperation.SETTINGS_UPDATE);
    }

}
