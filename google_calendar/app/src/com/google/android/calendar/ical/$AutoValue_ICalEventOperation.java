// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventOperation

abstract class $AutoValue_ICalEventOperation extends ICalEventOperation
{

    private final boolean canceled;
    private final ImmutableList events;
    private final int getImportType;

    $AutoValue_ICalEventOperation(ImmutableList immutablelist, boolean flag, int i)
    {
        if (immutablelist == null)
        {
            throw new NullPointerException("Null events");
        } else
        {
            events = immutablelist;
            canceled = flag;
            getImportType = i;
            return;
        }
    }

    public final boolean canceled()
    {
        return canceled;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ICalEventOperation)
            {
                if (!events.equals(((ICalEventOperation) (obj = (ICalEventOperation)obj)).events()) || canceled != ((ICalEventOperation) (obj)).canceled() || getImportType != ((ICalEventOperation) (obj)).getImportType())
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

    public final ImmutableList events()
    {
        return events;
    }

    public final int getImportType()
    {
        return getImportType;
    }

    public int hashCode()
    {
        int i = events.hashCode();
        char c;
        if (canceled)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return (c ^ (i ^ 0xf4243) * 0xf4243) * 0xf4243 ^ getImportType;
    }

    public String toString()
    {
        String s = String.valueOf(events);
        boolean flag = canceled;
        int i = getImportType;
        return (new StringBuilder(String.valueOf(s).length() + 70)).append("ICalEventOperation{events=").append(s).append(", canceled=").append(flag).append(", getImportType=").append(i).append("}").toString();
    }
}
