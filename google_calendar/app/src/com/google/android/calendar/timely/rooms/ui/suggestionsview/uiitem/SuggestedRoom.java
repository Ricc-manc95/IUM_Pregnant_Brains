// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.data.RoomCriteria;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem:
//            UiItem

public class SuggestedRoom extends UiItem
{

    public final Consumer consumer;
    public final RoomCriteria criteria;
    public final Room room;

    public SuggestedRoom(Room room1, Consumer consumer1, RoomCriteria roomcriteria)
    {
        room = room1;
        consumer = consumer1;
        criteria = roomcriteria;
    }
}
