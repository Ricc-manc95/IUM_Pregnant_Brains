// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeAttendee

public static final class email
    implements Comparator
{

    private final String email;

    public final int compare(Object obj, Object obj1)
    {
        int j = 0;
        obj = (FindTimeAttendee)obj;
        obj1 = (FindTimeAttendee)obj1;
        int i;
        if (obj != null && email.equals(((FindTimeAttendee) (obj)).email))
        {
            i = 0;
        } else
        {
            i = 1;
        }
        if (obj1 == null || !email.equals(((FindTimeAttendee) (obj1)).email))
        {
            j = 1;
        }
        return i - j;
    }

    public (String s)
    {
        email = s;
    }
}
