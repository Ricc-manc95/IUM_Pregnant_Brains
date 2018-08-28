// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.grid.views;

import android.view.MotionEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.gridviews.GridDayView;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.grid.views:
//            ProposeNewTimeGridDayView

final class this._cls0 extends android.view.ews.ProposeNewTimeGridDayView.GestureListener
{

    private final ProposeNewTimeGridDayView this$0;

    public final boolean onDown(MotionEvent motionevent)
    {
        return tapListener != null;
    }

    public final boolean onSingleTapUp(MotionEvent motionevent)
    {
        if (tapListener == null)
        {
            return false;
        }
        ProposeNewTimeGridDayView proposenewtimegriddayview = ProposeNewTimeGridDayView.this;
        int j = Math.min(((int)motionevent.getY() * 24) / proposenewtimegriddayview.getHeight(), 23);
        proposenewtimegriddayview = ProposeNewTimeGridDayView.this;
        float f = ((float)(int)motionevent.getY() * 24F) / (float)proposenewtimegriddayview.getHeight();
        int k = (int)((f - (float)(int)f) * 60F);
        int i = j * 60 + k;
        boolean flag;
        if (currentProposal != null && i < currentProposal.getStartTime())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (currentProposal != null && currentProposal.getEndDay() == getJulianDay() && currentProposal.getEndTime() <= i)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (currentProposal == null || flag || i)
        {
            playSoundEffect(0);
            tapListener.p(j, k);
        }
        return true;
    }

    ()
    {
        this$0 = ProposeNewTimeGridDayView.this;
        super();
    }
}
