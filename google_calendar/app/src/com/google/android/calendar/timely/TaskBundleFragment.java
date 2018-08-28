// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import com.google.android.calendar.AlternateTimelineUtils;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.event.DelayedActionDescription;
import com.google.android.calendar.event.DelayedActionPerformer;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.android.calendar.timely.animations.TaskBundleAnimation;
import com.google.android.calendar.timely.gridviews.ChipRecyclerManager;
import com.google.android.calendar.timely.gridviews.WeekHeaderLabelsView;
import com.google.android.calendar.timely.interaction.SwipeInteractionController;
import com.google.android.calendar.timely.interaction.helper.DelayedUpdateHelper;
import com.google.common.base.Optional;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineTaskBundle, TimelyDayView, TimelyDayViewSwipeHelper, DataFactory, 
//            MonthData, TimelineItem, TimelineTask, DayViewConfig

public class TaskBundleFragment extends OverlayFragment
    implements DelayedActionPerformer
{

    private static final DayViewConfig.Builder DAYVIEWCONFIG_BUILDER;
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/TaskBundleFragment);
    private boolean animateAfterLayout;
    public TaskBundleAnimation animationView;
    public Provider contentStore;
    public DayPopUpFragment.DayView dayView;
    private DayViewConfig dayViewConfig;
    public int julianDay;
    private ViewGroup overlayView;
    private Subscription subscription;

    public TaskBundleFragment()
    {
    }

    public static Bundle createArguments(TimelineTaskBundle timelinetaskbundle, EventInfoAnimationData eventinfoanimationdata)
    {
        Bundle bundle = new Bundle(2);
        bundle.putParcelable("task_bundle", timelinetaskbundle);
        if (eventinfoanimationdata != null)
        {
            bundle.putParcelable("animation_data", eventinfoanimationdata);
        }
        return bundle;
    }

    final void doDismissScreen()
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (obj instanceof com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayListener)
        {
            obj = (com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayListener)obj;
            boolean flag = animationView.canAnimate();
            if (flag)
            {
                TaskBundleAnimation taskbundleanimation = animationView;
                if (taskbundleanimation.animSet != null && taskbundleanimation.animSet.isRunning())
                {
                    taskbundleanimation.animSet.end();
                }
                taskbundleanimation.animSet = new AnimatorSet();
                Object obj1 = taskbundleanimation.contentView;
                ObjectAnimator objectanimator = ObjectAnimator.ofFloat(taskbundleanimation.contentView, "alpha", new float[] {
                    1.0F, 0.0F
                }).setDuration(150L);
                objectanimator.addListener(new com.google.android.calendar.utils.animation.AnimationUtils._cls4(((View) (obj1)), ((View) (obj1)).getLayerType()));
                obj1 = taskbundleanimation.animSet.play(objectanimator);
                if (taskbundleanimation.shouldDrawScrim)
                {
                    taskbundleanimation.bgOverlayAlpha = (int)(1.0F * 255F);
                    taskbundleanimation.invalidate();
                    ((android.animation.AnimatorSet.Builder) (obj1)).with(ObjectAnimator.ofFloat(taskbundleanimation, "overlayAlpha", new float[] {
                        1.0F, 0.0F
                    }).setDuration(150L));
                }
                taskbundleanimation.animSet.setInterpolator(new LinearInterpolator());
                taskbundleanimation.animSet.start();
            }
            ((com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayListener) (obj)).dismissOverlay(this, flag);
            return;
        } else
        {
            dismissAllowingStateLoss();
            return;
        }
    }

    protected final int getDialogTheme()
    {
        return 0x7f140104;
    }

    protected final View getOverlayView()
    {
        return overlayView;
    }

    public final boolean isFullScreen(Resources resources)
    {
        return !super.isTabletConfig;
    }

    public final void onAttach(Activity activity)
    {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
        activity = DAYVIEWCONFIG_BUILDER;
        boolean flag;
        if (!super.isTabletConfig)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        activity.shouldDrawDayHeader = flag;
        dayViewConfig = activity.build();
    }

    protected final void onBackgroundChanged(com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground overlaybackground)
    {
        View view;
label0:
        {
            if (super.isTabletConfig)
            {
                view = super.mView;
                if (view != null)
                {
                    view = view.findViewById(0x7f10012b);
                    if (view != null)
                    {
                        if (overlaybackground != getShortBackground())
                        {
                            break label0;
                        }
                        view.setBackgroundResource(0x7f0d0313);
                    }
                }
            }
            return;
        }
        view.setBackgroundResource(0x7f0d02f3);
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        return new _cls2(bundle);
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Object obj;
        int i;
        julianDay = ((TimelineTaskBundle)getArguments().getParcelable("task_bundle")).timeRange.getStartDay();
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        obj = ChipRecyclerManager.getOrCreateRecycler(bundle);
        if (super.isTabletConfig)
        {
            if (super.mHost == null)
            {
                bundle = null;
            } else
            {
                bundle = (FragmentActivity)super.mHost.mActivity;
            }
            bundle = new DayPopUpFragment.NoDayHeaderDayView(bundle, ((com.google.android.calendar.utils.recycler.Recycler) (obj)), dayViewConfig, this);
        } else
        {
            if (super.mHost == null)
            {
                bundle = null;
            } else
            {
                bundle = (FragmentActivity)super.mHost.mActivity;
            }
            bundle = new DayPopUpFragment.DayView(bundle, ((com.google.android.calendar.utils.recycler.Recycler) (obj)), dayViewConfig, this);
        }
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = SwipeInteractionController.getInstance(((Activity) (obj)));
        ((TimelyDayView) (bundle)).swipe._flddelegate = ((TimelyDayView.SwipeGestureDelegate) (obj));
        dayView = bundle;
        bundle = new DelayedUpdateHelper(dayView);
        obj = (TimelineTaskBundle)getArguments().getParcelable("task_bundle");
        if (!super.isTabletConfig) goto _L2; else goto _L1
_L1:
        i = julianDay;
        viewgroup = layoutinflater.inflate(0x7f05002b, viewgroup, false);
        if (viewgroup != null) goto _L4; else goto _L3
_L3:
        layoutinflater = null;
_L6:
        if (layoutinflater == null)
        {
            return null;
        }
        break; /* Loop/switch isn't completed */
_L4:
        View view = viewgroup.findViewById(0x7f10012a);
        layoutinflater = viewgroup;
        if (view instanceof WeekHeaderLabelsView)
        {
            ((WeekHeaderLabelsView)view).setFirstJulianDay(i);
            layoutinflater = viewgroup;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        viewgroup = layoutinflater.inflate(0x7f05002c, viewgroup, false);
        if (viewgroup == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (ViewGroup)viewgroup.findViewById(0x7f10012a);
            TextView textview = (TextView)layoutinflater.findViewById(0x7f10012d);
            View view1 = layoutinflater.findViewById(0x7f10012e);
            textview.setText(((TimelineTaskBundle) (obj)).singleLineTitle);
            class .Lambda._cls5
                implements android.view.View.OnClickListener
            {

                private final TaskBundleFragment arg$1;

                public final void onClick(View view2)
                {
                    arg$1.doDismissScreen();
                }

            .Lambda._cls5()
            {
                arg$1 = TaskBundleFragment.this;
            }
            }

            if (Material.robotoMedium != null)
            {
                layoutinflater = Material.robotoMedium;
            } else
            {
                layoutinflater = Typeface.create("sans-serif-medium", 0);
                Material.robotoMedium = layoutinflater;
            }
            textview.setTypeface(layoutinflater);
            view1.setOnClickListener(new .Lambda._cls5());
            layoutinflater = viewgroup;
        }
        if (true) goto _L6; else goto _L5
_L5:
        viewgroup = (ScrollView)layoutinflater.findViewById(0x7f10012c);
        if (viewgroup != null)
        {
            viewgroup.addView(dayView);
            class .Lambda._cls0
                implements android.view.ViewTreeObserver.OnScrollChangedListener
            {

                private final TaskBundleFragment arg$1;

                public final void onScrollChanged()
                {
                    DayPopUpFragment.DayView dayview = arg$1.dayView;
                    dayview.maybeUpdateMonthHeaderParallax();
                    dayview.updateDayHeaderViewPosition();
                }

            .Lambda._cls0()
            {
                arg$1 = TaskBundleFragment.this;
            }
            }

            viewgroup.getViewTreeObserver().addOnScrollChangedListener(new .Lambda._cls0());
        }
        dayView.setJulianDay(julianDay);
        dayView.onUpdate(((TimelineTaskBundle) (obj)).timelineTaskList, julianDay, super.isTabletConfig);
        viewgroup = (DataFactory)super.mFragmentManager.findFragmentByTag("data_factory");
        if (AlternateTimelineUtils.isDataFactoryEnabled(getContext()))
        {
            viewgroup = viewgroup.getData(julianDay, false);
            class .Lambda._cls1
                implements Consumer
            {

                private final TaskBundleFragment arg$1;

                public final void accept(Object obj1)
                {
                    TaskBundleFragment taskbundlefragment = arg$1;
                    obj1 = (MonthData)obj1;
                    taskbundlefragment.updateBundle(((MonthData) (obj1)).getItems(taskbundlefragment.julianDay), ((MonthData) (obj1)).allTasksReady);
                }

            .Lambda._cls1()
            {
                arg$1 = TaskBundleFragment.this;
            }
            }

            .Lambda._cls1 _lcls1 = new .Lambda._cls1();
            bundle.getClass();
            class .Lambda._cls2
                implements Executor
            {

                private final DelayedUpdateHelper arg$1;

                public final void execute(Runnable runnable)
                {
                    arg$1.postUpdate(runnable);
                }

            .Lambda._cls2(DelayedUpdateHelper delayedupdatehelper)
            {
                arg$1 = delayedupdatehelper;
            }
            }

            bundle = new .Lambda._cls2(bundle);
            subscription = ((MonthData) (viewgroup)).subscriptions.subscribeFunction(new com.google.android.apps.calendar.util.concurrent.SubscriptionManager..Lambda._cls0(_lcls1), bundle);
        } else
        {
            viewgroup = (CalendarContentStore)((Optional)contentStore.get()).get();
            class .Lambda._cls3
                implements Consumer
            {

                private final TaskBundleFragment arg$1;

                public final void accept(Object obj1)
                {
                    TaskBundleFragment taskbundlefragment;
                    taskbundlefragment = arg$1;
                    obj1 = ((Collection)obj1).iterator();
_L2:
                    ImmutableList immutablelist;
                    int j;
                    int k;
                    if (!((Iterator) (obj1)).hasNext())
                    {
                        break MISSING_BLOCK_LABEL_219;
                    }
                    immutablelist = (ImmutableList)((CalendarWeek)((Iterator) (obj1)).next()).getDays();
                    k = immutablelist.size();
                    j = 0;
                    UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
_L4:
                    if (j >= k) goto _L2; else goto _L1
_L1:
                    Object obj2;
                    obj2 = immutablelist.get(j);
                    j++;
                    obj2 = (CalendarDay)obj2;
                    if (((CalendarDay) (obj2)).getJulianDate() != taskbundlefragment.julianDay) goto _L4; else goto _L3
_L3:
                    obj2 = ((CalendarDay) (obj2)).getItems();
                    obj1 = new TimeBoxToTimelineAdapter(taskbundlefragment.getContext());
                    ArrayList arraylist = new ArrayList();
                    obj2 = (UnmodifiableIterator)((ImmutableCollection) (obj2)).iterator();
                    do
                    {
                        if (!((Iterator) (obj2)).hasNext())
                        {
                            break;
                        }
                        VersionedItem versioneditem = (VersionedItem)((Iterator) (obj2)).next();
                        if ((((TimeRangeEntry)versioneditem.getItem()).getValue() instanceof TaskItem) || (((TimeRangeEntry)versioneditem.getItem()).getValue() instanceof TaskSet))
                        {
                            arraylist.add(((TimeBoxToTimelineAdapter) (obj1)).createTimelineItem((TimeRangeEntry)versioneditem.getItem()));
                        }
                    } while (true);
                    taskbundlefragment.updateBundle(ImmutableList.copyOf(arraylist), true);
                }

            .Lambda._cls3()
            {
                arg$1 = TaskBundleFragment.this;
            }
            }

            .Lambda._cls3 _lcls3 = new .Lambda._cls3();
            bundle.getClass();
            class .Lambda._cls4
                implements Executor
            {

                private final DelayedUpdateHelper arg$1;

                public final void execute(Runnable runnable)
                {
                    arg$1.postUpdate(runnable);
                }

            .Lambda._cls4(DelayedUpdateHelper delayedupdatehelper)
            {
                arg$1 = delayedupdatehelper;
            }
            }

            subscription = viewgroup.subscribe(_lcls3, new .Lambda._cls4(bundle));
        }
        return layoutinflater;
    }

    public final void onDestroyView()
    {
        super.onDestroyView();
        subscription.cancel(false);
    }

    public final void onDialogBackPressed()
    {
        doDismissScreen();
    }

    protected final void onFinalLayoutFinished()
    {
        if (animateAfterLayout)
        {
            boolean flag;
            if (super.mHost != null && super.mAdded)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                animateAfterLayout = false;
                animationView.startOpenAnimation();
            }
        }
    }

    public final void onTouchOutside()
    {
        doDismissScreen();
    }

    public final void onViewCreated(final View view, Bundle bundle)
    {
label0:
        {
label1:
            {
                super.onViewCreated(view, bundle);
                overlayView = (ViewGroup)view.findViewById(0x7f100129);
                animationView = (TaskBundleAnimation)view.findViewById(0x7f100128);
                boolean flag;
                if (bundle != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    bundle = null;
                } else
                if (!getArguments().containsKey("animation_data"))
                {
                    bundle = null;
                } else
                {
                    EventInfoAnimationData eventinfoanimationdata = (EventInfoAnimationData)getArguments().getParcelable("animation_data");
                    if (eventinfoanimationdata == null)
                    {
                        bundle = null;
                    } else
                    {
                        bundle = eventinfoanimationdata;
                        if (requireContext().getResources().getConfiguration().orientation != eventinfoanimationdata.orientation)
                        {
                            bundle = null;
                        }
                    }
                }
                animateAfterLayout = false;
                if (bundle != null)
                {
                    if (super.isTabletConfig)
                    {
                        break label0;
                    }
                    bundle = animationView;
                    bundle.contentView = overlayView;
                    bundle.shouldDrawScrim = true;
                    bundle = view.getViewTreeObserver();
                    if (bundle != null)
                    {
                        break label1;
                    }
                    LogUtils.w(TAG, "Unable to start animation after layout", new Object[0]);
                }
                return;
            }
            bundle.addOnGlobalLayoutListener(new _cls1());
            return;
        }
        animateAfterLayout = true;
        view = animationView;
        view.contentView = overlayView;
        view.shouldDrawScrim = false;
    }

    public final void performDelayedAction(DelayedActionDescription delayedactiondescription)
    {
        if (dayView != null)
        {
            dayView.performDelayedAction(delayedactiondescription);
            return;
        } else
        {
            LogUtils.wtf(TAG, "Failing to perform delayed action due to view not initialized", new Object[0]);
            return;
        }
    }

    public final boolean shouldDelayAction(DelayedActionDescription delayedactiondescription)
    {
        return dayView != null && dayView.shouldDelayAction(delayedactiondescription);
    }

    final void updateBundle(List list, boolean flag)
    {
        boolean flag1;
        if (super.mHost != null && super.mAdded)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (list != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (flag)
        {
            doDismissScreen();
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        TimelineTaskBundle timelinetaskbundle;
        String s;
        timelinetaskbundle = (TimelineTaskBundle)getArguments().getParcelable("task_bundle");
        s = (String)timelinetaskbundle.id;
        list = list.iterator();
_L7:
        if (!list.hasNext()) goto _L5; else goto _L4
_L4:
        TimelineItem timelineitem;
        timelineitem = (TimelineItem)list.next();
        if (!(timelineitem instanceof TimelineTaskBundle))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!s.equals(timelineitem.getId())) goto _L7; else goto _L6
_L6:
        list = (TimelineTaskBundle)timelineitem;
        if (timelinetaskbundle.equals(list)) goto _L1; else goto _L8
_L8:
        if (list == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        list.updateTitles(getContext());
        getArguments().putParcelable("task_bundle", list);
        dayView.setJulianDay(julianDay);
        dayView.onUpdate(((TimelineTaskBundle) (list)).timelineTaskList, julianDay, false);
        if (super.isTabletConfig || super.mView == null) goto _L1; else goto _L9
_L9:
        ((TextView)super.mView.findViewById(0x7f10012a).findViewById(0x7f10012d)).setText(((TimelineTaskBundle) (list)).singleLineTitle);
        return;
        if (!(timelineitem instanceof TimelineTask) || !timelinetaskbundle.contains((TimelineTask)timelineitem)) goto _L7; else goto _L10
_L10:
        TimelineTask timelinetask = (TimelineTask)timelineitem;
        if (timelinetaskbundle.done)
        {
            if (!timelinetask.isDone)
            {
                break MISSING_BLOCK_LABEL_381;
            }
            flag1 = true;
        } else
        if (timelinetaskbundle.timeRange.isAllDay())
        {
            if (!timelinetask.timeRange.isAllDay() || timelinetask.isDone)
            {
                break MISSING_BLOCK_LABEL_381;
            }
            flag1 = true;
        } else
        {
            if (timelinetask.timeRange.isAllDay() || timelinetask.timeRange.getUtcStartMillis() != timelinetaskbundle.timeRange.getUtcStartMillis())
            {
                break MISSING_BLOCK_LABEL_381;
            }
            flag1 = true;
        }
_L13:
        if (!flag1) goto _L7; else goto _L11
_L11:
        list = (TimelineTask)timelineitem;
        timelinetaskbundle.timelineTaskList.clear();
        timelinetaskbundle.updateFinished = false;
        timelinetaskbundle.addTimelineTask(list);
        list = timelinetaskbundle;
          goto _L8
        if (!flag) goto _L1; else goto _L12
_L12:
        doDismissScreen();
        return;
_L5:
        list = null;
          goto _L8
        flag1 = false;
          goto _L13
    }

    static 
    {
        DayViewConfig.Builder builder = new DayViewConfig.Builder();
        builder.inGridMode = Boolean.valueOf(false);
        builder.supportsSwipe = true;
        builder.isChipClickable = true;
        builder.canDrawBackgroundImage = true;
        builder.neverDrawNowLine = true;
        DAYVIEWCONFIG_BUILDER = builder;
    }

    private class _cls2 extends com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayDialog
    {

        private final TaskBundleFragment this$0;

        public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            accessibilityevent.setContentDescription(TimelineItemUtil.createContentDescription(getContext(), (TimelineTaskBundle)getArguments().getParcelable("task_bundle"), false, ((TimelineTaskBundle)getArguments().getParcelable("task_bundle")).title));
            return super.dispatchPopulateAccessibilityEvent(accessibilityevent);
        }

        _cls2(Context context)
        {
            this$0 = TaskBundleFragment.this;
            super(TaskBundleFragment.this, context);
        }
    }


    private class _cls1
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        private final TaskBundleFragment this$0;
        private final View val$view;

        public final void onGlobalLayout()
        {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            animationView.startOpenAnimation();
        }

        _cls1()
        {
            this$0 = TaskBundleFragment.this;
            view = view1;
            super();
        }
    }

}
