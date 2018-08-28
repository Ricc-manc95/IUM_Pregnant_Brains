// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AbsListView;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

// Referenced classes of package com.android.datetimepicker.date:
//            DatePickerController, MonthAdapter, MonthView

public abstract class DayPickerView extends ListView
    implements android.widget.AbsListView.OnScrollListener, DatePickerDialog.OnDateChangedListener
{

    public static int LIST_TOP_OFFSET = -1;
    private static SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy", Locale.getDefault());
    public DatePickerController controller;
    public MonthAdapter mAdapter;
    public int mCurrentScrollState;
    private float mFriction;
    public Handler mHandler;
    public int mPreviousScrollState;
    private ScrollStateRunnable mScrollStateChangedRunnable;
    private MonthAdapter.CalendarDay mSelectedDay;
    private MonthAdapter.CalendarDay mTempDay;
    private boolean performingScroll;

    public DayPickerView(Context context, DatePickerController datepickercontroller)
    {
        super(context);
        mFriction = 1.0F;
        mSelectedDay = new MonthAdapter.CalendarDay();
        mTempDay = new MonthAdapter.CalendarDay();
        mPreviousScrollState = 0;
        mCurrentScrollState = 0;
        mScrollStateChangedRunnable = new ScrollStateRunnable();
        mHandler = new Handler();
        setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, -1));
        setDrawSelectorOnTop(false);
        setCacheColorHint(0);
        setDivider(null);
        setItemsCanFocus(true);
        setFastScrollEnabled(false);
        setVerticalScrollBarEnabled(false);
        setOnScrollListener(this);
        setFadingEdgeLength(0);
        setFriction(ViewConfiguration.getScrollFriction() * mFriction);
        controller = datepickercontroller;
        controller.registerOnDateChangedListener(this);
        refreshAdapter();
        goTo(controller.getSelectedDay(), false, true, true);
    }

    public abstract MonthAdapter createMonthAdapter(Context context, DatePickerController datepickercontroller);

    public final boolean goTo(MonthAdapter.CalendarDay calendarday, boolean flag, boolean flag1, boolean flag2)
    {
        if (flag1)
        {
            MonthAdapter.CalendarDay calendarday1 = mSelectedDay;
            calendarday1.year = calendarday.year;
            calendarday1.month = calendarday.month;
            calendarday1.day = calendarday.day;
        }
        MonthAdapter.CalendarDay calendarday2 = mTempDay;
        calendarday2.year = calendarday.year;
        calendarday2.month = calendarday.month;
        calendarday2.day = calendarday.day;
        final int position = (calendarday.year - controller.getMinYear()) * 12 + calendarday.month;
        int i = 0;
        do
        {
            calendarday = getChildAt(i);
            if (calendarday == null || calendarday.getTop() >= 0)
            {
                if (calendarday != null)
                {
                    getPositionForView(calendarday);
                }
                if (flag1)
                {
                    calendarday = mAdapter;
                    calendarday.selectedDay = mSelectedDay;
                    calendarday.notifyDataSetChanged();
                }
                calendarday = mTempDay;
                invalidateViews();
                mPreviousScrollState = 2;
                if (flag)
                {
                    smoothScrollToPositionFromTop(position, LIST_TOP_OFFSET, 250);
                    return true;
                } else
                {
                    clearFocus();
                    post(new _cls1());
                    onScrollStateChanged(this, 0);
                    return false;
                }
            }
            i++;
        } while (true);
    }

    protected void layoutChildren()
    {
        int i;
        int j;
        j = getChildCount();
        i = 0;
_L5:
        if (i >= j) goto _L2; else goto _L1
_L1:
        Object obj;
        obj = getChildAt(i);
        if (!(obj instanceof MonthView))
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (MonthView)obj;
        int l = ((ExploreByTouchHelper) (((MonthView) (obj)).mTouchHelper)).mAccessibilityFocusedVirtualViewId;
        if (l >= 0)
        {
            obj = new MonthAdapter.CalendarDay(((MonthView) (obj)).mYear, ((MonthView) (obj)).mMonth, l);
        } else
        {
            obj = null;
        }
        if (obj == null)
        {
            continue; /* Loop/switch isn't completed */
        }
_L6:
        super.layoutChildren();
        if (!performingScroll) goto _L4; else goto _L3
_L3:
        performingScroll = false;
_L8:
        return;
        i++;
          goto _L5
_L2:
        obj = null;
          goto _L6
_L4:
        if (obj == null) goto _L8; else goto _L7
_L7:
        int i1;
        i1 = getChildCount();
        i = 0;
_L11:
        if (i >= i1) goto _L8; else goto _L9
_L9:
        Object obj1 = getChildAt(i);
        if (!(obj1 instanceof MonthView))
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = (MonthView)obj1;
        int k;
        if (((MonthAdapter.CalendarDay) (obj)).year != ((MonthView) (obj1)).mYear || ((MonthAdapter.CalendarDay) (obj)).month != ((MonthView) (obj1)).mMonth || ((MonthAdapter.CalendarDay) (obj)).day > ((MonthView) (obj1)).mNumCells)
        {
            k = 0;
        } else
        {
            obj1 = ((MonthView) (obj1)).mTouchHelper;
            k = ((MonthAdapter.CalendarDay) (obj)).day;
            ((AccessibilityDelegateCompat) (obj1)).getAccessibilityNodeProvider(((MonthView.MonthViewTouchHelper) (obj1)).this$0).performAction(k, 64, null);
            k = 1;
        }
        if (k != 0) goto _L8; else goto _L10
_L10:
        i++;
          goto _L11
    }

    public final void onDateChanged()
    {
        goTo(controller.getSelectedDay(), false, true, true);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(accessibilityevent);
        accessibilityevent.setItemCount(-1);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.addAction(4096);
        accessibilitynodeinfo.addAction(8192);
    }

    public void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        MonthView monthview = (MonthView)abslistview.getChildAt(0);
        if (monthview == null)
        {
            return;
        } else
        {
            abslistview.getFirstVisiblePosition();
            monthview.getHeight();
            monthview.getBottom();
            mPreviousScrollState = mCurrentScrollState;
            return;
        }
    }

    public void onScrollStateChanged(AbsListView abslistview, int i)
    {
        abslistview = mScrollStateChangedRunnable;
        ((ScrollStateRunnable) (abslistview))._fld0.mHandler.removeCallbacks(abslistview);
        abslistview.newState = i;
        ((ScrollStateRunnable) (abslistview))._fld0.mHandler.postDelayed(abslistview, 40L);
    }

    public boolean performAccessibilityAction(int i, Bundle bundle)
    {
        if (i != 4096 && i != 8192)
        {
            return super.performAccessibilityAction(i, bundle);
        }
        int j = getFirstVisiblePosition();
        bundle = new MonthAdapter.CalendarDay(j / 12 + controller.getMinYear(), j % 12, 1);
        if (i != 4096) goto _L2; else goto _L1
_L1:
        bundle.month = ((MonthAdapter.CalendarDay) (bundle)).month + 1;
        if (((MonthAdapter.CalendarDay) (bundle)).month == 12)
        {
            bundle.month = 0;
            bundle.year = ((MonthAdapter.CalendarDay) (bundle)).year + 1;
        }
_L4:
        Object obj = Calendar.getInstance();
        ((Calendar) (obj)).set(((MonthAdapter.CalendarDay) (bundle)).year, ((MonthAdapter.CalendarDay) (bundle)).month, ((MonthAdapter.CalendarDay) (bundle)).day);
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(((Calendar) (obj)).getDisplayName(2, 2, Locale.getDefault()));
        stringbuffer.append(" ");
        stringbuffer.append(YEAR_FORMAT.format(((Calendar) (obj)).getTime()));
        obj = stringbuffer.toString();
        if (this != null && obj != null)
        {
            announceForAccessibility(((CharSequence) (obj)));
        }
        goTo(bundle, true, false, true);
        performingScroll = true;
        return true;
_L2:
        if (i == 8192)
        {
            View view = getChildAt(0);
            if (view != null && view.getTop() >= -1)
            {
                bundle.month = ((MonthAdapter.CalendarDay) (bundle)).month - 1;
                if (((MonthAdapter.CalendarDay) (bundle)).month == -1)
                {
                    bundle.month = 11;
                    bundle.year = ((MonthAdapter.CalendarDay) (bundle)).year - 1;
                }
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected final void refreshAdapter()
    {
        if (mAdapter == null)
        {
            mAdapter = createMonthAdapter(getContext(), controller);
        } else
        {
            MonthAdapter monthadapter = mAdapter;
            monthadapter.selectedDay = mSelectedDay;
            monthadapter.notifyDataSetChanged();
        }
        setAdapter(mAdapter);
    }


    private class ScrollStateRunnable
        implements Runnable
    {

        public int newState;
        public final DayPickerView this$0;

        public final void run()
        {
            Object obj;
            int k;
            k = 1;
            mCurrentScrollState = newState;
            if (newState != 0 || mPreviousScrollState == 0 || mPreviousScrollState == 1)
            {
                break MISSING_BLOCK_LABEL_204;
            }
            mPreviousScrollState = newState;
            obj = getChildAt(0);
            int i = 0;
            for (; obj != null && ((View) (obj)).getBottom() <= 0; obj = ((DayPickerView) (obj)).getChildAt(i))
            {
                obj = DayPickerView.this;
                i++;
            }

            if (obj != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            int l;
            int j = getFirstVisiblePosition();
            l = getLastVisiblePosition();
            int i1;
            if (j != 0 && l != getCount() - 1)
            {
                j = k;
            } else
            {
                j = 0;
            }
            k = ((View) (obj)).getTop();
            l = ((View) (obj)).getBottom();
            i1 = getHeight() / 2;
            if (!j || k >= DayPickerView.LIST_TOP_OFFSET) goto _L1; else goto _L3
_L3:
            if (l > i1)
            {
                smoothScrollBy(k, 250);
                return;
            } else
            {
                smoothScrollBy(l, 250);
                return;
            }
            mPreviousScrollState = newState;
            return;
        }

        protected ScrollStateRunnable()
        {
            this$0 = DayPickerView.this;
            super();
        }
    }


    private class _cls1
        implements Runnable
    {

        private final DayPickerView this$0;
        private final int val$position;

        public final void run()
        {
            setSelection(position);
        }

        _cls1()
        {
            this$0 = DayPickerView.this;
            position = i;
            super();
        }
    }

}
