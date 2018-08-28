// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.Property;
import android.view.View;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.util.concurrent.Previewable;
import com.google.android.calendar.Rescheduler;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.analytics.dnd.DndAnalytics;
import com.google.android.calendar.timeline.GhostChipModification;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.dnd.DragScrollPageController;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemModifications;
import com.google.android.calendar.timely.dnd.DragChipFactory;
import com.google.android.calendar.timely.dnd.DragChipOverlay;
import com.google.android.calendar.timely.dnd.InteractiveRescheduleManager;
import com.google.android.calendar.timely.interaction.InteractionTracker;
import com.google.android.calendar.utils.a11y.A11yHelper;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableListMultimap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridHourViewHighlightController, GridDragChipManager, DndEventHandler, GridHourDrawable

public final class GridDndController
    implements DndEventHandler.Delegate
{

    private final Activity activity;
    public DndAnalytics analytics;
    public final Delegate _flddelegate;
    public final GridDragChipManager dragChipManager;
    private final DragChipOverlay dragChipOverlay;
    private boolean dragging;
    private final com.google.android.calendar.CreateFabFragment.CreateFabStack fab;
    private TimelineItemModifications ghostItemModification;
    public final GridHourViewHighlightController highlightController = new GridHourViewHighlightController();
    private InteractiveRescheduleManager rescheduler;
    private final DragScrollPageController scrollPageController;
    private final com.google.android.calendar.timeline.dnd.DragScrollPageController.Delegate scrollPageDelegate = new _cls2();

    public GridDndController(Activity activity1, Delegate delegate1)
    {
        dragging = false;
        activity = activity1;
        _flddelegate = delegate1;
        if (activity instanceof com.google.android.calendar.CreateFabFragment.CreateFabInterface)
        {
            fab = ((com.google.android.calendar.CreateFabFragment.CreateFabInterface)activity1).getCreateFabStack();
        } else
        {
            fab = null;
        }
        delegate1 = DragChipOverlay.getInstance(activity);
        if (delegate1 == null)
        {
            throw new NullPointerException();
        } else
        {
            dragChipOverlay = (DragChipOverlay)delegate1;
            dragChipManager = new GridDragChipManager(dragChipOverlay);
            int i = activity1.getResources().getDimensionPixelOffset(0x7f0e011c);
            scrollPageController = new DragScrollPageController(activity1, dragChipManager.displayAreaRecycle, i, scrollPageDelegate);
            return;
        }
    }

    public final Previewable itemDropped(final DndEventHandler handler, final TimelineItem draggedItem, final TimeRange droppedOn)
    {
        if (droppedOn == null)
        {
            handler = analytics.context;
            draggedItem = AnalyticsLoggerHolder.instance;
            if (draggedItem == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            } else
            {
                ((AnalyticsLogger)draggedItem).trackEvent(handler, "dnd", "dnd_drop_failed", "dnd_drop_outside_grid", Long.valueOf(0L));
                return null;
            }
        } else
        {
            InteractionTracker.getInstance().trackInteractionStart(this, draggedItem);
            return rescheduler.reschedule(droppedOn.getUtcStartMillis(), new _cls1());
        }
    }

    public final void onDrag(DndEventHandler dndeventhandler, TimeRange timerange, int i, int j)
    {
        if (timerange != null)
        {
            highlightController.setHighlightedMinute(timerange.getStartMinute());
            GridDragChipManager griddragchipmanager = dragChipManager;
            Collections.sort(dndeventhandler.dndTargets, DndEventHandler.DndTarget.INDEX_COMPARATOR);
            List list = dndeventhandler.dndTargetsView;
            dndeventhandler = new ArrayList(2);
            griddragchipmanager.recycleTimelineSegment.timeRange = timerange;
            timerange = list.iterator();
            do
            {
                if (!timerange.hasNext())
                {
                    break;
                }
                DndEventHandler.DndTarget dndtarget = (DndEventHandler.DndTarget)timerange.next();
                if (dndtarget.getItemFrame(griddragchipmanager.recycleTimelineSegment, griddragchipmanager.chipFullFrameRecycle))
                {
                    dndeventhandler.add(griddragchipmanager.createDragChipFrame(dndtarget, griddragchipmanager.chipFullFrameRecycle));
                }
            } while (true);
            griddragchipmanager.updateDisplayArea(dndeventhandler);
            timerange = griddragchipmanager.lastFrames;
            boolean flag;
            if (!timerange.isEmpty())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("oldFrames is empty"));
            }
            if (!dndeventhandler.isEmpty())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("newFrames is empty"));
            }
            int k;
            int l;
            boolean flag1;
            if (Math.abs(timerange.size() - dndeventhandler.size()) <= 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            Preconditions.checkState(flag1, "Difference between oldFrames and newFrames more than 1. |oldFrames|=%s, |newFrames|=%s", timerange.size(), dndeventhandler.size());
            l = dndeventhandler.size() - 1;
            if (dndeventhandler.size() > timerange.size())
            {
                if (((GridDragChipManager.DragChipFrame)dndeventhandler.get(0)).gdvIndex < ((GridDragChipManager.DragChipFrame)timerange.get(0)).gdvIndex)
                {
                    griddragchipmanager.display.addDragChip(0, ((GridDragChipManager.DragChipFrame)dndeventhandler.get(0)).frame);
                    k = 1;
                } else
                {
                    griddragchipmanager.display.addDragChip(l, ((GridDragChipManager.DragChipFrame)dndeventhandler.get(l)).frame);
                    l--;
                    k = 0;
                }
            } else
            {
                k = 0;
            }
            if (dndeventhandler.size() < timerange.size())
            {
                if (((GridDragChipManager.DragChipFrame)dndeventhandler.get(0)).gdvIndex > ((GridDragChipManager.DragChipFrame)timerange.get(0)).gdvIndex)
                {
                    DragChipOverlay dragchipoverlay = griddragchipmanager.display;
                    Object obj1 = dragchipoverlay.getChildAt(0);
                    DragChipFactory dragchipfactory = dragchipoverlay.dragChipFactory;
                    obj1 = (Chip)obj1;
                    dragchipfactory.chipRecycler.recycle(obj1);
                    dragchipoverlay.removeViewAt(0);
                } else
                {
                    DragChipOverlay dragchipoverlay1 = griddragchipmanager.display;
                    i1 = timerange.size() - 1;
                    Object obj2 = dragchipoverlay1.getChildAt(i1);
                    DragChipFactory dragchipfactory1 = dragchipoverlay1.dragChipFactory;
                    obj2 = (Chip)obj2;
                    dragchipfactory1.chipRecycler.recycle(obj2);
                    dragchipoverlay1.removeViewAt(i1);
                }
            }
            if (griddragchipmanager.xCoordinatesFixed)
            {
                GridDragChipManager.copyXCoordinates(timerange, dndeventhandler);
            }
            while (k <= l) 
            {
                Object obj = griddragchipmanager.display;
                timerange = ((GridDragChipManager.DragChipFrame)dndeventhandler.get(k)).frame;
                Rect rect;
                int i1;
                int j1;
                if (!griddragchipmanager.xCoordinatesFixed)
                {
                    i1 = 1;
                } else
                {
                    i1 = 0;
                }
                ((DragChipOverlay) (obj)).frameRecycle.set(timerange);
                timerange = (Chip)((DragChipOverlay) (obj)).getChildAt(k);
                DragChipOverlay.setSize(timerange, ((DragChipOverlay) (obj)).frameRecycle.width(), ((DragChipOverlay) (obj)).frameRecycle.height());
                rect = ((DragChipOverlay) (obj)).frameRecycle;
                ((DragChipOverlay) (obj)).getLocationInWindow(((DragChipOverlay) (obj)).positionInWindowRecycle);
                rect.offset(-((DragChipOverlay) (obj)).positionInWindowRecycle[0], -((DragChipOverlay) (obj)).positionInWindowRecycle[1]);
                timerange.setTranslationY(((DragChipOverlay) (obj)).frameRecycle.top);
                j1 = ((DragChipOverlay) (obj)).frameRecycle.left;
                obj = timerange.getTag(0x7f100011);
                if (obj instanceof Animator)
                {
                    int k1 = ((Integer)timerange.getTag(0x7f100010)).intValue();
                    if (i1 != 0 && k1 == j1)
                    {
                        continue;
                    }
                    ((Animator)obj).cancel();
                }
                if (i1 == 0)
                {
                    timerange.setTranslationX(j1);
                } else
                {
                    ObjectAnimator objectanimator = ObjectAnimator.ofFloat(timerange, View.TRANSLATION_X, new float[] {
                        timerange.getTranslationX(), (float)j1
                    });
                    objectanimator.setDuration(100L);
                    objectanimator.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                    timerange.setTag(0x7f100011, objectanimator);
                    timerange.setTag(0x7f100010, Integer.valueOf(j1));
                    ((View)objectanimator.getTarget()).addOnAttachStateChangeListener(new com.google.android.calendar.utils.animation.AnimationUtils._cls2(objectanimator));
                    ((ObjectAnimator)objectanimator).start();
                }
                k++;
            }
            griddragchipmanager.recycleLastFrames(dndeventhandler);
        }
        scrollPageController.onDrag(i, j);
    }

    public final void onDragEnd(DndEventHandler dndeventhandler, TimelineItem timelineitem, Previewable previewable)
    {
        if (!dragging)
        {
            return;
        }
        dragging = false;
        highlightController.setHighlightedMinute(-1);
        Object obj = highlightController;
        GridHourDrawable gridhourdrawable = _flddelegate.getCurrentHourDrawable();
        if (gridhourdrawable != null && ((GridHourViewHighlightController) (obj)).gridHourDrawables.remove(gridhourdrawable))
        {
            gridhourdrawable.highlights.remove(obj);
            gridhourdrawable.invalidateSelf();
        }
        scrollPageController.onEnd();
        if (fab != null)
        {
            obj = fab.getShowAnimatorCreateFab();
            if (obj != null)
            {
                ((ObjectAnimator) (obj)).start();
            }
        }
        if (previewable == null)
        {
            timelineitem = dragChipManager;
            Collections.sort(dndeventhandler.dndTargets, DndEventHandler.DndTarget.INDEX_COMPARATOR);
            for (dndeventhandler = dndeventhandler.dndTargetsView.iterator(); dndeventhandler.hasNext(); ((DndEventHandler.DndTarget)dndeventhandler.next()).clearTransientState()) { }
            for (int i = ((GridDragChipManager) (timelineitem)).lastFrames.size() - 1; i >= 0; i--)
            {
                dndeventhandler = ((GridDragChipManager) (timelineitem)).display;
                obj = dndeventhandler.getChildAt(i);
                previewable = ((DragChipOverlay) (dndeventhandler)).dragChipFactory;
                obj = (Chip)obj;
                ((DragChipFactory) (previewable)).chipRecycler.recycle(obj);
                dndeventhandler.removeViewAt(i);
            }

            timelineitem.recycleLastFrames(null);
            return;
        }
        obj = dragChipManager;
        Collections.sort(dndeventhandler.dndTargets, DndEventHandler.DndTarget.INDEX_COMPARATOR);
        DndEventHandler.DndTarget dndtarget;
        DragChipOverlay dragchipoverlay;
        for (dndeventhandler = dndeventhandler.dndTargetsView.iterator(); dndeventhandler.hasNext(); dndtarget.dropItemDown(timelineitem, previewable, new GridDragChipManager..Lambda._cls0(dragchipoverlay)))
        {
            dndtarget = (DndEventHandler.DndTarget)dndeventhandler.next();
            dragchipoverlay = ((GridDragChipManager) (obj)).display;
            dragchipoverlay.getClass();
        }

        ((GridDragChipManager) (obj)).recycleLastFrames(null);
    }

    public final void onDragStart(DndEventHandler dndeventhandler, TimelineItem timelineitem)
    {
        if (dragging)
        {
            return;
        }
        Object obj = dragChipManager;
        Collections.sort(dndeventhandler.dndTargets, DndEventHandler.DndTarget.INDEX_COMPARATOR);
        Object obj1 = dndeventhandler.dndTargetsView;
        obj.xCoordinatesFixed = false;
        obj.lastFrames = new ArrayList();
        Iterator iterator = ((Iterable) (obj1)).iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj2 = (DndEventHandler.DndTarget)iterator.next();
            if (((DndEventHandler.DndTarget) (obj2)).pickItemUp(timelineitem, ((GridDragChipManager) (obj)).chipFullFrameRecycle))
            {
                int i = ((GridDragChipManager) (obj)).chipFullFrameRecycle.left;
                int j = ((GridDragChipManager) (obj)).chipFullFrameRecycle.right;
                ((GridDragChipManager) (obj)).chipFullFrameRecycle.left = 0;
                ((GridDragChipManager) (obj)).chipFullFrameRecycle.right = ((DndEventHandler.DndTarget) (obj2)).getWidth();
                obj2 = ((GridDragChipManager) (obj)).createDragChipFrame(((DndEventHandler.DndTarget) (obj2)), ((GridDragChipManager) (obj)).chipFullFrameRecycle);
                Object obj4 = ((GridDragChipManager) (obj)).display;
                Object obj3 = ((GridDragChipManager.DragChipFrame) (obj2)).frame;
                int k = ((DragChipOverlay) (obj4)).getChildCount();
                ((DragChipOverlay) (obj4)).addDragChip(k, ((Rect) (obj3)));
                obj3 = (Chip)((DragChipOverlay) (obj4)).getChildAt(k);
                obj4 = ((DragChipOverlay) (obj4)).dragChipFactory;
                obj4 = DragChipOverlay.DRAG_CHIP_LEFT;
                Property property = DragChipOverlay.DRAG_CHIP_RIGHT;
                obj4 = DragChipFactory.createPickUpDropAnimator(((Chip) (obj3)), ((Property) (obj4)), property, i, ((Integer)((Property) (obj4)).get(obj3)).intValue(), j, ((Integer)property.get(obj3)).intValue(), -ViewCompat.getElevation(((View) (obj3))), 0.0F);
                ((View) (obj3)).addOnAttachStateChangeListener(new com.google.android.calendar.utils.animation.AnimationUtils._cls2(((Animator) (obj4))));
                ((Animator) (obj4)).start();
                ((GridDragChipManager) (obj)).lastFrames.add(obj2);
            }
        } while (true);
        if (((GridDragChipManager) (obj)).lastFrames.isEmpty())
        {
            throw new GridDragChipManager.NoDragChipsOnTargetsException("No drag chips created during pickup.");
        }
        ((GridDragChipManager) (obj)).displayAreaRecycle.setEmpty();
        obj1 = ((Iterable) (obj1)).iterator();
        do
        {
            if (!((Iterator) (obj1)).hasNext())
            {
                break;
            }
            if (((DndEventHandler.DndTarget)((Iterator) (obj1)).next()).getGlobalVisibleRect(((GridDragChipManager) (obj)).gdvGlobalVisibleRecycle))
            {
                ((GridDragChipManager) (obj)).displayAreaRecycle.union(((GridDragChipManager) (obj)).gdvGlobalVisibleRecycle);
            }
        } while (true);
        obj.dragAreaLeft = ((GridDragChipManager) (obj)).displayAreaRecycle.left;
        obj.dragAreaRight = ((GridDragChipManager) (obj)).displayAreaRecycle.right;
        ((GridDragChipManager) (obj)).display.setDragChipArea(((GridDragChipManager) (obj)).displayAreaRecycle);
        dragging = true;
        obj = activity;
        rescheduler = new InteractiveRescheduleManager(((Activity) (obj)), new Rescheduler(((android.content.Context) (obj)), timelineitem, null), timelineitem);
        obj = highlightController;
        obj1 = _flddelegate.getCurrentHourDrawable();
        if (obj1 != null && ((GridHourViewHighlightController) (obj)).gridHourDrawables.add(obj1))
        {
            if (!((GridHourDrawable) (obj1)).highlights.contains(obj))
            {
                ((GridHourDrawable) (obj1)).highlights.add(obj);
            }
            ((GridHourDrawable) (obj1)).invalidateSelf();
        }
        highlightController.setHighlightedMinute(timelineitem.getTimeRange().getStartMinute());
        obj1 = new GhostChipModification();
        obj = new com.google.common.collect.ImmutableListMultimap.Builder();
        obj1 = (com.google.common.collect.ImmutableListMultimap.Builder)((com.google.common.collect.ImmutableMultimap.Builder) (obj)).put(timelineitem, obj1);
        ghostItemModification = new TimelineItemModifications((ImmutableListMultimap)((com.google.common.collect.ImmutableMultimap.Builder) (obj)).build());
        Collections.sort(dndeventhandler.dndTargets, DndEventHandler.DndTarget.INDEX_COMPARATOR);
        for (dndeventhandler = dndeventhandler.dndTargetsView.iterator(); dndeventhandler.hasNext(); ((DndEventHandler.DndTarget)dndeventhandler.next()).setItemModifications(ghostItemModification)) { }
        scrollPageController.disableAreasAroundNextPosition = true;
        if (fab != null)
        {
            dndeventhandler = fab.getHideAnimatorCreateFab();
            if (dndeventhandler != null)
            {
                dndeventhandler.start();
            }
        }
        analytics = new DndAnalytics(activity, _flddelegate.getViewMode(), InteractiveRescheduleManager.getDndAnalyticsType(timelineitem), timelineitem.getTimeRange());
    }

    public final void onTargetActivated(DndEventHandler.DndTarget dndtarget)
    {
        dndtarget.setItemModifications(ghostItemModification);
    }

    public final void onTargetChanged$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NMESJ9CHR6IPBNECNK8RJ48LR6ARJK91GMSP3CCLP3MAAM0()
    {
        highlightController.setHighlightedMinute(-1);
    }

    public final void onTargetDeactivated(DndEventHandler.DndTarget dndtarget)
    {
        dndtarget.setItemModifications(null);
    }

    public final void onTargetVisibleAreaChanged(DndEventHandler.DndTarget dndtarget)
    {
label0:
        {
            GridDragChipManager griddragchipmanager = dragChipManager;
            if (griddragchipmanager.lastFrames == null)
            {
                break label0;
            }
            Iterator iterator = griddragchipmanager.lastFrames.iterator();
            GridDragChipManager.DragChipFrame dragchipframe;
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
                dragchipframe = (GridDragChipManager.DragChipFrame)iterator.next();
            } while (dragchipframe.gdvIndex != dndtarget.getIndex());
            if (dndtarget.getGlobalVisibleRect(griddragchipmanager.gdvGlobalVisibleRecycle))
            {
                dragchipframe.gdvVisibleRect.set(griddragchipmanager.gdvGlobalVisibleRecycle);
            } else
            {
                dragchipframe.gdvVisibleRect.setEmpty();
            }
            griddragchipmanager.updateDisplayArea(griddragchipmanager.lastFrames);
        }
    }

    public final void startDnd$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NMESJ9CHR6IPBNECNK8RJ48LR6ARJK91GMSP3CCLP3MJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NN8QBDCLM7IBR4DPI2UH3IC5JK6Q39E1362ORKDTP7IEQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FAHKMQPBCD5N6AIBKCLMJMAAM0(DragChipFactory dragchipfactory)
    {
        DragChipOverlay dragchipoverlay = dragChipOverlay;
        boolean flag;
        if (dragchipoverlay.getChildCount() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Cannot set new factory while overlay still has active chips!"));
        } else
        {
            dragchipoverlay.dragChipFactory = dragchipfactory;
            return;
        }
    }

    public final boolean validateStartDragForItem$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NMESJ9CHR6IPBNECNK8RJ48LR6ARJK91GMSP3CCLP3MJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NN8QBDCLM7IBQKD5MMAR39DPIKIT35DKTIIMG_0(TimelineItem timelineitem)
    {
        A11yHelper.getInstance();
        if (A11yHelper.isAccessibilityEnabled(activity))
        {
            return false;
        }
        if (!InteractiveRescheduleManager.checkReschedulableWithFeedback(activity, timelineitem))
        {
            timelineitem = activity;
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            } else
            {
                ((AnalyticsLogger)analyticslogger).trackEvent(timelineitem, "dnd", "dnd_pickup_failed", "long_press_timeline_chip_grid_not_reschedulable", Long.valueOf(0L));
                return false;
            }
        } else
        {
            return true;
        }
    }

    private class _cls2
        implements com.google.android.calendar.timeline.dnd.DragScrollPageController.Delegate
    {

        private final GridDndController this$0;

        public final void onPage(int i)
        {
            GridDndController griddndcontroller = GridDndController.this;
            GridHourDrawable gridhourdrawable = griddndcontroller._flddelegate.getCurrentHourDrawable();
            griddndcontroller._flddelegate.scrollHorizontally(i);
            Object obj = griddndcontroller._flddelegate.getCurrentHourDrawable();
            if (gridhourdrawable != obj)
            {
                GridHourViewHighlightController gridhourviewhighlightcontroller = griddndcontroller.highlightController;
                if (obj != null && gridhourviewhighlightcontroller.gridHourDrawables.add(obj))
                {
                    if (!((GridHourDrawable) (obj)).highlights.contains(gridhourviewhighlightcontroller))
                    {
                        ((GridHourDrawable) (obj)).highlights.add(gridhourviewhighlightcontroller);
                    }
                    ((GridHourDrawable) (obj)).invalidateSelf();
                }
                obj = griddndcontroller.highlightController;
                if (gridhourdrawable != null && ((GridHourViewHighlightController) (obj)).gridHourDrawables.remove(gridhourdrawable))
                {
                    gridhourdrawable.highlights.remove(obj);
                    gridhourdrawable.invalidateSelf();
                }
            }
            griddndcontroller.analytics.hasPaged = true;
        }

        public final void onPageModeEnd()
        {
            dragChipManager.xCoordinatesFixed = false;
        }

        public final void onPageModeStart()
        {
            dragChipManager.xCoordinatesFixed = true;
        }

        public final void onScroll(int i)
        {
            _flddelegate.scrollVertically(i);
        }

        public final void onScrollModeEnd()
        {
        }

        public final void onScrollModeStart()
        {
        }

        _cls2()
        {
            this$0 = GridDndController.this;
            super();
        }
    }


    private class _cls1
        implements com.google.android.calendar.timely.dnd.InteractiveRescheduleManager.Callback
    {

        private final GridDndController this$0;
        private final TimelineItem val$draggedItem;
        private final TimeRange val$droppedOn;
        private final DndEventHandler val$handler;

        public final void onCancel()
        {
            InteractionTracker.getInstance().trackInteractionEnd(GridDndController.this, draggedItem);
            analytics.logFailureDroppedUndo();
            Object obj = GridDndController.this;
            obj = handler;
            Collections.sort(((DndEventHandler) (obj)).dndTargets, DndEventHandler.DndTarget.INDEX_COMPARATOR);
            for (obj = ((DndEventHandler) (obj)).dndTargetsView.iterator(); ((Iterator) (obj)).hasNext(); ((DndEventHandler.DndTarget)((Iterator) (obj)).next()).clearTransientState()) { }
        }

        public final void onFailure()
        {
            InteractionTracker.getInstance().trackInteractionEnd(GridDndController.this, draggedItem);
            Object obj = GridDndController.this;
            obj = handler;
            Collections.sort(((DndEventHandler) (obj)).dndTargets, DndEventHandler.DndTarget.INDEX_COMPARATOR);
            for (obj = ((DndEventHandler) (obj)).dndTargetsView.iterator(); ((Iterator) (obj)).hasNext(); ((DndEventHandler.DndTarget)((Iterator) (obj)).next()).clearTransientState()) { }
        }

        public final void onSuccess()
        {
            InteractionTracker.getInstance().trackInteractionEnd(GridDndController.this, draggedItem);
            analytics.logSuccess(droppedOn);
        }

        _cls1()
        {
            this$0 = GridDndController.this;
            draggedItem = timelineitem;
            handler = dndeventhandler;
            droppedOn = timerange;
            super();
        }
    }


    private class Delegate
    {

        public abstract GridHourDrawable getCurrentHourDrawable();

        public abstract String getViewMode();

        public abstract void scrollHorizontally(int i);

        public abstract void scrollVertically(int i);
    }

}
