// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.content.Context;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.reminders.Reminders;
import com.google.android.gms.reminders.RemindersApi;
import com.google.android.gms.reminders.model.Task;
import com.google.common.base.Absent;
import com.google.common.base.Present;
import com.google.common.base.VerifyException;
import com.google.common.collect.Iterators;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskConnection, ArpTaskDateTimeInCalendar

final class arg._cls5
    implements Callable
{

    private final ArpTaskConnection arg$1;
    private final Context arg$2;
    private final String arg$3;
    private final Set arg$4;
    private final boolean arg$5;

    public final Object call()
    {
        Object obj3 = arg$1;
        Context context = arg$2;
        String s = arg$3;
        Object obj2 = arg$4;
        boolean flag2 = arg$5;
        com.google.android.gms.common.api.GoogleApiClient googleapiclient = ((ArpTaskConnection) (obj3)).getClientAndBlockUntilConnected(context, s);
        Object aobj[] = (Object[])Array.newInstance(java/lang/String, 0);
        Object obj;
        boolean flag;
        if (obj2 instanceof Collection)
        {
            obj = (Collection)obj2;
        } else
        {
            java.util.Iterator iterator = ((Iterable) (obj2)).iterator();
            obj = new ArrayList();
            Iterators.addAll(((Collection) (obj)), iterator);
        }
        aobj = ((ArpTaskConnection) (obj3)).loadTasksFinish((com.google.android.gms.reminders.sult)((ArpTaskConnection) (obj3)).loadTasksByIdStart(context, s, (String[])((Collection) (obj)).toArray(aobj)).await(), ((Set) (obj2)).size());
        if (aobj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException();
        }
        obj2 = new ArrayList();
        obj3 = new ArrayList();
        int j = aobj.length;
        int i = 0;
        while (i < j) 
        {
            Object obj1 = aobj[i];
            com.google.android.gms.reminders.model.  = new com.google.android.gms.reminders.model.rt(((Task) (obj1)));
            if (flag2)
            {
                .rt = Boolean.valueOf(false);
                .rt = Boolean.valueOf(false);
                .rt = Boolean.valueOf(true);
                long l;
                if (Clock.mockedTimestamp > 0L)
                {
                    l = Clock.mockedTimestamp;
                } else
                {
                    l = System.currentTimeMillis();
                }
                .rt = Long.valueOf(l);
                obj1 = null;
            } else
            {
                .rt = Boolean.valueOf(false);
                .rt = Boolean.valueOf(false);
                DateTimeService datetimeservice = new DateTimeService(context);
                boolean flag1;
                if (((Task) (obj1)).getRecurrenceInfo() != null && ((Task) (obj1)).getDueDate() != null)
                {
                    obj1 = Long.valueOf(ArpTaskDateTimeInCalendar.getDueTimeMillis(((Task) (obj1)).getDueDate(), datetimeservice));
                    long l2 = ((Long) (obj1)).longValue();
                    long l1;
                    if (Clock.mockedTimestamp > 0L)
                    {
                        l1 = Clock.mockedTimestamp;
                    } else
                    {
                        l1 = System.currentTimeMillis();
                    }
                    if (l2 > l1)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                } else
                {
                    flag1 = false;
                    obj1 = null;
                }
                if (flag1)
                {
                    .meMillis = Boolean.valueOf(true);
                } else
                {
                    .meMillis = Boolean.valueOf(false);
                    if (false)
                    {
                        throw new NullPointerException();
                    }
                    .meMillis = null;
                    if (Clock.mockedTimestamp > 0L)
                    {
                        l1 = Clock.mockedTimestamp;
                    } else
                    {
                        l1 = System.currentTimeMillis();
                    }
                    obj1 = Long.valueOf(l1);
                }
            }
            ((List) (obj2)).add(.meMillis());
            ((List) (obj3)).add(obj1);
            i++;
        }
        if (((Status)Reminders.RemindersApi.batchUpdateReminder(googleapiclient, ((List) (obj2))).await()).zzaEP <= 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new VerifyException();
        }
        obj1 = (Long)((List) (obj3)).get(0);
        if (obj1 == null)
        {
            return Absent.INSTANCE;
        } else
        {
            return new Present(obj1);
        }
    }

    sult(ArpTaskConnection arptaskconnection, Context context, String s, Set set, boolean flag)
    {
        arg$1 = arptaskconnection;
        arg$2 = context;
        arg$3 = s;
        arg$4 = set;
        arg$5 = flag;
    }
}
