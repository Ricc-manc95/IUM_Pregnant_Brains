// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers.visualelements;

import com.google.android.libraries.social.analytics.events.handler.lite.AbstractVisualElementLiteMetadataHandler;
import com.google.android.libraries.social.analytics.visualelement.VisualElement;
import com.google.calendar.client.events.logging.CalendarClientVisualElementMetadata;
import com.google.calendar.client.events.logging.CalendarEvent;
import com.google.calendar.client.events.logging.PendingCalendarEvent;
import com.google.protobuf.GeneratedMessageLite;

// Referenced classes of package com.google.android.apps.calendar.loggers.visualelements:
//            EventVisualElement

final class EventMedatadaHandler extends AbstractVisualElementLiteMetadataHandler
{

    EventMedatadaHandler()
    {
    }

    public final Class getHandledVisualElementClass()
    {
        return com/google/android/apps/calendar/loggers/visualelements/EventVisualElement;
    }

    public final void handleVisualElementMetadata(VisualElement visualelement, com.google.protobuf.MessageLite.Builder builder)
    {
        Object obj = (EventVisualElement)visualelement;
        visualelement = (com.google.calendar.client.events.logging.CalendarClientVisualElementMetadata.Builder)builder;
        if (((EventVisualElement) (obj)).eventReferenceId != null)
        {
            builder = (com.google.calendar.client.events.logging.PendingCalendarEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)PendingCalendarEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            Object obj1 = ((EventVisualElement) (obj)).calendarId;
            builder.copyOnWrite();
            PendingCalendarEvent pendingcalendarevent = (PendingCalendarEvent)((com.google.calendar.client.events.logging.PendingCalendarEvent.Builder) (builder)).instance;
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            pendingcalendarevent.bitField0_ = pendingcalendarevent.bitField0_ | 2;
            pendingcalendarevent.calendarId_ = ((String) (obj1));
            obj1 = ((EventVisualElement) (obj)).eventReferenceId;
            builder.copyOnWrite();
            pendingcalendarevent = (PendingCalendarEvent)((com.google.calendar.client.events.logging.PendingCalendarEvent.Builder) (builder)).instance;
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            pendingcalendarevent.bitField0_ = pendingcalendarevent.bitField0_ | 1;
            pendingcalendarevent.eventReference_ = ((String) (obj1));
            visualelement.copyOnWrite();
            obj1 = (CalendarClientVisualElementMetadata)((com.google.calendar.client.events.logging.CalendarClientVisualElementMetadata.Builder) (visualelement)).instance;
            obj1.pendingCalendarEvent_ = (PendingCalendarEvent)(GeneratedMessageLite)builder.build();
            obj1.bitField0_ = ((CalendarClientVisualElementMetadata) (obj1)).bitField0_ | 4;
        }
        if (((EventVisualElement) (obj)).eventId != null)
        {
            builder = (com.google.calendar.client.events.logging.CalendarEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            Object obj2 = ((EventVisualElement) (obj)).eventId;
            builder.copyOnWrite();
            CalendarEvent calendarevent = (CalendarEvent)((com.google.calendar.client.events.logging.CalendarEvent.Builder) (builder)).instance;
            if (obj2 == null)
            {
                throw new NullPointerException();
            }
            calendarevent.bitField0_ = calendarevent.bitField0_ | 1;
            calendarevent.eventId_ = ((String) (obj2));
            obj = ((EventVisualElement) (obj)).calendarId;
            builder.copyOnWrite();
            obj2 = (CalendarEvent)((com.google.calendar.client.events.logging.CalendarEvent.Builder) (builder)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            obj2.bitField0_ = ((CalendarEvent) (obj2)).bitField0_ | 1;
            obj2.eventId_ = ((String) (obj));
            visualelement.copyOnWrite();
            visualelement = (CalendarClientVisualElementMetadata)((com.google.calendar.client.events.logging.CalendarClientVisualElementMetadata.Builder) (visualelement)).instance;
            visualelement.calendarEvent_ = (CalendarEvent)(GeneratedMessageLite)builder.build();
            visualelement.bitField0_ = ((CalendarClientVisualElementMetadata) (visualelement)).bitField0_ | 2;
        }
    }
}
