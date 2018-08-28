// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

// Referenced classes of package com.google.android.calendar.timely:
//            WeekRecyclerView

final class this._cls0 extends android.view.Listener
{

    public int actionDownOffset;
    public int currentOffset;
    public boolean receivedOnTouchEvent;
    private final WeekRecyclerView this$0;

    public final boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if (!receivedOnTouchEvent || Math.abs(f) < 2000F)
        {
            return false;
        }
        int i = getWidth();
        int j;
        if (f >= 0.0F)
        {
            i = -i;
        }
        j = actionDownOffset;
        smoothScrollBy((i + j) - currentOffset, 0);
        return true;
    }

    ()
    {
        this$0 = WeekRecyclerView.this;
        super();
    }
}
