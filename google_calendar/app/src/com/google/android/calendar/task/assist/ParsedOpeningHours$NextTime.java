// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.task.assist:
//            ParsedOpeningHours

static final class weekSecond
{

    public final int duration;
    public final boolean isOpen;
    public final int weekSecond;

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (weekSecond)obj;
            if (isOpen != ((isOpen) (obj)).isOpen || duration != ((duration) (obj)).duration || weekSecond != ((weekSecond) (obj)).weekSecond)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Boolean.valueOf(isOpen), Integer.valueOf(duration), Integer.valueOf(weekSecond)
        });
    }

    public I(boolean flag, int i, int j)
    {
        isOpen = flag;
        duration = i;
        weekSecond = j;
    }
}
