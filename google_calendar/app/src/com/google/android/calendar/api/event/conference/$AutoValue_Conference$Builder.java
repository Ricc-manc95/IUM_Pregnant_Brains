// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;


// Referenced classes of package com.google.android.calendar.api.event.conference:
//            AutoValue_Conference, Conference

final class  extends 
{

    private String accessCode;
    private String entryPointType;
    private Integer gatewayAccess;
    private String label;
    private String meetingCode;
    private String name;
    private String passCode;
    private String passcode;
    private String password;
    private String pin;
    private String regionCode;
    private Integer type;
    private String uri;

    final Conference autoBuild()
    {
        String s2 = "";
        if (type == null)
        {
            s2 = String.valueOf("").concat(" type");
        }
        String s = s2;
        if (uri == null)
        {
            s = String.valueOf(s2).concat(" uri");
        }
        s2 = s;
        if (name == null)
        {
            s2 = String.valueOf(s).concat(" name");
        }
        s = s2;
        if (passCode == null)
        {
            s = String.valueOf(s2).concat(" passCode");
        }
        s2 = s;
        if (regionCode == null)
        {
            s2 = String.valueOf(s).concat(" regionCode");
        }
        s = s2;
        if (accessCode == null)
        {
            s = String.valueOf(s2).concat(" accessCode");
        }
        s2 = s;
        if (entryPointType == null)
        {
            s2 = String.valueOf(s).concat(" entryPointType");
        }
        s = s2;
        if (label == null)
        {
            s = String.valueOf(s2).concat(" label");
        }
        s2 = s;
        if (meetingCode == null)
        {
            s2 = String.valueOf(s).concat(" meetingCode");
        }
        s = s2;
        if (passcode == null)
        {
            s = String.valueOf(s2).concat(" passcode");
        }
        s2 = s;
        if (password == null)
        {
            s2 = String.valueOf(s).concat(" password");
        }
        s = s2;
        if (pin == null)
        {
            s = String.valueOf(s2).concat(" pin");
        }
        s2 = s;
        if (gatewayAccess == null)
        {
            s2 = String.valueOf(s).concat(" gatewayAccess");
        }
        if (!s2.isEmpty())
        {
            String s1 = String.valueOf(s2);
            if (s1.length() != 0)
            {
                s1 = "Missing required properties:".concat(s1);
            } else
            {
                s1 = new String("Missing required properties:");
            }
            throw new IllegalStateException(s1);
        } else
        {
            return new AutoValue_Conference(type.intValue(), uri, name, passCode, regionCode, accessCode, entryPointType, label, meetingCode, passcode, password, pin, gatewayAccess.intValue());
        }
    }

    final String getRegionCode()
    {
        if (regionCode == null)
        {
            throw new IllegalStateException("Property \"regionCode\" has not been set");
        } else
        {
            return regionCode;
        }
    }

    public final regionCode setAccessCode(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null accessCode");
        } else
        {
            accessCode = s;
            return this;
        }
    }

    public final accessCode setEntryPointType(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null entryPointType");
        } else
        {
            entryPointType = s;
            return this;
        }
    }

    public final entryPointType setGatewayAccess(int i)
    {
        gatewayAccess = Integer.valueOf(i);
        return this;
    }

    public final gatewayAccess setLabel(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null label");
        } else
        {
            label = s;
            return this;
        }
    }

    public final label setMeetingCode(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null meetingCode");
        } else
        {
            meetingCode = s;
            return this;
        }
    }

    public final meetingCode setName(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null name");
        } else
        {
            name = s;
            return this;
        }
    }

    public final name setPassCode(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null passCode");
        } else
        {
            passCode = s;
            return this;
        }
    }

    public final passCode setPasscode(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null passcode");
        } else
        {
            passcode = s;
            return this;
        }
    }

    public final passcode setPassword(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null password");
        } else
        {
            password = s;
            return this;
        }
    }

    public final password setPin(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null pin");
        } else
        {
            pin = s;
            return this;
        }
    }

    public final pin setRegionCode(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null regionCode");
        } else
        {
            regionCode = s;
            return this;
        }
    }

    public final regionCode setType(int i)
    {
        type = Integer.valueOf(i);
        return this;
    }

    public final type setUri(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null uri");
        } else
        {
            uri = s;
            return this;
        }
    }

    ()
    {
    }
}
