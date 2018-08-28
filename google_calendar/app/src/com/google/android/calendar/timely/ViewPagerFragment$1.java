// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;

// Referenced classes of package com.google.android.calendar.timely:
//            ViewPagerFragment, BaseCalendarFragment

final class val.julianDay
    implements Runnable
{

    private final ViewPagerFragment this$0;
    private final int val$julianDay;
    private final int val$position;

    public final void run()
    {
        boolean flag;
        if (mState >= 5)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return;
        }
        if (sourceOfPageChange == 2)
        {
            Time time = mLastSelectedTime;
            Object obj = ViewPagerFragment.this;
            int i = ((ViewPagerFragment) (obj)).getFirstJulianDay(val$position);
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            obj = new Time(Utils.getTimeZoneId(((android.content.Context) (obj))));
            ((Time) (obj)).setJulianDaySafe(i);
            ((Time) (obj)).writeFieldsToImpl();
            time.set(((Time) (obj)).impl.toMillis(false));
        }
        updateMiniMonth(val$julianDay);
        updateTitle(val$position);
    }

    ()
    {
        this$0 = final_viewpagerfragment;
        val$position = i;
        val$julianDay = I.this;
        super();
    }
}
