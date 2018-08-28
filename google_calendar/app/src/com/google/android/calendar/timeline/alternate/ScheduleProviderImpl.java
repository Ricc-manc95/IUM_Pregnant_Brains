// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.content.Context;
import android.content.res.Resources;
import android.util.SparseArray;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.AlternateTimelineChipViewModelFactory;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.string.StringUtils;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.timeline.chip.ChipViewModel;
import com.google.android.calendar.timely.MonthBackgrounds;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.SoftReference;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            ChipHeights

public final class ScheduleProviderImpl
    implements ScheduleProvider
{

    private final SparseArray bannerCache = new SparseArray();
    private final ChipHeights chipHeights;
    public final Context context;
    private final DateTimeFormatHelper dateTimeFormatHelper;
    private final MonthBackgrounds monthBackgrounds;
    private final AlternateTimelineChipViewModelFactory viewModelFactory;

    ScheduleProviderImpl(Context context1, MonthBackgrounds monthbackgrounds, DateTimeFormatHelper datetimeformathelper, AlternateTimelineChipViewModelFactory alternatetimelinechipviewmodelfactory, ChipHeights chipheights)
    {
        context = context1;
        monthBackgrounds = monthbackgrounds;
        dateTimeFormatHelper = datetimeformathelper;
        viewModelFactory = alternatetimelinechipviewmodelfactory;
        chipHeights = chipheights;
    }

    public final int getBackgroundColor(int i)
    {
        MonthBackgrounds monthbackgrounds = monthBackgrounds;
        i = MonthBackgrounds.getMonthToShow$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D4III8_0(i);
        return monthbackgrounds.monthTopColorBackgrounds[i];
    }

    public final int getItemHeight(Object obj, int i)
    {
        obj = (TimeRangeEntry)obj;
        return chipHeights.getAgendaViewHeight(viewModelFactory.apply(ViewMode.SCHEDULE, ((TimeRangeEntry) (obj)), i).getChipType());
    }

    public final String getMonthText(long l)
    {
        return StringUtils.capitalizeStandalone(dateTimeFormatHelper.getTimeRangeText(l, l, 52), Locale.getDefault());
    }

    public final String getNothingPlannedText()
    {
        return context.getString(0x7f130350);
    }

    public final String getWeekText(int ai[], Integer integer)
    {
        DateTimeFormatHelper datetimeformathelper = dateTimeFormatHelper;
        int i;
        if (integer != null)
        {
            i = integer.intValue();
        } else
        {
            i = -1;
        }
        return StringUtils.capitalizeStandalone(datetimeformathelper.getWeekRangeText(ai, false, i), Locale.getDefault());
    }

    public final ListenableFuture loadBannerBitmap(int i)
    {
        i = MonthBackgrounds.getMonthToShow$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D4III8_0(i);
        i = MonthBackgrounds.MONTH_BANNER_DRAWABLE_IDS[i];
        Object obj = (SoftReference)bannerCache.get(i);
        Object obj1;
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = (ListenableFuture)((SoftReference) (obj)).get();
        }
        obj1 = obj;
        if (obj == null)
        {
            class .Lambda._cls0
                implements Callable
            {

                private final ScheduleProviderImpl arg$1;
                private final int arg$2;

                public final Object call()
                {
                    ScheduleProviderImpl scheduleproviderimpl = arg$1;
                    int j = arg$2;
                    return BitmapFactory.decodeResource(scheduleproviderimpl.context.getResources(), j);
                }

            .Lambda._cls0(int i)
            {
                arg$1 = ScheduleProviderImpl.this;
                arg$2 = i;
            }
            }

            obj1 = (FluentFuture)CalendarExecutor.BACKGROUND.submit(new .Lambda._cls0(i));
            bannerCache.put(i, new SoftReference(obj1));
        }
        return ((ListenableFuture) (obj1));
    }

    public final boolean shouldShowMonthImages()
    {
        return !context.getResources().getBoolean(0x7f0c0016);
    }
}
