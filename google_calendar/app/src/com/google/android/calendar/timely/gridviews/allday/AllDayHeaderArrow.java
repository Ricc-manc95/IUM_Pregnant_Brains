// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.calendar.Utils;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AllDayHeaderArrow extends FrameLayout
    implements OnPropertyChangedListener
{

    private final int arrowBottomMargin;
    public int arrowState;
    private ImageView arrowView;
    private final int arrowWidth;
    private final int gridlineStrokeWidth;
    private final int headerDayWeekBottom;
    private String monthLabel;
    private final int monthLabelColor;
    private final int monthLabelSize;
    private final Paint paint = new Paint();
    private final boolean shouldDrawMonthLabel;
    private boolean shouldDrawWeekNumber;
    private final int weekInYearBottom;
    private final int weekInYearLabelColor;
    private final int weekInYearLabelSize;
    private String weekLabel;

    public AllDayHeaderArrow(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        Resources resources = context.getResources();
        gridlineStrokeWidth = resources.getDimensionPixelOffset(0x7f0e01dc);
        headerDayWeekBottom = resources.getDimensionPixelOffset(0x7f0e03e2);
        weekInYearBottom = resources.getDimensionPixelOffset(0x7f0e03e6);
        arrowWidth = resources.getDimensionPixelSize(0x7f0e0052);
        arrowBottomMargin = resources.getDimensionPixelSize(0x7f0e03dc);
        monthLabelColor = resources.getColor(0x7f0d032e);
        weekInYearLabelColor = resources.getColor(0x7f0d032d);
        monthLabelSize = resources.getDimensionPixelSize(0x7f0e03e8);
        weekInYearLabelSize = resources.getDimensionPixelSize(0x7f0e03e7);
        context = context.obtainStyledAttributes(attributeset, com.google.android.calendar.R.styleable.AllDayHeaderArrow);
        if (context != null)
        {
            shouldDrawMonthLabel = context.getBoolean(com.google.android.calendar.R.styleable.AllDayHeaderArrow_draw_month_label, false);
            context.recycle();
        } else
        {
            shouldDrawMonthLabel = false;
        }
        context = CalendarProperties.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        shouldDrawWeekNumber = ((Boolean)((CalendarProperties)context).getPropertyValue(7)).booleanValue();
        paint.setStyle(android.graphics.Paint.Style.FILL);
        paint.setStrokeWidth(gridlineStrokeWidth);
        paint.setTextAlign(android.graphics.Paint.Align.CENTER);
        paint.setAntiAlias(true);
        attributeset = paint;
        if (Material.robotoMedium != null)
        {
            context = Material.robotoMedium;
        } else
        {
            context = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = context;
        }
        attributeset.setTypeface(context);
        setImportantForAccessibility(2);
    }

    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        if (shouldDrawMonthLabel && monthLabel != null)
        {
            paint.setColor(monthLabelColor);
            paint.setTextSize(monthLabelSize);
            canvas.drawText(monthLabel, getWidth() / 2, headerDayWeekBottom, paint);
        }
        if (shouldDrawWeekNumber && weekLabel != null)
        {
            paint.setColor(weekInYearLabelColor);
            paint.setTextSize(weekInYearLabelSize);
            canvas.drawText(weekLabel, getWidth() / 2, weekInYearBottom, paint);
        }
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).registerListener(7, this);
            return;
        }
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        if (i == 7)
        {
            shouldDrawWeekNumber = ((Boolean)obj).booleanValue();
            invalidate();
        }
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).unregisterListener(7, this);
            return;
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        arrowView = (ImageView)findViewById(0x7f100007);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        j = (k - i - arrowWidth) / 2;
        arrowView.layout(i + j, l - arrowBottomMargin - arrowWidth, k - j, l - arrowBottomMargin);
    }

    public void setJulianDay(int i)
    {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("MMM", Locale.getDefault());
        Time time = new Time();
        time.setJulianDaySafe(i);
        Date date = new Date();
        time.writeFieldsToImpl();
        date.setTime(time.impl.toMillis(false));
        monthLabel = simpledateformat.format(date);
        i = com.android.datetimepicker.Utils.getWeekNumberInYear(i, Utils.getFirstDayOfWeekAsTime(getContext()));
        weekLabel = NumberFormat.getNumberInstance().format(i);
        if (shouldDrawMonthLabel)
        {
            weekLabel = getResources().getString(0x7f1304be, new Object[] {
                weekLabel
            });
        }
        invalidate();
    }

    public void setState(int i)
    {
        arrowState = i;
        ImageView imageview = arrowView;
        if (arrowState == 0)
        {
            i = 4;
        } else
        {
            i = 0;
        }
        imageview.setVisibility(i);
        if (arrowState == 2)
        {
            arrowView.setRotation(0.0F);
        } else
        if (arrowState == 1)
        {
            arrowView.setRotation(180F);
            return;
        }
    }
}
