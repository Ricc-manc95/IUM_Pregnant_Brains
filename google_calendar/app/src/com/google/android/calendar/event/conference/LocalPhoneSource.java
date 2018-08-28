// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.conference;


public final class LocalPhoneSource extends Enum
{

    private static final LocalPhoneSource $VALUES[];
    public static final LocalPhoneSource EVENT_DEFAULT;
    public static final LocalPhoneSource FULL_LIST;
    public static final LocalPhoneSource LOCAL_NETWORK;
    public static final LocalPhoneSource LOCAL_SIM;
    public static final LocalPhoneSource REGIONAL_NETWORK;
    public static final LocalPhoneSource REGIONAL_SIM;

    private LocalPhoneSource(String s, int i)
    {
        super(s, i);
    }

    public static LocalPhoneSource valueOf(String s)
    {
        return (LocalPhoneSource)Enum.valueOf(com/google/android/calendar/event/conference/LocalPhoneSource, s);
    }

    public static LocalPhoneSource[] values()
    {
        return (LocalPhoneSource[])$VALUES.clone();
    }

    static 
    {
        LOCAL_NETWORK = new LocalPhoneSource("LOCAL_NETWORK", 0);
        LOCAL_SIM = new LocalPhoneSource("LOCAL_SIM", 1);
        REGIONAL_NETWORK = new LocalPhoneSource("REGIONAL_NETWORK", 2);
        REGIONAL_SIM = new LocalPhoneSource("REGIONAL_SIM", 3);
        FULL_LIST = new LocalPhoneSource("FULL_LIST", 4);
        EVENT_DEFAULT = new LocalPhoneSource("EVENT_DEFAULT", 5);
        $VALUES = (new LocalPhoneSource[] {
            LOCAL_NETWORK, LOCAL_SIM, REGIONAL_NETWORK, REGIONAL_SIM, FULL_LIST, EVENT_DEFAULT
        });
    }
}
