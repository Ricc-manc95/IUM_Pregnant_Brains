// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import com.google.common.collect.ImmutableSet;

final class AutoValue_TriggeringEventProcessor_TargetingData extends TriggeringEventProcessor.TargetingData
{

    private final ImmutableSet appStateIds;
    private final ImmutableSet clearcutEvents;
    private final ImmutableSet veEvents;

    AutoValue_TriggeringEventProcessor_TargetingData(ImmutableSet immutableset, ImmutableSet immutableset1, ImmutableSet immutableset2)
    {
        clearcutEvents = immutableset;
        veEvents = immutableset1;
        appStateIds = immutableset2;
    }

    final ImmutableSet appStateIds()
    {
        return appStateIds;
    }

    final ImmutableSet clearcutEvents()
    {
        return clearcutEvents;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TriggeringEventProcessor.TargetingData)
            {
                if (!clearcutEvents.equals(((TriggeringEventProcessor.TargetingData) (obj = (TriggeringEventProcessor.TargetingData)obj)).clearcutEvents()) || !veEvents.equals(((TriggeringEventProcessor.TargetingData) (obj)).veEvents()) || !appStateIds.equals(((TriggeringEventProcessor.TargetingData) (obj)).appStateIds()))
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

    public final int hashCode()
    {
        return ((clearcutEvents.hashCode() ^ 0xf4243) * 0xf4243 ^ veEvents.hashCode()) * 0xf4243 ^ appStateIds.hashCode();
    }

    public final String toString()
    {
        String s = String.valueOf(clearcutEvents);
        String s1 = String.valueOf(veEvents);
        String s2 = String.valueOf(appStateIds);
        return (new StringBuilder(String.valueOf(s).length() + 55 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("TargetingData{clearcutEvents=").append(s).append(", veEvents=").append(s1).append(", appStateIds=").append(s2).append("}").toString();
    }

    final ImmutableSet veEvents()
    {
        return veEvents;
    }
}
