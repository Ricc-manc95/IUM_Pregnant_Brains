// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.android.calendar.newapi.segment.common.navigation.OnNavigateAwayListener;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            HostDialog

public class HostedFragment extends Fragment
    implements OnNavigateAwayListener
{

    public HostedFragment()
    {
    }

    public final void dismiss()
    {
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity != null)
        {
            Object obj = super.mView;
            if (obj != null)
            {
                ((InputMethodManager)((View) (obj)).getContext().getSystemService("input_method")).hideSoftInputFromWindow(((View) (obj)).getWindowToken(), 0);
            }
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = (HostDialog)((FragmentActivity) (obj)).mFragments.mHost.mFragmentManager.findFragmentByTag("HostDialog");
            if (obj != null)
            {
                ((DialogFragment) (obj)).dismiss();
                return;
            }
        }
    }

    public boolean onNavigateAway()
    {
        return false;
    }
}
