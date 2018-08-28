// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            EventDeleteFlow

final class this._cls0
    implements FutureCallback
{

    private final EventDeleteFlow this$0;

    public final void onFailure(Throwable throwable)
    {
        EventDeleteFlow eventdeleteflow;
        Object obj;
        Fragment fragment;
        LogUtils.e(EventDeleteFlow.TAG, throwable, "Unable to delete", new Object[0]);
        eventdeleteflow = EventDeleteFlow.this;
        obj = new ambda._cls2(eventdeleteflow, false);
        fragment = ((Fragment) (eventdeleteflow)).mTarget;
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
        throwable = eventdeleteflow.getContext();
        if (throwable != null)
        {
            Toast.makeText(throwable, 0x7f1301ab, 0).show();
        }
        if (eventdeleteflow == null)
        {
            break MISSING_BLOCK_LABEL_336;
        }
        obj = ((Fragment) (eventdeleteflow)).mFragmentManager;
        if (eventdeleteflow == null) goto _L10; else goto _L9
_L9:
        if (((Fragment) (eventdeleteflow)).mHost != null && ((Fragment) (eventdeleteflow)).mAdded)
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
            ((Fragment) (eventdeleteflow)).mFragmentManager.beginTransaction().remove(eventdeleteflow);
        }
        return;
_L7:
label0:
        {
            if (fragment.mHost == null)
            {
                throwable = null;
            } else
            {
                throwable = (FragmentActivity)fragment.mHost.mActivity;
            }
            if (throwable != null && !throwable.isDestroyed() && !throwable.isFinishing())
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
        if (((Fragment) (eventdeleteflow)).mHost == null)
        {
            throwable = null;
        } else
        {
            throwable = (FragmentActivity)((Fragment) (eventdeleteflow)).mHost.mActivity;
        }
        if (throwable == null || throwable.isDestroyed() || throwable.isFinishing())
        {
            flag = false;
        } else
        {
            if (obj == null || ((FragmentManager) (obj)).isDestroyed())
            {
                break MISSING_BLOCK_LABEL_336;
            }
            flag = true;
        }
          goto _L15
        flag = false;
          goto _L15
    }

    public final void onSuccess(Object obj)
    {
        EventDeleteFlow eventdeleteflow;
        Object obj1;
        Fragment fragment;
        eventdeleteflow = EventDeleteFlow.this;
        obj1 = new ambda._cls2(eventdeleteflow, true);
        fragment = ((Fragment) (eventdeleteflow)).mTarget;
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
        if (eventdeleteflow.getContext() == null);
        if (eventdeleteflow == null)
        {
            break MISSING_BLOCK_LABEL_310;
        }
        obj1 = ((Fragment) (eventdeleteflow)).mFragmentManager;
        if (eventdeleteflow == null) goto _L10; else goto _L9
_L9:
        if (((Fragment) (eventdeleteflow)).mHost != null && ((Fragment) (eventdeleteflow)).mAdded)
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
            ((Fragment) (eventdeleteflow)).mFragmentManager.beginTransaction().remove(eventdeleteflow);
        }
        return;
_L7:
label0:
        {
            if (fragment.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)fragment.mHost.mActivity;
            }
            if (obj != null && !((Activity) (obj)).isDestroyed() && !((Activity) (obj)).isFinishing())
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
        if (((Fragment) (eventdeleteflow)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (eventdeleteflow)).mHost.mActivity;
        }
        if (obj == null || ((Activity) (obj)).isDestroyed() || ((Activity) (obj)).isFinishing())
        {
            flag = false;
        } else
        {
            if (obj1 == null || ((FragmentManager) (obj1)).isDestroyed())
            {
                break MISSING_BLOCK_LABEL_310;
            }
            flag = true;
        }
          goto _L15
        flag = false;
          goto _L15
    }

    ambda._cls2()
    {
        this$0 = EventDeleteFlow.this;
        super();
    }
}
