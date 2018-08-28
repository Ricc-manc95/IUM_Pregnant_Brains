// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;


// Referenced classes of package com.google.android.calendar.api.event:
//            EventResponseSummary

abstract class $AutoValue_EventResponseSummary extends EventResponseSummary
{

    private final int numAccepted;
    private final int numDeclined;
    private final int numNeedAction;
    private final int numTentative;

    $AutoValue_EventResponseSummary(int i, int j, int k, int l)
    {
        numAccepted = i;
        numDeclined = j;
        numNeedAction = k;
        numTentative = l;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof EventResponseSummary)
            {
                if (numAccepted != ((EventResponseSummary) (obj = (EventResponseSummary)obj)).getNumAccepted() || numDeclined != ((EventResponseSummary) (obj)).getNumDeclined() || numNeedAction != ((EventResponseSummary) (obj)).getNumNeedAction() || numTentative != ((EventResponseSummary) (obj)).getNumTentative())
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

    public final int getNumAccepted()
    {
        return numAccepted;
    }

    public final int getNumDeclined()
    {
        return numDeclined;
    }

    public final int getNumNeedAction()
    {
        return numNeedAction;
    }

    public final int getNumTentative()
    {
        return numTentative;
    }

    public int hashCode()
    {
        return (((numAccepted ^ 0xf4243) * 0xf4243 ^ numDeclined) * 0xf4243 ^ numNeedAction) * 0xf4243 ^ numTentative;
    }

    public String toString()
    {
        int i = numAccepted;
        int j = numDeclined;
        int k = numNeedAction;
        int l = numTentative;
        return (new StringBuilder(123)).append("EventResponseSummary{numAccepted=").append(i).append(", numDeclined=").append(j).append(", numNeedAction=").append(k).append(", numTentative=").append(l).append("}").toString();
    }
}
