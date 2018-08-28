// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.gridviews.ChipRecyclerManager;
import com.google.android.calendar.timely.gridviews.FindTimeGridDayView;
import com.google.android.calendar.timely.gridviews.FindTimeGridViewFrame;
import com.google.android.calendar.timely.gridviews.GridDayView;
import com.google.android.calendar.timely.gridviews.GridViewFrame;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.timely.gridviews.allday.AttendeeAllDayHeaderView;
import com.google.android.calendar.timely.gridviews.allday.ExpandableChipColumnView;
import com.google.android.calendar.timely.gridviews.attendees.AttendeeInfoLayout;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridUi, FindTimeAttendee, FindTimeGridData, TimelineSuggestion, 
//            TimelineEvent, FindTimeGridViewPager, FindTimeGridSlabPage, FindTimeGridSlabItem, 
//            FindTimeUtil, PagedScrollView, PinchZoomController, CustomUserSuggestionListenerHolder

public final class FindTimeGridFragment extends Fragment
    implements CustomUserSuggestionListenerHolder.OnCreateCustomSuggestionListener, FindTimeGridUi
{

    public String accountName;
    public String accountType;
    public AttendeeAllDayHeaderView allDayEventView;
    public AllDayHeaderArrow allDayHeaderArrow;
    private FindTimeGridViewFrame attendeeFrame;
    public AttendeeInfoLayout attendeeHeaderView;
    public int bestTimesCount;
    public FindTimeGridViewPager bottomPager;
    private Recycler chipRecycler;
    public Context context;
    public TimelineSuggestion currentSuggestion;
    private PagedScrollView eventScrollView;
    private Comparator findTimeChipComparator;
    public FindTimeGridData gridData;
    private PagedScrollView hoursScrollView;
    private LayoutInflater inflater;
    public boolean isManualTimeSlot;
    public FindTimeGridUi.Listener listener;
    private LinearLayout mainContent;
    private PeekHandler peekHandler;
    private PagedScrollView.ScrollManager scrollManager;
    private FindTimeGridDayView suggestionDayView;
    public int suggestionIndex;
    public TimeZone timezone;
    private TextView titleView;

    public FindTimeGridFragment()
    {
        isManualTimeSlot = false;
    }

    static final List lambda$updateGrid$1$FindTimeGridFragment(int i, FindTimeAttendee findtimeattendee)
    {
        List list = (List)findtimeattendee.daysToEvents.get(i);
        findtimeattendee = list;
        if (list == null)
        {
            findtimeattendee = ImmutableList.of();
        }
        return findtimeattendee;
    }

    static final String lambda$updateGrid$2$FindTimeGridFragment(FindTimeAttendee findtimeattendee)
    {
        return Platform.nullToEmpty(findtimeattendee.displayName);
    }

    private final void reorderChipsTraversal()
    {
        boolean flag = false;
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < suggestionDayView.getChildCount(); i++)
        {
            View view = suggestionDayView.getChildAt(i);
            if (view instanceof Chip)
            {
                view.setId(View.generateViewId());
                FindTimeGridViewFrame findtimegridviewframe = attendeeFrame;
                arraylist.add(new FindTimeChip(findtimegridviewframe.getChildCount() - findtimegridviewframe.getChildrenBeforeGridDayViews(), (Chip)view));
            }
        }

        int j = 0;
        do
        {
            Object obj = attendeeFrame;
            if (j >= ((GridViewFrame) (obj)).getChildCount() - ((GridViewFrame) (obj)).getChildrenBeforeGridDayViews())
            {
                break;
            }
            obj = attendeeFrame;
            obj = (GridDayView)((GridViewFrame) (obj)).getChildAt(((GridViewFrame) (obj)).getChildrenBeforeGridDayViews() + j);
            for (int l = 0; l < ((GridDayView) (obj)).getChildCount(); l++)
            {
                View view1 = ((GridDayView) (obj)).getChildAt(l);
                if (view1 instanceof Chip)
                {
                    view1.setId(View.generateViewId());
                    arraylist.add(new FindTimeChip(j, (Chip)view1));
                }
            }

            j++;
        } while (true);
        Collections.sort(arraylist, findTimeChipComparator);
        for (int k = ((flag) ? 1 : 0); k < arraylist.size() - 1; k++)
        {
            int i1 = ((FindTimeChip)arraylist.get(k + 1)).chip.getId();
            ((FindTimeChip)arraylist.get(k)).chip.setAccessibilityTraversalBefore(i1);
        }

    }

    static boolean wasSlabSwiped(Context context1)
    {
        return context1.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("com.google.android.calendar.findtime.grid.was_slab_swiped", false);
    }

    public final void customizeSystemDecorations()
    {
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        StatusbarAnimatorCompat.createInstance(fragmentactivity.getWindow()).tryApplyLightStatusbar(true, requireContext().getResources().getColor(0x7f0d0214), 0xff000000, 0);
    }

    public final void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        if (bundle == null)
        {
            bundle = AnalyticsLoggerHolder.instance;
            if (bundle == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)bundle).trackEvent(context, "find_a_time", "grid_view", "opened", null);
            gridData = (FindTimeGridData)getArguments().getParcelable("grid_data");
            bestTimesCount = getArguments().getInt("best_times_count");
            suggestionIndex = gridData.index;
            currentSuggestion = (TimelineSuggestion)gridData.suggestions.get(suggestionIndex);
        } else
        {
            currentSuggestion = (TimelineSuggestion)bundle.getParcelable("current_suggestion");
            suggestionIndex = bundle.getInt("suggestion_index");
            isManualTimeSlot = bundle.getBoolean("is_manual_time");
            gridData = (FindTimeGridData)bundle.getParcelable("grid_data");
        }
        bottomPager.setAdapter(new FindTimeGridPagerAdapter());
        updateBottomPage();
        updateGrid(false);
    }

    public final void onAttach(Activity activity)
    {
        super.onAttach(activity);
        context = activity.getApplicationContext();
        inflater = LayoutInflater.from(activity);
        findTimeChipComparator = new FindTimeChipComparator();
    }

    public final void onCreateCustomUserSuggestionChanged(TimelineSuggestion timelinesuggestion)
    {
        int i;
        long l;
        long l2;
        l = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcStartMillis();
        l2 = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcEndMillis();
        i = 0;
_L7:
        if (i >= gridData.suggestions.size()) goto _L2; else goto _L1
_L1:
        TimelineSuggestion timelinesuggestion1 = (TimelineSuggestion)gridData.suggestions.get(i);
        if (((TimelineEvent) (timelinesuggestion1)).timeRange.getUtcStartMillis() != l || ((TimelineEvent) (timelinesuggestion1)).timeRange.getUtcEndMillis() != l2) goto _L4; else goto _L3
_L3:
        if (i >= 0)
        {
            updateSelectedSuggestion(i);
            updateBottomPage();
            return;
        }
        break; /* Loop/switch isn't completed */
_L4:
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        i = -1;
        if (true) goto _L3; else goto _L5
_L5:
        currentSuggestion = timelinesuggestion;
        isManualTimeSlot = true;
        Object obj = (FindTimeGridSlabPage)bottomPager.findViewWithTag(Integer.valueOf(bottomPager.getCurrentItem()));
        Object obj1 = timezone;
        ((FindTimeGridSlabPage) (obj)).itemView.timezone = ((TimeZone) (obj1));
        obj1 = FindTimeUtil.getInstance(context).getDescription(currentSuggestion, accountName, accountType);
        Object obj2 = currentSuggestion;
        String s;
        long l1;
        long l3;
        long l4;
        boolean flag;
        if (suggestionIndex == gridData.suggestions.size() - 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((FindTimeGridSlabPage) (obj)).setTimelineSuggestion(((TimelineSuggestion) (obj2)), ((String) (obj1)), flag);
        obj1 = new StringBuilder();
        obj2 = new StringBuilder();
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = FindTimeUtil.getInstance(((Context) (obj)));
        s = timezone.getID();
        l3 = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcStartMillis();
        l4 = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcEndMillis();
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        Utils.getDisplayedDateTimesInTimezone(l3, l4, l1, s, timelinesuggestion.isAllDay(), 16, ((FindTimeUtil) (obj)).context, ((StringBuilder) (obj1)), ((StringBuilder) (obj2)));
        Utils.tryAccessibilityAnnounce(super.mView, requireContext().getResources().getString(0x7f13005f, new Object[] {
            obj1, obj2
        }));
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            reorderChipsTraversal();
        }
        timelinesuggestion = AnalyticsLoggerHolder.instance;
        if (timelinesuggestion == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)timelinesuggestion).trackEvent(context, "find_a_time", "grid_view", "timeslot_created_manual", null);
        return;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final View onCreateView(LayoutInflater layoutinflater, final ViewGroup view, Bundle bundle)
    {
        view = layoutinflater.inflate(0x7f05006a, view, false);
        class .Lambda._cls0
            implements com.google.android.calendar.utils.viewpager.UserAwareViewPager.UserAwareOnPageChangeListener
        {

            private final FindTimeGridFragment arg$1;

            public final void onPageSelected(int i, boolean flag)
            {
                Object obj = arg$1;
                ((FindTimeGridFragment) (obj)).updateSelectedSuggestion(i);
                ((FindTimeGridFragment) (obj)).updateGrid(flag);
                if (((FindTimeGridFragment) (obj)).listener != null)
                {
                    ((FindTimeGridFragment) (obj)).listener.onGridSuggestionSwiped(((FindTimeGridFragment) (obj)).currentSuggestion, i);
                }
                if (flag)
                {
                    if (((Fragment) (obj)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
                    }
                    SharedPrefs.setSharedPreference(((Context) (obj)), "com.google.android.calendar.findtime.grid.was_slab_swiped", true);
                }
            }

            .Lambda._cls0()
            {
                arg$1 = FindTimeGridFragment.this;
            }
        }

        TextView textview;
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        chipRecycler = ChipRecyclerManager.getOrCreateRecycler(layoutinflater);
        timezone = TimeZone.getTimeZone(getArguments().getString("timezone"));
        accountType = getArguments().getString("account_type");
        accountName = getArguments().getString("account_name");
        bundle = (Toolbar)view.findViewById(0x7f100113);
        titleView = (TextView)bundle.findViewById(0x7f100047);
        textview = titleView;
        if (Material.robotoMedium != null)
        {
            layoutinflater = Material.robotoMedium;
        } else
        {
            layoutinflater = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = layoutinflater;
        }
        textview.setTypeface(layoutinflater);
        layoutinflater = new _cls1();
        bundle.ensureNavButtonView();
        ((Toolbar) (bundle)).mNavButtonView.setOnClickListener(layoutinflater);
        mainContent = (LinearLayout)view.findViewById(0x7f10019c);
        attendeeFrame = (FindTimeGridViewFrame)view.findViewById(0x7f100008);
        attendeeHeaderView = (AttendeeInfoLayout)view.findViewById(0x7f10000a);
        allDayEventView = (AttendeeAllDayHeaderView)view.findViewById(0x7f100005);
        allDayHeaderArrow = (AllDayHeaderArrow)view.findViewById(0x7f10019d);
        bottomPager = (FindTimeGridViewPager)view.findViewById(0x7f1001a1);
        layoutinflater = bottomPager;
        layoutinflater.setOnPageChangeListener(new com.google.android.calendar.utils.viewpager.UserAwareViewPager.OnPageChangeListenerAdapter(layoutinflater, new .Lambda._cls0()));
        allDayHeaderArrow.setOnClickListener(new _cls2());
        allDayEventView.measurementChangedListener = new _cls3();
        allDayEventView.chipRecycler = chipRecycler;
        suggestionDayView = attendeeFrame.suggestionGridDayView;
        suggestionDayView.initialize(chipRecycler, null, 1);
        suggestionDayView.accountType = accountType;
        suggestionDayView.accountName = accountName;
        scrollManager = new PagedScrollView.ScrollManager(false);
        eventScrollView = (PagedScrollView)view.findViewById(0x7f10019f);
        hoursScrollView = (PagedScrollView)view.findViewById(0x7f10019e);
        layoutinflater = scrollManager;
        bundle = eventScrollView;
        bundle.setVerticalScrollPositionFromBottom(((PagedScrollView.ScrollManager) (layoutinflater)).verticalScrollPositionFromBottom, false);
        ((PagedScrollView.ScrollManager) (layoutinflater)).scrollViews.add(bundle);
        bundle.addOnLayoutChangeListener(layoutinflater);
        bundle.scrollManager = layoutinflater;
        bundle.pinchZoomController = new PinchZoomController(bundle, layoutinflater);
        ((PagedScrollView) (bundle)).pinchZoomController.bottomPadding = 0;
        layoutinflater = scrollManager;
        bundle = hoursScrollView;
        bundle.setVerticalScrollPositionFromBottom(((PagedScrollView.ScrollManager) (layoutinflater)).verticalScrollPositionFromBottom, false);
        ((PagedScrollView.ScrollManager) (layoutinflater)).scrollViews.add(bundle);
        bundle.addOnLayoutChangeListener(layoutinflater);
        bundle.scrollManager = layoutinflater;
        bundle.pinchZoomController = new PinchZoomController(bundle, layoutinflater);
        ((PagedScrollView) (bundle)).pinchZoomController.bottomPadding = 0;
        peekHandler = new PeekHandler(context, bottomPager);
        peekHandler.sendEmptyMessageDelayed(0, 1000L);
        CustomUserSuggestionListenerHolder.INSTANCE.createCustomSuggestionChangedListeners.add(this);
        return view;
    }

    public final void onDestroyView()
    {
        super.onDestroyView();
        CustomUserSuggestionListenerHolder.INSTANCE.createCustomSuggestionChangedListeners.clear();
    }

    public final void onResume()
    {
        super.onResume();
        if (!AccessibilityUtils.isAccessibilityEnabled(context) || super.mView == null)
        {
            return;
        } else
        {
            super.mView.post(new _cls4());
            return;
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("current_suggestion", currentSuggestion);
        bundle.putInt("suggestion_index", suggestionIndex);
        bundle.putBoolean("is_manual_time", isManualTimeSlot);
        bundle.putParcelable("grid_data", gridData);
        super.onSaveInstanceState(bundle);
    }

    public final void setListener(FindTimeGridUi.Listener listener1)
    {
        listener = listener1;
    }

    final void updateBottomPage()
    {
        bottomPager.setCurrentItem(suggestionIndex, false);
        bottomPager.getAdapter().notifyDataSetChanged();
        (new Handler()).post(new _cls5());
    }

    final void updateGrid(boolean flag)
    {
        Object obj;
        int i;
        int j;
        int k;
        k = ((TimelineEvent) (currentSuggestion)).timeRange.getStartDay();
        j = requireContext().getResources().getInteger(0x7f11001e);
        obj = currentSuggestion;
        FindTimeGridDayView findtimegriddayview;
        FindTimeAttendee findtimeattendee;
        List list;
        int l;
        if (((TimelineSuggestion) (obj)).attendees == null)
        {
            i = 0;
        } else
        {
            i = ((TimelineSuggestion) (obj)).attendees.size();
            if (j < 0 || j == 0 && i > 0)
            {
                throw new IndexOutOfBoundsException();
            }
            if (i <= j)
            {
                i = 0;
            } else
            {
                i -= j - 1;
            }
        }
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        l = Utils.getTodayJulianDay(((Context) (obj)));
        if (k == l)
        {
            obj = requireContext().getResources().getString(0x7f130488);
        } else
        if (k - l == 1)
        {
            obj = requireContext().getResources().getString(0x7f130490);
        } else
        if (k - l == -1)
        {
            obj = requireContext().getResources().getString(0x7f1304c9);
        } else
        {
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = DateUtils.formatDateTime(((Context) (obj)), Utils.getMillisFromJulianDay(k), 8);
        }
        titleView.setText(((CharSequence) (obj)));
        suggestionDayView.onUpdate(currentSuggestion, k, timezone);
        obj = suggestionDayView;
        ((FindTimeGridDayView) (obj)).post(new com.google.android.calendar.timely.gridviews.FindTimeGridDayView._cls1(((FindTimeGridDayView) (obj)), flag));
        obj = currentSuggestion;
        if (((TimelineSuggestion) (obj)).attendees != null) goto _L2; else goto _L1
_L1:
        obj = null;
_L4:
        attendeeFrame.removeGridDayViews();
        for (j = 0; j < ((List) (obj)).size(); j++)
        {
            findtimegriddayview = (FindTimeGridDayView)inflater.inflate(0x7f050069, null);
            findtimegriddayview.initialize(chipRecycler, null, 1);
            findtimeattendee = (FindTimeAttendee)((List) (obj)).get(j);
            list = (List)findtimeattendee.daysToEvents.get(k);
            attendeeFrame.addView(findtimegriddayview);
            findtimegriddayview.onUpdate(list, k, currentSuggestion, findtimeattendee.displayName);
        }

        break; /* Loop/switch isn't completed */
_L2:
        ArrayList arraylist = new ArrayList(((TimelineSuggestion) (obj)).attendees);
        Collections.sort(arraylist, TimelineSuggestion.ATTENDEE_COMPARATOR);
        obj = arraylist;
        if (arraylist.size() > j)
        {
            obj = arraylist.subList(0, j - 1);
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (i > 0)
        {
            FindTimeGridDayView findtimegriddayview1 = (FindTimeGridDayView)inflater.inflate(0x7f050069, null);
            findtimegriddayview1.initialize(chipRecycler, null, 1);
            findtimegriddayview1.isMoreAttendeeColumn = true;
            attendeeFrame.addView(findtimegriddayview1);
        }
        attendeeHeaderView.onUpdate(((List) (obj)), i, false);
        class .Lambda._cls1
            implements Function
        {

            private final int arg$1;

            public final Object apply(Object obj3)
            {
                return FindTimeGridFragment.lambda$updateGrid$1$FindTimeGridFragment(arg$1, (FindTimeAttendee)obj3);
            }

            .Lambda._cls1(int i)
            {
                arg$1 = i;
            }
        }

        Object obj1;
        Object obj2;
        if (obj instanceof FluentIterable)
        {
            obj1 = (FluentIterable)obj;
        } else
        {
            obj1 = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
        }
        obj2 = new .Lambda._cls1(k);
        obj1 = (Iterable)((FluentIterable) (obj1)).iterableDelegate.or(obj1);
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        obj1 = new com.google.common.collect.Iterables._cls5(((Iterable) (obj1)), ((Function) (obj2)));
        class .Lambda._cls2
            implements Function
        {

            public static final Function $instance = new .Lambda._cls2();

            public final Object apply(Object obj3)
            {
                return FindTimeGridFragment.lambda$updateGrid$2$FindTimeGridFragment((FindTimeAttendee)obj3);
            }


            private .Lambda._cls2()
            {
            }
        }

        if (obj1 instanceof FluentIterable)
        {
            obj1 = (FluentIterable)obj1;
        } else
        {
            obj1 = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj1)), ((Iterable) (obj1)));
        }
        obj1 = ImmutableList.copyOf((Iterable)((FluentIterable) (obj1)).iterableDelegate.or(obj1));
        if (obj instanceof FluentIterable)
        {
            obj = (FluentIterable)obj;
        } else
        {
            obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
        }
        obj2 = .Lambda._cls2..instance;
        obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        obj = new com.google.common.collect.Iterables._cls5(((Iterable) (obj)), ((Function) (obj2)));
        if (obj instanceof FluentIterable)
        {
            obj = (FluentIterable)obj;
        } else
        {
            obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
        }
        obj = ImmutableList.copyOf((Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj));
        allDayEventView.clear();
        allDayEventView.onUpdate$5166KOBMC4NNAT39DGNKOQBJEGTKOQJ1EPGIUTBKD5M2UJ39EDQ3MIA994KLC___0(((List) (obj1)), ((List) (obj)), k, 0);
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            reorderChipsTraversal();
        }
        return;
    }

    final void updateMainContentMargin(FindTimeGridSlabPage findtimegridslabpage)
    {
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)mainContent.getLayoutParams();
        if (findtimegridslabpage.slabBar.getHeight() == marginlayoutparams.bottomMargin)
        {
            return;
        } else
        {
            marginlayoutparams.bottomMargin = findtimegridslabpage.slabBar.getHeight();
            mainContent.setLayoutParams(marginlayoutparams);
            return;
        }
    }

    final void updateSelectedSuggestion(int i)
    {
        suggestionIndex = i;
        currentSuggestion = (TimelineSuggestion)gridData.suggestions.get(suggestionIndex);
        isManualTimeSlot = false;
        int j = 0;
        while (j < bottomPager.getChildCount()) 
        {
            FindTimeGridSlabPage findtimegridslabpage = (FindTimeGridSlabPage)bottomPager.getChildAt(j);
            int k = ((Integer)findtimegridslabpage.getTag()).intValue();
            if (k == i)
            {
                updateMainContentMargin(findtimegridslabpage);
                if (AccessibilityUtils.isAccessibilityEnabled(findtimegridslabpage.context))
                {
                    findtimegridslabpage.itemView.sendAccessibilityEvent(8);
                }
            } else
            {
                String s = FindTimeUtil.getInstance(context).getDescription((TimelineSuggestion)gridData.suggestions.get(k), accountName, accountType);
                TimelineSuggestion timelinesuggestion = (TimelineSuggestion)gridData.suggestions.get(k);
                boolean flag;
                if (k == gridData.suggestions.size() - 1)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                findtimegridslabpage.setTimelineSuggestion(timelinesuggestion, s, flag);
            }
            j++;
        }
    }

    private class FindTimeChip
    {

        public final Chip chip;
        public final int column;

        public FindTimeChip(int i, Chip chip1)
        {
            column = i;
            chip = chip1;
        }
    }


    private class FindTimeGridPagerAdapter extends PagerAdapter
    {

        private ArrayList itemsAdded;
        private ArrayList itemsToAdd;
        private ArrayList itemsToRemove;
        private ArrayList recycledViews;
        public final FindTimeGridFragment this$0;

        public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
        {
            viewgroup = (FindTimeGridSlabPage)obj;
            recycledViews.add(viewgroup);
            itemsToRemove.add(viewgroup);
            itemsAdded.remove(viewgroup);
        }

        public final void finishUpdate(ViewGroup viewgroup)
        {
            boolean flag = false;
            ArrayList arraylist = (ArrayList)itemsToRemove;
            int k = arraylist.size();
            for (int i = 0; i < k;)
            {
                Object obj = arraylist.get(i);
                i++;
                viewgroup.removeView((View)obj);
            }

            arraylist = (ArrayList)itemsToAdd;
            k = arraylist.size();
            for (int j = ((flag) ? 1 : 0); j < k;)
            {
                Object obj1 = arraylist.get(j);
                j++;
                viewgroup.addView((View)obj1);
            }

        }

        public final int getCount()
        {
            return gridData.suggestions.size();
        }

        public final Object instantiateItem(final ViewGroup container, int i)
        {
            boolean flag = false;
            Object obj;
            TimelineSuggestion timelinesuggestion;
            if (recycledViews.size() > 0)
            {
                container = (FindTimeGridSlabPage)recycledViews.remove(0);
            } else
            {
                Object obj1 = FindTimeGridFragment.this;
                class _cls1
                    implements android.view.View.OnClickListener
                {

                    private final FindTimeGridPagerAdapter this$1;

                    public final void onClick(View view)
                    {
                        boolean flag1;
                        if (!isManualTimeSlot && suggestionIndex < bestTimesCount)
                        {
                            flag1 = true;
                        } else
                        {
                            flag1 = false;
                        }
                        listener.onGridTimeSlotSelected(currentSuggestion, flag1, isManualTimeSlot);
                    }

                _cls1()
                {
                    this$1 = FindTimeGridPagerAdapter.this;
                    super();
                }
                }

                class _cls2
                    implements FindTimeGridSlabPage.OnSlabPageUpdatedListener
                {

                    private final FindTimeGridPagerAdapter this$1;
                    private final ViewGroup val$container;

                    public final void onSlabBarHeightUpdated(FindTimeGridSlabPage findtimegridslabpage)
                    {
                        if (((FindTimeGridViewPager)container).getCurrentItem() == ((Integer)findtimegridslabpage.getTag()).intValue())
                        {
                            updateMainContentMargin(findtimegridslabpage);
                        }
                    }

                _cls2()
                {
                    this$1 = FindTimeGridPagerAdapter.this;
                    container = viewgroup;
                    super();
                }
                }

                if (((Fragment) (obj1)).mHost == null)
                {
                    obj1 = null;
                } else
                {
                    obj1 = (FragmentActivity)((Fragment) (obj1)).mHost.mActivity;
                }
                obj1 = new FindTimeGridSlabPage(((Context) (obj1)), timezone);
                ((FindTimeGridSlabPage) (obj1)).setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -1));
                ((FindTimeGridSlabPage) (obj1)).setClickable(false);
                ((FindTimeGridSlabPage) (obj1)).doneFab.setOnClickListener(new _cls1());
                obj1.listener = new _cls2();
                container = ((ViewGroup) (obj1));
            }
            obj = timezone;
            ((FindTimeGridSlabPage) (container)).itemView.timezone = ((TimeZone) (obj));
            obj = FindTimeUtil.getInstance(context).getDescription((TimelineSuggestion)gridData.suggestions.get(i), accountName, accountType);
            timelinesuggestion = (TimelineSuggestion)gridData.suggestions.get(i);
            if (i == gridData.suggestions.size() - 1)
            {
                flag = true;
            }
            container.setTimelineSuggestion(timelinesuggestion, ((String) (obj)), flag);
            container.setTag(Integer.valueOf(i));
            itemsToAdd.add(container);
            itemsAdded.add(container);
            return container;
        }

        public final boolean isViewFromObject(View view, Object obj)
        {
            return view == obj;
        }

        public final void startUpdate(ViewGroup viewgroup)
        {
            itemsToAdd.clear();
            itemsToRemove.clear();
        }

        public FindTimeGridPagerAdapter()
        {
            this$0 = FindTimeGridFragment.this;
            super();
            recycledViews = new ArrayList();
            itemsToAdd = new ArrayList();
            itemsAdded = new ArrayList();
            itemsToRemove = new ArrayList();
        }
    }


    private class FindTimeChipComparator
        implements Comparator
    {

        private final FindTimeGridFragment this$0;

        public final int compare(Object obj, Object obj1)
        {
            FindTimeChip findtimechip = (FindTimeChip)obj;
            obj1 = (FindTimeChip)obj1;
            if (findtimechip.chip.partitionInfo.getStartTime() != ((FindTimeChip) (obj1)).chip.partitionInfo.getStartTime())
            {
                return findtimechip.chip.partitionInfo.getStartTime() - ((FindTimeChip) (obj1)).chip.partitionInfo.getStartTime();
            }
            if (findtimechip.column != ((FindTimeChip) (obj1)).column)
            {
                return findtimechip.column - ((FindTimeChip) (obj1)).column;
            }
            obj = FindTimeGridFragment.this;
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            if (RtlUtils.isLayoutDirectionRtl(((Context) (obj))))
            {
                return ((FindTimeChip) (obj1)).chip.partitionInfo.getPartition() - findtimechip.chip.partitionInfo.getPartition();
            } else
            {
                return findtimechip.chip.partitionInfo.getPartition() - ((FindTimeChip) (obj1)).chip.partitionInfo.getPartition();
            }
        }

        FindTimeChipComparator()
        {
            this$0 = FindTimeGridFragment.this;
            super();
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final FindTimeGridFragment this$0;

        public final void onClick(View view)
        {
            if (listener != null)
            {
                listener.onGridCancelled();
            }
        }

        _cls1()
        {
            this$0 = FindTimeGridFragment.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final FindTimeGridFragment this$0;

        public final void onClick(View view)
        {
            int i = 1;
            boolean flag;
            if (!((ExpandableChipColumnView) (allDayEventView)).isExpanded)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            view = allDayHeaderArrow;
            if (!flag)
            {
                i = 2;
            }
            view.setState(i);
            view = allDayEventView;
            if (flag != ((ExpandableChipColumnView) (view)).isExpanded)
            {
                view.isExpanded = flag;
                view.applyExpandedOrCollapsedState();
                view.onExpandStateChanged$51D2ILG_0();
            }
        }

        _cls2()
        {
            this$0 = FindTimeGridFragment.this;
            super();
        }
    }


    private class _cls3
        implements com.google.android.calendar.timely.gridviews.allday.AttendeeAllDayHeaderView.OnMeasurementChangedListener
    {

        private final FindTimeGridFragment this$0;
        private final View val$view;

        public final void onMeasurementChanged(int i, int j)
        {
            allDayHeaderArrow.setState(j);
            android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)allDayHeaderArrow.getLayoutParams();
            layoutparams.height = attendeeHeaderView.height + i;
            if (view.getHeight() != 0)
            {
                layoutparams.height = Math.min(layoutparams.height, view.getHeight());
            }
            allDayHeaderArrow.setLayoutParams(layoutparams);
        }

        _cls3()
        {
            this$0 = FindTimeGridFragment.this;
            view = view1;
            super();
        }
    }


    private class PeekHandler extends Handler
    {

        private final WeakReference weakContext;
        private final WeakReference weakPager;

        public final void handleMessage(Message message)
        {
            byte byte0 = -1;
            message = (FindTimeGridViewPager)weakPager.get();
            Context context1 = (Context)weakContext.get();
            if (message != null && context1 != null)
            {
                if (((TransparentViewPager) (message)).dispatchOnPager)
                {
                    sendEmptyMessageDelayed(0, 1000L);
                    return;
                }
                message = new PeekPagerHelper(context1, message);
                if (!FindTimeGridFragment.wasSlabSwiped(((PeekPagerHelper) (message)).context))
                {
                    int i = ((PeekPagerHelper) (message)).pager.getAdapter().getCount();
                    if (i >= 2)
                    {
                        class PeekPagerHelper._cls1
                            implements android.animation.ValueAnimator.AnimatorUpdateListener
                        {

                            private final PeekPagerHelper this$0;

                            public final void onAnimationUpdate(ValueAnimator valueanimator1)
                            {
                                float f4;
                                if (!((ViewPager) (pager)).mFakeDragging)
                                {
                                    fakeDragged = 0.0F;
                                    FindTimeGridViewPager findtimegridviewpager = pager;
                                    if (!((ViewPager) (findtimegridviewpager)).mIsBeingDragged)
                                    {
                                        findtimegridviewpager.mFakeDragging = true;
                                        findtimegridviewpager.setScrollState(1);
                                        findtimegridviewpager.mLastMotionX = 0.0F;
                                        findtimegridviewpager.mInitialMotionX = 0.0F;
                                        MotionEvent motionevent;
                                        long l;
                                        if (((ViewPager) (findtimegridviewpager)).mVelocityTracker == null)
                                        {
                                            findtimegridviewpager.mVelocityTracker = VelocityTracker.obtain();
                                        } else
                                        {
                                            ((ViewPager) (findtimegridviewpager)).mVelocityTracker.clear();
                                        }
                                        l = SystemClock.uptimeMillis();
                                        motionevent = MotionEvent.obtain(l, l, 0, 0.0F, 0.0F, 0);
                                        ((ViewPager) (findtimegridviewpager)).mVelocityTracker.addMovement(motionevent);
                                        motionevent.recycle();
                                        findtimegridviewpager.mFakeDragBeginTime = l;
                                    }
                                }
                                f4 = ((Float)valueanimator1.getAnimatedValue()).floatValue() - fakeDragged;
                                valueanimator1 = pager;
                                if (!((ViewPager) (valueanimator1)).mFakeDragging)
                                {
                                    throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
                                }
                                if (((ViewPager) (valueanimator1)).mAdapter != null)
                                {
                                    valueanimator1.mLastMotionX = ((ViewPager) (valueanimator1)).mLastMotionX + f4;
                                    float f3 = (float)valueanimator1.getScrollX() - f4;
                                    int j = valueanimator1.getMeasuredWidth() - valueanimator1.getPaddingLeft() - valueanimator1.getPaddingRight();
                                    float f1 = j;
                                    float f6 = ((ViewPager) (valueanimator1)).mFirstOffset;
                                    float f2 = j;
                                    float f5 = ((ViewPager) (valueanimator1)).mLastOffset;
                                    Object obj = (android.support.v4.view.ViewPager.ItemInfo)((ViewPager) (valueanimator1)).mItems.get(0);
                                    android.support.v4.view.ViewPager.ItemInfo iteminfo = (android.support.v4.view.ViewPager.ItemInfo)((ViewPager) (valueanimator1)).mItems.get(((ViewPager) (valueanimator1)).mItems.size() - 1);
                                    long l1;
                                    if (((android.support.v4.view.ViewPager.ItemInfo) (obj)).position != 0)
                                    {
                                        f1 = ((android.support.v4.view.ViewPager.ItemInfo) (obj)).offset * (float)j;
                                    } else
                                    {
                                        f1 *= f6;
                                    }
                                    if (iteminfo.position != ((ViewPager) (valueanimator1)).mAdapter.getCount() - 1)
                                    {
                                        f2 = iteminfo.offset * (float)j;
                                    } else
                                    {
                                        f2 *= f5;
                                    }
                                    if (f3 >= f1)
                                    {
                                        if (f3 > f2)
                                        {
                                            f1 = f2;
                                        } else
                                        {
                                            f1 = f3;
                                        }
                                    }
                                    valueanimator1.mLastMotionX = ((ViewPager) (valueanimator1)).mLastMotionX + (f1 - (float)(int)f1);
                                    valueanimator1.scrollTo((int)f1, valueanimator1.getScrollY());
                                    valueanimator1.pageScrolled((int)f1);
                                    l1 = SystemClock.uptimeMillis();
                                    obj = MotionEvent.obtain(((ViewPager) (valueanimator1)).mFakeDragBeginTime, l1, 2, ((ViewPager) (valueanimator1)).mLastMotionX, 0.0F, 0);
                                    ((ViewPager) (valueanimator1)).mVelocityTracker.addMovement(((MotionEvent) (obj)));
                                    ((MotionEvent) (obj)).recycle();
                                }
                                valueanimator1 = PeekPagerHelper.this;
                                valueanimator1.fakeDragged = ((PeekPagerHelper) (valueanimator1)).fakeDragged + f4;
                            }

                PeekPagerHelper._cls1()
                {
                    this$0 = PeekPagerHelper.this;
                    super();
                }
                        }

                        class PeekPagerHelper._cls2 extends AnimatorListenerAdapter
                        {

                            private final PeekPagerHelper this$0;

                            public final void onAnimationEnd(Animator animator)
                            {
                                animator = pager;
                                if (!((ViewPager) (animator)).mFakeDragging)
                                {
                                    throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
                                }
                                if (((ViewPager) (animator)).mAdapter != null)
                                {
                                    Object obj = ((ViewPager) (animator)).mVelocityTracker;
                                    ((VelocityTracker) (obj)).computeCurrentVelocity(1000, ((ViewPager) (animator)).mMaximumVelocity);
                                    int j = (int)((VelocityTracker) (obj)).getXVelocity(((ViewPager) (animator)).mActivePointerId);
                                    animator.mPopulatePending = true;
                                    int k = animator.getMeasuredWidth();
                                    int l = animator.getPaddingLeft();
                                    int i1 = animator.getPaddingRight();
                                    int j1 = animator.getScrollX();
                                    obj = animator.infoForCurrentScrollPosition();
                                    animator.setCurrentItemInternal(animator.determineTargetPage(((android.support.v4.view.ViewPager.ItemInfo) (obj)).position, ((float)j1 / (float)(k - l - i1) - ((android.support.v4.view.ViewPager.ItemInfo) (obj)).offset) / ((android.support.v4.view.ViewPager.ItemInfo) (obj)).widthFactor, j, (int)(((ViewPager) (animator)).mLastMotionX - ((ViewPager) (animator)).mInitialMotionX)), true, true, j);
                                }
                                animator.mIsBeingDragged = false;
                                animator.mIsUnableToDrag = false;
                                if (((ViewPager) (animator)).mVelocityTracker != null)
                                {
                                    ((ViewPager) (animator)).mVelocityTracker.recycle();
                                    animator.mVelocityTracker = null;
                                }
                                animator.mFakeDragging = false;
                            }

                PeekPagerHelper._cls2()
                {
                    this$0 = PeekPagerHelper.this;
                    super();
                }
                        }

                        float f;
                        ValueAnimator valueanimator;
                        if (((PeekPagerHelper) (message)).pager.getCurrentItem() == i - 1)
                        {
                            i = 1;
                        } else
                        {
                            i = -1;
                        }
                        if (!RtlUtils.isLayoutDirectionRtl(((PeekPagerHelper) (message)).context))
                        {
                            byte0 = 1;
                        }
                        f = ((PeekPagerHelper) (message)).context.getResources().getDimension(0x7f0e0182);
                        valueanimator = ValueAnimator.ofFloat(new float[] {
                            0.0F, (float)(i * byte0) * f
                        });
                        valueanimator.setDuration(500L);
                        valueanimator.setInterpolator(new FastOutSlowInInterpolator());
                        valueanimator.addUpdateListener(message. new PeekPagerHelper._cls1());
                        valueanimator.addListener(message. new PeekPagerHelper._cls2());
                        valueanimator.start();
                        return;
                    }
                }
            }
        }

        public PeekHandler(Context context1, FindTimeGridViewPager findtimegridviewpager)
        {
            weakContext = new WeakReference(context1);
            weakPager = new WeakReference(findtimegridviewpager);
        }

        private class PeekPagerHelper
        {

            public Context context;
            public float fakeDragged;
            public FindTimeGridViewPager pager;

            public PeekPagerHelper(Context context1, FindTimeGridViewPager findtimegridviewpager)
            {
                pager = findtimegridviewpager;
                context = context1;
            }
        }

    }


    private class _cls4
        implements Runnable
    {

        private final FindTimeGridFragment this$0;

        public final void run()
        {
            FindTimeGridFragment findtimegridfragment = FindTimeGridFragment.this;
            boolean flag;
            if (((Fragment) (findtimegridfragment)).mHost != null && ((Fragment) (findtimegridfragment)).mAdded)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return;
            } else
            {
                mView.announceForAccessibility(requireContext().getResources().getString(0x7f130470));
                return;
            }
        }

        _cls4()
        {
            this$0 = FindTimeGridFragment.this;
            super();
        }
    }


    private class _cls5
        implements Runnable
    {

        private final FindTimeGridFragment this$0;

        public final void run()
        {
            FindTimeGridSlabPage findtimegridslabpage = (FindTimeGridSlabPage)bottomPager.findViewWithTag(Integer.valueOf(bottomPager.getCurrentItem()));
            String s = FindTimeUtil.getInstance(context).getDescription(currentSuggestion, accountName, accountType);
            TimelineSuggestion timelinesuggestion = currentSuggestion;
            FindTimeGridFragment findtimegridfragment = FindTimeGridFragment.this;
            boolean flag;
            if (suggestionIndex == findtimegridfragment.gridData.suggestions.size() - 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            findtimegridslabpage.setTimelineSuggestion(timelinesuggestion, s, flag);
        }

        _cls5()
        {
            this$0 = FindTimeGridFragment.this;
            super();
        }
    }

}
