// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.common.collect.ImmutableSet;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            Item

public abstract class BirthdaySet
    implements Item
{

    public BirthdaySet()
    {
    }

    public abstract ImmutableSet getItems();

    public final Item.SortType getSortType()
    {
        return Item.SortType.BIRTHDAY;
    }
}
