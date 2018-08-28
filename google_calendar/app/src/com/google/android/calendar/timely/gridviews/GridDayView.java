// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.util.concurrent.Previewable;
import com.google.android.calendar.Utils;
import com.google.android.calendar.newevent.CreateNewEventView;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chip.ChipScalingRatios;
import com.google.android.calendar.timeline.chip.ChipViewModel;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipViewModelFactory;
import com.google.android.calendar.timely.MonthData;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemModifications;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.android.calendar.timely.callbacks.OnLaunchDetailsHandler;
import com.google.android.calendar.timely.geometry.GridTimelineSegmentGeometry;
import com.google.android.calendar.timely.geometry.SimplePartitionItem;
import com.google.android.calendar.timely.geometry.TimelineSegment;
import com.google.android.calendar.timely.gridviews.geometry.GraphChipGeometry;
import com.google.android.calendar.timely.gridviews.geometry.GridChipGeometry;
import com.google.android.calendar.timely.gridviews.geometry.GroupingChipGeometry;
import com.google.android.calendar.timely.gridviews.geometry.HorizontalChipGeometry;
import com.google.android.calendar.timely.gridviews.geometry.OverlappingChipGeometry;
import com.google.android.calendar.timely.gridviews.geometry.PositionOnGrid;
import com.google.android.calendar.timely.gridviews.geometry.SimpleChipGeometry;
import com.google.android.calendar.timely.interaction.InteractionTrackingGestureDetector;
import com.google.android.calendar.timely.interaction.helper.DelayedUpdateHelper;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.timely.timeline.TimelineItemCollection;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.Holder;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            TentativeChipGestureListener, ChipAnimationChoreographer, DndEventHandler, GridViewDndHelper

public class GridDayView extends ViewGroup
    implements com.google.android.calendar.timeline.chip.Chip.ChipActionListener, DndEventHandler.DndTarget, OnPropertyChangedListener
{
    final class CalendarGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
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

                    private final CalendarGestureListener arg$1;
                    private final int arg$2;

                    public final void run()
                    {
                        CalendarGestureListener calendargesturelistener = arg$1;
                        int j = arg$2;
                        CreateNewEventView.setSelectedTime(calendargesturelistener._fld0.getContext(), calendargesturelistener._fld0.julianDay, j);
                    }

                .Lambda._cls0(int i)
                {
                    arg$1 = CalendarGestureListener.this;
                    arg$2 = i;
                }
                }

                ((ViewGroup)getParent()).getHandler().post(new .Lambda._cls0(i));
                return true;
            }
        }

        CalendarGestureListener()
        {
            this$0 = GridDayView.this;
            super();
        }
    }

    static final class ChildViewIndexPair
        implements Comparable
    {

        public final int index;
        private final int z;

        public final int compareTo(Object obj)
        {
            obj = (ChildViewIndexPair)obj;
            return GridDayView.compare(z, ((ChildViewIndexPair) (obj)).z);
        }

        ChildViewIndexPair(View view, int i)
        {
            z = ((LayoutParams)view.getLayoutParams()).position.z;
            index = i;
        }
    }

    public static final class LayoutParams extends android.view.ViewGroup.LayoutParams
        implements Holder
    {

        public final PositionOnGrid position;

        public final Object get()
        {
            return position;
        }

        public LayoutParams(int i, int j, float f, float f1, int k)
        {
            super(-2, -2);
            position = new PositionOnGrid();
            position.topMinutes = 0;
            position.bottomMinutes = 0;
            position.startFraction = 0.0F;
            position.endFraction = 0.0F;
            position.z = 0x7fffffff;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            position = new PositionOnGrid();
        }
    }


    private static final Comparator CHIPS_BY_INCREASING_TIME;
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/gridviews/GridDayView);
    public final GridChipGeometry chipGeometry;
    private Recycler chipRecycler;
    private final ChipViewModelFactory chipViewModelFactory;
    private int chipViewType;
    public CreateNewEventView createNewEventView;
    private DelayedUpdateHelper delayedUpdateHelper;
    private GridViewDndHelper dndHelper;
    private TimelineItemModifications itemModifications;
    public final TimelineItemCollection items;
    public int julianDay;
    private boolean julianDayHasChanged;
    public GestureDetector mGestureDetector;
    private int orderedChildIndices[];
    private final ChipScalingRatios scalingRatios = new ChipScalingRatios(getResources());
    public final GridTimelineSegmentGeometry timelineSegmentGeometry;

    public GridDayView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        delayedUpdateHelper = getDelayedUpdateHelper();
        chipViewType = 1;
        chipViewModelFactory = new ChipViewModelFactory(context);
        items = new TimelineItemCollection(TimelineItem.ItemComparator, null);
        mGestureDetector = new GestureDetector(context, new CalendarGestureListener());
        timelineSegmentGeometry = new GridTimelineSegmentGeometry(context, CalendarProperties.getIntProperty(10).intValue());
        boolean flag;
        if (!AccessibilityUtils.isAccessibilityEnabled(context))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        attributeset = new SimpleChipGeometry(new GraphChipGeometry());
        if (flag)
        {
            attributeset = new OverlappingChipGeometry(attributeset);
        }
        attributeset = new GroupingChipGeometry(attributeset);
        chipGeometry = new GridChipGeometry(context, timelineSegmentGeometry, attributeset);
        if (flag)
        {
            setChildrenDrawingOrderEnabled(true);
        }
        setClipChildren(false);
    }

    static int compare(int i, int j)
    {
        if (i == j)
        {
            return 0;
        }
        return i >= j ? 1 : -1;
    }

    public static HorizontalChipGeometry createHorizontalChipGeometry(boolean flag)
    {
        Object obj = new SimpleChipGeometry(new GraphChipGeometry());
        if (flag)
        {
            obj = new OverlappingChipGeometry(((HorizontalChipGeometry) (obj)));
        }
        return new GroupingChipGeometry(((HorizontalChipGeometry) (obj)));
    }

    public static int[] getChildDrawingOrder(ViewGroup viewgroup)
    {
        boolean flag = false;
        ArrayList arraylist = new ArrayList(viewgroup.getChildCount());
        for (int i = 0; i < viewgroup.getChildCount(); i++)
        {
            arraylist.add(new ChildViewIndexPair(viewgroup.getChildAt(i), i));
        }

        Collections.sort(arraylist);
        int ai[] = new int[viewgroup.getChildCount()];
        for (int j = ((flag) ? 1 : 0); j < viewgroup.getChildCount(); j++)
        {
            ai[j] = ((ChildViewIndexPair)arraylist.get(j)).index;
        }

        return ai;
    }

    private final float getChipForegroundScale(int i)
    {
        boolean flag = true;
        if (getContext().getResources().getBoolean(0x7f0c0016) || chipViewType != 1)
        {
            flag = false;
        }
        return scalingRatios.getScalingRatio(i, flag);
    }

    static final int lambda$static$0$GridDayView(Chip chip, Chip chip1)
    {
        chip = (PositionOnGrid)((LayoutParams)chip.getLayoutParams()).position;
        chip1 = (PositionOnGrid)((LayoutParams)chip1.getLayoutParams()).position;
        if (((PositionOnGrid) (chip)).topMinutes == ((PositionOnGrid) (chip1)).topMinutes)
        {
            return Float.compare(((PositionOnGrid) (chip)).startFraction, ((PositionOnGrid) (chip1)).startFraction);
        }
        int i = ((PositionOnGrid) (chip)).topMinutes;
        int j = ((PositionOnGrid) (chip1)).topMinutes;
        if (i == j)
        {
            return 0;
        }
        return i >= j ? 1 : -1;
    }

    private final void measureChip(Chip chip, int i, boolean flag, Rect rect)
    {
        chipGeometry.getChipFrame(chip, i, flag, rect);
        i = rect.width();
        int j = rect.height();
        chip.measure(android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000));
    }

    private final void updateChipPartitions(Iterable iterable)
    {
        chipGeometry.updateChipsLayoutParams(Lists.newArrayList(iterable));
        iterable = items.allChipsView.iterator();
        do
        {
            if (!iterable.hasNext())
            {
                break;
            }
            Chip chip = (Chip)iterable.next();
            PositionOnGrid positionongrid = (PositionOnGrid)((LayoutParams)chip.getLayoutParams()).position;
            if (chip.viewModel.getBorderColor() == 0)
            {
                Iterator iterator = items.allChipsView.iterator();
                boolean flag = false;
                while (iterator.hasNext()) 
                {
                    Chip chip1 = (Chip)iterator.next();
                    PositionOnGrid positionongrid1 = (PositionOnGrid)((LayoutParams)chip1.getLayoutParams()).position;
                    boolean flag1;
                    if (chip != chip1 && positionongrid.overlaps(positionongrid1) && positionongrid.z > positionongrid1.z && chip1.viewModel.getBorderColor() == 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    flag = flag1 | flag;
                }
                byte byte0;
                if (flag)
                {
                    byte0 = -1;
                } else
                {
                    byte0 = 0;
                }
                chip.setViewModel(chip.viewModel.toBuilder().setOuterBorderColor(byte0).build());
            }
        } while (true);
    }

    private final void updateWithItems(List list, boolean flag)
    {
        boolean flag1;
        if (flag || julianDayHasChanged || !items.arePersistentItemsIdentical(list))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            julianDayHasChanged = false;
            clearChips();
            if (list != null)
            {
                Object obj = new com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder();
                obj.viewType = Integer.valueOf(chipViewType);
                obj = ((com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder) (obj)).build();
                list = list.iterator();
                do
                {
                    if (!list.hasNext())
                    {
                        break;
                    }
                    TimelineItem timelineitem = (TimelineItem)list.next();
                    if (timelineitem != null)
                    {
                        if (timelineitem.spansAtLeastOneDay())
                        {
                            items.put(timelineitem, null);
                        } else
                        {
                            addChip(timelineitem, ((ChipFragmentInfo) (obj)), julianDay, true);
                        }
                    }
                } while (true);
                list = items.allChipsView;
                chipGeometry.updateChipsLayoutParams(Lists.newArrayList(list));
                list = items.allChipsView.iterator();
                do
                {
                    if (!list.hasNext())
                    {
                        break;
                    }
                    Chip chip = (Chip)list.next();
                    PositionOnGrid positionongrid = (PositionOnGrid)((LayoutParams)chip.getLayoutParams()).position;
                    if (chip.viewModel.getBorderColor() == 0)
                    {
                        Iterator iterator = items.allChipsView.iterator();
                        boolean flag2 = false;
                        while (iterator.hasNext()) 
                        {
                            Chip chip1 = (Chip)iterator.next();
                            PositionOnGrid positionongrid1 = (PositionOnGrid)((LayoutParams)chip1.getLayoutParams()).position;
                            boolean flag3;
                            if (chip != chip1 && positionongrid.overlaps(positionongrid1) && positionongrid.z > positionongrid1.z && chip1.viewModel.getBorderColor() == 0)
                            {
                                flag3 = true;
                            } else
                            {
                                flag3 = false;
                            }
                            flag2 = flag3 | flag2;
                        }
                        byte byte0;
                        if (flag2)
                        {
                            byte0 = -1;
                        } else
                        {
                            byte0 = 0;
                        }
                        chip.setViewModel(chip.viewModel.toBuilder().setOuterBorderColor(byte0).build());
                    }
                } while (true);
                requestLayout();
            }
        }
        updateCreateNewEventView();
        requestLayout();
    }

    public final void addChip(TimelineItem timelineitem, ChipFragmentInfo chipfragmentinfo, int i, boolean flag)
    {
        Chip chip = (Chip)chipRecycler.getOrCreateObject();
        chip.partitionInfo = new SimplePartitionItem(timelineitem);
        ChipViewModel chipviewmodel = chipViewModelFactory.buildViewModel(timelineitem, chipfragmentinfo, i);
        Object obj = chipviewmodel;
        if (itemModifications != null)
        {
            obj = (UnmodifiableIterator)((ImmutableCollection)itemModifications.modifications.get(timelineitem)).iterator();
            for (chipfragmentinfo = chipviewmodel; ((Iterator) (obj)).hasNext(); chipfragmentinfo = ((com.google.android.calendar.timely.TimelineItemModifications.TimelineItemModification)((Iterator) (obj)).next()).apply(chipfragmentinfo)) { }
            if (chipviewmodel == chipfragmentinfo || chipviewmodel != null && chipviewmodel.equals(chipfragmentinfo))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            obj = chipviewmodel;
            if (i == 0)
            {
                chip.setEnabled(false);
                obj = chipfragmentinfo;
            }
        }
        chip.setViewModel(((ChipViewModel) (obj)));
        if (flag)
        {
            chip.setActionListener(this);
            chip.longPressListener = dndHelper;
            chip.updateInteractionListeners();
        }
        chip.setTextIconScale(getChipForegroundScale(timelineSegmentGeometry.gridHourCellHeight));
        items.put(timelineitem, chip);
        addView(chip);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    public final void clearChips()
    {
        boolean flag = false;
        Object obj = new ArrayList(getChildCount());
        int k = getChildCount();
        for (int i = 0; i < k; i++)
        {
            View view = getChildAt(i);
            if (view instanceof Chip)
            {
                ((List) (obj)).add((Chip)view);
            }
        }

        removeAllViews();
        obj = (ArrayList)obj;
        k = ((ArrayList) (obj)).size();
        for (int j = ((flag) ? 1 : 0); j < k;)
        {
            Object obj1 = ((ArrayList) (obj)).get(j);
            j++;
            obj1 = (Chip)obj1;
            chipRecycler.recycle(obj1);
        }

        obj = items;
        ((TimelineItemCollection) (obj)).persistentEntries.clear();
        ((TimelineItemCollection) (obj)).entries.clear();
        ((TimelineItemCollection) (obj)).entriesByChip.clear();
        requestLayout();
    }

    public final void clearTransientState()
    {
        itemModifications = null;
        updateWithItems(new ArrayList(items.persistentItemsView), true);
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if (!super.dispatchTouchEvent(motionevent))
        {
            mGestureDetector.onTouchEvent(motionevent);
        }
        return true;
    }

    public final void dropItemDown(TimelineItem timelineitem, Previewable previewable, Function function)
    {
        boolean flag1 = true;
        boolean flag2 = false;
        Object obj = (TimelineItem)previewable.preview;
        Chip chip = items.getChipForInstance(timelineitem);
        itemModifications = null;
        boolean flag;
        if (((TimelineItem) (obj)).getStartDay() <= julianDay && ((TimelineItem) (obj)).getEndDay() >= julianDay)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (chip == null)
        {
            flag1 = false;
        }
        if (!flag && !flag1)
        {
            return;
        }
        if (items.entries.remove(timelineitem) == null);
        class .Lambda._cls3
            implements Function
        {

            public static final Function $instance = new .Lambda._cls3();

            public final Object apply(Object obj2)
            {
                obj2 = (View)(Chip)obj2;
                ObjectAnimator objectanimator = ObjectAnimator.ofFloat(obj2, View.ALPHA, new float[] {
                    ((View) (obj2)).getAlpha(), 0.0F
                });
                objectanimator.addListener(new com.google.android.calendar.utils.animation.AnimationUtils._cls3(((View) (obj2))));
                return objectanimator;
            }


            private .Lambda._cls3()
            {
            }
        }

        class .Lambda._cls4
            implements Function
        {

            private final GridDayView arg$1;
            private final Function arg$2;

            public final Object apply(Object obj2)
            {
                Object obj3 = arg$1;
                obj2 = (Animator)arg$2.apply((Chip)obj2);
                obj3 = ((GridDayView) (obj3)).getParent();
                if (obj3 instanceof ViewGroup)
                {
                    ((Animator) (obj2)).addListener(new NoClipChildrenAnimatorListener((ViewGroup)obj3));
                }
                return obj2;
            }

            .Lambda._cls4(Function function)
            {
                arg$1 = GridDayView.this;
                arg$2 = function;
            }
        }

        if (flag)
        {
            timelineitem = (Chip)chipRecycler.getOrCreateObject();
            timelineitem.partitionInfo = new SimplePartitionItem(((TimelineSegment) (obj)));
            Object obj1 = chipViewModelFactory;
            com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder builder = new com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder();
            builder.viewType = Integer.valueOf(chipViewType);
            timelineitem.setViewModel(((ChipViewModelFactory) (obj1)).buildViewModel(((TimelineItem) (obj)), builder.build(), julianDay));
            timelineitem.setTextIconScale(getChipForegroundScale(timelineSegmentGeometry.gridHourCellHeight));
            obj1 = dndHelper;
            timelineitem.setOnTouchListener(new GridViewDndHelper..Lambda._cls0(new InteractionTrackingGestureDetector(timelineitem.getContext(), new TentativeChipGestureListener(timelineitem, previewable, this, ((com.google.android.calendar.timeline.chip.Chip.ChipLongPressListener) (obj1))))));
            items.include(((TimelineItem) (obj)), timelineitem);
            addViewInLayout(timelineitem, -1, new LayoutParams(0, 0, 0.0F, 0.0F, 0x7fffffff));
        } else
        {
            timelineitem = null;
        }
        previewable = items.allChipsView;
        if (previewable instanceof Collection)
        {
            previewable = new HashSet((Collection)previewable);
        } else
        {
            obj = previewable.iterator();
            previewable = new HashSet();
            Iterators.addAll(previewable, ((Iterator) (obj)));
        }
        obj = new ArrayList(previewable.size());
        for (int i = ((flag2) ? 1 : 0); i < getChildCount(); i++)
        {
            View view = getChildAt(getChildDrawingOrder(getChildCount(), i));
            if ((view instanceof Chip) && previewable.contains(view))
            {
                ((ArrayList) (obj)).add((Chip)view);
            }
        }

        updateChipPartitions(items.allChipsView);
        if (timelineitem != null)
        {
            previewable = new Rect();
            measureChip(timelineitem, getMeasuredWidth(), RtlUtils.isLayoutDirectionRtl(getContext()), previewable);
            boolean flag3 = RtlUtils.isLayoutDirectionRtl(getContext());
            chipGeometry.getChipFrame(timelineitem, getMeasuredWidth(), flag3, previewable);
            timelineitem.layout(((Rect) (previewable)).left, ((Rect) (previewable)).top, ((Rect) (previewable)).right, ((Rect) (previewable)).bottom);
        }
        ChipAnimationChoreographer.animateChips(this, chip, .Lambda._cls3..instance, timelineitem, new .Lambda._cls4(function), ((ArrayList) (obj)), 550);
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(0, 0, 0.0F, 0.0F, 0x7fffffff);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new LayoutParams(layoutparams);
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        if (orderedChildIndices == null || orderedChildIndices.length != i)
        {
            orderedChildIndices = getChildDrawingOrder(((ViewGroup) (this)));
        }
        return orderedChildIndices[j];
    }

    public DelayedUpdateHelper getDelayedUpdateHelper()
    {
        return new DelayedUpdateHelper(this);
    }

    public final int getFirstChipCenterYCoordinate(int i)
    {
        Iterator iterator = items.allChipsView.iterator();
        Chip chip;
        int j;
        for (i = 0x7fffffff; iterator.hasNext(); i = Math.min((timelineSegmentGeometry.computeItemBottom(chip, julianDay, 0, j) + j) / 2, i))
        {
            chip = (Chip)iterator.next();
            j = timelineSegmentGeometry.computeItemTop(chip, julianDay, 0);
        }

        int k = i;
        if (i == 0x7fffffff)
        {
            k = -1;
        }
        return k;
    }

    public final int getFirstChipTopCoordinate()
    {
        Object obj = items.allChipsView;
        class .Lambda._cls2
            implements com.google.android.calendar.utils.collection.Iterables2.IntFolder
        {

            private final GridDayView arg$1;

            public final int onFold(Object obj1, int j)
            {
                GridDayView griddayview = arg$1;
                obj1 = (Chip)obj1;
                return Math.min(griddayview.timelineSegmentGeometry.computeItemTop(((TimelineSegment) (obj1)), griddayview.julianDay, 0), j);
            }

            public .Lambda._cls2()
            {
                arg$1 = GridDayView.this;
            }
        }

        .Lambda._cls2 _lcls2 = new .Lambda._cls2();
        int i = 0x7fffffff;
        for (obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
        {
            i = _lcls2.onFold(((Iterator) (obj)).next(), i);
        }

        return i;
    }

    public final int getIndex()
    {
        return julianDay;
    }

    public final boolean getItemFrame(TimelineSegment timelinesegment, Rect rect)
    {
        if (timelinesegment.getStartDay() > julianDay || timelinesegment.getEndDay() < julianDay)
        {
            return false;
        }
        rect.top = timelineSegmentGeometry.computeItemTop(timelinesegment, julianDay, 0);
        rect.bottom = timelineSegmentGeometry.computeItemBottom(timelinesegment, julianDay, 0, rect.top);
        rect.left = 0;
        rect.right = getMeasuredWidth();
        if (!rect.intersect(0, 0, getWidth(), getHeight()))
        {
            rect.setEmpty();
        }
        return true;
    }

    public int getJulianDay()
    {
        return julianDay;
    }

    public final Time getTimeFromPosition(int i)
    {
        Time time = new Time(Utils.getTimeZoneId(getContext()));
        int j = julianDay;
        time.writeFieldsToImpl();
        time.impl.setJulianDay(j);
        time.copyFieldsFromImpl();
        time.hour = Math.min((i * 24) / getHeight(), 23);
        float f = (24F * (float)i) / (float)getHeight();
        time.minute = (int)((f - (float)(int)f) * 60F);
        return time;
    }

    public final void initialize(Recycler recycler, DndEventHandler dndeventhandler, int i)
    {
        chipRecycler = recycler;
        if (dndeventhandler != null)
        {
            removeOnAttachStateChangeListener(dndeventhandler.targetDetachListener);
            removeOnLayoutChangeListener(dndeventhandler.targetLayoutListener);
            addOnAttachStateChangeListener(dndeventhandler.targetDetachListener);
            addOnLayoutChangeListener(dndeventhandler.targetLayoutListener);
            if (ViewCompat.isAttachedToWindow(this))
            {
                dndeventhandler.activateTarget((DndEventHandler.DndTarget)this);
            }
            dndHelper = new GridViewDndHelper(items, dndeventhandler, timelineSegmentGeometry, recycler);
            dndHelper.julianDay = julianDay;
        } else
        {
            dndHelper = null;
        }
        chipViewType = i;
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        onCalendarPropertyChanged(10, CalendarProperties.getIntProperty(10));
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).registerListener(10, this);
            return;
        }
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        if (i == 10)
        {
            i = ((Integer)obj).intValue();
            timelineSegmentGeometry.gridHourCellHeight = i;
            updateCreateNewEventView();
            float f = getChipForegroundScale(i);
            for (obj = items.allChipsView.iterator(); ((Iterator) (obj)).hasNext(); ((Chip)((Iterator) (obj)).next()).setTextIconScale(f)) { }
            remeasure();
            requestLayout();
        }
    }

    public final void onChipPrimaryAction(Chip chip)
    {
        TimelineItem timelineitem = items.getItemForChip(chip);
        if (timelineitem == null)
        {
            LogUtils.w(TAG, "Not propagating chip primary action, getItemForChip() returned null.", new Object[0]);
            return;
        }
        Object obj = VisualElementHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)obj).recordChipTap(getContext(), chip);
            obj = chip.viewModel.toBuilder().setPrimaryText(Collections.EMPTY_LIST).build();
            com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder builder = new com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder();
            builder.viewType = Integer.valueOf(chipViewType);
            chip = new EventInfoAnimationData(chip, ((ChipViewModel) (obj)), builder.build(), julianDay);
            ((OnLaunchDetailsHandler)getContext()).onLaunchDetails(timelineitem, chip);
            CreateNewEventView.removeSelectedTime();
            return;
        }
    }

    public final void onChipSecondaryAction(Chip chip)
    {
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).unregisterListener(10, this);
            return;
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        k = 0;
        if (items != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        orderedChildIndices = null;
        Rect rect = new Rect();
        Chip chip1;
        for (Iterator iterator = items.allChipsView.iterator(); iterator.hasNext(); chip1.layout(rect.left, rect.top, rect.right, rect.bottom))
        {
            chip1 = (Chip)iterator.next();
            flag = RtlUtils.isLayoutDirectionRtl(getContext());
            chipGeometry.getChipFrame(chip1, getMeasuredWidth(), flag, rect);
        }

        if (!AccessibilityUtils.isAccessibilityEnabled(getContext()) || android.os.Build.VERSION.SDK_INT < 22) goto _L1; else goto _L3
_L3:
        ArrayList arraylist;
        arraylist = Lists.newArrayList(items.allChipsView);
        Collections.sort(arraylist, CHIPS_BY_INCREASING_TIME);
        ArrayList arraylist1 = (ArrayList)arraylist;
        l = arraylist1.size();
        j = 0;
        do
        {
            Object obj;
            do
            {
                i = k;
                if (j >= l)
                {
                    continue; /* Loop/switch isn't completed */
                }
                obj = arraylist1.get(j);
                i = j + 1;
                obj = (Chip)obj;
                j = i;
            } while (((Chip) (obj)).getId() != -1);
            ((Chip) (obj)).setId(View.generateViewId());
            j = i;
        } while (true);
_L6:
        j = -1;
_L7:
        Chip chip;
        chip.setNextFocusForwardId(j);
        i++;
        if (i >= arraylist.size()) goto _L1; else goto _L4
_L4:
        chip = (Chip)arraylist.get(i);
        if (i > 0)
        {
            j = ((Chip)arraylist.get(i - 1)).getId();
        } else
        {
            j = -1;
        }
        chip.setAccessibilityTraversalAfter(j);
        if (i + 1 >= arraylist.size()) goto _L6; else goto _L5
_L5:
        j = ((Chip)arraylist.get(i + 1)).getId();
          goto _L7
    }

    protected void onMeasure(int i, int j)
    {
        GridTimelineSegmentGeometry gridtimelinesegmentgeometry;
        if (android.view.View.MeasureSpec.getMode(i) != 0)
        {
            i = android.view.View.MeasureSpec.getSize(i);
            boolean flag = RtlUtils.isLayoutDirectionRtl(getContext());
            Rect rect = new Rect();
            for (Iterator iterator = items.allChipsView.iterator(); iterator.hasNext(); measureChip((Chip)iterator.next(), i, flag, rect)) { }
        } else
        {
            i = getMeasuredWidth();
        }
        gridtimelinesegmentgeometry = timelineSegmentGeometry;
        j = gridtimelinesegmentgeometry.gridHourCellHeight;
        setMeasuredDimension(i, (int)((float)(int)(float)(gridtimelinesegmentgeometry.gridlineHeight + j) * 24F));
    }

    public void onUpdate(MonthData monthdata, int i)
    {
        if (i != julianDay)
        {
            return;
        } else
        {
            updateWithItems(monthdata.getItems(julianDay), false);
            return;
        }
    }

    public final boolean pickItemUp(TimelineItem timelineitem, Rect rect)
    {
        timelineitem = items.getChipForInstance(timelineitem);
        if (timelineitem == null)
        {
            return false;
        } else
        {
            rect.set(timelineitem.getLeft(), timelineitem.getTop(), timelineitem.getRight(), timelineitem.getBottom());
            return true;
        }
    }

    public void postUpdate(MonthData monthdata, int i, com.google.android.calendar.timely.RangedData.UpdateFinishedListener updatefinishedlistener)
    {
        class .Lambda._cls0
            implements Runnable
        {

            private final GridDayView arg$1;
            private final MonthData arg$2;
            private final int arg$3;
            private final com.google.android.calendar.timely.RangedData.UpdateFinishedListener arg$4;

            public final void run()
            {
                GridDayView griddayview = arg$1;
                MonthData monthdata1 = arg$2;
                int j = arg$3;
                com.google.android.calendar.timely.RangedData.UpdateFinishedListener updatefinishedlistener1 = arg$4;
                griddayview.onUpdate(monthdata1, j);
                if (updatefinishedlistener1 != null)
                {
                    updatefinishedlistener1.notifyUpdateFinished();
                }
            }

            .Lambda._cls0(MonthData monthdata, int i, com.google.android.calendar.timely.RangedData.UpdateFinishedListener updatefinishedlistener)
            {
                arg$1 = GridDayView.this;
                arg$2 = monthdata;
                arg$3 = i;
                arg$4 = updatefinishedlistener;
            }
        }

        delayedUpdateHelper.postUpdate(new .Lambda._cls0(monthdata, i, updatefinishedlistener));
    }

    public final void remeasure()
    {
        Object obj = items.allChipsView;
        chipGeometry.updateChipsLayoutParams(Lists.newArrayList(((Iterable) (obj))));
        obj = items.allChipsView.iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            Chip chip = (Chip)((Iterator) (obj)).next();
            PositionOnGrid positionongrid = (PositionOnGrid)((LayoutParams)chip.getLayoutParams()).position;
            if (chip.viewModel.getBorderColor() == 0)
            {
                Iterator iterator = items.allChipsView.iterator();
                boolean flag = false;
                while (iterator.hasNext()) 
                {
                    Chip chip1 = (Chip)iterator.next();
                    PositionOnGrid positionongrid1 = (PositionOnGrid)((LayoutParams)chip1.getLayoutParams()).position;
                    boolean flag1;
                    if (chip != chip1 && positionongrid.overlaps(positionongrid1) && positionongrid.z > positionongrid1.z && chip1.viewModel.getBorderColor() == 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    flag = flag1 | flag;
                }
                byte byte0;
                if (flag)
                {
                    byte0 = -1;
                } else
                {
                    byte0 = 0;
                }
                chip.setViewModel(chip.viewModel.toBuilder().setOuterBorderColor(byte0).build());
            }
        } while (true);
        requestLayout();
    }

    public final void setItemModifications(TimelineItemModifications timelineitemmodifications)
    {
        itemModifications = timelineitemmodifications;
        updateWithItems(new ArrayList(items.entries.navigableKeySet()), true);
    }

    public void setJulianDay(int i)
    {
        if (julianDay != i)
        {
            julianDay = i;
            julianDayHasChanged = true;
            chipGeometry.julianDay = i;
            if (dndHelper != null)
            {
                dndHelper.julianDay = julianDay;
                return;
            }
        }
    }

    public void updateCreateNewEventView()
    {
        int i = CreateNewEventView.getSelectedHourInDay(getContext(), julianDay);
        if (i < 0)
        {
            removeView(createNewEventView);
        } else
        {
            if (createNewEventView == null)
            {
                createNewEventView = (CreateNewEventView)View.inflate(getContext(), 0x7f050034, null);
            }
            long l = Time.calculateDayHourMinuteMillis(julianDay, i, 0, Utils.getTimeZoneId(getContext()), null);
            createNewEventView.setStartTime(l);
            createNewEventView.refreshFromModel();
            float f = i;
            GridTimelineSegmentGeometry gridtimelinesegmentgeometry = timelineSegmentGeometry;
            i = gridtimelinesegmentgeometry.gridHourCellHeight;
            i = (int)(f * (float)(int)(float)(gridtimelinesegmentgeometry.gridlineHeight + i));
            int j = getMeasuredWidth();
            gridtimelinesegmentgeometry = timelineSegmentGeometry;
            CreateNewEventView createneweventview = createNewEventView;
            int k;
            if (createneweventview.defaultDuration == -1L)
            {
                l = 60L;
            } else
            {
                l = createneweventview.defaultDuration;
            }
            k = gridtimelinesegmentgeometry.computeHeight(l);
            createNewEventView.measure(android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000));
            createNewEventView.setVisibility(0);
            createNewEventView.layout(0, i, j, k + i);
            if (createNewEventView.getParent() == null)
            {
                addView(createNewEventView);
                if (AccessibilityUtils.isAccessibilityEnabled(getContext()))
                {
                    class .Lambda._cls1
                        implements Runnable
                    {

                        private final GridDayView arg$1;

                        public final void run()
                        {
                            GridDayView griddayview = arg$1;
                            if (griddayview.createNewEventView.getParent() == griddayview)
                            {
                                griddayview.createNewEventView.requestFocus();
                            }
                        }

            .Lambda._cls1()
            {
                arg$1 = GridDayView.this;
            }
                    }

                    post(new .Lambda._cls1());
                    return;
                }
            }
        }
    }

    static 
    {
        class .Lambda._cls5
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls5();

            public final int compare(Object obj, Object obj1)
            {
                return GridDayView.lambda$static$0$GridDayView((Chip)obj, (Chip)obj1);
            }


            private .Lambda._cls5()
            {
            }
        }

        CHIPS_BY_INCREASING_TIME = .Lambda._cls5..instance;
    }
}
