// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Trace;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimelyDayHeaderView extends View
    implements TimelineRecyclerView.HeaderViewDescriptor
{

    private static final Typeface ROBOTO_REGULAR = Typeface.create("sans-serif", 0);
    private final String agendaViewText;
    private Time currentTime;
    private CharSequence dateContentDescription;
    private int dateInfo[];
    private final int dayHeaderHeight;
    private final int dayHeaderWidth;
    private String dayOfMonthString;
    private int dayOfMonthTextSize;
    private int dayOfMonthYPos;
    public int dayOfWeek;
    private String dayOfWeekString;
    private int dayOfWeekTextSize;
    private int dayOfWeekYPos;
    public final int defaultMargin;
    public Time displayedTime;
    private final int futureTextColor;
    private final String hourViewText;
    public boolean isFirstDayOfMonth;
    public boolean isFirstDayOfWeek;
    private boolean isInAgendaView;
    private boolean isPast;
    private final boolean isRtl;
    private final boolean isTabletConfig;
    public boolean isToday;
    private final SimpleDateFormat month;
    public int monthHeaderSize;
    private String monthString;
    private final Paint paint;
    private final int pastTextColor;
    public int position;
    public Calendar recycleCalendar;
    public boolean shouldDrawMonthText;
    public boolean showMonthHeaderImages;
    private final int startPadding;
    private final int todayTextColor;
    public final int weekHeaderPadding;
    public final int weekHeaderSize;
    private final SimpleDateFormat weekday;

    public TimelyDayHeaderView(Context context)
    {
        this(context, null);
    }

    public TimelyDayHeaderView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        paint = new Paint();
        weekday = new SimpleDateFormat("E", Locale.getDefault());
        month = new SimpleDateFormat("MMM", Locale.getDefault());
        isPast = false;
        isToday = false;
        shouldDrawMonthText = false;
        currentTime = new Time();
        displayedTime = new Time();
        isRtl = RtlUtils.isLayoutDirectionRtl(context);
        isTabletConfig = context.getResources().getBoolean(0x7f0c0016);
        recycleCalendar = Calendar.getInstance();
        attributeset = Utils.getTimeZoneId(context);
        recycleCalendar.setTimeZone(TimeZone.getTimeZone(attributeset));
        weekday.setCalendar(recycleCalendar);
        month.setCalendar(recycleCalendar);
        currentTime.timezone = attributeset;
        displayedTime.timezone = attributeset;
        recycleCalendar.set(12, 0);
        recycleCalendar.set(10, 0);
        recycleCalendar.set(9, 1);
        attributeset = context.getResources();
        pastTextColor = attributeset.getColor(0x7f0d030e);
        futureTextColor = attributeset.getColor(0x7f0d030d);
        todayTextColor = attributeset.getColor(0x7f0d01d7);
        hourViewText = attributeset.getString(0x7f130073);
        agendaViewText = attributeset.getString(0x7f130071);
        dayHeaderWidth = attributeset.getDimensionPixelSize(0x7f0e03b3);
        dayHeaderHeight = attributeset.getDimensionPixelSize(0x7f0e03b0);
        startPadding = attributeset.getDimensionPixelSize(0x7f0e03b1);
        dayOfMonthTextSize = attributeset.getDimensionPixelSize(0x7f0e03b4);
        Paint paint1 = paint;
        if (isRtl)
        {
            context = android.graphics.Paint.Align.RIGHT;
        } else
        {
            context = android.graphics.Paint.Align.LEFT;
        }
        paint1.setTextAlign(context);
        paint.setAntiAlias(true);
        paint.setTypeface(ROBOTO_REGULAR);
        showMonthHeaderImages = attributeset.getBoolean(0x7f0c0015);
        weekHeaderSize = attributeset.getDimensionPixelSize(0x7f0e03aa);
        weekHeaderPadding = attributeset.getDimensionPixelOffset(0x7f0e03ac);
        defaultMargin = attributeset.getDimensionPixelSize(0x7f0e00de);
        if (showMonthHeaderImages)
        {
            monthHeaderSize = attributeset.getDimensionPixelSize(0x7f0e03a3) + (defaultMargin << 1);
        } else
        {
            monthHeaderSize = attributeset.getDimensionPixelSize(0x7f0e03a4);
        }
        setFocusable(true);
    }

    private final CharSequence getAndCacheDateContentDescription()
    {
        if (dateContentDescription == null)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(DateFormat.format("EEEE dd MMMM yyyy", recycleCalendar.getTimeInMillis()));
            int i = PreferencesConstants.getAlternateCalendarPref(getContext());
            if (i != 0)
            {
                int j = Utils.getJulianDay(recycleCalendar.get(1), recycleCalendar.get(2), recycleCalendar.get(5));
                stringbuilder.append(", ");
                stringbuilder.append(AlternateCalendarUtils.getAlternateMonthDayString(j, getResources(), i));
            }
            dateContentDescription = stringbuilder.toString();
        }
        return dateContentDescription;
    }

    public final CharSequence getAnnounceableDescription()
    {
        return getAndCacheDateContentDescription();
    }

    public CharSequence getContentDescription()
    {
        Object obj1 = getAndCacheDateContentDescription();
        Object obj = obj1;
        if (!isTabletConfig)
        {
            obj1 = String.valueOf(obj1);
            if (isInAgendaView)
            {
                obj = hourViewText;
            } else
            {
                obj = agendaViewText;
            }
            obj = (new StringBuilder(String.valueOf(obj1).length() + 2 + String.valueOf(obj).length())).append(((String) (obj1))).append(", ").append(((String) (obj))).toString();
        }
        return ((CharSequence) (obj));
    }

    public final void initializeText()
    {
        int k;
        k = 0;
        Trace.beginSection("createDateStrings");
        dayOfMonthString = String.format(Locale.getDefault(), "%d", new Object[] {
            Integer.valueOf(dateInfo[2])
        });
        monthString = month.format(recycleCalendar.getTime());
        if (!AlternateCalendarUtils.isAlternateCalendarEnabled(getContext()))
        {
            break MISSING_BLOCK_LABEL_312;
        }
        int i = Utils.getJulianDay(recycleCalendar.get(1), recycleCalendar.get(2), recycleCalendar.get(5));
        dayOfWeekString = (new StringBuilder()).append(Utils.getShortDayOfWeek(recycleCalendar.getTime())).append("(").append(AlternateCalendarUtils.getAlternateDayOfMonthString(i, getContext().getResources(), PreferencesConstants.getAlternateCalendarPref(getContext()))).append(")").toString();
_L1:
        Resources resources = getResources();
        int j;
        int l;
        int i1;
        if (AlternateCalendarUtils.isAlternateCalendarEnabled(getContext()) && !isTabletConfig)
        {
            j = 0x7f0e03b6;
        } else
        {
            j = 0x7f0e03b5;
        }
        dayOfWeekTextSize = resources.getDimensionPixelSize(j);
        j = resources.getDimensionPixelSize(0x7f0e03b2);
        l = dayOfMonthTextSize;
        i1 = dayOfWeekTextSize;
        dayOfMonthYPos = (dayHeaderHeight / 2 - (l + j + i1) / 2) + dayOfMonthTextSize;
        dayOfWeekYPos = j + dayOfMonthYPos + dayOfWeekTextSize;
        Trace.endSection();
        j = k;
        if (isFirstDayOfMonth)
        {
            j = monthHeaderSize + 0;
        }
        k = j;
        if (isFirstDayOfWeek)
        {
            Exception exception;
            if (!showMonthHeaderImages && isFirstDayOfMonth)
            {
                k = weekHeaderPadding;
            } else
            {
                k = weekHeaderSize;
            }
            k = j + k;
        }
        setTag(Integer.valueOf(k));
        invalidate();
        return;
        dayOfWeekString = weekday.format(recycleCalendar.getTime());
          goto _L1
        exception;
        Trace.endSection();
        throw exception;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        paint.setColor(-1);
        canvas.drawRect(0.0F, 0.0F, dayHeaderWidth, dayHeaderHeight, paint);
        Object obj;
        int i;
        if (isRtl)
        {
            i = canvas.getWidth() - startPadding;
        } else
        {
            i = startPadding;
        }
        if (isToday)
        {
            paint.setColor(todayTextColor);
        } else
        if (isPast)
        {
            paint.setColor(pastTextColor);
        } else
        {
            paint.setColor(futureTextColor);
        }
        paint.setTextSize(dayOfMonthTextSize);
        if (dayOfMonthString != null)
        {
            canvas.drawText(dayOfMonthString, i, dayOfMonthYPos, paint);
        }
        paint.setTextSize(dayOfWeekTextSize);
        if (isToday)
        {
            Paint paint1 = paint;
            if (Material.robotoMedium != null)
            {
                obj = Material.robotoMedium;
            } else
            {
                obj = Typeface.create("sans-serif-medium", 0);
                Material.robotoMedium = ((Typeface) (obj));
            }
            paint1.setTypeface(((Typeface) (obj)));
        }
        if (shouldDrawMonthText)
        {
            obj = monthString;
        } else
        {
            obj = dayOfWeekString;
        }
        if (obj != null)
        {
            canvas.drawText(((String) (obj)), i, dayOfWeekYPos, paint);
        }
        if (isToday)
        {
            paint.setTypeface(ROBOTO_REGULAR);
        }
    }

    protected void onMeasure(int i, int j)
    {
        setMeasuredDimension(dayHeaderWidth, dayHeaderHeight);
    }

    public final void setDateInfo(int ai[], boolean flag)
    {
        boolean flag2 = true;
        dateInfo = ai;
        boolean flag1;
        if (dateInfo != null && dateInfo[2] == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        isFirstDayOfMonth = flag1;
        isInAgendaView = flag;
        recycleCalendar.set(ai[0], ai[1], ai[2]);
        displayedTime.set(recycleCalendar.getTimeInMillis());
        updateCurrentTime();
        dayOfWeek = recycleCalendar.get(7);
        if (dayOfWeek == PreferenceUtils.getFirstDayOfWeekAsCalendar(getContext()))
        {
            flag = flag2;
        } else
        {
            flag = false;
        }
        isFirstDayOfWeek = flag;
        dateContentDescription = null;
    }

    public void setPosition(int i)
    {
        position = i;
    }

    public final void updateCurrentTime()
    {
label0:
        {
            boolean flag1 = false;
            Object obj = Utils.getTimeZoneId(getContext());
            currentTime.timezone = ((String) (obj));
            displayedTime.timezone = ((String) (obj));
            recycleCalendar.setTimeZone(TimeZone.getTimeZone(((String) (obj))));
            obj = currentTime;
            long l;
            boolean flag;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            ((Time) (obj)).set(l);
            if (currentTime.year == displayedTime.year && currentTime.month == displayedTime.month && currentTime.monthDay == displayedTime.monthDay)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            isToday = flag;
            if (currentTime.year <= displayedTime.year && (currentTime.year != displayedTime.year || currentTime.month <= displayedTime.month))
            {
                flag = flag1;
                if (currentTime.year != displayedTime.year)
                {
                    break label0;
                }
                flag = flag1;
                if (currentTime.month != displayedTime.month)
                {
                    break label0;
                }
                flag = flag1;
                if (currentTime.monthDay <= displayedTime.monthDay)
                {
                    break label0;
                }
            }
            flag = true;
        }
        isPast = flag;
    }

}
