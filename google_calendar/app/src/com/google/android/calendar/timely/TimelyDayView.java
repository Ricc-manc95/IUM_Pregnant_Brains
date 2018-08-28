// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Trace;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.bitmap.ResourceRequestKey;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.android.bitmap.drawable.ExtendedBitmapDrawable;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.event.DelayedActionDescription;
import com.google.android.calendar.event.DelayedActionPerformer;
import com.google.android.calendar.event.image.BitmapCacheHolder;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.newevent.CreateNewEventView;
import com.google.android.calendar.newevent.NewEventTimeChangedListenerHolder;
import com.google.android.calendar.task.assist.TaskAssistHolder;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.TimeUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper;
import com.google.android.calendar.timeline.alternate.ChipHeights;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chip.ChipAnimations;
import com.google.android.calendar.timeline.chip.ChipScalingRatios;
import com.google.android.calendar.timeline.chip.ChipSwipeHelper;
import com.google.android.calendar.timeline.chip.ChipViewModel;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipViewModelFactory;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.android.calendar.timely.animations.TimelineAgendaGridAnimationStatus;
import com.google.android.calendar.timely.callbacks.OnLaunchDetailsHandler;
import com.google.android.calendar.timely.geometry.GridPartitionItemGeometry;
import com.google.android.calendar.timely.geometry.GridTimelineSegmentGeometry;
import com.google.android.calendar.timely.geometry.PartitionItem;
import com.google.android.calendar.timely.geometry.SimplePartitionItem;
import com.google.android.calendar.timely.geometry.TimelineSegment;
import com.google.android.calendar.timely.gridviews.GridDayView;
import com.google.android.calendar.timely.gridviews.geometry.GridChipGeometry;
import com.google.android.calendar.timely.interaction.InteractionTracker;
import com.google.android.calendar.timely.interaction.helper.DelayedUpdateHelper;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.timely.timeline.TimelineItemCollection;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.NumberUtils;
import com.google.android.calendar.utils.animation.AnimationUtils;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItem, TimelyDayViewSwipeHelper, MonthBackgrounds, StickyAllDayEventsView, 
//            TimelyDayHeaderView, DayViewConfig, TimelyDayViewResources, NowLineDrawable, 
//            TimelineItemUtil, LargeThreadpoolBitmapDrawable, AgendaScrollCallback, TimelineRecyclerView, 
//            TimelineTask, OnTimelineModeChangedListener, MonthData, OnTimelineGestureListener

public class TimelyDayView extends ViewGroup
    implements android.view.View.OnClickListener, DelayedActionPerformer, com.google.android.calendar.timeline.chip.Chip.ChipActionListener, DualTimelineGridFragment.SimpleOnScrollListener, OnPropertyChangedListener
{
    final class AnimatorProvider
        implements com.google.android.calendar.timely.timeline.TimelineItemCollection.AnimatorProvider
    {

        public final TimelyDayView this$0;

        private final int adjustNewChipTopForNowLine(TimelineItem timelineitem, int i)
        {
            if (shouldDrawNowLine())
            {
                Time time = currentTime;
                time.writeFieldsToImpl();
                long l = time.impl.toMillis(false);
                boolean flag = isNowLineBeforeEvent(l, timelineitem);
                if (i > nowLineYAgenda && !flag)
                {
                    return nowLineYAgenda;
                }
                if (i <= nowLineYAgenda && flag)
                {
                    return nowLineYAgenda + (int)nowLineDrawable.paint.getStrokeWidth() + resources.chipVerticalSpacing;
                }
            }
            return i;
        }

        private final ValueAnimator createMoveViewsAnimator(int i, float f, boolean flag)
        {
            final ArrayList viewsToMove = new ArrayList();
            Object obj = items.allChipsView.iterator();
            do
            {
                if (!((Iterator) (obj)).hasNext())
                {
                    break;
                }
                Chip chip = (Chip)((Iterator) (obj)).next();
                if ((float)getChipTop(chip) >= f)
                {
                    viewsToMove.add(chip);
                    chip.bringToFront();
                }
            } while (true);
            viewsToMove.trimToSize();
            class _cls2
                implements android.animation.ValueAnimator.AnimatorUpdateListener
            {

                private int lastValue;
                private final AnimatorProvider this$1;
                private final boolean val$shouldMoveNowLine;
                private final ArrayList val$viewsToMove;

                public final void onAnimationUpdate(ValueAnimator valueanimator)
                {
                    int j = ((Integer)valueanimator.getAnimatedValue()).intValue();
                    int l = j - lastValue;
                    lastValue = j;
                    valueanimator = (android.view.ViewGroup.MarginLayoutParams)getLayoutParams();
                    valueanimator.bottomMargin = ((android.view.ViewGroup.MarginLayoutParams) (valueanimator)).bottomMargin + l;
                    setLayoutParams(valueanimator);
                    if (shouldMoveNowLine)
                    {
                        nowLineYAgenda = nowLineYAgenda + l;
                    }
                    int i1 = viewsToMove.size();
                    for (int k = 0; k < i1; k++)
                    {
                        valueanimator = (View)viewsToMove.get(k);
                        valueanimator.setY(valueanimator.getY() + (float)l);
                    }

                }

                _cls2()
                {
                    this$1 = AnimatorProvider.this;
                    shouldMoveNowLine = flag;
                    viewsToMove = arraylist;
                    super();
                    lastValue = 0;
                }
            }

            final boolean shouldMoveNowLine;
            if ((float)nowLineYAgenda >= f)
            {
                shouldMoveNowLine = true;
            } else
            {
                shouldMoveNowLine = false;
            }
            obj = ValueAnimator.ofInt(new int[] {
                0, i
            });
            ((ValueAnimator) (obj)).setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
            ((ValueAnimator) (obj)).setDuration(200L);
            ((ValueAnimator) (obj)).setStartDelay(100L);
            if (flag && i > 0)
            {
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)getLayoutParams();
                marginlayoutparams.bottomMargin = marginlayoutparams.bottomMargin - i;
                TimelyDayView timelydayview = TimelyDayView.this;
                timelydayview.animationHeightAdjustement = timelydayview.animationHeightAdjustement + i;
                setLayoutParams(marginlayoutparams);
            }
            ((ValueAnimator) (obj)).addUpdateListener(new _cls2());
            return ((ValueAnimator) (obj));
        }

        private final int getChipBottom(Chip chip)
        {
            if (chip == null)
            {
                return -1;
            }
            if (chip.getParent() == stickyAllDayEventsView)
            {
                return stickyAllDayEventsView.getTop() + chip.getBottom();
            } else
            {
                return chip.getBottom();
            }
        }

        private final int getChipTop(Chip chip)
        {
            if (chip == null)
            {
                return -1;
            }
            if (chip.getParent() == stickyAllDayEventsView)
            {
                return stickyAllDayEventsView.getTop() + chip.getTop();
            } else
            {
                return chip.getTop();
            }
        }

        private final int getEmptyStateHeightAdjustment()
        {
            int j = getNoItemsAgendaViewHeight() - (agendaFirstChipY + getBottomPadding());
            TimelyDayView timelydayview = TimelyDayView.this;
            boolean flag;
            int i;
            if (!timelydayview.dayViewConfig.shouldNeverDrawNowLine() && timelydayview.dayHeaderView.isToday)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            i = j;
            if (flag)
            {
                i = j - (resources.chipVerticalSpacing + (int)nowLineDrawable.paint.getStrokeWidth());
            }
            LogUtils.i(TimelyDayView.TAG, "Margin adjustment: %d", new Object[] {
                Integer.valueOf(i)
            });
            return i;
        }

        public final Animator createAfterExcludeItemAnimator(Chip chip, TimelineItem timelineitem)
        {
            Object obj;
            ArrayList arraylist;
            obj = null;
            if (timelineitem == null || chip == null)
            {
                return null;
            }
            arraylist = new ArrayList();
            float f;
            if (chip.swipeData == null)
            {
                timelineitem = ObjectAnimator.ofFloat(chip, View.ALPHA, new float[] {
                    1.0F, 0.0F
                });
            } else
            {
                timelineitem = ObjectAnimator.ofInt(chip, com.google.android.calendar.timeline.chip.ChipSwipeData.FootprintMaskAlpha.PROPERTY, new int[] {
                    0, 255
                });
                if (!(timelineitem.getTarget() instanceof Chip))
                {
                    throw new IllegalArgumentException("Not an animator of TimelyChip");
                }
                timelineitem.addUpdateListener(com.google.android.calendar.timeline.chip.ChipAnimations.ChipInvalidator.InstanceHolder.INSTANCE);
            }
            timelineitem.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
            timelineitem.addListener(new com.google.android.calendar.timeline.chip.ChipAnimations._cls1(chip));
            arraylist.add(timelineitem.setDuration(200L));
            f = getChipBottom(chip);
            arraylist.add(createMoveViewsAnimator(-(chip.getHeight() + resources.chipVerticalSpacing), f, false));
            if (!items.entries.isEmpty()) goto _L2; else goto _L1
_L1:
            arraylist.add(createMoveViewsAnimator(getEmptyStateHeightAdjustment(), getBottom(), false));
            if (forceShow) goto _L4; else goto _L3
_L3:
            chip = ObjectAnimator.ofFloat(dayHeaderView, View.ALPHA, new float[] {
                0.0F
            });
_L6:
            if (chip != null)
            {
                chip.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                chip.setDuration(200L);
                arraylist.add(chip);
            }
_L2:
            chip = new AnimatorSet();
            chip.playTogether(arraylist);
            return chip;
_L4:
            chip = obj;
            if (dayViewConfig.shouldDrawNoEventsView())
            {
                layoutNoEventsTodayView();
                chip = TimelyDayView.this;
                timelineitem = noEventsView;
                if (timelineitem.getParent() != chip)
                {
                    chip.addView(timelineitem, 0);
                }
                timelineitem.bringToFront();
                noEventsView.setVisibility(0);
                noEventsView.setText(0x7f130350);
                timelineitem = ObjectAnimator.ofFloat(noEventsView, View.ALPHA, new float[] {
                    0.0F, 1.0F
                });
                chip = timelineitem;
                if (shouldDrawNowLine())
                {
                    chip = ValueAnimator.ofInt(new int[] {
                        0, chipHeights.chipSingleHeight
                    });
                    chip.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                    chip.setDuration(200L);
                    chip.setStartDelay(100L);
                    class _cls1
                        implements android.animation.ValueAnimator.AnimatorUpdateListener
                    {

                        private int lastValue;
                        private final AnimatorProvider this$1;

                        public final void onAnimationUpdate(ValueAnimator valueanimator)
                        {
                            int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
                            int j = lastValue;
                            lastValue = i;
                            nowLineYAgenda = (i - j) + nowLineYAgenda;
                        }

                _cls1()
                {
                    this$1 = AnimatorProvider.this;
                    super();
                    lastValue = 0;
                }
                    }

                    chip.addUpdateListener(new _cls1());
                    arraylist.add(chip);
                    chip = timelineitem;
                }
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public final Animator createBeforeIncludeItemAnimator(TimelineItem timelineitem)
        {
            return createBeforeUpdateItemAnimator(null, null, timelineitem);
        }

        public final Animator createBeforeUpdateItemAnimator(Chip chip, TimelineItem timelineitem, TimelineItem timelineitem1)
        {
            int i;
            int j;
            int k;
            if (dayViewConfig.inListView() && isInAllDaySection(timelineitem1))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0) goto _L2; else goto _L1
_L1:
            if (!allDayChips.isEmpty()) goto _L4; else goto _L3
_L3:
            i = agendaFirstChipY;
_L5:
            Chip chip1 = (Chip)chipRecycler.getOrCreateObject();
            ChipFragmentInfo chipfragmentinfo = getChipFragmentInfo(false);
            chip1.partitionInfo = new SimplePartitionItem(timelineitem1);
            chip1.setViewModel(chipViewModelFactory.buildViewModel(timelineitem1, chipfragmentinfo, getCurrentJulianDay()));
            k = chipHeights.getAgendaViewHeight(chip1.viewModel.getChipType());
            chipRecycler.recycle(chip1);
            if (getChipTop(chip) == i || getChipBottom(chip) + resources.chipVerticalSpacing == i)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (chip != null && chip.getTranslationX() == 0.0F && j != 0 && chip.getHeight() == k)
            {
                return null;
            }
            break MISSING_BLOCK_LABEL_593;
_L4:
            j = allDayChips.size();
            i = 0;
_L6:
label0:
            {
                if (i >= j)
                {
                    break MISSING_BLOCK_LABEL_303;
                }
                chip1 = (Chip)allDayChips.get(i);
                if (TimelineItem.AllDayComparator.compare(items.getItemForChip(chip1), timelineitem1) < 0)
                {
                    break label0;
                }
                i = getChipTop(chip1);
            }
              goto _L5
            i++;
              goto _L6
            i = getChipBottom((Chip)allDayChips.get(allDayChips.size() - 1)) + resources.chipVerticalSpacing;
              goto _L5
_L2:
            if (allDayChips.isEmpty() && timedChips.isEmpty())
            {
                i = agendaFirstChipY;
            } else
            {
label1:
                {
                    if (!timedChips.isEmpty())
                    {
                        break label1;
                    }
                    i = adjustNewChipTopForNowLine(timelineitem1, getChipBottom((Chip)allDayChips.get(allDayChips.size() - 1)) + resources.chipVerticalSpacing);
                }
            }
              goto _L5
            j = timedChips.size();
            i = 0;
_L7:
label2:
            {
                if (i >= j)
                {
                    break MISSING_BLOCK_LABEL_537;
                }
                chip1 = (Chip)timedChips.get(i);
                if (TimelineItem.ItemComparator.compare(items.getItemForChip(chip1), timelineitem1) < 0)
                {
                    break label2;
                }
                i = adjustNewChipTopForNowLine(timelineitem1, getChipTop(chip1));
            }
              goto _L5
            i++;
              goto _L7
            i = adjustNewChipTopForNowLine(timelineitem1, getChipBottom((Chip)timedChips.get(timedChips.size() - 1)) + resources.chipVerticalSpacing);
              goto _L5
            int l = resources.chipVerticalSpacing + k;
            k = l;
            if (items.entries.isEmpty())
            {
                k = l - getEmptyStateHeightAdjustment();
                LogUtils.i(TimelyDayView.TAG, "Translation adjusted: %d", new Object[] {
                    Integer.valueOf(k)
                });
            }
            if (j != 0)
            {
                i = getChipBottom(chip);
            }
            return AnimationUtils.playTogether(createMoveViewsAnimator(k, i, true), new Animator[] {
                createAfterExcludeItemAnimator(chip, timelineitem)
            });
        }

        AnimatorProvider()
        {
            this$0 = TimelyDayView.this;
            super();
        }
    }

    final class CalendarGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        private final TimelyDayView this$0;

        public final boolean onSingleTapUp(MotionEvent motionevent)
        {
            int j = (int)motionevent.getY();
            if (j >= startOfGrid && dayViewConfig.inGridMode())
            {
                int i = startOfGrid;
                motionevent = timelineSegmentGeometry;
                int k = ((GridTimelineSegmentGeometry) (motionevent)).gridHourCellHeight;
                i = (j - i) / (int)(float)(((GridTimelineSegmentGeometry) (motionevent)).gridlineHeight + k);
                long l1 = getSelectedTimeInMillis(i);
                CreateNewEventView.setSelectedTime(getContext(), julianDay, i);
                if (createNewEventView != null)
                {
                    createNewEventView.setStartTime(l1);
                    installCreateEventView();
                }
            }
            int l = getMonthHeaderSize();
            int i1 = resources.weekHeaderHeight;
            if (dayViewConfig.inListView())
            {
                motionevent = TimelyDayView.this;
                boolean flag;
                if (((TimelyDayView) (motionevent)).dayViewConfig.shouldDrawExtraHeaders() && ((TimelyDayView) (motionevent)).dayHeaderView.isFirstDayOfWeek)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && j > l && j < l + i1)
                {
                    timelineGestureListener.onWeekDividerTap();
                }
            }
            requestLayout();
            return true;
        }

        CalendarGestureListener()
        {
            this$0 = TimelyDayView.this;
            super();
        }
    }

    final class Subscriber
    {

        public Subscription dataSubscription;
        public int day;
        public MonthData monthData;
        public final TimelyDayView this$0;

        Subscriber()
        {
            this$0 = TimelyDayView.this;
            super();
            class _cls1
                implements android.view.View.OnAttachStateChangeListener
            {

                public final Subscriber this$1;

                public final void onViewAttachedToWindow(View view)
                {
                    boolean flag;
                    if (dataSubscription == null)
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
                    if (monthData != null)
                    {
                        view = Subscriber.this;
                        MonthData monthdata = monthData;
                        class .Lambda._cls0
                            implements Consumer
                        {

                            private final _cls1 arg$1;

                            public final void accept(Object obj1)
                            {
                                _cls1 _lcls1 = arg$1;
                                obj1 = (MonthData)obj1;
                                _lcls1._fld1._fld0.onUpdate(((MonthData) (obj1)), _lcls1._fld1.day, false);
                            }

                            .Lambda._cls0()
                            {
                                arg$1 = _cls1.this;
                            }
                        }

                        .Lambda._cls0 _lcls0 = new .Lambda._cls0();
                        Object obj = delayedUpdateHelper;
                        obj.getClass();
                        class .Lambda._cls1
                            implements Executor
                        {

                            private final DelayedUpdateHelper arg$1;

                            public final void execute(Runnable runnable)
                            {
                                arg$1.postUpdate(runnable);
                            }

                            .Lambda._cls1(DelayedUpdateHelper delayedupdatehelper)
                            {
                                arg$1 = delayedupdatehelper;
                            }
                        }

                        obj = new .Lambda._cls1(((DelayedUpdateHelper) (obj)));
                        view.dataSubscription = monthdata.subscriptions.subscribeFunction(new com.google.android.apps.calendar.util.concurrent.SubscriptionManager..Lambda._cls0(_lcls0), ((Executor) (obj)));
                    }
                }

                public final void onViewDetachedFromWindow(View view)
                {
                    if (dataSubscription != null)
                    {
                        dataSubscription.cancel(false);
                        dataSubscription = null;
                    }
                }

                _cls1()
                {
                    this$1 = Subscriber.this;
                    super();
                }
            }

            addOnAttachStateChangeListener(new _cls1());
        }
    }

    public static interface SwipeGestureDelegate
    {

        public abstract int getSupportedSwipeDirections(TimelineItem timelineitem);

        public abstract Integer getSwipeIcon(TimelineItem timelineitem, int i);

        public abstract boolean isSwipeEnabled(TimelyDayView timelydayview);

        public abstract boolean onDismissActionComplete$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM7IH31F5B6IPBN7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM6IRJ595Q6AR9R94KLK___0(TimelyDayView timelydayview, TimelineItem timelineitem);

        public abstract boolean onSwipeGestureComplete$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM7IH31F5B6IPBN7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM6IRJ595Q6AR9R94KLK___0(TimelyDayView timelydayview, TimelineItem timelineitem);
    }


    private static final Typeface ROBOTO_REGULAR = Typeface.create("sans-serif", 0);
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/TimelyDayView);
    public int agendaFirstChipY;
    public ArrayList allDayChips;
    public int animationHeightAdjustement;
    private final TimelineAgendaGridAnimationStatus animationStatusProvider;
    private final Time baseDate;
    private final CalendarProperties calendarProperties;
    private final GridPartitionItemGeometry chipGeometry;
    public final ChipHeights chipHeights;
    private int chipLayoutStopX;
    public final Recycler chipRecycler;
    private final ChipScheduleGridAnimationHelper chipScheduleGridAnimationHelper = new ChipScheduleGridAnimationHelper(getResources());
    public final ChipViewModelFactory chipViewModelFactory;
    public CreateNewEventView createNewEventView;
    public Time currentTime;
    private int dateInfo[];
    public TimelyDayHeaderView dayHeaderView;
    private int dayViewAgendaMeasuredHeight;
    public final DayViewConfig dayViewConfig;
    private int dayViewGridMeasuredHeight;
    public final DelayedUpdateHelper delayedUpdateHelper = new DelayedUpdateHelper(this);
    public boolean forceShow;
    private final GridChipGeometry geometry;
    private final GestureDetector gestureDetector;
    public boolean hasItems;
    private final boolean isRtl = RtlUtils.isLayoutDirectionRtl(getContext());
    private final boolean isTablet = getContext().getResources().getBoolean(0x7f0c0016);
    public final TimelineItemCollection items;
    public int julianDay;
    private boolean julianDayHasChanged;
    public boolean mDataLoaded;
    private final MonthBackgrounds monthBackgrounds;
    private LargeThreadpoolBitmapDrawable monthHeaderDrawable;
    private String monthHeaderText;
    private final android.view.View.OnClickListener noEventsClickListener = new _cls3();
    public TextView noEventsView;
    public final NowLineDrawable nowLineDrawable;
    public int nowLineYAgenda;
    private int nowLineYGrid;
    private int nowlineEventIndex;
    private int orderedChildIndices[];
    private Paint paint;
    private final Time recycleTime = new Time();
    public final TimelyDayViewResources resources;
    public boolean shouldDrawYearHeader;
    private boolean showMonthHeaderImages;
    private final float solidLines[] = new float[100];
    public int startOfGrid;
    public StickyAllDayEventsView stickyAllDayEventsView;
    public Subscriber subscriber;
    public final TimelyDayViewSwipeHelper swipe = new TimelyDayViewSwipeHelper(this);
    public ArrayList timedChips;
    public OnTimelineGestureListener timelineGestureListener;
    private OnTimelineModeChangedListener timelineModeChangedListener;
    public final GridTimelineSegmentGeometry timelineSegmentGeometry;
    public int viewWidth;
    private boolean waitingForMeasurement;
    private String weekHeaderText;
    private String yearHeaderText;

    public TimelyDayView(Context context, Recycler recycler, DayViewConfig dayviewconfig, TimelineAgendaGridAnimationStatus timelineagendagridanimationstatus, OnTimelineModeChangedListener ontimelinemodechangedlistener)
    {
        super(context);
        items = new TimelineItemCollection(TimelineItem.ItemComparator, new AnimatorProvider());
        allDayChips = new ArrayList();
        timedChips = new ArrayList();
        paint = new Paint();
        nowLineYGrid = -1;
        nowlineEventIndex = -1;
        waitingForMeasurement = false;
        mDataLoaded = false;
        hasItems = false;
        subscriber = new Subscriber();
        setClipChildren(false);
        Object obj = CalendarProperties.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        calendarProperties = (CalendarProperties)obj;
        obj = context.getResources();
        chipRecycler = recycler;
        dayViewConfig = dayviewconfig;
        animationStatusProvider = timelineagendagridanimationstatus;
        timelineModeChangedListener = ontimelinemodechangedlistener;
        if (MonthBackgrounds.instance == null)
        {
            MonthBackgrounds.instance = new MonthBackgrounds(((Resources) (obj)));
        }
        monthBackgrounds = MonthBackgrounds.instance;
        stickyAllDayEventsView = new StickyAllDayEventsView(context, timelineagendagridanimationstatus, chipScheduleGridAnimationHelper);
        stickyAllDayEventsView.setLayoutDirection(getLayoutDirection());
        dayHeaderView = new TimelyDayHeaderView(context);
        recycler = dayHeaderView;
        long l;
        boolean flag;
        if (!dayViewConfig.shouldNeverDrawMonthHeader() && recycler.getResources().getBoolean(0x7f0c0015))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        recycler.showMonthHeaderImages = flag;
        if (((TimelyDayHeaderView) (recycler)).showMonthHeaderImages)
        {
            recycler.monthHeaderSize = recycler.getResources().getDimensionPixelSize(0x7f0e03a3) + (((TimelyDayHeaderView) (recycler)).defaultMargin << 1);
        } else
        {
            recycler.monthHeaderSize = recycler.getResources().getDimensionPixelSize(0x7f0e03a4);
        }
        if (!dayviewconfig.shouldDrawDayHeader())
        {
            dayHeaderView.setVisibility(8);
        } else
        if (!isTablet)
        {
            dayHeaderView.setOnClickListener(this);
        }
        recycler = LayoutInflater.from(context);
        createNewEventView = (CreateNewEventView)recycler.inflate(0x7f050034, null);
        noEventsView = (TextView)recycler.inflate(0x7f0500f8, null);
        noEventsView.setOnClickListener(noEventsClickListener);
        if (!dayViewConfig.shouldNeverDrawMonthHeader() && ((Resources) (obj)).getBoolean(0x7f0c0015))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        showMonthHeaderImages = flag;
        timelineSegmentGeometry = new GridTimelineSegmentGeometry(context, CalendarProperties.getIntProperty(10).intValue());
        chipGeometry = new GridPartitionItemGeometry(context);
        if (!AccessibilityUtils.isAccessibilityEnabled(context))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        recycler = GridDayView.createHorizontalChipGeometry(flag);
        geometry = new GridChipGeometry(context, timelineSegmentGeometry, recycler);
        gestureDetector = new GestureDetector(context, new CalendarGestureListener());
        setFocusable(false);
        setFocusableInTouchMode(false);
        setBackgroundColor(((Resources) (obj)).getColor(0x7f0d0313));
        currentTime = new Time(Utils.getTimeZoneId(context));
        recycler = currentTime;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        recycler.set(l);
        baseDate = new Time(Utils.getTimeZoneId(context));
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        baseDate.set(l);
        resources = new TimelyDayViewResources(context.getResources(), showMonthHeaderImages);
        chipHeights = new ChipHeights(context);
        nowLineDrawable = NowLineDrawable.createAgendaStyle(context.getResources(), false);
        chipViewModelFactory = new ChipViewModelFactory(context);
        remeasure();
        chipLayoutStopX = viewWidth - resources.horizontalMargin;
        if (flag)
        {
            setChildrenDrawingOrderEnabled(true);
        }
    }

    private final void clearChips()
    {
        CalendarExecutor.MAIN.checkOnThread();
        do
        {
            if (getChildCount() <= 0)
            {
                break;
            }
            Object obj = getChildAt(0);
            removeView(((View) (obj)));
            if (obj instanceof Chip)
            {
                obj = (Chip)obj;
                chipRecycler.recycle(obj);
            }
        } while (true);
        Chip chip;
        for (; stickyAllDayEventsView.getChildCount() > 0; chipRecycler.recycle(chip))
        {
            chip = (Chip)stickyAllDayEventsView.getChildAt(0);
            stickyAllDayEventsView.removeView(chip);
        }

        timedChips.clear();
        allDayChips.clear();
        TimelineItemCollection timelineitemcollection = items;
        timelineitemcollection.persistentEntries.clear();
        timelineitemcollection.entries.clear();
        timelineitemcollection.entriesByChip.clear();
        chipScheduleGridAnimationHelper.chips.clear();
    }

    private final void drawNowLine(Canvas canvas)
    {
        if (shouldDrawNowLine())
        {
            int i = nowLineYAgenda + (int)((float)(nowLineYGrid - nowLineYAgenda) * animationStatusProvider.getGridModeRatio()) + (int)(0.5F * nowLineDrawable.paint.getStrokeWidth() * (1.0F - animationStatusProvider.getGridModeRatio()));
            if (isRtl)
            {
                NowLineDrawable nowlinedrawable = nowLineDrawable;
                int j = viewWidth;
                int l = getNowLineMarginStart();
                int j1 = getEventLayoutStartX();
                nowlinedrawable.y = i;
                nowlinedrawable.startX = j - l;
                nowlinedrawable.endX = j1;
            } else
            {
                NowLineDrawable nowlinedrawable1 = nowLineDrawable;
                int k = getNowLineMarginStart();
                int i1 = getEventLayoutEndX();
                nowlinedrawable1.y = i;
                nowlinedrawable1.startX = k;
                nowlinedrawable1.endX = i1;
            }
            nowLineDrawable.draw(canvas);
        }
    }

    private final void drawWeekHeaderBackground(Canvas canvas)
    {
        if (showMonthHeaderImages)
        {
            setObjectToDraw(4);
            int i = getMonthHeaderSize();
            canvas.drawRect(0.0F, i, viewWidth, i + resources.weekHeaderHeight, paint);
        }
    }

    private final int getNowLineEventIndex(long l)
    {
        if (!dayHeaderView.isToday)
        {
            return -1;
        }
        Object obj;
        boolean flag1;
        for (int i = timedChips.size() - 1; i >= 0; i--)
        {
            Chip chip = (Chip)timedChips.get(i);
            if (TimelineItemUtil.shouldItemBeRenderedAsMultiday(items.getItemForChip(chip)))
            {
                boolean flag;
                if (i < 0 || i >= timedChips.size())
                {
                    flag = false;
                } else
                {
                    Chip chip1 = (Chip)timedChips.get(i);
                    if (l > items.getItemForChip(chip1).getStartMillis())
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                }
                if (flag)
                {
                    return i;
                }
            }
            if (i < 0 || i >= timedChips.size())
            {
                flag1 = false;
            } else
            {
                obj = (Chip)timedChips.get(i);
                obj = items.getItemForChip(((Chip) (obj)));
                if (l - ((TimelineItem) (obj)).getStartMillis() >= ((TimelineItem) (obj)).getEndMillis() - l)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
            }
            if (flag1)
            {
                return i;
            }
        }

        return 0;
    }

    private final int getNowLineYBusy()
    {
        int i = currentTime.hour;
        GridTimelineSegmentGeometry gridtimelinesegmentgeometry = timelineSegmentGeometry;
        int j = gridtimelinesegmentgeometry.gridHourCellHeight;
        j = (int)(float)(gridtimelinesegmentgeometry.gridlineHeight + j);
        float f = (float)currentTime.minute / 60F;
        gridtimelinesegmentgeometry = timelineSegmentGeometry;
        int k = gridtimelinesegmentgeometry.gridHourCellHeight;
        return i * j + (int)(f * (float)(int)(float)(gridtimelinesegmentgeometry.gridlineHeight + k)) + startOfGrid;
    }

    private final int getWeekHeaderSize()
    {
        if (dayViewConfig.shouldDrawExtraHeaders() && dayHeaderView.isFirstDayOfWeek)
        {
            TimelyDayHeaderView timelydayheaderview = dayHeaderView;
            if (!timelydayheaderview.showMonthHeaderImages && timelydayheaderview.isFirstDayOfMonth)
            {
                return timelydayheaderview.weekHeaderPadding;
            } else
            {
                return timelydayheaderview.weekHeaderSize;
            }
        } else
        {
            return 0;
        }
    }

    private final int getYearHeaderSize()
    {
        int i = 0;
        boolean flag;
        if (dayViewConfig.shouldDrawYearHeader() && shouldDrawYearHeader)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            i = resources.weekHeaderHeight;
        }
        return i;
    }

    private final void initializeMonthHeader(int ai[])
    {
        if (monthHeaderDrawable != null)
        {
            Object obj = ((ExtendedBitmapDrawable) (monthHeaderDrawable)).opts;
            getContext();
            int i = MonthBackgrounds.getMonthToShow$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D4III8_0(ai[1]);
            if (monthBackgrounds.monthTopColorBackgrounds[i] != ((com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions) (obj)).backgroundColor)
            {
                obj.backgroundColor = monthBackgrounds.monthTopColorBackgrounds[i];
                monthHeaderDrawable.onOptsChanged();
            }
            obj = monthHeaderDrawable;
            ai = getResources();
            i = MonthBackgrounds.MONTH_BANNER_DRAWABLE_IDS[i];
            if (i != 0)
            {
                ai = new ResourceRequestKey(ai, i);
            } else
            {
                ai = null;
            }
            ((BasicBitmapDrawable) (obj)).bind(ai);
        }
    }

    private final boolean instantiateMonthHeaderDrawable()
    {
        boolean flag1 = true;
        boolean flag;
        if (dayViewConfig.shouldDrawExtraHeaders() && dayHeaderView.isFirstDayOfMonth)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || !showMonthHeaderImages)
        {
            flag1 = false;
        } else
        if (monthHeaderDrawable == null)
        {
            if (viewWidth == 0)
            {
                waitingForMeasurement = true;
                return false;
            } else
            {
                com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions extendedoptions = new com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions(6);
                int ai[] = Utils.getDateInfo(julianDay);
                getContext();
                int i = MonthBackgrounds.getMonthToShow$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D4III8_0(ai[1]);
                extendedoptions.backgroundColor = monthBackgrounds.monthTopColorBackgrounds[i];
                extendedoptions.parallaxSpeedMultiplier = (float)viewWidth / (float)resources.monthHeaderHeight;
                com.android.bitmap.BitmapCache bitmapcache = BitmapCacheHolder.getMonthHeaderBitmapCache();
                monthHeaderDrawable = new LargeThreadpoolBitmapDrawable(getResources(), bitmapcache, false, extendedoptions);
                monthHeaderDrawable.setDecodeDimensions(viewWidth, resources.monthHeaderHeight);
                monthHeaderDrawable.setBounds(0, resources.defaultMargin, viewWidth, resources.defaultMargin + resources.monthHeaderHeight);
                monthHeaderDrawable.setCallback(this);
                return true;
            }
        }
        return flag1;
    }

    private final boolean isCreateNewEventTimeInDay()
    {
        boolean flag1 = false;
        long l = NewEventTimeChangedListenerHolder.INSTANCE.createNewEventTime;
        long l1 = getSelectedTimeInMillis(0);
        boolean flag = flag1;
        if (l >= l1)
        {
            flag = flag1;
            if (l < 0x5265c00L + l1)
            {
                flag = true;
            }
        }
        return flag;
    }

    static final int lambda$loadTimelineItems$1$TimelyDayView(Map map, Chip chip, Chip chip1)
    {
        return TimelineItem.AllDayComparator.compare((TimelineItem)map.get(chip), (TimelineItem)map.get(chip1));
    }

    private final boolean loadTimelineItems(List list)
    {
        Trace.beginSection("DayView loadTimelineItems");
        if (list.size() > 0)
        {
            removeView(noEventsView);
        }
        if (stickyAllDayEventsView.getParent() == this)
        {
            removeView(stickyAllDayEventsView);
        }
        boolean flag = false;
        if (!dayViewConfig.inListView())
        {
            flag = true;
        }
        ChipFragmentInfo chipfragmentinfo;
        Object obj;
        ChipFragmentInfo chipfragmentinfo1;
        int j;
        chipfragmentinfo = getChipFragmentInfo(false);
        obj = baseDate;
        ((Time) (obj)).writeFieldsToImpl();
        j = android.text.format.Time.getJulianDay(((Time) (obj)).impl.toMillis(true), baseDate.gmtoff);
        chipfragmentinfo1 = getChipFragmentInfo(true);
        obj = new HashMap();
        int i = 0;
_L2:
        TimelineItem timelineitem;
        if (i >= list.size())
        {
            break MISSING_BLOCK_LABEL_444;
        }
        timelineitem = (TimelineItem)list.get(i);
        if (timelineitem == null)
        {
            break MISSING_BLOCK_LABEL_164;
        }
        if (!timelineitem.spansAtLeastOneDay() || dayViewConfig.inListView())
        {
            break MISSING_BLOCK_LABEL_177;
        }
        items.put(timelineitem, null);
        break MISSING_BLOCK_LABEL_552;
        Chip chip;
        ChipScheduleGridAnimationHelper chipschedulegridanimationhelper;
        ChipViewModel chipviewmodel;
        chip = (Chip)chipRecycler.getOrCreateObject();
        chip.partitionInfo = new SimplePartitionItem(timelineitem);
        chip.setViewModel(chipViewModelFactory.buildViewModel(timelineitem, chipfragmentinfo, j));
        if (dayViewConfig.isChipClickable())
        {
            chip.setActionListener(this);
            class .Lambda._cls1
                implements com.google.android.calendar.timeline.chip.Chip.ChipLongPressListener
            {

                private final TimelyDayView arg$1;

                public final boolean onChipLongPress(Chip chip1, Point point)
                {
                    DndAnalytics.logDndFailedStartWrongView(arg$1.getContext(), "long_press_timeline_chip_agenda");
                    return false;
                }

            .Lambda._cls1()
            {
                arg$1 = TimelyDayView.this;
            }
            }

            chip.longPressListener = new .Lambda._cls1();
            chip.updateInteractionListeners();
            TimelyDayViewSwipeHelper timelydayviewswipehelper = swipe;
            chip.swipeHelper._flddelegate = timelydayviewswipehelper;
            chip.configureSwipeState(null);
        }
        chipschedulegridanimationhelper = chipScheduleGridAnimationHelper;
        chipviewmodel = chipViewModelFactory.buildViewModel(timelineitem, chipfragmentinfo1, j);
        boolean flag1;
        if (!timelineitem.isAllDay())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        chipschedulegridanimationhelper.chips.put(chip, new com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper.ChipRegistry(chip.viewModel, chipviewmodel, flag1));
        items.put(timelineitem, chip);
        if (flag)
        {
            break MISSING_BLOCK_LABEL_378;
        }
        if (chipHeights.getAgendaViewHeight(chip.viewModel.getChipType()) != 0)
        {
            flag = true;
        }
        if (isInAllDaySection(timelineitem))
        {
            stickyAllDayEventsView.addView(chip);
            allDayChips.add(chip);
            ((Map) (obj)).put(chip, timelineitem);
            break MISSING_BLOCK_LABEL_552;
        }
        timedChips.add(chip);
        addView(chip);
        chip.bringToFront();
        break MISSING_BLOCK_LABEL_552;
        if (allDayChips.size() > 0)
        {
            list = stickyAllDayEventsView;
            if (list.getParent() != this)
            {
                addView(list, 0);
            }
            list.bringToFront();
            class .Lambda._cls2
                implements Comparator
            {

                private final Map arg$1;

                public final int compare(Object obj1, Object obj2)
                {
                    return TimelyDayView.lambda$loadTimelineItems$1$TimelyDayView(arg$1, (Chip)obj1, (Chip)obj2);
                }

            .Lambda._cls2(Map map)
            {
                arg$1 = map;
            }
            }

            Collections.sort(allDayChips, new .Lambda._cls2(((Map) (obj))));
            list = chipGeometry;
            GridPartitionItemGeometry.doComputePositions(allDayChips, 0L, true, false, false);
        }
        list = chipGeometry;
        GridPartitionItemGeometry.doComputePositions(timedChips, 0x1b7740L, false, false, false);
        if (isCreateNewEventTimeInDay())
        {
            installCreateEventView();
        }
        Trace.endSection();
        return flag;
        list;
        Trace.endSection();
        throw list;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private final void remeasure()
    {
        Trace.beginSection("remeasure");
        if (!dayViewConfig.shouldDrawExtraHeaders() || !dayHeaderView.isFirstDayOfMonth) goto _L2; else goto _L1
_L1:
        int j = dayHeaderView.monthHeaderSize;
_L79:
        if (!dayViewConfig.shouldDrawExtraHeaders() || !dayHeaderView.isFirstDayOfWeek) goto _L4; else goto _L3
_L3:
        Object obj = dayHeaderView;
        if (((TimelyDayHeaderView) (obj)).showMonthHeaderImages || !((TimelyDayHeaderView) (obj)).isFirstDayOfMonth) goto _L6; else goto _L5
_L5:
        int i = ((TimelyDayHeaderView) (obj)).weekHeaderPadding;
_L11:
        float f;
        Object obj1;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int l2;
        int i3;
        long l3;
        boolean flag;
        if (dayViewConfig.shouldDrawYearHeader() && shouldDrawYearHeader)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k == 0) goto _L8; else goto _L7
_L7:
        k = resources.weekHeaderHeight;
_L80:
        agendaFirstChipY = k + (j + i) + resources.defaultMargin;
        l1 = getEventLayoutStartX();
        i2 = getEventLayoutEndX();
        i1 = stickyAllDayEventsView.scrollOffset;
        chipGeometry.cellWidth = i2 - l1;
        i = 0;
        j1 = chipHeights.chipSingleHeight + timelineSegmentGeometry.chipVerticalSpacing;
        j = 0;
        k = 0;
        l = 0;
_L10:
        if (k >= allDayChips.size())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (Chip)allDayChips.get(k);
        l = resources.gridChipsAllDayTopMargin + (j1 * k - i1);
        ChipScheduleGridAnimationHelper chipschedulegridanimationhelper = chipScheduleGridAnimationHelper;
        k1 = getEventLayoutStartX();
        j2 = getEventLayoutEndX();
        int k2 = chipHeights.chipSingleHeight;
        ((com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper.ChipRegistry)chipschedulegridanimationhelper.chips.get(obj)).gridCoordinates.set(k1, l, j2, k2 + l);
        k1 = chipHeights.getAgendaViewHeight(((Chip) (obj)).viewModel.getChipType());
        ((com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper.ChipRegistry)chipScheduleGridAnimationHelper.chips.get(obj)).scheduleCoordinates.set(l1, i, i2, i + k1);
        l = resources.chipVerticalSpacing;
        j2 = resources.chipVerticalSpacing;
        k++;
        j += l + k1;
        l = i;
        i += j2 + k1;
        if (true) goto _L10; else goto _L9
_L6:
        i = ((TimelyDayHeaderView) (obj)).weekHeaderSize;
          goto _L11
_L9:
        k = l + agendaFirstChipY;
        l = i + agendaFirstChipY;
        startOfGrid = 0;
        if (j != 0) goto _L13; else goto _L12
_L12:
        i = 0;
_L59:
        obj = stickyAllDayEventsView;
        i1 = agendaFirstChipY;
        k1 = agendaFirstChipY;
        obj.agendaYStart = i1;
        obj.agendaYEnd = (k1 + j) - i;
        i1 = allDayChips.size();
        i = i1;
        if (i1 <= 3)
        {
            break MISSING_BLOCK_LABEL_511;
        }
        i = 2;
        startOfGrid = startOfGrid + j1;
        i1 = i * j1;
        i = i1;
        if (!dayViewConfig.inListView()) goto _L15; else goto _L14
_L14:
        if (!dayViewConfig.inGridMode()) goto _L17; else goto _L16
_L16:
        i = stickyAllDayEventsView.gridHeight;
_L15:
        j2 = (int)((float)j + animationStatusProvider.getGridModeRatio() * (float)(i - j));
        j = startOfGrid;
        f = j2;
        startOfGrid = (int)((float)(i - j2) * animationStatusProvider.getGridModeRatio() + f + (float)(resources.dayHeaderHeight + resources.dayHeaderBottomMargin) * (1.0F - animationStatusProvider.getGridModeRatio())) + j;
        obj = currentTime;
        ((Time) (obj)).writeFieldsToImpl();
        l3 = ((Time) (obj)).impl.toMillis(false);
        l2 = getNowLineEventIndex(l3);
        nowlineEventIndex = l2;
        if (l2 < 0) goto _L19; else goto _L18
_L18:
        if (l2 < timedChips.size()) goto _L20; else goto _L19
_L60:
        i = 0;
        if (geometry != null)
        {
            geometry.julianDay = julianDay;
            geometry.updateChipsLayoutParams(timedChips);
            orderedChildIndices = null;
        }
        nowLineYAgenda = -1;
        obj = new Rect();
        i1 = 0;
        j = k;
        k = i;
        i = l;
_L82:
        if (i1 >= timedChips.size()) goto _L22; else goto _L21
_L21:
        obj1 = (Chip)timedChips.get(i1);
        if (geometry == null) goto _L24; else goto _L23
_L23:
        geometry.getChipFrame(((View) (obj1)), i2 - l1, isRtl, ((Rect) (obj)));
        ((Rect) (obj)).offset(l1, startOfGrid);
_L63:
        ChipScheduleGridAnimationHelper chipschedulegridanimationhelper1 = chipScheduleGridAnimationHelper;
        l = ((Rect) (obj)).left;
        j1 = ((Rect) (obj)).top;
        k1 = ((Rect) (obj)).right;
        i3 = ((Rect) (obj)).bottom;
        ((com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper.ChipRegistry)chipschedulegridanimationhelper1.chips.get(obj1)).gridCoordinates.set(l, j1, k1, i3);
        i3 = chipHeights.getAgendaViewHeight(((Chip) (obj1)).viewModel.getChipType());
        if (l2 == i1)
        {
            j1 = 1;
        } else
        {
            j1 = 0;
        }
        if (j1 == 0) goto _L26; else goto _L25
_L25:
        if (!flag) goto _L28; else goto _L27
_L27:
        nowLineYAgenda = i;
        if (!dayViewConfig.shouldNeverDrawNowLine() && dayHeaderView.isToday)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        if (l == 0) goto _L30; else goto _L29
_L29:
        if (!hasItems) goto _L30; else goto _L31
_L31:
        l = 1;
_L83:
        if (l == 0) goto _L26; else goto _L32
_L32:
        i += resources.chipVerticalSpacing + (int)nowLineDrawable.paint.getStrokeWidth();
_L26:
        ((com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper.ChipRegistry)chipScheduleGridAnimationHelper.chips.get(obj1)).scheduleCoordinates.set(l1, i, i2, i + i3);
        l = i;
        if (j1 == 0) goto _L34; else goto _L33
_L33:
        if (!flag) goto _L36; else goto _L35
_L35:
        if (!dayViewConfig.shouldDrawExtraHeaders() || !dayHeaderView.isFirstDayOfMonth) goto _L38; else goto _L37
_L37:
        j1 = dayHeaderView.monthHeaderSize;
_L85:
        if (!dayViewConfig.shouldDrawExtraHeaders() || !dayHeaderView.isFirstDayOfWeek) goto _L40; else goto _L39
_L39:
        obj1 = dayHeaderView;
        if (((TimelyDayHeaderView) (obj1)).showMonthHeaderImages || !((TimelyDayHeaderView) (obj1)).isFirstDayOfMonth) goto _L42; else goto _L41
_L41:
        l = ((TimelyDayHeaderView) (obj1)).weekHeaderPadding;
_L68:
        if (dayViewConfig.shouldDrawYearHeader() && shouldDrawYearHeader)
        {
            k1 = 1;
        } else
        {
            k1 = 0;
        }
        if (k1 == 0) goto _L44; else goto _L43
_L43:
        k1 = resources.weekHeaderHeight;
_L86:
        if (j <= k1 + (j1 + l)) goto _L46; else goto _L45
_L45:
        l = j - resources.chipVerticalSpacing;
_L78:
        if (!dayViewConfig.shouldDrawExtraHeaders() || !dayHeaderView.isFirstDayOfMonth) goto _L48; else goto _L47
_L47:
        j1 = dayHeaderView.monthHeaderSize;
_L87:
        if (!dayViewConfig.shouldDrawExtraHeaders() || !dayHeaderView.isFirstDayOfWeek) goto _L50; else goto _L49
_L49:
        obj1 = dayHeaderView;
        if (((TimelyDayHeaderView) (obj1)).showMonthHeaderImages || !((TimelyDayHeaderView) (obj1)).isFirstDayOfMonth) goto _L52; else goto _L51
_L51:
        j = ((TimelyDayHeaderView) (obj1)).weekHeaderPadding;
_L69:
        float f1;
        float f2;
        GridPartitionItemGeometry gridpartitionitemgeometry;
        GridTimelineSegmentGeometry gridtimelinesegmentgeometry;
        boolean flag1;
        if (dayViewConfig.shouldDrawYearHeader() && shouldDrawYearHeader)
        {
            k1 = 1;
        } else
        {
            k1 = 0;
        }
        if (k1 == 0)
        {
            break MISSING_BLOCK_LABEL_2127;
        }
        k1 = resources.weekHeaderHeight;
_L88:
        j = k1 + (j1 + j);
        if (dayHeaderView.isToday) goto _L54; else goto _L53
_L53:
        j *= -1;
          goto _L55
_L81:
        if (i3 <= 0) goto _L57; else goto _L56
_L56:
        k = resources.chipVerticalSpacing + i3 + i;
          goto _L58
_L13:
        i = resources.chipVerticalSpacing;
          goto _L59
_L17:
        stickyAllDayEventsView.setGridHeight(i1);
        i = i1;
          goto _L15
        obj;
        Trace.endSection();
        throw obj;
_L20:
        obj = (Chip)timedChips.get(l2);
        flag = isNowLineBeforeEvent(l3, items.getItemForChip(((Chip) (obj))));
          goto _L60
_L24:
        gridpartitionitemgeometry = chipGeometry;
        gridtimelinesegmentgeometry = timelineSegmentGeometry;
        j1 = julianDay;
        k1 = startOfGrid;
        flag1 = isRtl;
        l = gridtimelinesegmentgeometry.computeItemTop(((TimelineSegment) (obj1)), j1, k1);
        j1 = gridtimelinesegmentgeometry.computeItemBottom(((TimelineSegment) (obj1)), j1, k1, l);
        k1 = ((PartitionItem) (obj1)).getMaxPartitions();
        if (k1 > 1) goto _L62; else goto _L61
_L61:
        f1 = 0.0F;
_L64:
        f1 = ((float)gridpartitionitemgeometry.cellWidth - f1) / (float)k1;
        f2 = ((PartitionItem) (obj1)).getPartition();
        k1 = l1 + (int)((f1 + (float)gridpartitionitemgeometry.chipHorizontalSpacing) * f2);
        i3 = ((PartitionItem) (obj1)).getMaxPartitions();
        if (i3 > 1)
        {
            break MISSING_BLOCK_LABEL_1596;
        }
        f1 = 0.0F;
_L65:
        ((Rect) (obj)).set(k1, l, (int)(((float)gridpartitionitemgeometry.cellWidth - f1) / (float)i3) + k1, j1);
          goto _L63
_L62:
        f1 = (k1 - 1) * gridpartitionitemgeometry.chipHorizontalSpacing;
          goto _L64
        f1 = (i3 - 1) * gridpartitionitemgeometry.chipHorizontalSpacing;
          goto _L65
_L28:
        if (i3 <= 0) goto _L67; else goto _L66
_L66:
        l = i + i3 + resources.chipVerticalSpacing;
_L84:
        nowLineYAgenda = l;
          goto _L26
_L36:
        l = resources.chipVerticalSpacing + (int)nowLineDrawable.paint.getStrokeWidth() + i;
        j = i;
        i = l;
          goto _L35
_L42:
        l = ((TimelyDayHeaderView) (obj1)).weekHeaderSize;
          goto _L68
_L52:
        j = ((TimelyDayHeaderView) (obj1)).weekHeaderSize;
          goto _L69
_L54:
        if (hasItems || mDataLoaded) goto _L71; else goto _L70
_L70:
        j = -1;
          goto _L55
_L71:
        j = Math.max(l, j) * -1;
          goto _L55
_L22:
        if (dayHeaderView.isToday && allDayChips.size() != 0 && timedChips.size() == 0)
        {
            i = resources.chipVerticalSpacing + (int)nowLineDrawable.paint.getStrokeWidth() + i;
        }
        dayViewAgendaMeasuredHeight = i + getBottomPadding();
        i = agendaFirstChipY;
        j = Math.max(resources.dayHeaderHeight, j2);
        obj = timelineSegmentGeometry;
        l = ((GridTimelineSegmentGeometry) (obj)).gridHourCellHeight;
        dayViewGridMeasuredHeight = i + j + (int)(float)(((GridTimelineSegmentGeometry) (obj)).gridlineHeight + l) * 24 + resources.gridHoursTextSize / 2 + getBottomPadding();
        if (!dayHeaderView.isToday) goto _L73; else goto _L72
_L72:
        if (dayViewConfig.getAgendaScrollCallback() != null)
        {
            dayViewConfig.getAgendaScrollCallback().setAgendaScrollOffsetToNow(k);
        }
        if (noEventsView.getParent() != this) goto _L75; else goto _L74
_L74:
        nowLineYAgenda = agendaFirstChipY + chipHeights.chipSingleHeight;
_L77:
        nowLineYGrid = getNowLineYBusy();
_L73:
        Trace.endSection();
        return;
_L75:
        if (nowLineYAgenda != -1) goto _L77; else goto _L76
_L76:
        nowLineYAgenda = agendaFirstChipY + j2;
          goto _L77
_L57:
        k = i;
          goto _L58
_L34:
        j = k;
        i = l;
        break; /* Loop/switch isn't completed */
_L46:
        l = j;
          goto _L78
_L2:
        j = 0;
          goto _L79
_L4:
        i = 0;
          goto _L11
_L8:
        k = 0;
          goto _L80
_L19:
        flag = true;
          goto _L60
_L55:
        l = i;
        if (j == -1) goto _L34; else goto _L81
_L58:
        i1++;
        l = i;
        i = k;
        k = j;
        j = l;
          goto _L82
_L30:
        l = 0;
          goto _L83
_L67:
        l = i;
          goto _L84
_L38:
        j1 = 0;
          goto _L85
_L40:
        l = 0;
          goto _L68
_L44:
        k1 = 0;
          goto _L86
_L48:
        j1 = 0;
          goto _L87
_L50:
        j = 0;
          goto _L69
        k1 = 0;
          goto _L88
    }

    private final void setObjectToDraw(int i)
    {
        int j = 0xff000000;
        paint.reset();
        switch (i)
        {
        case 1: // '\001'
        default:
            return;

        case 0: // '\0'
            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setColor(resources.gridLineColor);
            paint.setStrokeWidth(timelineSegmentGeometry.gridlineHeight);
            paint.setAntiAlias(false);
            paint.setAlpha((int)(animationStatusProvider.getGridModeRatio() * 255F));
            return;

        case 2: // '\002'
            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setStrokeWidth(timelineSegmentGeometry.gridlineHeight);
            paint.setColor(resources.gridHourTextColor);
            paint.setTextSize(resources.gridHoursTextSize);
            Paint paint1 = paint;
            android.graphics.Paint.Align align;
            if (isRtl)
            {
                align = android.graphics.Paint.Align.RIGHT;
            } else
            {
                align = android.graphics.Paint.Align.LEFT;
            }
            paint1.setTextAlign(align);
            paint.setAntiAlias(true);
            paint.setAlpha((int)(animationStatusProvider.getGridModeRatio() * 255F));
            return;

        case 5: // '\005'
            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setColor(0xff000000);
            paint.setTextSize(resources.monthHeaderTextSize);
            Paint paint2 = paint;
            Object obj;
            if (Material.robotoMedium != null)
            {
                obj = Material.robotoMedium;
            } else
            {
                obj = Typeface.create("sans-serif-medium", 0);
                Material.robotoMedium = ((Typeface) (obj));
            }
            paint2.setTypeface(((Typeface) (obj)));
            paint2 = paint;
            if (isRtl)
            {
                obj = android.graphics.Paint.Align.RIGHT;
            } else
            {
                obj = android.graphics.Paint.Align.LEFT;
            }
            paint2.setTextAlign(((android.graphics.Paint.Align) (obj)));
            paint.setAntiAlias(true);
            return;

        case 3: // '\003'
            paint.setStyle(android.graphics.Paint.Style.FILL);
            Object obj1 = paint;
            i = j;
            if (showMonthHeaderImages)
            {
                i = resources.weekHeaderTextColor;
            }
            ((Paint) (obj1)).setColor(i);
            paint.setTextSize(resources.weekHeaderTextSize);
            Paint paint3 = paint;
            if (isRtl)
            {
                obj1 = android.graphics.Paint.Align.RIGHT;
            } else
            {
                obj1 = android.graphics.Paint.Align.LEFT;
            }
            paint3.setTextAlign(((android.graphics.Paint.Align) (obj1)));
            paint.setTypeface(ROBOTO_REGULAR);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(resources.weekHeaderTextStrokeWidth);
            return;

        case 4: // '\004'
            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setColor(resources.weekHeaderBackgroundColor);
            return;
        }
    }

    private final void setViewWidth(int i)
    {
        if (i != viewWidth)
        {
            viewWidth = i;
            chipLayoutStopX = viewWidth - resources.horizontalMargin;
            if (waitingForMeasurement)
            {
                waitingForMeasurement = false;
                if (instantiateMonthHeaderDrawable())
                {
                    initializeMonthHeader(Utils.getDateInfo(julianDay));
                }
            }
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof com.google.android.calendar.timely.gridviews.GridDayView.LayoutParams;
    }

    public final Animator createRestoreAnimator(TimelineItem timelineitem)
    {
        timelineitem = (com.google.android.calendar.timely.timeline.TimelineItemCollection.Entry)items.persistentEntries.get(timelineitem);
        if (timelineitem == null)
        {
            timelineitem = null;
        } else
        {
            timelineitem = ((com.google.android.calendar.timely.timeline.TimelineItemCollection.Entry) (timelineitem)).chip;
        }
        if (timelineitem == null || timelineitem.getTranslationX() == 0.0F)
        {
            return null;
        } else
        {
            return ChipAnimations.createTranslationXSwipeAnimator(timelineitem, 0.0F, ChipAnimations.calculateTranslationDuration(-timelineitem.getTranslationX(), Float.valueOf(0.0F)));
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if (!super.dispatchTouchEvent(motionevent))
        {
            gestureDetector.onTouchEvent(motionevent);
        }
        return true;
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if (animationStatusProvider.getGridModeRatio() == 0.0F)
        {
            drawNowLine(canvas);
        }
    }

    public final Animator excludeItemAnimated(TimelineItem timelineitem)
    {
        Object obj = items;
        if (timelineitem != null)
        {
            boolean flag;
            if (((TimelineItemCollection) (obj)).entries.remove(timelineitem) != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && ((TimelineItemCollection) (obj)).animatorProvider != null)
            {
                com.google.android.calendar.timely.timeline.TimelineItemCollection.AnimatorProvider animatorprovider = ((TimelineItemCollection) (obj)).animatorProvider;
                obj = (com.google.android.calendar.timely.timeline.TimelineItemCollection.Entry)((TimelineItemCollection) (obj)).persistentEntries.get(timelineitem);
                if (obj == null)
                {
                    obj = null;
                } else
                {
                    obj = ((com.google.android.calendar.timely.timeline.TimelineItemCollection.Entry) (obj)).chip;
                }
                return animatorprovider.createAfterExcludeItemAnimator(((Chip) (obj)), timelineitem);
            }
        }
        return null;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new com.google.android.calendar.timely.gridviews.GridDayView.LayoutParams(0, 0, 0.0F, 0.0F, 0x7fffffff);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new com.google.android.calendar.timely.gridviews.GridDayView.LayoutParams(layoutparams);
    }

    protected int getBottomPadding()
    {
        return resources.defaultMargin;
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        if (orderedChildIndices == null || orderedChildIndices.length != i)
        {
            orderedChildIndices = GridDayView.getChildDrawingOrder(this);
        }
        return orderedChildIndices[j];
    }

    public final Chip getChipForItem(TimelineItem timelineitem)
    {
        timelineitem = (com.google.android.calendar.timely.timeline.TimelineItemCollection.Entry)items.persistentEntries.get(timelineitem);
        if (timelineitem == null)
        {
            return null;
        } else
        {
            return ((com.google.android.calendar.timely.timeline.TimelineItemCollection.Entry) (timelineitem)).chip;
        }
    }

    final ChipFragmentInfo getChipFragmentInfo(boolean flag)
    {
        com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder builder;
        byte byte0;
        if (flag)
        {
            byte0 = 3;
        } else
        {
            byte0 = 0;
        }
        builder = new com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder();
        builder.showMultidayAnnotations = true;
        builder.shouldShowImages = dayViewConfig.canDrawBackgroundImage();
        builder.viewType = Integer.valueOf(byte0);
        builder.swipeConfigProvider = swipe;
        return builder.build();
    }

    final int getCurrentJulianDay()
    {
        Time time = baseDate;
        time.writeFieldsToImpl();
        return android.text.format.Time.getJulianDay(time.impl.toMillis(true), baseDate.gmtoff);
    }

    public int getEventLayoutEndX()
    {
        if (isRtl)
        {
            return viewWidth - resources.chipLayoutStartX;
        } else
        {
            return chipLayoutStopX;
        }
    }

    public int getEventLayoutStartX()
    {
        if (isRtl)
        {
            return resources.horizontalMargin;
        } else
        {
            return resources.chipLayoutStartX;
        }
    }

    public final int getGridHourStartOffset()
    {
        android.view.ViewParent viewparent;
        int i;
        int k;
        if (dayHeaderView.isToday)
        {
            i = TimeUtils.previousHourBefore(currentTime, 30);
            GridTimelineSegmentGeometry gridtimelinesegmentgeometry = timelineSegmentGeometry;
            int j = gridtimelinesegmentgeometry.gridHourCellHeight;
            i *= (int)(float)(gridtimelinesegmentgeometry.gridlineHeight + j);
        } else
        if (timedChips != null && timedChips.size() > 0)
        {
            recycleTime.timezone = android.text.format.Time.getCurrentTimezone();
            recycleTime.set(((Chip)timedChips.get(0)).partitionInfo.getStartMillis());
            i = TimeUtils.previousHourBefore(recycleTime, 30);
            GridTimelineSegmentGeometry gridtimelinesegmentgeometry1 = timelineSegmentGeometry;
            int l = gridtimelinesegmentgeometry1.gridHourCellHeight;
            i *= (int)(float)(gridtimelinesegmentgeometry1.gridlineHeight + l);
        } else
        {
            i = agendaFirstChipY;
            GridTimelineSegmentGeometry gridtimelinesegmentgeometry2 = timelineSegmentGeometry;
            int i1 = gridtimelinesegmentgeometry2.gridHourCellHeight;
            i += (int)(float)(gridtimelinesegmentgeometry2.gridlineHeight + i1) << 3;
        }
        viewparent = getParent();
        k = i;
        if (viewparent instanceof View)
        {
            k = ((View)viewparent).getHeight();
            k = Math.min(i, dayViewGridMeasuredHeight - k);
        }
        return k * -1;
    }

    final int getMonthHeaderSize()
    {
        if (dayViewConfig.shouldDrawExtraHeaders() && dayHeaderView.isFirstDayOfMonth)
        {
            return dayHeaderView.monthHeaderSize;
        } else
        {
            return 0;
        }
    }

    public final int getMonthLabelBottom()
    {
        if (monthHeaderDrawable != null)
        {
            return resources.monthHeaderTopMargin + resources.monthHeaderTextSize;
        } else
        {
            return resources.monthHeaderHeight - resources.monthHeaderBottomMargin;
        }
    }

    final int getNoItemsAgendaViewHeight()
    {
        int l = 0;
        int i;
        int j;
        int k;
        if (dayViewConfig.shouldDrawExtraHeaders() && dayHeaderView.isFirstDayOfMonth)
        {
            j = dayHeaderView.monthHeaderSize;
        } else
        {
            j = 0;
        }
        if (dayViewConfig.shouldDrawExtraHeaders() && dayHeaderView.isFirstDayOfWeek)
        {
            TimelyDayHeaderView timelydayheaderview = dayHeaderView;
            if (!timelydayheaderview.showMonthHeaderImages && timelydayheaderview.isFirstDayOfMonth)
            {
                i = timelydayheaderview.weekHeaderPadding;
            } else
            {
                i = timelydayheaderview.weekHeaderSize;
            }
        } else
        {
            i = 0;
        }
        if (dayViewConfig.shouldDrawYearHeader() && shouldDrawYearHeader)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0)
        {
            k = resources.weekHeaderHeight;
        } else
        {
            k = 0;
        }
        if (forceShow)
        {
            l = resources.dayHeaderHeight + resources.defaultMargin;
        }
        return k + (i + j) + l;
    }

    protected int getNowLineMarginStart()
    {
        return nowLineDrawable.oneDayStartPadding;
    }

    final long getSelectedTimeInMillis(int i)
    {
        recycleTime.timezone = Utils.getTimeZoneId(getContext());
        Time time = recycleTime;
        return Time.calculateDayHourMinuteMillis(julianDay, i, 0, time.timezone, time);
    }

    final void installCreateEventView()
    {
        createNewEventView.refreshFromModel();
        if (createNewEventView.getParent() != this)
        {
            addView(createNewEventView);
            if (AccessibilityUtils.isAccessibilityEnabled(getContext()))
            {
                CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
                CreateNewEventView createneweventview = createNewEventView;
                createneweventview.getClass();
                class .Lambda._cls0
                    implements Runnable
                {

                    private final CreateNewEventView arg$1;

                    public final void run()
                    {
                        arg$1.requestFocus();
                    }

            .Lambda._cls0(CreateNewEventView createneweventview)
            {
                arg$1 = createneweventview;
            }
                }

                calendarexecutor.execute(new .Lambda._cls0(createneweventview));
            }
        }
    }

    public final void invalidateChips()
    {
        int j = getChildCount();
        for (int i = 0; i < j; i++)
        {
            View view = getChildAt(i);
            if (view instanceof Chip)
            {
                view.invalidate();
            }
        }

    }

    final boolean isInAllDaySection(TimelineItem timelineitem)
    {
label0:
        {
            if (!timelineitem.isAllDay())
            {
                Time time = baseDate;
                time.writeFieldsToImpl();
                if (TimelineItemUtil.isFirstDay(timelineitem, android.text.format.Time.getJulianDay(time.impl.toMillis(true), baseDate.gmtoff)) || TimelineItemUtil.getDurationHours(timelineitem) < 24L)
                {
                    break label0;
                }
            }
            return true;
        }
        return false;
    }

    final boolean isNowLineBeforeEvent(long l, TimelineItem timelineitem)
    {
        if (!TimelineItemUtil.shouldItemBeRenderedAsMultiday(timelineitem)) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (l > timelineitem.getStartMillis())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        boolean flag1;
        if (l - timelineitem.getStartMillis() >= timelineitem.getEndMillis() - l)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    final void layoutNoEventsTodayView()
    {
        int k = 0;
        int i;
        int j;
        boolean flag;
        if (dayViewConfig.shouldDrawExtraHeaders() && dayHeaderView.isFirstDayOfMonth)
        {
            j = dayHeaderView.monthHeaderSize;
        } else
        {
            j = 0;
        }
        if (dayViewConfig.shouldDrawExtraHeaders() && dayHeaderView.isFirstDayOfWeek)
        {
            TimelyDayHeaderView timelydayheaderview = dayHeaderView;
            if (!timelydayheaderview.showMonthHeaderImages && timelydayheaderview.isFirstDayOfMonth)
            {
                i = timelydayheaderview.weekHeaderPadding;
            } else
            {
                i = timelydayheaderview.weekHeaderSize;
            }
        } else
        {
            i = 0;
        }
        if (dayViewConfig.shouldDrawYearHeader() && shouldDrawYearHeader)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            k = resources.weekHeaderHeight;
        }
        i = i + j + k;
        noEventsView.layout(getEventLayoutStartX(), i, getEventLayoutEndX(), resources.dayHeaderHeight + i);
        noEventsView.invalidate();
    }

    final void maybeUpdateMonthHeaderParallax()
    {
        if ((getParent() instanceof TimelineRecyclerView) && dayHeaderView.isFirstDayOfMonth && monthHeaderDrawable != null)
        {
            Object obj = (TimelineRecyclerView)getParent();
            int i = resources.defaultMargin + resources.monthHeaderHeight;
            float f = (float)(getTop() + i) / (float)(((TimelineRecyclerView) (obj)).getHeight() + i);
            obj = monthHeaderDrawable;
            obj.parallaxFraction = f;
            ((ExtendedBitmapDrawable) (obj)).invalidateSelf();
        }
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        calendarProperties.registerListener(5, this);
        calendarProperties.registerListener(7, this);
        calendarProperties.registerListener(0, this);
        calendarProperties.registerListener(10, this);
        calendarProperties.registerListener(1, this);
        noEventsView.setOnClickListener(noEventsClickListener);
        if (dayViewConfig.shouldDrawDayHeader() && !isTablet)
        {
            dayHeaderView.setOnClickListener(this);
        }
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        int j;
        boolean flag;
        flag = true;
        j = -1;
        i;
        JVM INSTR tableswitch 0 10: default 64
    //                   0 65
    //                   1 140
    //                   2 64
    //                   3 64
    //                   4 64
    //                   5 217
    //                   6 64
    //                   7 355
    //                   8 64
    //                   9 64
    //                   10 454;
           goto _L1 _L2 _L3 _L1 _L1 _L1 _L4 _L1 _L5 _L1 _L1 _L6
_L1:
        return;
_L2:
        obj = (String)obj;
        dayHeaderView.updateCurrentTime();
        dayHeaderView.invalidate();
        baseDate.timezone = ((String) (obj));
        Time time = baseDate;
        time.writeFieldsToImpl();
        time.impl.normalize(true);
        time.copyFieldsFromImpl();
        time = currentTime;
        time.writeFieldsToImpl();
        time.impl.switchTimezone(((String) (obj)));
        time.copyFieldsFromImpl();
        invalidate();
        return;
_L3:
        if (monthHeaderDrawable != null)
        {
            LargeThreadpoolBitmapDrawable largethreadpoolbitmapdrawable = monthHeaderDrawable;
            Resources resources1 = getResources();
            i = dayHeaderView.recycleCalendar.get(2);
            j = ((Integer)obj).intValue();
            i = MonthBackgrounds.MONTH_BANNER_DRAWABLE_IDS[(i + j) % 12];
            if (i != 0)
            {
                obj = new ResourceRequestKey(resources1, i);
            } else
            {
                obj = null;
            }
            largethreadpoolbitmapdrawable.bind(((com.android.bitmap.RequestKey) (obj)));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        obj = dayHeaderView;
        if (((TimelyDayHeaderView) (obj)).dayOfWeek != PreferenceUtils.getFirstDayOfWeekAsCalendar(((TimelyDayHeaderView) (obj)).getContext()))
        {
            flag = false;
        }
        obj.isFirstDayOfWeek = flag;
        dayHeaderView.invalidate();
        if (dayHeaderView.isFirstDayOfWeek && dateInfo != null)
        {
            obj = DateTimeFormatHelper.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
            }
            obj = (DateTimeFormatHelper)obj;
            int ai[] = dateInfo;
            if (CalendarProperties.getBooleanProperty(7).booleanValue())
            {
                j = com.android.datetimepicker.Utils.getWeekNumberInYear(julianDay, Utils.getFirstDayOfWeekAsTime(getContext()));
            }
            weekHeaderText = ((DateTimeFormatHelper) (obj)).getWeekRangeText(ai, false, j);
        }
        remeasure();
        requestLayout();
        return;
_L5:
        if (dayHeaderView.isFirstDayOfWeek && dateInfo != null)
        {
            obj = DateTimeFormatHelper.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
            }
            obj = (DateTimeFormatHelper)obj;
            int ai1[] = dateInfo;
            if (CalendarProperties.getBooleanProperty(7).booleanValue())
            {
                j = com.android.datetimepicker.Utils.getWeekNumberInYear(julianDay, Utils.getFirstDayOfWeekAsTime(getContext()));
            }
            weekHeaderText = ((DateTimeFormatHelper) (obj)).getWeekRangeText(ai1, false, j);
            dayHeaderView.invalidate();
            return;
        }
        if (true) goto _L1; else goto _L6
_L6:
        timelineSegmentGeometry.gridHourCellHeight = ((Integer)obj).intValue();
        remeasure();
        requestLayout();
        return;
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
            obj = getChipFragmentInfo(false);
            Time time = baseDate;
            time.writeFieldsToImpl();
            int i = android.text.format.Time.getJulianDay(time.impl.toMillis(true), baseDate.gmtoff);
            chip = new EventInfoAnimationData(chip, chip.viewModel, ((ChipFragmentInfo) (obj)), i);
            ((OnLaunchDetailsHandler)getContext()).onLaunchDetails(timelineitem, chip);
            return;
        }
    }

    public final void onChipSecondaryAction(Chip chip)
    {
        Object obj = items.getItemForChip(chip);
        if (!(obj instanceof TimelineTask))
        {
            LogUtils.w(TAG, "Not executing chip secondary action, getItemForChip() is not a task.", new Object[0]);
        } else
        {
            chip = getContext().getApplicationContext();
            obj = (TimelineTask)obj;
            boolean flag = ExperimentalOptions.isTaskAssistStagingEnabled(chip);
            if (chip != null && ((TimelineTask) (obj)).taskAssistHolder != null)
            {
                ((TimelineTask) (obj)).taskAssistHolder.logClick(chip, "chip_action", flag);
                ((TimelineTask) (obj)).taskAssistHolder.gotoAssist(chip);
                return;
            }
        }
    }

    public void onClick(View view)
    {
        CreateNewEventView.removeSelectedTime();
        if (timelineModeChangedListener != null)
        {
            view = getContext();
            announceForAccessibility(view.getString(0x7f130449, new Object[] {
                view.getString(0x7f1302f6)
            }));
            view = AnalyticsLoggerHolder.instance;
            if (view == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)view).trackEvent(getContext(), "menu_item", "day_toggle");
            timelineModeChangedListener.onModeChanged(dayHeaderView.position, getTop(), getGridHourStartOffset(), 0, 0);
        }
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        calendarProperties.unregisterListener(5, this);
        calendarProperties.unregisterListener(7, this);
        calendarProperties.unregisterListener(0, this);
        calendarProperties.unregisterListener(10, this);
        calendarProperties.unregisterListener(1, this);
        noEventsView.setOnClickListener(null);
        dayHeaderView.setOnClickListener(null);
    }

    protected void onDraw(Canvas canvas)
    {
        boolean flag1 = false;
        super.onDraw(canvas);
        boolean flag;
        if (animationStatusProvider.getGridModeRatio() > 0.0F)
        {
            setObjectToDraw(2);
            int l = resources.gridHoursTextSize / 2;
            int l1 = startOfGrid;
            Object obj = timelineSegmentGeometry;
            int i = ((GridTimelineSegmentGeometry) (obj)).gridHourCellHeight;
            int k2 = (int)(float)(((GridTimelineSegmentGeometry) (obj)).gridlineHeight + i);
            if (isRtl)
            {
                i = canvas.getWidth() - resources.gridHourStartMargin;
            } else
            {
                i = resources.gridHourStartMargin;
            }
            obj = DateTimeFormatHelper.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
            }
            obj = ((DateTimeFormatHelper)obj).getHoursStrings();
            int j2 = ((List) (obj)).size();
            l1 = l + l1 + k2;
            for (int i1 = 0; i1 < j2; i1++)
            {
                canvas.drawText((String)((List) (obj)).get(i1), i, l1, paint);
                GridTimelineSegmentGeometry gridtimelinesegmentgeometry1 = timelineSegmentGeometry;
                int l2 = gridtimelinesegmentgeometry1.gridHourCellHeight;
                l1 += (int)(float)(gridtimelinesegmentgeometry1.gridlineHeight + l2);
            }

            float f;
            float f1;
            if (isTablet)
            {
                float f2;
                float f3;
                GridTimelineSegmentGeometry gridtimelinesegmentgeometry;
                float af[];
                int j;
                int j1;
                if (isRtl)
                {
                    j = viewWidth - resources.dayHeaderWidth;
                } else
                {
                    j = resources.dayHeaderWidth;
                }
                f1 = j;
                if (isRtl)
                {
                    f = 0.0F;
                } else
                {
                    f = viewWidth;
                }
            } else
            {
                f1 = getEventLayoutStartX();
                f = getEventLayoutEndX();
            }
            gridtimelinesegmentgeometry = timelineSegmentGeometry;
            j = gridtimelinesegmentgeometry.gridHourCellHeight;
            f3 = (int)(float)(gridtimelinesegmentgeometry.gridlineHeight + j);
            f2 = startOfGrid;
            for (j = 0; j < solidLines.length;)
            {
                af = solidLines;
                j1 = j + 1;
                af[j] = f1;
                af = solidLines;
                j = j1 + 1;
                af[j1] = f2;
                af = solidLines;
                j1 = j + 1;
                af[j] = f;
                af = solidLines;
                j = j1 + 1;
                af[j1] = f2;
                f2 += f3;
            }

            setObjectToDraw(0);
            canvas.drawLines(solidLines, 0, solidLines.length, paint);
        } else
        if (animationStatusProvider.getGridModeRatio() < 1.0F)
        {
            if (dayViewConfig.shouldDrawExtraHeaders() && dayHeaderView.isFirstDayOfMonth)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if (k != 0)
            {
                setObjectToDraw(5);
                if (monthHeaderDrawable != null)
                {
                    Utils.drawRtlCompatibleDrawable(monthHeaderDrawable, canvas, isRtl);
                }
                int i2;
                if (isRtl)
                {
                    k = canvas.getWidth() - resources.monthHeaderLeftMargin;
                } else
                {
                    k = resources.monthHeaderLeftMargin;
                }
                canvas.drawText(monthHeaderText, k, getMonthLabelBottom(), paint);
            }
            if (dayViewConfig.shouldDrawYearHeader() && shouldDrawYearHeader)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if (k != 0)
            {
                drawWeekHeaderBackground(canvas);
                setObjectToDraw(3);
                if (isRtl)
                {
                    k = canvas.getWidth() - resources.chipLayoutStartX;
                } else
                {
                    k = resources.chipLayoutStartX;
                }
                k1 = getYearHeaderSize();
                i2 = resources.weekHeaderBottomMargin;
                canvas.drawText(yearHeaderText, k, k1 - i2, paint);
            }
        }
        flag = flag1;
        if (dayViewConfig.shouldDrawExtraHeaders())
        {
            flag = flag1;
            if (dayHeaderView.isFirstDayOfWeek)
            {
                flag = true;
            }
        }
        if (flag)
        {
            drawWeekHeaderBackground(canvas);
            setObjectToDraw(3);
            int k = getMonthHeaderSize();
            int k1;
            if (showMonthHeaderImages || !dayHeaderView.isFirstDayOfMonth)
            {
                k = (k + getWeekHeaderSize()) - resources.weekHeaderBottomMargin;
            }
            if (isRtl)
            {
                k1 = canvas.getWidth() - resources.weekHeaderLeftMargin;
            } else
            {
                k1 = resources.weekHeaderLeftMargin;
            }
            canvas.drawText(weekHeaderText, k1, k, paint);
        }
        if (animationStatusProvider.getGridModeRatio() > 0.0F)
        {
            drawNowLine(canvas);
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if (stickyAllDayEventsView.getParent() == this)
        {
            StickyAllDayEventsView stickyalldayeventsview = stickyAllDayEventsView;
            float f = stickyalldayeventsview.animationStatus.getGridModeRatio();
            j = stickyalldayeventsview.agendaYStart;
            l = stickyalldayeventsview.gridYStart;
            float f2 = j;
            j = (int)((float)(l - j) * f + f2);
            l = stickyalldayeventsview.agendaYEnd;
            int i1 = stickyalldayeventsview.gridYStart;
            int k1 = stickyalldayeventsview.gridHeight;
            f2 = l;
            stickyalldayeventsview.layout(i, j, k, (int)(f * (float)((i1 + k1) - l) + f2));
        }
        orderedChildIndices = null;
        Object obj = chipScheduleGridAnimationHelper;
        float f4 = animationStatusProvider.getGridModeRatio();
        float f1;
        Iterator iterator;
        if (f4 < 0.5F)
        {
            f1 = 1.0F;
        } else
        {
            f1 = ((ChipScheduleGridAnimationHelper) (obj)).scalingRatios.getScalingRatio(CalendarProperties.getIntProperty(10).intValue(), false);
        }
        iterator = ((ChipScheduleGridAnimationHelper) (obj)).chips.entrySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            obj = (java.util.Map.Entry)iterator.next();
            Chip chip = (Chip)((java.util.Map.Entry) (obj)).getKey();
            com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper.ChipRegistry chipregistry = (com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper.ChipRegistry)((java.util.Map.Entry) (obj)).getValue();
            Object obj2 = (com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper.ChipRegistry)((java.util.Map.Entry) (obj)).getValue();
            obj = ((com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper.ChipRegistry) (obj2)).scheduleCoordinates;
            obj2 = ((com.google.android.calendar.timeline.ChipScheduleGridAnimationHelper.ChipRegistry) (obj2)).gridCoordinates;
            float f3;
            if (f4 == 0.0F)
            {
                chip.layout(((Rect) (obj)).left, ((Rect) (obj)).top, ((Rect) (obj)).right, ((Rect) (obj)).bottom);
            } else
            if (f4 == 1.0F)
            {
                chip.layout(((Rect) (obj2)).left, ((Rect) (obj2)).top, ((Rect) (obj2)).right, ((Rect) (obj2)).bottom);
            } else
            {
                chip.layout((int)NumberUtils.linearInterpolate(((Rect) (obj)).left, ((Rect) (obj2)).left, f4), (int)NumberUtils.linearInterpolate(((Rect) (obj)).top, ((Rect) (obj2)).top, f4), (int)NumberUtils.linearInterpolate(((Rect) (obj)).right, ((Rect) (obj2)).right, f4), (int)NumberUtils.linearInterpolate(((Rect) (obj)).bottom, ((Rect) (obj2)).bottom, f4));
            }
            if (f4 < 0.5F)
            {
                obj = chipregistry.scheduleViewModel;
            } else
            {
                obj = chipregistry.gridViewModel;
            }
            chip.setViewModel(((ChipViewModel) (obj)));
            if ((double)f4 < 0.5D)
            {
                f3 = 1.0F - 2.0F * f4;
            } else
            {
                f3 = 2.0F * (f4 - 0.5F);
            }
            chip.setForegroundAlpha(f3);
            chip.setBackgroundImageAlpha(1.0F - f4);
            if (chipregistry.shouldScaleForegroundInGridMode)
            {
                chip.setTextIconScale(f1);
            }
        } while (true);
        if (dayViewConfig.inGridMode() && !animationStatusProvider.isAnimating() && isCreateNewEventTimeInDay())
        {
            recycleTime.timezone = Utils.getTimeZoneId(getContext());
            Object obj1 = recycleTime;
            ((Time) (obj1)).setJulianDaySafe(julianDay);
            long l1 = NewEventTimeChangedListenerHolder.INSTANCE.createNewEventTime;
            ((Time) (obj1)).writeFieldsToImpl();
            i = (int)((l1 - ((Time) (obj1)).impl.toMillis(true)) / 0x36ee80L);
            j = startOfGrid;
            obj1 = timelineSegmentGeometry;
            k = ((GridTimelineSegmentGeometry) (obj1)).gridHourCellHeight;
            i = i * (int)(float)(((GridTimelineSegmentGeometry) (obj1)).gridlineHeight + k) + j + 1;
            obj1 = createNewEventView;
            j = getEventLayoutStartX();
            k = getEventLayoutEndX();
            GridTimelineSegmentGeometry gridtimelinesegmentgeometry = timelineSegmentGeometry;
            CreateNewEventView createneweventview = createNewEventView;
            int j1;
            if (createneweventview.defaultDuration == -1L)
            {
                l1 = 60L;
            } else
            {
                l1 = createneweventview.defaultDuration;
            }
            ((CreateNewEventView) (obj1)).layout(j, i, k, (Math.max(Math.round(((float)l1 / 60F) * (float)(gridtimelinesegmentgeometry.gridHourCellHeight + gridtimelinesegmentgeometry.gridlineHeight)), Math.round((float)(gridtimelinesegmentgeometry.gridHourCellHeight * 30) / 60F)) + i) - 1);
        }
        if (dayViewConfig.shouldDrawExtraHeaders() && dayHeaderView.isFirstDayOfMonth)
        {
            j = dayHeaderView.monthHeaderSize;
        } else
        {
            j = 0;
        }
        if (dayViewConfig.shouldDrawExtraHeaders() && dayHeaderView.isFirstDayOfWeek)
        {
            TimelyDayHeaderView timelydayheaderview = dayHeaderView;
            if (!timelydayheaderview.showMonthHeaderImages && timelydayheaderview.isFirstDayOfMonth)
            {
                i = timelydayheaderview.weekHeaderPadding;
            } else
            {
                i = timelydayheaderview.weekHeaderSize;
            }
        } else
        {
            i = 0;
        }
        if (dayViewConfig.shouldDrawYearHeader() && shouldDrawYearHeader)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0)
        {
            k = resources.weekHeaderHeight;
        } else
        {
            k = 0;
        }
        l = i + j + k;
        if (isRtl)
        {
            i = viewWidth - resources.dayHeaderWidth;
        } else
        {
            i = 0;
        }
        if (isRtl)
        {
            j = viewWidth;
        } else
        {
            j = resources.dayHeaderWidth;
        }
        timelydayheaderview = dayHeaderView;
        j1 = resources.dayHeaderHeight;
        if (dayViewConfig == null)
        {
            k = 0;
        } else
        {
            k = (int)(animationStatusProvider.getGridModeRatio() * (float)resources.gridHoursMaskHeight);
        }
        timelydayheaderview.layout(i, l, j, k + (l + j1));
        updateDayHeaderViewPosition();
        if (mDataLoaded && !hasItems && forceShow && dayViewConfig.shouldDrawNoEventsView())
        {
            layoutNoEventsTodayView();
        }
        maybeUpdateMonthHeaderParallax();
        updateDayHeaderViewPosition();
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        int k1 = android.view.View.MeasureSpec.getSize(i);
        setViewWidth(k1);
        if (hasItems)
        {
            int i1 = dayViewAgendaMeasuredHeight;
            boolean flag = dayViewConfig.inGridMode();
            int j1 = getEventLayoutEndX() - getEventLayoutStartX();
            int k = allDayChips.size();
            chipGeometry.cellWidth = j1;
            i = 0;
            while (i < k) 
            {
                Chip chip = (Chip)allDayChips.get(i);
                if (flag)
                {
                    j = chipHeights.chipSingleHeight;
                } else
                {
                    j = chipHeights.getAgendaViewHeight(chip.viewModel.getChipType());
                }
                chip.measure(android.view.View.MeasureSpec.makeMeasureSpec(j1, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000));
                i++;
            }
            int l1 = timedChips.size();
            i = 0;
            while (i < l1) 
            {
                Chip chip1 = (Chip)timedChips.get(i);
                int l;
                if (flag)
                {
                    Object obj = chipGeometry;
                    j = chip1.getMaxPartitions();
                    float f;
                    TimelineItem timelineitem;
                    if (j <= 1)
                    {
                        f = 0.0F;
                    } else
                    {
                        f = (j - 1) * ((GridPartitionItemGeometry) (obj)).chipHorizontalSpacing;
                    }
                    l = (int)(((float)((GridPartitionItemGeometry) (obj)).cellWidth - f) / (float)j);
                    obj = timelineSegmentGeometry;
                    timelineitem = items.getItemForChip(chip1);
                    j = Math.max(Math.round(((float)(long)(timelineitem.getEndTime() - timelineitem.getStartTime()) / 60F) * (float)(((GridTimelineSegmentGeometry) (obj)).gridHourCellHeight + ((GridTimelineSegmentGeometry) (obj)).gridlineHeight)), Math.round((float)(((GridTimelineSegmentGeometry) (obj)).gridHourCellHeight * 30) / 60F));
                } else
                {
                    j = chipHeights.getAgendaViewHeight(chip1.viewModel.getChipType());
                    l = j1;
                }
                chip1.measure(android.view.View.MeasureSpec.makeMeasureSpec(l, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000));
                i++;
            }
            i = i1;
        } else
        {
            i = getNoItemsAgendaViewHeight();
        }
        setMeasuredDimension(k1, i + (int)(animationStatusProvider.getGridModeRatio() * (float)(dayViewGridMeasuredHeight - i)) + animationHeightAdjustement);
    }

    public final void onScrolled(int i)
    {
        maybeUpdateMonthHeaderParallax();
        if (i == 0 || Math.round(dayHeaderView.getTranslationY()) != 0)
        {
            updateDayHeaderViewPosition();
        }
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        setViewWidth(i);
        remeasure();
    }

    public void onUpdate(MonthData monthdata, int i, boolean flag)
    {
        if (i != julianDay)
        {
            return;
        } else
        {
            onUpdate(monthdata.getItems(i), monthdata.getDateInfo(i), flag);
            mDataLoaded = monthdata.isDataReady();
            return;
        }
    }

    public final void onUpdate(List list, int ai[], boolean flag)
    {
        boolean flag1;
        byte byte0;
        boolean flag3;
        byte0 = -1;
        flag1 = true;
        flag3 = false;
        Trace.beginSection("onUpdate");
        Object obj;
        int i;
        int j;
        long l;
        boolean flag2;
        boolean flag4;
        if (!julianDayHasChanged && mDataLoaded)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        julianDayHasChanged = false;
        dateInfo = ai;
        obj = currentTime;
        if (Clock.mockedTimestamp <= 0L) goto _L2; else goto _L1
_L1:
        l = Clock.mockedTimestamp;
_L7:
        ((Time) (obj)).set(l);
        dayHeaderView.setAlpha(1.0F);
        dayHeaderView.setPosition(julianDay - 0x253d8c);
        dayHeaderView.shouldDrawMonthText = dayViewConfig.shouldDrawMonthInDayHeader();
        dayHeaderView.setDateInfo(ai, dayViewConfig.inListView());
        if (dayHeaderView.isToday || flag)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag2 == forceShow)
        {
            break MISSING_BLOCK_LABEL_159;
        }
        forceShow = flag2;
        i = 1;
        obj = currentTime;
        ((Time) (obj)).writeFieldsToImpl();
        if (getNowLineEventIndex(((Time) (obj)).impl.toMillis(false)) != nowlineEventIndex)
        {
            i = 1;
        }
        if (!dayHeaderView.isToday)
        {
            break MISSING_BLOCK_LABEL_903;
        }
        j = getNowLineYBusy();
_L25:
        if (j != nowLineYGrid)
        {
            nowLineYGrid = j;
            invalidate();
        }
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_250;
        }
        if (!items.arePersistentItemsIdentical(list))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!dayHeaderView.isFirstDayOfMonth) goto _L4; else goto _L3
_L3:
        obj = DateTimeFormatHelper.instance;
        if (obj != null) goto _L6; else goto _L5
_L5:
        throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        list;
        Trace.endSection();
        throw list;
_L2:
        l = System.currentTimeMillis();
          goto _L7
_L6:
        monthHeaderText = ((DateTimeFormatHelper)obj).getMonthText(ai);
_L4:
        if (instantiateMonthHeaderDrawable())
        {
            initializeMonthHeader(ai);
        }
        if (!dayHeaderView.isFirstDayOfWeek) goto _L9; else goto _L8
_L8:
        obj = DateTimeFormatHelper.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_363;
        }
        throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        obj = (DateTimeFormatHelper)obj;
        if (CalendarProperties.getBooleanProperty(7).booleanValue()) goto _L11; else goto _L10
_L10:
        j = byte0;
_L16:
        weekHeaderText = ((DateTimeFormatHelper) (obj)).getWeekRangeText(ai, false, j);
_L9:
        if (dayViewConfig.shouldDrawYearHeader() && shouldDrawYearHeader)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L13; else goto _L12
_L12:
        obj = DateTimeFormatHelper.instance;
        if (obj != null) goto _L15; else goto _L14
_L14:
        throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
_L11:
        j = com.android.datetimepicker.Utils.getWeekNumberInYear(julianDay, Utils.getFirstDayOfWeekAsTime(getContext()));
          goto _L16
_L15:
        yearHeaderText = ((DateTimeFormatHelper)obj).getYearText(ai[0]);
_L13:
        if (i == 0)
        {
            Trace.endSection();
            return;
        }
        clearChips();
        hasItems = false;
        if (list == null)
        {
            break MISSING_BLOCK_LABEL_551;
        }
        baseDate.setJulianDaySafe(julianDay);
        hasItems = loadTimelineItems(list);
        dayHeaderView.initializeText();
        list = dayHeaderView;
        if (list.getParent() != this)
        {
            addView(list, 0);
        }
        if (hasItems)
        {
            break MISSING_BLOCK_LABEL_630;
        }
        removeAllViews();
        if (dayHeaderView.isToday || flag)
        {
            break MISSING_BLOCK_LABEL_590;
        }
        if (animationStatusProvider.getGridModeRatio() <= 0.0F)
        {
            break MISSING_BLOCK_LABEL_597;
        }
        dayHeaderView.initializeText();
        if (!dayHeaderView.isToday && !flag)
        {
            break MISSING_BLOCK_LABEL_630;
        }
        list = dayHeaderView;
        if (list.getParent() != this)
        {
            addView(list, 0);
        }
        flag2 = hasItems;
        flag4 = dayHeaderView.isToday;
        list = dayViewConfig;
        if (flag2 || !flag4 && !flag) goto _L18; else goto _L17
_L17:
        if (!list.inListView() || !list.shouldDrawNoEventsView()) goto _L18; else goto _L19
_L19:
        list = noEventsView;
        if (list.getParent() != this)
        {
            addView(list, 0);
        }
        noEventsView.setText(0x7f130350);
        noEventsView.setAlpha(1.0F);
_L22:
        if (!isCreateNewEventTimeInDay()) goto _L21; else goto _L20
_L20:
        installCreateEventView();
_L23:
        list = getLayoutParams();
        if (list instanceof android.view.ViewGroup.MarginLayoutParams)
        {
            list = (android.view.ViewGroup.MarginLayoutParams)list;
            list.bottomMargin = 0;
            setLayoutParams(list);
        }
        animationHeightAdjustement = 0;
        remeasure();
        requestLayout();
        invalidateChips();
        flag = flag3;
        if (!dayViewConfig.shouldDrawExtraHeaders())
        {
            break MISSING_BLOCK_LABEL_804;
        }
        flag = flag3;
        if (dayHeaderView.isFirstDayOfWeek)
        {
            flag = true;
        }
        setFocusable(flag);
        setFocusableInTouchMode(flag);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_868;
        }
        list = weekHeaderText;
_L24:
        setContentDescription(list);
        if (flag)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = 2;
        }
        setImportantForAccessibility(i);
        Trace.endSection();
        return;
_L18:
        removeView(noEventsView);
          goto _L22
_L21:
        removeView(createNewEventView);
          goto _L23
        list = null;
          goto _L24
        j = -1;
          goto _L25
    }

    public final void performDelayedAction(DelayedActionDescription delayedactiondescription)
    {
        delayedactiondescription = delayedactiondescription.timelineItem;
        delayedactiondescription = (com.google.android.calendar.timely.timeline.TimelineItemCollection.Entry)items.persistentEntries.get(delayedactiondescription);
        if (delayedactiondescription == null)
        {
            delayedactiondescription = null;
        } else
        {
            delayedactiondescription = ((com.google.android.calendar.timely.timeline.TimelineItemCollection.Entry) (delayedactiondescription)).chip;
        }
        if (delayedactiondescription == null)
        {
            LogUtils.e(TAG, "Failing to perform delayed action due to chip not found", new Object[0]);
        } else
        {
            TimelyDayViewSwipeHelper timelydayviewswipehelper = swipe;
            if (timelydayviewswipehelper._flddelegate != null)
            {
                InteractionTracker.getInstance().trackInteractionStart(timelydayviewswipehelper, delayedactiondescription);
                com.google.android.calendar.timely.interaction.InteractionTracker.EndInteractionCountdown endinteractioncountdown = new com.google.android.calendar.timely.interaction.InteractionTracker.EndInteractionCountdown(timelydayviewswipehelper, delayedactiondescription, 1);
                TimelineItem timelineitem = timelydayviewswipehelper.dayView.items.getItemForChip(delayedactiondescription);
                AnimationUtils.animateThenRun(ChipAnimations.dismiss(delayedactiondescription, null, 1), new TimelyDayViewSwipeHelper._cls1(timelydayviewswipehelper, timelineitem, 1, delayedactiondescription, endinteractioncountdown));
                return;
            }
        }
    }

    public final void recycleDayView()
    {
        clearChips();
        if (monthHeaderDrawable != null)
        {
            monthHeaderDrawable.unbind();
            monthHeaderDrawable = null;
        }
        shouldDrawYearHeader = false;
        dayHeaderView.setTranslationY(0.0F);
    }

    public void setJulianDay(int i)
    {
        if (julianDay != i)
        {
            julianDay = i;
            julianDayHasChanged = true;
        }
    }

    public final boolean shouldDelayAction(DelayedActionDescription delayedactiondescription)
    {
        delayedactiondescription = delayedactiondescription.timelineItem;
        delayedactiondescription = (com.google.android.calendar.timely.timeline.TimelineItemCollection.Entry)items.persistentEntries.get(delayedactiondescription);
        if (delayedactiondescription == null)
        {
            delayedactiondescription = null;
        } else
        {
            delayedactiondescription = ((com.google.android.calendar.timely.timeline.TimelineItemCollection.Entry) (delayedactiondescription)).chip;
        }
        if (delayedactiondescription != null)
        {
            return (((Chip) (delayedactiondescription)).viewModel.getSupportedSwipeDirections() & 1) != 0;
        } else
        {
            return false;
        }
    }

    final boolean shouldDrawNowLine()
    {
        boolean flag;
        if (!dayViewConfig.shouldNeverDrawNowLine() && dayHeaderView.isToday)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag && hasItems;
    }

    final void updateDayHeaderViewPosition()
    {
        float f = Math.max(0.0F, -getY() - (float)dayHeaderView.getTop());
        float f1 = Math.max(0.0F, ((float)dayHeaderView.getBottom() + f) - (float)getHeight());
        dayHeaderView.setTranslationY(Math.max(0.0F, f - f1));
    }

    public final Animator updateItemAnimated(TimelineItem timelineitem, TimelineItem timelineitem1)
    {
        TimelineItemCollection timelineitemcollection = items;
        Object obj = (com.google.android.calendar.timely.timeline.TimelineItemCollection.Entry)timelineitemcollection.persistentEntries.get(timelineitem);
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((com.google.android.calendar.timely.timeline.TimelineItemCollection.Entry) (obj)).chip;
        }
        if (obj != null)
        {
            if (timelineitemcollection.animatorProvider == null)
            {
                if (timelineitemcollection.entries.remove(timelineitem) == null);
                timelineitemcollection.include(timelineitem1, ((Chip) (obj)));
            } else
            {
                Animator animator = timelineitemcollection.animatorProvider.createBeforeUpdateItemAnimator(((Chip) (obj)), timelineitem, timelineitem1);
                boolean flag;
                boolean flag1;
                if (timelineitemcollection.entries.remove(timelineitem) != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                flag1 = timelineitemcollection.include(timelineitem1, ((Chip) (obj)));
                if (flag && flag1)
                {
                    return animator;
                }
            }
        }
        return null;
    }

    public boolean verifyDrawable(Drawable drawable)
    {
        return super.verifyDrawable(drawable) || drawable == monthHeaderDrawable;
    }

    static 
    {
        new StringBuilder(50);
    }

    private class _cls3
        implements android.view.View.OnClickListener
    {

        private final TimelyDayView this$0;

        public final void onClick(View view)
        {
            for (view = view.getContext(); !(view instanceof OnLaunchEdit) && (view instanceof ContextWrapper); view = ((ContextWrapper)view).getBaseContext()) { }
            if (view instanceof OnLaunchEdit)
            {
                Bundle bundle = Utils.getExtraEventBundle(EditHelper.constructDefaultStartTime(EditHelper.constructDefaultStartTimeWithoutCorrection(dayHeaderView.displayedTime, view)), null, false);
                bundle.putString("createEditSource", "grid");
                ((OnLaunchEdit)view).onLaunchInsertOrEdit(bundle);
            }
        }

        _cls3()
        {
            this$0 = TimelyDayView.this;
            super();
        }
    }

}
