// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;


// Referenced classes of package com.google.android.calendar.task.assist:
//            ParsedOpeningHours

static final class end
    implements Comparable
{

    public int end;
    public int start;

    public final int compareTo(Object obj)
    {
        obj = (end)obj;
        if (start != ((start) (obj)).start)
        {
            return start - ((start) (obj)).start;
        } else
        {
            return end - ((end) (obj)).end;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (end)obj;
            if (start != ((start) (obj)).start || end != ((end) (obj)).end)
            {
                return false;
            }
        }
        return true;
    }

    public I(int i, int j)
    {
        start = i;
        end = j;
    }
}
