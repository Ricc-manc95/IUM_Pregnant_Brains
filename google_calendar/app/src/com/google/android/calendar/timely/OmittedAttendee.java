// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.text.TextUtils;
import java.util.Arrays;

public final class OmittedAttendee
{

    private final String attendeeEmail;
    private final String displayName;
    public final int reason;

    public OmittedAttendee(String s, String s1, int i)
    {
        attendeeEmail = s;
        displayName = s1;
        reason = i;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof OmittedAttendee)
        {
            obj = (OmittedAttendee)obj;
            String s = attendeeEmail;
            String s2 = ((OmittedAttendee) (obj)).attendeeEmail;
            boolean flag;
            if (s == s2 || s != null && s.equals(s2))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                String s1 = displayName;
                String s3 = ((OmittedAttendee) (obj)).displayName;
                if (s1 == s3 || s1 != null && s1.equals(s3))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && reason == ((OmittedAttendee) (obj)).reason)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            attendeeEmail, displayName, Integer.valueOf(reason)
        });
    }

    public final String toString()
    {
        if (TextUtils.isEmpty(displayName))
        {
            return attendeeEmail;
        } else
        {
            return displayName;
        }
    }
}
