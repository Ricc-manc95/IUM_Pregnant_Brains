// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.userstatus;


// Referenced classes of package com.google.android.calendar.api.event.userstatus:
//            AutoReply

abstract class $AutoValue_AutoReply extends AutoReply
{

    private final String body;
    private final boolean enabled;
    private final boolean restrictToContacts;
    private final boolean restrictToDomain;
    private final String subject;

    $AutoValue_AutoReply(boolean flag, String s, String s1, boolean flag1, boolean flag2)
    {
        enabled = flag;
        subject = s;
        body = s1;
        restrictToContacts = flag1;
        restrictToDomain = flag2;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof AutoReply)
            {
                if (enabled != ((AutoReply) (obj = (AutoReply)obj)).isEnabled() || (subject != null ? !subject.equals(((AutoReply) (obj)).getSubject()) : ((AutoReply) (obj)).getSubject() != null) || (body != null ? !body.equals(((AutoReply) (obj)).getBody()) : ((AutoReply) (obj)).getBody() != null) || (restrictToContacts != ((AutoReply) (obj)).isRestrictToContacts() || restrictToDomain != ((AutoReply) (obj)).isRestrictToDomain()))
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

    public final String getBody()
    {
        return body;
    }

    public final String getSubject()
    {
        return subject;
    }

    public int hashCode()
    {
        int j = 0;
        char c2 = '\u04CF';
        char c;
        int i;
        char c1;
        if (enabled)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (subject == null)
        {
            i = 0;
        } else
        {
            i = subject.hashCode();
        }
        if (body != null)
        {
            j = body.hashCode();
        }
        if (restrictToContacts)
        {
            c1 = '\u04CF';
        } else
        {
            c1 = '\u04D5';
        }
        if (!restrictToDomain)
        {
            c2 = '\u04D5';
        }
        return (c1 ^ ((i ^ (c ^ 0xf4243) * 0xf4243) * 0xf4243 ^ j) * 0xf4243) * 0xf4243 ^ c2;
    }

    public final boolean isEnabled()
    {
        return enabled;
    }

    public final boolean isRestrictToContacts()
    {
        return restrictToContacts;
    }

    public final boolean isRestrictToDomain()
    {
        return restrictToDomain;
    }

    public String toString()
    {
        boolean flag = enabled;
        String s = subject;
        String s1 = body;
        boolean flag1 = restrictToContacts;
        boolean flag2 = restrictToDomain;
        return (new StringBuilder(String.valueOf(s).length() + 91 + String.valueOf(s1).length())).append("AutoReply{enabled=").append(flag).append(", subject=").append(s).append(", body=").append(s1).append(", restrictToContacts=").append(flag1).append(", restrictToDomain=").append(flag2).append("}").toString();
    }
}
