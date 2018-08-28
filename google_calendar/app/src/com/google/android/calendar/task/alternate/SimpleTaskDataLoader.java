// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import android.accounts.Account;
import android.content.Context;
import com.google.android.apps.calendar.timebox.task.TaskDataLoader;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.calendar.Utils;
import com.google.android.calendar.task.ArpTaskConnection;
import com.google.android.calendar.task.TaskConnection;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.reminders.model.RemindersBuffer;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.task.alternate:
//            TaskItemConverter

public final class SimpleTaskDataLoader
    implements TaskDataLoader
{

    public final Context context;
    public final ListenableFutureCache settingsCache;
    public final TaskConnection taskConnection = new ArpTaskConnection();
    public final TaskItemConverter taskItemConverter;
    public final TimeZone timeZone;

    public SimpleTaskDataLoader(Context context1, ListenableFutureCache listenablefuturecache)
    {
        context = context1.getApplicationContext();
        taskItemConverter = new TaskItemConverter(context1);
        timeZone = Utils.getTimeZone(context1);
        settingsCache = listenablefuturecache;
    }

    final List convertTasksForAccount(RemindersBuffer remindersbuffer, Account account, int i)
    {
        if (remindersbuffer != null) goto _L2; else goto _L1
_L1:
        account = Collections.emptyList();
_L4:
        return account;
_L2:
        class .Lambda._cls3
            implements Function
        {

            private final SimpleTaskDataLoader arg$1;
            private final Account arg$2;
            private final int arg$3;

            public final Object apply(Object obj)
            {
                Object obj2 = arg$1;
                Object obj1 = arg$2;
                int j = arg$3;
                obj = (Task)obj;
                Object obj3 = ((SimpleTaskDataLoader) (obj2)).taskItemConverter;
                obj1 = ((Account) (obj1)).name;
                obj2 = ((TaskItemConverter) (obj3)).dateTimeService;
                obj3 = ((TaskItemConverter) (obj3)).dateTimeService;
                long l;
                if (Clock.mockedTimestamp > 0L)
                {
                    l = Clock.mockedTimestamp;
                } else
                {
                    l = System.currentTimeMillis();
                }
                obj3 = DateTimeService.toDateTimeImpl((new DateTimeImpl(l, ((DateTimeService) (obj3)).calendarTimeZone)).withTime(0, 0, 0)).time;
                ((Time) (obj3)).writeFieldsToImpl();
                return TaskUtils.createTaskData(((Task) (obj)), ((String) (obj1)), j, ((DateTimeService) (obj2)), ((Time) (obj3)).impl.toMillis(false));
            }

            .Lambda._cls3(Account account, int i)
            {
                arg$1 = SimpleTaskDataLoader.this;
                arg$2 = account;
                arg$3 = i;
            }
        }

        account = new .Lambda._cls3(account, i);
        if (remindersbuffer != null)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        throw new NullPointerException();
        account;
        if (((AbstractDataBuffer) (remindersbuffer)).zzaKT != null)
        {
            ((AbstractDataBuffer) (remindersbuffer)).zzaKT.close();
        }
        throw account;
        if (account != null)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        throw new NullPointerException();
        java.util.ArrayList arraylist = Lists.newArrayList(new com.google.common.collect.Iterables._cls5(remindersbuffer, account));
        account = arraylist;
        if (((AbstractDataBuffer) (remindersbuffer)).zzaKT != null)
        {
            ((AbstractDataBuffer) (remindersbuffer)).zzaKT.close();
            return arraylist;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final FluentFuture loadAsync(int i, int j)
    {
        Object obj = settingsCache.getValueAsync();
        class .Lambda._cls0
            implements AsyncFunction
        {

            private final SimpleTaskDataLoader arg$1;
            private final int arg$2;
            private final int arg$3;

            public final ListenableFuture apply(Object obj1)
            {
                SimpleTaskDataLoader simpletaskdataloader = arg$1;
                int k = arg$2;
                int l = arg$3;
                ImmutableMap immutablemap = (ImmutableMap)obj1;
                long l1 = TimeBoxUtil.julianDayToMs(simpletaskdataloader.timeZone, k);
                long l2 = TimeBoxUtil.julianDayToMs(simpletaskdataloader.timeZone, l + 1);
                ArrayList arraylist = new ArrayList();
                Account aaccount[] = AccountsUtil.getGoogleAccounts(simpletaskdataloader.context);
                int i1 = aaccount.length;
                k = 0;
                while (k < i1) 
                {
                    Account account = aaccount[k];
                    obj1 = (Settings)immutablemap.get(account);
                    class .Lambda._cls1
                        implements Function
                    {

                        private final SimpleTaskDataLoader arg$1;
                        private final Account arg$2;
                        private final GoogleSettings arg$3;

                        public final Object apply(Object obj2)
                        {
                            SimpleTaskDataLoader simpletaskdataloader1 = arg$1;
                            Account account1 = arg$2;
                            GoogleSettings googlesettings = arg$3;
                            return simpletaskdataloader1.convertTasksForAccount((RemindersBuffer)obj2, account1, googlesettings.getTaskColor().getValue());
                        }

                        .Lambda._cls1(Account account, GoogleSettings googlesettings)
                        {
                            arg$1 = SimpleTaskDataLoader.this;
                            arg$2 = account;
                            arg$3 = googlesettings;
                        }
                    }

                    boolean flag;
                    if (obj1 instanceof GoogleSettings)
                    {
                        obj1 = (GoogleSettings)obj1;
                    } else
                    {
                        obj1 = null;
                    }
                    if (obj1 != null && ((GoogleSettings) (obj1)).areTasksVisible())
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        arraylist.add((FluentFuture)AbstractTransformFuture.create(simpletaskdataloader.taskConnection.loadTasksAsync(simpletaskdataloader.context, account.name, l1, l2), simpletaskdataloader. new .Lambda._cls1(account, ((GoogleSettings) (obj1))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE));
                    }
                    k++;
                }
                obj1 = new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(arraylist), true);
                class .Lambda._cls2
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls2();

                    public final Object apply(Object obj2)
                    {
                        obj2 = (List)obj2;
                        if (obj2 == null)
                        {
                            throw new NullPointerException();
                        } else
                        {
                            return ImmutableList.copyOf(new com.google.common.collect.FluentIterable._cls2(((Iterable) (obj2))));
                        }
                    }


                        private .Lambda._cls2()
                        {
                        }
                }

                if (obj1 instanceof FluentFuture)
                {
                    obj1 = (FluentFuture)obj1;
                } else
                {
                    obj1 = new ForwardingFluentFuture(((ListenableFuture) (obj1)));
                }
                return (FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj1)), .Lambda._cls2..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            }

            .Lambda._cls0(int i, int j)
            {
                arg$1 = SimpleTaskDataLoader.this;
                arg$2 = i;
                arg$3 = j;
            }
        }

        if (obj instanceof FluentFuture)
        {
            obj = (FluentFuture)obj;
        } else
        {
            obj = new ForwardingFluentFuture(((ListenableFuture) (obj)));
        }
        return (FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj)), new .Lambda._cls0(i, j), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }
}
