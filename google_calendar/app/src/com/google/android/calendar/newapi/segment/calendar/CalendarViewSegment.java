// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.newapi.model.CalendarHolder;
import com.google.android.calendar.newapi.model.CalendarListEntryHolder;
import com.google.android.calendar.newapi.model.HolidayHolder;
import com.google.android.calendar.newapi.model.VisibleCalendarsHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;

public final class CalendarViewSegment extends TextTileView
    implements ViewSegment
{

    private final CalendarHolder model;

    public CalendarViewSegment(Context context, CalendarHolder calendarholder)
    {
        super(context);
        model = calendarholder;
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        setIconDrawable(0x7f0201f3);
        super.denseMode = false;
        setFocusable(true);
    }

    public final void updateUi()
    {
        Object obj = ((CalendarListEntryHolder)model).getCalendarListEntry().getAccessLevel();
        Object obj1 = CalendarAccessLevel.OWNER;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        int i;
        if (((CalendarAccessLevel) (obj)).level - ((CalendarAccessLevel) (obj1)).level == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0 || ((VisibleCalendarsHolder)model).getVisibleCalendarsCount() > 1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (this != null)
        {
            String s;
            if (i != 0)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            setVisibility(i);
        }
        s = ((CalendarListEntryHolder)model).getCalendarListEntry().getDisplayName();
        obj1 = model.getAccount().name;
        obj = s;
        if (s.equalsIgnoreCase(((String) (obj1))))
        {
            obj = getResources().getString(0x7f1303b3);
        }
        setPrimaryText(new CharSequence[] {
            obj
        });
        if (((HolidayHolder)model).isHolidayEvent())
        {
            obj = null;
        } else
        {
            obj = obj1;
        }
        setSecondaryText(new CharSequence[] {
            obj
        });
        useTextAsContentDescription(0x7f130167);
    }
}
