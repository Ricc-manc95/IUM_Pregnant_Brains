// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.alternatecalendar.AlternateCalendarHelper;
import com.google.android.calendar.material.Material;
import com.google.common.base.Optional;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolderImpl, ViewLayoutParams

public final class DayHeaderViewHolder extends TimelineAdapterViewHolderImpl
{

    private final SimpleDateFormat a11yFullDateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.getDefault());
    private final AlternateCalendarHelper alternateCalendarHelper;
    private final Calendar calendar = Calendar.getInstance();
    public final com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderClickListener clickListener;
    public final Context context;
    private final ObservableReference currentJulianDay;
    private final SimpleDateFormat dayOfWeekFormat = new SimpleDateFormat("EEE", Locale.getDefault());
    private final HeaderDrawable drawable;
    private final ObservableReference isA11yEnabled;
    private int lastJulianDay;
    private final com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderNextModeSupplier nextModeSupplier;
    public int position;
    private final Typeface robotoMediumTypeface;
    private final SimpleDateFormat shortDayOfWeekFormat = new SimpleDateFormat("EEEEE", Locale.getDefault());
    private final TimeUtils timeUtils;
    private ViewMode viewMode;

    public DayHeaderViewHolder(Context context1, ObservableReference observablereference, HeaderDrawable headerdrawable, TimeUtils timeutils, ObservableReference observablereference1, com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderClickListener dayheaderclicklistener, com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.DayHeaderNextModeSupplier dayheadernextmodesupplier, 
            ObservableReference observablereference2, AlternateCalendarHelper alternatecalendarhelper)
    {
        super(new View(context1));
        context = context1;
        isA11yEnabled = observablereference;
        timeUtils = timeutils;
        clickListener = dayheaderclicklistener;
        nextModeSupplier = dayheadernextmodesupplier;
        currentJulianDay = observablereference2;
        alternateCalendarHelper = alternatecalendarhelper;
        dayOfWeekFormat.setTimeZone((TimeZone)timeutils.timeZone.get());
        shortDayOfWeekFormat.setTimeZone((TimeZone)timeutils.timeZone.get());
        a11yFullDateFormat.setTimeZone((TimeZone)timeutils.timeZone.get());
        drawable = headerdrawable;
        itemView.setBackground(headerdrawable);
        class .Lambda._cls0
            implements Consumer
        {

            private final DayHeaderViewHolder arg$1;

            public final void accept(Object obj)
            {
                obj = arg$1;
                ((DayHeaderViewHolder) (obj)).bind(((DayHeaderViewHolder) (obj)).position);
                ((DayHeaderViewHolder) (obj)).itemView.invalidate();
            }

            .Lambda._cls0()
            {
                arg$1 = DayHeaderViewHolder.this;
            }
        }

        if (Material.robotoMedium != null)
        {
            context1 = Material.robotoMedium;
        } else
        {
            context1 = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = context1;
        }
        robotoMediumTypeface = context1;
        context1 = itemView;
        context1.addOnAttachStateChangeListener(new com.google.android.apps.calendar.util.android.Views._cls1(context1, observablereference1, new .Lambda._cls0(), CalendarExecutor.MAIN, false));
    }

    public final void bind(int i)
    {
        position = i;
        if (viewMode != null)
        {
            i = i - CalendarViewType.DAY_HEADER.minPosition - 100;
            class .Lambda._cls1
                implements android.view.View.OnClickListener
            {

                private final DayHeaderViewHolder arg$1;
                private final int arg$2;

                public final void onClick(View view)
                {
                    view = arg$1;
                    int k = arg$2;
                    ((DayHeaderViewHolder) (view)).clickListener.onClick(k);
                }

            .Lambda._cls1(int i)
            {
                arg$1 = DayHeaderViewHolder.this;
                arg$2 = i;
            }
            }

            itemView.setOnClickListener(new .Lambda._cls1(i));
            calendar.setTimeZone((TimeZone)timeUtils.timeZone.get());
            calendar.setTimeInMillis(timeUtils.julianDateToMs(i) + TimeUnit.HOURS.toMillis(12L));
            int j = ((Integer)currentJulianDay.get()).intValue();
            Object obj;
            HeaderDrawable headerdrawable;
            if (i < j)
            {
                drawable.dayOfWeekPaint.setColor(drawable.pastColor);
                drawable.dayOfWeekPaint.setTypeface(Typeface.DEFAULT);
                drawable.dayOfMonthPaint.setColor(drawable.pastColor);
            } else
            if (i == j)
            {
                drawable.dayOfWeekPaint.setColor(drawable.currentColor);
                drawable.dayOfWeekPaint.setTypeface(robotoMediumTypeface);
                drawable.dayOfMonthPaint.setColor(drawable.currentColor);
            } else
            {
                drawable.dayOfWeekPaint.setColor(drawable.futureColor);
                drawable.dayOfWeekPaint.setTypeface(Typeface.DEFAULT);
                drawable.dayOfMonthPaint.setColor(drawable.futureColor);
            }
            drawable.dayOfMonthText = String.format(Locale.getDefault(), "%d", new Object[] {
                Integer.valueOf(calendar.get(5))
            });
            headerdrawable = drawable;
            obj = calendar;
            if (alternateCalendarHelper.isEnabled())
            {
                j = timeUtils.msToJulianDate(((Calendar) (obj)).getTimeInMillis());
                obj = shortDayOfWeekFormat.format(((Calendar) (obj)).getTime());
                String s = alternateCalendarHelper.getDayOfMonth(j);
                obj = (new StringBuilder(String.valueOf(obj).length() + 2 + String.valueOf(s).length())).append(((String) (obj))).append("(").append(s).append(")").toString();
            } else
            {
                obj = dayOfWeekFormat.format(((Calendar) (obj)).getTime());
            }
            headerdrawable.dayOfWeekText = ((String) (obj));
            if (((Boolean)isA11yEnabled.get()).booleanValue())
            {
                obj = new StringBuilder();
                ((StringBuilder) (obj)).append(a11yFullDateFormat.format(calendar.getTime()));
                if (alternateCalendarHelper.isEnabled())
                {
                    ((StringBuilder) (obj)).append(", ");
                    ((StringBuilder) (obj)).append(alternateCalendarHelper.getMonthAndDay(i));
                }
                Object obj1 = nextModeSupplier.nextViewMode();
                class .Lambda._cls2
                    implements Consumer
                {

                    private final DayHeaderViewHolder arg$1;
                    private final StringBuilder arg$2;

                    public final void accept(Object obj2)
                    {
                        DayHeaderViewHolder dayheaderviewholder = arg$1;
                        StringBuilder stringbuilder = arg$2;
                        obj2 = (ViewMode)obj2;
                        if (obj2 == ViewMode.SCHEDULE)
                        {
                            stringbuilder.append(", ").append(dayheaderviewholder.context.getResources().getString(0x7f130071));
                        } else
                        if (obj2 == ViewMode.ONE_DAY_GRID)
                        {
                            stringbuilder.append(", ").append(dayheaderviewholder.context.getResources().getString(0x7f130073));
                            return;
                        }
                    }

            .Lambda._cls2(StringBuilder stringbuilder)
            {
                arg$1 = DayHeaderViewHolder.this;
                arg$2 = stringbuilder;
            }
                }

                .Lambda._cls2 _lcls2 = new .Lambda._cls2(((StringBuilder) (obj)));
                Runnable runnable = com.google.android.apps.calendar.util.Optionals..Lambda._cls0.$instance;
                obj1 = ((Optional) (obj1)).orNull();
                if (obj1 != null)
                {
                    _lcls2.accept(obj1);
                } else
                {
                    runnable.run();
                }
                itemView.setContentDescription(((StringBuilder) (obj)).toString());
                return;
            }
        }
    }

    public final void onLayoutUpdate(ViewLayoutParams viewlayoutparams)
    {
        if (viewMode != viewlayoutparams.getViewMode())
        {
            viewMode = viewlayoutparams.getViewMode();
            bind(position);
        } else
        if (lastJulianDay != ((Integer)currentJulianDay.get()).intValue())
        {
            lastJulianDay = ((Integer)currentJulianDay.get()).intValue();
            bind(position);
            itemView.invalidate();
            return;
        }
    }

    private class HeaderDrawable extends Drawable
    {

        public final int currentColor;
        private final float dayOfMonthHeightPx;
        public final Paint dayOfMonthPaint;
        public String dayOfMonthText;
        private final float dayOfWeekHeightPx;
        public final Paint dayOfWeekPaint;
        public String dayOfWeekText;
        private final float dayPaddingPx;
        public final int futureColor;
        private final ObservableReference isRtl;
        public final int pastColor;

        public final void draw(Canvas canvas)
        {
            float f1 = 0.0F;
            Rect rect = getBounds();
            boolean flag = ((Boolean)isRtl.get()).booleanValue();
            Paint paint = dayOfWeekPaint;
            float f;
            Object obj;
            if (flag)
            {
                obj = android.graphics.Paint.Align.RIGHT;
            } else
            {
                obj = android.graphics.Paint.Align.LEFT;
            }
            paint.setTextAlign(((android.graphics.Paint.Align) (obj)));
            paint = dayOfMonthPaint;
            if (flag)
            {
                obj = android.graphics.Paint.Align.RIGHT;
            } else
            {
                obj = android.graphics.Paint.Align.LEFT;
            }
            paint.setTextAlign(((android.graphics.Paint.Align) (obj)));
            obj = dayOfWeekText;
            if (flag)
            {
                f = rect.width();
            } else
            {
                f = 0.0F;
            }
            canvas.drawText(((String) (obj)), f, dayOfMonthHeightPx + dayPaddingPx + dayOfWeekHeightPx, dayOfWeekPaint);
            obj = dayOfMonthText;
            f = f1;
            if (flag)
            {
                f = rect.width();
            }
            canvas.drawText(((String) (obj)), f, dayOfMonthHeightPx, dayOfMonthPaint);
        }

        public final int getOpacity()
        {
            return -1;
        }

        public final void setAlpha(int i)
        {
        }

        public final void setColorFilter(ColorFilter colorfilter)
        {
        }

        HeaderDrawable(Context context1, DimensConverter dimensconverter, ObservableReference observablereference, ObservableReference observablereference1, AlternateCalendarHelper alternatecalendarhelper)
        {
            dayOfWeekPaint = new Paint();
            dayOfMonthPaint = new Paint();
            isRtl = observablereference;
            pastColor = ContextCompat.getColor(context1, 0x7f0d030c);
            currentColor = ContextCompat.getColor(context1, 0x7f0d0310);
            futureColor = ContextCompat.getColor(context1, 0x7f0d030f);
            if (!alternatecalendarhelper.isEnabled()) goto _L2; else goto _L1
_L1:
            float f;
            boolean flag;
            if ((ScreenType)observablereference1.get() == ScreenType.PHONE)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag) goto _L2; else goto _L3
_L3:
            f = 11F;
_L5:
            f = dimensconverter.spToPx(f);
            dayOfWeekPaint.setTextSize(f);
            dayOfWeekHeightPx = f - dayOfWeekPaint.getFontMetrics().descent;
            dayOfWeekPaint.setAntiAlias(true);
            f = dimensconverter.spToPx(28F);
            dayOfMonthPaint.setTextSize(f);
            dayOfMonthHeightPx = f - dayOfMonthPaint.getFontMetrics().descent;
            dayOfMonthPaint.setAntiAlias(true);
            dayPaddingPx = dimensconverter.dpToPx(6F);
            return;
_L2:
            f = 14F;
            if (true) goto _L5; else goto _L4
_L4:
        }
    }

}
