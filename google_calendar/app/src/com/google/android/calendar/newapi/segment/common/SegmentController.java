// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;

public abstract class SegmentController extends Fragment
{

    public Object model;
    public View view;

    public SegmentController()
    {
    }

    public static SegmentController newInstance(Fragment fragment, Class class1, Object obj)
    {
        SegmentController segmentcontroller = null;
        if (fragment == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = fragment.mFragmentManager;
        if (fragment == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (fragment.mHost != null && fragment.mAdded)
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
        if (!flag)
        {
            return null;
        }
        break; /* Loop/switch isn't completed */
_L5:
        FragmentActivity fragmentactivity;
        if (fragment.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)fragment.mHost.mActivity;
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
        Object obj1;
        String s;
        FragmentManager fragmentmanager;
        if (fragment.mHost == null)
        {
            obj1 = segmentcontroller;
        } else
        {
            obj1 = (FragmentActivity)fragment.mHost.mActivity;
        }
        s = class1.getSimpleName();
        fragmentmanager = fragment.getChildFragmentManager();
        segmentcontroller = (SegmentController)fragmentmanager.findFragmentByTag(s);
        fragment = segmentcontroller;
        if (segmentcontroller == null)
        {
            fragment = (SegmentController)Fragment.instantiate(((android.content.Context) (obj1)), class1.getName(), new Bundle());
            fragmentmanager.beginTransaction().add(fragment, s).commitAllowingStateLoss();
        }
        fragment.view = fragment.createView(LayoutInflater.from(((android.content.Context) (obj1))));
        fragment.model = obj;
        return fragment;
    }

    public abstract View createView(LayoutInflater layoutinflater);

    public abstract void onInitialize();
}
