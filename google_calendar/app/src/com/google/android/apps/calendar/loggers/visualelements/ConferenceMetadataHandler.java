// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers.visualelements;

import com.google.android.calendar.event.conference.LocalPhoneSource;
import com.google.android.libraries.social.analytics.events.handler.lite.AbstractVisualElementLiteMetadataHandler;
import com.google.android.libraries.social.analytics.visualelement.VisualElement;
import com.google.calendar.client.events.logging.CalendarClientVisualElementMetadata;
import com.google.calendar.client.events.logging.ConferenceMetadata;
import com.google.calendar.client.events.logging.ConferencePhoneNumberLocationSource;
import com.google.protobuf.GeneratedMessageLite;

// Referenced classes of package com.google.android.apps.calendar.loggers.visualelements:
//            ConferenceVisualElement

public final class ConferenceMetadataHandler extends AbstractVisualElementLiteMetadataHandler
{

    public ConferenceMetadataHandler()
    {
    }

    public final Class getHandledVisualElementClass()
    {
        return com/google/android/apps/calendar/loggers/visualelements/ConferenceVisualElement;
    }

    public final void handleVisualElementMetadata(VisualElement visualelement, com.google.protobuf.MessageLite.Builder builder)
    {
        com.google.calendar.client.events.logging.CalendarClientVisualElementMetadata.Builder builder1;
        visualelement = (ConferenceVisualElement)visualelement;
        builder1 = (com.google.calendar.client.events.logging.CalendarClientVisualElementMetadata.Builder)builder;
        if (((ConferenceVisualElement) (visualelement)).localPhoneSource == null)
        {
            break MISSING_BLOCK_LABEL_209;
        }
        builder = (com.google.calendar.client.events.logging.ConferenceMetadata.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)ConferenceMetadata.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        ((ConferenceVisualElement) (visualelement)).localPhoneSource.ordinal();
        JVM INSTR tableswitch 0 5: default 80
    //                   0 116
    //                   1 123
    //                   2 130
    //                   3 137
    //                   4 144
    //                   5 109;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        visualelement = ConferencePhoneNumberLocationSource.UNKNOWN_LOCATION_SOURCE;
_L9:
        ConferenceMetadata conferencemetadata;
        builder.copyOnWrite();
        conferencemetadata = (ConferenceMetadata)((com.google.calendar.client.events.logging.ConferenceMetadata.Builder) (builder)).instance;
        if (visualelement == null)
        {
            throw new NullPointerException();
        }
        break; /* Loop/switch isn't completed */
_L7:
        visualelement = ConferencePhoneNumberLocationSource.DEFAULT_EVENT_CONFERENCE_PHONE_NUMBER;
        continue; /* Loop/switch isn't completed */
_L2:
        visualelement = ConferencePhoneNumberLocationSource.CURRENT_CARRIER_NETWORK;
        continue; /* Loop/switch isn't completed */
_L3:
        visualelement = ConferencePhoneNumberLocationSource.CURRENT_CARRIER_NETWORK_FALLBACK;
        continue; /* Loop/switch isn't completed */
_L4:
        visualelement = ConferencePhoneNumberLocationSource.SIM_HOME_COUNTRY_NETWORK;
        continue; /* Loop/switch isn't completed */
_L5:
        visualelement = ConferencePhoneNumberLocationSource.SIM_HOME_COUNTRY_NETWORK_FALLBACK;
        continue; /* Loop/switch isn't completed */
_L6:
        visualelement = ConferencePhoneNumberLocationSource.UNKNOWN_LOCATION_SOURCE;
        if (true) goto _L9; else goto _L8
_L8:
        conferencemetadata.bitField0_ = conferencemetadata.bitField0_ | 1;
        conferencemetadata.phoneNumberLocationSource_ = ((ConferencePhoneNumberLocationSource) (visualelement)).value;
        builder1.copyOnWrite();
        visualelement = (CalendarClientVisualElementMetadata)builder1.instance;
        visualelement.conferenceMetadata_ = (ConferenceMetadata)(GeneratedMessageLite)builder.build();
        visualelement.bitField0_ = ((CalendarClientVisualElementMetadata) (visualelement)).bitField0_ | 8;
    }
}
