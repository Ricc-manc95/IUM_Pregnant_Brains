// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;


// Referenced classes of package com.google.android.calendar.api.event.conference:
//            Conference

abstract class $AutoValue_Conference extends Conference
{

    private final String accessCode;
    private final String entryPointType;
    private final int gatewayAccess;
    private final String label;
    private final String meetingCode;
    private final String name;
    private final String passCode;
    private final String passcode;
    private final String password;
    private final String pin;
    private final String regionCode;
    private final int type;
    private final String uri;

    $AutoValue_Conference(int i, String s, String s1, String s2, String s3, String s4, String s5, 
            String s6, String s7, String s8, String s9, String s10, int j)
    {
        type = i;
        if (s == null)
        {
            throw new NullPointerException("Null uri");
        }
        uri = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null name");
        }
        name = s1;
        if (s2 == null)
        {
            throw new NullPointerException("Null passCode");
        }
        passCode = s2;
        if (s3 == null)
        {
            throw new NullPointerException("Null regionCode");
        }
        regionCode = s3;
        if (s4 == null)
        {
            throw new NullPointerException("Null accessCode");
        }
        accessCode = s4;
        if (s5 == null)
        {
            throw new NullPointerException("Null entryPointType");
        }
        entryPointType = s5;
        if (s6 == null)
        {
            throw new NullPointerException("Null label");
        }
        label = s6;
        if (s7 == null)
        {
            throw new NullPointerException("Null meetingCode");
        }
        meetingCode = s7;
        if (s8 == null)
        {
            throw new NullPointerException("Null passcode");
        }
        passcode = s8;
        if (s9 == null)
        {
            throw new NullPointerException("Null password");
        }
        password = s9;
        if (s10 == null)
        {
            throw new NullPointerException("Null pin");
        } else
        {
            pin = s10;
            gatewayAccess = j;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof Conference)
            {
                if (type != ((Conference) (obj = (Conference)obj)).getType() || !uri.equals(((Conference) (obj)).getUri()) || !name.equals(((Conference) (obj)).getName()) || !passCode.equals(((Conference) (obj)).getPassCode()) || !regionCode.equals(((Conference) (obj)).getRegionCode()) || !accessCode.equals(((Conference) (obj)).getAccessCode()) || !entryPointType.equals(((Conference) (obj)).getEntryPointType()) || !label.equals(((Conference) (obj)).getLabel()) || !meetingCode.equals(((Conference) (obj)).getMeetingCode()) || !passcode.equals(((Conference) (obj)).getPasscode()) || !password.equals(((Conference) (obj)).getPassword()) || !pin.equals(((Conference) (obj)).getPin()) || gatewayAccess != ((Conference) (obj)).getGatewayAccess())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final String getAccessCode()
    {
        return accessCode;
    }

    public final String getEntryPointType()
    {
        return entryPointType;
    }

    public final int getGatewayAccess()
    {
        return gatewayAccess;
    }

    public final String getLabel()
    {
        return label;
    }

    public final String getMeetingCode()
    {
        return meetingCode;
    }

    public final String getName()
    {
        return name;
    }

    public final String getPassCode()
    {
        return passCode;
    }

    public final String getPasscode()
    {
        return passcode;
    }

    public final String getPassword()
    {
        return password;
    }

    public final String getPin()
    {
        return pin;
    }

    public final String getRegionCode()
    {
        return regionCode;
    }

    public final int getType()
    {
        return type;
    }

    public final String getUri()
    {
        return uri;
    }

    public int hashCode()
    {
        return ((((((((((((type ^ 0xf4243) * 0xf4243 ^ uri.hashCode()) * 0xf4243 ^ name.hashCode()) * 0xf4243 ^ passCode.hashCode()) * 0xf4243 ^ regionCode.hashCode()) * 0xf4243 ^ accessCode.hashCode()) * 0xf4243 ^ entryPointType.hashCode()) * 0xf4243 ^ label.hashCode()) * 0xf4243 ^ meetingCode.hashCode()) * 0xf4243 ^ passcode.hashCode()) * 0xf4243 ^ password.hashCode()) * 0xf4243 ^ pin.hashCode()) * 0xf4243 ^ gatewayAccess;
    }

    public String toString()
    {
        int i = type;
        String s = uri;
        String s1 = name;
        String s2 = passCode;
        String s3 = regionCode;
        String s4 = accessCode;
        String s5 = entryPointType;
        String s6 = label;
        String s7 = meetingCode;
        String s8 = passcode;
        String s9 = password;
        String s10 = pin;
        int j = gatewayAccess;
        return (new StringBuilder(String.valueOf(s).length() + 172 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length() + String.valueOf(s6).length() + String.valueOf(s7).length() + String.valueOf(s8).length() + String.valueOf(s9).length() + String.valueOf(s10).length())).append("Conference{type=").append(i).append(", uri=").append(s).append(", name=").append(s1).append(", passCode=").append(s2).append(", regionCode=").append(s3).append(", accessCode=").append(s4).append(", entryPointType=").append(s5).append(", label=").append(s6).append(", meetingCode=").append(s7).append(", passcode=").append(s8).append(", password=").append(s9).append(", pin=").append(s10).append(", gatewayAccess=").append(j).append("}").toString();
    }
}
