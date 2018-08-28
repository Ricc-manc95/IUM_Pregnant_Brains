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
import com.google.android.calendar.newapi.overflow.DeletionConfirmationDialog;
import com.google.android.gms.reminders.model.RecurrenceInfo;
import com.google.android.gms.reminders.model.Task;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderDeleteFlow

final class arg._cls2
    implements Consumer
{

    private final String arg$1;
    private final Task arg$2;

    public final void accept(Object obj)
    {
        Task task;
        ReminderDeleteFlow reminderdeleteflow;
        String s = arg$1;
        task = arg$2;
        reminderdeleteflow = (ReminderDeleteFlow)obj;
        reminderdeleteflow.accountName = s;
        reminderdeleteflow.task = task;
        if (reminderdeleteflow == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = ((Fragment) (reminderdeleteflow)).mFragmentManager;
        if (reminderdeleteflow == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (((Fragment) (reminderdeleteflow)).mHost != null && ((Fragment) (reminderdeleteflow)).mAdded)
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
            obj = task.getRecurrenceInfo();
            if (obj != null && ((RecurrenceInfo) (obj)).getRecurrence() != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                break; /* Loop/switch isn't completed */
            }
            ScopeSelectionDialog.newInstance(reminderdeleteflow, (new com.google.android.calendar.event.scope.g.Builder()).dditionalArguments(new Bundle()).copes(Arrays.asList(new com.google.android.calendar.event.scope.copes[] {
                new AutoValue_ScopeSelectionDialog_Scope(0x7f13042d, 0), new AutoValue_ScopeSelectionDialog_Scope(0x7f13042b, 2)
            })).itle(0x7f130162).ositiveButton(0x7f130160).uild()).show(((Fragment) (reminderdeleteflow)).mFragmentManager, com/google/android/calendar/event/scope/ScopeSelectionDialog.getSimpleName());
        }
        return;
_L5:
        if (((Fragment) (reminderdeleteflow)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (reminderdeleteflow)).mHost.mActivity;
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
        DeletionConfirmationDialog.newInstance(reminderdeleteflow, 0x7f13015e).show(((Fragment) (reminderdeleteflow)).mFragmentManager, null);
        return;
    }

    (String s, Task task)
    {
        arg$1 = s;
        arg$2 = task;
    }
}
