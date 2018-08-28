// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.calendar.client.events.logging.CalendarClientEventsExtension;
import com.google.calendar.client.events.logging.CalendarClientLogEvent;
import com.google.calendar.client.events.logging.CalendarEventInfo;
import com.google.calendar.client.events.logging.CustomTimeInfo;
import com.google.calendar.client.events.logging.RoomInfo;
import com.google.calendar.client.events.logging.RoomViewInfo;
import com.google.calendar.client.events.logging.ServiceResponseId;
import com.google.calendar.client.events.logging.TimeSuggestionInfo;
import com.google.calendar.client.events.logging.TimeSuggestionViewInfo;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.PrimitiveNonBoxingCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.loggers:
//            CalendarClearcutLogger

public final class CalendarClientLogger
{

    private static CalendarClientLogger instance;
    private final CalendarClearcutLogger calendarClearcutLogger;
    private final ClearcutLogger clearcutLogger;

    private CalendarClientLogger(Context context)
    {
        context = context.getApplicationContext();
        if (CalendarClearcutLogger.calendarClearcutLogger == null)
        {
            CalendarClearcutLogger.calendarClearcutLogger = new CalendarClearcutLogger(context);
        }
        calendarClearcutLogger = CalendarClearcutLogger.calendarClearcutLogger;
        clearcutLogger = new ClearcutLogger(context, "CALENDAR_CLIENT", null);
    }

    public static com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder getEventProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type type, String s, String s1, Long long1, Long long2)
    {
        com.google.calendar.client.events.logging.CalendarEventInfo.Builder builder = (com.google.calendar.client.events.logging.CalendarEventInfo.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarEventInfo.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        if (!TextUtils.isEmpty(s))
        {
            builder.copyOnWrite();
            CalendarEventInfo calendareventinfo = (CalendarEventInfo)builder.instance;
            if (s == null)
            {
                throw new NullPointerException();
            }
            calendareventinfo.bitField0_ = calendareventinfo.bitField0_ | 1;
            calendareventinfo.calendarEventId_ = s;
        }
        if (!TextUtils.isEmpty(s1))
        {
            builder.copyOnWrite();
            s = (CalendarEventInfo)builder.instance;
            if (s1 == null)
            {
                throw new NullPointerException();
            }
            s.bitField0_ = ((CalendarEventInfo) (s)).bitField0_ | 2;
            s.calendarEventReference_ = s1;
        }
        s = (com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarClientLogEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        s.copyOnWrite();
        s1 = (CalendarClientLogEvent)((com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder) (s)).instance;
        if (type == null)
        {
            throw new NullPointerException();
        }
        s1.bitField0_ = ((CalendarClientLogEvent) (s1)).bitField0_ | 1;
        s1.logEventType_ = type.value;
        s.copyOnWrite();
        type = (CalendarClientLogEvent)((com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder) (s)).instance;
        type.calendarEvent_ = (CalendarEventInfo)(GeneratedMessageLite)builder.build();
        type.bitField0_ = ((CalendarClientLogEvent) (type)).bitField0_ | 4;
        if (long1 != null || long2 != null)
        {
            type = (com.google.calendar.client.events.logging.CustomTimeInfo.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CustomTimeInfo.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            if (long1 != null)
            {
                long l = long1.longValue();
                type.copyOnWrite();
                s1 = (CustomTimeInfo)((com.google.calendar.client.events.logging.CustomTimeInfo.Builder) (type)).instance;
                s1.bitField0_ = ((CustomTimeInfo) (s1)).bitField0_ | 1;
                s1.startTimeMillis_ = l;
            }
            if (long2 != null)
            {
                long l1 = long2.longValue();
                type.copyOnWrite();
                s1 = (CustomTimeInfo)((com.google.calendar.client.events.logging.CustomTimeInfo.Builder) (type)).instance;
                s1.bitField0_ = ((CustomTimeInfo) (s1)).bitField0_ | 2;
                s1.endTimeMillis_ = l1;
            }
            s.copyOnWrite();
            s1 = (CalendarClientLogEvent)((com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder) (s)).instance;
            s1.customTimeInfo_ = (CustomTimeInfo)(GeneratedMessageLite)type.build();
            s1.bitField0_ = ((CalendarClientLogEvent) (s1)).bitField0_ | 0x80;
        }
        return s;
    }

    public static CalendarClientLogger getInstance(Context context)
    {
        com/google/android/apps/calendar/loggers/CalendarClientLogger;
        JVM INSTR monitorenter ;
        if (instance == null)
        {
            instance = new CalendarClientLogger(context);
        }
        context = instance;
        com/google/android/apps/calendar/loggers/CalendarClientLogger;
        JVM INSTR monitorexit ;
        return context;
        context;
        throw context;
    }

    public final com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder getFindTimeProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type type, String s, int i, boolean flag, String s1, Integer integer, Long long1, 
            Long long2, String s2, String s3)
    {
        type = getEventProto(type, null, s3, long1, long2);
        if (!TextUtils.isEmpty(s))
        {
            long1 = (com.google.calendar.client.events.logging.ServiceResponseId.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)ServiceResponseId.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            long1.copyOnWrite();
            long2 = (ServiceResponseId)((com.google.calendar.client.events.logging.ServiceResponseId.Builder) (long1)).instance;
            if (s == null)
            {
                throw new NullPointerException();
            }
            long2.bitField0_ = ((ServiceResponseId) (long2)).bitField0_ | 1;
            long2.suggestTimeResponseId_ = s;
            type.copyOnWrite();
            s = (CalendarClientLogEvent)((com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder) (type)).instance;
            s.serviceResponseId_ = (ServiceResponseId)(GeneratedMessageLite)long1.build();
            s.bitField0_ = ((CalendarClientLogEvent) (s)).bitField0_ | 8;
        }
        if (i != 0x80000000 || flag)
        {
            s = (com.google.calendar.client.events.logging.TimeSuggestionViewInfo.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)TimeSuggestionViewInfo.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            if (i != 0x80000000)
            {
                s.copyOnWrite();
                long1 = (TimeSuggestionViewInfo)((com.google.calendar.client.events.logging.TimeSuggestionViewInfo.Builder) (s)).instance;
                long1.bitField0_ = ((TimeSuggestionViewInfo) (long1)).bitField0_ | 1;
                long1.numBestSuggestions_ = i;
            }
            if (flag)
            {
                s.copyOnWrite();
                long1 = (TimeSuggestionViewInfo)((com.google.calendar.client.events.logging.TimeSuggestionViewInfo.Builder) (s)).instance;
                long1.bitField0_ = ((TimeSuggestionViewInfo) (long1)).bitField0_ | 2;
                long1.suggestionsForRecurringEvent_ = flag;
            }
            type.copyOnWrite();
            long1 = (CalendarClientLogEvent)((com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder) (type)).instance;
            long1.timeSuggestionViewInfo_ = (TimeSuggestionViewInfo)(GeneratedMessageLite)s.build();
            long1.bitField0_ = ((CalendarClientLogEvent) (long1)).bitField0_ | 0x20;
        }
        if (!TextUtils.isEmpty(s1) || integer != null)
        {
            s = (com.google.calendar.client.events.logging.TimeSuggestionInfo.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)TimeSuggestionInfo.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            if (!TextUtils.isEmpty(s1))
            {
                s.copyOnWrite();
                long1 = (TimeSuggestionInfo)((com.google.calendar.client.events.logging.TimeSuggestionInfo.Builder) (s)).instance;
                if (s1 == null)
                {
                    throw new NullPointerException();
                }
                long1.bitField0_ = ((TimeSuggestionInfo) (long1)).bitField0_ | 1;
                long1.suggestionToken_ = s1;
            }
            if (integer != null)
            {
                i = integer.intValue();
                s.copyOnWrite();
                s = (TimeSuggestionInfo)((com.google.calendar.client.events.logging.TimeSuggestionInfo.Builder) (s)).instance;
                s.bitField0_ = ((TimeSuggestionInfo) (s)).bitField0_ | 2;
                s.rank_ = i;
            }
        }
        return type;
    }

    public final com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type type, String s, String s1, String s2, Boolean boolean1, Boolean boolean2, Boolean boolean3, 
            Boolean boolean4, String s3, Boolean boolean5, Boolean boolean6, String s4, List list)
    {
        s2 = getEventProto(type, null, s2, null, null);
        if (!TextUtils.isEmpty(s) || !TextUtils.isEmpty(s1))
        {
            type = (com.google.calendar.client.events.logging.ServiceResponseId.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)ServiceResponseId.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            if (!TextUtils.isEmpty(s))
            {
                type.copyOnWrite();
                ServiceResponseId serviceresponseid = (ServiceResponseId)((com.google.calendar.client.events.logging.ServiceResponseId.Builder) (type)).instance;
                if (s == null)
                {
                    throw new NullPointerException();
                }
                serviceresponseid.bitField0_ = serviceresponseid.bitField0_ | 2;
                serviceresponseid.suggestRoomResponseId_ = s;
            }
            if (!TextUtils.isEmpty(s1))
            {
                type.copyOnWrite();
                s = (ServiceResponseId)((com.google.calendar.client.events.logging.ServiceResponseId.Builder) (type)).instance;
                if (s1 == null)
                {
                    throw new NullPointerException();
                }
                s.bitField0_ = ((ServiceResponseId) (s)).bitField0_ | 4;
                s.suggestMeetingResponseId_ = s1;
            }
            s2.copyOnWrite();
            s = (CalendarClientLogEvent)((com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder) (s2)).instance;
            s.serviceResponseId_ = (ServiceResponseId)(GeneratedMessageLite)type.build();
            s.bitField0_ = ((CalendarClientLogEvent) (s)).bitField0_ | 8;
        }
        if (boolean1 != null || boolean2 != null || boolean3 != null && boolean4 != null)
        {
            s = (com.google.calendar.client.events.logging.RoomViewInfo.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)RoomViewInfo.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            if (boolean1 != null)
            {
                boolean flag = boolean1.booleanValue();
                s.copyOnWrite();
                type = (RoomViewInfo)((com.google.calendar.client.events.logging.RoomViewInfo.Builder) (s)).instance;
                type.bitField0_ = ((RoomViewInfo) (type)).bitField0_ | 1;
                type.containsSuggestions_ = flag;
            }
            if (boolean2 != null)
            {
                boolean flag1 = boolean2.booleanValue();
                s.copyOnWrite();
                type = (RoomViewInfo)((com.google.calendar.client.events.logging.RoomViewInfo.Builder) (s)).instance;
                type.bitField0_ = ((RoomViewInfo) (type)).bitField0_ | 2;
                type.containsListing_ = flag1;
            }
            if (boolean3 != null && boolean4 != null)
            {
                if (boolean3.booleanValue())
                {
                    type = com.google.calendar.client.events.logging.RoomViewInfo.ListingType.FLAT;
                } else
                if (boolean4.booleanValue())
                {
                    type = com.google.calendar.client.events.logging.RoomViewInfo.ListingType.HIERARCHICAL;
                } else
                {
                    type = com.google.calendar.client.events.logging.RoomViewInfo.ListingType.UNKNOWN;
                }
                s.copyOnWrite();
                s1 = (RoomViewInfo)((com.google.calendar.client.events.logging.RoomViewInfo.Builder) (s)).instance;
                if (type == null)
                {
                    throw new NullPointerException();
                }
                s1.bitField0_ = ((RoomViewInfo) (s1)).bitField0_ | 4;
                s1.listingType_ = ((com.google.calendar.client.events.logging.RoomViewInfo.ListingType) (type)).value;
            }
            s2.copyOnWrite();
            type = (CalendarClientLogEvent)((com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder) (s2)).instance;
            type.roomViewInfo_ = (RoomViewInfo)(GeneratedMessageLite)s.build();
            type.bitField0_ = ((CalendarClientLogEvent) (type)).bitField0_ | 0x100;
        }
        if (!TextUtils.isEmpty(s3) || boolean5 != null && boolean6 != null)
        {
            s = (com.google.calendar.client.events.logging.RoomInfo.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)RoomInfo.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            if (!TextUtils.isEmpty(s3))
            {
                s.copyOnWrite();
                type = (RoomInfo)((com.google.calendar.client.events.logging.RoomInfo.Builder) (s)).instance;
                if (s3 == null)
                {
                    throw new NullPointerException();
                }
                type.bitField0_ = ((RoomInfo) (type)).bitField0_ | 1;
                type.roomEmail_ = s3;
            }
            if (boolean5 != null && boolean6 != null)
            {
                if (boolean5.booleanValue())
                {
                    type = com.google.calendar.client.events.logging.RoomInfo.UiEntryPointType.SUGGESTIONS;
                } else
                if (boolean6.booleanValue())
                {
                    type = com.google.calendar.client.events.logging.RoomInfo.UiEntryPointType.LISTING;
                } else
                {
                    type = com.google.calendar.client.events.logging.RoomInfo.UiEntryPointType.UNKNOWN;
                }
                s.copyOnWrite();
                s1 = (RoomInfo)((com.google.calendar.client.events.logging.RoomInfo.Builder) (s)).instance;
                if (type == null)
                {
                    throw new NullPointerException();
                }
                s1.bitField0_ = ((RoomInfo) (s1)).bitField0_ | 2;
                s1.uiEntryPointType_ = ((com.google.calendar.client.events.logging.RoomInfo.UiEntryPointType) (type)).value;
            }
            s2.copyOnWrite();
            type = (CalendarClientLogEvent)((com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder) (s2)).instance;
            type.room_ = (RoomInfo)(GeneratedMessageLite)s.build();
            type.bitField0_ = ((CalendarClientLogEvent) (type)).bitField0_ | 0x200;
        }
        if (!TextUtils.isEmpty(s4))
        {
            s2.copyOnWrite();
            type = (CalendarClientLogEvent)((com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder) (s2)).instance;
            if (s4 == null)
            {
                throw new NullPointerException();
            }
            type.bitField0_ = ((CalendarClientLogEvent) (type)).bitField0_ | 0x400;
            type.hierarchyNodeId_ = s4;
        }
        s2.copyOnWrite();
        type = (CalendarClientLogEvent)((com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder) (s2)).instance;
        if (!((CalendarClientLogEvent) (type)).buildingIdList_.isModifiable())
        {
            type.buildingIdList_ = GeneratedMessageLite.mutableCopy(((CalendarClientLogEvent) (type)).buildingIdList_);
        }
        type = ((CalendarClientLogEvent) (type)).buildingIdList_;
        Internal.checkNotNull(list);
        if (list instanceof LazyStringList)
        {
            s1 = ((LazyStringList)list).getUnderlyingElements();
            s = (LazyStringList)type;
            int j1 = type.size();
            for (type = s1.iterator(); type.hasNext();)
            {
                s1 = ((String) (type.next()));
                if (s1 == null)
                {
                    int i = s.size();
                    type = (new StringBuilder(37)).append("Element at index ").append(i - j1).append(" is null.").toString();
                    for (int j = s.size() - 1; j >= j1; j--)
                    {
                        s.remove(j);
                    }

                    throw new NullPointerException(type);
                }
                if (s1 instanceof ByteString)
                {
                    s.add((ByteString)s1);
                } else
                {
                    s.add((String)s1);
                }
            }

        } else
        if (list instanceof PrimitiveNonBoxingCollection)
        {
            type.addAll((Collection)list);
        } else
        {
            if ((type instanceof ArrayList) && (list instanceof Collection))
            {
                s = (ArrayList)type;
                int k = type.size();
                s.ensureCapacity(((Collection)list).size() + k);
            }
            int k1 = type.size();
            s = list.iterator();
            while (s.hasNext()) 
            {
                s1 = ((String) (s.next()));
                if (s1 == null)
                {
                    int l = type.size();
                    s = (new StringBuilder(37)).append("Element at index ").append(l - k1).append(" is null.").toString();
                    for (int i1 = type.size() - 1; i1 >= k1; i1--)
                    {
                        type.remove(i1);
                    }

                    throw new NullPointerException(s);
                }
                type.add(s1);
            }
        }
        return s2;
    }

    public final void log(Account account, com.google.calendar.client.events.logging.CalendarClientLogEvent.Builder builder)
    {
        CalendarClearcutLogger calendarclearcutlogger = calendarClearcutLogger;
        ClearcutLogger clearcutlogger = clearcutLogger;
        com.google.calendar.client.events.logging.CalendarClientEventsExtension.Builder builder1 = (com.google.calendar.client.events.logging.CalendarClientEventsExtension.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarClientEventsExtension.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        builder1.copyOnWrite();
        CalendarClientEventsExtension calendarclienteventsextension = (CalendarClientEventsExtension)builder1.instance;
        if (!calendarclienteventsextension.events_.isModifiable())
        {
            calendarclienteventsextension.events_ = GeneratedMessageLite.mutableCopy(calendarclienteventsextension.events_);
        }
        calendarclienteventsextension.events_.add((CalendarClientLogEvent)(GeneratedMessageLite)builder.build());
        calendarclearcutlogger.logEvent(new com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder(clearcutlogger, ((CalendarClientEventsExtension)(GeneratedMessageLite)builder1.build()).toByteArray()), account);
    }
}
