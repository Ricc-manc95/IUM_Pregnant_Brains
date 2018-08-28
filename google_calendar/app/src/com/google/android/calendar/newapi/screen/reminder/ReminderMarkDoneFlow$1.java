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
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.task.TaskUtils;
import com.google.android.calendar.utils.flow.Flow;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderMarkDoneFlow

final class val.isDone
    implements FutureCallback
{

    private final ReminderMarkDoneFlow this$0;
    private final boolean val$isDone;

    public final void onFailure(Throwable throwable)
    {
        Object obj;
        LogUtils.e(ReminderMarkDoneFlow.TAG, throwable, "Failed marking task as done.", new Object[0]);
        obj = ReminderMarkDoneFlow.this;
        class .Lambda._cls1
            implements Consumer
        {

            public static final Consumer $instance = new .Lambda._cls1();

            public final void accept(Object obj1)
            {
                ((ReminderMarkDoneFlow.Listener)obj1).onTaskDoneStateChanged(false);
            }


            private .Lambda._cls1()
            {
            }
        }

        throwable = .Lambda._cls1..instance;
        obj = ((Fragment) (obj)).mTarget;
        if (Flow.doesTargetFragmentExist(((Fragment) (obj))))
        {
            throwable.accept(obj);
        }
        if (getContext() != null)
        {
            Toast.makeText(getContext(), 0x7f1301ab, 0).show();
        }
        obj = ReminderMarkDoneFlow.this;
        if (obj == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = ((Fragment) (obj)).mFragmentManager;
        if (obj == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (((Fragment) (obj)).mHost != null && ((Fragment) (obj)).mAdded)
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
            ((Fragment) (obj)).mFragmentManager.beginTransaction().remove(((Fragment) (obj)));
        }
        return;
_L5:
        if (((Fragment) (obj)).mHost == null)
        {
            throwable = null;
        } else
        {
            throwable = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
        }
        if (throwable == null || throwable.isDestroyed() || throwable.isFinishing())
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
    }

    public final void onSuccess(Object obj)
    {
        Object obj1;
        Object obj2;
        boolean flag;
        boolean flag1;
        flag1 = false;
        obj = (Long)obj;
        class .Lambda._cls0
            implements Consumer
        {

            public static final Consumer $instance = new .Lambda._cls0();

            public final void accept(Object obj3)
            {
                ((ReminderMarkDoneFlow.Listener)obj3).onTaskDoneStateChanged(true);
            }


            private .Lambda._cls0()
            {
            }
        }

        boolean flag2;
        if (!val$isDone)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        obj2 = ReminderMarkDoneFlow.this;
        obj1 = .Lambda._cls0..instance;
        obj2 = ((Fragment) (obj2)).mTarget;
        if (Flow.doesTargetFragmentExist(((Fragment) (obj2))))
        {
            ((Consumer) (obj1)).accept(obj2);
        }
        obj1 = getContext();
        if (obj1 != null)
        {
            TaskUtils.showReminderToast(((android.content.Context) (obj1)), 1, flag2, ((Long) (obj)));
        }
        obj1 = ReminderMarkDoneFlow.this;
        flag = flag1;
        if (obj1 == null) goto _L2; else goto _L1
_L1:
        obj2 = ((Fragment) (obj1)).mFragmentManager;
        flag = flag1;
        if (obj1 == null) goto _L2; else goto _L3
_L3:
        if (((Fragment) (obj1)).mHost != null && ((Fragment) (obj1)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L5; else goto _L4
_L4:
        flag = flag1;
_L2:
        if (flag)
        {
            ((Fragment) (obj1)).mFragmentManager.beginTransaction().remove(((Fragment) (obj1)));
        }
        return;
_L5:
        if (((Fragment) (obj1)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj1)).mHost.mActivity;
        }
        flag = flag1;
        if (obj != null)
        {
            flag = flag1;
            if (!((Activity) (obj)).isDestroyed())
            {
                flag = flag1;
                if (!((Activity) (obj)).isFinishing())
                {
                    flag = flag1;
                    if (obj2 != null)
                    {
                        flag = flag1;
                        if (!((FragmentManager) (obj2)).isDestroyed())
                        {
                            flag = true;
                        }
                    }
                }
            }
        }
        if (true) goto _L2; else goto _L6
_L6:
    }

    .Lambda._cls0()
    {
        this$0 = final_remindermarkdoneflow;
        val$isDone = Z.this;
        super();
    }
}
