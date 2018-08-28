// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskId;
import com.google.common.base.Platform;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderSaveFlow

final class arg._cls4
    implements Runnable
{

    private final ReminderSaveFlow arg$1;
    private final ListenableFuture arg$2;
    private final Context arg$3;
    private final int arg$4;

    public final void run()
    {
        ReminderSaveFlow remindersaveflow;
        Object obj;
        Fragment fragment;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        int i;
        remindersaveflow = arg$1;
        obj = arg$2;
        Context context = arg$3;
        int j = arg$4;
        boolean flag = ((Boolean)Futures.getUnchecked(((java.util.concurrent.Future) (obj)))).booleanValue();
        if (flag)
        {
            obj = remindersaveflow.task;
            if (((Task) (obj)).getTaskId() == null || Platform.stringIsNullOrEmpty(((Task) (obj)).getTaskId().getClientAssignedId()))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = 0x7f130145;
            } else
            {
                i = 0x7f130428;
            }
            Toast.makeText(context, context.getString(i), 0).show();
        } else
        {
            Toast.makeText(context, 0x7f1301ab, 0).show();
        }
        obj = new <init>(remindersaveflow, flag, j);
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
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L7; else goto _L6
_L6:
        i = 0;
_L12:
        if (i == 0) goto _L2; else goto _L8
_L8:
        i = 1;
_L14:
        if (i != 0)
        {
            ((Consumer) (obj)).accept(fragment);
        }
        if (remindersaveflow == null)
        {
            break MISSING_BLOCK_LABEL_422;
        }
        obj = ((Fragment) (remindersaveflow)).mFragmentManager;
        if (remindersaveflow == null) goto _L10; else goto _L9
_L9:
        if (((Fragment) (remindersaveflow)).mHost != null && ((Fragment) (remindersaveflow)).mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L11; else goto _L10
_L10:
        i = 0;
_L15:
        if (i != 0)
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
            i = 0;
        }
          goto _L12
        if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed()) goto _L4; else goto _L13
_L13:
        i = 1;
          goto _L12
_L4:
        i = 0;
          goto _L12
_L2:
        i = 0;
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
            i = 0;
        } else
        {
            if (obj == null || ((FragmentManager) (obj)).isDestroyed())
            {
                break MISSING_BLOCK_LABEL_422;
            }
            i = 1;
        }
          goto _L15
        i = 0;
          goto _L15
    }

    (ReminderSaveFlow remindersaveflow, ListenableFuture listenablefuture, Context context, int i)
    {
        arg$1 = remindersaveflow;
        arg$2 = listenablefuture;
        arg$3 = context;
        arg$4 = i;
    }
}
