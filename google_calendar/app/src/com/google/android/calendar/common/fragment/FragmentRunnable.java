// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.fragment;

import android.support.v4.app.Fragment;

public abstract class FragmentRunnable
    implements Runnable
{

    private final Fragment fragment;

    public FragmentRunnable(Fragment fragment1)
    {
        fragment = fragment1;
    }

    public abstract void go();

    public void run()
    {
        Fragment fragment1 = fragment;
        boolean flag;
        if (fragment1.mHost != null && fragment1.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return;
        } else
        {
            go();
            return;
        }
    }
}
