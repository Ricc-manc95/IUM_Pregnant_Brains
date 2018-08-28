// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.transition.TransitionSet;
import android.view.View;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveFrequencyMoreOptionsFragment, AnimatorHelper

final class this._cls0
    implements android.view.MoreOptionsFragment._cls1
{

    private final GrooveFrequencyMoreOptionsFragment this$0;

    public final void onClick(View view)
    {
        view = ((View) (view.getTag()));
        if (!(view instanceof equency))
        {
            LogUtils.wtf(GrooveFrequencyMoreOptionsFragment.TAG, "Wrong tag type!", new Object[0]);
        } else
        {
            equency equency = (equency)view;
            GrooveFrequencyMoreOptionsFragment groovefrequencymoreoptionsfragment = GrooveFrequencyMoreOptionsFragment.this;
            if (android.os.cyMoreOptionsFragment >= 23)
            {
                if (((Fragment) (groovefrequencymoreoptionsfragment)).mHost == null)
                {
                    view = null;
                } else
                {
                    view = (FragmentActivity)((Fragment) (groovefrequencymoreoptionsfragment)).mHost.mActivity;
                }
                groovefrequencymoreoptionsfragment.setExitTransition(AnimatorHelper.createSlideTransition(view, true).addTarget(0x7f100144));
            }
            if (((Fragment) (groovefrequencymoreoptionsfragment)).mHost == null)
            {
                view = null;
            } else
            {
                view = (FragmentActivity)((Fragment) (groovefrequencymoreoptionsfragment)).mHost.mActivity;
            }
            if (view instanceof stener)
            {
                if (((Fragment) (groovefrequencymoreoptionsfragment)).mHost == null)
                {
                    view = null;
                } else
                {
                    view = (FragmentActivity)((Fragment) (groovefrequencymoreoptionsfragment)).mHost.mActivity;
                }
                ((stener)view).onFrequencyMoreOptionsSelected(equency.interval, equency.instancesPerInterval);
                return;
            }
        }
    }

    stener()
    {
        this$0 = GrooveFrequencyMoreOptionsFragment.this;
        super();
    }
}
