// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.common.collect.ImmutableSet;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            BirthdaySet

final class AutoValue_BirthdaySet extends BirthdaySet
{

    private final ImmutableSet items;

    AutoValue_BirthdaySet(ImmutableSet immutableset)
    {
        items = immutableset;
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof BirthdaySet)
        {
            obj = (BirthdaySet)obj;
            return items.equals(((BirthdaySet) (obj)).getItems());
        } else
        {
            return false;
        }
    }

    public final ImmutableSet getItems()
    {
        return items;
    }

    public final int hashCode()
    {
        return 0xf4243 ^ items.hashCode();
    }

    public final String toString()
    {
        String s = String.valueOf(items);
        return (new StringBuilder(String.valueOf(s).length() + 19)).append("BirthdaySet{items=").append(s).append("}").toString();
    }
}
