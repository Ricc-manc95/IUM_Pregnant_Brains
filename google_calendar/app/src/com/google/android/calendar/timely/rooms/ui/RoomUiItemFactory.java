// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.content.Context;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            RoomUiItem

public final class RoomUiItemFactory
{

    private final Context context;

    RoomUiItemFactory(Context context1)
    {
        context = context1;
    }

    public static List fromAddedRooms(List list, Predicate predicate)
    {
        ArrayList arraylist = new ArrayList();
        list = list.iterator();
        while (list.hasNext()) 
        {
            Room room = (Room)list.next();
            byte byte0;
            if (predicate.apply(room))
            {
                byte0 = 3;
            } else
            {
                byte0 = 1;
            }
            arraylist.add(new RoomUiItem(null, room, byte0, null, false));
        }
        return arraylist;
    }

    public final List fromResult(List list, String s, List list1, String s1, List list2, boolean flag, ImmutableSet immutableset)
    {
        s = new ArrayList();
        if (!list.isEmpty())
        {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) 
            {
                Room room = (Room)iterator.next();
                byte byte0;
                boolean flag2;
                if (immutableset == null)
                {
                    flag2 = false;
                } else
                {
                    flag2 = immutableset.contains(room.getEmail());
                }
                if (flag2)
                {
                    byte0 = 3;
                } else
                {
                    byte0 = 1;
                }
                s.add(new RoomUiItem(null, room, byte0, null, true));
            }
        }
        if (!list1.isEmpty())
        {
            if (s1 != null)
            {
                s.add(new RoomUiItem(s1, null, 0, null, false));
            }
            list1 = list1.iterator();
            while (list1.hasNext()) 
            {
                s1 = (Room)list1.next();
                byte byte1;
                boolean flag3;
                if (immutableset == null)
                {
                    flag3 = false;
                } else
                {
                    flag3 = immutableset.contains(s1.getEmail());
                }
                if (flag3)
                {
                    byte1 = 3;
                } else
                {
                    byte1 = 1;
                }
                s.add(new RoomUiItem(null, s1, byte1, null, false));
            }
        }
        if (!list2.isEmpty())
        {
            boolean flag1;
            if (AccessibilityUtils.isAccessibilityEnabled(context) || list.size() <= 2)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag || flag1)
            {
                s.add(new RoomUiItem(context.getString(0x7f1300a0), null, 0, null, false));
                for (list = list2.iterator(); list.hasNext(); s.add(new RoomUiItem(null, null, 2, (RoomHierarchyNode)list.next(), false))) { }
            } else
            {
                s.add(new RoomUiItem(null, null, 5, null, false));
            }
        }
        return s;
    }
}
