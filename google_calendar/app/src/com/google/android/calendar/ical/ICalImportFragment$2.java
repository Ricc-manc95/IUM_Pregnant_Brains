// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.google.android.calendar.EventFragmentHostActivity;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalImportFragment, NewICalActivity

final class this._cls0
    implements FutureCallback
{

    private final ICalImportFragment this$0;

    public final void onFailure(Throwable throwable)
    {
        onLaunchEventFailed();
    }

    public final void onSuccess(Object obj)
    {
        OverlayFragment overlayfragment = (OverlayFragment)obj;
        obj = ICalImportFragment.this;
        if (((Fragment) (obj)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
        }
        if (obj instanceof NewICalActivity)
        {
            obj = ICalImportFragment.this;
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            ((NewICalActivity)obj).showOverlayFragment("ViewScreenController", overlayfragment);
        }
    }

    yFragment()
    {
        this$0 = ICalImportFragment.this;
        super();
    }
}
