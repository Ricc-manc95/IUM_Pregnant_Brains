// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventListFragment, ICalImportFragment

final class val.importFragment
    implements FutureCallback
{

    private final ICalImportFragment val$importFragment;

    public final void onFailure(Throwable throwable)
    {
        throwable = val$importFragment;
        if (((Fragment) (throwable)).mFragmentManager.findFragmentByTag(ICalEventListFragment.TAG) != null)
        {
            throwable.scheduleRefresh();
            return;
        }
        if (((Fragment) (throwable)).mHost == null)
        {
            throwable = null;
        } else
        {
            throwable = (FragmentActivity)((Fragment) (throwable)).mHost.mActivity;
        }
        throwable.finish();
    }

    public final void onSuccess(Object obj)
    {
        obj = val$importFragment;
        if (((Fragment) (obj)).mFragmentManager.findFragmentByTag(ICalEventListFragment.TAG) != null)
        {
            ((ICalImportFragment) (obj)).scheduleRefresh();
            return;
        }
        if (((Fragment) (obj)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
        }
        ((FragmentActivity) (obj)).finish();
    }

    ()
    {
        val$importFragment = icalimportfragment;
        super();
    }
}
