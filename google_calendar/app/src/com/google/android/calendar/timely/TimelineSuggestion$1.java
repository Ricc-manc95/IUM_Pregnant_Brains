// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.text.TextUtils;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeAttendee

final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        boolean flag = false;
        obj = (FindTimeAttendee)obj;
        obj1 = (FindTimeAttendee)obj1;
        int i;
        int j;
        if (((FindTimeAttendee) (obj)).isOrganizer)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (((FindTimeAttendee) (obj1)).isOrganizer)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (i + j == 1)
        {
            return j - i;
        }
        if (((FindTimeAttendee) (obj)).email.endsWith("resource.calendar.google.com"))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        j = ((flag) ? 1 : 0);
        if (((FindTimeAttendee) (obj1)).email.endsWith("resource.calendar.google.com"))
        {
            j = 1;
        }
        if (i + j == 1)
        {
            return i - j;
        }
        Comparator comparator = String.CASE_INSENSITIVE_ORDER;
        if (TextUtils.isEmpty(((FindTimeAttendee) (obj)).displayName))
        {
            obj = ((FindTimeAttendee) (obj)).email;
        } else
        {
            obj = ((FindTimeAttendee) (obj)).displayName;
        }
        if (TextUtils.isEmpty(((FindTimeAttendee) (obj1)).displayName))
        {
            obj1 = ((FindTimeAttendee) (obj1)).email;
        } else
        {
            obj1 = ((FindTimeAttendee) (obj1)).displayName;
        }
        return comparator.compare(obj, obj1);
    }

    ()
    {
    }
}
