// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.userstatus;


// Referenced classes of package com.google.android.calendar.api.event.userstatus:
//            AutoValue_AutoReply, AutoReply

public final class  extends 
{

    private String body;
    private Boolean enabled;
    private Boolean restrictToContacts;
    private Boolean restrictToDomain;
    private String subject;

    public final AutoReply build()
    {
        String s2 = "";
        if (enabled == null)
        {
            s2 = String.valueOf("").concat(" enabled");
        }
        String s = s2;
        if (restrictToContacts == null)
        {
            s = String.valueOf(s2).concat(" restrictToContacts");
        }
        s2 = s;
        if (restrictToDomain == null)
        {
            s2 = String.valueOf(s).concat(" restrictToDomain");
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
            return new AutoValue_AutoReply(enabled.booleanValue(), subject, body, restrictToContacts.booleanValue(), restrictToDomain.booleanValue());
        }
    }

    public final restrictToDomain setBody(String s)
    {
        body = s;
        return this;
    }

    public final body setEnabled(boolean flag)
    {
        enabled = Boolean.valueOf(flag);
        return this;
    }

    public final enabled setRestrictToContacts(boolean flag)
    {
        restrictToContacts = Boolean.valueOf(flag);
        return this;
    }

    public final restrictToContacts setRestrictToDomain(boolean flag)
    {
        restrictToDomain = Boolean.valueOf(flag);
        return this;
    }

    public final restrictToDomain setSubject(String s)
    {
        subject = s;
        return this;
    }

    public ()
    {
    }
}
