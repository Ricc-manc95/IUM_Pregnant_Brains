// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            AutoValue_ScheduleItem

final class ScheduleDayFactory
{

    public final int chipEndMargin;
    public final int chipVerticalSpacing;
    public final int chipsStartMargin;
    public final int dayBottomMargin;
    public final int dayHeaderStartMargin;
    public final int defaultMargin;
    public final TimelineHostView hostView;
    public final ObservableReference isRtl;
    public final ItemAdapter itemAdapter;
    public final LayoutDimens layoutDimens;
    public final int monthBannerHeight;
    public final int nothingPlannedBannerHeight;
    public final int nowLineBottomPadding;
    private final int nowLineHeight;
    public final int nowLineTopPadding;
    public final ScheduleProvider scheduleProvider;
    public final TimeUtils timeUtils;
    public final int weekBannerHeight;

    ScheduleDayFactory(ItemAdapter itemadapter, ScheduleProvider scheduleprovider, ObservableReference observablereference, ObservableReference observablereference1, TimeUtils timeutils, TimelineHostView timelinehostview, DimensConverter dimensconverter, 
            LayoutDimens layoutdimens)
    {
        itemAdapter = itemadapter;
        scheduleProvider = scheduleprovider;
        timeUtils = timeutils;
        hostView = timelinehostview;
        isRtl = observablereference1;
        layoutDimens = layoutdimens;
        defaultMargin = dimensconverter.getPixelSize(12F);
        float f;
        boolean flag;
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            f = 180F;
        } else
        {
            f = 104F;
        }
        monthBannerHeight = dimensconverter.getPixelSize(f);
        weekBannerHeight = dimensconverter.getPixelSize(48F);
        dayHeaderStartMargin = dimensconverter.getPixelOffset(19F);
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            f = 100F;
        } else
        {
            f = 72F;
        }
        chipsStartMargin = dimensconverter.getPixelSize(f);
        chipEndMargin = layoutdimens.scheduleChipEndMargin();
        chipVerticalSpacing = dimensconverter.getPixelSize(10F);
        nowLineHeight = (int)Math.ceil(layoutdimens.converter.dpToPx(5F) * 2.0F);
        nowLineTopPadding = (int)layoutdimens.converter.dpToPx(5F);
        nowLineBottomPadding = (int)(layoutdimens.converter.dpToPx(5F) * 2.0F) - nowLineTopPadding;
        dayBottomMargin = (defaultMargin << 1) + chipVerticalSpacing;
        nothingPlannedBannerHeight = Math.round(layoutdimens.converter.dpToPx(60F)) - dayBottomMargin;
    }

    final int addNowLine(List list, int i)
    {
        int l = CalendarViewType.NOW_LINE.minPosition;
        int j;
        int k;
        int i1;
        if (((Boolean)isRtl.get()).booleanValue())
        {
            j = chipEndMargin;
        } else
        {
            j = chipsStartMargin - (int)Math.ceil(layoutDimens.converter.dpToPx(5F));
        }
        i1 = hostView.getMeasuredWidth();
        if (((Boolean)isRtl.get()).booleanValue())
        {
            k = chipsStartMargin - (int)Math.ceil(layoutDimens.converter.dpToPx(5F));
        } else
        {
            k = chipEndMargin;
        }
        list.add(new AutoValue_ScheduleItem(l, j, i, i1 - k, i + nowLineHeight, null, null, false));
        return nowLineHeight;
    }
}
