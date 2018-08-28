// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import com.android.datetimepicker.date.MonthView;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthData, TimelineItem, TimelineBirthday, TimelineHoliday, 
//            DataFactory

public final class TimelyColorMonthView extends MonthView
    implements OnPropertyChangedListener
{

    private int alternateCalendarPref;
    private int alternateDayLineHeight;
    private final SparseArray alternateDayStrings = new SparseArray(42);
    private final int alternateDayTextColor;
    private final float alternateMonthNumTextSize;
    private final int bottomPadding;
    private Paint circlePaint;
    public final DataFactory dataFactory;
    private final int dotsColorWithIllustration;
    private int dotsColors[];
    private final float dotsRadius;
    private final float dotsSpacing;
    public int interDayPadding;
    private final boolean isTabletConfig;
    public int listenerTag;
    private final float monthNumTextSize;
    private final int overflowPlusColor;
    private boolean showIllustration;
    private final int textBottomMargin;
    public Time time;
    private final int todayCircleColor;
    private final int topPadding;

    public TimelyColorMonthView(Context context, DataFactory datafactory)
    {
        int i = 0;
        super(context);
        time = new Time();
        circlePaint = new Paint();
        showIllustration = false;
        isTabletConfig = context.getResources().getBoolean(0x7f0c0016);
        dataFactory = datafactory;
        context = context.getResources();
        mSelectedCirclePaint.setAlpha(255);
        mSelectedCirclePaint.setColor(context.getColor(0x7f0d02f2));
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(android.graphics.Paint.Style.FILL);
        circlePaint.setStrokeWidth(context.getDimensionPixelSize(0x7f0e00cd));
        monthNumTextSize = context.getDimensionPixelSize(0x7f0e00cf);
        mMonthNumPaint.setTextSize(monthNumTextSize);
        alternateCalendarPref = PreferencesConstants.getAlternateCalendarPref(getContext());
        alternateMonthNumTextSize = context.getDimensionPixelSize(0x7f0e00be);
        if (alternateCalendarPref != 0)
        {
            i = context.getDimensionPixelSize(0x7f0e00bd);
        }
        alternateDayLineHeight = i;
        alternateDayTextColor = context.getColor(0x7f0d007c);
        mWeekNumPaint.setTypeface(null);
        mWeekNumPaint.setTextSize(context.getDimensionPixelSize(0x7f0e00cf));
        mWeekNumPaint.setColor(context.getColor(0x7f0d032d));
        mMonthDayLabelPaint.setTypeface(null);
        mMonthDayLabelPaint.setTextSize(context.getDimensionPixelSize(0x7f0e00cf));
        mMonthDayLabelPaint.setColor(context.getColor(0x7f0d0151));
        todayCircleColor = context.getColor(0x7f0d01d7);
        overflowPlusColor = context.getColor(0x7f0d007e);
        dotsColorWithIllustration = context.getColor(0x7f0d007d);
        mTodayNumberColor = context.getColor(0x106000b);
        mDayTextColor = context.getColor(0x7f0d0082);
        DAY_SELECTED_CIRCLE_SIZE = context.getDimensionPixelOffset(0x7f0e00c0);
        mWeekNumsColWidth = DAY_SELECTED_CIRCLE_SIZE * 2;
        dotsRadius = (float)context.getDimensionPixelOffset(0x7f0e00c2) / 2.0F;
        dotsSpacing = context.getDimensionPixelOffset(0x7f0e00c3);
        mEdgePadding = context.getDimensionPixelOffset(0x7f0e00c4);
        mWeekNumsPadding = context.getDimensionPixelOffset(0x7f0e00d4);
        textBottomMargin = context.getDimensionPixelOffset(0x7f0e00ce);
        topPadding = context.getDimensionPixelOffset(0x7f0e00d0);
        bottomPadding = context.getDimensionPixelOffset(0x7f0e00bf);
        updateRowHeight();
        setRtlEnabled(true);
    }

    private final void onAlternateCalendarSettingsChange()
    {
        alternateCalendarPref = PreferencesConstants.getAlternateCalendarPref(getContext());
        int i;
        if (alternateCalendarPref != 0)
        {
            i = getResources().getDimensionPixelSize(0x7f0e00bd);
        } else
        {
            i = 0;
        }
        alternateDayLineHeight = i;
        alternateDayStrings.clear();
        updateRowHeight();
        requestLayout();
    }

    private final void updateInternalPaddingValues()
    {
        if (isTabletConfig)
        {
            Resources resources = getResources();
            if (mShowWeekNumbers)
            {
                interDayPadding = resources.getDimensionPixelOffset(0x7f0e00c9);
                mEdgePadding = (mWidth - (DAY_SELECTED_CIRCLE_SIZE << 1) * mNumDays - interDayPadding * (mNumDays - 1) - mWeekNumsPadding - mWeekNumsColWidth) / 2;
                return;
            } else
            {
                interDayPadding = resources.getDimensionPixelOffset(0x7f0e00c8);
                mEdgePadding = (mWidth - (DAY_SELECTED_CIRCLE_SIZE << 1) * mNumDays - interDayPadding * (mNumDays - 1)) / 2;
                return;
            }
        }
        int l = mNumDays;
        int k = mWidth - mEdgePadding * 2 - (DAY_SELECTED_CIRCLE_SIZE << 1) * mNumDays;
        int j = k;
        int i = l;
        if (mShowWeekNumbers)
        {
            i = l + 1;
            j = k - mWeekNumsColWidth;
        }
        i = j / (i - 1);
        interDayPadding = i;
        mWeekNumsPadding = i;
    }

    private final void updateRowHeight()
    {
        if (isTabletConfig)
        {
            mRowHeight = getResources().getDimensionPixelOffset(0x7f0e00ca) + DAY_SELECTED_CIRCLE_SIZE * 2;
            return;
        } else
        {
            mRowHeight = Math.max(alternateDayLineHeight + getResources().getDimensionPixelOffset(0x7f0e00cc), getResources().getDisplayMetrics().heightPixels / 20);
            return;
        }
    }

    public final void drawMonthDay(Canvas canvas, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1, int i2)
    {
        Object obj = time;
        ((Time) (obj)).writeFieldsToImpl();
        ((Time) (obj)).impl.set(k, j, i);
        ((Time) (obj)).copyFieldsFromImpl();
        time.normalizeSafe();
        if (time.allDay)
        {
            obj = time;
            Time time1 = time;
            time.second = 0;
            time1.minute = 0;
            obj.hour = 0;
        }
        int j2 = j1 + DAY_SELECTED_CIRCLE_SIZE;
        if (mHasToday && mToday == k)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        if (mSelectedDay == k)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (l != 0)
        {
            circlePaint.setColor(todayCircleColor);
            canvas.drawCircle(j2, DAY_SELECTED_CIRCLE_SIZE + l1 + alternateDayLineHeight / 2, DAY_SELECTED_CIRCLE_SIZE, circlePaint);
        } else
        if (i1 != 0)
        {
            canvas.drawCircle(j2, DAY_SELECTED_CIRCLE_SIZE + l1 + alternateDayLineHeight / 2, DAY_SELECTED_CIRCLE_SIZE, mSelectedCirclePaint);
        } else
        {
            j1 = k - 1 << 2;
            for (i1 = 0; i1 <= 3 && dotsColors[j1] != 0; i1++)
            {
                j1++;
            }

            if (i1 > 0)
            {
                l1 = Math.min(i1, 3);
                float f1 = dotsRadius;
                float f2 = l1;
                float f4 = dotsSpacing;
                float f6 = l1 - 1;
                float f = j2;
                f2 = (2.0F * f1 * f2 + f4 * f6) / 2.0F;
                f4 = dotsRadius;
                f1 = i2 - textBottomMargin;
                f6 = dotsRadius;
                f1 = (float)alternateDayLineHeight + (f1 + 4F * f6);
                j1 = 0;
                f = f4 + (f - f2);
                while (j1 < l1) 
                {
                    if (isTabletConfig && showIllustration)
                    {
                        k1 = dotsColorWithIllustration;
                    } else
                    {
                        k1 = dotsColors[(k - 1 << 2) + j1];
                    }
                    if (j1 == 2 && i1 > 3)
                    {
                        Paint paint = circlePaint;
                        float f3;
                        float f5;
                        if (isTabletConfig && showIllustration)
                        {
                            k1 = dotsColorWithIllustration;
                        } else
                        {
                            k1 = overflowPlusColor;
                        }
                        paint.setColor(k1);
                        canvas.drawLine(f - dotsRadius, f1, f + dotsRadius, f1, circlePaint);
                        canvas.drawLine(f, f1 - dotsRadius, f, f1 + dotsRadius, circlePaint);
                    } else
                    {
                        circlePaint.setColor(k1);
                        canvas.drawCircle(f, f1, dotsRadius, circlePaint);
                    }
                    f3 = dotsRadius;
                    f5 = dotsSpacing;
                    j1++;
                    f += 2.0F * f3 + f5;
                }
            }
        }
        obj = mMonthNumPaint;
        if (l != 0)
        {
            i1 = mTodayNumberColor;
        } else
        {
            i1 = mDayTextColor;
        }
        ((Paint) (obj)).setColor(i1);
        mMonthNumPaint.setTextSize(monthNumTextSize);
        canvas.drawText(String.format("%d", new Object[] {
            Integer.valueOf(k)
        }), j2, i2 - textBottomMargin, mMonthNumPaint);
        if (alternateCalendarPref != 0)
        {
            Object obj1 = mMonthNumPaint;
            if (l != 0)
            {
                l = mTodayNumberColor;
            } else
            {
                l = alternateDayTextColor;
            }
            ((Paint) (obj1)).setColor(l);
            mMonthNumPaint.setTextSize(alternateMonthNumTextSize);
            obj1 = null;
            if (alternateCalendarPref != 0)
            {
                i = Utils.getJulianDay(i, j, k);
                String s = (String)alternateDayStrings.get(i);
                obj1 = s;
                if (s == null)
                {
                    obj1 = AlternateCalendarUtils.getAlternateDayOfMonthString(i, getResources(), alternateCalendarPref);
                    alternateDayStrings.put(i, obj1);
                }
            }
            canvas.drawText(((String) (obj1)), j2, i2, mMonthNumPaint);
        }
    }

    protected final void drawMonthDayLabels(Canvas canvas)
    {
        int l = topPadding;
        int i1 = DAY_SELECTED_CIRCLE_SIZE;
        int j1 = textBottomMargin;
        Date date = new Date();
        for (int i = 0; i < mNumDays; i++)
        {
            int k1 = getInternalStartPadding();
            int l1 = interDayPadding;
            int i2 = DAY_SELECTED_CIRCLE_SIZE;
            int j2 = DAY_SELECTED_CIRCLE_SIZE;
            int k = mWeekStart + getRtlCompatibleColumnIndex(i);
            int j = k;
            if (k > mNumDays)
            {
                j = k % mNumDays;
            }
            mDayLabelCalendar.set(7, j);
            date.setTime(mDayLabelCalendar.getTimeInMillis());
            canvas.drawText(Utils.getShortDayOfWeek(date), j2 + (k1 + (l1 + i2 * 2) * i), (l + i1 * 2) - j1, mMonthDayLabelPaint);
        }

    }

    protected final void drawMonthNums(Canvas canvas)
    {
        int k = topPadding + getMonthHeaderSize();
        int i = findDayOffset();
        for (int j = 1; j <= mNumCells;)
        {
            int l = getRtlCompatibleColumnIndex(i);
            l = getInternalStartPadding() + l * (interDayPadding + DAY_SELECTED_CIRCLE_SIZE * 2);
            int i1 = DAY_SELECTED_CIRCLE_SIZE;
            int j1 = DAY_SELECTED_CIRCLE_SIZE;
            drawMonthDay(canvas, mYear, mMonth, j, l, k, l, l + i1 * 2, k, k + j1 * 2);
            i1 = i + 1;
            i = i1;
            l = k;
            if (i1 == mNumDays)
            {
                i = 0;
                l = k + mRowHeight;
            }
            j++;
            k = l;
        }

    }

    protected final void drawMonthTitle(Canvas canvas)
    {
    }

    protected final void drawWeekNum$51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D4KIIA9954IILG_0(Canvas canvas, int i, int j, int k, int l, int i1)
    {
        l = DAY_SELECTED_CIRCLE_SIZE;
        j = (DAY_SELECTED_CIRCLE_SIZE * 2 + j + j) / 2;
        canvas.drawText(String.format("%d", new Object[] {
            Integer.valueOf(i)
        }), j, (l * 2 + k) - textBottomMargin, mWeekNumPaint);
    }

    public final com.android.datetimepicker.date.MonthAdapter.CalendarDay getDayFromLocation(float f, float f1)
    {
        float f2 = getInternalStartPadding() - interDayPadding / 2;
        float f3 = (mRowHeight + DAY_SELECTED_CIRCLE_SIZE) - mRowHeight / 2;
        if (f1 >= f3 && f >= f2 && f < (float)(mWidth - getInternalEndPadding()))
        {
            int i = (int)((f1 - f3) / (float)mRowHeight);
            i = (getRtlCompatibleColumnIndex((int)((f - f2) / (float)(DAY_SELECTED_CIRCLE_SIZE * 2 + interDayPadding))) - findDayOffset()) + 1 + i * mNumDays;
            if (i > 0 && i <= mNumCells)
            {
                return new com.android.datetimepicker.date.MonthAdapter.CalendarDay(mYear, mMonth, i);
            }
        }
        return null;
    }

    protected final int getMonthHeaderSize()
    {
        return mRowHeight - alternateDayLineHeight;
    }

    protected final com.android.datetimepicker.date.MonthView.MonthViewTouchHelper getMonthViewTouchHelper()
    {
        return new TimelyMonthViewTouchHelper(this);
    }

    protected final void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        showIllustration = CalendarProperties.getBooleanProperty(11).booleanValue();
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        ((CalendarProperties)calendarproperties).registerListener(11, this);
        calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        ((CalendarProperties)calendarproperties).registerListener(13, this);
        if (alternateCalendarPref != PreferencesConstants.getAlternateCalendarPref(getContext()))
        {
            onAlternateCalendarSettingsChange();
        }
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        if (i == 11)
        {
            showIllustration = ((Boolean)obj).booleanValue();
            invalidate();
        } else
        if (i == 13)
        {
            onAlternateCalendarSettingsChange();
            invalidate();
            return;
        }
    }

    protected final void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        ((CalendarProperties)calendarproperties).unregisterListener(11, this);
        calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).unregisterListener(13, this);
            return;
        }
    }

    protected final void onMeasure(int i, int j)
    {
        setMeasuredDimension(android.view.View.MeasureSpec.getSize(i), topPadding + mRowHeight * mNumRows + getMonthHeaderSize() + bottomPadding);
    }

    protected final void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        updateInternalPaddingValues();
    }

    final void onUpdateData(MonthData monthdata)
    {
        Arrays.fill(dotsColors, 0);
        if (monthdata.isDataReady())
        {
            int l = PreferenceUtils.getBirthdayColor(getContext());
            ArrayList arraylist = new ArrayList(4);
            int i = 0;
            int j = 0;
            while (j < dotsColors.length) 
            {
                Object obj = monthdata.getItems(mFirstJulianDay + i);
                if (obj != null && ((List) (obj)).size() != 0)
                {
                    arraylist.clear();
                    obj = ((List) (obj)).iterator();
                    int k = 0;
                    boolean flag = false;
label0:
                    do
                    {
                        int i1;
                        do
                        {
                            TimelineItem timelineitem;
                            do
                            {
                                if (!((Iterator) (obj)).hasNext())
                                {
                                    break label0;
                                }
                                timelineitem = (TimelineItem)((Iterator) (obj)).next();
                                if (timelineitem instanceof TimelineBirthday)
                                {
                                    flag = true;
                                    continue;
                                }
                                if (!(timelineitem instanceof TimelineHoliday))
                                {
                                    break;
                                }
                                k = timelineitem.getColor();
                            } while (true);
                            i1 = timelineitem.getColor();
                        } while (arraylist.contains(Integer.valueOf(i1)));
                        dotsColors[arraylist.size() + j] = i1;
                        arraylist.add(Integer.valueOf(i1));
                    } while (arraylist.size() != 4);
                    if (flag && arraylist.size() < 4 && !arraylist.contains(Integer.valueOf(l)))
                    {
                        dotsColors[arraylist.size() + j] = l;
                        arraylist.add(Integer.valueOf(l));
                    }
                    if (k != 0 && arraylist.size() < 4 && !arraylist.contains(Integer.valueOf(k)))
                    {
                        dotsColors[arraylist.size() + j] = k;
                    }
                }
                j += 4;
                i++;
            }
        }
    }

    public final void reuse()
    {
        MonthData monthdata = dataFactory.getDataAllowNull(mFirstJulianDay);
        if (monthdata != null)
        {
            monthdata.unregisterListener(mFirstJulianDay, 7);
        }
        super.reuse();
    }

    public final void setMonthParams(HashMap hashmap)
    {
        super.setMonthParams(hashmap);
        dotsColors = new int[mNumCells << 2];
        mFirstJulianDay = Utils.getJulianFirstDayFromMonth(mMonth, mYear);
        hashmap = dataFactory.getData(mFirstJulianDay, false);
        onUpdateData(hashmap);
        hashmap.registerListener(mFirstJulianDay, new _cls1());
        if (isTabletConfig)
        {
            hashmap = getResources();
            if (mShowWeekNumbers)
            {
                interDayPadding = hashmap.getDimensionPixelOffset(0x7f0e00c9);
                mEdgePadding = (mWidth - (DAY_SELECTED_CIRCLE_SIZE << 1) * mNumDays - interDayPadding * (mNumDays - 1) - mWeekNumsPadding - mWeekNumsColWidth) / 2;
                return;
            } else
            {
                interDayPadding = hashmap.getDimensionPixelOffset(0x7f0e00c8);
                mEdgePadding = (mWidth - (DAY_SELECTED_CIRCLE_SIZE << 1) * mNumDays - interDayPadding * (mNumDays - 1)) / 2;
                return;
            }
        }
        int l = mNumDays;
        int k = mWidth - mEdgePadding * 2 - (DAY_SELECTED_CIRCLE_SIZE << 1) * mNumDays;
        int j = k;
        int i = l;
        if (mShowWeekNumbers)
        {
            i = l + 1;
            j = k - mWeekNumsColWidth;
        }
        i = j / (i - 1);
        interDayPadding = i;
        mWeekNumsPadding = i;
    }

    private class TimelyMonthViewTouchHelper extends com.android.datetimepicker.date.MonthView.MonthViewTouchHelper
    {

        private final TimelyColorMonthView this$0;

        protected final void getItemBounds(int i, Rect rect)
        {
            int k1 = getInternalStartPadding();
            int l1 = interDayPadding / 2;
            int l = mRowHeight;
            int i1 = DAY_SELECTED_CIRCLE_SIZE;
            int j1 = mRowHeight / 2;
            int j = mRowHeight;
            int k = DAY_SELECTED_CIRCLE_SIZE * 2 + interDayPadding;
            int i2 = (i - 1) + findDayOffset();
            i = i2 / mNumDays;
            k1 = (k1 - l1) + getRtlCompatibleColumnIndex(i2 % mNumDays) * k;
            i = ((l + i1) - j1) + i * j;
            rect.set(k1, i, k + k1, j + i);
        }

        protected final CharSequence getItemDescription(int i)
        {
            Object obj;
            Resources resources;
            StringBuilder stringbuilder;
            Object obj1;
            obj = time;
            int j = mMonth;
            int l = mYear;
            ((Time) (obj)).writeFieldsToImpl();
            ((Time) (obj)).impl.set(i, j, l);
            ((Time) (obj)).copyFieldsFromImpl();
            obj = time;
            ((Time) (obj)).writeFieldsToImpl();
            ((Time) (obj)).impl.normalize(false);
            ((Time) (obj)).copyFieldsFromImpl();
            if (time.allDay)
            {
                obj = time;
                Time time1 = time;
                time.second = 0;
                time1.minute = 0;
                obj.hour = 0;
            }
            obj = time;
            ((Time) (obj)).writeFieldsToImpl();
            long l3 = ((Time) (obj)).impl.toMillis(true);
            obj = new StringBuilder();
            if (mHasToday && i == mToday)
            {
                ((StringBuilder) (obj)).append(getContext().getString(0x7f13048b));
                ((StringBuilder) (obj)).append(": ");
            }
            ((StringBuilder) (obj)).append(DateFormat.format("EEEE dd MMMM yyyy", l3));
            j = PreferencesConstants.getAlternateCalendarPref(getContext());
            if (j != 0)
            {
                i = Utils.getJulianDay(mYear, mMonth, i);
                ((StringBuilder) (obj)).append(", ");
                ((StringBuilder) (obj)).append(AlternateCalendarUtils.getAlternateFullDate(i, getResources(), j));
            }
            ((StringBuilder) (obj)).append(". ");
            i = android.text.format.Time.getJulianDay(l3, time.gmtoff);
            obj1 = dataFactory.getData(i, false);
            resources = getResources();
            stringbuilder = new StringBuilder();
            if (obj1 == null || !((MonthData) (obj1)).isDataReady()) goto _L2; else goto _L1
_L1:
            int l1;
            int i2;
            int j2;
            int k2;
            int l2;
            obj1 = ((MonthData) (obj1)).getItems(i);
            if (obj1 != null && !((List) (obj1)).isEmpty())
            {
                obj1 = ((List) (obj1)).iterator();
                int k1 = 0;
                i = 0;
                int j1 = 0;
                int i1 = 0;
                int k = 0;
                do
                {
                    l2 = k1;
                    k2 = i;
                    j2 = j1;
                    i2 = i1;
                    l1 = k;
                    if (!((Iterator) (obj1)).hasNext())
                    {
                        break;
                    }
                    TimelineItem timelineitem = (TimelineItem)((Iterator) (obj1)).next();
                    if (timelineitem instanceof TimelineBirthday)
                    {
                        j1++;
                    } else
                    if (timelineitem instanceof TimelineHoliday)
                    {
                        i1++;
                    } else
                    if (timelineitem instanceof TimelineTask)
                    {
                        i++;
                    } else
                    if (timelineitem instanceof TimelineTaskBundle)
                    {
                        i = ((TimelineTaskBundle)timelineitem).timelineTaskList.size() + i;
                    } else
                    if (timelineitem instanceof TimelineGroove)
                    {
                        k1++;
                    } else
                    {
                        k++;
                    }
                } while (true);
            } else
            {
                l2 = 0;
                k2 = 0;
                j2 = 0;
                i2 = 0;
                l1 = 0;
            }
            if (j2 + i2 + k2 + l2 + l1 != 0) goto _L4; else goto _L3
_L3:
            stringbuilder.append(resources.getString(0x7f13034f));
_L2:
            ((StringBuilder) (obj)).append(stringbuilder.toString());
            ((StringBuilder) (obj)).append(getContext().getString(0x7f1300e7));
            ((StringBuilder) (obj)).append(". ");
            return ((StringBuilder) (obj)).toString();
_L4:
            if (j2 > 0)
            {
                stringbuilder.append(resources.getQuantityString(0x7f12002f, j2, new Object[] {
                    Integer.valueOf(j2)
                }));
                stringbuilder.append(", ");
            }
            if (i2 > 0)
            {
                stringbuilder.append(resources.getQuantityString(0x7f120030, i2, new Object[] {
                    Integer.valueOf(i2)
                }));
                stringbuilder.append(", ");
            }
            if (k2 > 0)
            {
                stringbuilder.append(resources.getQuantityString(0x7f120031, k2, new Object[] {
                    Integer.valueOf(k2)
                }));
                stringbuilder.append(", ");
            }
            if (l2 > 0)
            {
                stringbuilder.append(resources.getQuantityString(0x7f12001e, l2, new Object[] {
                    Integer.valueOf(l2)
                }));
                stringbuilder.append(", ");
            }
            if (l1 > 0)
            {
                stringbuilder.append(resources.getQuantityString(0x7f120014, l1, new Object[] {
                    Integer.valueOf(l1)
                }));
                stringbuilder.append(", ");
            }
            if (true) goto _L2; else goto _L5
_L5:
        }

        public TimelyMonthViewTouchHelper(View view)
        {
            this$0 = TimelyColorMonthView.this;
            super(TimelyColorMonthView.this, view);
        }
    }


    private class _cls1
        implements RangedData.OnUpdateListener
    {

        public final TimelyColorMonthView this$0;

        public final int getListenerTag()
        {
            return listenerTag;
        }

        public final int getListenerTagType()
        {
            return 7;
        }

        public final void postUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NKQRREEHK48OBKC4TKIMICCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FA9GMSPR5CH262T314HAN0P31EHIKCQBED5PMGPB49HKN6T35DPIN4EP9AO______0(MonthData monthdata, int i, RangedData.UpdateFinishedListener updatefinishedlistener)
        {
            class .Lambda._cls0
                implements Runnable
            {

                private final _cls1 arg$1;
                private final int arg$2;
                private final MonthData arg$3;
                private final RangedData.UpdateFinishedListener arg$4;

                public final void run()
                {
                    _cls1 _lcls1 = arg$1;
                    int j = arg$2;
                    MonthData monthdata1 = arg$3;
                    RangedData.UpdateFinishedListener updatefinishedlistener1 = arg$4;
                    if (j == _lcls1._fld0.mFirstJulianDay)
                    {
                        _lcls1._fld0.onUpdateData(monthdata1);
                        _lcls1._fld0.invalidate();
                    }
                    updatefinishedlistener1.notifyUpdateFinished();
                }

                .Lambda._cls0(int i, MonthData monthdata, RangedData.UpdateFinishedListener updatefinishedlistener)
                {
                    arg$1 = _cls1.this;
                    arg$2 = i;
                    arg$3 = monthdata;
                    arg$4 = updatefinishedlistener;
                }
            }

            monthdata = (FluentFuture)CalendarExecutor.MAIN.submit(new .Lambda._cls0(i, monthdata, updatefinishedlistener));
        }

        public final void setListenerTag(int i)
        {
            listenerTag = i;
        }

        _cls1()
        {
            this$0 = TimelyColorMonthView.this;
            super();
        }
    }

}
