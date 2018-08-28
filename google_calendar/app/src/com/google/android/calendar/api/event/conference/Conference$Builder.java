// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;


// Referenced classes of package com.google.android.calendar.api.event.conference:
//            Conference

public static abstract class I
{

    public abstract Conference autoBuild();

    public abstract String getRegionCode();

    public abstract I setAccessCode(String s);

    public abstract I setEntryPointType(String s);

    public abstract I setGatewayAccess(int i);

    public abstract I setLabel(String s);

    public abstract I setMeetingCode(String s);

    public abstract I setName(String s);

    public abstract I setPassCode(String s);

    public abstract I setPasscode(String s);

    public abstract I setPassword(String s);

    public abstract I setPin(String s);

    public abstract I setRegionCode(String s);

    public abstract I setType(int i);

    public abstract I setUri(String s);

    public I()
    {
    }
}
