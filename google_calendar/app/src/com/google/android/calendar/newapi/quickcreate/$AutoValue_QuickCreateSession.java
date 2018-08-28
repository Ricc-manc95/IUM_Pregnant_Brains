// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import android.accounts.Account;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            QuickCreateSession, QuickCreateType

abstract class $AutoValue_QuickCreateSession extends QuickCreateSession
{

    private final Account account;
    private final String id;
    private final QuickCreateType type;

    $AutoValue_QuickCreateSession(Account account1, String s, QuickCreateType quickcreatetype)
    {
        if (account1 == null)
        {
            throw new NullPointerException("Null account");
        }
        account = account1;
        if (s == null)
        {
            throw new NullPointerException("Null id");
        }
        id = s;
        if (quickcreatetype == null)
        {
            throw new NullPointerException("Null type");
        } else
        {
            type = quickcreatetype;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof QuickCreateSession)
            {
                if (!account.equals(((QuickCreateSession) (obj = (QuickCreateSession)obj)).getAccount()) || !id.equals(((QuickCreateSession) (obj)).getId()) || !type.equals(((QuickCreateSession) (obj)).getType()))
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

    public final Account getAccount()
    {
        return account;
    }

    public final String getId()
    {
        return id;
    }

    public final QuickCreateType getType()
    {
        return type;
    }

    public int hashCode()
    {
        return ((account.hashCode() ^ 0xf4243) * 0xf4243 ^ id.hashCode()) * 0xf4243 ^ type.hashCode();
    }

    public String toString()
    {
        String s = String.valueOf(account);
        String s1 = id;
        String s2 = String.valueOf(type);
        return (new StringBuilder(String.valueOf(s).length() + 40 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("QuickCreateSession{account=").append(s).append(", id=").append(s1).append(", type=").append(s2).append("}").toString();
    }
}
