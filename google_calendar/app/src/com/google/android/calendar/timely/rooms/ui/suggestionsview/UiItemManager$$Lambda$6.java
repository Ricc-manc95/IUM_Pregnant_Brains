// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import com.google.android.calendar.timely.rooms.data.AttendeeGroup;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.AddedRoom;
import com.google.common.collect.ListMultimap;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui.suggestionsview:
//            UiItemManager

public final class arg._cls2
    implements Runnable
{

    private final UiItemManager arg$1;
    private final AttendeeGroup arg$2;

    public final void run()
    {
        Object obj1 = arg$1;
        AttendeeGroup attendeegroup = arg$2;
        if (((UiItemManager) (obj1)).listener != null)
        {
            arg._cls2 _lcls2 = ((UiItemManager) (obj1)).listener;
            Object obj;
            if (((UiItemManager) (obj1)).addedRoomsByHierarchyNodeId == null)
            {
                obj = null;
            } else
            {
                obj = new com.google.common.collect.it>();
                com.google.common.collect.ndeeGroup ndeegroup;
                for (obj1 = ((UiItemManager) (obj1)).addedRoomsByHierarchyNodeId.get(attendeegroup.getHierarchyNodeId()).iterator(); ((Iterator) (obj1)).hasNext();)
                {
                    ndeegroup = (com.google.common.collect.ndeeGroup.getHierarchyNodeId)((com.google.common.collect.der) (obj)).add(((AddedRoom)((Iterator) (obj1)).next()).room.getEmail());
                }

                obj = ((com.google.common.collect..getEmail) (obj)).ld();
            }
            _lcls2.xpandSuggestions(attendeegroup, ((com.google.common.collect.ImmutableSet) (obj)));
        }
    }
                                                                                                                                                  }
