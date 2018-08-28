// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.task.TaskDataFactory;
import com.google.android.calendar.utils.flow.Flow;
import com.google.android.gms.reminders.model.Task;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;

public class ReminderDeleteFlow extends Flow
    implements com.google.android.calendar.event.scope.ScopeSelectionDialog.OnScopeSelectedCallback, com.google.android.calendar.newapi.overflow.DeletionConfirmationDialog.OnDeleteConfirmedCallback
{
    public static final class Factory
    {

        public Factory()
        {
        }
    }

    public static interface Listener
    {

        public abstract void onTaskDeleted(boolean flag, int i);
    }


    public String accountName;
    public Task task;

    public ReminderDeleteFlow()
    {
    }

    private final void delete(int i)
    {
        Object obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)obj).trackEvent(getContext(), "reminder", "delete");
        obj = accountName;
        Task task1 = task;
        if (TaskDataFactory.instance == null)
        {
            TaskDataFactory.instance = new TaskDataFactory();
        }
        TaskConnection taskconnection = TaskDataFactory.instance.getTaskConnection();
        class .Lambda._cls1
            implements Callable
        {

            private final ReminderDeleteFlow arg$1;
            private final int arg$2;
            private final TaskConnection arg$3;
            private final String arg$4;
            private final Task arg$5;

            public final Object call()
            {
                ReminderDeleteFlow reminderdeleteflow = arg$1;
                int j = arg$2;
                TaskConnection taskconnection1 = arg$3;
                String s = arg$4;
                Task task2 = arg$5;
                if (j == 0)
                {
                    return Boolean.valueOf(taskconnection1.deleteTask(reminderdeleteflow.getContext(), s, task2));
                } else
                {
                    return Boolean.valueOf(taskconnection1.deleteRecurrence(reminderdeleteflow.getContext(), s, task2, 1));
                }
            }

            .Lambda._cls1(int i, TaskConnection taskconnection, String s, Task task1)
            {
                arg$1 = ReminderDeleteFlow.this;
                arg$2 = i;
                arg$3 = taskconnection;
                arg$4 = s;
                arg$5 = task1;
            }
        }

        obj = (FluentFuture)CalendarExecutor.BACKGROUND.submit(new .Lambda._cls1(i, taskconnection, ((String) (obj)), task1));
        class .Lambda._cls0
            implements Runnable
        {

            private final ReminderDeleteFlow arg$1;
            private final ListenableFuture arg$2;
            private final int arg$3;

            public final void run()
            {
                ReminderDeleteFlow reminderdeleteflow;
                Object obj1;
                Fragment fragment;
                reminderdeleteflow = arg$1;
                ListenableFuture listenablefuture = arg$2;
                int j = arg$3;
                boolean flag1 = ((Boolean)Futures.getUnchecked(listenablefuture)).booleanValue();
                if (!flag1 && reminderdeleteflow.getContext() != null)
                {
                    Toast.makeText(reminderdeleteflow.getContext(), 0x7f1301ab, 0).show();
                }
                class .Lambda._cls2
                    implements Consumer
                {

                    private final boolean arg$1;
                    private final int arg$2;

                    public final void accept(Object obj2)
                    {
                        ReminderDeleteFlow.lambda$delete$0$ReminderDeleteFlow(arg$1, arg$2, (Listener)obj2);
                    }

                        .Lambda._cls2(boolean flag, int i)
                        {
                            arg$1 = flag;
                            arg$2 = i;
                        }
                }

                obj1 = new .Lambda._cls2(flag1, j);
                fragment = ((Fragment) (reminderdeleteflow)).mTarget;
                if (fragment == null) goto _L2; else goto _L1
_L1:
                if (fragment == null) goto _L4; else goto _L3
_L3:
                android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = fragment.mFragmentManager;
                if (fragment == null) goto _L6; else goto _L5
_L5:
                boolean flag;
                if (fragment.mHost != null && fragment.mAdded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag) goto _L7; else goto _L6
_L6:
                flag = false;
_L12:
                if (!flag) goto _L2; else goto _L8
_L8:
                flag = true;
_L14:
                if (flag)
                {
                    ((Consumer) (obj1)).accept(fragment);
                }
                if (reminderdeleteflow == null)
                {
                    break MISSING_BLOCK_LABEL_353;
                }
                obj1 = ((Fragment) (reminderdeleteflow)).mFragmentManager;
                if (reminderdeleteflow == null) goto _L10; else goto _L9
_L9:
                if (((Fragment) (reminderdeleteflow)).mHost != null && ((Fragment) (reminderdeleteflow)).mAdded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag) goto _L11; else goto _L10
_L10:
                flag = false;
_L15:
                if (flag)
                {
                    ((Fragment) (reminderdeleteflow)).mFragmentManager.beginTransaction().remove(reminderdeleteflow);
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
                    flag = false;
                }
                  goto _L12
                if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed()) goto _L4; else goto _L13
_L13:
                flag = true;
                  goto _L12
_L4:
                flag = false;
                  goto _L12
_L2:
                flag = false;
                  goto _L14
_L11:
                FragmentActivity fragmentactivity1;
                if (((Fragment) (reminderdeleteflow)).mHost == null)
                {
                    fragmentactivity1 = null;
                } else
                {
                    fragmentactivity1 = (FragmentActivity)((Fragment) (reminderdeleteflow)).mHost.mActivity;
                }
                if (fragmentactivity1 == null || fragmentactivity1.isDestroyed() || fragmentactivity1.isFinishing())
                {
                    flag = false;
                } else
                {
                    if (obj1 == null || ((FragmentManager) (obj1)).isDestroyed())
                    {
                        break MISSING_BLOCK_LABEL_353;
                    }
                    flag = true;
                }
                  goto _L15
                flag = false;
                  goto _L15
            }

            .Lambda._cls0(ListenableFuture listenablefuture, int i)
            {
                arg$1 = ReminderDeleteFlow.this;
                arg$2 = listenablefuture;
                arg$3 = i;
            }
        }

        ((ListenableFuture) (obj)).addListener(new .Lambda._cls0(((ListenableFuture) (obj)), i), CalendarExecutor.MAIN);
    }

    static final void lambda$delete$0$ReminderDeleteFlow(boolean flag, int i, Listener listener)
    {
        listener.onTaskDeleted(flag, i);
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            accountName = bundle.getString("INSTANCE_ACCOUNT_NAME");
            task = (Task)bundle.getParcelable("INSTANCE_TASK");
        }
    }

    public final void onDeleteConfirmed()
    {
        delete(0);
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putString("INSTANCE_ACCOUNT_NAME", accountName);
        bundle.putParcelable("INSTANCE_TASK", task);
        super.onSaveInstanceState(bundle);
    }

    public final void onScopeSelected(int i, com.google.android.calendar.event.scope.ScopeSelectionDialog.Config config)
    {
        delete(i);
    }
}
