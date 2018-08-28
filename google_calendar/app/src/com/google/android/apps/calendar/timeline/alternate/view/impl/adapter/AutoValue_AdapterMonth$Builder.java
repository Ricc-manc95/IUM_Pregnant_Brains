// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AutoValue_AdapterMonth, AdapterMonth

final class  extends 
{

    private ImmutableList days;

    public final AdapterMonth build()
    {
        String s = "";
        if (days == null)
        {
            s = String.valueOf("").concat(" days");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_AdapterMonth(days);
        }
    }

    public final days setDays(ImmutableList immutablelist)
    {
        if (immutablelist == null)
        {
            throw new NullPointerException("Null days");
        } else
        {
            days = immutablelist;
            return this;
        }
    }

    ()
    {
    }
}
