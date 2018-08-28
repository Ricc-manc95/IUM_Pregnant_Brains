// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.graphics.Rect;
import com.google.android.calendar.timely.dnd.DragChipOverlay;
import com.google.android.calendar.utils.recycler.Recycler;
import java.util.List;

public final class GridDragChipManager
{

    private static final Recycler DRAG_CHIP_FRAMES = new Recycler(new _cls1());
    public final Rect chipFullFrameRecycle = new Rect();
    public final DragChipOverlay display;
    public final Rect displayAreaRecycle = new Rect();
    public int dragAreaLeft;
    public int dragAreaRight;
    public final Rect gdvGlobalVisibleRecycle = new Rect();
    public List lastFrames;
    private final int pointRecycle[] = new int[2];
    public final TimeRangeTimelineSegment recycleTimelineSegment = new TimeRangeTimelineSegment();
    public boolean xCoordinatesFixed;

    public GridDragChipManager(DragChipOverlay dragchipoverlay)
    {
        display = dragchipoverlay;
    }

    static void copyXCoordinates(List list, List list1)
    {
label0:
        {
label1:
            {
                if (list.size() == list1.size())
                {
                    for (int i = 0; i < list1.size(); i++)
                    {
                        DragChipFrame dragchipframe = (DragChipFrame)list.get(i);
                        DragChipFrame dragchipframe1 = (DragChipFrame)list1.get(i);
                        dragchipframe1.frame.left = dragchipframe.frame.left;
                        dragchipframe1.frame.right = dragchipframe.frame.right;
                    }

                } else
                {
                    if (list.size() <= list1.size())
                    {
                        break label0;
                    }
                    if (((DragChipFrame)list1.get(0)).gdvIndex <= ((DragChipFrame)list.get(0)).gdvIndex)
                    {
                        break label1;
                    }
                    list = (DragChipFrame)list.get(0);
                    list1 = (DragChipFrame)list1.get(0);
                    ((DragChipFrame) (list1)).frame.left = ((DragChipFrame) (list)).frame.left;
                    ((DragChipFrame) (list1)).frame.right = ((DragChipFrame) (list)).frame.right;
                }
                return;
            }
            list = (DragChipFrame)list.get(1);
            list1 = (DragChipFrame)list1.get(0);
            ((DragChipFrame) (list1)).frame.left = ((DragChipFrame) (list)).frame.left;
            ((DragChipFrame) (list1)).frame.right = ((DragChipFrame) (list)).frame.right;
            return;
        }
        if (((DragChipFrame)list1.get(1)).gdvIndex > ((DragChipFrame)list.get(0)).gdvIndex)
        {
            list = (DragChipFrame)list.get(0);
            list1 = (DragChipFrame)list1.get(0);
            ((DragChipFrame) (list1)).frame.left = ((DragChipFrame) (list)).frame.left;
            ((DragChipFrame) (list1)).frame.right = ((DragChipFrame) (list)).frame.right;
            return;
        } else
        {
            list = (DragChipFrame)list.get(0);
            list1 = (DragChipFrame)list1.get(1);
            ((DragChipFrame) (list1)).frame.left = ((DragChipFrame) (list)).frame.left;
            ((DragChipFrame) (list1)).frame.right = ((DragChipFrame) (list)).frame.right;
            return;
        }
    }

    final DragChipFrame createDragChipFrame(DndEventHandler.DndTarget dndtarget, Rect rect)
    {
        boolean flag;
        if (!rect.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        dndtarget.getLocationInWindow(pointRecycle);
        rect.offset(pointRecycle[0], pointRecycle[1]);
        if (!dndtarget.getGlobalVisibleRect(gdvGlobalVisibleRecycle))
        {
            gdvGlobalVisibleRecycle.setEmpty();
        }
        int i = dndtarget.getIndex();
        dndtarget = gdvGlobalVisibleRecycle;
        DragChipFrame dragchipframe = (DragChipFrame)DRAG_CHIP_FRAMES.getOrCreateObject();
        dragchipframe.gdvIndex = i;
        dragchipframe.gdvVisibleRect.set(dndtarget);
        dragchipframe.frame.set(rect);
        return dragchipframe;
    }

    final void recycleLastFrames(List list)
    {
        for (int i = 0; i < lastFrames.size(); i++)
        {
            DRAG_CHIP_FRAMES.recycle((DragChipFrame)lastFrames.get(i));
        }

        lastFrames = list;
    }

    final void updateDisplayArea(List list)
    {
        boolean flag;
        if (!list.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        displayAreaRecycle.setEmpty();
        for (int i = 0; i < list.size(); i++)
        {
            displayAreaRecycle.union(((DragChipFrame)list.get(i)).gdvVisibleRect);
        }

        displayAreaRecycle.left = dragAreaLeft;
        displayAreaRecycle.right = dragAreaRight;
        display.setDragChipArea(displayAreaRecycle);
    }


    private class TimeRangeTimelineSegment
        implements TimelineSegment
    {

        public TimeRange timeRange;

        public final int getEndDay()
        {
            return timeRange.getEndDay();
        }

        public final long getEndMillis()
        {
            return timeRange.getUtcEndMillis();
        }

        public final int getEndTime()
        {
            return timeRange.getEndMinute();
        }

        public final int getStartDay()
        {
            return timeRange.getStartDay();
        }

        public final long getStartMillis()
        {
            return timeRange.getUtcStartMillis();
        }

        public final int getStartTime()
        {
            return timeRange.getStartMinute();
        }

        public final boolean isAllDay()
        {
            return false;
        }

        public final boolean spansAtLeastOneDay()
        {
            return false;
        }

        TimeRangeTimelineSegment()
        {
        }
    }


    private class DragChipFrame
    {

        public final Rect frame = new Rect();
        public int gdvIndex;
        public final Rect gdvVisibleRect = new Rect();

        DragChipFrame()
        {
        }
    }


    private class _cls1
        implements com.google.android.calendar.utils.recycler.Recycler.RecyclerManager
    {

        public final volatile void clean(Object obj)
        {
        }

        public final Object createObject()
        {
            return new DragChipFrame();
        }

        public final volatile void prepareToReuse(Object obj)
        {
        }

        _cls1()
        {
        }
    }

}
