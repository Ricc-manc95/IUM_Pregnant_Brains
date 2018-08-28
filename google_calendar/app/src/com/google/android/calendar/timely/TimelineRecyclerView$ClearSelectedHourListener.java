// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.MotionEvent;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.FluentFuture;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineRecyclerView

static final class .Lambda._cls0 extends android.view.rSelectedHourListener
{

    public final boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if (Math.abs(f1) > 3000F)
        {
            class .Lambda._cls0
                implements Runnable
            {

                public static final Runnable $instance = new .Lambda._cls0();

                public final void run()
                {
                    CreateNewEventView.removeSelectedTime();
                }


            private .Lambda._cls0()
            {
            }
            }

            motionevent = (FluentFuture)CalendarExecutor.MAIN.submit(.Lambda._cls0..instance);
            return true;
        } else
        {
            return false;
        }
    }

    .Lambda._cls0()
    {
    }
}
