// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.util.concurrent.Previewable;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemModifications;
import com.google.android.calendar.timely.dnd.DragChipFactory;
import com.google.android.calendar.timely.geometry.TimelineSegment;
import com.google.common.base.Function;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DndEventHandler
    implements GridViewDndHelper.Delegate
{
    public static interface Delegate
    {

        public abstract Previewable itemDropped(DndEventHandler dndeventhandler, TimelineItem timelineitem, TimeRange timerange);

        public abstract void onDrag(DndEventHandler dndeventhandler, TimeRange timerange, int i, int j);

        public abstract void onDragEnd(DndEventHandler dndeventhandler, TimelineItem timelineitem, Previewable previewable);

        public abstract void onDragStart(DndEventHandler dndeventhandler, TimelineItem timelineitem);

        public abstract void onTargetActivated(DndTarget dndtarget);

        public abstract void onTargetChanged$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NMESJ9CHR6IPBNECNK8RJ48LR6ARJK91GMSP3CCLP3MAAM0();

        public abstract void onTargetDeactivated(DndTarget dndtarget);

        public abstract void onTargetVisibleAreaChanged(DndTarget dndtarget);

        public abstract void startDnd$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NMESJ9CHR6IPBNECNK8RJ48LR6ARJK91GMSP3CCLP3MJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NN8QBDCLM7IBR4DPI2UH3IC5JK6Q39E1362ORKDTP7IEQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FAHKMQPBCD5N6AIBKCLMJMAAM0(DragChipFactory dragchipfactory);

        public abstract boolean validateStartDragForItem$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NMESJ9CHR6IPBNECNK8RJ48LR6ARJK91GMSP3CCLP3MJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NN8QBDCLM7IBQKD5MMAR39DPIKIT35DKTIIMG_0(TimelineItem timelineitem);
    }

    public static interface DndTarget
    {

        public static final Comparator INDEX_COMPARATOR = .Lambda._cls0..instance;

        public abstract void clearTransientState();

        public abstract void dropItemDown(TimelineItem timelineitem, Previewable previewable, Function function);

        public abstract boolean getGlobalVisibleRect(Rect rect);

        public abstract int getIndex();

        public abstract boolean getItemFrame(TimelineSegment timelinesegment, Rect rect);

        public abstract void getLocationInWindow(int ai[]);

        public abstract Time getTimeFromPosition(int i);

        public abstract int getWidth();

        public abstract boolean pickItemUp(TimelineItem timelineitem, Rect rect);

        public abstract void setItemModifications(TimelineItemModifications timelineitemmodifications);


        class .Lambda._cls0
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls0();

            public final int compare(Object obj, Object obj1)
            {
                obj = (DndTarget)obj;
                obj1 = (DndTarget)obj1;
                return Integer.valueOf(((DndTarget) (obj)).getIndex()).compareTo(Integer.valueOf(((DndTarget) (obj1)).getIndex()));
            }


                private .Lambda._cls0()
                {
                }
        }

    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/gridviews/DndEventHandler);
    public final Delegate _flddelegate;
    public final List dndTargets = new ArrayList();
    public final List dndTargetsView;
    public TimelineItem draggedItem;
    public Previewable droppedItem;
    public boolean isDragInProgress;
    public DndTarget lastTarget;
    private final android.view.View.OnDragListener onDragListener = new _cls3();
    public final int recycleLocation[] = new int[2];
    public final Point recyclePoint = new Point();
    public final Rect recycleRect = new Rect();
    public final android.view.View.OnAttachStateChangeListener targetDetachListener = new _cls1();
    public final android.view.View.OnLayoutChangeListener targetLayoutListener = new _cls2();
    public int verticalOffsetWithinChip;

    private DndEventHandler(Delegate delegate1, View view)
    {
        dndTargetsView = Collections.unmodifiableList(dndTargets);
        isDragInProgress = false;
        if (delegate1 == null)
        {
            throw new NullPointerException();
        } else
        {
            _flddelegate = (Delegate)delegate1;
            view.setOnDragListener(onDragListener);
            return;
        }
    }

    public static DndEventHandler create(Delegate delegate1, View view)
    {
        if (delegate1 == null)
        {
            return null;
        } else
        {
            return new DndEventHandler(delegate1, view);
        }
    }

    final void activateTarget(DndTarget dndtarget)
    {
        if (!dndTargets.contains(dndtarget))
        {
            dndTargets.add(dndtarget);
        }
        if (isDragInProgress)
        {
            _flddelegate.onTargetActivated(dndtarget);
        }
    }

    public final boolean startDnd(View view, DragChipFactory dragchipfactory, TimelineItem timelineitem, int i)
    {
        boolean flag = true;
        if (_flddelegate._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NMESJ9CHR6IPBNECNK8RJ48LR6ARJK91GMSP3CCLP3MJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NN8QBDCLM7IBQKD5MMAR39DPIKIT35DKTIIMG_0(timelineitem) && ViewCompat.startDragAndDrop(view, null, new com.google.android.calendar.timely.dnd.DragChipOverlay.EmptyDragShadowBuilder(), null, 0))
        {
            draggedItem = timelineitem;
            droppedItem = null;
            lastTarget = null;
            verticalOffsetWithinChip = i;
            isDragInProgress = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            _flddelegate._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NMESJ9CHR6IPBNECNK8RJ48LR6ARJK91GMSP3CCLP3MJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NN8QBDCLM7IBR4DPI2UH3IC5JK6Q39E1362ORKDTP7IEQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FAHKMQPBCD5N6AIBKCLMJMAAM0(dragchipfactory);
        }
        return flag;
    }


    private class _cls1
        implements android.view.View.OnAttachStateChangeListener
    {

        private final DndEventHandler this$0;

        public final void onViewAttachedToWindow(View view)
        {
            activateTarget((DndTarget)view);
        }

        public final void onViewDetachedFromWindow(View view)
        {
            DndEventHandler dndeventhandler = DndEventHandler.this;
            DndTarget dndtarget = (DndTarget)view;
            dndeventhandler.dndTargets.remove(dndtarget);
            if (dndeventhandler.isDragInProgress)
            {
                dndeventhandler._flddelegate.onTargetDeactivated(dndtarget);
            }
            view.removeOnAttachStateChangeListener(targetDetachListener);
            view.removeOnLayoutChangeListener(targetLayoutListener);
        }

        _cls1()
        {
            this$0 = DndEventHandler.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnLayoutChangeListener
    {

        private final DndEventHandler this$0;

        public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1)
        {
            _flddelegate.onTargetVisibleAreaChanged((DndTarget)view);
        }

        _cls2()
        {
            this$0 = DndEventHandler.this;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnDragListener
    {

        private final DndEventHandler this$0;

        private final void handleLocation(DndEventHandler dndeventhandler, DndTarget dndtarget, Point point, TimeRange timerange)
        {
            if (lastTarget != dndtarget)
            {
                _flddelegate._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NMESJ9CHR6IPBNECNK8RJ48LR6ARJK91GMSP3CCLP3MAAM0();
                lastTarget = dndtarget;
            }
            _flddelegate.onDrag(dndeventhandler, timerange, point.x, point.y);
        }

        public final boolean onDrag(View view, DragEvent dragevent)
        {
            Object obj;
            Point point;
            if (!isDragInProgress)
            {
                return false;
            }
            point = recyclePoint;
            obj = DndEventHandler.this;
            dragevent.getAction();
            JVM INSTR tableswitch 1 3: default 56
        //                       1 186
        //                       2 186
        //                       3 186;
               goto _L1 _L2 _L2 _L2
_L1:
            point.set(-1, -1);
_L3:
            DndEventHandler dndeventhandler = DndEventHandler.this;
            Iterator iterator = dndeventhandler.dndTargets.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break MISSING_BLOCK_LABEL_226;
                }
                obj = (DndTarget)iterator.next();
            } while (!((DndTarget) (obj)).getGlobalVisibleRect(dndeventhandler.recycleRect) || !dndeventhandler.recycleRect.contains(point.x, point.y));
_L4:
            DndEventHandler dndeventhandler1 = DndEventHandler.this;
            switch (dragevent.getAction())
            {
            case 5: // '\005'
            default:
                return false;

            case 1: // '\001'
                InteractionTracker.getInstance().trackInteractionStart(this, view);
                lastTarget = ((DndTarget) (obj));
                try
                {
                    _flddelegate.onDragStart(dndeventhandler1, draggedItem);
                }
                // Misplaced declaration of an exception variable
                catch (DragEvent dragevent)
                {
                    LogUtils.e(DndEventHandler.TAG, dragevent, "Could not start drag, cancelling gesture.", new Object[0]);
                    ViewCompat.cancelDragAndDrop(view);
                    dragevent = DndEventHandler.this;
                    dragevent.draggedItem = null;
                    dragevent.droppedItem = null;
                    dragevent.lastTarget = null;
                    dragevent.verticalOffsetWithinChip = -1;
                    dragevent.isDragInProgress = false;
                    InteractionTracker.getInstance().trackInteractionEnd(this, view);
                    return false;
                }
                return true;

            case 2: // '\002'
                view = DndEventHandler.this;
                if (obj == null)
                {
                    view = null;
                } else
                {
                    ((DndTarget) (obj)).getLocationInWindow(((DndEventHandler) (view)).recycleLocation);
                    dragevent = ((DndTarget) (obj)).getTimeFromPosition(point.y - ((DndEventHandler) (view)).verticalOffsetWithinChip - ((DndEventHandler) (view)).recycleLocation[1]);
                    TimeUtils.roundTime(dragevent, 15, ((DndEventHandler) (view)).draggedItem.getTimeRange().getStartMinute());
                    dragevent.writeFieldsToImpl();
                    long l = ((Time) (dragevent)).impl.toMillis(false);
                    long l2 = ((DndEventHandler) (view)).draggedItem.getStartMillis();
                    view = TimeRange.newNonAllDay(TimeZone.getTimeZone(((Time) (dragevent)).timezone), l, (l - l2) + ((DndEventHandler) (view)).draggedItem.getEndMillis());
                }
                handleLocation(dndeventhandler1, ((DndTarget) (obj)), point, view);
                return false;

            case 3: // '\003'
                boolean flag;
                if (droppedItem == null)
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
                view = DndEventHandler.this;
                if (obj == null)
                {
                    view = null;
                } else
                {
                    ((DndTarget) (obj)).getLocationInWindow(((DndEventHandler) (view)).recycleLocation);
                    dragevent = ((DndTarget) (obj)).getTimeFromPosition(point.y - ((DndEventHandler) (view)).verticalOffsetWithinChip - ((DndEventHandler) (view)).recycleLocation[1]);
                    TimeUtils.roundTime(dragevent, 15, ((DndEventHandler) (view)).draggedItem.getTimeRange().getStartMinute());
                    dragevent.writeFieldsToImpl();
                    long l1 = ((Time) (dragevent)).impl.toMillis(false);
                    long l3 = ((DndEventHandler) (view)).draggedItem.getStartMillis();
                    view = TimeRange.newNonAllDay(TimeZone.getTimeZone(((Time) (dragevent)).timezone), l1, (l1 - l3) + ((DndEventHandler) (view)).draggedItem.getEndMillis());
                }
                handleLocation(dndeventhandler1, ((DndTarget) (obj)), point, view);
                droppedItem = _flddelegate.itemDropped(dndeventhandler1, draggedItem, view);
                return droppedItem != null;

            case 6: // '\006'
                lastTarget = null;
                _flddelegate._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NMESJ9CHR6IPBNECNK8RJ48LR6ARJK91GMSP3CCLP3MAAM0();
                return true;

            case 4: // '\004'
                _flddelegate.onDragEnd(dndeventhandler1, draggedItem, droppedItem);
                dragevent = DndEventHandler.this;
                dragevent.draggedItem = null;
                dragevent.droppedItem = null;
                dragevent.lastTarget = null;
                dragevent.verticalOffsetWithinChip = -1;
                dragevent.isDragInProgress = false;
                InteractionTracker.getInstance().trackInteractionEnd(this, view);
                return true;
                break;
            }
              goto _L3
_L2:
            view.getLocationInWindow(((DndEventHandler) (obj)).recycleLocation);
            point.set(((DndEventHandler) (obj)).recycleLocation[0] + (int)dragevent.getX(), ((DndEventHandler) (obj)).recycleLocation[1] + (int)dragevent.getY());
              goto _L3
            obj = null;
              goto _L4
        }

        _cls3()
        {
            this$0 = DndEventHandler.this;
            super();
        }
    }

}
