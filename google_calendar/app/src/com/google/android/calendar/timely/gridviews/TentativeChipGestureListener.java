// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.graphics.Point;
import android.view.MotionEvent;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.Previewable;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;

public class TentativeChipGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/gridviews/TentativeChipGestureListener);
    public final com.google.android.calendar.timeline.chip.Chip.ChipActionListener actionListener;
    public final Chip chip;
    public final com.google.android.calendar.timeline.chip.Chip.ChipLongPressListener longPressListener;
    private final Previewable tentativeItem;

    public TentativeChipGestureListener(Chip chip1, Previewable previewable, com.google.android.calendar.timeline.chip.Chip.ChipActionListener chipactionlistener, com.google.android.calendar.timeline.chip.Chip.ChipLongPressListener chiplongpresslistener)
    {
        chip = chip1;
        tentativeItem = previewable;
        actionListener = chipactionlistener;
        longPressListener = chiplongpresslistener;
    }

    public boolean onDown(MotionEvent motionevent)
    {
        motionevent = (ListenableFuture)tentativeItem.resultRequest.get();
        return true;
    }

    public void onLongPress(MotionEvent motionevent)
    {
        Object obj = new Point((int)motionevent.getX(), (int)motionevent.getY());
        motionevent = (ListenableFuture)tentativeItem.resultRequest.get();
        class .Lambda._cls1
            implements Consumer
        {

            private final TentativeChipGestureListener arg$1;
            private final Point arg$2;

            public final void accept(Object obj1)
            {
                obj1 = arg$1;
                Point point = arg$2;
                ((TentativeChipGestureListener) (obj1)).longPressListener.onChipLongPress(((TentativeChipGestureListener) (obj1)).chip, point);
            }

            .Lambda._cls1(Point point)
            {
                arg$1 = TentativeChipGestureListener.this;
                arg$2 = point;
            }
        }

        obj = LogUtils.newFailureLoggingCallback(new .Lambda._cls1(((Point) (obj))), TAG, "item finalizer failed", new Object[0]);
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            motionevent.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(motionevent, ((com.google.common.util.concurrent.FutureCallback) (obj))), calendarexecutor);
            return;
        }
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        motionevent = (ListenableFuture)tentativeItem.resultRequest.get();
        class .Lambda._cls0
            implements Consumer
        {

            private final TentativeChipGestureListener arg$1;

            public final void accept(Object obj)
            {
                obj = arg$1;
                ((TentativeChipGestureListener) (obj)).actionListener.onChipPrimaryAction(((TentativeChipGestureListener) (obj)).chip);
            }

            .Lambda._cls0()
            {
                arg$1 = TentativeChipGestureListener.this;
            }
        }

        com.google.common.util.concurrent.FutureCallback futurecallback = LogUtils.newFailureLoggingCallback(new .Lambda._cls0(), TAG, "item finalizer failed", new Object[0]);
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (futurecallback == null)
        {
            throw new NullPointerException();
        } else
        {
            motionevent.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(motionevent, futurecallback), calendarexecutor);
            return true;
        }
    }

}
