// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitIdTypeUtil;
import com.google.android.calendar.time.TimeUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.calendar.v2a.android.util.provider.Projection;
import com.google.calendar.v2a.android.util.provider.Selection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventNotificationClientBase, HabitNotificationClient, EnumConverter, EventStoreUtils, 
//            AutoValue_HabitNotificationClient_HabitInstance, CpEventKey, AutoValue_NotificationInfo

public final class CPEventNotificationClient extends EventNotificationClientBase
{

    private static final String INSTANCES_PROJECTION[] = Projection.of(new String[] {
        "event_id", "begin", "end", "allDay", "sync_data8 AS sync_data8", "sync_data9 AS sync_data9", "calendar_id"
    });
    private static final String REMINDERS_PROJECTION[] = Projection.of(new String[] {
        "event_id", "minutes"
    });
    private static final Selection REMINDERS_SELECTION_BASE;

    public CPEventNotificationClient()
    {
    }

    private final Map getSortedReminderMinutesByEventIdMap(Iterable iterable)
    {
        Throwable throwable;
        Object obj;
        boolean flag1 = true;
        throwable = null;
        obj = Selection.where(REMINDERS_SELECTION_BASE);
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append(" AND ");
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append("event_id");
        obj = (new com.google.calendar.v2a.android.util.provider.Selection.Builder.ColumnExpression(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)))).this$0;
        boolean flag;
        if (!Iterables.isEmpty(iterable))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append(" IN(");
        for (iterable = iterable.iterator(); iterable.hasNext(); ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append(","))
        {
            Object obj1 = iterable.next();
            DatabaseUtils.appendValueToSql(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString, obj1);
        }

        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.deleteCharAt(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.length() - 1);
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append(")");
        Selection selection = new Selection(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.toString(), ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).argsCount);
        obj = CalendarApi.getApiContentResolver();
        Uri uri = android.provider.CalendarContract.Reminders.CONTENT_URI;
        String as[] = REMINDERS_PROJECTION;
        String s = selection.filterString;
        iterable = new String[0];
        if (iterable.length == selection.argsCount)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Invalid number of arguments"));
        }
        if (selection.argsCount == 0)
        {
            iterable = null;
        }
        obj = ((ContentResolver) (obj)).query(uri, as, s, iterable, null);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_276;
        }
        if (((Cursor) (obj)).getCount() != 0)
        {
            break MISSING_BLOCK_LABEL_318;
        }
        iterable = RegularImmutableMap.EMPTY;
label0:
        {
            if (obj != null)
            {
                if (true)
                {
                    break label0;
                }
                try
                {
                    ((Cursor) (obj)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Throwable throwable)
                {
                    ThrowableExtension.STRATEGY.addSuppressed(null, throwable);
                    return iterable;
                }
            }
            return iterable;
        }
        ((Cursor) (obj)).close();
        return iterable;
        iterable = new HashMap();
        int i;
        long l;
        for (; ((Cursor) (obj)).moveToNext(); ((List)iterable.get(Long.valueOf(l))).add(Integer.valueOf(i)))
        {
            l = ((Cursor) (obj)).getLong(0);
            i = ((Cursor) (obj)).getInt(1);
            if (!iterable.containsKey(Long.valueOf(l)))
            {
                iterable.put(Long.valueOf(l), new ArrayList());
            }
        }

          goto _L1
_L3:
        throw throwable;
        iterable;
        Iterator iterator;
        if (obj != null)
        {
            if (throwable != null)
            {
                try
                {
                    ((Cursor) (obj)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    ThrowableExtension.STRATEGY.addSuppressed(throwable, ((Throwable) (obj)));
                }
            } else
            {
                ((Cursor) (obj)).close();
            }
        }
        throw iterable;
_L1:
        try
        {
            for (iterator = iterable.keySet().iterator(); iterator.hasNext(); Collections.sort((List)iterable.get((Long)iterator.next()))) { }
            break MISSING_BLOCK_LABEL_486;
        }
        // Misplaced declaration of an exception variable
        catch (Throwable throwable) { }
        finally { }
        if (true) goto _L3; else goto _L2
_L2:
        if (obj != null)
        {
            if (false)
            {
                try
                {
                    ((Cursor) (obj)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Throwable throwable)
                {
                    ThrowableExtension.STRATEGY.addSuppressed(null, throwable);
                }
            } else
            {
                ((Cursor) (obj)).close();
            }
        }
        return iterable;
    }

    static final Long lambda$getInstanceCursor$1$CPEventNotificationClient(CalendarListEntry calendarlistentry)
    {
        return Long.valueOf(calendarlistentry.getDescriptor().calendarKey.getLocalId());
    }

    final Pair getEventNotificationsAndHabitInstances(Iterable iterable, long l, long l1)
    {
        Object obj;
        com.google.common.collect.ImmutableList.Builder builder;
        HashMap hashmap;
        Object obj1;
        builder = ImmutableList.builder();
        obj = ImmutableList.builder();
        hashmap = new HashMap();
        obj1 = iterable.iterator();
        do
        {
            if (!((Iterator) (obj1)).hasNext())
            {
                break;
            }
            CalendarListEntry calendarlistentry = (CalendarListEntry)((Iterator) (obj1)).next();
            if (!calendarlistentry.getDescriptor().calendarKey.equals(CalendarKey.NONE) && !calendarlistentry.getDescriptor().calendarKey.equals(CalendarKey.BIRTHDAY))
            {
                hashmap.put(Long.valueOf(calendarlistentry.getDescriptor().calendarKey.getLocalId()), calendarlistentry);
            }
        } while (true);
        long l2 = Math.max(HabitNotificationClient.MAX_FOLLOW_UP_NOTIFICATION_INTERVAL + HabitNotificationClient.HABIT_NOTIFICATION_EXPIRATION_INTERVAL, TimeUnit.HOURS.toMillis(12L));
        long l4 = TimeUnit.DAYS.toMillis(28L);
        long l6 = TimeUnit.HOURS.toMillis(12L);
        obj1 = android.provider.CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(((android.net.Uri.Builder) (obj1)), (l - l2) + 1L);
        ContentUris.appendId(((android.net.Uri.Builder) (obj1)), l4 + l6 + l1);
        obj1 = ((android.net.Uri.Builder) (obj1)).build();
        Object obj2 = Selection.where("selfAttendeeStatus").this$0;
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj2)).filterString.append("<>");
        DatabaseUtils.appendValueToSql(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj2)).filterString, Integer.valueOf(2));
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj2)).filterString.append(" AND ");
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj2)).filterString.append("calendar_id");
        obj2 = new com.google.calendar.v2a.android.util.provider.Selection.Builder.ColumnExpression(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj2)));
        class .Lambda._cls1
            implements Function
        {

            public static final Function $instance = new .Lambda._cls1();

            public final Object apply(Object obj7)
            {
                return CPEventNotificationClient.lambda$getInstanceCursor$1$CPEventNotificationClient((CalendarListEntry)obj7);
            }


            private .Lambda._cls1()
            {
            }
        }

        Object obj3 = .Lambda._cls1..instance;
        if (iterable == null)
        {
            throw new NullPointerException();
        }
        if (obj3 == null)
        {
            throw new NullPointerException();
        }
        obj3 = new com.google.common.collect.Iterables._cls5(iterable, ((Function) (obj3)));
        iterable = ((com.google.calendar.v2a.android.util.provider.Selection.Builder.ColumnExpression) (obj2)).this$0;
        boolean flag;
        if (!Iterables.isEmpty(((Iterable) (obj3))))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (iterable)).filterString.append(" IN(");
        for (obj2 = ((Iterable) (obj3)).iterator(); ((Iterator) (obj2)).hasNext(); ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (iterable)).filterString.append(","))
        {
            Object obj4 = ((Iterator) (obj2)).next();
            DatabaseUtils.appendValueToSql(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (iterable)).filterString, obj4);
        }

        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (iterable)).filterString.deleteCharAt(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (iterable)).filterString.length() - 1);
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (iterable)).filterString.append(")");
        Selection selection = new Selection(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (iterable)).filterString.toString(), ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (iterable)).argsCount);
        obj2 = CalendarApi.getApiContentResolver();
        String as[] = INSTANCES_PROJECTION;
        String s = selection.filterString;
        iterable = new String[0];
        if (iterable.length == selection.argsCount)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Invalid number of arguments"));
        }
        if (selection.argsCount == 0)
        {
            iterable = null;
        }
        obj1 = ((ContentResolver) (obj2)).query(((Uri) (obj1)), as, s, iterable, null);
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_552;
        }
        if (((Cursor) (obj1)).getCount() != 0)
        {
            break MISSING_BLOCK_LABEL_605;
        }
        iterable = Pair.create(ImmutableList.of(), ImmutableList.of());
label0:
        {
            if (obj1 != null)
            {
                if (true)
                {
                    break label0;
                }
                try
                {
                    ((Cursor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    ThrowableExtension.STRATEGY.addSuppressed(null, ((Throwable) (obj)));
                    return iterable;
                }
            }
            return iterable;
        }
        ((Cursor) (obj1)).close();
        return iterable;
        iterable = new com.google.common.collect.ImmutableSet.Builder();
        ((Cursor) (obj1)).moveToPosition(-1);
        com.google.common.collect.ImmutableSet.Builder builder1;
        while (((Cursor) (obj1)).moveToNext()) 
        {
            builder1 = (com.google.common.collect.ImmutableSet.Builder)iterable.add(Long.valueOf(((Cursor) (obj1)).getLong(0)));
        }
        Map map;
        map = getSortedReminderMinutesByEventIdMap(iterable.build());
        ((Cursor) (obj1)).moveToPosition(-1);
_L16:
        if (!((Cursor) (obj1)).moveToNext()) goto _L2; else goto _L1
_L1:
        long l5;
        long l7;
        long l8;
        l8 = ((Cursor) (obj1)).getLong(0);
        l7 = ((Cursor) (obj1)).getLong(1);
        l5 = ((Cursor) (obj1)).getLong(2);
        int i;
        boolean flag1;
        Throwable throwable;
        Throwable throwable1;
        Object obj5;
        Object obj6;
        long l3;
        long l9;
        if (((Cursor) (obj1)).getInt(3) == 1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        l3 = l5;
        if (l5 > l7) goto _L4; else goto _L3
_L3:
        if (i == 0) goto _L6; else goto _L5
_L5:
        l3 = TimeUnit.DAYS.toMillis(1L);
          goto _L7
_L4:
        if (i == 0) goto _L9; else goto _L8
_L8:
        l5 = TimeUtils.convertAlldayUtcToLocal(context, l7);
        l3 = TimeUtils.convertAlldayUtcToLocal(context, l3);
_L22:
        l9 = ((Cursor) (obj1)).getLong(6);
        if (hashmap.containsKey(Long.valueOf(l9)) && AccountUtil.isGoogleAccount(((CalendarListEntry)hashmap.get(Long.valueOf(l9))).getDescriptor().account))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!HabitNotificationClient.areHabitNotificationsSupported() || i == 0) goto _L11; else goto _L10
_L10:
        iterable = HabitIdTypeUtil.parseHabitIdAndType(((Cursor) (obj1)).getString(4));
        if (iterable == null) goto _L13; else goto _L12
_L12:
        if (iterable.length != 0)
        {
            break MISSING_BLOCK_LABEL_1334;
        }
          goto _L13
_L23:
        if (TextUtils.isEmpty(iterable)) goto _L11; else goto _L14
_L14:
        obj5 = ((CalendarListEntry)hashmap.get(Long.valueOf(l9))).getDescriptor();
        obj6 = ((Cursor) (obj1)).getString(5);
        i = EnumConverter.convertHabitStoreFlagsToStatus(((String) (obj6)));
        flag1 = EventStoreUtils.convertHabitStoreFlagsToStatusInferredValue(((String) (obj6)));
        if (i == 3 && !flag1 || i == 2) goto _L16; else goto _L15
_L15:
        iterable = new HabitDescriptor(((CalendarDescriptor) (obj5)), iterable);
        if (i == 3 && flag1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        iterable = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj)).add(new AutoValue_HabitNotificationClient_HabitInstance(iterable, CpEventKey.newInstance(l8), l5, l3, flag1));
          goto _L16
_L6:
        l3 = TimeUnit.HOURS.toMillis(1L);
          goto _L7
_L11:
        if (!map.containsKey(Long.valueOf(l8)) || l3 <= l) goto _L16; else goto _L17
_L17:
        iterable = CpEventKey.newInstance(l8, l7);
        obj5 = ((List)map.get(Long.valueOf(l8))).iterator();
_L19:
        if (!((Iterator) (obj5)).hasNext()) goto _L16; else goto _L18
_L18:
        i = ((Integer)((Iterator) (obj5)).next()).intValue();
        l7 = l5 - TimeUnit.MINUTES.toMillis(i);
label1:
        {
            if (l7 < l1)
            {
                break label1;
            }
            l3 = l7;
        }
          goto _L19
        obj6 = (com.google.common.collect.ImmutableList.Builder)builder.add(new AutoValue_NotificationInfo(iterable, NotificationInfo.NotificationType.EVENT, l7, l3));
        if (l7 <= l) goto _L16; else goto _L20
_L20:
        l3 = l7;
          goto _L19
_L2:
        if (obj1 != null)
        {
            if (false)
            {
                try
                {
                    ((Cursor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Iterable iterable)
                {
                    ThrowableExtension.STRATEGY.addSuppressed(null, iterable);
                }
            } else
            {
                ((Cursor) (obj1)).close();
            }
        }
        builder.forceCopy = true;
        iterable = ImmutableList.asImmutableList(builder.contents, builder.size);
        obj.forceCopy = true;
        return Pair.create(iterable, ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj)).size));
        throwable;
        throw throwable;
        iterable;
_L21:
        if (obj1 != null)
        {
            if (throwable != null)
            {
                try
                {
                    ((Cursor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Throwable throwable1)
                {
                    ThrowableExtension.STRATEGY.addSuppressed(throwable, throwable1);
                }
            } else
            {
                ((Cursor) (obj1)).close();
            }
        }
        throw iterable;
        iterable;
        throwable = null;
        if (true) goto _L21; else goto _L9
_L9:
        l5 = l7;
          goto _L22
_L7:
        l3 = l7 + l3;
          goto _L4
_L13:
        iterable = null;
          goto _L23
        iterable = iterable[0];
          goto _L23
    }

    final ListenableFuture getEventNotificationsAndHabitInstancesAsync(Iterable iterable, long l, long l1)
    {
        class .Lambda._cls0
            implements Callable
        {

            private final CPEventNotificationClient arg$1;
            private final Iterable arg$2;
            private final long arg$3;
            private final long arg$4;

            public final Object call()
            {
                return arg$1.getEventNotificationsAndHabitInstances(arg$2, arg$3, arg$4);
            }

            .Lambda._cls0(Iterable iterable, long l, long l1)
            {
                arg$1 = CPEventNotificationClient.this;
                arg$2 = iterable;
                arg$3 = l;
                arg$4 = l1;
            }
        }

        return (FluentFuture)CalendarExecutor.BACKGROUND.submit(new .Lambda._cls0(iterable, l, l1));
    }

    public final volatile void initialize(Context context)
    {
        super.initialize(context);
    }

    static 
    {
        com.google.calendar.v2a.android.util.provider.Selection.Builder builder = Selection.where("method").this$0;
        builder.filterString.append("=");
        DatabaseUtils.appendValueToSql(builder.filterString, Integer.valueOf(1));
        REMINDERS_SELECTION_BASE = new Selection(builder.filterString.toString(), builder.argsCount);
    }
}
