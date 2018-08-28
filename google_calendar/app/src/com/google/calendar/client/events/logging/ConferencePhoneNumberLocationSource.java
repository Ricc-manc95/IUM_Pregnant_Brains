// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.client.events.logging;


public final class ConferencePhoneNumberLocationSource extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final ConferencePhoneNumberLocationSource $VALUES[];
    public static final ConferencePhoneNumberLocationSource CURRENT_CARRIER_NETWORK;
    public static final ConferencePhoneNumberLocationSource CURRENT_CARRIER_NETWORK_FALLBACK;
    public static final ConferencePhoneNumberLocationSource DEFAULT_EVENT_CONFERENCE_PHONE_NUMBER;
    private static final ConferencePhoneNumberLocationSource GPS;
    private static final ConferencePhoneNumberLocationSource GPS_FALLBACK;
    public static final ConferencePhoneNumberLocationSource SIM_HOME_COUNTRY_NETWORK;
    public static final ConferencePhoneNumberLocationSource SIM_HOME_COUNTRY_NETWORK_FALLBACK;
    public static final ConferencePhoneNumberLocationSource UNKNOWN_LOCATION_SOURCE;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    public final int value;

    private ConferencePhoneNumberLocationSource(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static ConferencePhoneNumberLocationSource forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN_LOCATION_SOURCE;

        case 1: // '\001'
            return GPS;

        case 2: // '\002'
            return GPS_FALLBACK;

        case 3: // '\003'
            return CURRENT_CARRIER_NETWORK;

        case 4: // '\004'
            return CURRENT_CARRIER_NETWORK_FALLBACK;

        case 5: // '\005'
            return SIM_HOME_COUNTRY_NETWORK;

        case 6: // '\006'
            return SIM_HOME_COUNTRY_NETWORK_FALLBACK;

        case 7: // '\007'
            return DEFAULT_EVENT_CONFERENCE_PHONE_NUMBER;
        }
    }

    public static ConferencePhoneNumberLocationSource[] values()
    {
        return (ConferencePhoneNumberLocationSource[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN_LOCATION_SOURCE = new ConferencePhoneNumberLocationSource("UNKNOWN_LOCATION_SOURCE", 0, 0);
        GPS = new ConferencePhoneNumberLocationSource("GPS", 1, 1);
        GPS_FALLBACK = new ConferencePhoneNumberLocationSource("GPS_FALLBACK", 2, 2);
        CURRENT_CARRIER_NETWORK = new ConferencePhoneNumberLocationSource("CURRENT_CARRIER_NETWORK", 3, 3);
        CURRENT_CARRIER_NETWORK_FALLBACK = new ConferencePhoneNumberLocationSource("CURRENT_CARRIER_NETWORK_FALLBACK", 4, 4);
        SIM_HOME_COUNTRY_NETWORK = new ConferencePhoneNumberLocationSource("SIM_HOME_COUNTRY_NETWORK", 5, 5);
        SIM_HOME_COUNTRY_NETWORK_FALLBACK = new ConferencePhoneNumberLocationSource("SIM_HOME_COUNTRY_NETWORK_FALLBACK", 6, 6);
        DEFAULT_EVENT_CONFERENCE_PHONE_NUMBER = new ConferencePhoneNumberLocationSource("DEFAULT_EVENT_CONFERENCE_PHONE_NUMBER", 7, 7);
        $VALUES = (new ConferencePhoneNumberLocationSource[] {
            UNKNOWN_LOCATION_SOURCE, GPS, GPS_FALLBACK, CURRENT_CARRIER_NETWORK, CURRENT_CARRIER_NETWORK_FALLBACK, SIM_HOME_COUNTRY_NETWORK, SIM_HOME_COUNTRY_NETWORK_FALLBACK, DEFAULT_EVENT_CONFERENCE_PHONE_NUMBER
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return ConferencePhoneNumberLocationSource.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
