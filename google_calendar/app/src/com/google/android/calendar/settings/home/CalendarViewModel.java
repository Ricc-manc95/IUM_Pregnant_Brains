// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarCategory;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.calendarlist.CalendarListEntryModifications;
import com.google.android.calendar.api.calendarlist.CalendarListFactory;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.settings.SettingsShims;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// Referenced classes of package com.google.android.calendar.settings.home:
//            CalendarListItemViewModel

public class CalendarViewModel
    implements CalendarListItemViewModel
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/settings/home/CalendarViewModel);
    public List allDayNotifications;
    public Set allDayOptions;
    public final CalendarListEntry calendar;
    public CalendarColor calendarColor;
    private ListenableFuture calendarFuture;
    public String displayName;
    public boolean syncEnabled;
    public List timedNotifications;
    public Set timedOptions;

    CalendarViewModel(Context context, SettingsShims settingsshims, CalendarListEntry calendarlistentry)
    {
        class .Lambda._cls0
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls0();

            public final int compare(Object obj, Object obj1)
            {
                return CalendarViewModel.bridge$lambda$0$CalendarViewModel((Notification)obj, (Notification)obj1);
            }


            private .Lambda._cls0()
            {
            }
        }

        timedOptions = new TreeSet(.Lambda._cls0..instance);
        class .Lambda._cls1
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls1();

            public final int compare(Object obj, Object obj1)
            {
                return CalendarViewModel.bridge$lambda$0$CalendarViewModel((Notification)obj, (Notification)obj1);
            }


            private .Lambda._cls1()
            {
            }
        }

        allDayOptions = new TreeSet(.Lambda._cls1..instance);
        if (calendarlistentry == null)
        {
            throw new NullPointerException();
        }
        calendar = (CalendarListEntry)calendarlistentry;
        com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture immediatesuccessfulfuture;
        if (calendarlistentry == null)
        {
            immediatesuccessfulfuture = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            immediatesuccessfulfuture = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(calendarlistentry);
        }
        calendarFuture = immediatesuccessfulfuture;
        timedNotifications = new ArrayList(calendar.getDefaultNotifications(1));
        allDayNotifications = new ArrayList(calendar.getDefaultNotifications(2));
        timedOptions.addAll(settingsshims.getNotificationOptions(context, calendarlistentry.getDescriptor(), false));
        timedOptions.addAll(timedNotifications);
        allDayOptions.addAll(settingsshims.getNotificationOptions(context, calendarlistentry.getDescriptor(), true));
        allDayOptions.addAll(allDayNotifications);
        displayName = calendar.getDisplayName();
        syncEnabled = calendar.isSyncEnabled();
        calendarColor = calendar.getColor();
    }

    static final int bridge$lambda$0$CalendarViewModel(Notification notification, Notification notification1)
    {
        if (notification.minutesBefore != notification1.minutesBefore)
        {
            return notification.minutesBefore - notification1.minutesBefore;
        } else
        {
            return notification.method - notification1.method;
        }
    }

    static final void lambda$setColor$2$CalendarViewModel(CalendarColor calendarcolor, CalendarListEntryModifications calendarlistentrymodifications)
    {
        calendarlistentrymodifications.setColor(calendarcolor);
    }

    static final void lambda$setSyncEnabled$1$CalendarViewModel(boolean flag, CalendarListEntryModifications calendarlistentrymodifications)
    {
        calendarlistentrymodifications.setIsVisible(flag).setIsSyncEnabled(flag);
    }

    static final ListenableFuture lambda$updateCalendar$4$CalendarViewModel(Consumer consumer, CalendarListEntry calendarlistentry)
        throws Exception
    {
        calendarlistentry = CalendarApi.CalendarListFactory.modifyCalendarListEntry(calendarlistentry);
        consumer.accept(calendarlistentry);
        return CalendarApi.CalendarList.update(calendarlistentry);
    }

    static final void lambda$updateStore$3$CalendarViewModel(int i, List list, CalendarListEntryModifications calendarlistentrymodifications)
    {
        calendarlistentrymodifications.setDefaultNotifications(i, list);
    }

    public final CalendarColor getColor()
    {
        return calendarColor;
    }

    public final CharSequence getDisplayName(Resources resources)
    {
        if (calendar.isPrimary() && AccountUtil.isGoogleAccount(calendar.getDescriptor().account))
        {
            return resources.getString(0x7f1303b3);
        } else
        {
            return displayName;
        }
    }

    public final boolean isNameEditable()
    {
        if (!calendar.isPrimary())
        {
            String s = calendar.getDescriptor().account.type;
            boolean flag;
            if ("com.htc.pcsc".equals(s))
            {
                flag = true;
            } else
            {
                flag = "LOCAL".equals(s);
            }
            if (!flag && !calendar.getCategories().contains(CalendarCategory.FAMILY))
            {
                return true;
            }
        }
        return false;
    }

    public final boolean isSyncEditable()
    {
        String s = calendar.getDescriptor().account.type;
        boolean flag;
        if ("com.htc.pcsc".equals(s))
        {
            flag = true;
        } else
        {
            flag = "LOCAL".equals(s);
        }
        return !flag && (!calendar.isPrimary() || !calendar.isSyncEnabled());
    }

    public final void updateCalendar(Consumer consumer)
    {
        Object obj = calendarFuture;
        class .Lambda._cls6
            implements AsyncFunction
        {

            private final Consumer arg$1;

            public final ListenableFuture apply(Object obj1)
            {
                return CalendarViewModel.lambda$updateCalendar$4$CalendarViewModel(arg$1, (CalendarListEntry)obj1);
            }

            .Lambda._cls6(Consumer consumer)
            {
                arg$1 = consumer;
            }
        }

        class .Lambda._cls7
            implements AsyncFunction
        {

            private final CalendarViewModel arg$1;

            public final ListenableFuture apply(Object obj1)
            {
                obj1 = arg$1;
                return CalendarApi.CalendarList.read(((CalendarViewModel) (obj1)).calendar.getDescriptor());
            }

            .Lambda._cls7()
            {
                arg$1 = CalendarViewModel.this;
            }
        }

        com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor;
        if (obj instanceof FluentFuture)
        {
            obj = (FluentFuture)obj;
        } else
        {
            obj = new ForwardingFluentFuture(((ListenableFuture) (obj)));
        }
        calendarFuture = (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj)), new .Lambda._cls6(consumer), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), new .Lambda._cls7(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        consumer = calendarFuture;
        obj = new _cls1();
        directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            consumer.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(consumer, ((FutureCallback) (obj))), directexecutor);
            return;
        }
    }

    public final void updateStore(boolean flag)
    {
        class .Lambda._cls5
            implements Consumer
        {

            private final int arg$1;
            private final List arg$2;

            public final void accept(Object obj1)
            {
                CalendarViewModel.lambda$updateStore$3$CalendarViewModel(arg$1, arg$2, (CalendarListEntryModifications)obj1);
            }

            .Lambda._cls5(int i, List list)
            {
                arg$1 = i;
                arg$2 = list;
            }
        }

        Object obj;
        byte byte0;
        if (flag)
        {
            obj = allDayNotifications;
        } else
        {
            obj = timedNotifications;
        }
        obj = new ArrayList(new HashSet(((java.util.Collection) (obj))));
        if (flag)
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        updateCalendar(new .Lambda._cls5(byte0, ((List) (obj))));
    }


    private class _cls1
        implements FutureCallback
    {

        public final void onFailure(Throwable throwable)
        {
            LogUtils.wtf(CalendarViewModel.TAG, throwable, "Unable to update calendar", new Object[0]);
        }

        public final volatile void onSuccess(Object obj)
        {
        }

        _cls1()
        {
        }
    }

}
