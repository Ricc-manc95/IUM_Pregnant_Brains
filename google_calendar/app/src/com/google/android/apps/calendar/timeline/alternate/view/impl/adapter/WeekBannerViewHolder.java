// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolderImpl

final class WeekBannerViewHolder extends TimelineAdapterViewHolderImpl
{

    public int julianDate;
    private final ScheduleProvider scheduleProvider;
    private final ObservableReference shouldShowWeekNumbers;
    private final TimeUtils timeUtils;
    private final TextView view;

    WeekBannerViewHolder(Context context, TimeUtils timeutils, ScheduleProvider scheduleprovider, ObservableReference observablereference, Runnable runnable)
    {
        super(new TextView(context));
        timeUtils = timeutils;
        scheduleProvider = scheduleprovider;
        shouldShowWeekNumbers = observablereference;
        view = (TextView)itemView;
        view.setGravity(0x800013);
        view.setTextAlignment(5);
        view.setTextColor(ContextCompat.getColor(context, 0x7f0d021a));
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final Runnable arg$1;

            public final void onClick(View view1)
            {
                WeekBannerViewHolder.lambda$new$0$WeekBannerViewHolder$5166KOBMC4NMOOBECSNL4TBEDPGM4R357D662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0(arg$1);
            }

            .Lambda._cls0(Runnable runnable)
            {
                arg$1 = runnable;
            }
        }

        view.setOnClickListener(new .Lambda._cls0(runnable));
        context = view;
        class .Lambda._cls1
            implements Consumer
        {

            private final WeekBannerViewHolder arg$1;

            public final void accept(Object obj)
            {
                obj = arg$1;
                ((WeekBannerViewHolder) (obj)).bind(((WeekBannerViewHolder) (obj)).julianDate);
            }

            .Lambda._cls1()
            {
                arg$1 = WeekBannerViewHolder.this;
            }
        }

        context.addOnAttachStateChangeListener(new com.google.android.apps.calendar.util.android.Views._cls1(context, observablereference, new .Lambda._cls1(), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), false));
    }

    static final void lambda$new$0$WeekBannerViewHolder$5166KOBMC4NMOOBECSNL4TBEDPGM4R357D662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0(Runnable runnable)
    {
        runnable.run();
    }

    final void bind(int i)
    {
        julianDate = i;
        Object obj;
        if (((Boolean)shouldShowWeekNumbers.get()).booleanValue())
        {
            obj = Integer.valueOf(timeUtils.getCalendarFieldForJulianDay(i, 3));
        } else
        {
            obj = null;
        }
        obj = scheduleProvider.getWeekText(timeUtils.julianDateToDateInfo(i), ((Integer) (obj)));
        view.setText(((CharSequence) (obj)));
        view.setContentDescription(((CharSequence) (obj)));
    }
}
