// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.timely.contract.TimelyContract;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.calendar.api.calendarlist.CalendarListApiStoreImpl;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListClientFutureImpl;
import com.google.android.calendar.api.calendarlist.CalendarListFactory;
import com.google.android.calendar.api.calendarlist.CalendarListFactoryImpl;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.color.ColorFactoryImpl;
import com.google.android.calendar.api.event.CPEventNotificationClient;
import com.google.android.calendar.api.event.EventApiStoreImpl;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventClientFutureImpl;
import com.google.android.calendar.api.event.EventDescriptorFactory;
import com.google.android.calendar.api.event.EventDescriptorFactoryImpl;
import com.google.android.calendar.api.event.EventFactory;
import com.google.android.calendar.api.event.EventFactoryImpl;
import com.google.android.calendar.api.event.EventNotificationClient;
import com.google.android.calendar.api.event.EventPermissionsFactory;
import com.google.android.calendar.api.event.EventPermissionsFactoryImpl;
import com.google.android.calendar.api.habit.HabitApiStoreImpl;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitClientFutureImpl;
import com.google.android.calendar.api.habit.HabitFactory;
import com.google.android.calendar.api.habit.HabitFactoryImpl;
import com.google.android.calendar.api.settings.SettingsApiStoreImpl;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.api.settings.SettingsClientFutureImpl;
import com.google.android.calendar.api.settings.SettingsFactory;
import com.google.android.calendar.api.settings.SettingsFactoryImpl;

// Referenced classes of package com.google.android.calendar.api:
//            CalendarApiFactory

public final class CalendarApiFactoryImpl extends CalendarApiFactory
{

    private final boolean optInV2aCalendars;
    private final boolean optInV2aEvents;
    private final boolean optInV2aHabits;
    private final boolean optInV2aSettings;

    public CalendarApiFactoryImpl(boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        optInV2aSettings = flag;
        optInV2aHabits = flag1;
        optInV2aEvents = flag2;
        optInV2aCalendars = flag3;
    }

    public final CalendarListClient getCalendarList()
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).uss();
            return new CalendarListClientFutureImpl(new CalendarListApiStoreImpl());
        }
    }

    public final CalendarListFactory getCalendarListFactory()
    {
        return new CalendarListFactoryImpl();
    }

    public final ColorFactory getColorFactory(Context context)
    {
        return ColorFactoryImpl.create(context.getResources());
    }

    public final EventDescriptorFactory getEventDescriptorFactory()
    {
        return new EventDescriptorFactoryImpl();
    }

    public final EventFactory getEventFactory()
    {
        return new EventFactoryImpl();
    }

    public final EventNotificationClient getEventNotifications()
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).uss();
            return new CPEventNotificationClient();
        }
    }

    public final EventPermissionsFactory getEventPermissionsFactory()
    {
        return new EventPermissionsFactoryImpl();
    }

    public final EventClient getEvents()
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).uss();
            return new EventClientFutureImpl(new EventApiStoreImpl(false));
        }
    }

    public final HabitFactory getHabitFactory()
    {
        return new HabitFactoryImpl();
    }

    public final HabitClient getHabits()
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).uss();
            return new HabitClientFutureImpl(new HabitApiStoreImpl());
        }
    }

    public final SettingsClient getSettings()
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).uss();
            return new SettingsClientFutureImpl(new SettingsApiStoreImpl());
        }
    }

    public final SettingsFactory getSettingsFactory()
    {
        return new SettingsFactoryImpl();
    }

    public final void initializeCaches(Context context)
    {
        Object obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).uss();
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj1)
            {
                return new com.google.android.apps.calendar.util.concurrent.Subscription..Lambda._cls0(Arrays.asList(new Subscription[0]));
            }


            private .Lambda._cls0()
            {
            }
        }

        obj = .Lambda._cls0..instance;
        class .Lambda._cls1
            implements Function
        {

            private final Context arg$1;

            public final Object apply(Object obj1)
            {
                Object obj2 = arg$1;
                obj1 = (Consumer)obj1;
                obj2 = TimelyStore.acquire(((Context) (obj2)));
                CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
                return ((TimelyStore) (obj2)).notificationSubscribers.subscribeFunction(new com.google.android.apps.calendar.util.concurrent.SubscriptionManager..Lambda._cls0(((Consumer) (obj1))), calendarexecutor);
            }

            .Lambda._cls1(Context context)
            {
                arg$1 = context;
            }
        }

        class .Lambda._cls2
            implements Function
        {

            private final Context arg$1;

            public final Object apply(Object obj1)
            {
                Object obj2 = arg$1;
                obj1 = (Consumer)obj1;
                obj2 = TimelyStore.acquire(((Context) (obj2)));
                CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
                return ((TimelyStore) (obj2)).conferenceSubscribers.subscribeFunction(new com.google.android.apps.calendar.util.concurrent.SubscriptionManager..Lambda._cls0(((Consumer) (obj1))), calendarexecutor);
            }

            .Lambda._cls2(Context context)
            {
                arg$1 = context;
            }
        }

        obj = new com.google.android.apps.calendar.util.api.CalendarListEntryCache..Lambda._cls0(context, new .Lambda._cls1(context), new .Lambda._cls2(context), ((Function) (obj)));
        CalendarListEntryCache.instance = new ListenableFutureCache(LogUtils.getLogTag(com/google/android/apps/calendar/util/api/CalendarListEntryCache), com.google.android.apps.calendar.util.api.CalendarListEntryCache..Lambda._cls1.$instance, ((Function) (obj)));
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).uss();
        class .Lambda._cls4
            implements Function
        {

            private final Context arg$1;

            public final Object apply(Object obj1)
            {
                Object obj2 = arg$1;
                obj1 = (Consumer)obj1;
                obj2 = TimelyStore.acquire(((Context) (obj2)));
                CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
                return ((TimelyStore) (obj2)).notificationSubscribers.subscribeFunction(new com.google.android.apps.calendar.util.concurrent.SubscriptionManager..Lambda._cls0(((Consumer) (obj1))), calendarexecutor);
            }

            .Lambda._cls4(Context context)
            {
                arg$1 = context;
            }
        }

        context = new com.google.android.apps.calendar.util.api.SettingsCache..Lambda._cls0(context, TimelyContract.ACCOUNT_SETTINGS_URI, new .Lambda._cls4(context));
        SettingsCache.instance = new ListenableFutureCache(LogUtils.getLogTag(com/google/android/apps/calendar/util/api/SettingsCache), com.google.android.apps.calendar.util.api.SettingsCache..Lambda._cls1.$instance, context);
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)context).uss();
            return;
        }
    }
}
