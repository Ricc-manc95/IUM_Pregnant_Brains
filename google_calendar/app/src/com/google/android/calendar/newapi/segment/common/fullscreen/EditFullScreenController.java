// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common.fullscreen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.calendar.newapi.segment.common.navigation.OnNavigateAwayListener;

public abstract class EditFullScreenController extends Fragment
    implements OnNavigateAwayListener
{
    public static interface OnFullScreenResultListener
    {

        public abstract void onFullScreenClosed();

        public abstract void onFullScreenResult(Object obj);
    }


    public static final String TAG = com/google/android/calendar/newapi/segment/common/fullscreen/EditFullScreenController.getSimpleName();

    public EditFullScreenController()
    {
    }

    public final void close()
    {
        if (this == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (super.mHost != null && super.mAdded)
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
            super.mFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss();
        }
        return;
_L5:
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
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

    public abstract View createView(Context context);

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return createView(layoutinflater.getContext());
    }

    public void onDestroy()
    {
        if ((OnFullScreenResultListener)super.mTarget != null)
        {
            ((OnFullScreenResultListener)super.mTarget).onFullScreenClosed();
        }
        super.onDestroy();
    }

    public boolean onNavigateAway()
    {
        close();
        return true;
    }

}
