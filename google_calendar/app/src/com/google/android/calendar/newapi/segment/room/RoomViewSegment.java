// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.room;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.utils.account.AccountUtil;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.room:
//            RoomUtils

public class RoomViewSegment extends TextTileView
    implements ViewSegment
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/room/RoomViewSegment);
    private final EventHolder model;

    public RoomViewSegment(Context context, EventHolder eventholder)
    {
        super(context);
        indentContent();
        setIconDrawable(0x7f020212);
        super.denseMode = false;
        setFocusable(true);
        model = eventholder;
    }

    public final void updateUi()
    {
        Object obj;
        Object obj1;
        Object obj2;
        CalendarDescriptor calendardescriptor;
        boolean flag = false;
        obj1 = null;
        obj = RoomUtils.getRooms(model.getEvent());
        byte byte0;
        if (((List) (obj)).isEmpty())
        {
            byte0 = 8;
        } else
        {
            byte0 = 0;
        }
        setVisibility(byte0);
        obj = RoomUtils.createRoomLabels(((List) (obj)));
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = (CharSequence[])((List) (obj)).toArray(new CharSequence[((List) (obj)).size()]);
        }
        setPrimaryText(((CharSequence []) (obj)));
        useTextAsContentDescription(0x7f13017e);
        obj2 = model.getEvent();
        calendardescriptor = ((Event) (obj2)).getCalendar();
        if (calendardescriptor != null) goto _L2; else goto _L1
_L1:
        obj = obj1;
_L4:
        if (obj != null)
        {
            flag = true;
        }
        treatAsButton(flag);
        setOnClickListener(((android.view.View.OnClickListener) (obj)));
        return;
_L2:
        obj = obj1;
        if (AccountUtil.isGoogleCorpAccount(calendardescriptor.account))
        {
            obj2 = RoomUtils.getRooms(((Event) (obj2)));
            obj = obj1;
            class .Lambda._cls0
                implements android.view.View.OnClickListener
            {

                private final RoomViewSegment arg$1;
                private final List arg$2;

                public final void onClick(View view)
                {
                    view = arg$1;
                    List list = arg$2;
                    ActivityUtils.startActivityForUrl(view.getContext(), RoomUtils.createRoomsLink(list), RoomViewSegment.TAG);
                }

            .Lambda._cls0(List list)
            {
                arg$1 = RoomViewSegment.this;
                arg$2 = list;
            }
            }

            if (!((List) (obj2)).isEmpty())
            {
                obj = new .Lambda._cls0(((List) (obj2)));
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

}
