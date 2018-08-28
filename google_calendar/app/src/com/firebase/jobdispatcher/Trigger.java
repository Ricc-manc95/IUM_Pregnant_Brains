// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import java.util.List;

public final class Trigger
{

    public static final JobTrigger.ImmediateTrigger NOW = new JobTrigger.ImmediateTrigger();

    public static JobTrigger.ContentUriTrigger contentUriTrigger(List list)
    {
        if (list == null || list.isEmpty())
        {
            throw new IllegalArgumentException("Uris must not be null or empty.");
        } else
        {
            return new JobTrigger.ContentUriTrigger(list);
        }
    }

    public static JobTrigger.ExecutionWindowTrigger executionWindow(int i, int j)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException("Window start can't be less than 0");
        }
        if (j < i)
        {
            throw new IllegalArgumentException("Window end can't be less than window start");
        } else
        {
            return new JobTrigger.ExecutionWindowTrigger(i, j);
        }
    }

}
