// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import com.google.android.calendar.timely.net.TimestampUtils;
import com.google.android.calendar.timely.rooms.data.Attendee;
import com.google.android.calendar.timely.rooms.data.AutoValue_RoomFeature;
import com.google.android.calendar.timely.rooms.data.AutoValue_RoomSuggestion;
import com.google.calendar.suggest.v2.ResponseStatus;
import com.google.calendar.suggest.v2.Room;
import com.google.calendar.suggest.v2.RoomFeature;
import com.google.calendar.suggest.v2.RoomSuggestion;
import com.google.calendar.suggest.v2.SingleEventTime;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.GeneratedMessageLite;
import java.util.Iterator;
import java.util.List;

final class ConvertUtils
{

    static com.google.android.calendar.timely.rooms.data.Room fromGrpcRoom(Room room, int i)
    {
        com.google.android.calendar.timely.rooms.data.Room.Builder builder1;
        com.google.common.collect.ImmutableList.Builder builder2;
        int j;
        boolean flag;
        flag = true;
        Object obj1 = (new com.google.android.calendar.timely.rooms.data..AutoValue_Room.Builder()).setAvailability(0).setFeatures(ImmutableList.of()).setCategory(0).setEmail(room.email_).setName(room.name_).setShortName(Platform.emptyToNull(room.shortName_)).setDescription(Platform.emptyToNull(room.description_)).setAvailability(i).setHierarchyNodeId(room.hierarchyNodeId_);
        Object obj;
        Iterator iterator;
        String s;
        RoomFeature roomfeature;
        if (room.capacity_ == 0)
        {
            obj = null;
        } else
        {
            obj = Integer.valueOf(room.capacity_);
        }
        builder1 = ((com.google.android.calendar.timely.rooms.data.Room.Builder) (obj1)).setCapacity(((Integer) (obj))).setBuildingName(Platform.emptyToNull(room.buildingName_)).setFloorName(Platform.emptyToNull(room.floorName_)).setFloorSectionName(Platform.emptyToNull(room.floorSectionName_));
        obj = room.features_;
        builder2 = ImmutableList.builder();
        iterator = ((List) (obj)).iterator();
_L12:
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_306;
        }
        roomfeature = (RoomFeature)iterator.next();
        s = roomfeature.name_;
        obj1 = com.google.calendar.suggest.v2.RoomFeature.EquipmentType.forNumber(roomfeature.equipmentType_);
        obj = obj1;
        if (obj1 == null)
        {
            obj = com.google.calendar.suggest.v2.RoomFeature.EquipmentType.UNRECOGNIZED;
        }
        ((com.google.calendar.suggest.v2.RoomFeature.EquipmentType) (obj)).ordinal();
        JVM INSTR tableswitch 1 2: default 208
    //                   1 295
    //                   2 290;
           goto _L1 _L2 _L3
_L1:
        i = 0;
_L4:
        obj1 = com.google.calendar.suggest.v2.RoomFeature.DisplayProminence.forNumber(roomfeature.displayProminence_);
        obj = obj1;
        if (obj1 == null)
        {
            obj = com.google.calendar.suggest.v2.RoomFeature.DisplayProminence.UNRECOGNIZED;
        }
        switch (((com.google.calendar.suggest.v2.RoomFeature.DisplayProminence) (obj)).ordinal())
        {
        default:
            j = 0;
            break;

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_300;
        }
_L5:
        obj = (com.google.common.collect.ImmutableList.Builder)builder2.add(new AutoValue_RoomFeature(s, i, j));
        continue; /* Loop/switch isn't completed */
_L3:
        i = 2;
          goto _L4
_L2:
        i = 1;
          goto _L4
        j = 1;
          goto _L5
        com.google.android.calendar.timely.rooms.data.Room.Builder builder;
        builder2.forceCopy = true;
        builder = builder1.setFeatures(ImmutableList.asImmutableList(builder2.contents, builder2.size));
        com.google.calendar.suggest.v2.Room.ResourceCategory resourcecategory = com.google.calendar.suggest.v2.Room.ResourceCategory.forNumber(room.category_);
        room = resourcecategory;
        if (resourcecategory == null)
        {
            room = com.google.calendar.suggest.v2.Room.ResourceCategory.UNRECOGNIZED;
        }
        i = ((flag) ? 1 : 0);
        room.ordinal();
        JVM INSTR tableswitch 1 2: default 380
    //                   1 382
    //                   2 391;
           goto _L6 _L7 _L8
_L7:
        break; /* Loop/switch isn't completed */
_L6:
        i = 0;
_L10:
        return builder.setCategory(i).build();
_L8:
        i = 2;
        if (true) goto _L10; else goto _L9
_L9:
        if (true) goto _L12; else goto _L11
_L11:
    }

    static ImmutableList fromGrpcRoomSuggestions(List list)
    {
        com.google.common.collect.ImmutableList.Builder builder = ImmutableList.builder();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) 
        {
            RoomSuggestion roomsuggestion = (RoomSuggestion)iterator.next();
            if (roomsuggestion.room_ == null)
            {
                list = Room.DEFAULT_INSTANCE;
            } else
            {
                list = roomsuggestion.room_;
            }
            list = (com.google.common.collect.ImmutableList.Builder)builder.add(new AutoValue_RoomSuggestion(fromGrpcRoom(list, roomsuggestion.availability_)));
        }
        builder.forceCopy = true;
        return ImmutableList.asImmutableList(builder.contents, builder.size);
    }

    static List toGrpcAttendees(List list)
    {
        com.google.common.collect.ImmutableList.Builder builder;
        Iterator iterator;
        builder = ImmutableList.builder();
        iterator = list.iterator();
_L8:
        com.google.calendar.suggest.v2.Attendee.Builder builder1;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        list = (Attendee)iterator.next();
        builder1 = (com.google.calendar.suggest.v2.Attendee.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.calendar.suggest.v2.Attendee.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        String s = list.getEmail();
        builder1.copyOnWrite();
        com.google.calendar.suggest.v2.Attendee attendee1 = (com.google.calendar.suggest.v2.Attendee)builder1.instance;
        if (s == null)
        {
            throw new NullPointerException();
        }
        attendee1.email_ = s;
        boolean flag = list.isOrganizer();
        builder1.copyOnWrite();
        ((com.google.calendar.suggest.v2.Attendee)builder1.instance).organizer_ = flag;
        list.getResponseStatus().ordinal();
        JVM INSTR tableswitch 1 3: default 140
    //                   1 183
    //                   2 176
    //                   3 169;
           goto _L1 _L2 _L3 _L4
_L1:
        list = ResponseStatus.NEEDS_ACTION;
_L6:
        com.google.calendar.suggest.v2.Attendee attendee;
        builder1.copyOnWrite();
        attendee = (com.google.calendar.suggest.v2.Attendee)builder1.instance;
        if (list == null)
        {
            throw new NullPointerException();
        }
        break; /* Loop/switch isn't completed */
_L4:
        list = ResponseStatus.DECLINED;
        continue; /* Loop/switch isn't completed */
_L3:
        list = ResponseStatus.TENTATIVE;
        continue; /* Loop/switch isn't completed */
_L2:
        list = ResponseStatus.ACCEPTED;
        if (true) goto _L6; else goto _L5
_L5:
        if (list == ResponseStatus.UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        attendee.responseStatus_ = ((ResponseStatus) (list)).value;
        list = (com.google.common.collect.ImmutableList.Builder)builder.add((com.google.calendar.suggest.v2.Attendee)(GeneratedMessageLite)builder1.build());
        if (true) goto _L8; else goto _L7
_L7:
        builder.forceCopy = true;
        return ImmutableList.asImmutableList(builder.contents, builder.size);
    }

    static SingleEventTime toGrpcSingleEventTime(com.google.android.calendar.timely.rooms.data.SingleEventTime singleeventtime)
    {
        com.google.calendar.suggest.v2.SingleEventTime.Builder builder = (com.google.calendar.suggest.v2.SingleEventTime.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)SingleEventTime.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        com.google.protobuf.Timestamp timestamp = TimestampUtils.timestampFromMillis(singleeventtime.getStart());
        builder.copyOnWrite();
        SingleEventTime singleeventtime1 = (SingleEventTime)builder.instance;
        if (timestamp == null)
        {
            throw new NullPointerException();
        }
        singleeventtime1.startTime_ = timestamp;
        if (singleeventtime.getEnd() != null)
        {
            com.google.protobuf.Timestamp timestamp1 = TimestampUtils.timestampFromMillis(singleeventtime.getEnd().longValue());
            builder.copyOnWrite();
            SingleEventTime singleeventtime2 = (SingleEventTime)builder.instance;
            if (timestamp1 == null)
            {
                throw new NullPointerException();
            }
            singleeventtime2.endTime_ = timestamp1;
        }
        if (singleeventtime.getAllDay() != null)
        {
            boolean flag = singleeventtime.getAllDay().booleanValue();
            builder.copyOnWrite();
            ((SingleEventTime)builder.instance).allDay_ = flag;
        }
        return (SingleEventTime)(GeneratedMessageLite)builder.build();
    }
}
