// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import com.google.android.calendar.CreateFabFragment;
import com.google.android.calendar.Utils;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.event.DelayedActionDescription;
import com.google.android.calendar.event.DelayedActionPerformer;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.gridviews.ChipRecyclerManager;
import com.google.android.calendar.timely.gridviews.WeekHeaderLabelsView;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayView, TimelyDayHeaderView, DataFactory, MonthData, 
//            DayViewConfig

public final class DayPopUpFragment extends CreateFabFragment
    implements com.google.android.calendar.CreateFabFragment.CreateFabStack.StartDay, DelayedActionPerformer
{

    private static final DayViewConfig DAY_VIEW_CONFIG;
    private MonthData dayData;
    private DayView dayView;
    private int julianDay;

    public DayPopUpFragment()
    {
    }

    public static Bundle createArguments(int i, int j, int k)
    {
        boolean flag1 = false;
        boolean flag;
        if (i >= 1970)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (j >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        flag = flag1;
        if (k >= 0)
        {
            flag = true;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            Bundle bundle = new Bundle(1);
            bundle.putInt("julian_day_item", Utils.getJulianDay(i, j, k));
            return bundle;
        }
    }

    public final Time getCreateStartDay()
    {
        return ((TimelyDayView) (dayView)).dayHeaderView.displayedTime;
    }

    protected final int getDialogTheme()
    {
        return 0x7f140104;
    }

    protected final com.google.android.calendar.CreateFabFragment.CreateFabStack.StartDay getStartDayInterface()
    {
        return this;
    }

    protected final void onBackgroundChanged(com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground overlaybackground)
    {
        View view;
label0:
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
        return new _cls1(bundle);
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        layoutinflater = super.interfaceActivity.getLayoutInflater();
        bundle = (ViewGroup)layoutinflater.inflate(0x7f050032, viewgroup, false);
        super.overlayView = layoutinflater.inflate(0x7f05003f, bundle, false);
        if (super.overlayView != null)
        {
            bundle.addView(super.overlayView, 0);
        }
        View view = getOverlayView();
        if (view == null)
        {
            LogUtils.wtf(TAG, "getOverlayView returned null", new Object[0]);
            return null;
        }
        julianDay = getArguments().getInt("julian_day_item");
        layoutinflater = view.findViewById(0x7f10012a);
        if (layoutinflater instanceof WeekHeaderLabelsView)
        {
            ((WeekHeaderLabelsView)layoutinflater).setFirstJulianDay(julianDay);
        }
        int i;
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        if (super.mHost == null)
        {
            viewgroup = null;
        } else
        {
            viewgroup = (FragmentActivity)super.mHost.mActivity;
        }
        dayView = new NoDayHeaderDayView(layoutinflater, ChipRecyclerManager.getOrCreateRecycler(viewgroup), DAY_VIEW_CONFIG, this);
        layoutinflater = view.findViewById(0x7f10012c);
        if (layoutinflater instanceof ViewGroup)
        {
            ((ViewGroup)layoutinflater).addView(dayView);
        }
        dayData = ((DataFactory)super.mFragmentManager.findFragmentByTag("data_factory")).getData(julianDay, false);
        viewgroup = dayView;
        layoutinflater = dayData;
        i = julianDay;
        viewgroup.setJulianDay(i);
        viewgroup = ((TimelyDayView) (viewgroup)).subscriber;
        if (((TimelyDayView.Subscriber) (viewgroup)).monthData != layoutinflater)
        {
            if (((TimelyDayView.Subscriber) (viewgroup)).dataSubscription != null)
            {
                ((TimelyDayView.Subscriber) (viewgroup)).dataSubscription.cancel(false);
                viewgroup.dataSubscription = null;
            }
            viewgroup.monthData = layoutinflater;
            viewgroup.day = i;
            if (ViewCompat.isAttachedToWindow(((TimelyDayView.Subscriber) (viewgroup)).this$0))
            {
                TimelyDayView.Subscriber..Lambda._cls0 _lcls0 = new TimelyDayView.Subscriber..Lambda._cls0(viewgroup, i);
                Object obj = ((TimelyDayView.Subscriber) (viewgroup)).this$0.delayedUpdateHelper;
                obj.getClass();
                obj = new TimelyDayView.Subscriber..Lambda._cls1(((com.google.android.calendar.timely.interaction.helper.DelayedUpdateHelper) (obj)));
                viewgroup.dataSubscription = ((MonthData) (layoutinflater)).subscriptions.subscribeFunction(new com.google.android.apps.calendar.util.concurrent.SubscriptionManager..Lambda._cls0(_lcls0), ((java.util.concurrent.Executor) (obj)));
            }
        }
        dayView.onUpdate(dayData, julianDay, true);
        return bundle;
    }

    public final void onTouchOutside()
    {
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity instanceof com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayListener)
        {
            ((com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayListener)fragmentactivity).dismissOverlay(this, false);
            return;
        } else
        {
            dismissAllowingStateLoss();
            return;
        }
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
        if (dayView != null)
        {
            return dayView.shouldDelayAction(delayedactiondescription);
        } else
        {
            return false;
        }
    }

    static 
    {
        DayViewConfig.Builder builder = new DayViewConfig.Builder();
        builder.inGridMode = Boolean.valueOf(false);
        builder.shouldDrawNoEventsView = true;
        builder.canDrawBackgroundImage = true;
        builder.supportsSwipe = true;
        builder.isChipClickable = true;
        DAY_VIEW_CONFIG = builder.build();
    }

    private class _cls1 extends com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayDialog
    {

        private final DayPopUpFragment this$0;

        public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            Object obj = getOverlayView().findViewById(0x7f10012a);
            if (obj != null)
            {
                Object obj1 = DayPopUpFragment.this;
                String s = requireContext().getResources().getString(0x7f130155);
                obj1 = ((Fragment) (obj1)).requireContext().getResources().getString(0x7f130449, new Object[] {
                    s
                });
                obj = String.valueOf(((View) (obj)).getContentDescription());
                accessibilityevent.setContentDescription((new StringBuilder(String.valueOf(obj1).length() + 2 + String.valueOf(obj).length())).append(((String) (obj1))).append(", ").append(((String) (obj))).toString());
            }
            return super.dispatchPopulateAccessibilityEvent(accessibilityevent);
        }

        _cls1(Context context)
        {
            this$0 = DayPopUpFragment.this;
            super(DayPopUpFragment.this, context);
        }
    }


    private class NoDayHeaderDayView extends DayView
    {
        private class DayView extends TimelyDayView
        {

            private final WeakReference reference;

            public final void onUpdate(MonthData monthdata, int i, boolean flag)
            {
                if (i == super.julianDay)
                {
                    super.onUpdate(monthdata, i, true);
                    if (reference.get() != null)
                    {
                        DayPopUpFragment.addLayoutChangeListener((OverlayFragment)reference.get(), this);
                        return;
                    }
                }
            }

            public final void onUpdate(List list, int i, boolean flag)
            {
                if (i == super.julianDay)
                {
                    onUpdate(list, Utils.getDateInfo(i), flag);
                    mDataLoaded = true;
                    if (flag && reference.get() != null)
                    {
                        DayPopUpFragment.addLayoutChangeListener((OverlayFragment)reference.get(), this);
                        return;
                    }
                }
            }

            public DayView(Context context, Recycler recycler, DayViewConfig dayviewconfig, OverlayFragment overlayfragment)
            {
                float f;
                if (dayviewconfig.inGridMode())
                {
                    f = 1.0F;
                } else
                {
                    f = 0.0F;
                }
                super(context, recycler, dayviewconfig, new com.google.android.calendar.timely.animations.TimelineAgendaGridAnimationStatus.ImmutableTimelineAgendaGridAnimationStatus(false, f), null);
                reference = new WeakReference(overlayfragment);
            }
        }


        private final int chipMarginHorizontal;

        protected final int getBottomPadding()
        {
            if (super.hasItems)
            {
                return super.resources.defaultMargin - super.resources.chipVerticalSpacing;
            } else
            {
                return 0;
            }
        }

        public final int getEventLayoutEndX()
        {
            return super.viewWidth - chipMarginHorizontal;
        }

        public final int getEventLayoutStartX()
        {
            return chipMarginHorizontal;
        }

        protected final int getNowLineMarginStart()
        {
            return chipMarginHorizontal - (int)super.nowLineDrawable.radius;
        }

        public NoDayHeaderDayView(Context context, Recycler recycler, DayViewConfig dayviewconfig, OverlayFragment overlayfragment)
        {
            super(context, recycler, dayviewconfig, overlayfragment);
            chipMarginHorizontal = context.getResources().getDimensionPixelSize(0x7f0e00da);
        }
    }

}
