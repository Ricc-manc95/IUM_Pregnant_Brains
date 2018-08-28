// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace.export;

import java.util.Map;

final class AutoValue_SampledSpanStore_PerSpanNameSummary extends SampledSpanStore.PerSpanNameSummary
{

    private final Map numbersOfErrorSampledSpans;
    private final Map numbersOfLatencySampledSpans;

    AutoValue_SampledSpanStore_PerSpanNameSummary(Map map, Map map1)
    {
        if (map == null)
        {
            throw new NullPointerException("Null numbersOfLatencySampledSpans");
        }
        numbersOfLatencySampledSpans = map;
        if (map1 == null)
        {
            throw new NullPointerException("Null numbersOfErrorSampledSpans");
        } else
        {
            numbersOfErrorSampledSpans = map1;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof SampledSpanStore.PerSpanNameSummary)
            {
                if (!numbersOfLatencySampledSpans.equals(((SampledSpanStore.PerSpanNameSummary) (obj = (SampledSpanStore.PerSpanNameSummary)obj)).getNumbersOfLatencySampledSpans()) || !numbersOfErrorSampledSpans.equals(((SampledSpanStore.PerSpanNameSummary) (obj)).getNumbersOfErrorSampledSpans()))
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

    public final Map getNumbersOfErrorSampledSpans()
    {
        return numbersOfErrorSampledSpans;
    }

    public final Map getNumbersOfLatencySampledSpans()
    {
        return numbersOfLatencySampledSpans;
    }

    public final int hashCode()
    {
        return (numbersOfLatencySampledSpans.hashCode() ^ 0xf4243) * 0xf4243 ^ numbersOfErrorSampledSpans.hashCode();
    }

    public final String toString()
    {
        String s = String.valueOf(numbersOfLatencySampledSpans);
        String s1 = String.valueOf(numbersOfErrorSampledSpans);
        return (new StringBuilder(String.valueOf(s).length() + 78 + String.valueOf(s1).length())).append("PerSpanNameSummary{numbersOfLatencySampledSpans=").append(s).append(", numbersOfErrorSampledSpans=").append(s1).append("}").toString();
    }
}
