// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.net;

import android.util.Pair;
import com.google.android.calendar.timely.FindTimeAttendee;
import com.google.common.collect.ComparisonChain;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely.findatime.net:
//            FindTimeExchangeClient

static final class organizerEmail
    implements Comparator
{

    private final String organizerEmail;

    public final int compare(Object obj, Object obj1)
    {
        obj = (Pair)obj;
        obj1 = (Pair)obj1;
        FindTimeAttendee findtimeattendee = (FindTimeAttendee)((Pair) (obj)).first;
        FindTimeAttendee findtimeattendee1 = (FindTimeAttendee)((Pair) (obj1)).first;
        if (organizerEmail.equals(findtimeattendee.email))
        {
            return -1;
        }
        if (organizerEmail.equals(findtimeattendee1.email))
        {
            return 1;
        } else
        {
            double d = ((Double)((Pair) (obj)).second).doubleValue();
            double d1 = ((Double)((Pair) (obj1)).second).doubleValue();
            return ComparisonChain.ACTIVE.compare(d1, d).compare(findtimeattendee.displayName, findtimeattendee1.displayName).result();
        }
    }

    public (String s)
    {
        organizerEmail = s;
    }
}
