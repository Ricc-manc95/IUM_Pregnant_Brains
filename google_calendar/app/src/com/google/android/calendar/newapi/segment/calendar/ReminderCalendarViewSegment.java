// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import com.google.android.calendar.newapi.model.CalendarHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;

public final class ReminderCalendarViewSegment extends TextTileView
    implements ViewSegment
{

    private final CalendarHolder model;

    public ReminderCalendarViewSegment(Context context, CalendarHolder calendarholder)
    {
        super(context);
        model = calendarholder;
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        setIconDrawable(0x7f020227);
        super.denseMode = false;
        setFocusable(true);
    }

    public final void updateUi()
    {
        setPrimaryText(new CharSequence[] {
            getResources().getString(0x7f1303e2, new Object[0])
        });
        setSecondaryText(new CharSequence[] {
            model.getAccount().name
        });
        useTextAsContentDescription(0x7f130167);
    }
}
