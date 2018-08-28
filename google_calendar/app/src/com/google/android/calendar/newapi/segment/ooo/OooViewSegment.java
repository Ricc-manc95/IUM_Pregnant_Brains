// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.ooo;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.userstatus.AutoReply;
import com.google.android.calendar.api.event.userstatus.OutOfOffice;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;

public class OooViewSegment extends LinearLayout
    implements ViewSegment
{

    private final TextTileView autoDeclineStatusTile = (TextTileView)findViewById(0x7f10028c);
    private final TextTileView autoReplyStatusTile = (TextTileView)findViewById(0x7f10028d);
    private final EventHolder model;

    public OooViewSegment(Context context, EventHolder eventholder)
    {
        super(context);
        model = eventholder;
        setOrientation(1);
        inflate(context, 0x7f0500de, this);
    }

    public final void updateUi()
    {
        boolean flag1 = false;
        Object obj = model.getEvent().getParticipantStatus();
        boolean flag;
        if (obj != null)
        {
            obj = ((UserStatus) (obj)).getOutOfOffice();
        } else
        {
            obj = null;
        }
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (this != null)
        {
            int j;
            if (flag)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            setVisibility(j);
        }
        if (flag)
        {
            TextTileView texttileview = autoDeclineStatusTile;
            int i;
            if (((OutOfOffice) (obj)).isAutoDeclineEnabled())
            {
                i = 0x7f13036c;
            } else
            {
                i = 0x7f13036b;
            }
            texttileview.setPrimaryText(new CharSequence[] {
                texttileview.getResources().getString(i, new Object[0])
            });
            texttileview = autoReplyStatusTile;
            if (((OutOfOffice) (obj)).getAutoReply() != null && ((OutOfOffice) (obj)).getAutoReply().isEnabled())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (texttileview != null)
            {
                if (i != 0)
                {
                    i = ((flag1) ? 1 : 0);
                } else
                {
                    i = 8;
                }
                texttileview.setVisibility(i);
                return;
            }
        }
    }

    static 
    {
        LogUtils.getLogTag(com/google/android/calendar/newapi/segment/ooo/OooViewSegment);
    }
}
