// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.os.Bundle;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.flow.Flow;
import com.google.android.gms.reminders.model.Task;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;

public class ReminderSaveFlow extends Flow
    implements com.google.android.calendar.event.scope.ScopeSelectionDialog.OnScopeSelectedCallback
{
    public static final class Factory
    {

        public Factory()
        {
        }
    }

    public static interface Listener
    {

        public abstract void onTaskSaved(boolean flag, Task task1, int i);
    }


    public String accountName;
    public DateTimeService dateTimeService;
    public Task original;
    public Task task;

    public ReminderSaveFlow()
    {
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            accountName = bundle.getString("INSTANCE_ACCOUNT_NAME");
            task = (Task)bundle.getParcelable("INSTANCE_TASK");
            original = (Task)bundle.getParcelable("INSTANCE_ORIGINAL_TASK");
        }
        dateTimeService = DateTimeService.getInstance();
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putString("INSTANCE_ACCOUNT_NAME", accountName);
        bundle.putParcelable("INSTANCE_TASK", task);
        bundle.putParcelable("INSTANCE_ORIGINAL_TASK", original);
        super.onSaveInstanceState(bundle);
    }

    public final void onScopeSelected(int i, com.google.android.calendar.event.scope.ScopeSelectionDialog.Config config)
    {
        save(i);
    }

    final void save(int i)
    {
        Context context = CalendarApi.getApiAppContext();
        Object obj = accountName;
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).setDefaultTaskAccountValue(((String) (obj)), true);
            class .Lambda._cls0
                implements Callable
            {

                private final ReminderSaveFlow arg$1;
                private final Context arg$2;
                private final int arg$3;

                public final Object call()
                {
                    Object obj2 = arg$1;
                    Context context1 = arg$2;
                    int j = arg$3;
                    Object obj1 = ((ReminderSaveFlow) (obj2)).dateTimeService;
                    String s = ((ReminderSaveFlow) (obj2)).accountName;
                    Task task1 = ((ReminderSaveFlow) (obj2)).original;
                    Object obj3 = ((ReminderSaveFlow) (obj2)).task;
                    obj2 = new com.google.android.gms.reminders.model.Task.Builder(((Task) (obj3)));
                    boolean flag;
                    long l;
                    long l1;
                    boolean flag1;
                    if (Clock.mockedTimestamp > 0L)
                    {
                        l = Clock.mockedTimestamp;
                    } else
                    {
                        l = System.currentTimeMillis();
                    }
                    if (((Task) (obj3)).getDueDateMillis() != null)
                    {
                        l1 = ((Task) (obj3)).getDueDateMillis().longValue();
                    } else
                    {
                        com.google.android.gms.reminders.model.DateTime datetime = ((Task) (obj3)).getDueDate();
                        l1 = TaskUtils.dateTimeToMillis(Utils.getTimeZone(context1), datetime);
                    }
                    if (l1 > l)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag || TaskUtils.isRecurring(((Task) (obj3))))
                    {
                        obj2.zzcji = Boolean.valueOf(false);
                        obj2.zzcjj = Boolean.valueOf(true);
                        obj2.zzcjg = Boolean.valueOf(false);
                        obj2.zzcjk = Long.valueOf(l);
                    } else
                    {
                        obj2.zzcjj = Boolean.valueOf(false);
                    }
                    if (((Task) (obj3)).getRecurrenceInfo() != null)
                    {
                        com.google.android.gms.reminders.model.Recurrence recurrence = ((Task) (obj3)).getRecurrenceInfo().getRecurrence();
                        com.google.android.gms.reminders.model.DateTime datetime1 = ((Task) (obj3)).getDueDate();
                        obj1 = RecurrenceConverter.fromRfcRecurrenceString(((RecurRulePart)ReminderRecurrenceConverter.toApiRecurrence(recurrence).rrules.get(0)).toRfc5545String(), datetime1, ((DateTimeService) (obj1)));
                        obj3 = new com.google.android.gms.reminders.model.RecurrenceInfo.Builder(((Task) (obj3)).getRecurrenceInfo());
                        if (obj1 != null)
                        {
                            obj1 = (com.google.android.gms.reminders.model.Recurrence)((com.google.android.gms.reminders.model.Recurrence) (obj1)).freeze();
                        } else
                        {
                            obj1 = null;
                        }
                        obj3.zzciU = ((com.google.android.gms.reminders.model.Recurrence) (obj1));
                        obj1 = new zzab(((com.google.android.gms.reminders.model.RecurrenceInfo.Builder) (obj3)).zzciU, ((com.google.android.gms.reminders.model.RecurrenceInfo.Builder) (obj3)).zzciV, ((com.google.android.gms.reminders.model.RecurrenceInfo.Builder) (obj3)).zzciW, ((com.google.android.gms.reminders.model.RecurrenceInfo.Builder) (obj3)).zzciX, true);
                        if (obj1 != null)
                        {
                            obj1 = (RecurrenceInfo)((RecurrenceInfo) (obj1)).freeze();
                        } else
                        {
                            obj1 = null;
                        }
                        obj2.zzcjr = ((RecurrenceInfo) (obj1));
                    }
                    obj1 = ((com.google.android.gms.reminders.model.Task.Builder) (obj2)).build();
                    if (TaskDataFactory.instance == null)
                    {
                        TaskDataFactory.instance = new TaskDataFactory();
                    }
                    obj2 = TaskDataFactory.instance.getTaskConnection();
                    if (((Task) (obj1)).getTaskId() == null || Platform.stringIsNullOrEmpty(((Task) (obj1)).getTaskId().getClientAssignedId()))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        flag1 = ((TaskConnection) (obj2)).createTask(context1, s, ((Task) (obj1)));
                    } else
                    if (j == 0)
                    {
                        flag1 = ((TaskConnection) (obj2)).updateTask(context1, s, task1, ((Task) (obj1)));
                    } else
                    {
                        flag1 = ((TaskConnection) (obj2)).updateRecurrence(context1, s, task1, ((Task) (obj1)), 1);
                    }
                    return Boolean.valueOf(flag1);
                }

            .Lambda._cls0(Context context, int i)
            {
                arg$1 = ReminderSaveFlow.this;
                arg$2 = context;
                arg$3 = i;
            }
            }

            obj = (FluentFuture)CalendarExecutor.BACKGROUND.submit(new .Lambda._cls0(context, i));
            class .Lambda._cls1
                implements Runnable
            {

                private final ReminderSaveFlow arg$1;
                private final ListenableFuture arg$2;
                private final Context arg$3;
                private final int arg$4;

                public final void run()
                {
                    ReminderSaveFlow remindersaveflow;
                    Object obj1;
                    Fragment fragment;
                    android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
                    int j;
                    remindersaveflow = arg$1;
                    obj1 = arg$2;
                    Context context1 = arg$3;
                    int k = arg$4;
                    boolean flag = ((Boolean)Futures.getUnchecked(((java.util.concurrent.Future) (obj1)))).booleanValue();
                    if (flag)
                    {
                        obj1 = remindersaveflow.task;
                        class .Lambda._cls2
                            implements Consumer
                        {

                            private final ReminderSaveFlow arg$1;
                            private final boolean arg$2;
                            private final int arg$3;

                            public final void accept(Object obj2)
                            {
                                ReminderSaveFlow remindersaveflow1 = arg$1;
                                boolean flag1 = arg$2;
                                int l = arg$3;
                                ((Listener)obj2).onTaskSaved(flag1, remindersaveflow1.task, l);
                            }

                            .Lambda._cls2(boolean flag, int i)
                            {
                                arg$1 = ReminderSaveFlow.this;
                                arg$2 = flag;
                                arg$3 = i;
                            }
                        }

                        if (((Task) (obj1)).getTaskId() == null || Platform.stringIsNullOrEmpty(((Task) (obj1)).getTaskId().getClientAssignedId()))
                        {
                            j = 1;
                        } else
                        {
                            j = 0;
                        }
                        if (j != 0)
                        {
                            j = 0x7f130145;
                        } else
                        {
                            j = 0x7f130428;
                        }
                        Toast.makeText(context1, context1.getString(j), 0).show();
                    } else
                    {
                        Toast.makeText(context1, 0x7f1301ab, 0).show();
                    }
                    obj1 = remindersaveflow. new .Lambda._cls2(flag, k);
                    fragment = ((Fragment) (remindersaveflow)).mTarget;
                    if (fragment == null) goto _L2; else goto _L1
_L1:
                    if (fragment == null) goto _L4; else goto _L3
_L3:
                    fragmentmanagerimpl = fragment.mFragmentManager;
                    if (fragment == null) goto _L6; else goto _L5
_L5:
                    if (fragment.mHost != null && fragment.mAdded)
                    {
                        j = 1;
                    } else
                    {
                        j = 0;
                    }
                    if (j != 0) goto _L7; else goto _L6
_L6:
                    j = 0;
_L12:
                    if (j == 0) goto _L2; else goto _L8
_L8:
                    j = 1;
_L14:
                    if (j != 0)
                    {
                        ((Consumer) (obj1)).accept(fragment);
                    }
                    if (remindersaveflow == null)
                    {
                        break MISSING_BLOCK_LABEL_422;
                    }
                    obj1 = ((Fragment) (remindersaveflow)).mFragmentManager;
                    if (remindersaveflow == null) goto _L10; else goto _L9
_L9:
                    if (((Fragment) (remindersaveflow)).mHost != null && ((Fragment) (remindersaveflow)).mAdded)
                    {
                        j = 1;
                    } else
                    {
                        j = 0;
                    }
                    if (j != 0) goto _L11; else goto _L10
_L10:
                    j = 0;
_L15:
                    if (j != 0)
                    {
                        ((Fragment) (remindersaveflow)).mFragmentManager.beginTransaction().remove(remindersaveflow);
                    }
                    return;
_L7:
label0:
                    {
                        FragmentActivity fragmentactivity;
                        if (fragment.mHost == null)
                        {
                            fragmentactivity = null;
                        } else
                        {
                            fragmentactivity = (FragmentActivity)fragment.mHost.mActivity;
                        }
                        if (fragmentactivity != null && !fragmentactivity.isDestroyed() && !fragmentactivity.isFinishing())
                        {
                            break label0;
                        }
                        j = 0;
                    }
                      goto _L12
                    if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed()) goto _L4; else goto _L13
_L13:
                    j = 1;
                      goto _L12
_L4:
                    j = 0;
                      goto _L12
_L2:
                    j = 0;
                      goto _L14
_L11:
                    FragmentActivity fragmentactivity1;
                    if (((Fragment) (remindersaveflow)).mHost == null)
                    {
                        fragmentactivity1 = null;
                    } else
                    {
                        fragmentactivity1 = (FragmentActivity)((Fragment) (remindersaveflow)).mHost.mActivity;
                    }
                    if (fragmentactivity1 == null || fragmentactivity1.isDestroyed() || fragmentactivity1.isFinishing())
                    {
                        j = 0;
                    } else
                    {
                        if (obj1 == null || ((FragmentManager) (obj1)).isDestroyed())
                        {
                            break MISSING_BLOCK_LABEL_422;
                        }
                        j = 1;
                    }
                      goto _L15
                    j = 0;
                      goto _L15
                }

            .Lambda._cls1(ListenableFuture listenablefuture, Context context, int i)
            {
                arg$1 = ReminderSaveFlow.this;
                arg$2 = listenablefuture;
                arg$3 = context;
                arg$4 = i;
            }
            }

            ((ListenableFuture) (obj)).addListener(new .Lambda._cls1(((ListenableFuture) (obj)), context, i), CalendarExecutor.MAIN);
            return;
        }
    }
}
