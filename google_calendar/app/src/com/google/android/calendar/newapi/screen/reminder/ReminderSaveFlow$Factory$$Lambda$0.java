// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.event.scope.AutoValue_ScopeSelectionDialog_Scope;
import com.google.android.calendar.event.scope.ScopeSelectionDialog;
import com.google.android.calendar.task.TaskUtils;
import com.google.android.gms.reminders.model.Task;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderSaveFlow

final class arg._cls3
    implements Consumer
{

    private final String arg$1;
    private final Task arg$2;
    private final Task arg$3;

    public final void accept(Object obj)
    {
        ReminderSaveFlow remindersaveflow;
        Task task;
        Task task2;
        String s = arg$1;
        task = arg$2;
        task2 = arg$3;
        remindersaveflow = (ReminderSaveFlow)obj;
        remindersaveflow.accountName = s;
        remindersaveflow.task = task2;
        remindersaveflow.original = task;
        if (remindersaveflow == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = ((Fragment) (remindersaveflow)).mFragmentManager;
        if (remindersaveflow == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (((Fragment) (remindersaveflow)).mHost != null && ((Fragment) (remindersaveflow)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L5; else goto _L4
_L4:
        flag = false;
_L7:
        if (flag)
        {
            if (TaskUtils.isRecurring(task))
            {
                break; /* Loop/switch isn't completed */
            }
            remindersaveflow.save(0);
        }
        return;
_L5:
        if (((Fragment) (remindersaveflow)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (remindersaveflow)).mHost.mActivity;
        }
        if (obj == null || ((Activity) (obj)).isDestroyed() || ((Activity) (obj)).isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
        if (!TaskUtils.isRecurring(task2))
        {
            remindersaveflow.save(1);
            return;
        }
        if (remindersaveflow.original != null)
        {
            obj = new com.google.android.gms.reminders.model.al(remindersaveflow.task);
            if (false)
            {
                throw new NullPointerException();
            }
            obj.al = null;
            Task task1 = ((com.google.android.gms.reminders.model.al) (obj)).al();
            obj = new com.google.android.gms.reminders.model.al(remindersaveflow.original);
            if (false)
            {
                throw new NullPointerException();
            }
            obj.al = null;
            if (task1.equals(((com.google.android.gms.reminders.model.al) (obj)).al()))
            {
                remindersaveflow.save(1);
                return;
            }
        }
        ScopeSelectionDialog.newInstance(remindersaveflow, (new com.google.android.calendar.event.scope.fig.Builder()).additionalArguments(new Bundle()).scopes(Arrays.asList(new com.google.android.calendar.event.scope..scopes[] {
            new AutoValue_ScopeSelectionDialog_Scope(0x7f13042d, 0), new AutoValue_ScopeSelectionDialog_Scope(0x7f13042b, 2)
        })).title(0x7f1301b9).positiveButton(0x7f130142).build()).show(((Fragment) (remindersaveflow)).mFragmentManager, com/google/android/calendar/event/scope/ScopeSelectionDialog.getSimpleName());
        return;
    }

    (String s, Task task, Task task1)
    {
        arg$1 = s;
        arg$2 = task;
        arg$3 = task1;
    }
}
