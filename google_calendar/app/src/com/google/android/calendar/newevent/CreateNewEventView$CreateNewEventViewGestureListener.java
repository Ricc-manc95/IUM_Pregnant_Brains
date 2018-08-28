// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newevent;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import com.google.android.calendar.Utils;
import com.google.android.calendar.viewedit.callbacks.OnLaunchEdit;

// Referenced classes of package com.google.android.calendar.newevent:
//            CreateNewEventView

final class this._cls0 extends android.view.ventViewGestureListener
{

    private final CreateNewEventView this$0;

    public final boolean onSingleTapUp(MotionEvent motionevent)
    {
        long l = startTime;
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

        android.content.Context context;
        if (defaultDuration == -1L)
        {
            motionevent = null;
        } else
        {
            motionevent = Long.valueOf(startTime + defaultDuration * 60000L);
        }
        motionevent = Utils.getExtraEventBundle(l, motionevent, false);
        motionevent.putString("createEditSource", "grid");
        context = getContext();
        if (context instanceof OnLaunchEdit)
        {
            ((OnLaunchEdit)context).onLaunchInsertOrEdit(motionevent);
        }
        motionevent = getHandler();
        if (motionevent != null)
        {
            motionevent.postDelayed(.Lambda._cls0..instance, 1000L);
        }
        return true;
    }

    .Lambda._cls0()
    {
        this$0 = CreateNewEventView.this;
        super();
    }
}
