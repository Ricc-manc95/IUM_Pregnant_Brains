// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.android.datetimepicker.Utils;
import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;

// Referenced classes of package com.android.datetimepicker.date:
//            DatePickerController

public abstract class MonthView extends View
{

    public int DAY_SELECTED_CIRCLE_SIZE;
    public int MINI_DAY_NUMBER_TEXT_SIZE;
    private int MONTH_DAY_LABEL_TEXT_SIZE;
    private int MONTH_HEADER_SIZE;
    private int MONTH_LABEL_TEXT_SIZE;
    private final Calendar calendar;
    private int dayOfWeekStart;
    private String dayOfWeekTypeface;
    private final Formatter formatter;
    private final GestureDetector gestureDetector;
    public boolean isRtl;
    private boolean lockAccessibilityDelegate;
    public Runnable mActiveLaunchDayRunnable;
    public DatePickerController mController;
    public final Calendar mDayLabelCalendar;
    public int mDayTextColor;
    public int mDisabledDayTextColor;
    public int mEdgePadding;
    public int mFirstJulianDay;
    public boolean mHasToday;
    public int mMonth;
    public Paint mMonthDayLabelPaint;
    public Paint mMonthNumPaint;
    private int mMonthTitleBGColor;
    private Paint mMonthTitleBGPaint;
    private Paint mMonthTitlePaint;
    public int mNumCells;
    public int mNumDays;
    public int mNumRows;
    public OnDayClickListener mOnDayClickListener;
    public int mRowHeight;
    public Paint mSelectedCirclePaint;
    public int mSelectedDay;
    public Drawable mSelector;
    public boolean mSelectorHasRipples;
    public boolean mShowWeekNumbers;
    public int mToday;
    public int mTodayNumberColor;
    public final MonthViewTouchHelper mTouchHelper;
    public Paint mWeekNumPaint;
    public int mWeekNumsColWidth;
    public int mWeekNumsPadding;
    public int mWeekStart;
    public int mWidth;
    public int mYear;
    private String monthTitleTypeface;
    public boolean rtlEnabled;
    public final Rect selectorRect;
    private final StringBuilder stringBuilder;
    public String timezone;

    public MonthView(Context context)
    {
        this(context, null);
    }

    public MonthView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mEdgePadding = 0;
        mWeekNumsPadding = 0;
        selectorRect = new Rect();
        mFirstJulianDay = -1;
        mRowHeight = 32;
        mHasToday = false;
        mSelectedDay = -1;
        mToday = -1;
        mWeekStart = 1;
        mNumDays = 7;
        mNumCells = mNumDays;
        mNumRows = 6;
        rtlEnabled = false;
        dayOfWeekStart = 0;
        attributeset = context.getResources();
        mDayLabelCalendar = Calendar.getInstance();
        calendar = Calendar.getInstance();
        dayOfWeekTypeface = attributeset.getString(0x7f130153);
        monthTitleTypeface = attributeset.getString(0x7f130424);
        mDayTextColor = attributeset.getColor(0x7f0d0080);
        mTodayNumberColor = attributeset.getColor(0x7f0d003c);
        mDisabledDayTextColor = attributeset.getColor(0x7f0d007f);
        attributeset.getColor(0x106000b);
        mMonthTitleBGColor = attributeset.getColor(0x7f0d0065);
        stringBuilder = new StringBuilder(50);
        formatter = new Formatter(stringBuilder, Locale.getDefault());
        MINI_DAY_NUMBER_TEXT_SIZE = attributeset.getDimensionPixelSize(0x7f0e00d9);
        MONTH_LABEL_TEXT_SIZE = attributeset.getDimensionPixelSize(0x7f0e02a2);
        MONTH_DAY_LABEL_TEXT_SIZE = attributeset.getDimensionPixelSize(0x7f0e02a1);
        MONTH_HEADER_SIZE = attributeset.getDimensionPixelOffset(0x7f0e02a3);
        DAY_SELECTED_CIRCLE_SIZE = attributeset.getDimensionPixelSize(0x7f0e00d8);
        mWeekNumsColWidth = attributeset.getDimensionPixelSize(0x7f0e00d3);
        mRowHeight = (attributeset.getDimensionPixelOffset(0x7f0e00d2) - getMonthHeaderSize()) / 6;
        mTouchHelper = getMonthViewTouchHelper();
        ViewCompat.setAccessibilityDelegate(this, mTouchHelper);
        ViewCompat.setImportantForAccessibility(this, 1);
        lockAccessibilityDelegate = true;
        boolean flag;
        if (getContext().getResources().getConfiguration().getLayoutDirection() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isRtl = flag;
        gestureDetector = new GestureDetector(context, new MonthViewGestureListener());
        mMonthTitlePaint = new Paint();
        mMonthTitlePaint.setFakeBoldText(true);
        mMonthTitlePaint.setAntiAlias(true);
        mMonthTitlePaint.setTextSize(MONTH_LABEL_TEXT_SIZE);
        mMonthTitlePaint.setTypeface(Typeface.create(monthTitleTypeface, 1));
        mMonthTitlePaint.setColor(mDayTextColor);
        mMonthTitlePaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        mMonthTitlePaint.setStyle(android.graphics.Paint.Style.FILL);
        mMonthTitleBGPaint = new Paint();
        mMonthTitleBGPaint.setFakeBoldText(true);
        mMonthTitleBGPaint.setAntiAlias(true);
        mMonthTitleBGPaint.setColor(mMonthTitleBGColor);
        mMonthTitleBGPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        mMonthTitleBGPaint.setStyle(android.graphics.Paint.Style.FILL);
        mSelectedCirclePaint = new Paint();
        mSelectedCirclePaint.setFakeBoldText(true);
        mSelectedCirclePaint.setAntiAlias(true);
        mSelectedCirclePaint.setColor(mTodayNumberColor);
        mSelectedCirclePaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        mSelectedCirclePaint.setStyle(android.graphics.Paint.Style.FILL);
        mSelectedCirclePaint.setAlpha(60);
        mMonthDayLabelPaint = new Paint();
        mMonthDayLabelPaint.setAntiAlias(true);
        mMonthDayLabelPaint.setTextSize(MONTH_DAY_LABEL_TEXT_SIZE);
        mMonthDayLabelPaint.setColor(mDayTextColor);
        mMonthDayLabelPaint.setTypeface(Typeface.create(dayOfWeekTypeface, 0));
        mMonthDayLabelPaint.setStyle(android.graphics.Paint.Style.FILL);
        mMonthDayLabelPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        mMonthDayLabelPaint.setFakeBoldText(true);
        mMonthNumPaint = new Paint();
        mMonthNumPaint.setAntiAlias(true);
        mMonthNumPaint.setTextSize(MINI_DAY_NUMBER_TEXT_SIZE);
        mMonthNumPaint.setStyle(android.graphics.Paint.Style.FILL);
        mMonthNumPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        mMonthNumPaint.setFakeBoldText(false);
        mWeekNumPaint = new Paint();
        mWeekNumPaint.setAntiAlias(true);
        mWeekNumPaint.setTextSize(MINI_DAY_NUMBER_TEXT_SIZE);
        mWeekNumPaint.setStyle(android.graphics.Paint.Style.FILL);
        mWeekNumPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
    }

    public final int calculateNumRows()
    {
        int i = findDayOffset();
        int j = (mNumCells + i) / mNumDays;
        if ((i + mNumCells) % mNumDays > 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        return i + j;
    }

    public boolean dispatchClickOnDay(float f, float f1, OnDayClickDispatcher ondayclickdispatcher)
    {
        if (!selectorRect.isEmpty())
        {
            if (!selectorRect.contains((int)f, (int)f1))
            {
                selectorRect.setEmpty();
                updateSelectorState();
                return false;
            }
            MonthAdapter.CalendarDay calendarday = getDayFromLocation(f, f1);
            if (calendarday != null)
            {
                if (mSelectorHasRipples)
                {
                    mSelector.setHotspot(f, f1);
                    class .Lambda._cls0
                        implements Runnable
                    {

                        private final MonthView arg$1;
                        private final OnDayClickDispatcher arg$2;
                        private final MonthAdapter.CalendarDay arg$3;

                        public final void run()
                        {
                            MonthView monthview = arg$1;
                            arg$2.invoke(arg$3);
                            monthview.mActiveLaunchDayRunnable = null;
                        }

            .Lambda._cls0(OnDayClickDispatcher ondayclickdispatcher, MonthAdapter.CalendarDay calendarday)
            {
                arg$1 = MonthView.this;
                arg$2 = ondayclickdispatcher;
                arg$3 = calendarday;
            }
                    }

                    mActiveLaunchDayRunnable = new .Lambda._cls0(ondayclickdispatcher, calendarday);
                    postDelayed(mActiveLaunchDayRunnable, 75L);
                } else
                {
                    ondayclickdispatcher.invoke(calendarday);
                }
                return true;
            }
        }
        return false;
    }

    public boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        if (mTouchHelper.dispatchHoverEvent(motionevent))
        {
            return true;
        } else
        {
            return super.dispatchHoverEvent(motionevent);
        }
    }

    public abstract void drawMonthDay(Canvas canvas, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1, int i2);

    public void drawMonthDayLabels(Canvas canvas)
    {
        int k = getMonthHeaderSize();
        int l = MONTH_DAY_LABEL_TEXT_SIZE / 2;
        int i1 = mWidth;
        int i;
        int j;
        boolean flag;
        if (mShowWeekNumbers)
        {
            i = mWeekNumsColWidth + mWeekNumsPadding;
        } else
        {
            i = 0;
        }
        if (rtlEnabled && isRtl)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            i = mEdgePadding;
        } else
        {
            i += mEdgePadding;
        }
        if (mShowWeekNumbers)
        {
            j = mWeekNumsColWidth + mWeekNumsPadding;
        } else
        {
            j = 0;
        }
        if (rtlEnabled && isRtl)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            j += mEdgePadding;
        } else
        {
            j = mEdgePadding;
        }
        i1 = (i1 - (i + j)) / (mNumDays << 1);
        i = 0;
        while (i < mNumDays) 
        {
            int j1 = getRtlCompatibleColumnIndex(i);
            int k1 = mWeekStart;
            int l1 = mNumDays;
            if (mShowWeekNumbers)
            {
                j = mWeekNumsColWidth + mWeekNumsPadding;
            } else
            {
                j = 0;
            }
            if (rtlEnabled && isRtl)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                j = mEdgePadding;
            } else
            {
                j += mEdgePadding;
            }
            mDayLabelCalendar.set(7, (j1 + k1) % l1);
            canvas.drawText(mDayLabelCalendar.getDisplayName(7, 1, Locale.getDefault()).toUpperCase(Locale.getDefault()), j + (i * 2 + 1) * i1, k - l, mMonthDayLabelPaint);
            i++;
        }
    }

    public void drawMonthNums(Canvas canvas)
    {
        int l = ((mRowHeight + MINI_DAY_NUMBER_TEXT_SIZE) / 2 - 1) + getMonthHeaderSize();
        int j1 = mWidth;
        float f;
        int i;
        int j;
        int k;
        if (mShowWeekNumbers)
        {
            i = mWeekNumsColWidth + mWeekNumsPadding;
        } else
        {
            i = 0;
        }
        if (rtlEnabled && isRtl)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            i = mEdgePadding;
        } else
        {
            i += mEdgePadding;
        }
        if (mShowWeekNumbers)
        {
            j = mWeekNumsColWidth + mWeekNumsPadding;
        } else
        {
            j = 0;
        }
        if (rtlEnabled && isRtl)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0)
        {
            j += mEdgePadding;
        } else
        {
            j = mEdgePadding;
        }
        f = (float)(j1 - (i + j)) / ((float)mNumDays * 2.0F);
        i = findDayOffset();
        j = 1;
        k = l;
        while (j <= mNumCells) 
        {
            float f1 = getRtlCompatibleColumnIndex(i) * 2 + 1;
            int i1;
            int k1;
            int l1;
            int i2;
            int j2;
            if (mShowWeekNumbers)
            {
                i1 = mWeekNumsColWidth + mWeekNumsPadding;
            } else
            {
                i1 = 0;
            }
            if (rtlEnabled && isRtl)
            {
                k1 = 1;
            } else
            {
                k1 = 0;
            }
            if (k1 != 0)
            {
                i1 = mEdgePadding;
            } else
            {
                i1 += mEdgePadding;
            }
            i1 = (int)((float)i1 + f1 * f);
            i2 = (mRowHeight + MINI_DAY_NUMBER_TEXT_SIZE) / 2;
            k1 = (int)((float)i1 - f);
            l1 = (int)((float)i1 + f);
            i2 = k - (i2 - 1);
            j2 = mRowHeight;
            drawMonthDay(canvas, mYear, mMonth, j, i1, k, k1, l1, i2, i2 + j2);
            k1 = i + 1;
            i = k1;
            i1 = k;
            if (k1 == mNumDays)
            {
                i = 0;
                i1 = k + mRowHeight;
            }
            j++;
            k = i1;
        }
    }

    public void drawMonthTitle(Canvas canvas)
    {
        int k = 1;
        int l = mWidth;
        String s;
        Context context;
        Formatter formatter1;
        int i;
        int j;
        long l1;
        if (mShowWeekNumbers)
        {
            i = mWeekNumsColWidth + mWeekNumsPadding;
        } else
        {
            i = 0;
        }
        if (rtlEnabled && isRtl)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            i = mEdgePadding;
        } else
        {
            i += mEdgePadding;
        }
        if (mShowWeekNumbers)
        {
            j = mWeekNumsColWidth + mWeekNumsPadding;
        } else
        {
            j = 0;
        }
        if (!rtlEnabled || !isRtl)
        {
            k = 0;
        }
        if (k != 0)
        {
            j += mEdgePadding;
        } else
        {
            j = mEdgePadding;
        }
        i = (i + j + l) / 2;
        j = (getMonthHeaderSize() - MONTH_DAY_LABEL_TEXT_SIZE) / 2;
        k = MONTH_LABEL_TEXT_SIZE / 3;
        stringBuilder.setLength(0);
        l1 = calendar.getTimeInMillis();
        context = getContext();
        formatter1 = formatter;
        if (TextUtils.isEmpty(timezone))
        {
            s = Time.getCurrentTimezone();
        } else
        {
            s = timezone;
        }
        canvas.drawText(DateUtils.formatDateRange(context, formatter1, l1, l1, 52, s).toString(), i, j + k, mMonthTitlePaint);
    }

    public void drawWeekNum$51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D4KIIA9954IILG_0(Canvas canvas, int i, int j, int k, int l, int i1)
    {
        l = (l - j) / 2;
        k = (k + i1) / 2;
        canvas.drawText(String.valueOf(i), l + j, k, mWeekNumPaint);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        updateSelectorState();
    }

    public final int findDayOffset()
    {
        int i;
        if (dayOfWeekStart < mWeekStart)
        {
            i = dayOfWeekStart + mNumDays;
        } else
        {
            i = dayOfWeekStart;
        }
        return i - mWeekStart;
    }

    public MonthAdapter.CalendarDay getDayFromLocation(float f, float f1)
    {
        Integer integer = getInternalDayFromLocation(f, f1);
        if (integer == null || integer.intValue() <= 0 || integer.intValue() > mNumCells)
        {
            return null;
        } else
        {
            return new MonthAdapter.CalendarDay(mYear, mMonth, integer.intValue());
        }
    }

    public final int getEdgePadding()
    {
        boolean flag1 = false;
        int i;
        int j;
        boolean flag;
        if (mShowWeekNumbers)
        {
            i = mWeekNumsColWidth + mWeekNumsPadding;
        } else
        {
            i = 0;
        }
        if (rtlEnabled && isRtl)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            i = mEdgePadding;
        } else
        {
            i += mEdgePadding;
        }
        if (mShowWeekNumbers)
        {
            j = mWeekNumsColWidth + mWeekNumsPadding;
        } else
        {
            j = 0;
        }
        flag = flag1;
        if (rtlEnabled)
        {
            flag = flag1;
            if (isRtl)
            {
                flag = true;
            }
        }
        if (flag)
        {
            j = mEdgePadding + j;
        } else
        {
            j = mEdgePadding;
        }
        return i + j;
    }

    public final Integer getInternalDayFromLocation(float f, float f1)
    {
        int i = getInternalStartPadding();
        if (f < (float)i || f > (float)(mWidth - getInternalEndPadding()))
        {
            return null;
        } else
        {
            int j = (int)(f1 - (float)getMonthHeaderSize()) / mRowHeight;
            return Integer.valueOf((getRtlCompatibleColumnIndex((int)(((f - (float)i) * (float)mNumDays) / (float)(mWidth - i - getInternalEndPadding()))) - findDayOffset()) + 1 + j * mNumDays);
        }
    }

    public final int getInternalEndPadding()
    {
        boolean flag1 = false;
        int i;
        boolean flag;
        if (mShowWeekNumbers)
        {
            i = mWeekNumsColWidth + mWeekNumsPadding;
        } else
        {
            i = 0;
        }
        flag = flag1;
        if (rtlEnabled)
        {
            flag = flag1;
            if (isRtl)
            {
                flag = true;
            }
        }
        if (flag)
        {
            return i + mEdgePadding;
        } else
        {
            return mEdgePadding;
        }
    }

    public final int getInternalStartPadding()
    {
        boolean flag1 = false;
        int i;
        boolean flag;
        if (mShowWeekNumbers)
        {
            i = mWeekNumsColWidth + mWeekNumsPadding;
        } else
        {
            i = 0;
        }
        flag = flag1;
        if (rtlEnabled)
        {
            flag = flag1;
            if (isRtl)
            {
                flag = true;
            }
        }
        if (flag)
        {
            return mEdgePadding;
        } else
        {
            return i + mEdgePadding;
        }
    }

    public int getMonthHeaderSize()
    {
        return MONTH_HEADER_SIZE;
    }

    public MonthViewTouchHelper getMonthViewTouchHelper()
    {
        return new MonthViewTouchHelper(this);
    }

    public final int getRtlCompatibleColumnIndex(int i)
    {
        boolean flag = true;
        if (i < 0 || i >= mNumDays)
        {
            Log.wtf("MonthView", String.format("Unexpected column index %d. Expected index in range of 0 <= x < %d", new Object[] {
                Integer.valueOf(i), Integer.valueOf(mNumDays)
            }));
        }
        int j;
        if (!rtlEnabled || !isRtl)
        {
            flag = false;
        }
        j = i;
        if (flag)
        {
            j = mNumDays - 1 - i;
        }
        return j;
    }

    protected final boolean isOutOfRange(MonthAdapter.CalendarDay calendarday)
    {
        boolean flag1 = false;
        if (mController == null) goto _L2; else goto _L1
_L1:
        Calendar calendar1 = mController.getMinDate();
        if (calendar1 == null || calendarday.compareTo(new MonthAdapter.CalendarDay(calendar1)) >= 0) goto _L2; else goto _L3
_L3:
        boolean flag = true;
_L7:
        if (flag) goto _L5; else goto _L4
_L4:
        if (mController == null)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        Calendar calendar2 = mController.getMaxDate();
        if (calendar2 == null || calendarday.compareTo(new MonthAdapter.CalendarDay(calendar2)) <= 0)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        flag = true;
_L8:
        if (!flag) goto _L6; else goto _L5
_L5:
        flag1 = true;
_L6:
        return flag1;
_L2:
        flag = false;
          goto _L7
        flag = false;
          goto _L8
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if (mSelector != null)
        {
            mSelector.jumpToCurrentState();
        }
    }

    final void onDayLongClick(MonthAdapter.CalendarDay calendarday)
    {
        boolean flag1 = false;
        if (mController == null) goto _L2; else goto _L1
_L1:
        Calendar calendar1 = mController.getMinDate();
        if (calendar1 == null || calendarday.compareTo(new MonthAdapter.CalendarDay(calendar1)) >= 0) goto _L2; else goto _L3
_L3:
        boolean flag = true;
_L7:
        if (flag) goto _L5; else goto _L4
_L4:
        if (mController == null)
        {
            break MISSING_BLOCK_LABEL_101;
        }
        Calendar calendar2 = mController.getMaxDate();
        if (calendar2 == null || calendarday.compareTo(new MonthAdapter.CalendarDay(calendar2)) <= 0)
        {
            break MISSING_BLOCK_LABEL_101;
        }
        flag = true;
_L8:
        if (!flag) goto _L6; else goto _L5
_L5:
        flag1 = true;
_L6:
        if (flag1)
        {
            return;
        } else
        {
            mTouchHelper.sendEventForVirtualView(calendarday.day, 2);
            return;
        }
_L2:
        flag = false;
          goto _L7
        flag = false;
          goto _L8
    }

    public void onDetachedFromWindow()
    {
        if (mActiveLaunchDayRunnable != null)
        {
            getHandler().removeCallbacks(mActiveLaunchDayRunnable);
            mActiveLaunchDayRunnable = null;
        }
        super.onDetachedFromWindow();
    }

    public void onDraw(Canvas canvas)
    {
        if (mSelector != null)
        {
            mSelector.draw(canvas);
        }
        drawMonthTitle(canvas);
        drawMonthDayLabels(canvas);
        drawMonthNums(canvas);
        if (mShowWeekNumbers)
        {
            int k = (mRowHeight + MINI_DAY_NUMBER_TEXT_SIZE) / 2;
            int k1 = getMonthHeaderSize();
            int i1 = (mRowHeight + MINI_DAY_NUMBER_TEXT_SIZE) / 2 - 1;
            int j1 = mWeekNumsColWidth + mWeekNumsPadding;
            int i;
            int j;
            int l;
            if (rtlEnabled && isRtl)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                j = canvas.getWidth() - mEdgePadding - j1;
            } else
            {
                j = mEdgePadding;
            }
            i = Utils.getWeekNumberInYear(mFirstJulianDay, Utils.convertDayOfWeekFromCalendarToTime(mWeekStart));
            l = 0;
            for (k = k1 + (k - 1); l < mNumRows; k += k1)
            {
                if (mMonth == 11 && l == mNumRows - 1 || mMonth == 0 && l == 1)
                {
                    i = Utils.getWeekNumberInYear(mFirstJulianDay + l * 7, Utils.convertDayOfWeekFromCalendarToTime(mWeekStart));
                }
                k1 = mRowHeight;
                drawWeekNum$51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D4KIIA9954IILG_0(canvas, i, j, k - i1, j + j1, k + i1);
                l++;
                i++;
            }

        }
    }

    public void onMeasure(int i, int j)
    {
        setMeasuredDimension(android.view.View.MeasureSpec.getSize(i), mRowHeight * mNumRows + getMonthHeaderSize());
    }

    public void onSizeChanged(int i, int j, int k, int l)
    {
        mWidth = i;
        mTouchHelper.invalidateRoot();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        gestureDetector.onTouchEvent(motionevent);
        return super.onTouchEvent(motionevent);
    }

    public void reuse()
    {
        mNumRows = 6;
        requestLayout();
    }

    public void setAccessibilityDelegate(android.view.View.AccessibilityDelegate accessibilitydelegate)
    {
        if (!lockAccessibilityDelegate)
        {
            super.setAccessibilityDelegate(accessibilitydelegate);
        }
    }

    public void setMonthParams(HashMap hashmap)
    {
        if (!hashmap.containsKey("month") && !hashmap.containsKey("year"))
        {
            throw new InvalidParameterException("You must specify month and year for this view");
        }
        setTag(hashmap);
        if (hashmap.containsKey("height"))
        {
            mRowHeight = ((Integer)hashmap.get("height")).intValue();
            if (mRowHeight < 10)
            {
                mRowHeight = 10;
            }
        }
        if (hashmap.containsKey("selected_day"))
        {
            mSelectedDay = ((Integer)hashmap.get("selected_day")).intValue();
        }
        Object obj;
        int i;
        boolean flag1;
        if (hashmap.containsKey("show_wk_num") && ((Integer)hashmap.get("show_wk_num")).intValue() != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        mShowWeekNumbers = flag1;
        mMonth = ((Integer)hashmap.get("month")).intValue();
        mYear = ((Integer)hashmap.get("year")).intValue();
        if (TextUtils.isEmpty(timezone))
        {
            obj = Time.getCurrentTimezone();
        } else
        {
            obj = timezone;
        }
        obj = new Time(((String) (obj)));
        ((Time) (obj)).setToNow();
        mHasToday = false;
        mToday = -1;
        calendar.set(2, mMonth);
        calendar.set(1, mYear);
        calendar.set(5, 1);
        dayOfWeekStart = calendar.get(7);
        if (hashmap.containsKey("week_start"))
        {
            mWeekStart = ((Integer)hashmap.get("week_start")).intValue();
        } else
        {
            mWeekStart = calendar.getFirstDayOfWeek();
        }
        mNumCells = Utils.getDaysInMonth(mMonth, mYear);
        i = 0;
        while (i < mNumCells) 
        {
            int j = i + 1;
            boolean flag;
            if (mYear == ((Time) (obj)).year && mMonth == ((Time) (obj)).month && j == ((Time) (obj)).monthDay)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                mHasToday = true;
                mToday = j;
            }
            i++;
        }
        mNumRows = calculateNumRows();
        mTouchHelper.invalidateRoot();
    }

    public void setRtlEnabled(boolean flag)
    {
        rtlEnabled = flag;
    }

    public void setSelectedDay(int i)
    {
        mSelectedDay = i;
    }

    public final void updateSelectorState()
    {
        if (mSelector != null)
        {
            Drawable drawable = mSelector;
            int ai[];
            if (selectorRect.isEmpty())
            {
                ai = StateSet.NOTHING;
            } else
            {
                ai = getDrawableState();
            }
            drawable.setState(ai);
        }
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return mSelector == drawable || super.verifyDrawable(drawable);
    }

    private class MonthViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public final MonthView this$0;

        public final boolean onDown(MotionEvent motionevent)
        {
            float f = motionevent.getX();
            float f1 = motionevent.getY();
            selectorRect.setEmpty();
            int j = getInternalEndPadding();
            int k = getInternalStartPadding();
            int i = getMonthHeaderSize();
            if (f >= (float)k && f <= (float)(mWidth - j) && f1 >= (float)i)
            {
                float f2 = (float)(mWidth - k - j) / (float)mNumDays;
                float f3 = k;
                j = (int)((float)(int)((f - (float)k) / f2) * f2 + f3);
                i += (int)((f1 - (float)i) / (float)mRowHeight) * mRowHeight;
                selectorRect.set(j, i, (int)(f2 + (float)j), mRowHeight + i);
                if (mSelector != null)
                {
                    mSelector.setBounds(selectorRect);
                    updateSelectorState();
                    if (mSelectorHasRipples)
                    {
                        mSelector.setHotspot(f, f1);
                        return true;
                    }
                }
            }
            return true;
        }

        public final void onLongPress(MotionEvent motionevent)
        {
            MonthView monthview = MonthView.this;
            class .Lambda._cls1
                implements OnDayClickDispatcher
            {

                private final MonthView arg$1;

                public final void invoke(MonthAdapter.CalendarDay calendarday)
                {
                    arg$1.onDayLongClick(calendarday);
                }

                .Lambda._cls1()
                {
                    arg$1 = MonthView.this;
                }
            }

            if (!monthview.selectorRect.isEmpty())
            {
                monthview.dispatchClickOnDay(motionevent.getX(), motionevent.getY(), monthview. new .Lambda._cls1());
            }
        }

        public final boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            if (selectorRect.isEmpty())
            {
                return false;
            }
            f = motionevent1.getX();
            f1 = motionevent1.getY();
            if (!selectorRect.contains((int)f, (int)f1))
            {
                selectorRect.setEmpty();
                updateSelectorState();
                return false;
            }
            if (mSelectorHasRipples)
            {
                mSelector.setHotspot(f, f1);
            }
            return true;
        }

        public final boolean onSingleTapUp(MotionEvent motionevent)
        {
            class .Lambda._cls0
                implements OnDayClickDispatcher
            {

                private final MonthViewGestureListener arg$1;

                public final void invoke(MonthAdapter.CalendarDay calendarday)
                {
                    MonthView monthview;
                    boolean flag1;
                    flag1 = false;
                    monthview = arg$1._fld0;
                    if (monthview.mController == null) goto _L2; else goto _L1
_L1:
                    Calendar calendar1 = monthview.mController.getMinDate();
                    if (calendar1 == null || calendarday.compareTo(new MonthAdapter.CalendarDay(calendar1)) >= 0) goto _L2; else goto _L3
_L3:
                    boolean flag = true;
_L7:
                    if (flag) goto _L5; else goto _L4
_L4:
                    if (monthview.mController == null)
                    {
                        break MISSING_BLOCK_LABEL_145;
                    }
                    Calendar calendar2 = monthview.mController.getMaxDate();
                    if (calendar2 == null || calendarday.compareTo(new MonthAdapter.CalendarDay(calendar2)) <= 0)
                    {
                        break MISSING_BLOCK_LABEL_145;
                    }
                    flag = true;
_L8:
                    if (!flag) goto _L6; else goto _L5
_L5:
                    flag1 = true;
_L6:
                    if (!flag1)
                    {
                        if (monthview.mOnDayClickListener != null)
                        {
                            monthview.mOnDayClickListener.onDayClick(monthview, calendarday);
                        }
                        monthview.mTouchHelper.sendEventForVirtualView(calendarday.day, 1);
                    }
                    return;
_L2:
                    flag = false;
                      goto _L7
                    flag = false;
                      goto _L8
                }

                .Lambda._cls0()
                {
                    arg$1 = MonthViewGestureListener.this;
                }

                private class OnDayClickListener
                {

                    public abstract void onDayClick(MonthView monthview, MonthAdapter.CalendarDay calendarday);
                }

            }

            if (selectorRect.isEmpty())
            {
                return false;
            } else
            {
                return dispatchClickOnDay(motionevent.getX(), motionevent.getY(), new .Lambda._cls0());
            }
        }

        MonthViewGestureListener()
        {
            this$0 = MonthView.this;
            super();
        }
    }


    private class OnDayClickDispatcher
    {

        public abstract void invoke(MonthAdapter.CalendarDay calendarday);
    }


    private class MonthViewTouchHelper extends ExploreByTouchHelper
    {

        private final Calendar tempCalendar = Calendar.getInstance();
        private final Rect tempRect = new Rect();
        public final MonthView this$0;

        public CharSequence getDateDescription(int i)
        {
            tempCalendar.set(mYear, mMonth, i);
            return DateFormat.format("dd MMMM yyyy", tempCalendar.getTimeInMillis());
        }

        public void getItemBounds(int i, Rect rect)
        {
            int i1 = getInternalStartPadding();
            int l = getMonthHeaderSize();
            int j = mRowHeight;
            int k = (mWidth - getEdgePadding()) / mNumDays;
            int j1 = (i - 1) + findDayOffset();
            i = j1 / mNumDays;
            i1 += getRtlCompatibleColumnIndex(j1 % mNumDays) * k;
            i = l + i * j;
            rect.set(i1, i, k + i1, j + i);
        }

        public CharSequence getItemDescription(int i)
        {
            CharSequence charsequence = getDateDescription(i);
            Object obj = charsequence;
            if (i == mSelectedDay)
            {
                obj = getContext().getString(0x7f130311, new Object[] {
                    charsequence
                });
            }
            return ((CharSequence) (obj));
        }

        public int getVirtualViewAt(float f, float f1)
        {
            MonthAdapter.CalendarDay calendarday = getDayFromLocation(f, f1);
            if (calendarday != null)
            {
                return calendarday.day;
            } else
            {
                return 0x80000000;
            }
        }

        public void getVisibleVirtualViews(List list)
        {
            for (int i = 1; i <= mNumCells; i++)
            {
                list.add(Integer.valueOf(i));
            }

        }

        public boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
        {
            boolean flag;
            boolean flag1;
            flag = false;
            flag1 = true;
            j;
            JVM INSTR lookupswitch 2: default 32
        //                       16: 38
        //                       32: 213;
               goto _L1 _L2 _L3
_L1:
            flag1 = false;
_L13:
            return flag1;
_L2:
            MonthAdapter.CalendarDay calendarday;
            bundle = MonthView.this;
            calendarday = new MonthAdapter.CalendarDay(mYear, mMonth, i);
            if (((MonthView) (bundle)).mController == null) goto _L5; else goto _L4
_L4:
            Calendar calendar1 = ((MonthView) (bundle)).mController.getMinDate();
            if (calendar1 == null || calendarday.compareTo(new MonthAdapter.CalendarDay(calendar1)) >= 0) goto _L5; else goto _L6
_L6:
            i = 1;
_L14:
            if (i != 0) goto _L8; else goto _L7
_L7:
            if (((MonthView) (bundle)).mController == null) goto _L10; else goto _L9
_L9:
            calendar1 = ((MonthView) (bundle)).mController.getMaxDate();
            if (calendar1 == null || calendarday.compareTo(new MonthAdapter.CalendarDay(calendar1)) <= 0) goto _L10; else goto _L11
_L11:
            i = 1;
_L15:
            j = ((flag) ? 1 : 0);
            if (i == 0)
            {
                continue; /* Loop/switch isn't completed */
            }
_L8:
            j = 1;
            if (j != 0) goto _L13; else goto _L12
_L12:
            if (((MonthView) (bundle)).mOnDayClickListener != null)
            {
                ((MonthView) (bundle)).mOnDayClickListener.onDayClick(bundle, calendarday);
            }
            ((MonthView) (bundle)).mTouchHelper.sendEventForVirtualView(calendarday.day, 1);
            return true;
_L5:
            i = 0;
              goto _L14
_L10:
            i = 0;
              goto _L15
_L3:
            onDayLongClick(new MonthAdapter.CalendarDay(mYear, mMonth, i));
            return true;
              goto _L14
        }

        public void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent)
        {
            accessibilityevent.setContentDescription(getItemDescription(i));
        }

        public void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            getItemBounds(i, tempRect);
            Object obj = getItemDescription(i);
            accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj)));
            obj = tempRect;
            accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj)));
            accessibilitynodeinfocompat.mInfo.addAction(16);
            accessibilitynodeinfocompat.mInfo.addAction(32);
            if (i == mSelectedDay)
            {
                accessibilitynodeinfocompat.mInfo.setSelected(true);
            }
        }

        public MonthViewTouchHelper(View view)
        {
            this$0 = MonthView.this;
            super(view);
        }
    }

}
