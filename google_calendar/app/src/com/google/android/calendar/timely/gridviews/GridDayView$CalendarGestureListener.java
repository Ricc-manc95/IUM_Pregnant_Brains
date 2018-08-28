// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridDayView

final class this._cls0 extends android.view.ner
{

    public final GridDayView this$0;

    public final boolean onSingleTapUp(MotionEvent motionevent)
    {
        Object obj = VisualElementHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)obj).recordTap(getContext(), GridDayView.this);
            obj = GridDayView.this;
            int i = Math.min(((int)motionevent.getY() * 24) / ((GridDayView) (obj)).getHeight(), 23);
            class .Lambda._cls0
                implements Runnable
            {

                private final GridDayView.CalendarGestureListener arg$1;
                private final int arg$2;

                public final void run()
                {
                    GridDayView.CalendarGestureListener calendargesturelistener = arg$1;
                    int j = arg$2;
                    CreateNewEventView.setSelectedTime(calendargesturelistener.this$0.getContext(), calendargesturelistener.this$0.julianDay, j);
                }

            .Lambda._cls0(int i)
            {
                arg$1 = GridDayView.CalendarGestureListener.this;
                arg$2 = i;
            }
            }

            ((ViewGroup)getParent()).getHandler().post(new .Lambda._cls0(i));
            return true;
        }
    }

    .Lambda._cls0()
    {
        this$0 = GridDayView.this;
        super();
    }
}
