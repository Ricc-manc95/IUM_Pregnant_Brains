// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderDeleteFlow

final class arg._cls3
    implements Runnable
{

    private final ReminderDeleteFlow arg$1;
    private final ListenableFuture arg$2;
    private final int arg$3;

    public final void run()
    {
        ReminderDeleteFlow reminderdeleteflow;
        Object obj;
        Fragment fragment;
        reminderdeleteflow = arg$1;
        ListenableFuture listenablefuture = arg$2;
        int i = arg$3;
        boolean flag1 = ((Boolean)Futures.getUnchecked(listenablefuture)).booleanValue();
        if (!flag1 && reminderdeleteflow.getContext() != null)
        {
            Toast.makeText(reminderdeleteflow.getContext(), 0x7f1301ab, 0).show();
        }
        obj = new <init>(flag1, i);
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
            ((Consumer) (obj)).accept(fragment);
        }
        if (reminderdeleteflow == null)
        {
            break MISSING_BLOCK_LABEL_353;
        }
        obj = ((Fragment) (reminderdeleteflow)).mFragmentManager;
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
            if (obj == null || ((FragmentManager) (obj)).isDestroyed())
            {
                break MISSING_BLOCK_LABEL_353;
            }
            flag = true;
        }
          goto _L15
        flag = false;
          goto _L15
    }

    (ReminderDeleteFlow reminderdeleteflow, ListenableFuture listenablefuture, int i)
    {
        arg$1 = reminderdeleteflow;
        arg$2 = listenablefuture;
        arg$3 = i;
    }
}
