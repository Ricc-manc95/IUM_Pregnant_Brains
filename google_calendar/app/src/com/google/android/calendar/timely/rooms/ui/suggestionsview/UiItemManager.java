// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.calendar.timely.rooms.data.AttendeeGroup;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.data.RoomCriteria;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.AddedRoom;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.LocationHeader;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.ShortDividerItem;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.UiItem;
import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui.suggestionsview:
//            UiItemList

public final class UiItemManager
{

    public final ListMultimap addedRoomsByHierarchyNodeId = new ArrayListMultimap();
    public final Context context;
    public final Multimap decorationByLocation = new HashMultimap();
    public final Map headerByLocation = new HashMap();
    private final Predicate isRoomRemovable;
    public Listener listener;
    public final Map nodeExpansionStateByHierarchyNodeId = new HashMap();
    public final Map roomCriteriaByHierarchyNodeId = new HashMap();
    public final ListMultimap suggestedRoomTileByLocation = new ArrayListMultimap();
    public final UiItemList uiItemList;

    public UiItemManager(Context context1, UiItemList uiitemlist, Predicate predicate)
    {
        context = context1;
        uiItemList = uiitemlist;
        isRoomRemovable = predicate;
    }

    final void collapseLocation(AttendeeGroup attendeegroup)
    {
        UiItem uiitem;
        for (Iterator iterator = suggestedRoomTileByLocation.get(attendeegroup).iterator(); iterator.hasNext(); uiItemList.removeItem(uiitem))
        {
            uiitem = (UiItem)iterator.next();
        }

        suggestedRoomTileByLocation.removeAll(attendeegroup);
        Object obj = decorationByLocation.get(attendeegroup).iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            UiItem uiitem1 = (UiItem)((Iterator) (obj)).next();
            if (addedRoomsByHierarchyNodeId.get(attendeegroup.getHierarchyNodeId()).isEmpty() || !(uiitem1 instanceof ShortDividerItem))
            {
                uiItemList.removeItem(uiitem1);
            }
        } while (true);
        nodeExpansionStateByHierarchyNodeId.put(attendeegroup.getHierarchyNodeId(), Boolean.valueOf(false));
        LocationHeader locationheader;
        boolean flag;
        if (!attendeegroup.getRoomSuggestions().isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = getHeader(attendeegroup, flag, 3);
        locationheader = (LocationHeader)headerByLocation.get(attendeegroup);
        headerByLocation.put(attendeegroup, obj);
        uiItemList.replaceItem(locationheader, ((UiItem) (obj)));
    }

    public final AddedRoom getAddedRoom(Room room, RoomCriteria roomcriteria)
    {
        class .Lambda._cls7
            implements Consumer
        {

            private final UiItemManager arg$1;

            public final void accept(Object obj)
            {
                UiItemManager uiitemmanager = arg$1;
                obj = (AddedRoom)obj;
                if (uiitemmanager.listener != null)
                {
                    uiitemmanager.listener.removeRoom(((AddedRoom) (obj)));
                }
            }

            .Lambda._cls7()
            {
                arg$1 = UiItemManager.this;
            }

            private class Listener
            {

                public abstract void acceptSuggestion(AttendeeGroup attendeegroup, SuggestedRoom suggestedroom);

                public abstract void changeCapacityRequirementClicked$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NN4RRFDLPIUP31EHGIUGBKEHIMSP35CL3N4RRLE0TIILG_0();

                public abstract void expandSection();

                public abstract void expandSuggestions(AttendeeGroup attendeegroup, ImmutableSet immutableset);

                public abstract void removeRoom(AddedRoom addedroom);

                public abstract void toggleAudioRequirement(AttendeeGroup attendeegroup);

                public abstract void toggleVideoRequirement(AttendeeGroup attendeegroup);
            }

        }

        .Lambda._cls7 _lcls7;
        if (isRoomRemovable.apply(room))
        {
            _lcls7 = new .Lambda._cls7();
        } else
        {
            _lcls7 = null;
        }
        return new AddedRoom(room, _lcls7, roomcriteria);
    }

    public final LocationHeader getHeader(AttendeeGroup attendeegroup, boolean flag, int i)
    {
        Resources resources = context.getResources();
        RoomCriteria roomcriteria = attendeegroup.getCriteria();
        class .Lambda._cls1
            implements Runnable
        {

            private final UiItemManager arg$1;
            private final AttendeeGroup arg$2;

            public final void run()
            {
                UiItemManager uiitemmanager = arg$1;
                AttendeeGroup attendeegroup1 = arg$2;
                if (((Boolean)uiitemmanager.nodeExpansionStateByHierarchyNodeId.get(attendeegroup1.getHierarchyNodeId())).booleanValue())
                {
                    uiitemmanager.collapseLocation(attendeegroup1);
                } else
                {
                    uiitemmanager.nodeExpansionStateByHierarchyNodeId.put(attendeegroup1.getHierarchyNodeId(), Boolean.valueOf(true));
                    if (uiitemmanager.listener != null)
                    {
                        uiitemmanager.listener.expandSection();
                        return;
                    }
                }
            }

            .Lambda._cls1(AttendeeGroup attendeegroup)
            {
                arg$1 = UiItemManager.this;
                arg$2 = attendeegroup;
            }
        }

        .Lambda._cls1 _lcls1 = new .Lambda._cls1(attendeegroup);
        class .Lambda._cls2
            implements Runnable
        {

            private final UiItemManager arg$1;
            private final AttendeeGroup arg$2;

            public final void run()
            {
                UiItemManager uiitemmanager = arg$1;
                AttendeeGroup attendeegroup1 = arg$2;
                if (uiitemmanager.listener != null)
                {
                    uiitemmanager.listener._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NN4RRFDLPIUP31EHGIUGBKEHIMSP35CL3N4RRLE0TIILG_0();
                }
            }

            .Lambda._cls2(AttendeeGroup attendeegroup)
            {
                arg$1 = UiItemManager.this;
                arg$2 = attendeegroup;
            }
        }

        .Lambda._cls2 _lcls2 = new .Lambda._cls2(attendeegroup);
        class .Lambda._cls3
            implements Runnable
        {

            private final UiItemManager arg$1;
            private final AttendeeGroup arg$2;

            public final void run()
            {
                UiItemManager uiitemmanager = arg$1;
                AttendeeGroup attendeegroup1 = arg$2;
                if (uiitemmanager.listener != null)
                {
                    uiitemmanager.listener.toggleVideoRequirement(attendeegroup1);
                }
            }

            .Lambda._cls3(AttendeeGroup attendeegroup)
            {
                arg$1 = UiItemManager.this;
                arg$2 = attendeegroup;
            }
        }

        .Lambda._cls3 _lcls3 = new .Lambda._cls3(attendeegroup);
        class .Lambda._cls4
            implements Runnable
        {

            private final UiItemManager arg$1;
            private final AttendeeGroup arg$2;

            public final void run()
            {
                UiItemManager uiitemmanager = arg$1;
                AttendeeGroup attendeegroup1 = arg$2;
                if (uiitemmanager.listener != null)
                {
                    uiitemmanager.listener.toggleAudioRequirement(attendeegroup1);
                }
            }

            .Lambda._cls4(AttendeeGroup attendeegroup)
            {
                arg$1 = UiItemManager.this;
                arg$2 = attendeegroup;
            }
        }

        .Lambda._cls4 _lcls4 = new .Lambda._cls4(attendeegroup);
        Object obj = roomcriteria.getPreferredBuildingName();
        ImmutableList immutablelist = roomcriteria.getAttendees();
        if (!flag && obj == null)
        {
            int j = immutablelist.size();
            attendeegroup = String.format(resources.getQuantityString(0x7f120021, j), new Object[] {
                Integer.valueOf(j)
            });
        } else
        {
            attendeegroup = ((AttendeeGroup) (obj));
        }
        if (obj == null)
        {
            obj = attendeegroup;
        } else
        {
            obj = resources.getString(0x7f1301e6, new Object[] {
                Integer.valueOf(immutablelist.size()), obj
            });
        }
        return new LocationHeader(attendeegroup, ((String) (obj)), immutablelist, _lcls1, flag, roomcriteria.getNumSeats(), false, false, i, _lcls2, _lcls3, _lcls4);
    }
}
