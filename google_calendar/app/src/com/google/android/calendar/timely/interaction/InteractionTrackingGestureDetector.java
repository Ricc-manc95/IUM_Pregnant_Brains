// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

// Referenced classes of package com.google.android.calendar.timely.interaction:
//            InteractionTracker

public final class InteractionTrackingGestureDetector extends GestureDetector
{

    public InteractionTrackingGestureDetector(Context context, android.view.GestureDetector.OnGestureListener ongesturelistener)
    {
        super(context, ongesturelistener);
    }

    public final boolean onTouchEvent(MotionEvent motionevent)
    {
        if (motionevent.getActionMasked() == 0)
        {
            InteractionTracker.getInstance().trackInteractionStart(this, null);
        }
        boolean flag = super.onTouchEvent(motionevent);
        if (motionevent.getActionMasked() == 1 || motionevent.getActionMasked() == 3)
        {
            InteractionTracker.getInstance().trackInteractionEnd(this, null);
        }
        return flag;
    }
}
