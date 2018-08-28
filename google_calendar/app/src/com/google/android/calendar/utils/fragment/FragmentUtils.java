// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public final class FragmentUtils
{

    public static Fragment attachFragment(Activity activity, FragmentManager fragmentmanager, Class class1, Fragment fragment, Bundle bundle)
    {
        if (!canCommitTransaction(activity, fragmentmanager))
        {
            return null;
        }
        String s = class1.getName();
        Fragment fragment1 = fragmentmanager.findFragmentByTag(s);
        if (class1.isInstance(fragment1))
        {
            fragment1.setTargetFragment(fragment, 0);
            return fragment1;
        } else
        {
            activity = Fragment.instantiate(activity, class1.getName(), bundle);
            activity.setTargetFragment(fragment, 0);
            fragmentmanager.beginTransaction().add(activity, s).commitNow();
            return activity;
        }
    }

    public static boolean canCommitTransaction(Activity activity, FragmentManager fragmentmanager)
    {
        while (activity == null || activity.isDestroyed() || activity.isFinishing() || fragmentmanager == null || fragmentmanager.isDestroyed()) 
        {
            return false;
        }
        return true;
    }
}
