// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeekHeaderLabelsView extends View
{

    private static final Typeface ROBOTO_REGULAR = Typeface.create("sans-serif", 0);
    private int alternateCalendarPref;
    private int countOfDays;
    private final int dayHeaderFutureColor;
    private final int dayHeaderPastColor;
    private String dayNumberLabel[];
    private String dayWeekLabel[];
    private int firstJulianDay;
    private final int gridlineStrokeWidth;
    private final int headerDayNumberBottomMargin;
    private final int headerDayWeekBottomMargin;
    private final int headerLabelColor;
    private final int headerLabelLeftMargin;
    private final int headerNumberSize;
    private int headerTextSize;
    private final boolean isRtl;
    private final int lineColor;
    private final Paint paint = new Paint();
    private final Typeface robotoMediumTypeface;
    private final int todayColor;
    private int todayIndex;

    public WeekHeaderLabelsView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        alternateCalendarPref = 0;
        isRtl = RtlUtils.isLayoutDirectionRtl(context);
        Resources resources = context.getResources();
        headerNumberSize = resources.getDimensionPixelSize(0x7f0e03e0);
        headerLabelColor = resources.getColor(0x7f0d004c);
        int i = resources.getDimensionPixelOffset(0x7f0e03e3);
        headerDayNumberBottomMargin = resources.getDimensionPixelOffset(0x7f0e03e1);
        headerDayWeekBottomMargin = resources.getDimensionPixelOffset(0x7f0e03e2);
        gridlineStrokeWidth = resources.getDimensionPixelOffset(0x7f0e01dc);
        dayHeaderPastColor = resources.getColor(0x7f0d032c);
        dayHeaderFutureColor = resources.getColor(0x7f0d032b);
        todayColor = resources.getColor(0x7f0d01d7);
        lineColor = resources.getColor(0x7f0d011a);
        context = context.obtainStyledAttributes(attributeset, com.google.android.calendar.R.styleable.AllDayHeaderView);
        if (context != null)
        {
            countOfDays = context.getInteger(com.google.android.calendar.R.styleable.AllDayHeaderView_count_days, 7);
            headerLabelLeftMargin = context.getDimensionPixelOffset(com.google.android.calendar.R.styleable.AllDayHeaderView_label_marginStart, i);
            context.recycle();
        } else
        {
            countOfDays = 7;
            headerLabelLeftMargin = i;
        }
        paint.setTypeface(ROBOTO_REGULAR);
        attributeset = paint;
        if (isRtl)
        {
            context = android.graphics.Paint.Align.RIGHT;
        } else
        {
            context = android.graphics.Paint.Align.LEFT;
        }
        attributeset.setTextAlign(context);
        todayIndex = -1;
        if (Material.robotoMedium != null)
        {
            context = Material.robotoMedium;
        } else
        {
            context = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = context;
        }
        robotoMediumTypeface = context;
        firstJulianDay = 0x80000000;
    }

    private final void updateHeaderTextSize()
    {
        headerTextSize = getResources().getDimensionPixelSize(0x7f0e03de);
        if (!AlternateCalendarUtils.isAlternateCalendarEnabled(getContext())) goto _L2; else goto _L1
_L1:
        float f;
        int i;
        f = (float)getWidth() / (float)countOfDays;
        i = 0;
_L7:
        if (i >= dayWeekLabel.length) goto _L2; else goto _L3
_L3:
        paint.setTextSize(headerTextSize);
        if (paint.measureText(dayWeekLabel[i]) <= f - (float)(headerLabelLeftMargin * 2)) goto _L5; else goto _L4
_L4:
        headerTextSize = getResources().getDimensionPixelSize(0x7f0e03df);
_L2:
        return;
_L5:
        i++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    protected void onDraw(Canvas canvas)
    {
        int j = getHeight();
        float f = (float)getWidth() / (float)countOfDays;
        if (dayWeekLabel != null)
        {
            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setColor(headerLabelColor);
            paint.setAntiAlias(true);
            float f1;
            int i;
            if (isRtl)
            {
                i = canvas.getWidth() - headerLabelLeftMargin;
            } else
            {
                i = headerLabelLeftMargin;
            }
            f1 = i;
            i = 0;
            while (i < countOfDays) 
            {
                float f3;
                if (i < todayIndex)
                {
                    paint.setColor(dayHeaderPastColor);
                } else
                if (i == todayIndex)
                {
                    paint.setColor(todayColor);
                } else
                {
                    paint.setColor(dayHeaderFutureColor);
                }
                paint.setTextSize(headerNumberSize);
                canvas.drawText(dayNumberLabel[i], f1, headerDayNumberBottomMargin, paint);
                if (todayIndex == i)
                {
                    paint.setTypeface(robotoMediumTypeface);
                }
                paint.setTextSize(headerTextSize);
                canvas.drawText(dayWeekLabel[i], f1, headerDayWeekBottomMargin, paint);
                if (todayIndex == i)
                {
                    paint.setTypeface(ROBOTO_REGULAR);
                }
                if (isRtl)
                {
                    f3 = -f;
                } else
                {
                    f3 = f;
                }
                f1 += f3;
                i++;
            }
        }
        paint.setStrokeWidth(gridlineStrokeWidth);
        paint.setColor(lineColor);
        paint.setAntiAlias(false);
        float f4 = f;
        for (float f2 = 1.0F; f2 < (float)countOfDays; f2++)
        {
            canvas.drawLine(f4, 0.0F, f4, j, paint);
            f4 += f;
        }

    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        updateHeaderTextSize();
    }

    public void setFirstJulianDay(int i)
    {
        int k = Utils.getTodayJulianDay(getContext()) - i;
        Resources resources = getContext().getResources();
        int j = PreferencesConstants.getAlternateCalendarPref(getContext());
        Time time;
        if (alternateCalendarPref != j)
        {
            alternateCalendarPref = j;
            j = 1;
        } else
        {
            j = 0;
        }
        if (k == todayIndex && i == firstJulianDay && j == 0)
        {
            return;
        }
        time = new Time();
        if (i != firstJulianDay || j != 0)
        {
            firstJulianDay = i;
            if (dayWeekLabel == null)
            {
                dayWeekLabel = new String[countOfDays];
                dayNumberLabel = new String[countOfDays];
            }
            SimpleDateFormat simpledateformat = new SimpleDateFormat("E", Locale.getDefault());
            SimpleDateFormat simpledateformat1 = new SimpleDateFormat("d", Locale.getDefault());
            Date date = new Date();
            j = 0;
            while (j < countOfDays) 
            {
                int l = firstJulianDay + j;
                time.setJulianDaySafe(l);
                time.writeFieldsToImpl();
                date.setTime(time.impl.toMillis(false));
                if (alternateCalendarPref != 0)
                {
                    dayWeekLabel[j] = (new StringBuilder()).append(Utils.getShortDayOfWeek(date)).append("(").append(AlternateCalendarUtils.getAlternateDayOfMonthString(l, resources, alternateCalendarPref)).append(")").toString();
                } else
                {
                    dayWeekLabel[j] = simpledateformat.format(date);
                }
                dayNumberLabel[j] = simpledateformat1.format(date);
                j++;
            }
        }
        updateHeaderTextSize();
        if (k != todayIndex)
        {
            todayIndex = k;
        }
        time.setJulianDaySafe(i);
        if (countOfDays == 1)
        {
            time.writeFieldsToImpl();
            setContentDescription(DateFormat.format("EEEE dd MMMM yyyy", time.impl.toMillis(false)));
        } else
        {
            StringBuilder stringbuilder;
            DateTimeFormatHelper datetimeformathelper;
            if (CalendarProperties.getBooleanProperty(7).booleanValue())
            {
                i = com.android.datetimepicker.Utils.getWeekNumberInYear(firstJulianDay, Utils.getFirstDayOfWeekAsTime(getContext()));
            } else
            {
                i = -1;
            }
            stringbuilder = new StringBuilder();
            datetimeformathelper = DateTimeFormatHelper.instance;
            if (datetimeformathelper == null)
            {
                throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
            }
            stringbuilder.append(((DateTimeFormatHelper)datetimeformathelper).getWeekRangeText(new int[] {
                time.year, time.month, time.monthDay
            }, true, i));
            if (AlternateCalendarUtils.isAlternateCalendarEnabled(getContext()))
            {
                stringbuilder.append(", ");
                i = Utils.getJulianDay(time.year, time.month, time.monthDay);
                stringbuilder.append(AlternateCalendarUtils.getAlternateAccessibilityDateRange(i, (i + 7) - 1, getResources(), PreferencesConstants.getAlternateCalendarPref(getContext())));
            }
            setContentDescription(stringbuilder.toString());
        }
        invalidate();
    }

}
