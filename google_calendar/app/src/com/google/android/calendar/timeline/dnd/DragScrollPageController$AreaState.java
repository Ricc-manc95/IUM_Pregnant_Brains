// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.dnd;

import android.os.Handler;
import android.os.SystemClock;

public final class callbackInterval
{

    public static final Handler HANDLER = new Handler();
    public final Callback callback;
    public final long callbackInterval;
    public final Runnable callbackRunner = new _cls1();
    public float currentDepth;
    public final int edge;
    public long enterTime;


    _cls1.this._cls0(int i, Callback callback1, long l)
    {
        class _cls1
            implements Runnable
        {

            private final DragScrollPageController.AreaState this$0;

            public final void run()
            {
                class Callback
                {

                    public abstract void enterArea$514IILG_0();

                    public abstract void inArea(int j, float f, long l1);

                    public abstract void leaveArea$514IILG_0();
                }

                callback.inArea(edge, currentDepth, SystemClock.uptimeMillis() - enterTime);
                DragScrollPageController.AreaState.HANDLER.postDelayed(this, callbackInterval);
            }

            _cls1()
            {
                this$0 = DragScrollPageController.AreaState.this;
                super();
            }
        }

        edge = i;
        callback = callback1;
        callbackInterval = l;
    }
}
