// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.userstatus;


// Referenced classes of package com.google.android.calendar.api.event.userstatus:
//            UserStatus, OutOfOffice

abstract class $AutoValue_UserStatus extends UserStatus
{

    private final OutOfOffice outOfOffice;

    $AutoValue_UserStatus(OutOfOffice outofoffice)
    {
        outOfOffice = outofoffice;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof UserStatus)
            {
                obj = (UserStatus)obj;
                if (outOfOffice == null)
                {
                    if (((UserStatus) (obj)).getOutOfOffice() != null)
                    {
                        return false;
                    }
                } else
                {
                    return outOfOffice.equals(((UserStatus) (obj)).getOutOfOffice());
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final OutOfOffice getOutOfOffice()
    {
        return outOfOffice;
    }

    public int hashCode()
    {
        int i;
        if (outOfOffice == null)
        {
            i = 0;
        } else
        {
            i = outOfOffice.hashCode();
        }
        return i ^ 0xf4243;
    }

    public String toString()
    {
        String s = String.valueOf(outOfOffice);
        return (new StringBuilder(String.valueOf(s).length() + 24)).append("UserStatus{outOfOffice=").append(s).append("}").toString();
    }
}
