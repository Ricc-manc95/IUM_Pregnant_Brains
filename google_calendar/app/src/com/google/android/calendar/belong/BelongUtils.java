// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.apps.calendar.util.gms.GmsFutures;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitFilterOptions;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.activity.ActivityUtils;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.CombinedFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.belong:
//            FitOperationReceiver

public class BelongUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/belong/BelongUtils);

    public BelongUtils()
    {
    }

    public static ListenableFuture hasHabitsWithFitIntegrationAsync()
    {
        ListenableFutureCache listenablefuturecache = SettingsCache.instance;
        class .Lambda._cls2
            implements AsyncFunction
        {

            public static final AsyncFunction $instance = new .Lambda._cls2();

            public final ListenableFuture apply(Object obj)
            {
                return BelongUtils.lambda$hasHabitsWithFitIntegrationAsync$2$BelongUtils((ImmutableMap)obj);
            }


            private .Lambda._cls2()
            {
            }
        }

        if (listenablefuturecache == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            return AbstractTransformFuture.create(((ListenableFutureCache)listenablefuturecache).getValueAsync(), .Lambda._cls2..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
    }

    public static ListenableFuture hasHabitsWithFitIntegrationAsync(ListenableFuture listenablefuture)
    {
        return AbstractTransformFuture.create(listenablefuture, .Lambda._cls2..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    static boolean isAutoTrackingSupported(int i)
    {
        return i == 258 || i == 262;
    }

    static final Boolean lambda$hasHabitsWithFitIntegrationAsync$1$BelongUtils(List list)
        throws Exception
    {
        for (list = list.iterator(); list.hasNext();)
        {
            if (((Integer)((ListenableFuture)list.next()).get()).intValue() > 0)
            {
                return Boolean.valueOf(true);
            }
        }

        return Boolean.valueOf(false);
    }

    static final ListenableFuture lambda$hasHabitsWithFitIntegrationAsync$2$BelongUtils(ImmutableMap immutablemap)
        throws Exception
    {
        Object obj = new ArrayList();
        Object obj1 = (UnmodifiableIterator)((ImmutableSet)immutablemap.keySet()).iterator();
        do
        {
            if (!((Iterator) (obj1)).hasNext())
            {
                break;
            }
            Object obj3 = (Account)((Iterator) (obj1)).next();
            if (immutablemap.get(obj3) instanceof GoogleSettings)
            {
                Object obj2 = CalendarApi.Habits;
                obj3 = (new HabitFilterOptions(((Account) (obj3)).name)).setFitIntegrationStatus(Integer.valueOf(20));
                class .Lambda._cls3
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls3();

                    public final Object apply(Object obj4)
                    {
                        return Integer.valueOf(((com.google.android.calendar.api.habit.HabitClient.GenericResult)obj4).getCount());
                    }


            private .Lambda._cls3()
            {
            }
                }

                long l;
                if (Clock.mockedTimestamp > 0L)
                {
                    l = Clock.mockedTimestamp;
                } else
                {
                    l = System.currentTimeMillis();
                }
                obj3.activeAfterFilter = Long.valueOf(l);
                obj2 = ((HabitClient) (obj2)).count(((HabitFilterOptions) (obj3)));
                obj3 = .Lambda._cls3..instance;
                ((List) (obj)).add(AbstractTransformFuture.create(GmsFutures.asFuture(((com.google.android.gms.common.api.PendingResult) (obj2))), new com.google.android.apps.calendar.util.gms.GmsFutures..Lambda._cls0(((Function) (obj3))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE));
            }
        } while (true);
        immutablemap = new com.google.common.util.concurrent.Futures.FutureCombiner(true, ImmutableList.copyOf(((Iterable) (obj))));
        class .Lambda._cls4
            implements Callable
        {

            private final List arg$1;

            public final Object call()
            {
                return BelongUtils.lambda$hasHabitsWithFitIntegrationAsync$1$BelongUtils(arg$1);
            }

            .Lambda._cls4(List list)
            {
                arg$1 = list;
            }
        }

        obj = new .Lambda._cls4(((List) (obj)));
        obj1 = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        return new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (immutablemap)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (immutablemap)).allMustSucceed, ((java.util.concurrent.Executor) (obj1)), ((Callable) (obj)));
    }

    static final void lambda$startActivityCheck$0$BelongUtils(Context context, boolean flag, Boolean boolean1)
    {
        if (boolean1.booleanValue())
        {
            boolean1 = (new Intent("com.google.android.calendar.intent.action.FIT_ACTIVITY_CHECK")).setComponent(new ComponentName(context, com/google/android/calendar/belong/FitOperationReceiver));
            int i;
            if (flag)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            context.sendBroadcast(boolean1.putExtra("check_source", i));
        }
    }

    public static void log(Context context, String s, String s1)
    {
        if (context != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "fit", s, s1, null);
        }
    }

    static void log(Context context, String s, String s1, Long long1)
    {
        if (context == null)
        {
            return;
        }
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "fit", s, s1, long1);
            return;
        }
    }

    public static void onIntegrationStatusChange(Context context, boolean flag)
    {
        context.sendBroadcast((new Intent("com.google.android.calendar.intent.action.FIT_SUBSCRIPTION_REFRESH")).setComponent(new ComponentName(context, com/google/android/calendar/belong/FitOperationReceiver)));
        String s;
        if (flag)
        {
            s = "enabled";
        } else
        {
            s = "disabled";
        }
        if (context != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "fit", "integration_status", s, Long.valueOf(1L));
        }
    }

    static void onIntegrationStatusChange(Context context, boolean flag, long l)
    {
        context.sendBroadcast((new Intent("com.google.android.calendar.intent.action.FIT_SUBSCRIPTION_REFRESH")).setComponent(new ComponentName(context, com/google/android/calendar/belong/FitOperationReceiver)));
        String s;
        if (flag)
        {
            s = "enabled";
        } else
        {
            s = "disabled";
        }
        if (context != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "fit", "integration_status", s, Long.valueOf(l));
        }
    }

    public static void onLearnMoreLinkClicked(Context context, String s)
    {
        ActivityUtils.startActivityForUrl(context, "https://support.google.com/calendar?p=fit_goal_android", TAG);
        if (context != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "fit", s, "learn_more", null);
        }
    }

    static long roundTime(Context context, long l)
    {
        context = Calendar.getInstance(Utils.getTimeZone(context));
        context.setTimeInMillis(l);
        context.add(12, -(context.get(12) % 30));
        context.set(13, 0);
        context.set(14, 0);
        return context.getTimeInMillis();
    }

    public static void startActivityCheck(Context context, boolean flag)
    {
        class .Lambda._cls0
            implements Supplier
        {

            public static final Supplier $instance = new .Lambda._cls0();

            public final Object get()
            {
                return BelongUtils.hasHabitsWithFitIntegrationAsync();
            }


            private .Lambda._cls0()
            {
            }
        }

        Object obj = .Lambda._cls0..instance;
        com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor;
        if (flag)
        {
            obj = Boolean.valueOf(true);
            class .Lambda._cls1
                implements Consumer
            {

                private final Context arg$1;
                private final boolean arg$2;

                public final void accept(Object obj1)
                {
                    BelongUtils.lambda$startActivityCheck$0$BelongUtils(arg$1, arg$2, (Boolean)obj1);
                }

            .Lambda._cls1(Context context, boolean flag)
            {
                arg$1 = context;
                arg$2 = flag;
            }
            }

            if (obj == null)
            {
                obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
            }
        } else
        {
            obj = (ListenableFuture)((Supplier) (obj)).get();
        }
        context = LogUtils.newFailureLoggingCallback(new .Lambda._cls1(context, flag), TAG, "Unable to check habit fit integration.", new Object[0]);
        directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        if (context == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), context), directexecutor);
            return;
        }
    }

    public static void startActivityCheckForFollowup(Context context, EventKey eventkey)
    {
        context.sendBroadcast((new Intent("com.google.android.calendar.intent.action.FIT_ACTIVITY_CHECK")).setComponent(new ComponentName(context, com/google/android/calendar/belong/FitOperationReceiver)).putExtra("instance_id", eventkey).putExtra("check_source", 1));
    }

    public static void startIntegrationDisabling(Context context)
    {
        context.sendBroadcast((new Intent("com.google.android.calendar.intent.action.FIT_DISABLE_INTEGRATION")).setComponent(new ComponentName(context, com/google/android/calendar/belong/FitOperationReceiver)));
        if (context != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "fit", "disconnect", "clicked", null);
        }
    }

}
