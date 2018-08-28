// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarKey;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            Calendar

public final class AutoValue_Calendar extends Calendar
{

    private final CalendarAccessLevel accessLevel;
    private final String accountName;
    private final String accountType;
    private final CalendarKey key;
    private final String ownerAccount;

    public AutoValue_Calendar(CalendarKey calendarkey, CalendarAccessLevel calendaraccesslevel, String s, String s1, String s2)
    {
        if (calendarkey == null)
        {
            throw new NullPointerException("Null key");
        }
        key = calendarkey;
        if (calendaraccesslevel == null)
        {
            throw new NullPointerException("Null accessLevel");
        } else
        {
            accessLevel = calendaraccesslevel;
            ownerAccount = s;
            accountType = s1;
            accountName = s2;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof Calendar))
            {
                break MISSING_BLOCK_LABEL_141;
            }
            obj = (Calendar)obj;
            if (key.equals(((Calendar) (obj)).getKey()) && accessLevel.equals(((Calendar) (obj)).getAccessLevel()) && (ownerAccount != null ? ownerAccount.equals(((Calendar) (obj)).getOwnerAccount()) : ((Calendar) (obj)).getOwnerAccount() == null) && (accountType != null ? accountType.equals(((Calendar) (obj)).getAccountType()) : ((Calendar) (obj)).getAccountType() == null))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (accountName != null) goto _L4; else goto _L3
_L3:
        if (((Calendar) (obj)).getAccountName() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!accountName.equals(((Calendar) (obj)).getAccountName())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final CalendarAccessLevel getAccessLevel()
    {
        return accessLevel;
    }

    public final String getAccountName()
    {
        return accountName;
    }

    public final String getAccountType()
    {
        return accountType;
    }

    public final CalendarKey getKey()
    {
        return key;
    }

    public final String getOwnerAccount()
    {
        return ownerAccount;
    }

    public final int hashCode()
    {
        int k = 0;
        int l = key.hashCode();
        int i1 = accessLevel.hashCode();
        int i;
        int j;
        if (ownerAccount == null)
        {
            i = 0;
        } else
        {
            i = ownerAccount.hashCode();
        }
        if (accountType == null)
        {
            j = 0;
        } else
        {
            j = accountType.hashCode();
        }
        if (accountName != null)
        {
            k = accountName.hashCode();
        }
        return (j ^ (i ^ ((l ^ 0xf4243) * 0xf4243 ^ i1) * 0xf4243) * 0xf4243) * 0xf4243 ^ k;
    }

    public final String toString()
    {
        String s = String.valueOf(key);
        String s1 = String.valueOf(accessLevel);
        String s2 = ownerAccount;
        String s3 = accountType;
        String s4 = accountName;
        return (new StringBuilder(String.valueOf(s).length() + 71 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length())).append("Calendar{key=").append(s).append(", accessLevel=").append(s1).append(", ownerAccount=").append(s2).append(", accountType=").append(s3).append(", accountName=").append(s4).append("}").toString();
    }
}
