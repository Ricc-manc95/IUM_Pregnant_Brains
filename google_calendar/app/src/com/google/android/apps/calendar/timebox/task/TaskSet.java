// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.Item;
import com.google.common.collect.ImmutableList;

public abstract class TaskSet
    implements Item
{

    public TaskSet()
    {
    }

    public abstract ImmutableList getItems();

    public final com.google.android.apps.calendar.timebox.Item.SortType getSortType()
    {
        if (isDone())
        {
            return com.google.android.apps.calendar.timebox.Item.SortType.DONE_REMINDER_BUNDLE;
        } else
        {
            return com.google.android.apps.calendar.timebox.Item.SortType.NOT_DONE_REMINDER_BUNDLE;
        }
    }

    public abstract boolean isDone();

    public abstract Builder toBuilder();
}
