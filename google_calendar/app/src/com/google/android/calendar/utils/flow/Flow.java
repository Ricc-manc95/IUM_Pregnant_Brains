// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.flow;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;

public class Flow extends Fragment
{

    public Flow()
    {
    }

    public static boolean doesTargetFragmentExist(Fragment fragment)
    {
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
_L8:
        if (flag)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L7:
        if (fragment.mHost == null)
        {
            fragment = null;
        } else
        {
            fragment = (FragmentActivity)fragment.mHost.mActivity;
        }
        if (fragment == null || fragment.isDestroyed() || fragment.isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        flag = false;
        if (true) goto _L8; else goto _L2
_L2:
        return false;
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        Object obj;
        obj = super.mTarget;
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_148;
        }
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
_L6:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_148;
        }
        flag = true;
_L8:
        if (!flag)
        {
            setTargetFragment(null, 0);
        }
        super.onSaveInstanceState(bundle);
        return;
_L5:
label0:
        {
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            if (obj != null && !((Activity) (obj)).isDestroyed() && !((Activity) (obj)).isFinishing())
            {
                break label0;
            }
            flag = false;
        }
          goto _L6
        if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed()) goto _L2; else goto _L7
_L7:
        flag = true;
          goto _L6
_L2:
        flag = false;
          goto _L6
        flag = false;
          goto _L8
    }
}
