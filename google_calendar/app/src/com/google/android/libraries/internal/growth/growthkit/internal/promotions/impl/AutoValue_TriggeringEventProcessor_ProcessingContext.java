// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.promotions.impl;

import com.google.protobuf.GeneratedMessageLite;

final class AutoValue_TriggeringEventProcessor_ProcessingContext extends TriggeringEventProcessor.ProcessingContext
{

    private final String accountName;
    private final com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent event;
    private final long eventTimeMs;

    AutoValue_TriggeringEventProcessor_ProcessingContext(com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent triggeringevent, String s, long l)
    {
        if (triggeringevent == null)
        {
            throw new NullPointerException("Null event");
        } else
        {
            event = triggeringevent;
            accountName = s;
            eventTimeMs = l;
            return;
        }
    }

    final String accountName()
    {
        return accountName;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TriggeringEventProcessor.ProcessingContext)
            {
                if (!event.equals(((TriggeringEventProcessor.ProcessingContext) (obj = (TriggeringEventProcessor.ProcessingContext)obj)).event()) || (accountName != null ? !accountName.equals(((TriggeringEventProcessor.ProcessingContext) (obj)).accountName()) : ((TriggeringEventProcessor.ProcessingContext) (obj)).accountName() != null) || eventTimeMs != ((TriggeringEventProcessor.ProcessingContext) (obj)).eventTimeMs())
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

    final com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringEvent event()
    {
        return event;
    }

    final long eventTimeMs()
    {
        return eventTimeMs;
    }

    public final int hashCode()
    {
        int j = event.hashCode();
        int i;
        if (accountName == null)
        {
            i = 0;
        } else
        {
            i = accountName.hashCode();
        }
        return (i ^ (j ^ 0xf4243) * 0xf4243) * 0xf4243 ^ (int)(eventTimeMs >>> 32 ^ eventTimeMs);
    }

    public final String toString()
    {
        String s = String.valueOf(event);
        String s1 = accountName;
        long l = eventTimeMs;
        return (new StringBuilder(String.valueOf(s).length() + 73 + String.valueOf(s1).length())).append("ProcessingContext{event=").append(s).append(", accountName=").append(s1).append(", eventTimeMs=").append(l).append("}").toString();
    }
}
